package han.com.kg.bordoMal.service.impl;

import han.com.kg.bordoMal.dto.request.AdRequest;
import han.com.kg.bordoMal.dto.request.AdUpdateRequest;
import han.com.kg.bordoMal.dto.response.AdResponse;
import han.com.kg.bordoMal.exception.NotFoundException;
import han.com.kg.bordoMal.mapper.AdMapper;
import han.com.kg.bordoMal.model.Ad;
import han.com.kg.bordoMal.model.AdStatus;
import han.com.kg.bordoMal.model.User;
import han.com.kg.bordoMal.repository.AdRepository;
import han.com.kg.bordoMal.service.AdService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final AdMapper adMapper;

    public AdServiceImpl(AdRepository adRepository, AdMapper adMapper) {
        this.adRepository = adRepository;
        this.adMapper = adMapper;
    }

    @Transactional
    @Override
    public AdResponse createAd(AdRequest request, User seller) {
        Ad ad= adMapper.toEntity(request);
        ad.setSeller(seller);
        Ad savedAd = adRepository.save(ad);
        return adMapper.tDto(savedAd);
    }

    @Override
    public AdResponse getById(Long id) {
        Ad ad=adRepository.findById(id).orElseThrow(()-> new NotFoundException("Ad not found"));
        return adMapper.tDto(ad);
    }

    @Override
    public List<AdResponse> getAll() {
        List<Ad> ads=adRepository.findAll();
        return  adMapper.tDtos(ads);
    }

    @Override
    public AdResponse update(Long id, AdUpdateRequest request, User seller) {
        Ad ad=adRepository.findById(id).orElseThrow(()->new NotFoundException("not found"));
        if (!ad.getSeller().getId().equals(seller.getId())) {
            throw new AccessDeniedException("You cannot update this ad");
        }
        adMapper.update(ad,request);
        Ad saved=adRepository.save(ad);
        return adMapper.tDto(saved);
    }

    @Override
    public void delete(Long id) {
        Ad ad=adRepository.findById(id).orElseThrow(()-> new NotFoundException("not found"));
        adRepository.delete(ad);
    }
}
