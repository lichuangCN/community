package vex.muzhi.community.dto;

import lombok.Data;

/**
 * Author: lichuang
 * Date: Create in 9:30 2019/9/17
 * Description:
 */
@Data
public class CommentCreateDTO {

    /** 被回复的问题/评论id*/
    private Long parentId;

    /** 判断被回复的类型：问题：1,评论：2*/
    private Integer type;

    /** 回复的内容*/
    private String content;
}
