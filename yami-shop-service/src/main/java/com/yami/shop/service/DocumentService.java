package com.yami.shop.service;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yami.shop.bean.dto.CreateDocumentDTO;
import com.yami.shop.bean.dto.DocumentSearchDTO;
import com.yami.shop.bean.dto.document.AuditDocumentDTO;
import com.yami.shop.bean.model.Document;
import com.yami.shop.bean.vo.document.DocumentPageVO;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.service
 * @Project：mall4j
 * @name：DocumentService
 * @Date：2024/6/8 23:41
 * @Filename：DocumentService
 * @Description:
 */

public interface DocumentService extends IService<Document> {
    void createDocument(CreateDocumentDTO createDocumentDTO,String userId);

    IPage<DocumentPageVO> getDocumentPage(DocumentSearchDTO documentSearchDTO,String userId);

    void collectDocument(Long documentId, String userId);

    String upload(MultipartFile file);

    void auditDocument(AuditDocumentDTO auditDocumentDTO);

}