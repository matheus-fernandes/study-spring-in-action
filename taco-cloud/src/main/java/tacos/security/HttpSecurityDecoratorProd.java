package tacos.security;

import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;
import tacos.constant.ProfileConstants;

@Component
@Profile(ProfileConstants.PROD)
public class HttpSecurityDecoratorProd implements HttpSecurityDecorator {
    public HttpSecurity decorate(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.authorizeRequests()
                .antMatchers("/logout").authenticated()
                .antMatchers("/design", "/orders", "/orders/current", "/api/tacos").hasRole("USER")
                .antMatchers("/", "/register", "/login", "/images/*").permitAll()
                .antMatchers("/h2-console").permitAll()
                .antMatchers("**").denyAll()
                .and();
    }
}
