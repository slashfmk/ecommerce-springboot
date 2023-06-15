package com.slashfmk.ecommerce.jwt;

import com.slashfmk.ecommerce.service.UserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;

import static java.time.temporal.ChronoUnit.DAYS;

import java.util.Map;
import java.util.Date;


@Service
public class JWTUtil {

    public static final String SECRET_KEY = "nnddedededededeffrfrfrfven_1864_uijdnbcebubc jce_51233!!";


    public String issueToken(String subject) {
        return issueToken(subject, Map.of());
    }

    public String issueToken(String subject, String... scopes) {
        return issueToken(subject, Map.of("scopes", scopes));
    }


    public String issueToken(String subject, Map<String, Object> claims) {

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuer("https://yannickfumukani.com")
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(15, DAYS)))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }


    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    public Claims getClaims(String token) {

        var claims = Jwts.parserBuilder()
                .setSigningKey(this.getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }


    private Key getSigninKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public boolean isTokenValid(String jwt, String username) {

        String subject = getSubject(jwt);
        return subject.equals(username) && !isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String jwt) {

        Date today = Date.from(Instant.now());

        return getClaims(jwt).getExpiration().before(today);
    }
}
