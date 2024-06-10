package com.example.carsharing.configuration;

import com.example.carsharing.security.UserDetailsServiceImpl;
import com.example.carsharing.security.security_util.RolesPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(RolesPaths.USER_LIST).hasRole(RolesPaths.USER)
                        .requestMatchers(RolesPaths.ADMIN_LIST).hasRole(RolesPaths.ADMIN)
                        .requestMatchers(RolesPaths.MANAGER_LIST).hasRole(RolesPaths.MANAGER)
                        .requestMatchers(RolesPaths.GUEST_LIST).hasRole(RolesPaths.GUEST)
                        .anyRequest().authenticated())
                .httpBasic(withDefaults())
                .logout(logoutPage -> logoutPage.logoutSuccessUrl("/"))
                .formLogin(withDefaults());
        return http.build();
    }
}
