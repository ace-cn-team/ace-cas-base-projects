package ace.cas.base.api.client.autoconfigure;

import ace.cas.base.api.facade.OAuth2BaseServiceFacade;
import ace.cas.base.api.facade.impl.OAuth2BaseServiceFacadeImpl;
import ace.cas.base.api.service.OAuth2BaseService;
import ace.cas.base.define.constant.CasConstants;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/2/5 1:20
 * @description
 */
@ConditionalOnProperty(
        name = CasConstants.CONFIG_KEY_ACCOUNT_CAS_BASE_API_ENABLE,
        havingValue = "true",
        matchIfMissing = true
)
@ConditionalOnBean(annotation = {EnableFeignClients.class})
@EnableFeignClients(basePackages = {CasConstants.FEIGN_CLIENT_SERVICE_PACKAGE})
//@ComponentScan(basePackages = {CasConstants.FEIGN_CLIENT_FACADE_SERVICE_PACKAGE})
@Configuration
public class EnableFeignClientAutoConfigure {
    @Bean
    public OAuth2BaseServiceFacade oAuth2BaseServiceFacade(OAuth2BaseService oAuth2BaseService) {
        OAuth2BaseServiceFacadeImpl oAuth2BaseServiceFacade = new OAuth2BaseServiceFacadeImpl();
        oAuth2BaseServiceFacade.setOAuth2BaseService(oAuth2BaseService);
        return oAuth2BaseServiceFacade;
    }
}
