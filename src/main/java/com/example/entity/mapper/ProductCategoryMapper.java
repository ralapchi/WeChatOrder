package com.example.entity.mapper;


import com.example.entity.ProductCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by qidd on 2018-4-1
 */
public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name,category_type) values(#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER)")
    int insertByMap(Map<String, Object> map);

    @Insert("insert into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER)")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type =#{categoryType}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    ProductCategory selectByCategoryType(Integer categoryType);


    @Select("select * from product_category where category_name =#{categoryName}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    List<ProductCategory> selectByCategoryName(String categoryName);


    @Update("update product_category set category_name = #{categoryName} where category_type =#{categoryType}")
    int updateByType(@Param("categoryType") String categoryType, @Param("categoryName") String categoryName);


    ProductCategory findByCategoryType(Integer categoryType);
}
