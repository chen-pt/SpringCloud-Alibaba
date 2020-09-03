package com.chenpt.service;

import com.alibaba.fastjson.JSON;
import com.chenpt.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(
        consumerGroup = "shop-user", //消费者组名
        topic = "order-topic"//消费主题
//        consumeMode = ConsumeMode.CONCURRENTLY,//消费模式,指定是否顺序消费 CONCURRENTLY(同步,默认) ORDERLY(顺序)
//        messageModel = MessageModel.CLUSTERING//消息模式 BROADCASTING(广播)  CLUSTERING(集群,默认)
)
public class RocketConsumerService implements RocketMQListener<Order> {

    //消费逻辑
    @Override
    public void onMessage(Order message) {
        log.info("接收到了一个订单信息{},接下来就可以发送短信通知了", JSON.toJSONString(message));
    }

}
