package dev.scaler.abin.ecommsite.repo;

import dev.scaler.abin.ecommsite.models.Category;
import dev.scaler.abin.ecommsite.models.Product;
import dev.scaler.abin.ecommsite.repository.CategoryRepo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional
    public void testLoading(){
        Optional<Category> categoryOptional = categoryRepo.findById(2L);
        for(Product p : categoryOptional.get().getProducts()){
            System.out.println(p.getName());
        }
    }
}
