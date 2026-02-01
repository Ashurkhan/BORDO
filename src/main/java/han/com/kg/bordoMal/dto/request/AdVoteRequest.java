package han.com.kg.bordoMal.dto.request;

import han.com.kg.bordoMal.model.AdVoteType;
import jakarta.validation.constraints.NotNull;

public class AdVoteRequest {
    @NotNull
    private Long adId;

    @NotNull
    private AdVoteType type;
    private Boolean remove;// LIKE или DISLIKE

    public AdVoteRequest(Long adId, AdVoteType type,Boolean remove) {
        this.adId = adId;
        this.type = type;
        this.remove=remove;
    }
    public AdVoteRequest(){}

    public @NotNull Long getAdId() {
        return adId;
    }

    public void setAdId(@NotNull Long adId) {
        this.adId = adId;
    }

    public @NotNull AdVoteType getType() {
        return type;
    }

    public void setType(@NotNull AdVoteType type) {
        this.type = type;
    }

    public Boolean getRemove() {
        return remove;
    }

    public void setRemove(Boolean remove) {
        this.remove = remove;
    }
}