package com.wjy.monitor.infrastructure.dao;

import com.wjy.monitor.infrastructure.po.MonitorDataMapNodeField;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMonitorDataMapNodeFieldDao {
    /**
     * 根据monitorId、monitorNodeId查询node_field表，得到 monitor_id, monitor_node_id, log_name, log_index, log_type, attribute_name, attribute_field, attribute_ognl
     * @param monitorDataMapNodeFieldReq
     * @return
     */
    List<MonitorDataMapNodeField> queryMonitorDataMapNodeList(MonitorDataMapNodeField monitorDataMapNodeFieldReq);
}
