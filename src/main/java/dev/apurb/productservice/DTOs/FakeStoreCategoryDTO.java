package dev.apurb.productservice.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.apurb.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class FakeStoreCategoryDTO {
//    @JsonProperty("MyArray")
//    public ArrayList<String> myArray;

    private Long id;
    private String title;
    public Category toCategory(){
        Category category = new Category();
        category.setId(getId());
        category.setTitle(getTitle());
        return category;
    }
}

