package vex.muzhi.community.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vex.muzhi.community.dto.PaginationDTO;
import vex.muzhi.community.service.QuestionService;

/**
 * Author: lichuang
 * Date: Create in 18:52 2019/9/6
 * Description:
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "7") Integer size,
                        @RequestParam(name = "search", required = false) String search) {

        // 问题内容以及问题发起人信息列表
        PaginationDTO pagination = questionService.getQuestionList(search, page, size);
        model.addAttribute("pagination", pagination);

        if (StringUtils.isNoneBlank(search)){
            model.addAttribute("search", search);
        } else {
            model.addAttribute("search", "");
        }
        return "index";
    }
}
