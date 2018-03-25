package com.example.controller;

import com.example.entity.ProductCategory;
import com.example.entity.ProductInfo;
import com.example.exception.SellException;
import com.example.form.CategoryForm;
import com.example.form.ProductForm;
import com.example.service.ProductCategoryService;
import com.example.utils.KeyUtil;
import com.lly835.bestpay.rest.type.Get;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by qidd on 2018-3-25
 */
@Controller
@RequestMapping("/seller/category")
public class SellCategoryController {

    @Autowired
    private ProductCategoryService categoryService;


    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }

    /**
     * 展示类目
     *
     * @param categoryId
     * @param map
     * @return
     */

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {

        if (categoryId != null) {
            ProductCategory category = categoryService.findOne(categoryId);
            map.put("category", category);

        }
        return new ModelAndView("category/index", map);
    }

    /**
     * 更新或保存类目
     * 更新或
     *
     * @param categoryform
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("save")
    public ModelAndView save(@Valid CategoryForm categoryform, BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("message", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");

            return new ModelAndView("common/error", map);
        }

        ProductCategory category = new ProductCategory();
        try {


            if (categoryform.getCategoryId() != null) {
                category = categoryService.findOne(categoryform.getCategoryId());
            }
            BeanUtils.copyProperties(categoryform, category);
            categoryService.save(category);
        } catch (SellException e) {
            map.put("message", e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }
}
