package cn.itcast.feign.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;

import feign.Logger;

@FeignClient("userservice")
public class DefaultFeignConfiguration {
    
    @Bean
    Logger.Level logLevel() {
        return Logger.Level.BASIC;
    }
}
