package han.com.kg.bordoMal.service;

import han.com.kg.bordoMal.dto.response.AdResponse;
import han.com.kg.bordoMal.model.User;

import java.util.Set;

public interface FavoriteService {
     void addToFavorites(User user, Long adId);
    void removeFromFavorites(User user, Long adId);
    Set<AdResponse> getFavorites(User user);
}
