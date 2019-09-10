package vex.muzhi.community.model;

import lombok.Data;

/**
 * Author: lichuang
 * Date: Create in 21:00 2019/9/9
 * Description: 提交的问题的实体类
 */

@Data
public class Question {

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

}
