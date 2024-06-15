package com.yami.shop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yami.shop.bean.dto.CreateDocumentDTO;
import com.yami.shop.bean.dto.DocumentSearchDTO;
import com.yami.shop.bean.vo.document.DocumentPageVO;
import com.yami.shop.common.response.ServerResponseEntity;
import com.yami.shop.security.api.model.YamiUser;
import com.yami.shop.service.DocumentService;
import com.yami.shop.security.api.util.SecurityUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.api.controller
 * @Project：mall4j
 * @name：DocumentController
 * @Date：2024/6/10 00:15
 * @Filename：DocumentController
 * @Description:
 */
@RestController
@RequestMapping("/p/document")
@Tag(name = "文档接口")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping
    @Operation(summary = "新建文档", description = "新建文档")
    public ServerResponseEntity<String> createDocument(@RequestBody CreateDocumentDTO createDocumentDTO) {
        YamiUser user = SecurityUtils.getUser();
        documentService.createDocument(createDocumentDTO,user.getUserId());
        return ServerResponseEntity.success();
    }

    @PostMapping("/upload")
    @Operation(summary = "上传文档", description = "上传文档")
    public ServerResponseEntity<String> uploadDocument(MultipartFile file) {
        return ServerResponseEntity.success(documentService.upload(file));
    }


    @Operation(summary = "查看文档分页", description = "查看文档分页")
    @GetMapping
    public ServerResponseEntity<IPage<DocumentPageVO>> getDocumentPage(DocumentSearchDTO documentSearchDTO) {
        YamiUser user = SecurityUtils.getUser();
        return ServerResponseEntity.success(documentService.getDocumentPage(documentSearchDTO, user.getUserId()));
    }
    @Operation(summary = "收藏文档", description = "收藏文档")
    @PostMapping("/collect")
    public ServerResponseEntity<String> collectDocument(Long documentId) {
        YamiUser user = SecurityUtils.getUser();
        documentService.collectDocument(documentId, user.getUserId());
        return ServerResponseEntity.success();
    }

}