package vex.muzhi.community.mapper;

import org.apache.ibatis.annotations.Param;
import vex.muzhi.community.dto.QuestionQueryDTO;
import vex.muzhi.community.model.Question;

import java.util.List;

// @Mapper
public interface QuestionExtMapper {

    /**
     * 阅读数增加
     *
     * @param record
     * @return
     */
    int increaseView(@Param("record") Question record);

    /**
     * 回复数增加
     *
     * @param record
     * @return
     */
    int increaseCommentCount(@Param("record") Question record);

    /**
     * 检索相关问题(依据标签检索)
     *
     * @param record
     * @return
     */
    List<Question> selectRelated(@Param("record") Question record);

    /**
     * 检索问题总数(搜索条件)
     *
     * @param questionQueryDTO
     * @return
     */
    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    /**
     * 检索问题列表(搜索条件)
     *
     * @param questionQueryDTO
     * @return
     */
    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}