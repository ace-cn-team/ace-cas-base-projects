package ace.cas.base.api.client.autoconfigure;

import ace.cas.base.define.constant.CasConstants;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/2/5 1:20
 * @description
 */
@ImportAutoConfiguration({EnableFeignClientFacadeAutoConfigure.class})
@ConditionalOnProperty(
        name = CasConstants.CONFIG_KEY_ACCOUNT_CAS_BASE_API_ENABLE,
        havingValue = "true",
        matchIfMissing = true
)
@ConditionalOnBean(annotation = {EnableFeignClients.class})
@EnableFeignClients(basePackages = {CasConstants.FEIGN_CLIENT_SERVICE_PACKAGE})
@Configuration
public class EnableFeignClientAutoConfigure {


}
