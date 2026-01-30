package han.com.kg.bordoMal.security.jwt;

import han.com.kg.bordoMal.dto.JwtAuthenticationDto;
import han.com.kg.bordoMal.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    private static final Logger LOGGER = LogManager.getLogger(JwtService.class);

    @Value("${jwt.secret}")
    private String jwtSecret;


    public JwtAuthenticationDto generateAuthToken(User user){
        JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();
        jwtDto.setToken(generateToken(user));
        jwtDto.setRefreshToken(generateRefreshToken(user));
        return jwtDto;
    }
    public JwtAuthenticationDto refreshBaseToken(User user, String refreshToken){
        JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();
        jwtDto.setToken(generateToken(user));
        jwtDto.setRefreshToken(refreshToken);
        return jwtDto;
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(getSingInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        }catch (ExpiredJwtException expEx){
            LOGGER.error("Expired JwtException", expEx);
        }catch (UnsupportedJwtException expEx){
            LOGGER.error("Unsupported JwtException", expEx);
        }catch (MalformedJwtException expEx){
            LOGGER.error("Malformed JwtException", expEx);
        }catch (SecurityException expEx){
            LOGGER.error("Security Exception", expEx);
        }catch (Exception expEx){
            LOGGER.error("invalid token", expEx);
        }
        return false;
    }

    public String extractEmailFromToken(String token) {
        Claims claims=Jwts.parser()
                .verifyWith(getSingInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    public String extractTokenType(String token) {
        if (token == null || token.trim().isEmpty()) {
            return null;
        }
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getSingInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return claims.get("type", String.class);  // вернёт null, если claim отсутствует

        } catch (ExpiredJwtException e) {
            LOGGER.warn("Token expired while extracting type", e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Unexpected error extracting token type", e);
            return null;
        }
    }


    private String generateToken(User user) {
        Date date = Date.from(LocalDateTime.now().plusMinutes(10).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole())
                .claim("type", "access")
                .issuedAt(new Date())
                .expiration(date)
                .signWith(getSingInKey())
                .compact();
    }




    private String generateRefreshToken(User user) {
        Date date = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("type", "refresh")
                .issuedAt(new Date())
                .expiration(date)
                .signWith(getSingInKey())
                .compact();
    }





    private SecretKey getSingInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
