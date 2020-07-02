package ace.cas.base.api.client.autoconfigure;

import ace.cas.base.define.constant.CasConstants;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
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
        CasConstants.FEIGN_CLIENT_FACADE_SERVICE_PACKAGE
})
@Configuration
public class EnableFeignClientFacadeAutoConfigure {


}
