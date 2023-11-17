package com.example.aula3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.aula3.security.JwtAuthFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
        private final JwtAuthFilter jwtAuthfilter;
        private final AuthenticationProvider authenticationProvider;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                /*
                 * http
                 * .csrf()
                 * .disable()
                 * .authorizeHttpRequests()
                 * .requestMatchers(AntPathRequestMatcher.antMatcher("/api/auth"),
                 * AntPathRequestMatcher.antMatcher("/h2-console/"),
                 * AntPathRequestMatcher.antMatcher("/swagger-ui/"),
                 * AntPathRequestMatcher.antMatcher("/v3/api-docs/"),
                 * AntPathRequestMatcher.antMatcher("/api-docs/"))
                 * .permitAll()
                 * .anyRequest().authenticated()
                 * .and()
                 * .sessionManagement()
                 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 * .and()
                 * .authenticationProvider(authenticationProvider)
                 * .addFilterBefore(jwtAuthfilter, UsernamePasswordAuthenticationFilter.class);
                 * return http.build();
                 */

                http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/auth/**"),
                                                                AntPathRequestMatcher.antMatcher("/h2-console/**"),
                                                                AntPathRequestMatcher.antMatcher("/swagger-ui/**"),
                                                                AntPathRequestMatcher.antMatcher("/v3/api-docs/**"),
                                                                AntPathRequestMatcher.antMatcher("/api-docs/**"))
                                                .permitAll()
                                                .anyRequest().authenticated())
                                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthfilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();

        }
}
