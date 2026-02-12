package han.com.kg.bordoMal.service.impl;

import han.com.kg.bordoMal.dto.response.ImageResponse;
import han.com.kg.bordoMal.exception.NotFoundException;
import han.com.kg.bordoMal.exception.TooManyImagesException;
import han.com.kg.bordoMal.mapper.ImageMapper;
import han.com.kg.bordoMal.model.Ad;
import han.com.kg.bordoMal.model.Image;
import han.com.kg.bordoMal.repository.AdRepository;
import han.com.kg.bordoMal.repository.ImageRepository;
import han.com.kg.bordoMal.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private static final int MAX_IMAGES_PER_AD = 5;
    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
            "image/jpeg", "image/png", "image/gif", "image/webp"
    );

    private final ImageRepository imageRepository;
    private final AdRepository adRepository;
    private final ImageMapper imageMapper;

    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    public ImageServiceImpl(ImageRepository imageRepository, AdRepository adRepository, ImageMapper imageMapper) {
        this.imageRepository = imageRepository;
        this.adRepository = adRepository;
        this.imageMapper = imageMapper;
    }

    @Override
    @Transactional
    public ImageResponse uploadImage(Long adId, MultipartFile file, boolean main) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is required");
        }
        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType)) {
            throw new IllegalArgumentException("Allowed types: JPEG, PNG, GIF, WEBP");
        }

        Ad ad = adRepository.findById(adId).orElseThrow(() -> new NotFoundException("Ad not found"));
        long count = imageRepository.countByAdId(adId);
        if (count >= MAX_IMAGES_PER_AD) {
            throw new TooManyImagesException("Maximum " + MAX_IMAGES_PER_AD + " images per ad");
        }

        String extension = getExtension(contentType);
        String filename = UUID.randomUUID() + extension;
        Path adDir = Paths.get(uploadDir, "ads", adId.toString());
        Path filePath = adDir.resolve(filename);

        try {
            Files.createDirectories(adDir);
            file.transferTo(filePath.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image: " + e.getMessage());
        }

        // URL для доступа через наш сервер (раздаём через /api/uploads/...)
        String url = "/api/uploads/ads/" + adId + "/" + filename;

        Image image = new Image();
        image.setUrl(url);
        image.setMain(main);
        image.setAd(ad);
        Image saved = imageRepository.save(image);

        if (main) {
            imageRepository.findByAd_Id(adId).stream()
                    .filter(img -> !img.getId().equals(saved.getId()))
                    .forEach(img -> {
                        img.setMain(false);
                        imageRepository.save(img);
                    });
        }

        return imageMapper.tDto(saved);
    }

    @Override
    @Transactional
    public ImageResponse updateImage(Long imageId, boolean main) {
        Image image = imageRepository.findById(imageId).orElseThrow(() -> new NotFoundException("Image not found"));
        image.setMain(main);
        imageRepository.save(image);
        if (main && image.getAd() != null) {
            Long adId = image.getAd().getId();
            imageRepository.findByAd_Id(adId).stream()
                    .filter(img -> !img.getId().equals(imageId))
                    .forEach(img -> {
                        img.setMain(false);
                        imageRepository.save(img);
                    });
        }
        return imageMapper.tDto(image);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ImageResponse> getImagesByAd(Long adId) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new NotFoundException("Ad not found"));
        return ad.getImages().stream().map(imageMapper::tDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ImageResponse getImage(Long imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow(() -> new NotFoundException("Image not found"));
        return imageMapper.tDto(image);
    }

    @Override
    @Transactional
    public void deleteImage(Long imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow(() -> new NotFoundException("Image not found"));
        String url = image.getUrl();
        imageRepository.delete(image);
        deleteFileFromDisk(url);
    }

    private String getExtension(String contentType) {
        return switch (contentType) {
            case "image/png" -> ".png";
            case "image/gif" -> ".gif";
            case "image/webp" -> ".webp";
            default -> ".jpg";
        };
    }

    private void deleteFileFromDisk(String url) {
        try {
            if (url == null || !url.startsWith("/api/uploads/ads/")) return;
            String relative = url.replace("/api/uploads/", "");
            Path path = Paths.get(uploadDir, relative);
            if (Files.exists(path)) {
                Files.delete(path);
            }
        } catch (IOException ignored) {
            // лог при необходимости
        }
    }

    public Path getFilePathForUrl(String url) {
        if (url == null || !url.startsWith("/api/uploads/ads/")) return null;
        String relative = url.replace("/api/uploads/", "");
        return Paths.get(uploadDir, relative);
    }
}
