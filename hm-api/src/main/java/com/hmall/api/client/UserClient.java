package com.hmall.api.client;

import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.constraints.NotNull;

@FeignClient("user-service")
public interface UserClient {


    void deductMoney(@NotNull(message = "支付密码") String pw, Integer amount);
}
