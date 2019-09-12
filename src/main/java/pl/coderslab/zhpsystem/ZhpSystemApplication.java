package pl.coderslab.zhpsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ZhpSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhpSystemApplication.class, args);
    }

}
