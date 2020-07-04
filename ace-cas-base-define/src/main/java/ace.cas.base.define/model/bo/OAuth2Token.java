package ace.cas.base.define.model.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/7/2 22:32
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2Token {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Integer expiresIn;
    private String scope;
}
