package com.example.service;

import com.example.entity.ProductCategory;

import java.util.List;

/**
 * Created by qidd on 2018-3-10
 */
public interface ProductCategoryService {
    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    ProductCategory save(ProductCategory productCategory);

}
