package dev.scaler.abin.ecommsite.services;

import dev.scaler.abin.ecommsite.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);

    Product createProduct(Product product);

    List<Product> getAllProducts();

}
