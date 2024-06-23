package com.xp.pgojbackendjudgeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.xp")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.xp.pgojbackendserviceclient.service"})
public class PgojBackendJudgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PgojBackendJudgeServiceApplication.class, args);
    }

}
