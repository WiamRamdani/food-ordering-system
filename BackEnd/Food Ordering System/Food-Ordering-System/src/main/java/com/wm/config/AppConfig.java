package com.wm.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
@Configuration
@EnableWebSecurity
public class AppConfig {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(Authorize -> Authorize
                        .requestMatchers("/api/admin/**").hasAnyRole("ADMIN", "RESTAURANT_OWNER")
                        .requestMatchers("/api/home").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtTokenValidator(), BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource));

        return http.build();
    }

    @Bean
    public JwtTokenValidator jwtTokenValidator() {
        return new JwtTokenValidator(jwtSecret);
    }
}


// public class AppConfig {

//     @Bean
//     SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
//         http
//             .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .authorizeRequests(authorize -> authorize
//                 .requestMatchers("/api/auth/**").permitAll()        // endpoints login/signup publics
//                 .requestMatchers("/api/home").permitAll()           // endpoint public
//                 .requestMatchers("/api/admin/**").hasAnyRole("ADMIN", "RESTAURANT_OWNER") // protégé Admin//                 .requestMatchers("/api/users/**").authenticated()   // ex: profil utilisateur, protégé
//                 .anyRequest().authenticated()                        // tout le reste aussi protégé
//             )
//             .addFilterBefore(new JwtTockenValidator(), UsernamePasswordAuthenticationFilter.class)
//             .csrf(csrf -> csrf.disable())
//             .cors(cors -> cors.configurationSource(corsConfigurationSource));

//         return http.build();
//     }
// }
