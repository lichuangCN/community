package vex.muzhi.community.dto;

import lombok.Data;
import vex.muzhi.community.model.User;

/**
 * Author: lichuang
 * Date: Create in 10:17 2019/9/18
 * Description:
 */
@Data
public class CommentDTO {

    private Long id;

    private Long parentId;

    private Integer type;

    private String content;

    private Long commentator;

    private Long gmtCreate;

    private Long gmtModified;

    private Integer likeCount;

    private User user;
}
