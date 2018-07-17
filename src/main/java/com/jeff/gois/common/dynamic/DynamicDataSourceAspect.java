package com.jeff.gois.common.dynamic;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切换数据源Advice
 */
@Aspect
@Order(-10)//保证该AOP在@Transactional之前执行MyBatisConfiguration
@Component
public class DynamicDataSourceAspect {
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) throws Throwable {
        //获取当前的指定的数据源
        String dsId = targetDataSource.value();
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            logger.error("数据源[{}]不存在，使用默认数据源 >>>>>>>>> {}", targetDataSource.value(), joinPoint.getSignature());
        } else {
            logger.info("Use DataSource : {} >>>>>>>>> {}", targetDataSource.value(), joinPoint.getSignature());
            //找到的话，那么设置到动态数据源上下文中
            DynamicDataSourceContextHolder.setDataSourceType(targetDataSource.value());
        }
    }

    @After("@annotation(targetDataSource)")
    public void restoreDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        logger.info("Revert DataSource : {} >>>>>>>>> {}", targetDataSource.value(), joinPoint.getSignature());
        //方法执行完毕之后，销毁当前数据源信息，进行垃圾回收。
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
