package vex.muzhi.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vex.muzhi.community.dto.PaginationDTO;
import vex.muzhi.community.mapper.UserMapper;
import vex.muzhi.community.service.QuestionService;

/**
 * Author: lichuang
 * Date: Create in 18:52 2019/9/6
 * Description:
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "8") Integer size) {

        // 问题内容以及问题发起人信息列表
        PaginationDTO pagination = questionService.getQuestionList(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
