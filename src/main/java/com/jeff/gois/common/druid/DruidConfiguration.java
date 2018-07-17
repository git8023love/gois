package com.jeff.gois.common.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * druid 配置.
 * 这样的方式：Application 不需要添加注解 @ServletComponentScan
 *
 */
@Configuration
public class DruidConfiguration {

    private static Logger logger = LoggerFactory.getLogger(DruidConfiguration.class);

    /**
     * 注册一个StatViewServlet
     * 这样的方式不需要添加注解：@ServletComponentScan
     * 访问模式:localhost:8888/druid/index.html
     * @return
     */
    @Bean
    public ServletRegistrationBean druidStatViewServle(){
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //添加初始化参数：initParams
        //白名单：
        servletRegistrationBean.addInitParameter("allow","192.168.1.226,127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny","192.168.1.73");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,*.map,/druid/*");
        return filterRegistrationBean;
    }

    /**
     * 注册dataSouce
     * @param driver 数据库驱动
     * @param url 数据库连接
     * @param username 用户名
     * @param password 密码
     * @param minIdle 最小连接池数量
     * @param maxActive 最大连接池数量
     * @param initialSize 初始化时建立物理连接的个数
     * @param timeBetweenEvictionRunsMillis 有两个含义：1) Destroy线程会检测连接的间隔时间2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
     * @param minEvictableIdleTimeMillis
     * @param validationQuery 用来检测连接是否有效的sql，要求是一个查询语句，如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用。
     * @param testWhileIdle 建议配置为true，不影响性能，并且保证安全性
     * @param testOnBorrow 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
     * @param testOnReturn 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
     * @return
     */
    @Bean
    public DataSource druidDataSource(@Value("${spring.datasource.driver-class-name}") String driver,
                                      @Value("${spring.datasource.url}") String url,
                                      @Value("${spring.datasource.username}") String username,
                                      @Value("${spring.datasource.password}") String password,
                                      @Value("${spring.datasource.minIdle}") int minIdle,
                                      @Value("${spring.datasource.maxActive}") int maxActive,
                                      @Value("${spring.datasource.initialSize}") int initialSize,
                                      @Value("${spring.datasource.timeBetweenEvictionRunsMillis}") int timeBetweenEvictionRunsMillis,
                                      @Value("${spring.datasource.minEvictableIdleTimeMillis}") int minEvictableIdleTimeMillis,
                                      @Value("${spring.datasource.validationQuery}") String validationQuery,
                                      @Value("${spring.datasource.testWhileIdle}") boolean testWhileIdle,
                                      @Value("${spring.datasource.testOnBorrow}") boolean testOnBorrow,
                                      @Value("${spring.datasource.testOnReturn}") boolean testOnReturn){

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        logger.info("DruidConfiguration.druidDataSource(),url={},username={}password={}",url,username,password);
        try {
            druidDataSource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;

    }
}
