package com.jeff.gois.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 服务启动执行
 * 如果有多个启动执行类,要按照顺序执行时,可以使用@Order(value =1 或者 =2)
 */
@Component
public class ServiceStartupRunner implements CommandLineRunner {

    public static Logger logger = LoggerFactory.getLogger(ServiceStartupRunner.class);

    @Override
    public void run(String... strings) throws Exception {
        logger.info(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");

    }
}
