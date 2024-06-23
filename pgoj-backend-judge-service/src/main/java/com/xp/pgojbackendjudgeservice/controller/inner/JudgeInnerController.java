package com.xp.pgojbackendjudgeservice.controller.inner;

import com.xp.pgojbackendjudgeservice.judge.JudgeService;
import com.xp.pgojbackendmodel.model.entity.QuestionSubmit;
import com.xp.pgojbackendserviceclient.service.JudgeFeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author <a href=""https://github.com/Fzs0>逢左使</a>
 * @create 2024/06/21 21:54
 * @Description: 该服务仅内部调用，不是给前端的
 */
@RestController
@RequestMapping("/inner")
public class JudgeInnerController implements JudgeFeignClient {

    @Resource
    private JudgeService judgeService;

    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    @Override
    @PostMapping("/do")
    public QuestionSubmit doJudge(@RequestParam("questionSubmitId") long questionSubmitId){
        return judgeService.doJudge(questionSubmitId);
    }

}
