package vex.muzhi.community.util;

import vex.muzhi.community.dto.PaginationDTO;

/**
 * Author: lichuang
 * Date: Create in 9:13 2019/9/21
 * Description:
 */

public class PaginationDTOUtil {

    /**
     * 设置分页信息除去列表数据外的其他参数
     *
     * @param paginationDTO 向分页信息传递的对象
     * @param totalPage     总页数
     * @param page          页面传递的页码值
     */
    public static void setPagination(PaginationDTO paginationDTO, Integer totalPage, Integer page) {

        // 页面传入非法页码的处理
        if (page > totalPage) {
            page = totalPage;
        }
        if (page <= 1) {
            page = 1;
        }

        // 设置总页码
        paginationDTO.setTotalPage(totalPage);
        // 设置当前页码
        paginationDTO.setPage(page);

        // 添加当前页
        paginationDTO.getPages().add(page);
        // 设置页面显示的页码列表
        for (int i = 1; i <= 3; i++) {
            // 补全左侧页码
            if (page - i > 0) {
                // 头插，插入第一位
                paginationDTO.getPages().add(0, page - i);
            }
            // 补全右侧页码
            if (page + i <= totalPage) {
                // 尾插
                paginationDTO.getPages().add(page + i);
            }
        }
        // 当前页码为第一页的时候，不展示向前图标
        boolean showPrevious = (page == 1) ? false : true;
        paginationDTO.setShowPrevious(showPrevious);
        // 当前页码为最后一页的时候，不展示向后图标
        boolean showNext = (page == totalPage) ? false : true;
        paginationDTO.setShowNext(showNext);

        // 页面显示的页码不包含第一页的时候，展示跳到第一页的图标
        boolean showFirstPage = (paginationDTO.getPages().contains(1)) ? false : true;
        paginationDTO.setShowFirstPage(showFirstPage);
        // 页面显示的页码不包含最后一页的时候，展示跳到最后一页图标
        boolean showEndPage = (paginationDTO.getPages().contains(totalPage)) ? false : true;
        paginationDTO.setShowEndPage(showEndPage);
    }
}
