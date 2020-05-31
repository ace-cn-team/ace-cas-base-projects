package ace.account.cas.base.api.client.autoconfigure;

import ace.account.define.base.constant.AccountCasConstants;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/2/5 1:20
 * @description
 */
@AutoConfigureAfter(EnableFeignClientAutoConfigure.class)
@ComponentScan(basePackages = {
        AccountCasConstants.FEIGN_CLIENT_FACADE_SERVICE_PACKAGE
})
@Configuration
public class EnableFeignClientFacadeAutoConfigure {


}
