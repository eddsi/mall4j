package com.yami.shop.bean.model;


import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @TableName user_document
 */
@Data
@TableName(value = "user_buy")
public class UserBuy implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户id
     */
    private Long userId;

    private String userName;
    /**
     * 文档id
     */
    private Long prodId;
    /**
     * 创建时间
     */
    private Date createTime;


}