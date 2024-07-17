package com.wjy.monitor.sdk.push.impl;

import com.wjy.monitor.sdk.model.LogMessage;
import com.wjy.monitor.sdk.push.IPush;
import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * redis发布订阅方式进行消息推送
 */
public class RedisPush implements IPush {
    public  final String topicStr = "business-behavior-monitor-sdk-topic";

    private final Logger logger = LoggerFactory.getLogger(RedisPush.class);

    private RedissonClient redissonClient;

    @Override
    public synchronized void open(String host, int port) {
        // 如果之前没有创建过redis连接，则创建redis连接
        if (null != redissonClient && !redissonClient.isShutdown()) return;
        Config config = new Config();
        config.setCodec(JsonJacksonCodec.INSTANCE);
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setPassword("Rrr241356")
                .setConnectionPoolSize(64)
                .setConnectionMinimumIdleSize(10)
                .setIdleConnectionTimeout(1000)
                .setConnectTimeout(1000)
                .setRetryAttempts(3)
                .setRetryInterval(1000)
                .setPingConnectionInterval(0)
                .setKeepAlive(true);
        this.redissonClient = Redisson.create(config);
    }



    /**
     * 推送消息
     * @param logMessage
     */
    @Override
    public void send(LogMessage logMessage) {
        try {
            RTopic topic = redissonClient.getTopic(topicStr);
            long publish = topic.publish(logMessage);
        } catch (Exception e) {
            logger.error("警告: 业务行为监控组件，推送日志消息失败", e);
        }

    }

}
