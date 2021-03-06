package ace.cas.base.define.model.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class OAuth2Profile implements Serializable {
    /**
     * 账号ID
     */
    private String accountId;
    /**
     * 账号所属应用ID
     */
    private String appId;
}
