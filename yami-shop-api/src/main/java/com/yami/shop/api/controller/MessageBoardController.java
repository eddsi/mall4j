package com.yami.shop.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yami.shop.bean.dto.message.CreateMessageBoardDTO;
import com.yami.shop.bean.dto.message.MessageBoardDTO;
import com.yami.shop.bean.vo.message.MessageDetailVO;
import com.yami.shop.common.bean.BasePage;
import com.yami.shop.common.response.ServerResponseEntity;
import com.yami.shop.security.api.util.SecurityUtils;
import com.yami.shop.service.MessageBoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.api.controller
 * @Project：mall4j
 * @name：MessageBoardController
 * @Date：2024/6/22 21:39
 * @Filename：MessageBoardController
 * @Description:
 */
@Schema(name = "MessageBoardController", description = "留言板")
@RestController
@RequestMapping("/p/message-board")
@Tag(name = "留言板接口")
public class MessageBoardController {

    @Resource
    private MessageBoardService messageBoardService;

    @Operation(summary = "留言，回复留言", description = "留言")
    @PostMapping
    public ServerResponseEntity<String> createMessageBoard(@RequestBody CreateMessageBoardDTO createMessageBoardDTO) {
        String userName = SecurityUtils.getUser().getUserName();
        messageBoardService.createMessageBoard(createMessageBoardDTO, userName);
        return ServerResponseEntity.success();
    }

    @Operation(summary = "查看留言，只显示两层的子留言", description = "查看留言，只显示两层的子留言")
    @GetMapping
    public ServerResponseEntity<IPage<MessageBoardDTO>> getMessageBoard(BasePage basePage) {
        return ServerResponseEntity.success(messageBoardService.getMessageBoard(basePage));
    }

    @Operation(summary = "查看留言的子留言", description = "根据留言的消息ID查看其所有子留言")
    @GetMapping("/child")
    public ServerResponseEntity<MessageDetailVO> getMessageBoardChild(BasePage basePage,
            @RequestParam(required = true) Long messageId) {
        return ServerResponseEntity.success(messageBoardService.getMessageBoardChild(basePage, messageId));
    }

}