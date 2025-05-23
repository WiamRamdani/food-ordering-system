package com.wm.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

// public class JwtTockenValidator extends OncePerRequestFilter {

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain) throws ServletException, IOException {
//                                         String path = request.getServletPath();

//         //  Ignorer les endpoints d'authentification
//         if (path.startsWith("/api/auth/signup") || path.startsWith("/api/auth/login")) {
//             filterChain.doFilter(request, response);
//             return;
//         }
        
//         String jwt = request.getHeader(JwtConstant.JWT_HEADER);
//         if (jwt != null) {
//             jwt = jwt.substring(7);
//             try{
//                 SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
//                 Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
//                 String email=String.valueOf(claims.get("email"));
//                  String authorities=String.valueOf(claims.get("authorities"));
//                 List <GrantedAuthority> auth= AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
//                 Authentication authentication=new UsernamePasswordAuthenticationToken(email,null,auth);
//                 SecurityContextHolder.getContext().setAuthentication(authentication);
//             }
//             catch (Exception e){
//                 e.printStackTrace();
//                 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                 response.getWriter().write("Invalid or expired JWT token");
//                 return; // Stop la chaîne de filtre ici}
//             }
//         }else{
//             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             response.getWriter().write("Missing or invalid Authorization header");
//             return;
//         }
//         filterChain.doFilter(request, response);
    
// }}
public class JwtTockenValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();

        // Ignorer tous les endpoints d'authentification publics
        if (path.startsWith("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = request.getHeader(JwtConstant.JWT_HEADER);
        if (jwt != null && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
            try {
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
                String email = String.valueOf(claims.get("email"));
                String authorities = String.valueOf(claims.get("authorities"));
                List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auth);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or expired JWT token");
                return; // Stop la chaîne de filtres
            }
        } else {
            // Pas de token, mais on ne bloque pas ici.
            // On laisse passer la requête (ex: endpoints publics)
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
