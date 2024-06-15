package com.yami.shop.bean.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.bean.dto
 * @Project：mall4j
 * @name：CreateDocumentDTO
 * @Date：2024/6/10 00:17
 * @Filename：CreateDocumentDTO
 * @Description:
 */
@Data
@Schema(name = "CreateDocumentDTO", description = "新建文档")
public class CreateDocumentDTO {

    @Schema(name = "name", description = "文档名称")
    private String name;

    @Schema(name = "content", description = "文档内容")
    private String descriptions;

    @Schema(name = "keyword", description = "关键字")
    private String keyword;

    @Schema(name = "type", description = "文档关键字")
    private Integer type;

    @Schema(name = "key", description = "s3的key")
    private String key;

    @Schema(name = "prices", description = "价格")
    private Double prices;


}