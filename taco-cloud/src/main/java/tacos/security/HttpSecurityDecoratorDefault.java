package tacos.security;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;
import tacos.constant.ProfileConstants;

@Component
@Profile(ProfileConstants.DEFAULT)
public class HttpSecurityDecoratorDefault implements HttpSecurityDecorator {
    public HttpSecurity decorate(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeRequests()
                    .antMatchers("/logout").authenticated()
                    .antMatchers(HttpMethod.POST, "/data-api/ingredients").hasAuthority("SCOPE_writeIngredients")
                    .antMatchers(HttpMethod.DELETE, "/data-api/ingredients").hasAuthority("SCOPE_deleteIngredients")
                    .antMatchers("/design", "/orders", "/orders/current").hasRole("USER")
                    .antMatchers("/", "/register", "/login", "/images/*", "/api/tacos").permitAll()
                    .antMatchers("**").permitAll()
                .and()
                .csrf()
                    .disable()
                .oauth2ResourceServer(oauth2 -> oauth2.jwt());
    }
}
