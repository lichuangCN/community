package vex.muzhi.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vex.muzhi.community.model.Question;

@Mapper
public interface QuestionExtMapper {

    /**
     * 阅读数增加
     *
     * @param record
     * @return
     */
    int increaseView(@Param("record") Question record);
}