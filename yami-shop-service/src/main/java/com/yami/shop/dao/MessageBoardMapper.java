package com.yami.shop.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yami.shop.bean.model.MessageBoard;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.dao
 * @Project：mall4j
 * @name：MessageBoardMapper
 * @Date：2024/6/22 21:22
 * @Filename：MessageBoardMapper
 * @Description:
 */
@Mapper
public interface MessageBoardMapper extends BaseMapper<MessageBoard> {
}