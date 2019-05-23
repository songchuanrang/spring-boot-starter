package com.baidu.springbootstarter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableSwagger2
@MapperScan("com.baidu.springbootstarter.dao")
public class SpringBootApplicationInitializer extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationInitializer.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootApplicationInitializer.class);
    }

}
