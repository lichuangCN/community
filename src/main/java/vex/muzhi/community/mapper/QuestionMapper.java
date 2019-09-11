package vex.muzhi.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
     *
     * @param question
     */
    void insert(Question question);

    /**
     * 问题列表(分页)
     *
     * @param offset
     * @param size
     * @return
     */
    List<Question> findQuestionList(@Param("offset") Integer offset, @Param("size") Integer size);

    /**
     * 问题总数
     *
     * @return
     */
    Integer count();
}
