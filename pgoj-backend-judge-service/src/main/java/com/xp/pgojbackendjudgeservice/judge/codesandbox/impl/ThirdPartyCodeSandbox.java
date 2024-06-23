package com.xp.pgojbackendjudgeservice.judge.codesandbox.impl;

import com.xp.pgojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.xp.pgojbackendmodel.model.sandbox.ExecuteCodeRequest;
import com.xp.pgojbackendmodel.model.sandbox.ExecuteCodeResponse;

/**
 * @author <a href=""https://github.com/Fzs0>逢左使</a>
 * @create 2024/06/14 02:09
 * @Description: 第三方代码沙箱 （调用网上现场的代码沙箱）
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {
    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        return null;
    }
}
