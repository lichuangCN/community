package vex.muzhi.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vex.muzhi.community.dto.PaginationDTO;
import vex.muzhi.community.dto.QuestionDTO;
import vex.muzhi.community.mapper.QuestionMapper;
import vex.muzhi.community.mapper.UserMapper;
import vex.muzhi.community.model.Question;
import vex.muzhi.community.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: lichuang
 * Date: Create in 20:50 2019/9/10
 * Description:
 */
@Service("questionService")
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取问题内容以及问题发起人信息列表
     *
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO getQuestionList(Integer page, Integer size) {

        // 问题总数
        Integer totalCount = questionMapper.count();
        // 总页数
        Integer totalPage = (totalCount % size == 0) ? (totalCount / size) : (totalCount / size + 1);
        PaginationDTO paginationDTO = new PaginationDTO();

        // 传入非法参数的处理
        if (page > totalPage) {
            page = totalPage;
        }
        if (page <= 1) {
            page = 1;
        }

        paginationDTO.setPagination(totalPage, page);
        // 查询的起始位置
        Integer offset = size * (page - 1);
        // 问题列表
        List<Question> questionList = questionMapper.findQuestionList(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            // 复制question的值到questionDTO中
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        // 将问题列表封装到与页面传输数据的对象中
        paginationDTO.setQuestionList(questionDTOList);

        return paginationDTO;
    }

    /**
     * 某个用户的问题列表
     *
     * @param userId 用户id
     * @param page
     * @param size
     */
    public PaginationDTO getQuestionListByUserId(Integer userId, Integer page, Integer size) {

        // 用户的问题总数
        Integer totalCount = questionMapper.countByUserId(userId);
        // 总页数
        Integer totalPage = (totalCount % size == 0) ? (totalCount / size) : (totalCount / size + 1);
        PaginationDTO paginationDTO = new PaginationDTO();

        // 传入非法参数的处理
        if (page >= totalPage) {
            page = totalPage;
        }
        if (page <= 1) {
            page = 1;
        }

        paginationDTO.setPagination(totalPage, page);

        // 查询的起始位置
        Integer offset = size * (page - 1);
        // 某位用户的问题列表
        List<Question> questionList = questionMapper.findQuestionListByUserId(userId, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            // 复制question的值到questionDTO中
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        // 将问题列表封装到与页面传输数据的对象中
        paginationDTO.setQuestionList(questionDTOList);

        return paginationDTO;
    }

    /**
     * 获取单个问题信息
     *
     * @param id 问题id
     * @return
     */
    public QuestionDTO getQuestionById(Integer id) {
        // 问题信息
        Question question = questionMapper.findById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        // 检索问题的发布人
        questionDTO.setUser(userMapper.findById(question.getCreator()));
        return questionDTO;
    }

    /**
     * 创建或更新问题
     *
     * @param question
     */
    public void createOrUpdate(Question question) {

        if (question.getId() == null) {
            // 问题不存在，创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        } else {
            // 问题存在，更新
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }

}
