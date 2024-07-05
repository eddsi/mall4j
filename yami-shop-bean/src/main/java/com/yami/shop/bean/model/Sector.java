package com.yami.shop.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.bean.model
 * @Project：mall4j
 * @name：Sector
 * @Date：2024/7/6 01:19
 * @Filename：Sector
 * @Description:
 */
@Data
@TableName("sector")
public class Sector {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 行业名
     */
    private String name;
}