package han.com.kg.bordoMal.service;

import han.com.kg.bordoMal.dto.response.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    ImageResponse uploadImage(Long adId, MultipartFile file, boolean main);

    ImageResponse updateImage(Long imageId, boolean main);

    List<ImageResponse> getImagesByAd(Long adId);

    ImageResponse getImage(Long imageId);

    void deleteImage(Long imageId);
}
