package ace.cas.base.define.model.response;

import ace.cas.base.define.model.bo.OAuth2Profile;
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
public class OAuth2GetProfileResponse {
    /**
     * 账号 profile
     */
    private OAuth2Profile profile;
}
