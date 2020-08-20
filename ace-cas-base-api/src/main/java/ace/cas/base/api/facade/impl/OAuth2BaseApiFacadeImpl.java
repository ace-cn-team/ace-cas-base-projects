package ace.cas.base.api.facade.impl;

import ace.cas.base.api.facade.OAuth2BaseApiFacade;
import ace.cas.base.api.OAuth2BaseApi;
import ace.cas.base.define.constant.CasConstants;
import ace.cas.base.define.enums.CasBusinessErrorEnum;
import ace.cas.base.define.model.bo.OAuth2Profile;
import ace.cas.base.define.model.bo.OAuth2Token;
import ace.cas.base.define.model.bo.OAuth2TokenDto;
import ace.cas.base.define.model.request.OAuth2GetProfileRequest;
import ace.cas.base.define.model.request.OAuth2GetTokenRequest;
import ace.cas.base.define.model.request.OAuth2IntrospectRequest;
import ace.cas.base.define.model.request.OAuth2RevokeRequest;
import ace.cas.base.define.model.request.facade.OAuth2GetProfileFacadeRequest;
import ace.cas.base.define.model.request.facade.OAuth2GetTokenFacadeRequest;
import ace.cas.base.define.model.request.facade.OAuth2IntrospectFacadeRequest;
import ace.cas.base.define.model.request.facade.OAuth2RevokeFacadeRequest;
import ace.cas.base.define.model.response.OAuth2GetProfileResponse;
import ace.cas.base.define.model.response.OAuth2IntrospectResponse;
import ace.fw.enums.SystemCodeEnum;
import ace.fw.exception.BusinessException;
import ace.fw.json.JsonUtils;
import ace.fw.model.response.GenericResponseExt;
import ace.fw.util.GenericResponseExtUtils;
import feign.FeignException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.Base64;
import java.util.Objects;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/1/14 12:29
 * @description
 */
@Slf4j
public class OAuth2BaseApiFacadeImpl implements OAuth2BaseApiFacade {
    @Setter
    private OAuth2BaseApi oAuth2BaseApi;

    /**
     * 封装 {@link org.apereo.cas.support.oauth.web.endpoints.OAuth20AccessTokenEndpointController}
     *
     * @param request
     * @return
     */
    @Override
    public GenericResponseExt<OAuth2Token> getOAuth2Token(OAuth2GetTokenFacadeRequest request) {
        try {
            OAuth2Profile oAuth2Profile = request.getProfile();

            String oAuth2ProfileJsonString = JsonUtils.toJson(oAuth2Profile);

            OAuth2GetTokenRequest oauth2GetTokenRequest = OAuth2GetTokenRequest.builder()
                    .client_id(CasConstants.CAS_OAUTH2_CLIENT_ID_DEFAULT_VALUE)
                    .client_secret(CasConstants.CAS_OAUTH2_CLIENT_SECRET_DEFAULT_VALUE)
                    .grant_type(CasConstants.CAS_OAUTH2_GRANT_TYPE_DEFAULT_VALUE)
                    .password(CasConstants.CAS_OAUTH2_PASSWORD_DEFAULT_VALUE)
                    .username(request.getAccountId())
                    .profile(oAuth2ProfileJsonString)
                    .build();

            String body = oAuth2BaseApi.getOAuth2Token(oauth2GetTokenRequest);
            if (body.indexOf("error") > 0) {
                log.error("请求失败[OAuth2BaseServiceFacadeImpl][getOAuth2Token]," + body);
                return GenericResponseExtUtils.buildBySystemCodeEnum(SystemCodeEnum.ERROR_SYSTEM_EXCEPTION);
            }
            OAuth2TokenDto oAuth2TokenDto = JsonUtils.toObject(body, OAuth2TokenDto.class);
            OAuth2Token oAuth2Token = OAuth2Token.builder()
                    .accessToken(oAuth2TokenDto.getAccess_token())
                    .expiresIn(oAuth2TokenDto.getExpires_in())
                    .refreshToken(oAuth2TokenDto.getRefresh_token())
                    .scope(oAuth2TokenDto.getScope())
                    .tokenType(oAuth2TokenDto.getToken_type())
                    .build();
            return GenericResponseExtUtils.buildSuccessWithData(oAuth2Token);
        } catch (BusinessException ex) {
            return GenericResponseExt.<OAuth2Token>builder()
                    .code(ex.getCode())
                    .message(ex.getMessage())
                    .build();
        } catch (Exception ex) {
            log.error("[CasBaseFacadeServiceImpl][getOAuth2Token]", ex);
            return GenericResponseExtUtils.buildBySystemCodeEnum(SystemCodeEnum.ERROR_SYSTEM_EXCEPTION);
        }
    }

