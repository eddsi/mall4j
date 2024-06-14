package com.yami.shop.bean.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName(value = "user_collect_document")
@Accessors(chain = true)
public class UserCollectDocument implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String userId;
    private Long documentId;
    private LocalDateTime createTime;

    // getters and setters
}