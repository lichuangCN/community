package vex.muzhi.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import vex.muzhi.community.model.Question;

/**
 * Author: lichuang
 * Date: Create in 21:00 2019/9/9
 * Description:
 */
@Mapper
public interface QuestionMapper {

    void insert(Question question);
}
