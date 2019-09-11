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
        if (page <= 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page, size);
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
}
