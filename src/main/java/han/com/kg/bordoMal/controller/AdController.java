package han.com.kg.bordoMal.controller;

import han.com.kg.bordoMal.dto.request.AdRequest;
import han.com.kg.bordoMal.dto.response.AdResponse;
import han.com.kg.bordoMal.model.Ad;
import han.com.kg.bordoMal.model.User;
import han.com.kg.bordoMal.security.CustomUserDetails;
import han.com.kg.bordoMal.service.AdService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ad/")
public class AdController {
    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping("create")
    public ResponseEntity<AdResponse> createAd(@RequestBody @Valid AdRequest request, @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        if (customUserDetails == null) {
            throw new RuntimeException("USER NOT AUTHENTICATED");
        }
        User seller = customUserDetails.user();
        AdResponse response = adService.createAd(request, seller);
        return ResponseEntity.ok(response);
    }

}
