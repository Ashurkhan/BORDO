package han.com.kg.bordoMal.repository;

import han.com.kg.bordoMal.model.AdView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdViewRepository extends JpaRepository<AdView, Long> {

    Long countByAdId(Long adId);
}