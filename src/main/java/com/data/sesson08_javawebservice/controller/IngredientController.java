package com.data.sesson08_javawebservice.controller;

import com.data.sesson08_javawebservice.entity.Ingredient;
import com.data.sesson08_javawebservice.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Ingredient ingredient) {
        Ingredient saved = ingredientService.create(ingredient);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Ingredient ingredient) {
        Ingredient updated = ingredientService.update(id, ingredient);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ingredientService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Ingredient deleted successfully"));
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAll() {
        return ResponseEntity.ok(ingredientService.findAll());
    }
}
