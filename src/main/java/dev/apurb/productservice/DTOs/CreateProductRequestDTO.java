package dev.apurb.productservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDTO {

    private String title;

    private String description;

    private String image;

    private String category;

    private double price;

}
