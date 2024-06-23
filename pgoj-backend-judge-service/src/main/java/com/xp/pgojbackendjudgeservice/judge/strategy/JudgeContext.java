package com.xp.pgojbackendjudgeservice.judge.strategy;

import com.xp.pgojbackendmodel.model.dto.question.JudgeCase;
import com.xp.pgojbackendmodel.model.entity.Question;
import com.xp.pgojbackendmodel.model.entity.QuestionSubmit;
import com.xp.pgojbackendmodel.model.sandbox.JudgeInfo;
import lombok.Data;

import java.util.List;

/**
 * @author <a href=""https://github.com/Fzs0>逢左使</a>
 * @create 2024/06/14 23:23
 * @Description: 上下文（用于定义在策略中要传递的参数）
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;
}
