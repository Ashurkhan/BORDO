package han.com.kg.bordoMal.dto.request;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.pl.NIP;

public class FavoriteRequest {
    @NotNull
    private Long adId;

    public FavoriteRequest(@NotNull Long adId){
        this.adId=adId;
    }
    public FavoriteRequest(){}

    public @NotNull Long getAdId() {
        return adId;
    }

    public void setAdId(@NotNull Long adId) {
        this.adId = adId;
    }
}
