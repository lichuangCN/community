package vex.muzhi.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vex.muzhi.community.dto.NotificationDTO;
import vex.muzhi.community.enums.NotificationTypeEnum;
import vex.muzhi.community.model.User;
import vex.muzhi.community.service.NotificationService;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: lichuang
 * Date: Create in 15:51 2019/9/20
 * Description:
 */

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String notification(@PathVariable(name = "id") Long id,
                               HttpServletRequest request) {

        // 如果当前用户未登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        // 修改通知状态为已读
        NotificationDTO notificationDTO = notificationService.read(id, user);
        if (NotificationTypeEnum.REPLAY_COMMENT.getType() == notificationDTO.getNotifyType()
                || NotificationTypeEnum.REPLAY_QUESTION.getType() == notificationDTO.getNotifyType()) {
            return "redirect:/question/" + notificationDTO.getNotifyOuterId();
        } else {
            return "redirect:/";
        }
    }
}
