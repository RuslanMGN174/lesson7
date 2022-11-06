package ru.knyazev.lesson7.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.knyazev.lesson7.service.CartService;

@RestController
@RequestMapping("/api/v1/cart")
public class CartResource {

    private final CartService cartService;
    @Autowired
    public CartResource(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{productId}/product")
    public void addProductInCart(@PathVariable("productId") Long productId,
                                  @RequestParam("qty") Integer qty) {
        cartService.addProductInCartQty(productId, qty);
    }

    @PostMapping("/remove/{productId}/product")
    public void removeProductInCart(@PathVariable("productId") Long productId,
                                     @RequestParam("qty") Integer qty) {
        cartService.removeProductInCart(productId, qty);
    }

    @DeleteMapping("/cart/remove")
    public void removeAllProduct() {
        cartService.removeAllProduct();
    }
}
