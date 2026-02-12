package han.com.kg.bordoMal.service;

import han.com.kg.bordoMal.dto.request.AdFilterRequest;
import han.com.kg.bordoMal.dto.request.AdRequest;
import han.com.kg.bordoMal.dto.request.AdUpdateRequest;
import han.com.kg.bordoMal.dto.response.AdResponse;
import han.com.kg.bordoMal.model.Ad;
import han.com.kg.bordoMal.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdService {
    AdResponse createAd(AdRequest request, User seller);
    AdResponse getById(Long id);
    Page<AdResponse> getAll(AdFilterRequest filter, Pageable pageable);
    AdResponse update(Long id, AdUpdateRequest request, User seller);
    void delete(Long id);
}
