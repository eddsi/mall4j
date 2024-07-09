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

    @Schema(name = "description", description = "文档简介")
    private String descriptions;

    @Schema(name = "keyword", description = "关键字")
    private String keyword;

    @Schema(name = "type", description = "文档类别")
    private Integer type;

    @Schema(name = "key", description = "s3的key（上传成功后服务器返回的json中的data字段）")
    private String key;

    @Schema(name = "price", description = "文档价格")
    private Double prices;


}