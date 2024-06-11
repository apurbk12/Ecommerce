package dev.apurb.productservice.services;

import dev.apurb.productservice.models.Category;
import dev.apurb.productservice.models.Product;

import java.util.List;

public interface ProductService {

    public Product getSingleProduct(Long id);

    public Product createProduct(String title, String description, String image, String Category, double price);

    public List<Product> getAllProducts();

    public List<Category> getAllCategories();
}
