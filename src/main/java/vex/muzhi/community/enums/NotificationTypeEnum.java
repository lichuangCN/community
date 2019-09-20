package vex.muzhi.community.enums;

/**
 * Author: lichuang
 * Date: Create in 9:29 2019/9/20
 * Description:
 */

public enum NotificationTypeEnum {
    REPLAY_QUESTION(1, "回复了问题"),
    REPLAY_COMMENT(2, "回复了评论"),
    ;

    private Integer type;
    private String name;

    NotificationTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static String nameOfType(int type) {
        for (NotificationTypeEnum typeEnum : NotificationTypeEnum.values()) {
            if (type == typeEnum.getType()) {
                return typeEnum.getName();
            }
        }
        return "";
    }
}
