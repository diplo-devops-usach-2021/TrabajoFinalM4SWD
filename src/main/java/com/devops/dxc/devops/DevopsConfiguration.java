package com.devops.dxc.devops;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = {"com.devops.dxc.devops.services", "com.devops.dxc.devops.model"})
public class DevopsConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
