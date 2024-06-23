package com.xp.pgojbackendmodel.model.dto.question;

import lombok.Data;

/**
 * @author <a href=""https://github.com/Fzs0>逢左使</a>
 * @create 2024/06/02 14:03
 * @Description: 题目配置
 */
@Data
public class JudgeConfig {

    /**
     * 时间限制（ms）
     */
    private Long timeLimit;

    /**
     * 内存限制（KB）
     */
    private Long memoryLimit;

    /**
     * 堆栈限制（KB）
     */
    private Long stackLimit;

}
