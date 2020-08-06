package redis.demo.breakdown.server.service.impl;

import org.springframework.stereotype.Service;
import redis.demo.breakdown.server.entity.Item;
import redis.demo.breakdown.server.mapper.ItemMapper123;
import redis.demo.breakdown.server.service.ItemService123;

import javax.annotation.Resource;

@Service("item123")
public class ItemService123Impl implements ItemService123 {

    @Resource
    private ItemMapper123 itemMapper;

    @Override
    public Item selectItemByCode1(String code) {
        return itemMapper.selectItemByCode1(code);
    }
}
