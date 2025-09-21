package com.hmall.api.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@FeignClient("cart-service")
public interface CartClient {
    
    @DeleteMapping()
    void removeByItemIds(@RequestParam("ids") Set<Long> itemIds);
}
