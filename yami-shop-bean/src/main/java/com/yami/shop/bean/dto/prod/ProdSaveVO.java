package com.yami.shop.bean.dto.prod;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.bean.dto.prod
 * @Project：mall4j
 * @name：ProdSaveVO
 * @Date：2024/7/23 06:29
 * @Filename：ProdSaveVO
 * @Description:
 */
@Data
@Schema(description = "商品保存DTO")
public class ProdSaveVO {

    /**
     * 商品名称
     */
    @NotBlank(message = "文档名称不能为空")
    @Size(max = 200, message = "文档名称长度应该小于{max}")
    private String prodName;


    /**
     * 商品价格
     */
    @Schema(description = "请输入商品现价")
    private Double price;

    /**
     * 商品价格
     */
    @NotNull(message = "请输入商品原价")
    private Double oriPrice;

    /**
     * 简要描述,卖点等
     */
    @Schema(description = "商品描述")
    @Size(max = 500, message = "商品描述长度应该小于{max}")
    private String brief;


    @Schema(description = "原始的S3的key")
    private String originalKey;

    @Schema(description = "关键字")
    private String keyWord;

    /**
     * 商品分类
     */
    @NotNull(message = "请选择商品分类")
    private Long categoryId;


    /**
     * content 商品详情
     */
    private String content;


}