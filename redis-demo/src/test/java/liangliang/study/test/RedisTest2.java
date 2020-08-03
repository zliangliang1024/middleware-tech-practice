package liangliang.study.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liangliang.study.redis.demo.App;
import liangliang.study.redis.demo.bean.Person;
import liangliang.study.redis.demo.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
public class RedisTest2 {

    // 定义日志
    private static final Logger logger = LoggerFactory.getLogger(RedisTest2.class);

    // 定义RedisTemplate 操作组件
    @Autowired
    @Qualifier("myRedis")
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * String
     */
    @Test
    public void one() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // 构造用户个体对象
        Person person = new Person(1002, 25, "阿修罗", "debug", "火星");
        // 定义字符串内容及存入缓存的key
        final String content = mapper.writeValueAsString(person);
        final String key = "redis:test:1";
        // Redis 通用操作组件
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        // 将字符串写入缓存
        logger.info("存入缓存中的用户实体对象信息：{}", content);
        valueOperations.set(key, content);
        Object result = valueOperations.get(key);
        logger.info("读取缓存中的对象信息：{}", result);
    }

    /**
     * 列表类型
     */
    @Test
    public void two() throws JsonProcessingException {
        // 构造已经排好序的用户对象列表
        List<Person> list = new ArrayList<>();
        list.add(new Person(1001, 25, "阿修罗", "debug", "火星"));
        list.add(new Person(1002, 26, "盘古", "jack", "水帘洞"));
        list.add(new Person(1003, 27, "大圣", "lee", "上古"));
        list.add(new Person(1004, 28, "八戒", "tom", "火星"));
        logger.info("构造已经排好序的用户对象列表：{}", list);
        // 定义字符串内容及存入缓存的key
        final String key = "redis:test:2";
        // Redis 通用操作组件
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        for (Person p : list) {
            // 往列表中添加数据-从队尾添加
            listOperations.leftPush(key,p);
        }
        // 获取Redis 中 List 的数据--从对头中遍历获取，直到没有元素为止
        logger.info("--- 获取Redis 中 List的数据-从对头获取--");
        Object result = listOperations.rightPop(key);
        //Person resP;
        while(result != null){
//            resP = (Person) result;
            logger.info("当前数据：{}", result);
            result = listOperations.rightPop(key);
        }
    }

    /**
     * String
     */
    @Test
    public void three() {
        logger.info("----开始 stringRedisTemplate 操作组件实战 ----");
        // 定义字符串内容及存入缓存的key
        final String content = "StringRedisTemplate 实战字符串信息";
        final String key = "redis:three";
        // Redis 通用操作组件
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        /* 将字符串写入缓存 */
        logger.info("写入缓存中的内容：{}", content);
        valueOperations.set(key, content);
        Object result = valueOperations.get(key);
        logger.info("读取缓存中的内容：{}", result);
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
        User user = new User(1, "string", "阿修罗");
        // 定义字符串内容及存入缓存的key
        final String content = mapper.writeValueAsString(user);
        final String key = "redis:four";
        // Redis 通用操作组件
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        // 将字符串写入缓存
        logger.info("写入缓存中的对象信息：{}", content);
        valueOperations.set(key, content);
        Object result = valueOperations.get(key);
        logger.info("读取缓存中的对象信息：{}", result);
    }


}
