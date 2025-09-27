package com.hmall.pay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "hm.pay")
public class PayConfig {
    private String aliPayAppId;
    private String aliPayPrivateKey;
    private String aliPayPublicKey;
}
