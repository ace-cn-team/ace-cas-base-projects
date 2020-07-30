package ace.cas.base.define.model.request.facade;

import ace.cas.base.define.model.bo.OAuth2Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/7/2 22:32
 * @description 获取 token 的接口，自定义封装
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2GetTokenFacadeRequest {
    /**
     * 用户唯一Id
     */
    @NotBlank
    private String accountId;

    @NotNull
    OAuth2Profile profile;
}
