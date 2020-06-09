package ace.account.cas.base.api.facade;

import ace.account.cas.base.api.service.AccountCasBaseService;
import ace.cas.base.define.model.dto.OAuth2Token;
import ace.fw.model.response.GenericResponseExt;
import org.springframework.validation.annotation.Validated;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/1/14 12:29
 * @description {@link AccountCasBaseService}接口包装
 */
@Validated
public interface AccountCasBaseFacadeService {
    /**
     * 获取OAuth2 token
     *
     * @param accountIdentityId
     * @return
     */
    GenericResponseExt<OAuth2Token> getOAuth2Token(String accountIdentityId);


}
