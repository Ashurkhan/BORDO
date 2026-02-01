package han.com.kg.bordoMal.repository;

import han.com.kg.bordoMal.model.Ad;
import han.com.kg.bordoMal.model.AdVote;
import han.com.kg.bordoMal.model.AdVoteType;
import han.com.kg.bordoMal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdVoteRepository  extends JpaRepository<AdVote,Long> {
    Optional<AdVote> findByUserAndAd(User user, Ad ad);
    long countByAdAndType(Ad ad, AdVoteType type);

    Optional<AdVote> findByAdAndUser(Ad ad, User user);
}
