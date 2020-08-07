package redis.demo.breakdown.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * MapperScan 必须具体到 mapper 包下，
 * 不然会报： Invalid bound statement (not found)错误
 */
@SpringBootApplication
@MapperScan("redis.demo.breakdown.server.mapper")
@ComponentScan(basePackages = {"redis.demo.breakdown"})
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class,args);
    }

}
