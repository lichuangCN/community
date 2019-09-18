package vex.muzhi.community.exception;

/**
 * Author: lichuang
 * Date: Create in 16:17 2019/9/16
 * Description:
 */

public class CustomizeException extends RuntimeException {

    private static final long serialVersionUID = 8777951518066127145L;
    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
