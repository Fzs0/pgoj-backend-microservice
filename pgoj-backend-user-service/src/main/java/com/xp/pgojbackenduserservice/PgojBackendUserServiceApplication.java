package com.xp.pgojbackenduserservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.xp.pgojbackenduserservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.xp")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.xp.pgojbackendserviceclient.service"})
public class PgojBackendUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PgojBackendUserServiceApplication.class, args);
    }

}
