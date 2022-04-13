package com.alibaba.csp.sentinel.dashboard.repository.metric;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.MetricEntity;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>将元素添加到链表中,启动一个线程监听链表中是否有元素，有则获取插入数据库,无则循环等待</p >
 *
 * @author jack.li
 * @version 1.0
 * @date 2022/4/13 下午11:39
 */
@Component
public class MysqlMetricsRepository {

    public Boolean flag = Boolean.TRUE;

    private LinkedList<MetricEntity> linkedList = new LinkedList<>();


    /**
     * add MetricEntity to link list
     *
     * @param metricEntity MetricEntity
     */
    public void addMetricEntity(MetricEntity metricEntity) {
        linkedList.addFirst(metricEntity);
    }


    public void saveToMysql() throws InterruptedException {
        while (flag) {
            MetricEntity last = linkedList.pollLast();
            if (!Objects.isNull(last)) {
                // TODO
                System.out.println(JSON.toJSONString(last));
            } else {
                TimeUnit.SECONDS.sleep(1);
            }
        }


    }

}
