package com.chenpt.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenpt
 * @Description:
 * @Date: Created in 2020-08-06 17:41
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/sentinel")
public class SentinelController {
    @RequestMapping("/mesg1")
    public String message1(){
        return "message1";
    }


    @RequestMapping("/mesg2")
    public String message2(){
        return "message2";
    }

    @RequestMapping("/mesg3")
    @SentinelResource("mesg3")
    public String message3(String name,Integer age){

        return "message3="+name+"_"+age;
    }
}
