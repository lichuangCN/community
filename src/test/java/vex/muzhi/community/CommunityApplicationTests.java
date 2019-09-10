package vex.muzhi.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vex.muzhi.community.mapper.QuestionMapper;
import vex.muzhi.community.model.Question;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityApplicationTests {

    @Autowired
    private QuestionMapper questionMapper;

    @Test
    public void contextLoads() {
        List<Question> questionList = questionMapper.findQuestionList();
        System.out.println(questionList.size());
    }

}
