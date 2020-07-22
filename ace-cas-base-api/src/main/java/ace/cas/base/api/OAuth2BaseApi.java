package ace.cas.base.api;

import ace.cas.base.define.constant.CasConstants;
import ace.cas.base.define.model.request.OAuth2GetProfileRequest;
import ace.cas.base.define.model.request.OAuth2GetTokenRequest;
import ace.cas.base.define.model.request.OAuth2IntrospectRequest;
import ace.cas.base.define.model.request.OAuth2RevokeRequest;
import ace.fw.feign.config.MultipartSupportConfig;
import feign.form.spring.SpringFormEncoder;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/1/14 12:29
 * @description 原生 oauth2 接口
 */
@FeignClient(
        name = CasConstants.FEIGN_CLIENT_NAME,
        contextId = "oauth2BaseService",
        path = "/",
        configuration = {MultipartSupportConfig.class}
)
@Validated
public interface OAuth2BaseApi {
    @ApiOperation(value = "获取 oauth2 token")
    @RequestMapping(path = "/oauth2.0/token", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, method = RequestMethod.POST)
    String getOAuth2Token(@Valid OAuth2GetTokenRequest request);

    @ApiOperation(value = "获取 profile 根据 access token")
    @RequestMapping(path = "/oauth2.0/profile", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, method = RequestMethod.POST)
    String getProfile(@Valid OAuth2GetProfileRequest request);

    @ApiOperation(value = "删除 oauth2 token 根据 access token")
    @RequestMapping(path = "/oauth2.0/revoke",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, method = RequestMethod.POST)
    String revoke(@Valid OAuth2RevokeRequest request);

    @ApiOperation(value = "验证 access token 是否有效")
    @RequestMapping(path = "/oauth2.0/introspect", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},method = RequestMethod.POST)
    String introspect(@Valid OAuth2IntrospectRequest request, @NotBlank @RequestHeader(value = "Authorization", required = true) String basicAuth);


}
