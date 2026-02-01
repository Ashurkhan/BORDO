package han.com.kg.bordoMal.service;

import han.com.kg.bordoMal.dto.request.AdRequest;
import han.com.kg.bordoMal.dto.request.AdUpdateRequest;
import han.com.kg.bordoMal.dto.response.AdResponse;
import han.com.kg.bordoMal.model.Ad;
import han.com.kg.bordoMal.model.User;

import java.util.List;

public interface AdService {
    AdResponse createAd(AdRequest request, User seller);
    AdResponse getById(Long id);
    List<AdResponse> getAll();
    AdResponse update(Long id, AdUpdateRequest request, User seller);
    void delete(Long id);
}
