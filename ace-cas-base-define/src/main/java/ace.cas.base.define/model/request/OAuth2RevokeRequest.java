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
public class OAuth2RevokeRequest {
    private String client_id;
    private String client_secret;
    /**
     * 根据 access token 或者 refresh token 删除 token
     */
    @NotBlank
    private String token;
}
