package han.com.kg.bordoMal.controller;


import han.com.kg.bordoMal.dto.request.AdVoteRequest;
import han.com.kg.bordoMal.exception.NotFoundException;
import han.com.kg.bordoMal.mapper.AdMapper;
import han.com.kg.bordoMal.model.Ad;
import han.com.kg.bordoMal.model.User;
import han.com.kg.bordoMal.repository.AdRepository;
import han.com.kg.bordoMal.security.CustomUserDetails;
import han.com.kg.bordoMal.service.AdVoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ads/vote")
public class AdVoteController {

    private final AdVoteService adVoteService;
    private final AdRepository adRepository;
    private final AdMapper adMapper;

    public AdVoteController(AdVoteService adVoteService, AdRepository adRepository, AdMapper adMapper) {
        this.adVoteService = adVoteService;
        this.adRepository = adRepository;
        this.adMapper = adMapper;
    }
    @PostMapping
    public ResponseEntity<String> vote(@RequestBody @Valid AdVoteRequest request,
                                       @AuthenticationPrincipal CustomUserDetails userDetails){
        User user=userDetails.user();
        Ad ad = adRepository.findById(request.getAdId())
                .orElseThrow(() -> new NotFoundException("Ad не найден"));
        adVoteService.vote(ad,user,request);
        return ResponseEntity.ok("Голос учтен");
    }

    @GetMapping("/{adId}")
    public ResponseEntity<Map<String, Long>> count(@PathVariable Long adId) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new NotFoundException("Ad не найден"));

        Map<String, Long> counts = new HashMap<>();
        counts.put("likes", adVoteService.getLikesCount(ad));
        counts.put("dislikes", adVoteService.getDislikesCount(ad));

        return ResponseEntity.ok(counts);
    }
}
