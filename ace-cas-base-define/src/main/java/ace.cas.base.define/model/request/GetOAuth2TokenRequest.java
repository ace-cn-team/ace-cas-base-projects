package ace.cas.base.define.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetOAuth2TokenRequest {
    private String grantType;
    private String clientId;
    private String clientSecret;
    private String username;
    private String password;
}
