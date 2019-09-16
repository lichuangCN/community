package vex.muzhi.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vex.muzhi.community.mapper.UserMapper;
import vex.muzhi.community.model.User;
import vex.muzhi.community.model.UserExample;

import java.util.List;

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
     *
     * @param user
     */
    public void createOrUpdate(User user) {

        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) {
            // 如果用户之前不存在，插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertSelective(user);
        } else {
            // 若之前存在，更新
            User dbUser = users.get(0);
            dbUser.setName(user.getName());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setBio(user.getBio());
            dbUser.setToken(user.getToken());
            dbUser.setGmtModified(System.currentTimeMillis());
            userMapper.updateByPrimaryKeySelective(dbUser);
        }
    }
}
