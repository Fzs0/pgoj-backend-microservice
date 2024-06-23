package com.xp.pgojbackendjudgeservice.judge;

import com.xp.pgojbackendjudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.xp.pgojbackendjudgeservice.judge.strategy.JavaLanguageJudgeStrategy;
import com.xp.pgojbackendjudgeservice.judge.strategy.JudgeContext;
import com.xp.pgojbackendjudgeservice.judge.strategy.JudgeStrategy;
import com.xp.pgojbackendmodel.model.entity.QuestionSubmit;
import com.xp.pgojbackendmodel.model.sandbox.JudgeInfo;
import org.springframework.stereotype.Service;

/**
 * @author <a href=""https://github.com/Fzs0>逢左使</a>
 * @create 2024/06/14 23:31
 * @Description:
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
