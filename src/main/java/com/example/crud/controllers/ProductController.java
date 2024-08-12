package com.example.crud.controllers;


import com.example.crud.domain.product.ProductRepository;
import com.example.crud.domain.product.RequestProduct;
import com.example.crud.domain.product.product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository repository;


    @GetMapping
    public ResponseEntity getAllProducts(){

        var allProducts = repository.findAll();

        return ResponseEntity.ok(allProducts);
    }
    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data){
        product newProduct = new product(data);
        repository.save(newProduct);
        return  ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable String id, @RequestBody @Valid RequestProduct data){
        product newProduct = new product(data);
        product product = repository.getReferenceById(data.id());
        product.setName(data.name());
        product.setPrice_inc_cents(data.price_inc_cents());
        repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

}
