package vex.muzhi.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
     *
     * @param user
     */
    void insert(User user);

    /**
     * 根据token检索用户
     *
     * @param token
     * @return
     */
    User findByToken(String token);

    /**
     * 根据id检索用户
     *
     * @param creator
     * @return
     */
    User findById(Integer creator);

    /**
     * 根据accountId检索用户
     *
     * @param accountId
     * @return
     */
    User findByAccountId(@Param("accountId") String accountId);

    /**
     * 更新信息
     *
     * @param user
     */
    void update(User user);
}
