package com.wjy.monitor.domain.repository;


import com.wjy.monitor.domain.entity.MonitorDataEntity;
import com.wjy.monitor.domain.valobj.GatherNodeExpressionVO;

import java.util.List;

public interface IMonitorRepository {

    List<GatherNodeExpressionVO> queryGatherNodeExpressionVO(String systemName, String className, String methodName);

    String queryMonitoryNameByMonitoryId(String monitorId);


    void saveMonitoryData(MonitorDataEntity monitorDataEntity);

}
