package com.chenpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: chenpt
 * @Description:
 * @Date: Created in 2020-08-19 17:18
 * @Modified By:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args){
        SpringApplication.run(GatewayApplication.class, args);
    }
}
