package han.com.kg.bordoMal.mapper.impl;

import han.com.kg.bordoMal.dto.request.AdRequest;
import han.com.kg.bordoMal.dto.response.AdResponse;
import han.com.kg.bordoMal.dto.response.CategoryResponse;
import han.com.kg.bordoMal.dto.response.LocationResponse;
import han.com.kg.bordoMal.dto.response.SellerResponse;
import han.com.kg.bordoMal.exception.NotFoundException;
import han.com.kg.bordoMal.mapper.AdMapper;
import han.com.kg.bordoMal.model.Ad;
import han.com.kg.bordoMal.model.AdStatus;
import han.com.kg.bordoMal.model.Category;
import han.com.kg.bordoMal.model.Location;
import han.com.kg.bordoMal.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdMapperImpl implements AdMapper {
    private final CategoryRepository categoryRepository;

    public AdMapperImpl(CategoryRepository categoryRepository ) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public AdResponse tDto(Ad ad) {
        AdResponse response = new AdResponse();

        response.setId(ad.getId());
        response.setTitle(ad.getTitle());
        response.setDescription(ad.getDescription());
        response.setPrice(ad.getPrice());
        response.setCurrency(ad.getCurrency());
        response.setStatus(ad.getStatus());

        response.setCreatedAt(ad.getCreatedAt());
        response.setUpdatedAt(ad.getUpdatedAt());

        response.setViewsCount(ad.getViews() != null ? ad.getViews().size() : 0);
        response.setFavoritesCount(ad.getFavoritedBy() != null ? ad.getFavoritedBy().size() : 0);
        if (ad.getSeller() != null) {
            response.setSeller(new SellerResponse(
                    ad.getSeller().getId(),
                    ad.getSeller().getFullName()
            ));
        }
        if (ad.getCategory() != null) {
            response.setCategory(new CategoryResponse(
                    ad.getCategory().getId(),
                    ad.getCategory().getName()
            ));
        }
        if (ad.getLocation() != null) {
            response.setLocation(new LocationResponse(
                    ad.getLocation().getId(),
                    ad.getLocation().getStreet(),
                    ad.getLocation().getVillage(),
                    ad.getLocation().getCity(),
                    ad.getLocation().getRegion(),
                    ad.getLocation().getCountry()
            ));
        }

        return response;
    }


    @Override
    public List<AdResponse> tDtos(List<Ad> ads) {
        List<AdResponse> responses=new ArrayList<>();
        for(Ad ad:ads){
            responses.add(tDto(ad));
        }
        return responses;
    }

    @Override
    public Ad toEntity(AdRequest request) {

        Ad ad = new Ad();
        ad.setTitle(request.getTitle());
        ad.setDescription(request.getDescription());
        ad.setPrice(request.getPrice());
        ad.setCurrency(request.getCurrency());
        ad.setStatus(AdStatus.ACTIVE);
        Category category = categoryRepository.findById(request.getCategoryID())
                .orElseThrow(() -> new NotFoundException("Категория не найдена"));
        ad.setCategory(category);

        Location location = new Location();
        location.setCountry(request.getLocation().getCountry());
        location.setRegion(request.getLocation().getRegion());
        location.setCity(request.getLocation().getCity());
        location.setVillage(request.getLocation().getVillage());
        location.setStreet(request.getLocation().getStreet());

        ad.setLocation(location);

        return ad;
    }

}
