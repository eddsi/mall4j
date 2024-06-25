package com.yami.shop.bean.dto.message;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.bean.dto.message
 * @Project：mall4j
 * @name：CreateMessageBoardDTO
 * @Date：2024/6/22 22:12
 * @Filename：CreateMessageBoardDTO
 * @Description:
 */
@Data
@Schema(name = "CreateMessageBoardDTO", description = "创建留言板DTO")
public class CreateMessageBoardDTO {

    @Schema(description = "留言板ID,如果单独发的不需要填写")
    private Long messageId;

    @NotBlank
    @Schema(description = "留言内容", required = true)
    private String content;
}