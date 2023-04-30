package tacos.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

public interface HttpSecurityDecorator {
    HttpSecurity decorate(HttpSecurity httpSecurity) throws Exception;
}
