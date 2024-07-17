package com.wjy.monitor.infrastructure.dao;

import com.wjy.monitor.infrastructure.po.MonitorDataMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMonitorDataMapDao {
    /**
     * 根据监控id查询map表，得到monitor_name
     * @param monitorId
     * @return
     */
    String queryMonitorNameByMonitoryId(String monitorId);

    /**
     * 查map表，得到所有的监控id、监控name
     * @return
     */
    List<MonitorDataMap> queryMonitorDataMapEntityList();
}
