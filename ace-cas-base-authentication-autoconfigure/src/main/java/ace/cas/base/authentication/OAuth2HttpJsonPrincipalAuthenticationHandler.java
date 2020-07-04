package ace.cas.base.authentication;

import ace.cas.base.define.model.bo.OAuth2Profile;
import ace.fw.json.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.credential.UsernamePasswordCredential;
import org.apereo.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.apereo.cas.authentication.principal.Principal;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.authentication.principal.PrincipalFactoryUtils;
import org.apereo.cas.services.ServicesManager;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.GeneralSecurityException;
import java.util.*;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/7/4 10:40
 * @description
 */
public class OAuth2HttpJsonPrincipalAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {

    public OAuth2HttpJsonPrincipalAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    protected AuthenticationHandlerExecutionResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential, String originalPassword) throws GeneralSecurityException, PreventedException {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Optional<OAuth2Profile> oAuth2ProfileOptional = this.resolveOAuth2Profile(httpServletRequest);
        Map<String, List<Object>> attributes = new HashMap<>(1);
        if (oAuth2ProfileOptional.isPresent()) {
            attributes.put("profile", Arrays.asList(oAuth2ProfileOptional.get()));
        }
        Principal principal = this.principalFactory.createPrincipal(credential.getUsername(), attributes);
        return this.createHandlerResult(credential, principal);
    }

    @Override
    public boolean supports(final Class<? extends Credential> clazz) {
        return UsernamePasswordCredential.class.isAssignableFrom(clazz);
    }

    private Optional<OAuth2Profile> resolveOAuth2Profile(HttpServletRequest httpServletRequest) {
        String oAuth2ProfileJsonString = httpServletRequest.getParameter("profile");
        if (StringUtils.isBlank(oAuth2ProfileJsonString)) {
            return Optional.empty();
        }
        OAuth2Profile oAuth2Profile = JsonUtils.toObject(oAuth2ProfileJsonString, OAuth2Profile.class);

        return Optional.of(oAuth2Profile);
    }

}
