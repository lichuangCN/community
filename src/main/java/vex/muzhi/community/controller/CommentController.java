package vex.muzhi.community.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vex.muzhi.community.dto.CommentCreateDTO;
import vex.muzhi.community.dto.CommentDTO;
import vex.muzhi.community.dto.ResultDTO;
import vex.muzhi.community.enums.CommentTypeEnum;
import vex.muzhi.community.enums.CustomizeErrorCode;
import vex.muzhi.community.exception.CustomizeException;
import vex.muzhi.community.model.Comment;
import vex.muzhi.community.model.User;
import vex.muzhi.community.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Author: lichuang
 * Date: Create in 9:21 2019/9/17
 * Description:
 */

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        // 登录验证
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        // 评论为空
        if (commentCreateDTO == null
                || StringUtils.isBlank(commentCreateDTO.getContent())) {
            throw new CustomizeException(CustomizeErrorCode.COMMENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(user.getId());
        commentService.addComment(comment);
        return ResultDTO.okOf();
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id) {
        List<CommentDTO> commentDTOList = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOList);
    }
}
