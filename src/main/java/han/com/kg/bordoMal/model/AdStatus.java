package han.com.kg.bordoMal.model;

public enum AdStatus {
    DRAFT,
    ACTIVE,
    SOLD,
    BLOCKED;

    // Для фронтенда и API
    public String getDisplayName() {
        return switch (this) {
            case DRAFT  -> "Черновик";
            case ACTIVE -> "Активное";
            case SOLD   -> "Продано";
            case BLOCKED -> "Заблокировано";
        };
    }

}
