package com.data.sesson08_javawebservice.service;

import com.data.sesson08_javawebservice.advice_controller.ResourceNotFoundException;
import com.data.sesson08_javawebservice.entity.Dish;
import com.data.sesson08_javawebservice.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    public Dish createDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish updateDish(Long id, Dish newDish) {
        Dish existing = dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found"));
        existing.setName(newDish.getName());
        existing.setDescription(newDish.getDescription());
        existing.setPrice(newDish.getPrice());
        existing.setStatus(newDish.getStatus());
        existing.setImage(newDish.getImage());
        return dishRepository.save(existing);
    }

    public void deleteDish(Long id) {
        if (!dishRepository.existsById(id)) {
            throw new ResourceNotFoundException("Dish not found");
        }
        dishRepository.deleteById(id);
    }

    public Dish getDish(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found"));
    }
}
