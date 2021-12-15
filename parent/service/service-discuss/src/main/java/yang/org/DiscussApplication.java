package yang.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author LvXueYang
 * @description 评论启动类
 */

@EnableEurekaClient
@EnableSwagger2
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class DiscussApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscussApplication.class);
    }
}
