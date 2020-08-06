package redis.demo.breakdown.server.entity;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Item {

    private Integer id;
    private String code;
    private String name;
    private String createTime;
}
