package dev.apurb.productservice.Controller;

import dev.apurb.productservice.DTOs.CreateProductRequestDTO;
import dev.apurb.productservice.DTOs.FakeStoreProductDTO;
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
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable ("id") Long id ,@RequestBody FakeStoreProductDTO fakeStoreProductDTO){
        return productService.updateProduct(id , fakeStoreProductDTO);
    }
    @DeleteMapping("/products/{id}")
    public Product deleteProductById(@PathVariable ("id") Long id){
        return productService.deleteProductById(id);
    }

    @GetMapping("/products/category/{title}")
    public ResponseEntity<List<Product>> getAllProductinACategory(@PathVariable("title") String title){
        List<Product> responseData = productService.getAllProductinACategory(title);
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(responseData, HttpStatusCode.valueOf(202));
        return responseEntity;
    }
    @GetMapping("/products/categories")
    public List<String> getAllCategories(){
        return productService.getAllCategories();
    }
}
