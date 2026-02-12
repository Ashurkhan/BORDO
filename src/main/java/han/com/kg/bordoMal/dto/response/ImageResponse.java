package han.com.kg.bordoMal.dto.response;

import java.time.LocalDateTime;

/**
 * Ответ с данными изображения.
 * url — относительный путь (например /api/uploads/ads/1/uuid.jpg), фронт подставляет базовый URL сервера.
 */
public class ImageResponse {
    private Long id;
    private String url;
    private boolean main;
    private LocalDateTime createdAt;

    public ImageResponse(Long id, String url, boolean main, LocalDateTime createdAt) {
        this.id = id;
        this.url = url;
        this.main = main;
        this.createdAt = createdAt;
    }

    public ImageResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
