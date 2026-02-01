package han.com.kg.bordoMal.controller;

import han.com.kg.bordoMal.service.AdViewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ads/view")
public class AdViewController {
    private final AdViewService adViewService;

    public AdViewController(AdViewService adViewService) {
        this.adViewService = adViewService;
    }

    @PostMapping("/{adId}/view")
    public ResponseEntity<String> addView(
            @PathVariable Long adId,
            @RequestParam(required = false) Long viewerId
    ) {
        adViewService.addView(adId, viewerId);
        return ResponseEntity.ok("View added");
    }
}
