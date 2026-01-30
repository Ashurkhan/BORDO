package han.com.kg.bordoMal.service.impl;

import han.com.kg.bordoMal.dto.request.AdRequest;
import han.com.kg.bordoMal.dto.response.AdResponse;
import han.com.kg.bordoMal.mapper.AdMapper;
import han.com.kg.bordoMal.model.Ad;
import han.com.kg.bordoMal.model.AdStatus;
import han.com.kg.bordoMal.model.User;
import han.com.kg.bordoMal.repository.AdRepository;
import han.com.kg.bordoMal.service.AdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
