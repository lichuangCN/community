package vex.muzhi.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
     * @return
     */
    public List<QuestionDTO> getQuestionList() {
        // 问题列表
        List<Question> questionList = questionMapper.findQuestionList();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
           User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            // 复制question的值到questionDTO中
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
