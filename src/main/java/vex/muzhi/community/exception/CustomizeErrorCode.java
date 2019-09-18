package vex.muzhi.community.exception;

/**
 * Author: lichuang
 * Date: Create in 18:06 2019/9/16
 * Description:
 */

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001, "你找的问题不在了，换个问题找找看~"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复~"),
    NO_LOGIN(2003,"未登录无法评论,请先登录后评论"),
    SYSTEM_ERROR(2004,"服务正忙，请稍后重试"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不在了，换一个试试"),
    COMMENT_IS_EMPTY(2007,"评论内容为空，请输入内容"),
    ;

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
