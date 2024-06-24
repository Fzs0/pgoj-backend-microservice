package com.xp.pgojbackendjudgeservice.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 用于创建测试程序用到的交换机和队列（只用在程序启动前执行一次）
 */
@Slf4j
@Component
public class InitRabbitMqBean {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private String port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;

    @PostConstruct
    public void init() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(host);
            factory.setPort(5672);
            factory.setUsername(username);
            factory.setPassword(password);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            String EXCHANGE_NAME = "judge_exchange";
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            // 创建队列，随机分配一个队列名称
            String queueName = "judge_queue";
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, EXCHANGE_NAME, "my_routingKey");
            log.info("消息队列启动成功");
        } catch (Exception e) {
            log.error("消息队列启动失败", e);
        }
    }
}
