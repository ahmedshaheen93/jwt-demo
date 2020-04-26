package com.shaheen.jwtdemo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@EnableConfigurationProperties
public class TokenUtil {
    private static final String CLAIMS_SUBJECT = "sub";
    private static final String CLAIMS_CREATED = "created";
    private Logger logger = LogManager.getLogger(TokenUtil.class);
    //    @Value("${auth.expiration}")
    private Long tokenValidity = 1209600L;

    @Value("${auth.secret}")
    private String tokenSecret;


    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(0);
        claims.put(CLAIMS_SUBJECT, userDetails.getUsername());
        claims.put(CLAIMS_CREATED, new Date());
        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
    }

    public String getUserNameFromToken(String token) {
        try {
            Claims claims = getClaims(token);
            if (claims != null) {
                return claims.getSubject();
            }
        } catch (Exception e) {
            logger.log(Level.WARN, e.getMessage());
        }
        return null;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + tokenValidity * 1000);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        }
        return false;
    }

    private Claims getClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(tokenSecret)
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
}
