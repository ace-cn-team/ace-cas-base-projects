package ace.cas.base.api.facade;

import ace.cas.base.api.OAuth2BaseApi;
import ace.cas.base.define.enums.CasBusinessErrorEnum;
import ace.cas.base.define.model.bo.OAuth2Profile;
import ace.cas.base.define.model.bo.OAuth2Token;
import ace.cas.base.define.model.request.facade.OAuth2GetProfileFacadeRequest;
import ace.cas.base.define.model.request.facade.OAuth2GetTokenFacadeRequest;
import ace.cas.base.define.model.request.facade.OAuth2IntrospectFacadeRequest;
import ace.cas.base.define.model.request.facade.OAuth2RevokeFacadeRequest;
import ace.cas.base.define.model.response.OAuth2IntrospectResponse;
import ace.fw.model.response.GenericResponseExt;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/1/14 12:29
 * @description {@link OAuth2BaseApi}接口包装
 */
@Validated
public interface OAuth2BaseApiFacade {
    /**
     * 封装 {@link org.apereo.cas.support.oauth.web.endpoints.OAuth20AccessTokenEndpointController}
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "获取 oauth2 token")
    GenericResponseExt<OAuth2Token> getOAuth2Token(@Valid OAuth2GetTokenFacadeRequest request);

    /**
     * 封装 {@link org.apereo.cas.support.oauth.web.endpoints.OAuth20UserProfileEndpointController}
     *
     * @param request
     * @return 成功 {@link ace.fw.enums.SystemCodeEnum#SUCCESS}
     * 没有权限或已经过期 {@link CasBusinessErrorEnum#NO_PERMISSION}
     */
    @ApiOperation(value = "获取 profile 根据 access token")
    GenericResponseExt<OAuth2Profile> getProfile(@Valid OAuth2GetProfileFacadeRequest request);

    /**
     * 封装 {@link org.apereo.cas.support.oauth.web.endpoints.OAuth20RevocationEndpointController}
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "删除 oauth2 token 根据 access token")
    GenericResponseExt<Boolean> revoke(@Valid OAuth2RevokeFacadeRequest request);

    /**
     * 封装 {@link org.apereo.cas.support.oauth.web.endpoints.OAuth20IntrospectionEndpointController}
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "验证 access token 是否有效")
    GenericResponseExt<OAuth2IntrospectResponse> introspect(@Valid OAuth2IntrospectFacadeRequest request);
}
