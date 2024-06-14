package com.yami.shop.bean.dto;

import com.yami.shop.common.bean.BasePage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.bean.dto
 * @Project：mall4j
 * @name：DocumentSearchDTO
 * @Date：2024/6/10 03:42
 * @Filename：DocumentSearchDTO
 * @Description:
 */
@Data
@Schema(description = "文档搜索")
public class DocumentSearchDTO extends BasePage {

    @Schema(description = "关键字")
    private String keyword;

    @Schema(description = "行业分类")
    private String sectorName;

    @Schema(description = "文档名")
    private String documentName;

    @Schema(description = "文档类型")
    private Integer type;
}