package liangliang.study.redis.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 学生对象
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

    private String id;
    private String username;
    private String name;
}
