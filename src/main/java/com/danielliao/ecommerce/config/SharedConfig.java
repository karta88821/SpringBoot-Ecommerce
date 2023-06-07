package com.danielliao.ecommerce.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.danielliao.ecommerce.order", "com.danielliao.ecommerce.product"})
@EntityScan(basePackages = {"com.danielliao.ecommerce.order", "com.danielliao.ecommerce.product"})
public class SharedConfig {
    
}