package com.example.task_9_1.config;

import com.example.task_9_1.service.DevProfile;
import com.example.task_9_1.service.ProductionProfile;
import com.example.task_9_1.service.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(prefix = "netology", name = "profile", havingValue = "dev")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(prefix = "netology", name = "profile", havingValue = "prod")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
