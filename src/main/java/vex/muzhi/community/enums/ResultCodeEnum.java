package vex.muzhi.community.enums;

/**
 * Author: lichuang
 * Date: Create in 15:44 2019/9/18
 * Description:
 */

public enum ResultCodeEnum {

    SUCCESS(200, "请求成功"),
    REQUEST_ERROR(400, "请求错了，换个姿势试试~"),
    SERVICE_ERROR(500, "服务器努力工作中~"),
    ;
    private Integer code;
    private String message;

    ResultCodeEnum(Integer code, String message) {
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
