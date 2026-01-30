package han.com.kg.bordoMal.repository;

import han.com.kg.bordoMal.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<Ad,Long> {
}
