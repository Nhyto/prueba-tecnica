package com.antoniomorenoarribas.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	// Cargar las credenciales desde application.properties
    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.role}")
    private String adminRole;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .headers().frameOptions().disable() //Permite ver la consola H2
            .and()
            .authorizeRequests()
            .antMatchers("/h2-console/**").permitAll()  // Permite el acceso sin autenticación a la consola H2
            .antMatchers("/v1/prices/**").authenticated()  // Protege los endpoints de la API
            .anyRequest().authenticated()
            .and()
            .httpBasic();  // Autenticación básica

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Encriptación de contraseñas
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername(adminUsername)
            .password(passwordEncoder.encode(adminPassword))
            .roles(adminRole)
            .build();

        return new InMemoryUserDetailsManager(user);
    }
}
