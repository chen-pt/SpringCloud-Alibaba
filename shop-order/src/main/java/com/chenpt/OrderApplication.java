package com.chenpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: chenpt
 * @Description:
 * @Date: Created in 2020-08-03 11:26
 * @Modified By:
 */
@SpringBootApplication
@EnableDiscoveryClient //服务注册
@EnableFeignClients    //开启fegin
public class OrderApplication {


    public static void main(String[] args){
        SpringApplication.run(OrderApplication.class);
    }

    @Bean
    @LoadBalanced //基于ribbon的负载均衡
    public RestTemplate getRestTemplate() {
       return new RestTemplate();
    }

}
