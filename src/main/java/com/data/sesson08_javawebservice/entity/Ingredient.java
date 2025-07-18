package com.data.sesson08_javawebservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "ingredients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ingredient name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Column(name = "ingredient_name", nullable = false, length = 100)
    private String name;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be >= 0")
    @Column(nullable = false)
    private Integer stock;

    @NotNull(message = "Expiry date is required")
    @FutureOrPresent(message = "Expiry date must be today or in the future")
    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiry;

    @Column(name = "image_url")
    private String image;
}
