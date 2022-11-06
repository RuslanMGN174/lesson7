package ru.knyazev.lesson7.entites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.knyazev.lesson7.service.ProductDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "productByTitle", query = "from Product p where p.title=:title"),
        @NamedQuery(name = "allProducts", query = "from Product")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, unique = true, nullable = false)
    private String title;

    @NotNull
    @Column
    private BigDecimal cost;


    public Product(String title, BigDecimal cost) {
        this.title = title;
        this.cost = cost;
    }

    public Product(ProductDTO product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
    }
}