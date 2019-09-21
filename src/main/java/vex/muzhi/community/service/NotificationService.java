package vex.muzhi.community.service;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vex.muzhi.community.dto.NotificationDTO;
import vex.muzhi.community.dto.PaginationDTO;
import vex.muzhi.community.enums.CustomizeErrorCode;
import vex.muzhi.community.enums.NotificationStatusEnum;
import vex.muzhi.community.enums.NotificationTypeEnum;
import vex.muzhi.community.exception.CustomizeException;
import vex.muzhi.community.mapper.NotificationMapper;
import vex.muzhi.community.mapper.UserMapper;
import vex.muzhi.community.model.Notification;
import vex.muzhi.community.model.NotificationExample;
import vex.muzhi.community.model.User;
import vex.muzhi.community.model.UserExample;
import vex.muzhi.community.util.PaginationDTOUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: lichuang
 * Date: Create in 10:34 2019/9/20
 * Description:
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户的通知列表
     *
     * @param userId 用户id
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO notificationListByUserId(Long userId, Integer page, Integer size) {

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverIdEqualTo(userId);
        // 通知总数
        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);
        // 总页数
        Integer totalPage = (totalCount % size == 0) ? (totalCount / size) : (totalCount / size + 1);
        PaginationDTO<List<NotificationDTO>> paginationDTO = new PaginationDTO<>();
        // 设置分页信息除去列表数据外的其他参数
        PaginationDTOUtil.setPagination(paginationDTO, totalPage, page);

        // 计算查询的起始位置
        Integer offset = size * (paginationDTO.getPage() - 1);
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverIdEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notificationList = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        // 封装通知列表需要的数据
        List<NotificationDTO> notificationDTOList = new ArrayList<>();

        // 没有通知
        if (notificationList.size() == 0) {
            return paginationDTO;
        }
        // 回复通知的用户id集合
        Set<Long> userIds = notificationList.stream()
                .map(notification -> notification.getNotifierId())
                .collect(Collectors.toSet());
        ArrayList<Long> ids = new ArrayList<>(userIds);

        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(ids);
        // 回复通知的用户id集合
        List<User> userList = userMapper.selectByExample(userExample);

        Map<Long, User> userMap = userList.stream()
                .collect(Collectors.toMap(u -> userId, u -> u));

        for (Notification notification : notificationList) {
            NotificationDTO notificationDTO = new NotificationDTO();

            BeanUtils.copyProperties(notification, notificationDTO);
            // 通知类型名称
            notificationDTO.setNotifyTypeName(NotificationTypeEnum.nameOfType(notification.getNotifyType()));
            notificationDTO.setNotifier(userMapper.selectByPrimaryKey(notification.getNotifierId()));
            notificationDTOList.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOList);
        return paginationDTO;
    }

    /**
     * 检索未读通知数
     *
     * @param userId 用户id
     */
    public Long unreadCount(Long userId) {
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverIdEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(example);
    }

    /**
     * 设置通知状态为已读
     *
     * @param id
     * @param user
     * @return
     */
    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        //通知不存在
        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        // 查看的通知不是当前登录用户的通知
        if (!Objects.equals(notification.getReceiverId(), user.getId())) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        // 更新为已读
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        // 通知类型名称
        notificationDTO.setNotifyTypeName(NotificationTypeEnum.nameOfType(notification.getNotifyType()));
        notificationDTO.setNotifier(userMapper.selectByPrimaryKey(notification.getNotifierId()));
        return notificationDTO;
    }
}
