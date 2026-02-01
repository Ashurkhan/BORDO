package han.com.kg.bordoMal.service.impl;

import han.com.kg.bordoMal.dto.response.AdResponse;
import han.com.kg.bordoMal.exception.NotFoundException;
import han.com.kg.bordoMal.mapper.AdMapper;
import han.com.kg.bordoMal.model.Ad;
import han.com.kg.bordoMal.model.Favorite;
import han.com.kg.bordoMal.model.User;
import han.com.kg.bordoMal.repository.AdRepository;
import han.com.kg.bordoMal.repository.FavoriteRepository;
import han.com.kg.bordoMal.service.FavoriteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final AdRepository adRepository;
    private final AdMapper mapper;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, AdRepository adRepository,AdMapper mapper) {
        this.favoriteRepository = favoriteRepository;
        this.adRepository = adRepository;
        this.mapper=mapper;
    }

    @Override
    public void addToFavorites(User user, Long adId) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new NotFoundException("Ad не найден"));

        // Проверка, есть ли уже в избранном
        boolean exists = favoriteRepository.existsByUserAndAd(user, ad);
        if (exists) {
            throw new RuntimeException("Ad уже в избранном");
        }

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setAd(ad);

        favoriteRepository.save(favorite);
    }

    @Override
    public void removeFromFavorites(User user, Long adId) {
        Favorite favorite = favoriteRepository.findByUserAndAd(user, adRepository.getReferenceById(adId))
                .orElseThrow(() -> new NotFoundException("Favorite не найден"));
        favoriteRepository.delete(favorite);
    }

    @Override
    public Set<AdResponse> getFavorites(User user) {
        return favoriteRepository.findAllByUser(user).stream()
                .map(fav -> mapper.tDto(fav.getAd()))
                .collect(Collectors.toSet());

    }

}
