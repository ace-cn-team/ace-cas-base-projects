package ace.account.cas.base.api.facade.impl;

import ace.account.cas.base.api.facade.AccountCasBaseFacadeService;
import ace.account.cas.base.api.service.AccountCasBaseService;
import ace.cas.base.define.constant.CasConstants;
import ace.cas.base.define.model.dto.OAuth2Token;
import ace.cas.base.define.model.request.GetOAuth2TokenRequest;
import ace.fw.json.JsonUtils;
import ace.fw.model.response.GenericResponseExt;
import ace.fw.util.GenericResponseExtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/1/14 12:29
 * @description
 */
@Service
@Slf4j
public class AccountCasBaseFacadeServiceImpl implements AccountCasBaseFacadeService {

    @Autowired
    private AccountCasBaseService accountCasBaseService;

    @Override
    public GenericResponseExt<OAuth2Token> getOAuth2Token(String accountIdentityId) {
        GetOAuth2TokenRequest request = GetOAuth2TokenRequest.builder()
                .clientId(CasConstants.CAS_OAUTH2_CLIENT_ID_DEFAULT_VALUE)
                .clientSecret(CasConstants.CAS_OAUTH2_CLIENT_SECRET_DEFAULT_VALUE)
                .grantType(CasConstants.CAS_OAUTH2_GRANT_TYPE_DEFAULT_VALUE)
                .password(CasConstants.CAS_OAUTH2_PASSWORD_DEFAULT_VALUE)
                .username(accountIdentityId)
                .build();
        String body = accountCasBaseService.getOAuth2Token(request);
        if (body.indexOf("error") > 0) {
            log.error("请求失败[AccountCasBaseFacadeService][getOAuth2Token]," + body);
            return GenericResponseExtUtils.buildFailureWithData(null);
        }
        OAuth2Token oAuth2Token = JsonUtils.toObject(body, OAuth2Token.class);
        return GenericResponseExtUtils.buildSuccessWithData(oAuth2Token);
    }
}
