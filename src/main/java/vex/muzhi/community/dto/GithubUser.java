package vex.muzhi.community.dto;

import lombok.Data;

/**
 * Author: lichuang
 * Date: Create in 15:15 2019/9/7
 * Description: Github授权登录需要获取的部分用户信息
 */

@Data
public class GithubUser {

    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
