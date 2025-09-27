package com.hmall.pay.controller;

import com.hmall.common.domain.R;
import com.hmall.pay.config.PayConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("pay-config")
@RequiredArgsConstructor
public class PayConfigController {

    private final PayConfig payConfig;
    private final Environment environment;

    @GetMapping
    public R<Map<String, Object>> current() {
        Map<String, Object> data = new HashMap<>();
        data.put("aliPayAppId", payConfig.getAliPayAppId());
        data.put("aliPayPrivateKey", mask(payConfig.getAliPayPrivateKey()));
        data.put("aliPayPublicKey", mask(payConfig.getAliPayPublicKey()));
        data.put("profile", environment.getProperty("spring.profiles.active", "default"));
        data.put("timestamp", Instant.now().toString());
        return R.ok(data);
    }

    private String mask(String value) {
        if (value == null) return null;
        int len = value.length();
        if (len <= 8) return "******";
        return value.substring(0, 6) + "******" + value.substring(len - 4);
    }
}

