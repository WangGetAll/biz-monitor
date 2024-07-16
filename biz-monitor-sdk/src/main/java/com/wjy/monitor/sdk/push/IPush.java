package com.wjy.monitor.sdk.push;

import com.wjy.monitor.sdk.model.LogMessage;

/**
 * 发布
 */
public interface IPush {

    void open(String host, int port);

    void send(LogMessage logMessage);

}
