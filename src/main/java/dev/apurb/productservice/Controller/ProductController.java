package dev.apurb.productservice.Controller;

import dev.apurb.productservice.DTOs.CreateProductRequestDTO;
import dev.apurb.productservice.DTOs.ErrorDTO;
import dev.apurb.productservice.models.Category;
import dev.apurb.productservice.models.Product;
import dev.apurb.productservice.services.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDTO productRequestDTO){
        return productService.createProduct(
                productRequestDTO.getTitle(),
                productRequestDTO.getDescription(),
                productRequestDTO.getImage(),
                productRequestDTO.getCategory(),
                productRequestDTO.getPrice()
        );
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> responseData =  productService.getAllProducts();
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(responseData, HttpStatusCode.valueOf(202));
        return responseEntity;
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getSingleProduct(id);
    }
    @GetMapping("/products/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> responseData = productService.getAllCategories();
        ResponseEntity<List<Category>> responseEntity = new ResponseEntity<>(responseData, HttpStatusCode.valueOf(202));
        return responseEntity;
    }
    public void updateProduct(Long id, CreateProductRequestDTO productRequestDTO){

    }
    public void deleteProductById(Long id){

    }
    public void getAllProductinACategory(){

    }
}
