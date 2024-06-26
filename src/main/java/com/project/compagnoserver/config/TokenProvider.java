package com.project.compagnoserver.config;

import com.project.compagnoserver.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

@Service
public class TokenProvider {

    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String create(User user) {
        return Jwts.builder()
                .signWith(secretKey)
                .setClaims(Map.of(
                        "userId", user.getUserId(),
                        "userPersonName", user.getUserPersonName(),
                        "userNickname", user.getUserNickname(),
                        "userImg", user.getUserImg(),
                        "userRole", user.getUserRole(),
                        "userPhone", user.getUserPhone(),
                        "userEmail", user.getUserEmail(),
                        "userStatus",user.getUserStatus(),
                        "userEnrollDate", user.getUserEnrollDate()
                ))
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS))) // 로그인 토큰 유지기간 1일
                .compact();
    }
    public User validateGetUser(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(secretKey)
                            .parseClaimsJws(token).getBody();

        return User.builder()
                .userId((String) claims.get("userId"))
                .userPersonName((String) claims.get("userPersonName"))
                .userNickname((String)claims.get("userNickname"))
                .userImg((String)claims.get("userImg"))
                .userRole((String) claims.get("userRole"))
                .userPhone((String) claims.get("userPhone"))
                .userEmail((String) claims.get("userEmail"))
                .userStatus((String) claims.get("userStatus"))
                .userEnrollDate((String) claims.get("userEnrollDate"))
                .build();
    }
}
