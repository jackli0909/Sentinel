package com.alibaba.csp.sentinel.dashboard.listener;

import com.alibaba.csp.sentinel.dashboard.repository.metric.MysqlMetricsRepository;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;

/**
 * <p>description</p >
 *
 * @author jack.li
 * @version 1.0
 * @date 2022/4/13 下午11:58
 */
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent>, Ordered {


    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        ApplicationContext context = applicationStartedEvent.getApplicationContext();
        MysqlMetricsRepository mysqlMetricsRepository = context.getBean(MysqlMetricsRepository.class);
        try {
            mysqlMetricsRepository.saveToMysql();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
