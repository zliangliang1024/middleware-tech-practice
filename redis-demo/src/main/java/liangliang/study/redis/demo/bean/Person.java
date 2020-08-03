package liangliang.study.redis.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person implements Serializable {

    public static final long serialVersionUID = 1L;

    private Integer id;
    private Integer age;
    private String name;
    private String userName;
    private String addr;


}
