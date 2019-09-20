package vex.muzhi.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vex.muzhi.community.enums.NotificationTypeEnum;
import vex.muzhi.community.enums.NotificationStatusEnum;
import vex.muzhi.community.mapper.NotificationMapper;
import vex.muzhi.community.model.Notification;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityApplicationTests {

    @Autowired
    private NotificationMapper notificationMapper;

    @Test
    public void test() {
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setReceiverId(1L);
        notification.setNotifierId(1L);
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setNotifyType(NotificationTypeEnum.REPLAY_QUESTION.getType());
        notification.setNotifyOuterId(14L);
        notification.setNotifyOuterTitle("你好我是测试你好我是测试你好我是测试你好我是测试");
        notificationMapper.insert(notification);
    }
}
