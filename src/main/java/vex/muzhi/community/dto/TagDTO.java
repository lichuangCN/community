package vex.muzhi.community.dto;

import lombok.Data;

import java.util.List;

/**
 * Author: lichuang
 * Date: Create in 18:49 2019/9/19
 * Description:
 */
@Data
public class TagDTO {

    /**
     * 大类
     */
    private String categoryName;

    private List<String> tags;
}
