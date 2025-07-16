package dev.scaler.abin.ecommsite.repo;

import dev.scaler.abin.ecommsite.repository.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductRepoTest {
    @Autowired
    private ProductRepo productRepo;

    @Test
    public void testQueries(){
        System.out.println(productRepo.fetchProductNameById(2L));
    }
}
