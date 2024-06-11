package dev.apurb.productservice.services;

import dev.apurb.productservice.DTOs.FakeStoreCategoryDTO;
import dev.apurb.productservice.DTOs.FakeStoreProductDTO;
import dev.apurb.productservice.models.Category;
import dev.apurb.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long id){
        //FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject(
        //      "http://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);

        //return fakeStoreProductDTO.toProduct();
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity("http://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class);
        FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();
        return fakeStoreProductDTO.toProduct();
    }
    @Override
    public Product createProduct(String title , String description , String image , String category , double price ){
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(title);
        fakeStoreProductDTO.setDescription(description);
        fakeStoreProductDTO.setImage(image);
        fakeStoreProductDTO.setCategory(category);
        fakeStoreProductDTO.setPrice(price);
        FakeStoreProductDTO response = restTemplate.postForObject("http://fakestoreapi.com/products/",
                fakeStoreProductDTO, FakeStoreProductDTO.class);
        return response.toProduct();
    }
    public List<Product> getAllProducts(){
        FakeStoreProductDTO[] response = restTemplate.getForObject(
                "http://fakestoreapi.com/products", FakeStoreProductDTO[].class
        );
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO : response) {
            products.add(fakeStoreProductDTO.toProduct());
        }
        return products;
    }
    public List<Category> getAllCategories(){
        FakeStoreCategoryDTO[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories", FakeStoreCategoryDTO[].class
        );
        List<Category> categories = new ArrayList<>();
        for(FakeStoreCategoryDTO fakeStoreCategoryDTO : response) {
            categories.add(fakeStoreCategoryDTO.toCategory());
        }
        return categories;
    }
}
