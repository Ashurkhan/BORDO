package han.com.kg.bordoMal.controller;

import han.com.kg.bordoMal.dto.request.FavoriteRequest;
import han.com.kg.bordoMal.dto.response.AdResponse;
import han.com.kg.bordoMal.model.User;
import han.com.kg.bordoMal.security.CustomUserDetails;
import han.com.kg.bordoMal.service.FavoriteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/api/favorite")
@RestController
public class FavoriteController {
    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody @Valid FavoriteRequest request,
                                      @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = customUserDetails.user();
        favoriteService.addToFavorites(user, request.getAdId());
        return ResponseEntity.ok("Ad добавлен в избранное");
    }

    @DeleteMapping("/remove/{adId}")
    public ResponseEntity<String> remove(@PathVariable Long adId,
                                         @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = customUserDetails.user();
        favoriteService.removeFromFavorites(user, adId);
        return ResponseEntity.ok("Ad удалён из избранного");
    }

    @GetMapping
    public ResponseEntity<Set<AdResponse>> list(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = customUserDetails.user();
        Set<AdResponse> favorites = favoriteService.getFavorites(user);
        return ResponseEntity.ok(favorites);
    }
}
