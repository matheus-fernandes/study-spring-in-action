package taco.authorization;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoaderConfig {
    @Bean
    public ApplicationRunner dataLoader(UserRepository userRepository, PasswordEncoder encoder){
        return args -> {
            userRepository.save(new User("habuma", encoder.encode("password"), "ROLE_ADMIN"));
            userRepository.save(new User("tacochef", encoder.encode("password"), "ROLE_ADMIN"));
        };
    }
}
