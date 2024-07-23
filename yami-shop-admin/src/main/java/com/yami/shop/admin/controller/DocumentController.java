package com.yami.shop.admin.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yami.shop.bean.dto.document.AuditDocumentDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Resource;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.admin.controller
 * @Project：mall4j
 * @name：DocumentController
 * @Date：2024/6/22 19:48
 * @Filename：DocumentController
 * @Description:
 */
@Schema(name = "DocumentController", description = "文档管理")
@RestController
@RequestMapping("/shop/document")
public class DocumentController {

    @Resource
    private DocumentService documentService;

    @Operation(summary = "审核文档", description = "审核文档")
    @PutMapping("/audit")
    public void auditDocument(@RequestBody AuditDocumentDTO auditDocumentDTO) {
        documentService.auditDocument(auditDocumentDTO);
    }


}