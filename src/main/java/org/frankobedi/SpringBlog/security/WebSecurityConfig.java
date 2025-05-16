package org.frankobedi.SpringBlog.security;

import org.frankobedi.SpringBlog.util.constants.Privillages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {
    private static final String[] WHITELIST = {
        "/",
        "/login",
        "/register",
        "/uprofile",
        "/db-console/**",
        "/css/**",
        "/fonts/**",
        "/images/**",
        "/js/**"
    };

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
             // Permit all users to access login and logout pages
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(WHITELIST).permitAll()
                .requestMatchers("/profile/**").authenticated() // authenticate anything after 'profile' only
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/editor/**").hasAnyRole("EDITOR", "ADMIN") // editor panel accessible by editor and admin
                .requestMatchers("admin/**").hasAuthority(Privillages.ACCESS_ADMIN_PANEL.getPrivillage())
            )
            // Enable form-based authentication
            .formLogin(form -> form
                .loginPage("/login") // Custom login page URL
                .loginProcessingUrl("/login") // URL to submit the username and password
                .defaultSuccessUrl("/", true) // Redirect to homepage on successful login
                .failureUrl("/login?error=true") // Redirect to login on failure
                .usernameParameter("email") // Override default username parameter
                .passwordParameter("password") // Override default password parameter
            )
            // Enable logout functionality
            .logout(logout -> logout
                .logoutUrl("/logout") // Custom logout URL
                .logoutSuccessUrl("/login") // Redirect to home on successful logout
            )
            // Use HTTP Basic authentication
            .httpBasic(withDefaults());

        // TODO: Remove these after when we starting using real DB
        http.csrf(csrf -> csrf.disable());
        http.headers(header -> header.frameOptions(frame -> frame.disable()));
 
        return http.build();
    }
}