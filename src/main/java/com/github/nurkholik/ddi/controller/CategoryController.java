package com.github.nurkholik.ddi.controller;

import com.github.nurkholik.ddi.entity.CategoryDTO;
import com.github.nurkholik.ddi.service.CategoryService;
import com.github.nurkholik.ddi.utils.Utils;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CommonsLog
@RestController
@RequestMapping(path = "/api/v1")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(path = "/categories")
    public List<CategoryDTO> getCategoryList() {
        log.info("Get Categories");
        List<CategoryDTO> res = categoryService.getCategories();
        log.info("Response : " + Utils.toJson(res));
        return res;
    }

}
