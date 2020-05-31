package ace.account.cas.base.api.facade;

import ace.account.cas.base.api.service.AccountCasBaseService;
import ace.account.define.base.model.request.GetOAuth2TokenRequest;
import ace.account.define.base.model.response.GetOAuth2TokenResponse;
import ace.fw.model.response.GenericResponseExt;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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
    GenericResponseExt<GetOAuth2TokenResponse> getOAuth2Token(String accountIdentityId);


}
