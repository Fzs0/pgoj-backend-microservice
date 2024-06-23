package com.xp.pgojbackendmodel.model.sandbox;

import lombok.Data;

/**
 * @author <a href=""https://github.com/Fzs0>逢左使</a>
 * @create 2024/06/02 14:15
 * @Description: 判题信息
 */
@Data
public class JudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 消耗内存
     */
    private Long memory;

    /**
     * 消耗时间（ms）
      */
    private Long time;

}
