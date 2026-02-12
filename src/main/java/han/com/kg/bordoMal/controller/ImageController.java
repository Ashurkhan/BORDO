package han.com.kg.bordoMal.controller;

import han.com.kg.bordoMal.dto.response.ImageResponse;
import han.com.kg.bordoMal.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Загрузка фото к объявлению. Лимит — 5 фото на объявление.
     * Формат: multipart/form-data, поле "file" — файл, "main" — true/false (главное фото).
     */
    @PostMapping("/ad/{adId}")
    public ResponseEntity<ImageResponse> uploadImage(
            @PathVariable Long adId,
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "main", defaultValue = "false") boolean main
    ) {
        return ResponseEntity.ok(imageService.uploadImage(adId, file, main));
    }

    @PutMapping("/{imageId}")
    public ResponseEntity<ImageResponse> updateImage(
            @PathVariable Long imageId,
            @RequestParam(value = "main") boolean main
    ) {
        return ResponseEntity.ok(imageService.updateImage(imageId, main));
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
        imageService.deleteImage(imageId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ad/{adId}")
    public ResponseEntity<List<ImageResponse>> getImagesByAd(@PathVariable Long adId) {
        return ResponseEntity.ok(imageService.getImagesByAd(adId));
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<ImageResponse> getImage(@PathVariable Long imageId) {
        return ResponseEntity.ok(imageService.getImage(imageId));
    }
}
