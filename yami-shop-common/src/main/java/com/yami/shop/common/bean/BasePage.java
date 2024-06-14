package com.yami.shop.common.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.common.bean
 * @Project：mall4j
 * @name：BasePage
 * @Date：2024/6/10 14:00
 * @Filename：BasePage
 * @Description:
 */
@Data
@Schema(description = "分页基础类")
public class BasePage {

    @Schema(description = "当前页")
    private Integer current = 1;

    @Schema(description = "每页显示条数")
    private Integer size = 10;
}