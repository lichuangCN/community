package vex.muzhi.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: lichuang
 * Date: Create in 19:57 2019/9/11
 * Description: 问题列表
 */
@Data
public class PaginationDTO<T> {

    private T data;
    // 是否有向前按钮
    private boolean showPrevious;
    // 第一页按钮
    private boolean showFirstPage;
    // 下一页按钮
    private boolean showNext;
    // 尾页按钮
    private boolean showEndPage;
    // 当前页码
    private Integer page;
    // 当前页显示的页码列表
    private List<Integer> pages = new ArrayList<>();
    // 总页码
    private Integer totalPage;

}
