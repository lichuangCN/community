package vex.muzhi.community.enums;

/**
 * Author: lichuang
 * Date: Create in 15:30 2019/9/21
 * Description:
 */

public enum FileResultEnum {
    SUCCESS(1, "图片上传成功"),
    FAIL(0, "图片上传失败"),
    ;
    private Integer code;
    private String message;

    FileResultEnum(Integer code, String message) {
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
