package com.data.sesson08_javawebservice.controller;

import com.data.sesson08_javawebservice.dto.DishDTO;
import com.data.sesson08_javawebservice.entity.ApiResponse;
import com.data.sesson08_javawebservice.entity.Dish;
import com.data.sesson08_javawebservice.service.DishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dishes")
public class DishController {
    @Autowired
    private DishService dishService;

    private final String uploadPath = "uploads/";

    @PostMapping
    public ResponseEntity<ApiResponse<Dish>> createDish(@ModelAttribute @Valid DishDTO dto) throws IOException {
        Dish dish = new Dish();
        dish.setName(dto.getName());
        dish.setDescription(dto.getDescription());
        dish.setPrice(dto.getPrice());
        dish.setStatus(dto.getStatus());

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + dto.getImage().getOriginalFilename();
            Path path = Paths.get(uploadPath + fileName);
            Files.createDirectories(path.getParent());
            Files.copy(dto.getImage().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            dish.setImage(path.toString());
        }

        Dish saved = dishService.createDish(dish);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dish created", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Dish>> updateDish(@PathVariable Long id, @ModelAttribute @Valid DishDTO dto) throws IOException {
        Dish dish = new Dish();
        dish.setName(dto.getName());
        dish.setDescription(dto.getDescription());
        dish.setPrice(dto.getPrice());
        dish.setStatus(dto.getStatus());

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + dto.getImage().getOriginalFilename();
            Path path = Paths.get(uploadPath + fileName);
            Files.createDirectories(path.getParent());
            Files.copy(dto.getImage().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            dish.setImage(path.toString());
        }

        Dish updated = dishService.updateDish(id, dish);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dish updated", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dish deleted", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Dish>>> getAllDishes() {
        List<Dish> list = dishService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(true, "Dishes fetched", list));
    }
}
