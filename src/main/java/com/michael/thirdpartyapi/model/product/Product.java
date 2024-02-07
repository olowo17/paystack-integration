package com.michael.thirdpartyapi.model.dto.product;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class Product {
  private Long id;
  private String title;
  private String description;
  private Double price;
  private Double discountPercentage;
  private Double rating;
  private Integer stock;
  private String brand;
  private String category;
  private String thumbnail;
  private ArrayList<String> images = new ArrayList<String>();

}
