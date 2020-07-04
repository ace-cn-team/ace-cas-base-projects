package ace.cas.base.define.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/7/2 22:32
 * @description
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2IntrospectResponse {
    /**
     * 是否有效
     */
    private Boolean active;
    private String sub;
    private String scope;
    private Integer iat;
    private Integer exp;
    private String realmName;
    private String uniqueSecurityName;
    /**
     * token 类型
     */
    private String tokenType;
    private String aud;
    private String iss;
    private String client_id;
    private String grant_type;
}
