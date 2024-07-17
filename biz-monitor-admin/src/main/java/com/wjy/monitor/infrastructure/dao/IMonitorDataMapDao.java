package com.wjy.monitor.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMonitorDataMapDao {
    /**
     * 根据监控id查询map表，得到monitor_name
     * @param monitorId
     * @return
     */
    String queryMonitorNameByMonitoryId(String monitorId);
}
