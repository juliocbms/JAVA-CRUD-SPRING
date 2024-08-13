package com.example.crud.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<product,String> {
List <product> findAllByActiveTrue();
}
