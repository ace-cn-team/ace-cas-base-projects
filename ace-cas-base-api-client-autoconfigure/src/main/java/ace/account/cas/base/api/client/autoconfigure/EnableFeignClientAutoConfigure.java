package ace.account.cas.base.api.client.autoconfigure;

import ace.account.define.base.constant.AccountCasConstants;
import ace.account.define.base.constant.AccountIdentityConstants;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/2/5 1:20
 * @description
 */
@ImportAutoConfiguration({EnableFeignClientFacadeAutoConfigure.class})
@ConditionalOnProperty(
        name = AccountCasConstants.CONFIG_KEY_ACCOUNT_CAS_BASE_API_ENABLE,
        havingValue = "true",
        matchIfMissing = true
)
@ConditionalOnBean(annotation = {EnableFeignClients.class})
@EnableFeignClients(basePackages = {AccountCasConstants.FEIGN_CLIENT_SERVICE_PACKAGE})
@Configuration
public class EnableFeignClientAutoConfigure {


}
