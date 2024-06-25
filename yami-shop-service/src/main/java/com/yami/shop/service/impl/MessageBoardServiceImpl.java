package com.yami.shop.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yami.shop.bean.dto.message.CreateMessageBoardDTO;
import com.yami.shop.bean.dto.message.MessageBoardDTO;
import com.yami.shop.bean.model.MessageBoard;
import com.yami.shop.common.bean.BasePage;
import com.yami.shop.dao.MessageBoardMapper;
import com.yami.shop.service.MessageBoardService;

import cn.hutool.core.bean.BeanUtil;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.service.impl
 * @Project：mall4j
 * @name：MessageBoardServieImpl
 * @Date：2024/6/22 21:22
 * @Filename：MessageBoardServieImpl
 * @Description:
 */
@Service
public class MessageBoardServiceImpl extends ServiceImpl<MessageBoardMapper, MessageBoard> implements
        MessageBoardService {
    @Override
    public void createMessageBoard(CreateMessageBoardDTO createMessageBoardDTO, String bizUserId) {
        MessageBoard messageBoard = new MessageBoard();
        messageBoard.setContent(createMessageBoardDTO.getContent());
        messageBoard.setCreator(bizUserId);
        messageBoard.setCreateTime(LocalDateTime.now());
        messageBoard.setMessageId(createMessageBoardDTO.getMessageId());
        save(messageBoard);
    }

    @Override
    public IPage<MessageBoardDTO> getMessageBoard(BasePage basePage) {
        Page<MessageBoard> page = lambdaQuery().orderByDesc(MessageBoard::getCreateTime)
                .page(new Page<>(basePage.getCurrent(), basePage.getSize()));
        if (page.getRecords().size() == 0) {
            return new Page<>();
        }
        return page.convert(v -> {
            MessageBoardDTO messageBoardDTO = BeanUtil.copyProperties(v, MessageBoardDTO.class);
            List<MessageBoard> list = lambdaQuery().eq(MessageBoard::getMessageId, v.getMessageId())
                    .orderByDesc(MessageBoard::getCreateTime).last("limit 2").list();
            if (list.size() > 0) {
                List<MessageBoardDTO> children = new ArrayList<>();
                list.forEach(m -> {
                    MessageBoardDTO child = BeanUtil.copyProperties(m, MessageBoardDTO.class);
                    children.add(child);
                });
                messageBoardDTO.setChildren(children);
            } else {
                messageBoardDTO.setChildren(null);
            }
            return messageBoardDTO;

        });
    }

    @Override
    public IPage<MessageBoardDTO> getMessageBoardChild(BasePage basePage, Long messageId) {
        Page<MessageBoard> page = lambdaQuery().eq(MessageBoard::getMessageId, messageId)
                .orderByDesc(MessageBoard::getCreateTime).page(new Page<>(basePage.getCurrent(), basePage.getSize()));
        return  page.convert(v -> {
            MessageBoardDTO messageBoardDTO = BeanUtil.copyProperties(v, MessageBoardDTO.class);
            return messageBoardDTO;
        });}

}