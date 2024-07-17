package com.wjy.monitor.trigger.listener;


import com.alibaba.fastjson.JSON;
import com.wjy.monitor.domain.service.ILogAnalyticalService;
import com.wjy.monitor.sdk.model.LogMessage;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.listener.MessageListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class MonitorLogListener implements MessageListener<LogMessage> {

    @Resource
    private ILogAnalyticalService logAnalyticalService;

    @Override
    public void onMessage(CharSequence charSequence, LogMessage logMessage) {
        try {
            log.info("监听监控日志消息，解析存储: {}", JSON.toJSONString(logMessage));
            logAnalyticalService.doAnalytical(logMessage.getSystemName(), logMessage.getClassName(), logMessage.getMethodName(), logMessage.getLogList());
        } catch (Exception e) {
            log.error("监听监控日志消息，解析失败: {}", JSON.toJSONString(logMessage), e);
        }

    }
}
