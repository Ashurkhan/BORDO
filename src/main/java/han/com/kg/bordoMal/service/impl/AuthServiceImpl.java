package han.com.kg.bordoMal.service.impl;

import han.com.kg.bordoMal.dto.JwtAuthenticationDto;
import han.com.kg.bordoMal.dto.RefreshTokenDto;
import han.com.kg.bordoMal.dto.UserCredentialsDto;
import han.com.kg.bordoMal.dto.request.UserRequest;
import han.com.kg.bordoMal.dto.response.UserResponse;
import han.com.kg.bordoMal.exception.NotFoundException;
import han.com.kg.bordoMal.mapper.UserMapper;
import han.com.kg.bordoMal.model.User;
import han.com.kg.bordoMal.model.UserRole;
import han.com.kg.bordoMal.model.UserStatus;
import han.com.kg.bordoMal.repository.UserRepository;
import han.com.kg.bordoMal.security.jwt.JwtService;
import han.com.kg.bordoMal.service.AuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    private static final Logger LOGGER = LogManager.getLogger(JwtService.class);


    public AuthServiceImpl(UserRepository repository, UserMapper mapper,JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.jwtService=jwtService;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public UserResponse signUp(UserRequest request) {
        User user=mapper.toEntity(request);
        user.setPasswordHash(passwordEncoder.encode(request.getPasswordHash()));
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVE);
        repository.save(user);
        return mapper.tDto(user);
    }

    @Override
    public JwtAuthenticationDto signIn(UserCredentialsDto dto) throws AuthenticationException {
        User user= findByCredentialDto(dto);
        return jwtService.generateAuthToken(user);
    }

    private User findByCredentialDto(UserCredentialsDto dto) throws AuthenticationException {
        Optional<User>optionalUser= repository.findByEmail(dto.getEmail());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if (passwordEncoder.matches(dto.getPassword(), user.getPasswordHash())){
                return user;
            }
        }
        throw new AuthenticationException("Email or password is not correct");
    }

    @Override
    public JwtAuthenticationDto reFreshToken(RefreshTokenDto dto)  throws Exception{
        String refreshToken = dto.getRefreshToken();

        if (refreshToken == null || refreshToken.isBlank()) {
            throw new AuthenticationException("Refresh token is required");
        }

        if (!jwtService.validateToken(refreshToken)) {
            throw new AuthenticationException("Invalid or expired refresh token");
        }

        String tokenType = jwtService.extractTokenType(refreshToken);
        if (!"refresh".equals(tokenType)) {
            throw new AuthenticationException("Provided token is not a refresh token (type: "
                    + (tokenType != null ? tokenType : "missing") + ")");
        }

        try {
            User user =findByEmail(jwtService.extractEmailFromToken(refreshToken));
            if (user == null) {
                throw new AuthenticationException("User not found for this token");
            }
            return jwtService.refreshBaseToken(user, refreshToken);
        } catch (Exception e) {
            LOGGER.error("Error during refresh token processing", e);
            throw new AuthenticationException("Failed to process refresh token");
        }
    }

    private User findByEmail(String email)  {
        return repository.findByEmail(email).orElseThrow(()-> new NotFoundException("not found"));
    }


}
