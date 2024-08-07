/*
 * Copyright (c) 2018-2999 广州市蓝海创新科技有限公司 All rights reserved.
 *
 * https://www.mall4j.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.yami.shop.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yami.shop.bean.app.dto.CategoryDto;
import com.yami.shop.bean.model.Category;
import com.yami.shop.common.response.ServerResponseEntity;
import com.yami.shop.service.CategoryService;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author lanhai
 */
@RestController
@RequestMapping("/category")
@Tag(name = "分类接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 分类信息列表接口
     */
    @GetMapping("/categoryInfo")
    @Operation(summary = "分类信息列表", description = "获取所有的产品分类信息")
    public ServerResponseEntity<List<CategoryDto>> categoryInfo() {
        List<Category> categories = categoryService.list();
        List<CategoryDto> categoryDtos = BeanUtil.copyToList(categories, CategoryDto.class);
        return ServerResponseEntity.success(categoryDtos);
    }


}