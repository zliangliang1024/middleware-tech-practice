package liangliang.study.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liangliang.study.redis.demo.App;
import liangliang.study.redis.demo.bean.Fruit;
import liangliang.study.redis.demo.bean.Person;
import liangliang.study.redis.demo.bean.PhoneUser;
import liangliang.study.redis.demo.bean.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
            listOperations.leftPush(key, p);
        }
        // 获取Redis 中 List 的数据--从对头中遍历获取，直到没有元素为止
        logger.info("--- 获取Redis 中 List的数据-从对头获取--");
        Object result = listOperations.rightPop(key);
        //Person resP;
        while (result != null) {
//            resP = (Person) result;
            logger.info("当前数据：{}", result);
            result = listOperations.rightPop(key);
        }
    }

    /**
     * Set类型
     */
    @Test
    public void three() {

        // 构造一组用户姓名列表
        List<String> userList = new ArrayList<>();
        userList.add("debug");
        userList.add("jack");
        userList.add("修罗");
        userList.add("大圣");
        userList.add("debug");
        userList.add("修罗");
        logger.info("待处理的用户姓名列表，{}", userList);
        // 遍历访问，删除相同的用户并放入缓存集合中，最终存入缓存中
        final String key = "redis:test:3";
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        for (String str : userList) {
            setOperations.add(key, str);
        }
        // 从缓存中获取用户对象集合
        Object pop = setOperations.pop(key);
        while (pop != null) {
            logger.info("从缓存中获取的用户集合-当前用户：{} ", pop);
            pop = setOperations.pop(key);

        }
    }

    /**
     * 有序集合
     */
    @Test
    public void four() throws JsonProcessingException {

        // 构造一组无序的用户手机充值对象列表
        List<PhoneUser> userList = new ArrayList<>();
        userList.add(new PhoneUser("103", 130.0));
        userList.add(new PhoneUser("101", 120.0));
        userList.add(new PhoneUser("102", 88.0));
        userList.add(new PhoneUser("105", 99.0));
        userList.add(new PhoneUser("106", 50.0));
        userList.add(new PhoneUser("104", 65.0));
        userList.add(new PhoneUser("107", 78.5));
        logger.info("构造一组无序的用户手机充值列表：{}", userList);
        // 定义字符串内容及存入缓存的key
        final String key = "redis:test:4";
        // 遍历访问充值对象列表，将信息塞入Redis的缓存列表中
        // 因为zset在add元素进入缓存后，下次就不能进行更新了，因而为了测试方便
        // 进行操作之前先清空缓存(实际生产环境中不建议这么使用)
        redisTemplate.delete(key);
        ZSetOperations<String, Object> opsForZSet = redisTemplate.opsForZSet();
        // 将元素添加进有序集合SortedSet中
        for (PhoneUser p : userList) {
            opsForZSet.add(key, p, p.getFare());
        }

        // 前端获取访问充值排名靠前的用户列表
        Long size = opsForZSet.size(key);
        logger.info("缓存中的列表长度：" + size);
        // 从小到大排序
        Set<Object> range = opsForZSet.range(key, 0L, size);
        // 从大到小
//        Set<Object> range = opsForZSet.reverseRange(key, 0L, size);
        // 遍历获取有序集合中的元素
        for (Object u : range) {

            logger.info("从缓存中读取手机充值记录排序列表，当前记录：{}", u);

        }
    }

    /**
     * Hash 哈希存储
     */
    @Test
    public void five() {
        // 构造学生对象列表和水果对象列表
        List<Student> students = new ArrayList<>();
        List<Fruit> fruits = new ArrayList<>();
        // 往学生集合中添加学生对象
        students.add(new Student("10010", "张三", "three"));
        students.add(new Student("10086", "李四", "four"));
        students.add(new Student("10026", "王五", "five"));
        // 往水果集合中添加水果对象
        fruits.add(new Fruit("apple", "red"));
        fruits.add(new Fruit("orange", "橙色"));
        fruits.add(new Fruit("banana", "黄色"));
        // 分别遍历不同的对象列表，并采用Hash哈希存储在缓存中
        final String sKey = "redis:test:5";
        final String fKey = "redis:test:6";
        // 获取Hash存储操作值HashOperations，遍历获取集合中的对象并添加进缓存中
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        for (Student s : students) {
            opsForHash.put(sKey, s.getId(), s);
        }
        for (Fruit f : fruits) {
            opsForHash.put(fKey, f.getName(), f);
        }

        // 获取学生对象列表与水果对象列表
        Map<Object, Object> sMap = opsForHash.entries(sKey);
        logger.info("获取学生对象列表：{}",sMap);
        Map<Object, Object> fMap = opsForHash.entries(fKey);
        logger.info("获取水果对象列表：{}",fMap);
        // 获取指定的学生对象
        String sField = "10086";
        Object stu = opsForHash.get(sKey, sField);
        logger.info("获取指定的学生对象，{}->{}",sField,stu);
        // 获取指定的水果对象
        String fField = "orange";
        Object f = opsForHash.get(fKey, fField);
        logger.info("获取指定的学生对象，{}->{}",fField,f);

    }


}
