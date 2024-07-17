package com.wjy.monitor.domain.service;
import com.wjy.monitor.domain.entity.MonitorDataMapEntity;
import ognl.OgnlException;
import java.util.List;

/**
 * 解析数据
 */
public interface  ILogAnalyticalService {
    void doAnalytical(String systemName, String className, String methodName, List<String> logList) throws OgnlException;
    List<MonitorDataMapEntity> queryMonitorDataMapEntityList();
}
