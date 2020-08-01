package ace.cas.base.define.enums;

import ace.fw.enums.BaseEnum;
import lombok.Getter;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/3/17 15:10
 * @description Integer[] CAS = new Integer[]{140000, 149999};
 */
public enum CasBusinessErrorEnum implements BaseEnum<String> {
    NO_PERMISSION("140001", "没有权限"),
    ;
    @Getter
    private String code;
    @Getter
    private String desc;

    CasBusinessErrorEnum(String code, String desc) {

        this.code = code;
        this.desc = desc;
    }
}
