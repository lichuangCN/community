package vex.muzhi.community.enums;

/**
 * Author: lichuang
 * Date: Create in 9:29 2019/9/20
 * Description:
 */

public enum NotificationStatusEnum {
    UNREAD(0),
    READ(1),
    ;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    NotificationStatusEnum(Integer status) {
        this.status = status;
    }
}
