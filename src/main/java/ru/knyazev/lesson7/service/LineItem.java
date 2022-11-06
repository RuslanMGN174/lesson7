package ru.knyazev.lesson7.service;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.knyazev.lesson7.entites.Product;

@Getter
@Setter
@EqualsAndHashCode
public class LineItem {

    private ProductDTO product;

    private Integer qty;

    public LineItem(long productId, int qty) {
        this.product = new ProductDTO();
        this.product.setId(productId);
    }

    public LineItem(ProductDTO product, int qty) {
        this.product = product;
        this.qty = qty;
    }
}
