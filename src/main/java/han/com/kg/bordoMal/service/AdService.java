package han.com.kg.bordoMal.service;

import han.com.kg.bordoMal.dto.request.AdRequest;
import han.com.kg.bordoMal.dto.response.AdResponse;
import han.com.kg.bordoMal.model.User;

public interface AdService {
    AdResponse createAd(AdRequest request, User seller);
}
