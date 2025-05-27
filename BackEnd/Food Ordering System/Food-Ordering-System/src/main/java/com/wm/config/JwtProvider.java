package com.wm.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private SecretKey getSigningKey() { 
        // byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        // System.out.println("Key bytes length: " + keyBytes.length);
        // return Keys.hmacShaKeyFor(jwtSecret.getBytes());
        // byte[] decodedKey = Base64.getUrlDecoder().decode(jwtSecret);
        // return new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA512");
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getEmailFromToken(String jwt) {
        if (jwt.startsWith("Bearer ")) {
        jwt = jwt.substring(7).trim();
    } else {
        jwt = jwt.trim();
    }
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        String email = claims.getSubject();
        return email;
    }

    // private String popularAuthorities(Collection<? extends GrantedAuthority> authorities) {
    //     Set<String> auth = new HashSet<>();
    //     for (GrantedAuthority authority : authorities) {
    //         auth.add(authority.getAuthority());
    //     }
    //     return String.join(",", auth);
    // }
}
