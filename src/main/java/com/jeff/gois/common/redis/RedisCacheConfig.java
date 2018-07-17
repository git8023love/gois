package com.jeff.gois.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;

/**
 * redis 缓存配置;
 */
@Configuration
@EnableCaching //启用缓存
public class RedisCacheConfig extends CachingConfigurerSupport {

    private static Logger logger = LoggerFactory.getLogger(RedisCacheConfig.class);

    /**
     * 缓存管理器.
     * @param redisTemplate
     * @return CacheManager
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
        CacheManager cacheManager = new RedisCacheManager(redisTemplate);
        return cacheManager;
    }

    /**
     * redis模板操作类,类似于jdbcTemplate的一个类;
     * @param redisConnectionFactory 通过Spring进行注入，参数在application.properties进行配置；
     * @return  RedisTemplate<String,String>
     */
    @Bean
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,String> redisTemplate = new RedisTemplate<>();
        /**key序列化方式;（不然会出现乱码;）如果方法上有Long等非String类型的话，会报类型转换错误;
         * 在没有自己定义key生成策略的时候,建议不要这么写，可以不配置或者自己实现ObjectRedisSerializer
         * 或者JdkSerializationRedisSerializer序列化方式;
         */
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型不可以会出现异常信息;
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * 自定义key.
     * 根据类名+方法名+所有参数的值生成唯一的一个key,
     * 即使@Cacheable中的value属性一样，key也会不一样
     * @return KeyGenerator
     */
    @Override
    public KeyGenerator keyGenerator(){
        logger.info("RedisCacheConfig.keyGenerator()");
        return new KeyGenerator() {
            @Override
            public Object generate(Object object, Method method, Object... objects) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(object.getClass().getName());
                stringBuilder.append(method.getName());
                for (Object obj : objects) {
                    stringBuilder.append(obj.toString());
                }
                logger.info("keyGenerator={}",stringBuilder.toString());
                return stringBuilder.toString();
            }
        };
    }
}
