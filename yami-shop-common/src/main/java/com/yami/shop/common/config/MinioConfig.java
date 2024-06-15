package com.yami.shop.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.api.config
 * @Project：mall4j
 * @name：MinioConfig
 * @Date：2024/6/10 00:12
 * @Filename：MinioConfig
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    private String endpoint;

    private String accessKey;

    private String secretKey;

    private String bucket;

}