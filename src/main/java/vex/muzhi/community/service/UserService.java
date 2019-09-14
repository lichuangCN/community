package vex.muzhi.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vex.muzhi.community.mapper.UserMapper;
import vex.muzhi.community.model.User;

/**
 * Author: lichuang
 * Date: Create in 17:23 2019/9/14
 * Description:
 */
@Service("userService")
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户使用Github账户登录时，创建新用户或更新旧用户信息
     * @param user
     */
    public void createOrUpdate(User user) {

        User dbUser = userMapper.findByAccountId(user.getAccountId());

        if (dbUser == null) {
            // 如果用户之前不存在，插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            // 若之前存在，更新
            user.setGmtModified(System.currentTimeMillis());
            userMapper.update(user);
        }
    }
}
