package com.wjy.monitor.test.trigger;

import com.alibaba.fastjson.JSON;
import com.wjy.monitor.trigger.http.MonitorController;
import com.wjy.monitor.trigger.http.dto.MonitorDataMapDTO;
import com.wjy.monitor.types.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MonitorControllerTest {

    @Resource
    private MonitorController monitorController;

    @Test
    public void test_queryMonitorDataMapEntityList() {
        Response<List<MonitorDataMapDTO>> response = monitorController.queryMonitorDataMapEntityList();
        log.info("测试结果: {}", JSON.toJSONString(response));
    }

}
