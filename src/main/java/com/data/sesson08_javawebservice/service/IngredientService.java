package com.data.sesson08_javawebservice.service;

import com.data.sesson08_javawebservice.advice_controller.ResourceNotFoundException;
import com.data.sesson08_javawebservice.entity.Ingredient;
import com.data.sesson08_javawebservice.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient create(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(Long id, Ingredient updatedIngredient) {
        Ingredient existing = ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found with ID: " + id));

        existing.setName(updatedIngredient.getName());
        existing.setStock(updatedIngredient.getStock());
        existing.setExpiry(updatedIngredient.getExpiry());
        existing.setImage(updatedIngredient.getImage());

        return ingredientRepository.save(existing);
    }

    public void delete(Long id) {
        if (!ingredientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ingredient not found with ID: " + id);
        }
        ingredientRepository.deleteById(id);
    }

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }
}
