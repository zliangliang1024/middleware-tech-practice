package redis.demo.breakdown.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("redis.demo.breakdown")
@ComponentScan(basePackages = {"redis.demo.breakdown"})
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class,args);
    }

}
