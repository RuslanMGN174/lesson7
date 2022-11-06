package ru.knyazev.lesson7.service;

public interface CartService {

    void addProductInCartQty(long productId, int qty);

    void removeProductInCart(long productId, int qty);

    void removeAllProduct();
}
