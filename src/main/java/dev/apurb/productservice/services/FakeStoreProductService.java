package dev.apurb.productservice.services;

import dev.apurb.productservice.DTOs.FakeStoreCategoryDTO;
import dev.apurb.productservice.DTOs.FakeStoreProductDTO;
import dev.apurb.productservice.models.Category;
import dev.apurb.productservice.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
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
    @Override
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
    @Override
    public List<Product> getAllProductinACategory(String title){
        FakeStoreProductDTO[] response = restTemplate.getForObject(
                "http://fakestoreapi.com/products/category/" + title , FakeStoreProductDTO[].class
        );
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO : response) {
            products.add(fakeStoreProductDTO.toProduct());
        }
        return products;
    }

    @Override
    public Product updateProduct(Long id, FakeStoreProductDTO fakeStoreProductDTO){
        HttpEntity<FakeStoreProductDTO> requestEntitiy = new HttpEntity<FakeStoreProductDTO>(fakeStoreProductDTO);
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/{id}",
                HttpMethod.PUT,
                requestEntitiy,
                FakeStoreProductDTO.class,
                id
        );
        FakeStoreProductDTO response = responseEntity.getBody();
        return response.toProduct();
    }

    @Override
    public Product deleteProductById(Long id){
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/{id}",
                HttpMethod.DELETE,
                null,
                FakeStoreProductDTO.class,
                id
        );
        FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();
        return fakeStoreProductDTO.toProduct();
    }
    @Override
    public List<String> getAllCategories(){
        String[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                String[].class);
        return Arrays.asList(response);
    }
}
