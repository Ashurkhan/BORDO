package han.com.kg.bordoMal.repository;

import han.com.kg.bordoMal.model.Ad;
import han.com.kg.bordoMal.model.Favorite;
import han.com.kg.bordoMal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    boolean existsByUserAndAd(User user, Ad ad);
    Optional<Favorite> findByUserAndAd(User user, Ad ad);
    List<Favorite> findAllByUser(User user);
}