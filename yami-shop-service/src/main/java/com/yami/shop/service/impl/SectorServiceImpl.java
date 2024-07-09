package com.yami.shop.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yami.shop.bean.model.Sector;
import com.yami.shop.dao.SectorMapper;
import com.yami.shop.service.SectorService;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.service.impl
 * @Project：mall4j
 * @name：SectorServiceImpl
 * @Date：2024/7/6 01:29
 * @Filename：SectorServiceImpl
 * @Description:
 */
@Service
public class SectorServiceImpl extends ServiceImpl<SectorMapper, Sector> implements SectorService {
}