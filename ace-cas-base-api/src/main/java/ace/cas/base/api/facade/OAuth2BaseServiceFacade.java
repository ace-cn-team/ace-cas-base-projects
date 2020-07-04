package ace.cas.base.api.facade;

import ace.cas.base.api.service.OAuth2BaseService;
import ace.cas.base.define.model.bo.OAuth2Profile;
import ace.cas.base.define.model.bo.OAuth2Token;
import ace.cas.base.define.model.request.OAuth2IntrospectRequest;
import ace.cas.base.define.model.request.facade.OAuth2GetProfileFacadeRequest;
import ace.cas.base.define.model.request.facade.OAuth2GetTokenFacadeRequest;
import ace.cas.base.define.model.request.OAuth2RevokeRequest;
import ace.cas.base.define.model.request.facade.OAuth2IntrospectFacadeRequest;
import ace.cas.base.define.model.request.facade.OAuth2RevokeFacadeRequest;
import ace.cas.base.define.model.response.OAuth2IntrospectResponse;
import ace.fw.model.response.GenericResponseExt;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/1/14 12:29
 * @description {@link OAuth2BaseService}接口包装
 */
@Validated
public interface OAuth2BaseServiceFacade {
    @ApiOperation(value = "获取 oauth2 token")
    GenericResponseExt<OAuth2Token> getOAuth2Token(@Valid OAuth2GetTokenFacadeRequest request);

    @ApiOperation(value = "获取 profile 根据 access token")
    GenericResponseExt<OAuth2Profile> getProfile(@Valid OAuth2GetProfileFacadeRequest request);

    @ApiOperation(value = "删除 oauth2 token 根据 access token")
    GenericResponseExt<Boolean> revoke(@Valid OAuth2RevokeFacadeRequest request);

    @ApiOperation(value = "验证 access token 是否有效")
    GenericResponseExt<OAuth2IntrospectResponse> introspect(@Valid OAuth2IntrospectFacadeRequest request);
}
