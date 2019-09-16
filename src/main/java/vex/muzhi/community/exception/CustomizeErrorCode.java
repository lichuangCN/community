package vex.muzhi.community.exception;

/**
 * Author: lichuang
 * Date: Create in 18:06 2019/9/16
 * Description:
 */

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND("你找的问题不在了，换个问题找找看~");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
