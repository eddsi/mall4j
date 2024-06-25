package com.yami.shop.bean.dto.message;

import java.util.List;

import com.yami.shop.bean.model.MessageBoard;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.bean.dto.message
 * @Project：mall4j
 * @name：MessageBoardDTO
 * @Date：2024/6/23 04:42
 * @Filename：MessageBoardDTO
 * @Description:
 */
@Data
@Schema(name = "MessageBoardDTO", description = "查看留言板DTO")
public class MessageBoardDTO {

    @Schema(description = "留言板ID")
    private Long messageId;

    @Schema(description = "留言内容")
    private String content;

    @Schema(description = "留言人")
    private String creator;

    @Schema(description = "留言时间")
    private String createTime;

    @Schema(description = "回复/默认展示两条")
    private List<MessageBoardDTO> children;
}