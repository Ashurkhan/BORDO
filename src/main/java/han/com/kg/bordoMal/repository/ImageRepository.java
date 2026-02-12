package han.com.kg.bordoMal.repository;

import han.com.kg.bordoMal.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    long countByAdId(Long adId);

    java.util.List<Image> findByAd_Id(Long adId);
}
