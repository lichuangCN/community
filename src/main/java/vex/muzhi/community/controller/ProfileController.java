package vex.muzhi.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vex.muzhi.community.dto.PaginationDTO;
import vex.muzhi.community.model.User;
import vex.muzhi.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: lichuang
 * Date: Create in 18:09 2019/9/12
 * Description:
 */

@Controller("profileController")
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    /**
     * 个人信息
     *
     * @param action 查看个人信息的行为
     * @param model
     * @return
     */
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "8") Integer size) {

        // 如果当前用户未登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        if (action.equals("questions")) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的问题");
        } else if (action.equals("replies")) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        PaginationDTO paginationDTO = questionService.getQuestionListByUserId(user.getId(), page, size);
        // PaginationDTO paginationDTO = new PaginationDTO();
        model.addAttribute("pagination", paginationDTO);
        return "profile";
    }
}
