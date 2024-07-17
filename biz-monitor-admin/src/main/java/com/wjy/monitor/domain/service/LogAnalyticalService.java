package com.wjy.monitor.domain.service;

import com.alibaba.fastjson.JSONObject;
import com.wjy.monitor.domain.entity.MonitorDataEntity;
import com.wjy.monitor.domain.entity.MonitorDataMapEntity;
import com.wjy.monitor.domain.repository.IMonitorRepository;
import com.wjy.monitor.domain.valobj.GatherNodeExpressionVO;
import com.wjy.monitor.types.Constants;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogAnalyticalService implements ILogAnalyticalService {

    @Resource
    private IMonitorRepository repository;

    /**
     *
     * @param systemName
     * @param className
     * @param methodName
     * @param logList
     * @throws OgnlException
     */
    @Override
    public void doAnalytical(String systemName, String className, String methodName, List<String> logList) throws OgnlException {
        // 查询匹配解析节点
        // 根据系统、类、方法查node表，得到监控id和结点id
        // 根据监控id、结点id查node_field表，得到这个结点上的日志都有哪些字段，字段的解析规则。
        // 组装得到结点的信息
        List<GatherNodeExpressionVO> gatherNodeExpressionVOs = repository.queryGatherNodeExpressionVO(systemName, className, methodName);

        if (null == gatherNodeExpressionVOs || gatherNodeExpressionVOs.isEmpty()) return;


        // 解析结点数据，可以用线程池解析。线程池策略，可能会丢。配置多机操作。
        for (GatherNodeExpressionVO gatherNodeExpressionVO : gatherNodeExpressionVOs) {
            // 后续放到缓存中
            // 根据监控id查询map表，得到monitor_name
            String monitoryName = repository.queryMonitoryNameByMonitoryId(gatherNodeExpressionVO.getMonitorId());

            List<GatherNodeExpressionVO.Filed> fileds = gatherNodeExpressionVO.getFileds();
            for (GatherNodeExpressionVO.Filed filed : fileds) {
                Integer logIndex = filed.getLogIndex();
                // 默认第一个字段是logName
                String logName = logList.get(0);
                // 不匹配，就尝试匹配下一个
                if (!logName.equals(filed.getLogName())) {
                    continue;
                }

                String attributeValue = "";
                String logStr = logList.get(logIndex);
                // 对象类型用ognl表达式解析
                if ("Object".equals(filed.getLogType())) {
                    OgnlContext context = new OgnlContext();
                    context.setRoot(JSONObject.parseObject(logStr));
                    Object root = context.getRoot();
                    attributeValue = String.valueOf(Ognl.getValue(filed.getAttributeOgnl(), context, root));
                } else {
                    attributeValue = logStr.trim();
                    if (attributeValue.contains(Constants.COLON)) {
                        attributeValue = attributeValue.split(Constants.COLON)[1].trim();
                    }
                }

                MonitorDataEntity monitorDataEntity = MonitorDataEntity.builder()
                        .monitorId(gatherNodeExpressionVO.getMonitorId())
                        .monitorName(monitoryName)
                        .monitorNodeId(gatherNodeExpressionVO.getMonitorNodeId())
                        .systemName(gatherNodeExpressionVO.getGatherSystemName())
                        .clazzName(gatherNodeExpressionVO.getGatherClazzName())
                        .methodName(gatherNodeExpressionVO.getGatherMethodName())
                        .attributeName(filed.getAttributeName())
                        .attributeField(filed.getAttributeField())
                        .attributeValue(attributeValue)
                        .build();

                repository.saveMonitoryData(monitorDataEntity);
            }

        }

    }

    /**
     * 查map表，得到所有的监控id、监控name
     * @return
     */
    @Override
    public List<MonitorDataMapEntity> queryMonitorDataMapEntityList() {
        return repository.queryMonitorDataMapEntityList();
    }


}
