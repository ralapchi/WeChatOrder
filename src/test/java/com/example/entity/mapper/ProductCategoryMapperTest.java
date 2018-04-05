package com.example.entity.mapper;

import com.example.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by qidd on 2018-4-1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insert() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("category_Name", "桃色");
        map.put("category_Type", 101);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1, result);

    }

    @Test
    public void insertByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(4656465);
        productCategory.setCategoryName("桃花");
        productCategory.setCategoryType(101);

        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1, result);

    }


    @Test
    public void findByCategoryType(){

    }
}