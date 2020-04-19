package com.example.demo.service;

import com.example.demo.dto.PaginationDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 将question对象和User对象绑定到一起生成QuestionDTO对象然后放到PaginationDTO
 */
@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 将question对象和User对象绑定到一起生成QuestionDTO对象然后放到PaginationDTO并返回
     *
     * @param page 页数
     * @param size 页面大小
     * @return List<PaginationDTO>
     */
    public PaginationDTO list(Integer page, Integer size) {
        Integer totalPage = (int) Math.ceil((double) questionMapper.count() / (double) size);
        if (page < 1) page = 1;
        if (page > totalPage) page = totalPage;

        Integer offset = (page - 1) * size;
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();

        for (Question question : questions) {
            User user = userMapper.findByID(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOList(questionDTOS);
        paginationDTO.setPagination(totalPage, page, size);
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        Integer totalPage = (int) Math.ceil((double) questionMapper.countByUserId(userId) / (double) size);
        if (page < 1) page = 1;
        if (page > totalPage) page = totalPage;

        Integer offset = (page - 1) * size;
        List<Question> questions = questionMapper.listByUserId(userId,offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();

        for (Question question : questions) {
            User user = userMapper.findByID(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOList(questionDTOS);
        paginationDTO.setPagination(totalPage, page, size);
        return paginationDTO;
    }
}
