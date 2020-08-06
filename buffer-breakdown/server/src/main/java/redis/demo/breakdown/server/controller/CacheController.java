package redis.demo.breakdown.server.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.demo.breakdown.server.entity.Item;
import redis.demo.breakdown.server.service.ItemService123;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cache/pass")
public class CacheController {

    private static final Logger logger = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    private ItemService123 itemService123;

    @PostMapping("/item/info")
    public String getItem(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", "0");
        result.put("msg", "success");
        String itemCode = request.getParameter("itemCode");
        try {
            Item itemInfo = itemService123.selectItemByCode1(itemCode);
            result.put("data", itemInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.put("code", "-1");
            result.put("msg", "失败：" + ex.getMessage());

        }
        return result.toString();
    }


}
