package vex.muzhi.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vex.muzhi.community.mapper.QuestionMapper;
import vex.muzhi.community.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityApplicationTests {

    @Autowired
    private QuestionMapper questionMapper;

    @Test
    public void test() {
        Question record = new Question();
        record.setTitle("title");
        record.setDescription("description");
        record.setTag("tags");
        record.setCreator(1L);
        record.setGmtCreate(System.currentTimeMillis());
        record.setGmtModified(record.getGmtCreate());
        questionMapper.insertSelective(record);
    }
}
