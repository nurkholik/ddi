package com.github.nurkholik.ddi.service;

import com.github.nurkholik.ddi.entity.CategoryDTO;
import com.github.nurkholik.ddi.repository.CategoryRepository;
import com.github.nurkholik.ddi.repository.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryDTO> getCategories() {
        return categoryRepository.findAll().stream().sorted(Comparator.comparing(Category::getOrder_number))
                .map(cat -> new CategoryDTO(cat.getId(), cat.getName(), cat.getImage_link()))
                .collect(Collectors.toList());
    }

}
