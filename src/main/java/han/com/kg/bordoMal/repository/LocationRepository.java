package han.com.kg.bordoMal.repository;

import han.com.kg.bordoMal.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location ,Long> {
}
