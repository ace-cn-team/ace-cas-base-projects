package ace.cas.base.define.util;

import org.apache.commons.lang3.StringUtils;

public final class AccountIdentityRedisUtils {
    private static final String REDIS_KEY = "ace:identity:${accountIdentityId}";

    public AccountIdentityRedisUtils() {
    }

    public static String createRedisKey(String accountIdentityId) {
        String[] varKeys = new String[]{"${accountIdentityId}"};
        String[] varValues = new String[]{accountIdentityId};
        return StringUtils.replaceEach("ace:identity:${accountIdentityId}", varKeys, varValues);
    }

    public static void main(String[] args) {
        System.out.println(StringUtils.replaceEach("ace:identity:${accountIdentityId}", new String[]{"${appId}"}, new String[]{"1"}));
    }
}
