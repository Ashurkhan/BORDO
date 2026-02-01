package han.com.kg.bordoMal.mapper.impl;

import han.com.kg.bordoMal.dto.request.AdRequest;
import han.com.kg.bordoMal.dto.request.AdUpdateRequest;
import han.com.kg.bordoMal.dto.request.LocationRequest;
import han.com.kg.bordoMal.dto.response.*;
import han.com.kg.bordoMal.exception.NotFoundException;
import han.com.kg.bordoMal.mapper.AdMapper;
import han.com.kg.bordoMal.model.*;
import han.com.kg.bordoMal.repository.CategoryRepository;
import han.com.kg.bordoMal.service.AdVoteService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdMapperImpl implements AdMapper {
    private final CategoryRepository categoryRepository;
    private final AdVoteService adVoteService;
    public AdMapperImpl(CategoryRepository categoryRepository,AdVoteService adVoteservice ) {
        this.categoryRepository = categoryRepository;
        this.adVoteService=adVoteservice;
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

        response.setViewsCount(ad.getViews().size());
        response.setFavoritesCount(ad.getFavoritedBy().size());

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

        if (ad.getSubCategory() != null) {
            response.setSubCategory(new SubCategoryResponse(
                    ad.getSubCategory().getId(),
                    ad.getSubCategory().getName()
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

        response.setLikesCount(adVoteService.getLikesCount(ad));
        response.setDislikesCount(adVoteService.getDislikesCount(ad));

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
        ad.setCreatedAt(LocalDateTime.now());
        ad.setUpdatedAt(LocalDateTime.now());

        // ===== Category =====
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Категория не найдена"));
        ad.setCategory(category);

        // ===== SubCategory (user input) =====
        if (request.getSubCategory() != null &&
                request.getSubCategory().getName() != null &&
                !request.getSubCategory().getName().isBlank()) {

            SubCategory sub = new SubCategory();
            sub.setName(request.getSubCategory().getName().trim());
            ad.setSubCategory(sub);
        }

        // ===== Location =====
        if (request.getLocation() != null) {
            LocationRequest lr = request.getLocation();
            Location location = new Location();
            location.setCountry(lr.getCountry());
            location.setRegion(lr.getRegion());
            location.setCity(lr.getCity());
            location.setVillage(lr.getVillage());
            location.setStreet(lr.getStreet());
            ad.setLocation(location);
        }

        return ad;
    }

    // ======== UPDATE (PATCH) ========
    @Override
    public Ad update(Ad ad, AdUpdateRequest request) {
        if (request.getTitle() != null) ad.setTitle(request.getTitle());
        if (request.getDescription() != null) ad.setDescription(request.getDescription());
        if (request.getPrice() != null) ad.setPrice(request.getPrice());
        if (request.getCurrency() != null) ad.setCurrency(request.getCurrency());

        // Category
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Категория не найдена"));
            ad.setCategory(category);
        }

        // SubCategory
        if (request.getSubCategory() != null &&
                request.getSubCategory().getName() != null &&
                !request.getSubCategory().getName().isBlank()) {

            SubCategory sub = new SubCategory();
            sub.setName(request.getSubCategory().getName().trim());
            ad.setSubCategory(sub);
        }

        // Location
        if (request.getLocation() != null) {
            LocationRequest lr = request.getLocation();
            Location loc = ad.getLocation();
            if (loc == null) {
                loc = new Location();
                ad.setLocation(loc);
            }

            if (lr.getCountry() != null) loc.setCountry(lr.getCountry());
            if (lr.getRegion() != null) loc.setRegion(lr.getRegion());
            if (lr.getCity() != null) loc.setCity(lr.getCity());
            if (lr.getVillage() != null) loc.setVillage(lr.getVillage());
            if (lr.getStreet() != null) loc.setStreet(lr.getStreet());
        }

        ad.setUpdatedAt(LocalDateTime.now());
        return ad;
    }

}
