/*
 * Copyright (c) 2018-2999 广州市蓝海创新科技有限公司 All rights reserved.
 *
 * https://www.mall4j.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.yami.shop.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yami.shop.bean.app.dto.ProductDto;
import com.yami.shop.bean.app.dto.TagProductDto;
import com.yami.shop.bean.dto.SearchProdDto;
import com.yami.shop.bean.model.Product;
import com.yami.shop.common.util.PageParam;

/**
 * 商品
 *
 * @author lanhai
 */
public interface ProductService extends IService<Product> {

    /**
     * 保存商品
     *
     * @param product 商品信息
     */
    void saveProduct(Product product);

    /**
     * 更新商品
     *
     * @param product 商品信息
     */
    void updateProduct(Product product, Product dbProduct);

    /**
     * 根据商品id获取商品信息
     */
    Product getProductByProdId(Long prodId);

    Product getProductByProdIdAndUserName(Long prodId, String userName);

    /**
     * 根据商品id删除商品
     */
    void removeProductByProdId(Long prodId);

    /**
     * 根据商品id删除缓存
     */
    void removeProductCacheByProdId(Long prodId);

    /**
     * 根据上架时间倒序分页获取商品
     */
    IPage<ProductDto> pageByPutAwayTime(IPage<ProductDto> page);

    /**
     * 根据标签分页获取商品
     */
    IPage<ProductDto> pageByTagId(Page<ProductDto> page, Long tagId);

    /**
     * 分页获取销量较高商品
     */
    IPage<ProductDto> moreBuyProdList(Page<ProductDto> page);

    /**
     * 根据分类id分页获取商品列表
     */
    IPage<ProductDto> pageByCategoryId(Page<ProductDto> page, Long categoryId, String keyWord);

    /**
     * 根据商品名称
     */
    IPage<SearchProdDto> getSearchProdDtoPageByProdName(Page page, String prodName, Integer sort, Integer orderBy);

    /**
     * 分组获取商品列表
     */
    List<TagProductDto> tagProdList();

    /**
     * 分页获取收藏商品
     */
    IPage<ProductDto> collectionProds(PageParam page, String userId);

    String upload(MultipartFile file);
}