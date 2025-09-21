package com.hmall.user.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.util.Assert;

import java.security.KeyPair;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /**
     * // 也可以：本地开发兜底：如果密钥库不存在，生成内存RSA密钥对，避免启动失败
     * @param properties
     * @return
     */
    @Bean
    public KeyPair keyPair(JwtProperties properties){
        Assert.notNull(properties.getLocation(), "hm.jwt.location 未配置");
        Assert.hasText(properties.getPassword(), "hm.jwt.password 未配置");
        Assert.hasText(properties.getAlias(), "hm.jwt.alias 未配置");
        // 获取秘钥工厂
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(
                        properties.getLocation(),
                        properties.getPassword().toCharArray());
        //读取钥匙对
        // 从密钥库读取钥匙对
        return keyStoreKeyFactory.getKeyPair(
                properties.getAlias(),
                properties.getPassword().toCharArray());
    }
}
