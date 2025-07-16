package dev.scaler.abin.ecommsite.services;

import dev.scaler.abin.ecommsite.clients.FakeStoreApiClient;
import dev.scaler.abin.ecommsite.dtos.FakeStoreProductDto;
import dev.scaler.abin.ecommsite.models.Category;
import dev.scaler.abin.ecommsite.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements IProductService
{

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;


    @Override
    public Product getProductById(Long id) {
         RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreResponseEntity = restTemplate
                .getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto = fakeStoreResponseEntity.getBody();

        if(fakeStoreProductDto !=null &&
            fakeStoreResponseEntity.getStatusCode().is2xxSuccessful())
                return from(fakeStoreProductDto);

        return null;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = from(product);
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                restTemplate.postForEntity("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDtoResponse = fakeStoreProductDtoResponseEntity.getBody();

        if(fakeStoreProductDtoResponse != null && fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful())
                return from(fakeStoreProductDtoResponse);
        return null;
    }
    @Override
    public Product replaceProduct(Product input,Long id) {
        FakeStoreProductDto fakeStoreProductDtoInput = from(input);
        FakeStoreProductDto output = fakeStoreApiClient.replaceFakeStoreProduct(fakeStoreProductDtoInput,id);
        if(output==null) return null;
        return from(output);
    }


    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    private FakeStoreProductDto from(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());

        if(product.getCategory() != null){
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