    /**
     * 封装 {@link org.apereo.cas.support.oauth.web.endpoints.OAuth20UserProfileEndpointController}
     *
     * @param request
     * @return
     */
    @Override
    public GenericResponseExt<OAuth2Profile> getProfile(@Valid OAuth2GetProfileFacadeRequest request) {
        try {
            OAuth2GetProfileRequest oauth2GetTokenRequest = OAuth2GetProfileRequest.builder()
                    .access_token(request.getAccessToken())
                    .build();

            String body = oAuth2BaseApi.getProfile(oauth2GetTokenRequest);
            if (body.indexOf("error") > 0) {
                log.error("请求失败[OAuth2BaseServiceFacadeImpl][getProfile]," + body);
                return GenericResponseExtUtils.buildBySystemCodeEnum(SystemCodeEnum.ERROR_SYSTEM_EXCEPTION);
            }
            OAuth2GetProfileResponse oAuth2GetProfileResponse = JsonUtils.toObject(body, OAuth2GetProfileResponse.class);
            if (Objects.isNull(oAuth2GetProfileResponse)) {
                return GenericResponseExtUtils.buildSuccessWithData(null);
            }
            OAuth2Profile oauth2Profile = oAuth2GetProfileResponse.getProfile();

            return GenericResponseExtUtils.buildSuccessWithData(oauth2Profile);
        } catch (BusinessException ex) {
            return GenericResponseExt.<OAuth2Profile>builder()
                    .code(ex.getCode())
                    .message(ex.getMessage())
                    .build();
        } catch (FeignException.FeignClientException.Unauthorized ex) {
            return GenericResponseExt.<OAuth2Profile>builder()
                    .code(CasBusinessErrorEnum.NO_PERMISSION.getCode())
                    .message(CasBusinessErrorEnum.NO_PERMISSION.getCode())
                    .data(null)
                    .build();
        } catch (Exception ex) {
            log.error("[CasBaseFacadeServiceImpl][getProfile]", ex);
            return GenericResponseExtUtils.buildBySystemCodeEnum(SystemCodeEnum.ERROR_SYSTEM_EXCEPTION);
        }
    }

    /**
     * 封装 {@link org.apereo.cas.support.oauth.web.endpoints.OAuth20RevocationEndpointController}
     *
     * @param request
     * @return
     */
    @Override
    public GenericResponseExt<Boolean> revoke(@Valid OAuth2RevokeFacadeRequest request) {
        try {
            OAuth2RevokeRequest oauth2RevokeRequest = OAuth2RevokeRequest.builder()
                    .client_id(CasConstants.CAS_OAUTH2_CLIENT_ID_DEFAULT_VALUE)
                    .client_secret(CasConstants.CAS_OAUTH2_CLIENT_SECRET_DEFAULT_VALUE)
                    .token(request.getToken())
                    .build();

            String body = oAuth2BaseApi.revoke(oauth2RevokeRequest);
            if (body.indexOf("error") > 0) {
                log.error("请求失败[OAuth2BaseServiceFacadeImpl][revoke]," + body);
                return GenericResponseExtUtils.buildBySystemCodeEnum(SystemCodeEnum.ERROR_SYSTEM_EXCEPTION);
            }
            return GenericResponseExtUtils.buildSuccessWithData(true);
        } catch (BusinessException ex) {
            return GenericResponseExt.<Boolean>builder()
                    .code(ex.getCode())
                    .message(ex.getMessage())
                    .build();
        } catch (Exception ex) {
            log.error("[CasBaseFacadeServiceImpl][revoke]", ex);
            return GenericResponseExtUtils.buildBySystemCodeEnum(SystemCodeEnum.ERROR_SYSTEM_EXCEPTION);
        }
    }

    /**
     * 封装 {@link org.apereo.cas.support.oauth.web.endpoints.OAuth20IntrospectionEndpointController}
     *
     * @param request
     * @return
     */
    @Override
    public GenericResponseExt<OAuth2IntrospectResponse> introspect(@Valid OAuth2IntrospectFacadeRequest request) {
        try {
            OAuth2IntrospectRequest oauth2RevokeRequest = OAuth2IntrospectRequest.builder()
                    .token(request.getAccessToken())
                    .build();
            String basicAuthBase64 = Base64.getEncoder().encodeToString(
                    String.format("%s:%s",
                            CasConstants.CAS_OAUTH2_CLIENT_ID_DEFAULT_VALUE,
                            CasConstants.CAS_OAUTH2_CLIENT_SECRET_DEFAULT_VALUE
                    ).getBytes("utf-8")
            );
            String basicAuthHeader = String.format("Basic %s", basicAuthBase64);
            String body = oAuth2BaseApi.introspect(oauth2RevokeRequest, basicAuthHeader);
            if (body.indexOf("error") > 0) {
                log.error("请求失败[OAuth2BaseServiceFacadeImpl][introspect]," + body);
                return GenericResponseExtUtils.buildBySystemCodeEnum(SystemCodeEnum.ERROR_SYSTEM_EXCEPTION);
            }
            OAuth2IntrospectResponse oAuth2IntrospectResponse = JsonUtils.toObject(body, OAuth2IntrospectResponse.class);
            return GenericResponseExtUtils.buildSuccessWithData(oAuth2IntrospectResponse);
        } catch (BusinessException ex) {
            return GenericResponseExt.<OAuth2IntrospectResponse>builder()
                    .code(ex.getCode())
                    .message(ex.getMessage())
                    .build();
        } catch (Exception ex) {
            log.error("[CasBaseFacadeServiceImpl][introspect]", ex);
            return GenericResponseExtUtils.buildBySystemCodeEnum(SystemCodeEnum.ERROR_SYSTEM_EXCEPTION);
        }
    }
}
