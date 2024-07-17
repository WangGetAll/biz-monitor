package com.wjy.monitor.infrastructure.dao;

import com.wjy.monitor.infrastructure.po.MonitorDataMapNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMonitorDataMapNodeDao {
    /**
     * 根据gatherSystemName、gatherClazzName、gatherMethodName查询node表，得到monitorId、nodeId、gatherSystemName、gatherClazzName、gatherMethodName。
     * @param monitorDataMapNodeReq
     * @return
     */
    List<MonitorDataMapNode> queryMonitoryDataMapNodeList(MonitorDataMapNode monitorDataMapNodeReq);
}
