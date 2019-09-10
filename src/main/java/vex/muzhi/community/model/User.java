package vex.muzhi.community.model;

import lombok.Data;

/**
 * Author: lichuang
 * Date: Create in 11:10 2019/9/9
 * Description: 用户类
 */

@Data
public class User {

    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private String bio;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
