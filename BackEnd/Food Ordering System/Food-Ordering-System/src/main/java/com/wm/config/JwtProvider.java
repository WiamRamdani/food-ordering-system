// package com.wm.config;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.security.Keys;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.stereotype.Service;

// import javax.crypto.SecretKey;
// import java.util.Collection;
// import java.util.Date;
// import java.util.HashSet;
// import java.util.Set;

// @Service
// public class JwtProvider {
//     private final SecretKey key;

//     @Autowired
//     public JwtProvider(JwtConfig jwtConfig) {
//         this.key = jwtConfig.getSecretKey();
//     }
//     public String generateToken(Authentication authentication) {
//         Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//         String roles = popularAuthorities(authorities);
        
//         String jwt = Jwts.builder()
//                 .setSubject(authentication.getName())
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24h de validité
//                 .claim("email", authentication.getName())
//                 .claim("authorities", roles)
//                 .signWith(key)  // ici, on utilise bien un SecretKey
//                 .compact();

//         return jwt;
//     }

//     public String getEmailFromToken(String jwt) {
//         Claims claims =Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
//         String email =String.valueOf(claims.get("email"));
//         return email;
//     }

//     private String popularAuthorities(Collection<? extends GrantedAuthority> authorities) {
//         Set<String> auth=new HashSet<>();
//         for (GrantedAuthority Authority : authorities) {
//             auth.add(Authority.getAuthority());
//         }
//         return String.join(",", auth);
//     }

// }
package com.wm.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKeyEncoded;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey key;

    @jakarta.annotation.PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKeyEncoded);
        this.key = Keys.hmacShaKeyFor(keyBytes); // Assure une clé de taille correcte
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Invalid JWT: " + e.getMessage());
        }
        return false;
    }
}
