package ro.ubb.SaloonApp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ro.ubb.SaloonApp.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .cors()
                .and()
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.POST, "/category/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/category/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/category/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/beauty-service/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/beauty-service/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/beauty-service/**").hasAuthority("ADMIN")
                        .requestMatchers("/**/swagger-resources", "/**/swagger-resources/**", "/**/swagger-ui",
                            "/**/swagger-ui/**", "/**/swagger-ui.html", "/**/swagger-ui.html/**", "/**/v3/api-docs/**",
                            "/**/auth/register", "/**/auth/login").permitAll() // TODO remove /** after testing phase
                        .anyRequest().authenticated()
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider).addFilterAfter(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
