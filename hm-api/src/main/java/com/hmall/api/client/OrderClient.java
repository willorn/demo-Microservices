package com.hmall.api.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("order-service")
public interface OrderClient {
    
    
    
    
}
