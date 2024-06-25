package com.yami.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yami.shop.bean.dto.message.CreateMessageBoardDTO;
import com.yami.shop.bean.dto.message.MessageBoardDTO;
import com.yami.shop.bean.model.MessageBoard;
import com.yami.shop.common.bean.BasePage;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.service
 * @Project：mall4j
 * @name：MessageBoardService
 * @Date：2024/6/22 21:22
 * @Filename：MessageBoardService
 * @Description:
 */
public interface MessageBoardService extends IService<MessageBoard> {
    void createMessageBoard(CreateMessageBoardDTO createMessageBoardDTO,String bizUserId);

    IPage<MessageBoardDTO> getMessageBoard(BasePage basePage);

    IPage<MessageBoardDTO> getMessageBoardChild(BasePage basePage,Long messageId);  }