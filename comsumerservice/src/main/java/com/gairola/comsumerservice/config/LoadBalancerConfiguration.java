package com.gairola.comsumerservice.config;



import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.web.client.RestTemplate;


import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancerConfiguration {
@Bean
  @LoadBalanced
  public RestTemplate restTemplate() 
  {
       return new RestTemplate();
  }
}