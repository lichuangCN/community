package vex.muzhi.community.enums;

/**
 * Author: lichuang
 * Date: Create in 15:44 2019/9/18
 * Description:
 */

public enum ResultCode {

    SUCCESS(200, "请求成功"),
    ;
    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
