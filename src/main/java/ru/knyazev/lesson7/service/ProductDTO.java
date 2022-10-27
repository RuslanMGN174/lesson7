package ru.knyazev.lesson7.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.knyazev.lesson7.entites.Product;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class ProductDTO {

    private Long id;

    private String title;

    private BigDecimal cost;

    public ProductDTO(String title, BigDecimal cost) {
        this.title = title;
        this.cost = cost;
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
    }
}
