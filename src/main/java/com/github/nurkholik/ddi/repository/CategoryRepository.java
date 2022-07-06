package com.github.nurkholik.ddi.repository;

import com.github.nurkholik.ddi.repository.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}