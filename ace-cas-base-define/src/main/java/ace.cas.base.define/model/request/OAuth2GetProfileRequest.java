package ace.cas.base.define.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2GetProfileRequest {
    /**
     * 根据access token 获取 profile
     */
    private String access_token;
}
