package com.github.nurkholik.ddi.repository;

import com.github.nurkholik.ddi.repository.model.Groupx;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends JpaRepository<Groupx, Integer> {
    @Query("FROM Groupx a WHERE a.category_id = :catId ORDER BY a.name")
    Page<Groupx> findGroupByCategory(@Param("catId") int catId, Pageable pageable);
}