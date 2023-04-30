package tacos.security;

import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;
import tacos.constant.ProfileConstants;

@Component
@Profile(ProfileConstants.DEFAULT)
public class AuthorizeRequestsDecoratorDefault implements AuthorizeRequestsDecorator{
    public ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry decorate(
            ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry){

        return registry
                .antMatchers("/logout").authenticated()
                .antMatchers("/design", "/orders", "/orders/current").hasRole("USER")
                .antMatchers("/", "/register", "/login", "/images/*", "/api/tacos").permitAll()
                .antMatchers("**").permitAll();
    }
}
