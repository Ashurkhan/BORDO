package han.com.kg.bordoMal.controller;

import han.com.kg.bordoMal.dto.request.AdRequest;
import han.com.kg.bordoMal.dto.request.AdUpdateRequest;
import han.com.kg.bordoMal.dto.response.AdResponse;
import han.com.kg.bordoMal.model.User;
import han.com.kg.bordoMal.security.CustomUserDetails;
import han.com.kg.bordoMal.service.AdService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PatchMapping("update/{id}")
    public ResponseEntity<AdResponse> updateAd(@RequestBody @Valid AdUpdateRequest request, @PathVariable Long id, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        if (customUserDetails == null) {
            throw new RuntimeException("USER NOT AUTHENTICATED");
        }
        User seller=customUserDetails.user();
        AdResponse response=adService.update(id,request,seller);
        return  ResponseEntity.ok(response);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<AdResponse> getAd(@PathVariable Long id){
        AdResponse response=adService.getById(id);
        return  ResponseEntity.ok(response);

    }

    @GetMapping("getAll")
    public ResponseEntity<List<AdResponse>> getAll() {
        List<AdResponse> responses = adService.getAll();
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable Long id){
        adService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
