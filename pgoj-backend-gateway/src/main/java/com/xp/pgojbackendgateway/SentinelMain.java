package com.xp.pgojbackendgateway;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author <a href=""https://github.com/Fzs0>逢左使</a>
 * @create 2024/06/24 21:03
 * @Description:
 */
@RestController
public class SentinelMain {

    @GetMapping("/hello")
    @SentinelResource(value = "hello", blockHandler = "handleBlocked", fallback = "handleFallback")
    public String hello() {
        return "Hello World!";
    }

    private String handleFallback() {
        // 降级处理逻辑
        return "接口降级处理";
    }

    private String handleBlocked() {
        // 限流处理逻辑
        return "接口被限流";
    }

}
