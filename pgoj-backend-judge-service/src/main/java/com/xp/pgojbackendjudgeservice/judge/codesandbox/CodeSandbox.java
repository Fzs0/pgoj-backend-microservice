package com.xp.pgojbackendjudgeservice.judge.codesandbox;

import com.xp.pgojbackendmodel.model.sandbox.ExecuteCodeRequest;
import com.xp.pgojbackendmodel.model.sandbox.ExecuteCodeResponse;

/**
 * @author <a href=""https://github.com/Fzs0>逢左使</a>
 * @create 2024/06/14 01:58
 * @Description: 代码沙箱接口定义
 */
public interface CodeSandbox {

    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);

}
