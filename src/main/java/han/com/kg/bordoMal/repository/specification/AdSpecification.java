package han.com.kg.bordoMal.repository.specification;

import han.com.kg.bordoMal.dto.request.AdFilterRequest;
import han.com.kg.bordoMal.model.Ad;
import han.com.kg.bordoMal.model.Location;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AdSpecification {

    public static Specification<Ad> withFilters(AdFilterRequest filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter != null) {
                if (filter.getTitle() != null && !filter.getTitle().isBlank()) {
                    predicates.add(cb.like(cb.lower(root.get("title")),
                            "%" + filter.getTitle().toLowerCase().trim() + "%"));
                }
                if (filter.getCategoryId() != null) {
                    predicates.add(cb.equal(root.get("category").get("id"), filter.getCategoryId()));
                }
                if (filter.getMinPrice() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.getMinPrice()));
                }
                if (filter.getMaxPrice() != null) {
                    predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.getMaxPrice()));
                }
                if (filter.getStatus() != null) {
                    predicates.add(cb.equal(root.get("status"), filter.getStatus()));
                }
                if ((filter.getRegion() != null && !filter.getRegion().isBlank()) ||
                        (filter.getCity() != null && !filter.getCity().isBlank())) {
                    Join<Ad, Location> locationJoin = root.join("location");
                    if (filter.getRegion() != null && !filter.getRegion().isBlank()) {
                        predicates.add(cb.like(cb.lower(locationJoin.get("region")),
                                "%" + filter.getRegion().toLowerCase().trim() + "%"));
                    }
                    if (filter.getCity() != null && !filter.getCity().isBlank()) {
                        predicates.add(cb.like(cb.lower(locationJoin.get("city")),
                                "%" + filter.getCity().toLowerCase().trim() + "%"));
                    }
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
