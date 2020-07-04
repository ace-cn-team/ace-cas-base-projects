package ace.cas.base.authentication.autoconfigure;

import ace.cas.base.authentication.OAuth2HttpJsonPrincipalAuthenticationHandler;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.principal.PrincipalFactoryUtils;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/7/4 10:55
 * @description
 */
@Configurable
public class OAuth2HttpJsonPrincipalAuthenticationHandlerAutoConfigure implements AuthenticationEventExecutionPlanConfigurer {

    public OAuth2HttpJsonPrincipalAuthenticationHandler oAuth2HttpJsonPrincipalAuthenticationHandler() {
        OAuth2HttpJsonPrincipalAuthenticationHandler oAuth2HttpJsonPrincipalAuthenticationHandler =
                new OAuth2HttpJsonPrincipalAuthenticationHandler("OAuth2HttpJsonPrincipalAuthenticationHandler",
                        null,
                        PrincipalFactoryUtils.newPrincipalFactory(),
                        Integer.MIN_VALUE);
        return oAuth2HttpJsonPrincipalAuthenticationHandler;
    }

    @Override
    public void configureAuthenticationExecutionPlan(AuthenticationEventExecutionPlan plan) {
        plan.registerAuthenticationHandler(oAuth2HttpJsonPrincipalAuthenticationHandler());
    }
}
