package liangliang.study.test;

import javafx.application.Application;
import liangliang.study.redis.demo.config.CommonConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class RedisTest {

    // 定义日志
    private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);

    // 定义RedisTemplate 操作组件
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void one(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("zzz","lll");
        System.out.println(valueOperations.get("zzz"));
    }


}
