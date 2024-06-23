package com.xp.pgojbackendjudgeservice.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.xp.pgojbackendcommon.common.ErrorCode;
import com.xp.pgojbackendcommon.exception.BusinessException;
import com.xp.pgojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.xp.pgojbackendmodel.model.sandbox.ExecuteCodeRequest;
import com.xp.pgojbackendmodel.model.sandbox.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href=""https://github.com/Fzs0>逢左使</a>
 * @create 2024/06/14 02:09
 * @Description: 远程代码沙箱
 */
public class RemoteCodeSandbox implements CodeSandbox {

    // 定义鉴权请求头和密钥
    public static final String AUTH_REQUEST_HEADER = "auth";

    public static final String AUTH_REQUEST_SECRET = "secretKey";

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        String url = "http://localhost:8090/executeCode";
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
                .body(json)
                .execute()
                .body();
        if (StringUtils.isBlank(responseStr)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "executeCode remoteSandbox error, message = " + responseStr);
        }
        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }
}
