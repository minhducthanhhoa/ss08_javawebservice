package com.data.sesson08_javawebservice.repository;

import com.data.sesson08_javawebservice.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
