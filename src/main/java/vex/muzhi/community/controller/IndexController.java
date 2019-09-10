package vex.muzhi.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vex.muzhi.community.dto.QuestionDTO;
import vex.muzhi.community.mapper.QuestionMapper;
import vex.muzhi.community.mapper.UserMapper;
import vex.muzhi.community.model.Question;
import vex.muzhi.community.model.User;
import vex.muzhi.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public String index(HttpServletRequest request,
                        Model model) {
        // 从请求中的cookie中获取带有token的cookie, 判断此用户之前是否登陆过
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    // 此用户曾经登陆成功
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        // 将此用户信息存放在session中
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        // 问题内容以及问题发起人信息列表
        List<QuestionDTO> questionList = questionService.getQuestionList();
        model.addAttribute("questions",questionList);
        return "index";
    }
}
