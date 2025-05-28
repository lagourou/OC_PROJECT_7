package com.nnk.springboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Configuration class for Spring Security.
 * Sets up password encoding, authentication manager, and HTTP security filters.
 */
@Configuration
@Slf4j
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

        /**
         * Defines the password encoder bean using BCrypt.
         *
         * @return the password encoder
         */
        @Bean
        public PasswordEncoder passwordEncoder() {
                log.info("Initializing the encoded password");
                return new BCryptPasswordEncoder();
        }

        /**
         * Configures the authentication manager with user details and password encoder.
         *
         * @param http               the HttpSecurity object
         * @param encoder            the password encoder
         * @param userDetailsService the service to load user details
         * @return the configured authentication manager
         * @throws Exception in case of configuration error
         */
        @Bean
        public AuthenticationManager authenticationManager(
                        HttpSecurity http,
                        PasswordEncoder encoder,
                        UserDetailsService userDetailsService) throws Exception {

                log.info("Configuration of AuthenticationManager");

                AuthenticationManagerBuilder authManagerBuilder = http
                                .getSharedObject(AuthenticationManagerBuilder.class);
                authManagerBuilder.userDetailsService(userDetailsService)
                                .passwordEncoder(encoder);

                return authManagerBuilder.build();
        }

        /**
         * Configures the security filter chain with access rules and login/logout
         * settings.
         *
         * @param http           the HttpSecurity object
         * @param successHandler the custom login success handler
         * @return the configured security filter chain
         * @throws Exception in case of configuration error
         */
        @Bean
        public SecurityFilterChain securityFilterChain(
                        HttpSecurity http,
                        CustomLoginSuccessHandler successHandler) throws Exception {

                http
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/", "/app/login", "/css/**", "/user/add",
                                                                "/user/validate")
                                                .permitAll()
                                                .requestMatchers("/user/list", "/user/update/**", "/user/delete/**")
                                                .hasAuthority("ADMIN")
                                                .requestMatchers(
                                                                "/bidList/update/**", "/bidList/delete/**",
                                                                "/curvePoint/update/**", "/curvePoint/delete/**",
                                                                "/curvePoint/add/**",
                                                                "/trade/update/**", "/trade/delete/**",
                                                                "/rating/update/**", "/rating/delete/**",
                                                                "/ruleName/update/**", "/ruleName/delete/**",
                                                                "/ruleName/add/**")
                                                .hasAuthority("ADMIN")
                                                .requestMatchers("/bidList/**", "/curvePoint/**", "/trade/**",
                                                                "/rating/**", "/ruleName/**")
                                                .hasAnyAuthority("USER", "ADMIN")
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/app/login")
                                                .loginProcessingUrl("/app/login")
                                                .successHandler(successHandler)
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/app-logout")
                                                .logoutSuccessUrl("/app/login?logout")
                                                .permitAll())
                                .exceptionHandling(exception -> exception
                                                .accessDeniedPage("/403"));

                return http.build();
        }
}
