package com.xp.pgojbackendjudgeservice.judge;

import com.xp.pgojbackendmodel.model.entity.QuestionSubmit;

/**
 * @author <a href=""https://github.com/Fzs0>逢左使</a>
 * @create 2024/06/14 22:12
 * @Description: 判题服务
 */
public interface JudgeService {

    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(long questionSubmitId);

}
