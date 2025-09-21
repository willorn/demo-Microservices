package com.hmall.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@Data
@Validated
@ConfigurationProperties(prefix = "hm.jwt")
public class JwtProperties {
    @NotNull(message = "hm.jwt.location 未配置")
    private Resource location;

    @NotBlank(message = "hm.jwt.password 未配置")
    private String password;

    @NotBlank(message = "hm.jwt.alias 未配置")
    private String alias;

    @NotNull(message = "hm.jwt.tokenTTL 未配置")
    private Duration tokenTTL = Duration.ofMinutes(10);
}
