package com.yami.shop.bean.dto.document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.bean.dto.document
 * @Project：mall4j
 * @name：AduitDocumentDTO
 * @Date：2024/6/22 20:11
 * @Filename：AduitDocumentDTO
 * @Description:
 */
@Data
@Schema(name = "AuditDocumentDTO", description = "审核文档DTO")
public class AuditDocumentDTO {

    @Schema(description = "文档ID", required = true)
    private Long documentId;

    /**
     * 当状态为2时才会显示
     */
    @Schema(description = "审核状态 1不通过 2通过", required = true)
    private Integer status;
}