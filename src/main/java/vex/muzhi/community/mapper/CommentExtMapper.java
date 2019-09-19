package vex.muzhi.community.mapper;

import org.apache.ibatis.annotations.Param;
import vex.muzhi.community.model.Comment;

// @Mapper
public interface CommentExtMapper {

    /**
     * 回复数增加
     *
     * @param record
     * @return
     */
    int increaseCommentCount(@Param("record") Comment record);
}