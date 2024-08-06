package security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Creating a SecurityFilterChain object to define security configurations
        http
            .authorizeRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll() // Allow public access to these URLs
                .requestMatchers("/welcome").hasRole("USER") // Require USER role for /welcome
                .anyRequest().permitAll() // Allow all other requests
            )
            .formLogin(formLogin -> formLogin
                .loginPage("/login") // Specify the custom login page
                .loginProcessingUrl("/login") // URL to submit username and password
                .defaultSuccessUrl("/welcome", true) // Redirect to /welcome upon successful login
                .failureUrl("/login?error=true") // Redirect to /login with error parameter on failure
                .permitAll() // Allow access to the login page
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout=true") // Redirect to /login with logout parameter upon logout
                .permitAll() // Allow access to the logout URL
            );
        return http.build(); // Build and return the SecurityFilterChain object
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Creating an in-memory user details manager for demonstration purposes
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // Adding a user with username "harsh", password "1234", and role "USER"
        manager.createUser(User.withDefaultPasswordEncoder().username("harsh").password("hpandya@301").roles("USER").build());
        return manager; // Return the in-memory user details manager
    }
}
