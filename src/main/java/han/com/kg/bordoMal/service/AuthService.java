package han.com.kg.bordoMal.service;

import han.com.kg.bordoMal.dto.JwtAuthenticationDto;
import han.com.kg.bordoMal.dto.RefreshTokenDto;
import han.com.kg.bordoMal.dto.UserCredentialsDto;
import han.com.kg.bordoMal.dto.request.UserRequest;
import han.com.kg.bordoMal.dto.response.UserResponse;

import javax.naming.AuthenticationException;

public interface AuthService {
    UserResponse signUp(UserRequest request);
    JwtAuthenticationDto signIn(UserCredentialsDto dto) throws AuthenticationException;
    JwtAuthenticationDto reFreshToken(RefreshTokenDto dto) throws  Exception;}
