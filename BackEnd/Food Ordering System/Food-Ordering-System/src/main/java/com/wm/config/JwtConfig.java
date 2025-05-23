package com.wm.config;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtConfig {

    @Value("${jwt.secret}")
    private String secret;

    public SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret); 
        System.out.println("Clé décodée : " + keyBytes.length + " octets");
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
