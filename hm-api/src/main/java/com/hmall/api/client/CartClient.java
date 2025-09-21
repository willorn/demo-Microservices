package com.hmall.api.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Set;

@FeignClient("cart-service")
public interface CartClient {
    // 批量删除购物车中商品，对应 cart-service 的 DELETE /carts?ids=...
    @DeleteMapping("/carts")
    void removeByItemIds(@RequestParam("ids") Collection<Long> itemIds);
}
