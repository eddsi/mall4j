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
import com.yami.shop.common.bean.BasePage;
import com.yami.shop.common.response.ServerResponseEntity;
import com.yami.shop.security.api.util.SecurityUtils;
import com.yami.shop.service.MessageBoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class MessageBoardController {

    @Resource
    private MessageBoardService messageBoardService;

    @Operation(summary = "留言，回复留言", description = "留言")
    @PostMapping
    public void createMessageBoard(@RequestBody CreateMessageBoardDTO createMessageBoardDTO) {
        String bizUserId = SecurityUtils.getUser().getBizUserId();
        messageBoardService.createMessageBoard(createMessageBoardDTO,bizUserId);
    }

    @Operation(summary = "查看留言", description = "查看留言")
    @GetMapping
    public ServerResponseEntity<IPage<MessageBoardDTO>> getMessageBoard(BasePage basePage) {
        return ServerResponseEntity.success(messageBoardService.getMessageBoard(basePage));
    }

    @GetMapping("/message/child")
    public ServerResponseEntity<IPage<MessageBoardDTO>> getMessageBoardChild(BasePage basePage,@RequestParam(required = true) Long messageId) {
        return ServerResponseEntity.success(messageBoardService.getMessageBoardChild(basePage,messageId));
    }

}