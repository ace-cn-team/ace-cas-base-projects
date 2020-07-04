package ace.cas.base.define.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2GetTokenRequest {
    @NotBlank
    private String grant_type;
    @NotBlank
    private String client_id;
    @NotBlank
    private String client_secret;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    /***********************    扩展参数   ****************************************/
    /**
     * 记录登录账号的相关参数,格式是json字符串，{@link ace.cas.base.define.model.bo.OAuth2Profile}
     */
    private String profile;
}
