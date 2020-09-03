package com.chenpt.controller;

import com.alibaba.fastjson.JSON;
import com.chenpt.feginApi.ProductApiService;
import com.chenpt.domain.Order;
import com.chenpt.domain.Product;
import com.chenpt.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: chenpt
 * @Description:
 * @Date: Created in 2020-08-03 13:10
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    private static final String PRODUCT_URL = "http://localhost:8081/product/";
    private static final String PRODUCT_SERVER = "service-product";

    @Autowired
    private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;//专门负责服务注册和发现的，我们可以通过它获取到注册到注册中心的所有服务
    @Autowired
    private ProductApiService productApiService;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 基于fegin实现远程服务调用
     * @param pid
     * @return
     */
    @GetMapping("/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        log.info(">>>客户下单，调用商品微服务查询商品信息<<<");
        Product product = productApiService.getByPid(pid);
        log.info(">>商品信息,查询结果:" + JSON.toJSONString(product));
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户1");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
//        orderService.save(order);

        //下单成功之后放入消息队列
        rocketMQTemplate.convertAndSend("order-topic",order);

        return order;
    }

    /**
     * 采用nacos服务治理 获取商品微服务信息
     * 基于ribbon实现负载均衡
     * @param pid
     * @return
     */
//    @GetMapping("/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info(">>>客户下单，调用商品微服务查询商品信息<<<");
//
//        //直接使用微服务名字， 从nacos中获取服务地址
//        String url = PRODUCT_SERVER;
//        Product product = restTemplate.getForObject("http://"+url+"/product/"+pid, Product.class);
//
//        log.info(">>商品信息,查询结果:" + JSON.toJSONString(product));
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("测试用户1");
//        order.setPid(product.getPid());
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//        order.setNumber(1);
////        orderService.save(order);
//        return order;
//    }

    /**
     * 采用nacos服务治理 获取商品微服务信息
     * 自定义负载均衡
     * @param pid
     * @return
     */
//    @GetMapping("/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info(">>>客户下单，调用商品微服务查询商品信息<<<");
//
//        //从nacos中获取服务地址 获取的是个list集群信息
//        List<ServiceInstance> instances = discoveryClient.getInstances(PRODUCT_SERVER);
//
//        int index = new Random().nextInt(instances.size());
//        ServiceInstance instance = instances.get(index);
//
//        String url = instance.getHost() + ":" +instance.getPort();
//        log.info(">>从nacos中获取到的微服务地址为:" + url);
//        Product product = restTemplate.getForObject("http://"+url+"/product/"+pid, Product.class);
//
//        log.info(">>商品信息,查询结果:" + JSON.toJSONString(product));
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("测试用户1");
//        order.setPid(product.getPid());
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//        order.setNumber(1);
////        orderService.save(order);
//        return order;
//    }


    /**
     * 采用nacos服务治理 获取商品微服务信息
     * 目前只是取实例服务的第一条
     * 没实现集群
     * @param pid
     * @return
     */
//    @GetMapping("/prod/{pid}")
//    public Order order2(@PathVariable("pid") Integer pid){
//        log.info(">>>客户下单，调用商品微服务查询商品信息<<<");
//
//        //从nacos中获取服务地址 获取的是个list集群信息
//        ServiceInstance instance = discoveryClient.getInstances(PRODUCT_SERVER).get(0);
//        String url = instance.getHost() + ":" +instance.getPort();
//        log.info(">>从nacos中获取到的微服务地址为:" + url);
//        Product product = restTemplate.getForObject("http://"+url+"/product/"+pid, Product.class);
//
//        log.info(">>商品信息,查询结果:" + JSON.toJSONString(product));
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("测试用户1");
//        order.setPid(product.getPid());
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//        order.setNumber(1);
////        orderService.save(order);
//        return order;
//    }


    /**
     * 采用restTemplate调用商品微服务
     * 硬编码风格 后期不容易维护   无法实现集群
     * @param pid
     * @return
     */
//    @GetMapping("/prod/{pid}")
//    public Order order3(@PathVariable("pid") Integer pid){
//        log.info(">>>客户下单，调用商品微服务查询商品信息<<<");
//        // 采用restTemplate调用
//        Product product = restTemplate.getForObject(PRODUCT_URL+pid, Product.class);
//        log.info(">>商品信息,查询结果:" + JSON.toJSONString(product));
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("测试用户1");
//        order.setPid(product.getPid());
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//        order.setNumber(1);
//        orderService.save(order);
//        return order;
//    }


}
