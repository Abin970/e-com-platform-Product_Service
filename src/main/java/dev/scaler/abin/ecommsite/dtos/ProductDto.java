package dev.scaler.abin.ecommsite.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;

    private String name;

    private Double price;

    private String imageUrl;

    private CategoryDto category;
}
