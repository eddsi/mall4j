package com.yami.shop.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yami.shop.bean.model.UserDocument;
import com.yami.shop.dao.UserDocumentMapper;
import com.yami.shop.service.UserDocumentService;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.service.impl
 * @Project：mall4j
 * @name：UserDocumentServiceImpl
 * @Date：2024/6/10 12:26
 * @Filename：UserDocumentServiceImpl
 * @Description:
 */
@Service
public class UserDocumentServiceImpl extends ServiceImpl<UserDocumentMapper, UserDocument> implements UserDocumentService {
}