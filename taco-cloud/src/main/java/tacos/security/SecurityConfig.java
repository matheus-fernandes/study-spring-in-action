package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tacos.repository.UserRepository;

@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .authorizeRequests()
                    .antMatchers("/logout").authenticated()
                    .antMatchers("/design", "/orders", "/orders/current").hasRole("USER")
                    .antMatchers("/", "/register", "/login", "/images/*").permitAll()
                    .antMatchers("/h2-console").permitAll()
                    .antMatchers("**").denyAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/design")
                .and()
                .oauth2Login()
                    .loginPage("/login")
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
