package vex.muzhi.community.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vex.muzhi.community.dto.PaginationDTO;
import vex.muzhi.community.dto.QuestionDTO;
import vex.muzhi.community.dto.QuestionQueryDTO;
import vex.muzhi.community.exception.CustomizeErrorCode;
import vex.muzhi.community.exception.CustomizeException;
import vex.muzhi.community.mapper.QuestionExtMapper;
import vex.muzhi.community.mapper.QuestionMapper;
import vex.muzhi.community.mapper.UserMapper;
import vex.muzhi.community.model.Question;
import vex.muzhi.community.model.QuestionExample;
import vex.muzhi.community.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取问题内容以及问题发起人信息列表
     *
     * @param search
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO getQuestionList(String search, Integer page, Integer size) {

        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        // 是否是搜索状态
        if (StringUtils.isNotBlank(search)) {
            String[] conditions = StringUtils.split(search, " ");
            String regexpTag = Arrays.stream(conditions).collect(Collectors.joining("|"));
            // 检索条件
            questionQueryDTO.setSearch(regexpTag);
        }

        // 问题总数
        Integer totalCount = questionExtMapper.countBySearch(questionQueryDTO);
        // 总页数
        Integer totalPage = (totalCount % size == 0) ? (totalCount / size) : (totalCount / size + 1);
        PaginationDTO<List<QuestionDTO>> paginationDTO = new PaginationDTO<>();

        // 传入非法参数的处理
        if (page > totalPage) {
            page = totalPage;
        }
        if (page <= 1) {
            page = 1;
        }

        paginationDTO.setPagination(totalPage, page);
        // 查询的起始位置
        Integer offset = size * (page - 1);
        // 设置分页查询条件
        questionQueryDTO.setOffset(offset);
        questionQueryDTO.setSize(size);
        // 问题列表
        List<Question> questionList = questionExtMapper.selectBySearch(questionQueryDTO);
        // 问题和问题发起人的列表
        List<QuestionDTO> questionDTOList = questionList.stream().map(question -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(userMapper.selectByPrimaryKey(question.getCreator()));
            return questionDTO;
        }).collect(Collectors.toList());

        // 将问题列表封装到与页面传输数据的对象中
        paginationDTO.setData(questionDTOList);
        return paginationDTO;
    }

    /**
     * 某个用户的问题列表
     *
     * @param userId 用户id
     * @param page
     * @param size
     */
    public PaginationDTO questionListByUserId(Long userId, Integer page, Integer size) {

        // 用户的问题总数
        QuestionExample questionExample = new QuestionExample();
        // 添加条件：用户(问题发布人)id
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int) questionMapper.countByExample(questionExample);
        // 总页数
        Integer totalPage = (totalCount % size == 0) ? (totalCount / size) : (totalCount / size + 1);
        PaginationDTO<List<QuestionDTO>> paginationDTO = new PaginationDTO<>();

        // 传入非法参数的处理
        if (page >= totalPage) {
            page = totalPage;
        }
        if (page <= 1) {
            page = 1;
        }

        paginationDTO.setPagination(totalPage, page);

        // 查询的起始位置
        Integer offset = size * (page - 1);
        // 某位用户的问题列表
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            // 复制question的值到questionDTO中
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        // 将问题列表封装到与页面传输数据的对象中
        paginationDTO.setData(questionDTOList);

        return paginationDTO;
    }

    /**
     * 获取单个问题信息
     *
     * @param id 问题id
     * @return
     */
    public QuestionDTO getQuestionById(Long id) {
        // 问题信息
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        // 检索问题的发布人
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    /**
     * 创建或更新问题
     *
     * @param question
     */
    public void createOrUpdate(Question question) {

        if (question.getId() == null) {
            // 问题不存在，创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setCommentCount(0);
            question.setLikeCount(0);
            questionMapper.insert(question);
        } else {
            // 问题存在，更新
            question.setGmtModified(System.currentTimeMillis());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(question, questionExample);
            if (updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    /**
     * 阅读数累加
     *
     * @param id
     */
    public void increaseView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.increaseView(question);
    }

    /**
     * 检索相关问题
     *
     * @param questionDTO
     * @return
     */
    public List<QuestionDTO> selectRelated(QuestionDTO questionDTO) {

        // 问题没有标签
        if (StringUtils.isBlank(questionDTO.getTag())) {
            return new ArrayList<>();
        }

        String[] tags = StringUtils.split(questionDTO.getTag(), ",");
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setTag(regexpTag);
        List<Question> questions = questionExtMapper.selectRelated(question);
        // 对问题和问题发布人进行数据封装
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO dto = new QuestionDTO();
            BeanUtils.copyProperties(q, dto);
            // 问题发布人
            dto.setUser(userMapper.selectByPrimaryKey(questionDTO.getCreator()));
            return dto;
        }).collect(Collectors.toList());
        return questionDTOS;
    }
}
