package dev.apurb.productservice.DTOs;

import dev.apurb.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCategoryDTO {
    private String message;
    public Category toCategory(){
        Category category = new Category();
        category.setTitle(getMessage());
        return category;
    }
}
