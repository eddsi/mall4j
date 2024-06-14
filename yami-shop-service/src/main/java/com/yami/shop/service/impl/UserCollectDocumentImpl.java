package com.yami.shop.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yami.shop.bean.model.UserCollectDocument;
import com.yami.shop.dao.UserCollectDocumentMapper;
import com.yami.shop.service.UserCollectDocumentService;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.service.impl
 * @Project：mall4j
 * @name：UserCollectDocumentImpl
 * @Date：2024/6/10 13:50
 * @Filename：UserCollectDocumentImpl
 * @Description:
 */
@Service
public class UserCollectDocumentImpl extends ServiceImpl<UserCollectDocumentMapper, UserCollectDocument> implements
        UserCollectDocumentService {
}