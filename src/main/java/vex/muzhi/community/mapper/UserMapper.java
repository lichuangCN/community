package vex.muzhi.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import vex.muzhi.community.model.User;

/**
 * Author: lichuang
 * Date: Create in 11:09 2019/9/9
 * Description:
 */
@Mapper
public interface UserMapper {

    /**
     * 登录成功时插入用户信息
     * @param user
     */
    void insert(User user);

    /**
     * 根据token检索用户
     * @param token
     * @return
     */
    User findByToken(String token);
}
