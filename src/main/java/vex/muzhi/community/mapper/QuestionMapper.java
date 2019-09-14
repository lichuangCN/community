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
    List<Question> findQuestionList(@Param("offset") Integer offset,
                                    @Param("size") Integer size);

    /**
     * 所有问题总数
     *
     * @return
     */
    Integer count();

    /**
     * 某位用户问题总数
     *
     * @param userId
     * @return
     */
    Integer countByUserId(@Param("userId") Integer userId);

    /**
     * 某位用户的发布的问题列表
     *
     * @param userId
     * @param offset
     * @param size
     * @return
     */
    List<Question> findQuestionListByUserId(@Param("userId") Integer userId,
                                            @Param("offset") Integer offset,
                                            @Param("size") Integer size);

    /**
     * 查询指定的问题信息
     *
     * @param id 问题id
     * @return
     */
    Question findById(@Param("id") Integer id);

    /**
     * 更新信息
     * @param question
     */
    void update(Question question);
}
