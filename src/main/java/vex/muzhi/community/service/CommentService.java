package vex.muzhi.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vex.muzhi.community.dto.CommentDTO;
import vex.muzhi.community.enums.CommentTypeEnum;
import vex.muzhi.community.exception.CustomizeErrorCode;
import vex.muzhi.community.exception.CustomizeException;
import vex.muzhi.community.mapper.*;
import vex.muzhi.community.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author: lichuang
 * Date: Create in 11:32 2019/9/17
 * Description:
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentExtMapper commentExtMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加评论/回复
     *
     * @param comment
     */
    @Transactional
    public void addComment(Comment comment) {

        // 初始点赞数
        comment.setLikeCount(0);
        // 初始评论数
        comment.setCommentCount(0);

        // 未选中被回复的问题或评论
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        // 被回复的问题/评论类型不存在
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            // 回复评论
            // 被回复的评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                // 被回复的评论不存在
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
            dbComment.setCommentCount(1);
            commentExtMapper.increaseCommentCount(dbComment);
        } else {
            // 回复问题
            Question dbQuestion = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (dbQuestion == null) {
                // 被回复的问题不存在
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            dbQuestion.setCommentCount(1);
            questionExtMapper.increaseCommentCount(dbQuestion);
        }
    }

    /**
     * 某个问题/评论的所有回复
     *
     * @param id   问题id/评论Id
     * @param type 问题/评论类型
     * @return
     */
    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();
        // 问题id和属于回复问题的评论
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        // 根据评论时间排序，最新评论最前
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        // 问题没有评论
        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        // 去重的评论人的id集合
        Set<Long> commentators = comments.stream()
                .map(comment -> comment.getCommentator())
                .collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        // 评论人id转为评论人集合<id,user>
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors
                .toMap(user -> user.getId(), user -> user));
        // 评论和评论人信息
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }
}
