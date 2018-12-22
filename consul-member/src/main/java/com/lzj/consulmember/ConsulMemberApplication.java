package com.lzj.consulmember;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient //适用于zk consul
public class ConsulMemberApplication {

    @RequestMapping("/getMember")
    public String getMember() {
        return "调用会员服务";
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsulMemberApplication.class, args);
    }
}
