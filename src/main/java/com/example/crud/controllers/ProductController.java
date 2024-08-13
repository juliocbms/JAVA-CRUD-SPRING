package com.example.crud.controllers;


import com.example.crud.domain.product.ProductRepository;
import com.example.crud.domain.product.RequestProduct;
import com.example.crud.domain.product.product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository repository;


    @GetMapping
    public ResponseEntity getAllProducts(){

        var allProducts = repository.findAllByActiveTrue();

        return ResponseEntity.ok(allProducts);
    }
    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data){
        product newProduct = new product(data);
        repository.save(newProduct);
        return  ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct( @RequestBody @Valid RequestProduct data){
        Optional<product> optionalProduct = repository.findById(data.id());
        if (optionalProduct.isPresent()){
            product product = optionalProduct.get();
            product.setName(data.name());
            product.setPrice_inc_cents(data.price_inc_cents());
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public  ResponseEntity deleteProduct(@PathVariable String id){
        Optional<product> optionalProduct = repository.findById(id);

        if (optionalProduct.isPresent()){
            product product = optionalProduct.get();
            product.setActive(false);
return  ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
