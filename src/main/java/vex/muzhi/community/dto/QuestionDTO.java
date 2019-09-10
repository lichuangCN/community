package vex.muzhi.community.dto;

import lombok.Data;
import vex.muzhi.community.model.User;

/**
 * Author: lichuang
 * Date: Create in 20:48 2019/9/10
 * Description: 问题数据传输模型
 */

@Data
public class QuestionDTO {

    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;

}
