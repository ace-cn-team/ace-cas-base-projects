package ace.cas.base.define.constant;

public interface CasConstants {
    /**
     * feign客户端服务接口包路径
     */
    String FEIGN_CLIENT_SERVICE_PACKAGE = "ace.cas.base.api";
    /**
     * feign客户端服务接口封闭包路径
     */
    String FEIGN_CLIENT_FACADE_SERVICE_PACKAGE = "ace.cas.base.api.facade";
    /**
     * feign客户端名称
     */
    String FEIGN_CLIENT_NAME = "${ace.ms.service.api.ace-cas-base-api.name:ace-cas-base-api-web}";
    /**
     * 是否开启ace-cas-base-api的feign配置
     */
    String CONFIG_KEY_ACCOUNT_CAS_BASE_API_ENABLE = "ace.ms.service.ace-cas-base-api.enable";
    /**
     * 以下四项是cas项目的oauth2 配置参数类型
     */
    String CAS_OAUTH2_GRANT_TYPE_DEFAULT_VALUE = "password";
    String CAS_OAUTH2_PASSWORD_DEFAULT_VALUE = "password";
    String CAS_OAUTH2_CLIENT_ID_DEFAULT_VALUE = "ace-project-client";
    String CAS_OAUTH2_CLIENT_SECRET_DEFAULT_VALUE = "ace-project-client-secret";
}
