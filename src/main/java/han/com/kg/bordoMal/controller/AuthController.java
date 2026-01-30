package han.com.kg.bordoMal.controller;

import han.com.kg.bordoMal.dto.JwtAuthenticationDto;
import han.com.kg.bordoMal.dto.RefreshTokenDto;
import han.com.kg.bordoMal.dto.UserCredentialsDto;
import han.com.kg.bordoMal.dto.request.UserRequest;
import han.com.kg.bordoMal.dto.response.UserResponse;
import han.com.kg.bordoMal.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("signUp")
    public ResponseEntity<UserResponse> singUp (@RequestBody @Valid UserRequest request){
        UserResponse response=service.signUp(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sing-in")
    public ResponseEntity<JwtAuthenticationDto> singIn(@RequestBody UserCredentialsDto userCredentialsDto) {
        try {
            JwtAuthenticationDto jwtAuthenticationDto = service.signIn(userCredentialsDto);
            return ResponseEntity.ok(jwtAuthenticationDto);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/refresh")
    public JwtAuthenticationDto refresh(@RequestBody RefreshTokenDto refreshTokenDto) throws Exception {
        System.out.println("Получен RefreshTokenDto: " + refreshTokenDto);
        System.out.println("refreshToken значение: " + refreshTokenDto.getRefreshToken());
        return service.reFreshToken(refreshTokenDto);
    }


}
