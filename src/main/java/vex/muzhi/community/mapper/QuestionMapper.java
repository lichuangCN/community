package vex.muzhi.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import vex.muzhi.community.model.Question;

import java.util.List;

/**
 * Author: lichuang
 * Date: Create in 21:00 2019/9/9
 * Description:
 */
@Mapper
public interface QuestionMapper {

    /**
     * 新增问题
     * @param question
     */
    void insert(Question question);

    /**
     * 获得问题列表
     * @return
     */
    List<Question> findQuestionList();
}
