package taco.authorization;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Data
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "taco-auth.client")
public class DefaultClientProps {
    private String clientId;
    private String secret;
    private String uri;
    private Collection<String> scopes;
}
