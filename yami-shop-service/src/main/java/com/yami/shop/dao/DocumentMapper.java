package com.yami.shop.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yami.shop.bean.model.Document;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.dao
 * @Project：mall4j
 * @name：DocumentMapper
 * @Date：2024/6/9 23:42
 * @Filename：DocumentMapper
 * @Description:
 */
@Mapper
public interface DocumentMapper extends BaseMapper<Document> {
}