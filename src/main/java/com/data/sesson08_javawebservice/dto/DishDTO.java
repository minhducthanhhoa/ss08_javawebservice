package com.data.sesson08_javawebservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DishDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Size(max = 255, message = "Description can be up to 255 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be >= 0")
    private Double price;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "AVAILABLE|UNAVAILABLE", message = "Status must be either AVAILABLE or UNAVAILABLE")
    private String status;

    @NotNull(message = "Image is required")
    private MultipartFile image;
}
