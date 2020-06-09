package ace.account.cas.base.api.service;

import ace.cas.base.define.constant.CasConstants;
import ace.cas.base.define.model.request.GetOAuth2TokenRequest;
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
 * @description
 */
@FeignClient(
        name = CasConstants.FEIGN_CLIENT_NAME,
        contextId = "accountCasBaseService",
        path = "/" + AccountCasBaseService.MODULE_RESTFUL_NAME
)
@Validated
public interface AccountCasBaseService {
    String MODULE_RESTFUL_NAME = "account-cas-base";

    @ApiOperation(value = "获取OAuth2 token")
    @RequestMapping(path = "/get-oauth2-token", method = RequestMethod.POST)
    String getOAuth2Token(@Valid @RequestBody GetOAuth2TokenRequest request);


}
