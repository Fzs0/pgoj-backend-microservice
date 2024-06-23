package com.xp.pgojbackendquestionservice.controller.inner;

import com.xp.pgojbackendmodel.model.entity.Question;
import com.xp.pgojbackendmodel.model.entity.QuestionSubmit;
import com.xp.pgojbackendquestionservice.service.QuestionService;
import com.xp.pgojbackendquestionservice.service.QuestionSubmitService;
import com.xp.pgojbackendserviceclient.service.QuestionFeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author <a href=""https://github.com/Fzs0>逢左使</a>
 * @create 2024/06/21 21:34
 * @Description: 该服务仅内部调用 不是给前端来调用
 */
@RestController
@RequestMapping("/inner")
public class QuestionInnerController implements QuestionFeignClient {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @GetMapping("/get/id")
    @Override
    public Question getQuestionById(@RequestParam("questionId") long questionId) {
        return questionService.getById(questionId);
    }

    @GetMapping("/question_submit/get/id")
    @Override
    public QuestionSubmit getQuestionSubmitById(@RequestParam("questionId") long questionSubmitId) {
        return questionSubmitService.getById(questionSubmitId);
    }

    @PostMapping("/question_submit/update")
    @Override
    public boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit) {
        return questionSubmitService.updateById(questionSubmit);
    }


}

