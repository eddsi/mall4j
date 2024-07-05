package com.yami.shop.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yami.shop.bean.model.Sector;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.dao
 * @Project：mall4j
 * @name：SectorMapper
 * @Date：2024/7/6 01:30
 * @Filename：SectorMapper
 * @Description:
 */
@Mapper
public interface SectorMapper extends BaseMapper<Sector> {
}