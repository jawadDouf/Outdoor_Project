package com.example.productService.controllers;


import com.example.productService.exceptions.BadRequestException;
import com.example.productService.exceptions.NotFoundException;
import com.example.productService.helpers.ResponseHandler;
import com.example.productService.model.enteties.*;
import com.example.productService.repositories.ProductRepo;
import com.example.productService.services.ProductService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.productService.payload.*;


import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/products")
public class ProductController {


    private ProductService productService;

    private ResponseHandler responseHandler;


    public ProductController(ProductService productService, ResponseHandler responseHandler) {
        this.productService = productService;
        this.responseHandler = responseHandler;
    }

    @PostMapping()
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequest productRequest){
        try {
            //save product to database
            productService.save(productRequest.toProduct());
            //Add image to database
            try {
                productService.createImage(productRequest.getProductImage());
            } catch (Exception e) {
                throw new BadRequestException("Product image not saved");
            }
        }catch (Exception e){
            throw new BadRequestException("Product not saved");
        }
        return responseHandler.generateResponse("Product saved successfully",HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id){

        try {
            return new ResponseEntity<>(productService.getOneProduct(id),HttpStatus.OK);
        }catch (Exception e){
            throw new NotFoundException("Product not found");
        }

    }


    //Get all products from the database
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
        try {
            return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
        }catch (Exception e) {
            throw new NotFoundException("No products found");
        }
    }

    //Delete a product from the database
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
        return responseHandler.generateResponse("Product deleted successfully",HttpStatus.OK);
    }

}
