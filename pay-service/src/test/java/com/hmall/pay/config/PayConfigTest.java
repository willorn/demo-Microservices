package com.hmall.pay.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        // ensure no external Nacos import interferes
        "spring.config.import=",
        "spring.cloud.nacos.config.enabled=false",
        // avoid starting a DataSource/MyBatis in tests
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration,org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration"
})
@ActiveProfiles("test")
class PayConfigTest {

    @Autowired
    private PayConfig payConfig;

    @Test
    void shouldBindPropertiesFromTestProfile() {
        assertThat(payConfig.getAliPayAppId()).isEqualTo("test-app-id");
        assertThat(payConfig.getAliPayPrivateKey()).isEqualTo("test-private-key-1234567890");
        assertThat(payConfig.getAliPayPublicKey()).isEqualTo("test-public-key-abcdefg");
    }
}
