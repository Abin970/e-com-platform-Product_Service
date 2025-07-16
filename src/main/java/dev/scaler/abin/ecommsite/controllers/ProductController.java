package dev.scaler.abin.ecommsite.controllers;

import dev.scaler.abin.ecommsite.dtos.CategoryDto;
import dev.scaler.abin.ecommsite.dtos.ProductDto;
import dev.scaler.abin.ecommsite.models.Category;
import dev.scaler.abin.ecommsite.models.Product;
import dev.scaler.abin.ecommsite.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    IProductService productService;

    @RequestMapping("/products")
    public List<Product> getProducts() {
        //Dummy Response
        Product product = new Product();
        product.setId(1L);
        product.setPrice(1000D);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        return productList;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId){
        if(productId <= 0){
            throw new IllegalArgumentException("Product id is not found");
        }
        Product product = productService.getProductById(productId);
        if(product == null) return ResponseEntity.notFound().build();

        ProductDto productDto = from(product);

        return new ResponseEntity<>(productDto, HttpStatus.OK);

    }

    @PostMapping("products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = from(productDto);
        Product output = productService.createProduct(product);
        if (output == null) return null;
        return from(output);
    }

    @PutMapping("/products/{id}")
    public ProductDto replaceProduct(@PathVariable Long id,@RequestBody ProductDto productDto) {
        Product product = from(productDto);
        Product output = productService.replaceProduct(product,id);
        if(output == null) return null;
        return  from(output);
    }

    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setId(productDto.getCategory().getId());
            category.setName(productDto.getCategory().getName());
            product.setCategory(category);
        }
        return product;
    }

    private ProductDto from (Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if (product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }
}
