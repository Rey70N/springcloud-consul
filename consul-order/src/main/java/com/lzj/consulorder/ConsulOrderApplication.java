package com.lzj.consulorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsulOrderApplication {

    @Autowired
    private RestTemplate restTemplate;

    //两种调用方式：一种是采用服务别名方式调用，另一种是直接调用，使用别名去注册中心上获取对应的服务调用地址
    @RequestMapping("/getOrder01")
    public String getOrder01() {
        String serviceUrl = "http://consul-member/getMember";
        return restTemplate.getForObject(serviceUrl, String.class);
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsulOrderApplication.class, args);
    }


   /*

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/getOrder02")
    public String getOrder02() {
        //根据名称，在注册中心查找到具体url
        String serviceUrl = getServiceURL("consul-member")  + "/getMember";
        String result = restTemplate.getForObject(serviceUrl, String.class);
        return result;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    private String getServiceURL(String appName) {
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(appName);
        if(serviceInstanceList != null && !serviceInstanceList.isEmpty()) {
            return serviceInstanceList.get(0).getUri().toString();
        }

        return null;
    }*/


}
