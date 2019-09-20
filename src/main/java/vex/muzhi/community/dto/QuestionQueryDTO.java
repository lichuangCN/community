package vex.muzhi.community.dto;

import lombok.Data;

/**
 * Author: lichuang
 * Date: Create in 21:31 2019/9/20
 * Description:
 */

@Data
public class QuestionQueryDTO {

    private String search;
    private Integer offset;
    private Integer size;
}
