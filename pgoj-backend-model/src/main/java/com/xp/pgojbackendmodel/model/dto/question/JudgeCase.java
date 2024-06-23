package com.xp.pgojbackendmodel.model.dto.question;

import lombok.Data;

/**
 * @author <a href=""https://github.com/Fzs0>逢左使</a>
 * @create 2024/06/02 14:02
 * @Description: 题目用例
 */
@Data
public class JudgeCase {

    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;

}
