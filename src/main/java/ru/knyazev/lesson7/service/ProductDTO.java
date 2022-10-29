package ru.knyazev.lesson7.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import ru.knyazev.lesson7.entites.Product;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class ProductDTO {

    private Long id;

    @NotEmpty
    private String title;

    @DecimalMin(value = "0", inclusive = true)
    @Digits(integer = 3, fraction = 2)
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
