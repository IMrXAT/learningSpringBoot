package com.springbootlearning.ch2;


import com.springbootlearning.ch2.users.UserAccount;
import com.springbootlearning.ch2.users.UserManagementRepository;
import com.springbootlearning.ch2.users.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    CommandLineRunner initUsers(UserManagementRepository repository){
        return args -> {
            repository.save(new UserAccount("user", "password", "ROLE_USER"));
            repository.save(new UserAccount("admin", "1111", "ROLE_ADMIN"));
        };
    }

    @Bean
    UserDetailsService userService(UserRepository repository){
        return username -> repository.findByUsername(username).asUser();
    }

    @Bean
    SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests() //
                .requestMatchers("/login").permitAll() //
                .requestMatchers("/", "/universal-search").authenticated() //
                .requestMatchers(HttpMethod.GET, "/api/**").authenticated() //
                .requestMatchers("/admin").hasRole("ADMIN") //
                .requestMatchers(HttpMethod.POST, "/new-video", "/api/**").hasRole("ADMIN") //
                .anyRequest().denyAll() //
                .and() //
                .formLogin() //
                .and() //
                .httpBasic();
        return http.build();
    }

    @Bean
    SecurityFilterChain configurationSecurity(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/", "/universal-search").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/new-video", "/api/**").hasRole("ADMIN")
                .anyRequest().denyAll()
                .and()
                .formLogin()
                .and()
                .httpBasic();
        return http.build();
    }

}
