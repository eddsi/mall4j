package com.yami.shop.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yami.shop.bean.model.UserBuy;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.dao
 * @Project：mall4j
 * @name：UserDocumentMapper
 * @Date：2024/6/10 12:22
 * @Filename：UserDocumentMapper
 * @Description:
 */
@Mapper
public interface UserBuyMapper extends BaseMapper<UserBuy> {
}