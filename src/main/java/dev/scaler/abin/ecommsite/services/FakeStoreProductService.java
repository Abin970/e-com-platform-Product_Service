package dev.scaler.abin.ecommsite.services;

import dev.scaler.abin.ecommsite.models.Product;

import java.util.List;

public class FakeStoreProductService implements IProductService
{
    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
