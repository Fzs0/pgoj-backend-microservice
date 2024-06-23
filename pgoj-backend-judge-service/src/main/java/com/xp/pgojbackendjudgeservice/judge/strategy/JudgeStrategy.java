package com.xp.pgojbackendjudgeservice.judge.strategy;


import com.xp.pgojbackendmodel.model.sandbox.JudgeInfo;

/**
 * @author <a href=""https://github.com/Fzs0>逢左使</a>
 * @create 2024/06/14 23:22
 * @Description: 判题策略
 */
public interface JudgeStrategy {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);

}
