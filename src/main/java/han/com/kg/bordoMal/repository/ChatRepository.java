package han.com.kg.bordoMal.repository;

import han.com.kg.bordoMal.model.Ad;
import han.com.kg.bordoMal.model.Chat;
import han.com.kg.bordoMal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat,Long> {
    Optional<Chat> findByAdAndBuyerAndSeller(Ad ad, User buyer, User seller);
}
