package vex.muzhi.community.dto;

import lombok.Data;

/**
 * Author: lichuang
 * Date: Create in 21:33 2019/9/6
 * Description: 用于Github第三方授权
 */
@Data
public class AccessTokenDTO {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
