package com.hmall.pay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Component
@Validated
@RefreshScope
@ConfigurationProperties(prefix = "hm.pay")
public class PayConfig {
    @NotBlank(message = "hm.pay.aliPayAppId 未配置")
    private String aliPayAppId;

    @NotBlank(message = "hm.pay.aliPayPrivateKey 未配置")
    private String aliPayPrivateKey;

    @NotBlank(message = "hm.pay.aliPayPublicKey 未配置")
    private String aliPayPublicKey;
}
