package sg.ctx.fix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author yu.miao
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FixApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(FixApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
