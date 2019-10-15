package com.wx.spring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 开定时
@EnableScheduling
//事务
@EnableTransactionManagement
@SpringBootApplication
public class SpringbootApplication {
    private static final Logger LOG = LoggerFactory.getLogger(SpringbootApplication.class);

    public static void main( String[] args )
    {
        SpringApplication.run(SpringbootApplication.class, args);
        LOG.info("SpringbootApplication 程序加载完毕");
    }

}
