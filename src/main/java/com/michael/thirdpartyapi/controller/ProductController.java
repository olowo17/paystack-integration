package com.michael.thirdpartyapi.controller;

import com.michael.thirdpartyapi.model.dto.product.Product;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
//@AllArgsConstructor
@RequestMapping("/test")
public class ProductController {

//    private final WebClient.Builder webClient;
    WebClient webClient = WebClient.create("https://dummyjson.com");
    @GetMapping("/products/{id}")
   public ResponseEntity <?> getProductById (@PathVariable Long id){
        Product productResponse = webClient.get()
                .uri("/products/"+id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Product.class).block();
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PostMapping("/products/add")
    public ResponseEntity <?> addProduct (){
        Product newProduct = Product.builder()
                .title("Semolina")
                .brand("Olam")
                .category("Foodstuff")
                .description("food for household")
                .stock(34)
                .discountPercentage(11.2D)
                .rating(4.4)
                .build();

        Product productResponse = webClient.post()
                .uri("/products/add")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(newProduct), Product.class)
                .retrieve()
                .bodyToMono(Product.class).block();

        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);

    }
}
