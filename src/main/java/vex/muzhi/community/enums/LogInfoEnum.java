package vex.muzhi.community.enums;

/**
 * Author: lichuang
 * Date: Create in 19:33 2019/9/21
 * Description:
 */

public enum LogInfoEnum {
   GITHUB_USER_ERROR("callback()获取github用户信息错误,{}"),
    ;

    private String message;

    LogInfoEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
