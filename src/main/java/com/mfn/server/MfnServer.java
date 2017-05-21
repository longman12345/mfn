package com.mfn.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by LPF on 2017/2/25.
 */
@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = "com.mfn", lazyInit = true)
public class MfnServer {
    public static void main(String[] args) {
        SpringApplication.run(MfnServer.class, args);
    }
}
