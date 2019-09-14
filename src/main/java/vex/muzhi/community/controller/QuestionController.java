package vex.muzhi.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vex.muzhi.community.dto.QuestionDTO;
import vex.muzhi.community.mapper.QuestionMapper;
import vex.muzhi.community.service.QuestionService;

/**
 * Author: lichuang
 * Date: Create in 16:21 2019/9/14
 * Description:
 */

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model) {
        QuestionDTO questionDTO = questionService.getQuestionById(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
