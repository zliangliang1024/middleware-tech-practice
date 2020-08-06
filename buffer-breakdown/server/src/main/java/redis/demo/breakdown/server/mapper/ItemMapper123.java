package redis.demo.breakdown.server.mapper;

import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
import redis.demo.breakdown.server.entity.Item;

@Mapper
public interface ItemMapper123 {

    // 根据商品编码，查询商品详情
    Item selectItemByCode1(@Param("code") String code);
}
