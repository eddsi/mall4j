package com.yami.shop.bean.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "message_board")
public class MessageBoard implements Serializable {

    @TableId
    // 主键ID
    private Long id;
    // 留言内容
    private String content;
    // 创建者用户名
    private String creator;
    // 回复的留言ID，如果是第一条，则没有
    private Long messageId;
    // 创建时间
    private LocalDateTime createTime;

    // getters and setters
}