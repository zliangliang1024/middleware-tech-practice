package redis.demo.breakdown.server.service.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import redis.demo.breakdown.server.entity.Item;
import redis.demo.breakdown.server.service.ItemService;

import java.util.concurrent.TimeUnit;

/**
 * 缓存穿透service
 */
@Service
public class CachePassService {

    private static final Logger logger = LoggerFactory.getLogger(CachePassService.class);

    // 定义redis 操作组件
    @Autowired
    @Qualifier("myRedis")
    private RedisTemplate<String,Object> redisTemplate;

    // mysql
    @Autowired
    private ItemService itemService;

    // 定义缓存中key命名的前缀
    private static final String keyPrefix = "item:";

    // 定义JSON序列化与反序列化
    @Autowired
    private ObjectMapper objectMapper;

    public Item getItemInfo(String itemCode) throws JsonProcessingException {
        // 定义商品对象
        Item item = null;
        // 定义缓存中真正的key，有前缀+商品编码组成
        final String key = keyPrefix+itemCode;
        // 定义redis操作组件
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        if(redisTemplate.hasKey(key)){
            logger.info("----获取商品详情---缓存中存在该商品---商品编号为：{}",itemCode);

            // 从缓存中查询该商品详情
            Object res = opsForValue.get(key);
            if(res !=null && Strings.isNotBlank(res.toString())){
             // 如果可以找到该商品，则进行JSON反序列化解析
                item = objectMapper.readValue(res.toString(), Item.class);

            }
        }else{
            // 表示缓存中没有该商品
            logger.info("----获取商品详情---缓存中不存在该商品，从数据库中获取---商品编号为：{}",itemCode);
            // 从数据库中获取该商品
            item = itemService.selectItemByCode(itemCode);
            if(item !=null){
                // 如果数据库中查到该商品，，则将其序列化后写入缓存中
                opsForValue.set(key,objectMapper.writeValueAsString(item));
            }else{
                // 过期失效时间 TTL 设置为30分钟，实际情况要根据实际业务决定
                opsForValue.set(key,"",30L, TimeUnit.MINUTES);
            }
        }
        return item;
    }

}
