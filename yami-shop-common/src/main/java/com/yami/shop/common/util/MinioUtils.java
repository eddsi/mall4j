package com.yami.shop.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.yami.shop.common.config.MinioConfig;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MinioUtils {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioConfig minioConfig;

    /**
     * description: 上传MultipartFile文件
     *
     * @return: java.lang.String
     */
    public String uploadMultipartFileDown(MultipartFile file) {
        //判断bucket是否存在
        //判断目录路径是否存在
        String fileName = file.getOriginalFilename();
        String[] split = fileName.split("\\.");
        if (split.length > 1) {
            fileName = split[0] + "_" + System.currentTimeMillis() + "." + split[1];
        } else {
            fileName = fileName + System.currentTimeMillis();
        }
        InputStream in = null;
        try {
            in = file.getInputStream();
            //此方法为动态获取contentType,可以直接预览
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(minioConfig.getBucket())
                    .object(fileName)
                    .stream(in, in.available(), -1)
                    //如果为流,默认不预览直接下载
                    .contentType("application/octet-stream")
                    .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileName;
    }

    public Boolean existBucket(String name) {
        boolean exists;
        try {
            exists = minioClient.bucketExists(
                    BucketExistsArgs.builder()
                            .bucket(name)
                            .build());

            if (!exists) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder().
                                bucket(minioConfig.getBucket())
                                .build());
            }

        } catch (Exception e) {
            throw new RuntimeException();
        }

        return exists;

    }

    public void upload(MultipartFile file, String fileName) {
        try {
            InputStream inputStream = file.getInputStream();
            minioClient.putObject(PutObjectArgs
                    .builder()
                    .bucket(minioConfig.getBucket())
                    .object(fileName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public byte[] getObjectData(String objectName) {
        try {
            InputStream object = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(minioConfig.getBucket())
                            .object(objectName)
                            .build());

            return object.readAllBytes();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching object data", e);
        }
    }

    public String getObjectPreviewUrl(String objectName) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(minioConfig.getBucket())
                            .object(objectName)
                            .expiry(6, TimeUnit.HOURS)
                            .build());
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching object preview URL", e);
        }
    }

    public String upload(InputStream inputStream, String fileName, long size) {
        try {
            minioClient.putObject(PutObjectArgs
                    .builder()
                    .bucket(minioConfig.getBucket())
                    .object(fileName)
                    .stream(inputStream, size, -1)
                    .contentType("image/jpeg").build());
            return fileName;
        } catch (Exception e) {
            throw new RuntimeException("Error while uploading file to Minio", e);
        }
    }

}