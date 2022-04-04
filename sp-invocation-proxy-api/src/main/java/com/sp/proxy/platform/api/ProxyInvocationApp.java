package com.sp.proxy.platform.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * DubboProviderApplication
 * 服务提供启动类
 * @author: luchao
 * @date: Created in 4/3/22 1:06 AM
 */
@SpringBootApplication(excludeName = "druidDataSourceAutoConfigure")
//@EnableApolloConfig
public class ProxyInvocationApp {

    public static void main(String[] args) {
        SpringApplication.run(ProxyInvocationApp.class, args);
    }
}