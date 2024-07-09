package com.yami.shop.bean.dto.sector;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author：T3yes
 * @Package：com.yami.shop.bean.dto.sector
 * @Project：mall4j
 * @name：CreateSectorDTO
 * @Date：2024/7/6 01:41
 * @Filename：CreateSectorDTO
 * @Description:
 */
@Schema(name = "CreateSectorDTO", description = "添加行业DTO")
@Data
public class CreateSectorDTO {

    @Schema(description = "行业名", required = true)
    private String name;
}