package redis.demo.breakdown.server.service;


import redis.demo.breakdown.server.entity.Item;

public interface ItemService {

    Item selectItemByCode(String code);
}
