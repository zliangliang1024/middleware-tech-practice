package liangliang.study.red.envelops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"liangliang.study.red.envelops.mapper"})
public class RedEnvelopsApp {
    public static void main(String[] args) {
        SpringApplication.run(RedEnvelopsApp.class, args);
    }
}
