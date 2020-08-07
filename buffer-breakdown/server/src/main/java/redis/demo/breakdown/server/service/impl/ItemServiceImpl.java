package redis.demo.breakdown.server.service.impl;

import org.springframework.stereotype.Service;
import redis.demo.breakdown.server.entity.Item;
import redis.demo.breakdown.server.mapper.ItemMapper;
import redis.demo.breakdown.server.service.ItemService;

import javax.annotation.Resource;

@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemMapper itemMapper;

    @Override
    public Item selectItemByCode(String code) {
        return itemMapper.selectItemByCode(code);
    }
}
