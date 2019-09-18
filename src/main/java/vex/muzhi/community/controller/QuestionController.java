package vex.muzhi.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vex.muzhi.community.dto.CommentCreateDTO;
import vex.muzhi.community.dto.CommentDTO;
import vex.muzhi.community.dto.QuestionDTO;
import vex.muzhi.community.service.CommentService;
import vex.muzhi.community.service.QuestionService;

import java.util.List;

/**
 * Author: lichuang
 * Date: Create in 16:21 2019/9/14
 * Description:
 */

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model) {
        // 问题详情
        QuestionDTO questionDTO = questionService.getQuestionById(id);
        // 问题评论列表
        List<CommentDTO> comments = commentService.getCommentsByQuestionId(id);
        // 累加阅读数功能
        questionService.increaseView(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);
        return "question";
    }
}
