package liangliang.study.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liangliang.study.redis.demo.App;
import liangliang.study.redis.demo.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
public class RedisTest {

    // 定义日志
    private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);

    // 定义RedisTemplate 操作组件
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * String
     */
    @Test
    public void one(){
        logger.info("----开始 RedisTemplate 操作组件实战 ----");
        // 定义字符串内容及存入缓存的key
        final String content = "RedisTemplate 实战字符串信息";
        final String key = "redis:template:one:string";
        // Redis 通用操作组件
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        // 将字符串写入缓存
        logger.info("写入缓存中的内容：{}",content);
        valueOperations.set(key,content);
        Object result = valueOperations.get(key);
        logger.info("读取缓存中的内容：{}",result);
    }

    /**
     * 对象序列化
     * 采用RedisTemplate讲对象信息序列化微JSON序列化JSON格式字符串后写入和缓存中，
     * 然后将其读取出来，最后反序列化解析其中的内容并展示在控制台上
     */
    @Test
    public void two() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        logger.info("----开始 RedisTemplate 操作组件实战 ----");
        // 构造对象
        User user = new User(1,"debug","阿修罗");
        // 定义字符串内容及存入缓存的key
        final String content = mapper.writeValueAsString(user);
        final String key = "redis:template:two:object";
        // Redis 通用操作组件
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        // 将字符串写入缓存
        logger.info("写入缓存中的对象信息：{}",content);
        valueOperations.set(key,content);
        Object result = valueOperations.get(key);
        logger.info("读取缓存中的对象信息：{}",result);
    }

    /**
     * String
     */
    @Test
    public void three(){
        logger.info("----开始 stringRedisTemplate 操作组件实战 ----");
        // 定义字符串内容及存入缓存的key
        final String content = "StringRedisTemplate 实战字符串信息";
        final String key = "redis:three";
        // Redis 通用操作组件
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        /* 将字符串写入缓存 */
        logger.info("写入缓存中的内容：{}",content);
        valueOperations.set(key,content);
        Object result = valueOperations.get(key);
        logger.info("读取缓存中的内容：{}",result);
    }

    /**
     * 对象序列化
     * 采用RedisTemplate讲对象信息序列化微JSON序列化JSON格式字符串后写入和缓存中，
     * 然后将其读取出来，最后反序列化解析其中的内容并展示在控制台上
     */
    @Test
    public void four() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        logger.info("----开始 StringRedisTemplate 操作组件实战 ----");
        // 构造对象
        User user = new User(1,"string","阿修罗");
        // 定义字符串内容及存入缓存的key
        final String content = mapper.writeValueAsString(user);
        final String key = "redis:four";
        // Redis 通用操作组件
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        // 将字符串写入缓存
        logger.info("写入缓存中的对象信息：{}",content);
        valueOperations.set(key,content);
        Object result = valueOperations.get(key);
        logger.info("读取缓存中的对象信息：{}",result);
    }


}
