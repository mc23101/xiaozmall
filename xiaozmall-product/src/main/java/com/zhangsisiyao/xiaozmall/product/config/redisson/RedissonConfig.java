package com.zhangsisiyao.xiaozmall.product.config.redisson;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedissonConfig{

    String host;

    String port;

    String password;

    int timeout;

    @Bean(destroyMethod = "shutdown")
    RedissonClient redissonClient() {
        Config config = new Config();
        config.setCodec(new StringCodec());
        System.out.println(port);
        System.out.println(timeout);
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress("redis://"+host+":"+port)
                .setTimeout(timeout);

        if(StringUtils.isNotEmpty(password)){
            serverConfig.setPassword(password);
        }

        return Redisson.create(config);
    }

}