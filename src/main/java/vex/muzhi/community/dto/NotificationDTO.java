package vex.muzhi.community.dto;

import lombok.Data;
import vex.muzhi.community.model.User;

/**
 * Author: lichuang
 * Date: Create in 10:35 2019/9/20
 * Description:
 */
@Data
public class NotificationDTO {

    // 通知id
    private Long id;
    // 通知创建时间
    private Long gmtCreate;
    // 通知状态：未读/已读
    private Integer status;
    // 通知标题
    private String notifyOuterTitle;
    // 通知类型：问题/评论
    private Integer notifyType;
    private String notifyTypeName;

    private Long notifyOuterId;

    // 发起通知的人
    private User notifier;

}
