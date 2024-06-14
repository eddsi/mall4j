package com.yami.shop.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.api.config
 * @Project：mall4j
 * @name：MinioClientConfig
 * @Date：2024/6/10 00:32
 * @Filename：MinioClientConfig
 * @Description:
 */
@Component
@Slf4j
public class MinioClientConfig {
    @Autowired
    private MinioConfig minioConfig;

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioConfig.getEndpoint())
                .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey())
                .build();
        log.info("minio启动成功...");
        return minioClient;
    }

}