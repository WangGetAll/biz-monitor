package com.wjy.monitor.infrastructure.dao;

import com.wjy.monitor.infrastructure.po.MonitorData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMonitorDataDao {

    List<MonitorData> queryMonitorDataList(MonitorData monitorDataReq);

}
