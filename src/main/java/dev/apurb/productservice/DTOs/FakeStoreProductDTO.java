package dev.apurb.productservice.DTOs;

import dev.apurb.productservice.models.Category;
import dev.apurb.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {

    private Long id;

    private String title;

    private String description;

    private double price;

    private String image;

    private String category;

    public Product toProduct(){
        Product product = new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setDescription(getDescription());
        product.setImageurl(getImage());

        Category category = new Category();
        category.setTitle(getCategory());
        product.setCategory(category);

        return product;
    }
}
