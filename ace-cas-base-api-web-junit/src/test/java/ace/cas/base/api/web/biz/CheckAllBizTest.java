package ace.cas.base.api.web.biz;

import ace.cas.base.api.OAuth2BaseApi;
import ace.cas.base.api.web.application.CasJUnitBaseApplication;
import ace.cas.base.api.facade.OAuth2BaseApiFacade;
import ace.cas.base.define.model.bo.OAuth2Profile;
import ace.cas.base.define.model.bo.OAuth2Token;
import ace.cas.base.define.model.request.facade.OAuth2GetProfileFacadeRequest;
import ace.cas.base.define.model.request.facade.OAuth2GetTokenFacadeRequest;
import ace.cas.base.define.model.request.facade.OAuth2IntrospectFacadeRequest;
import ace.cas.base.define.model.request.facade.OAuth2RevokeFacadeRequest;
import ace.cas.base.define.model.response.OAuth2IntrospectResponse;
import ace.fw.exception.BusinessException;
import ace.fw.json.JsonUtils;
import ace.fw.model.response.GenericResponseExt;
import com.fasterxml.uuid.Generators;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/6/28 14:32
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CasJUnitBaseApplication.class)
public class CheckAllBizTest {

    @Autowired
    private OAuth2BaseApiFacade oAuth2BaseApiFacade;
    @Autowired
    private OAuth2BaseApi oAuth2BaseApi;

    @Test
    public void testBaseInsertBiz() {
        String accountId = Generators.timeBasedGenerator().generate().toString();
        GenericResponseExt<OAuth2Token> getOAuth2TokenResult = oAuth2BaseApiFacade.getOAuth2Token(OAuth2GetTokenFacadeRequest.builder()
                .accountId(accountId)
                .build());

        OAuth2Token oAuth2Token = getOAuth2TokenResult.check();

        System.out.println("token : " + JsonUtils.toJson(oAuth2Token));

        GenericResponseExt<OAuth2Profile> oAuth2ProfileResult = oAuth2BaseApiFacade.getProfile(OAuth2GetProfileFacadeRequest.builder()
                .accessToken(oAuth2Token.getAccessToken())
                .build());

        OAuth2Profile oAuth2Profile = oAuth2ProfileResult.check();
        if (StringUtils.isBlank(oAuth2Profile.getAccountId())) {
            throw new BusinessException("没有account id");
        }
        System.out.println("profile : " + JsonUtils.toJson(oAuth2Profile));

        OAuth2IntrospectResponse oAuth2IntrospectResponse = oAuth2BaseApiFacade.introspect(OAuth2IntrospectFacadeRequest.builder()
                .accessToken(oAuth2Token.getAccessToken())
                .build())
                .check();

        System.out.println("oAuth2Introspect : " + JsonUtils.toJson(oAuth2IntrospectResponse));

        if (Boolean.FALSE.equals(oAuth2IntrospectResponse.getActive())) {
            throw new BusinessException("token 不应该过期");
        }

        oAuth2BaseApiFacade.revoke(OAuth2RevokeFacadeRequest.builder()
                .token(oAuth2Token.getAccessToken())
                .build())
                .check();

        OAuth2IntrospectResponse oAuth2IntrospectResponse2 = oAuth2BaseApiFacade.introspect(OAuth2IntrospectFacadeRequest.builder()
                .accessToken(oAuth2Token.getAccessToken())
                .build())
                .check();

        System.out.println("oAuth2IntrospectResponse2 : " + JsonUtils.toJson(oAuth2IntrospectResponse2));

        if (Boolean.TRUE.equals(oAuth2IntrospectResponse2.getActive())) {
            throw new BusinessException("token 没有过期");
        }
    }

}
