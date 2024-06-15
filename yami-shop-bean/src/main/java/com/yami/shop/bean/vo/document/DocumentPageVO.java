package com.yami.shop.bean.vo.document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.bean.vo.document
 * @Project：mall4j
 * @name：DocumentPageVO
 * @Date：2024/6/10 03:33
 * @Filename：DocumentPageVO
 * @Description:
 */
@Schema(description = "文档分页")
@Data
public class DocumentPageVO {

    @Schema(description = "文档id")
    private Long id;

    @Schema(description = "文档名")
    private String name;

    @Schema(description = "文档描述")
    private String descriptions;

    @Schema(description = "文档关键字")
    private String keyword;

    @Schema(description = "行业分类")
    private Integer type;
    /**
     * 行业名
     */
    @Schema(description = "行业名")
    private String sectorName;

    @Schema(description = "下载地址")
    private String link;

    @Schema(description = "价格")
    private Double prices;

    @Schema(description = "是否收藏")
    private Boolean isCollect;

    @Schema(description = "是否购买")
    private Boolean isBuy;

    @Schema(description = "作者名")
    private String authorName;

}