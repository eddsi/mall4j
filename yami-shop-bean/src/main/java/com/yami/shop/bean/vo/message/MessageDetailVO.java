package com.yami.shop.bean.vo.message;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yami.shop.bean.dto.message.MessageBoardDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author：T3yrs
 * @Package：com.yami.shop.bean.vo.message
 * @Project：mall4j
 * @name：MessageDetailVO
 * @Date：2024/8/3 03:49
 * @Filename：MessageDetailVO
 * @Description:
 */
@Data
@Schema(name = "MessageDetailVO", description = "查看留言板详情VO")
public class MessageDetailVO {

    @Schema(description = "本身的ID")
    private Long id;

    @Schema(description = "回复的留言板ID")
    private Long messageId;

    @Schema(description = "留言内容")
    private String content;

    @Schema(description = "留言人")
    private String creator;

    @Schema(description = "留言时间")
    private String createTime;

    @Schema(description = "回复/默认展示两条")
    private IPage<MessageBoardDTO> children;
}