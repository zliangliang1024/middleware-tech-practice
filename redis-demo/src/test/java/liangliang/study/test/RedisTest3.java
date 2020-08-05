package liangliang.study.test;

import liangliang.study.redis.demo.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * 单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
public class RedisTest3 {

    // 定义日志
    private static final Logger logger = LoggerFactory.getLogger(RedisTest2.class);

    // 定义RedisTemplate 操作组件
    @Autowired
    @Qualifier("myRedis")
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * key 失效一
     */
    @Test
    public void one() throws InterruptedException {

        // 构造Key 与 Redis 操作组件
        final String key1 = "redis:test:6";
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        // 第一种方法，再往缓存中set数据时，提供一个ttl，表示ttl一到，缓存中的key
        // 将自动失效，即被清理，这里TTL是10秒
        opsForValue.set(key1, "expire 操作", 10L, TimeUnit.SECONDS);
        // 等待5秒，判断key是否存在
        Thread.sleep(5000L);
        Boolean existsKey1 = stringRedisTemplate.hasKey(key1);
        String val = opsForValue.get(key1);
        logger.info("等待5秒-判断key是否还存在：{}，对应的值：{}", existsKey1, val);

        // 再等待5秒，判断key是否存在
        Thread.sleep(5000L);
        existsKey1 = stringRedisTemplate.hasKey(key1);
        val = opsForValue.get(key1);
        logger.info("再等待5秒-再判断key是否还存在：{}，对应的值：{}", existsKey1, val);

    }

    /**
     * key 失效二
     */
    @Test
    public void two() throws InterruptedException {

        // 构造Key 与 Redis 操作组件
        final String key2 = "redis:test:7";
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        // 第二种方法，再往缓存中set数据后，采用redisTemplate 的 expire 方法使该key失效
        // 将自动失效，即被清理，这里TTL是10秒
        opsForValue.set(key2, "expire 操作-2");
        stringRedisTemplate.expire(key2,10L,TimeUnit.SECONDS);
        // 等待5秒，判断key是否存在
        Thread.sleep(5000L);
        Boolean existsKey2 = stringRedisTemplate.hasKey(key2);
        String val = opsForValue.get(key2);
        logger.info("等待5秒-判断key是否还存在：{}，对应的值：{}", existsKey2, val);

        // 再等待5秒，判断key是否存在
        Thread.sleep(5000L);
        existsKey2 = stringRedisTemplate.hasKey(key2);
        val = opsForValue.get(key2);
        logger.info("再等待5秒-再判断key是否还存在：{}，对应的值：{}", existsKey2, val);

    }
}
