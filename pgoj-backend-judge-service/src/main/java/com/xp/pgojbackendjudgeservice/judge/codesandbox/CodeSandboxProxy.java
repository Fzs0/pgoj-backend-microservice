package com.xp.pgojbackendjudgeservice.judge.codesandbox;

import com.xp.pgojbackendmodel.model.sandbox.ExecuteCodeRequest;
import com.xp.pgojbackendmodel.model.sandbox.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href=""https://github.com/Fzs0>逢左使</a>
 * @create 2024/06/14 21:59
 * @Description:
 */
@Slf4j
public class CodeSandboxProxy implements CodeSandbox{

    private final CodeSandbox codeSandbox;

    public CodeSandboxProxy(CodeSandbox codeSandbox) {
        this.codeSandbox = codeSandbox;
    }

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        return codeSandbox.executeCode(executeCodeRequest);
    }
}
