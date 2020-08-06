package redis.demo.breakdown.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * 通用化配置
 */
@Configuration
public class CommonConfig {

    // Redis 连接工厂
    /**
     * springboot2.x 使用LettuceConnectionFactory 代替 RedisConnectionFactory
     *     * application.yml配置基本信息后,springboot2.x  RedisAutoConfiguration能够自动装配
     *     * LettuceConnectionFactory 和 RedisConnectionFactory 及其 RedisTemplate
     *
     * ————————————————
     * 版权声明：本文为CSDN博主「M丶lang」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/u010957645/article/details/89340983
     */
    @Resource
    private LettuceConnectionFactory redisConnectionFactory;

    // 缓存操作组件 RedisTemplate 的自定义配置
    @Bean("myRedis")
    @Primary
    public RedisTemplate<String,Object> redisTemplate(){
        // 定义RedisTemplate 实例
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 指定Key序列化策略为String 序列化，Value 为JDK 自带的序列化策略
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        // 指定hashKey序列化策略为String 序列化-针对hash 散列存储
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    /**
     * 缓存操作组件 StringRedisTemplate
     * @return
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(){
        // 采用默认配置即可-后续有自定义配置时则在此处添加即可
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
    }
}
