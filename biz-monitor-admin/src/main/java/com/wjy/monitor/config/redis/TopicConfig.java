package com.wjy.monitor.config.redis;

import com.wjy.monitor.sdk.model.LogMessage;
import com.wjy.monitor.trigger.listener.MonitorLogListener;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {
    @Bean("businessBehaviorMonitorTopic")
    public RTopic businessBehaviorMonitorTopic(RedissonClient redissonClient, MonitorLogListener monitorLogListener) {
        RTopic topic = redissonClient.getTopic("business-behavior-monitor-sdk-topic");
        topic.addListener(LogMessage.class, monitorLogListener);
        return topic;
    }

}
