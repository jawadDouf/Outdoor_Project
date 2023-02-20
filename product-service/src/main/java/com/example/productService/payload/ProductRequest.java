package com.example.productService.payload;


import com.example.productService.model.enteties.Product;
import com.example.productService.model.enteties.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@Scope(value = "singleton")
public class ProductRequest {

    private String name;

    private Double price;

    private ProductImage productImage;

//    public Product toProduct(){
//        return Product.builder()
//                .name(name)
//                .price(price)
//                .build();
//    }

}
