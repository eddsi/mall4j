package com.yami.shop.bean.model;


import java.io.Serializable;

import java.time.LocalDate;
import lombok.Data;

import org.hibernate.validator.constraints.Length;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @TableName document
 */
@Data
@TableName(value = "document")
public class Document implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 文档名
     */
    @Length(max = 255, message = "编码长度不能超过255")
    private String name;
    /**
     * 描述
     */
    @Length(max = -1, message = "编码长度不能超过-1")
    private String descriptions;
    /**
     * 关键字
     */
    @Length(max = 128, message = "编码长度不能超过128")
    private String keyword;
    /**
     * 行业分类
     */
    private Integer type;
    /**
     * 行业名
     */
    @Length(max = 255, message = "编码长度不能超过255")
    private String sectorName;
    /**
     * s3文件的key
     */
    @TableField(value = "`key`")
    @Length(max = 255, message = "编码长度不能超过255")
    private String key;
    /**
     * 价格
     */
    private Double prices;
    /**
     * 作者
     */
    private Long owner;

    /**
     * minio的图片保存地址
     */
    private String documentPhotos;
    /**
     * 审核状态0未通过1已通过
     */
    private Integer status;
    /**
     * 权重（排序使用
     */
    private Integer weights;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 创建者名
     */
    @Length(max = 255, message = "编码长度不能超过255")
    private String createName;
    /**
     * 创建时间
     */
    private LocalDate createTime;
    /**
     * 更新时间
     */
    private LocalDate updateTime;

}