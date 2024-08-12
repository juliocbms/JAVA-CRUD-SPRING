package com.example.crud.domain.product;

import jakarta.persistence.*;
import lombok.*;

@Table(name="product")
@Entity(name="product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class product {
@Id
@GeneratedValue(strategy =  GenerationType.UUID)
    private String id;

private  String name;

private Integer price_inc_cents;

public product(RequestProduct requestProduct){
    this.name = requestProduct.name();
    this.price_inc_cents = requestProduct.price_inc_cents();
}

}
