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

    public void setPagination(Integer totalPage, Integer page) {

        // 总页码
        this.totalPage = totalPage;
        // 当前页码
        this.page = page;

        // 添加当前页
        pages.add(page);
        // 设置页面显示的页码列表
        for (int i = 1; i <= 3; i++) {
            // 补全左侧页码
            if (page - i > 0) {
                // 头插，插入第一位
                pages.add(0, page - i);
            }
            // 补全右侧页码
            if (page + i <= totalPage) {
                // 尾插
                pages.add(page + i);
            }
        }
        // 当前页码为第一页的时候，不展示向前图标
        showPrevious = (page == 1) ? false : true;
        // 当前页码为最后一页的时候，不展示向后图标
        showNext = (page == totalPage) ? false : true;

        // 页面显示的页码不包含第一页的时候，展示跳到第一页的图标
        showFirstPage = (pages.contains(1)) ? false : true;
        // 页面显示的页码不包含最后一页的时候，展示跳到最后一页图标
        showEndPage = (pages.contains(totalPage)) ? false : true;

    }
}
