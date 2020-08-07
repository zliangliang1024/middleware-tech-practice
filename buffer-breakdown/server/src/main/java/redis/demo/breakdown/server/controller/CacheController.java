package redis.demo.breakdown.server.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.demo.breakdown.server.entity.Item;
import redis.demo.breakdown.server.service.redis.CachePassService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cache/pass")
public class CacheController {

    private static final Logger logger = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    private CachePassService cachePassService;

    @PostMapping("/item/info")
    public String getItem(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        result.put("code", "0");
        result.put("msg", "success");
        String itemCode = request.getParameter("itemCode");
        try {
            Item itemInfo = cachePassService.getItemInfo(itemCode);
            result.put("data", itemInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.put("code", "-1");
            result.put("msg", "失败：" + ex.getMessage());

        }
        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }


}
