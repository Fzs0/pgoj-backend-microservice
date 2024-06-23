package com.xp.pgojbackendjudgeservice.judge.codesandbox;

import com.xp.pgojbackendjudgeservice.judge.codesandbox.impl.ExampleCodeSandbox;
import com.xp.pgojbackendjudgeservice.judge.codesandbox.impl.RemoteCodeSandbox;
import com.xp.pgojbackendjudgeservice.judge.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * @author <a href=""https://github.com/Fzs0>逢左使</a>
 * @create 2024/06/14 14:43
 * @Description: 代码沙箱工厂（根据字符串参数创建指定的代码沙箱实例）
 */
public class CodeSandboxFactory {

    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }

}
