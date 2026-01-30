package han.com.kg.bordoMal.repository;

import han.com.kg.bordoMal.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category ,Long > {
}
