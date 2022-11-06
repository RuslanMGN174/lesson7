package ru.knyazev.lesson7.service;

import org.springframework.stereotype.Service;
import ru.knyazev.lesson7.controller.NotFoundException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CartServiceImpl implements CartService{
    private static CartServiceImpl instance;
    private Map<LineItem, Integer> lineItemsMap;

    private final ProductService productService;


    private CartServiceImpl(ProductService productService){
        this.productService = productService;
    }

    public static synchronized CartServiceImpl getInstance(ProductService productService) {
        if (instance == null) {
            instance = new CartServiceImpl(productService);
        }
        return instance;
    }


    @Override
    public void addProductInCartQty(long productId, int qty) {
        lineItemsMap = new ConcurrentHashMap<LineItem, Integer>();
        ProductDTO product = productService.findById(productId).orElseThrow(NotFoundException::new);
        LineItem key = new LineItem(product, qty);
        lineItemsMap.merge(key, qty, Integer::sum);
    }

    @Override
    public void removeProductInCart(long productId, int qty) {
        LineItem key = new LineItem(productId, qty);

        Integer count = lineItemsMap.get(key);
        if (count != null) {
            if (count < qty) {
                lineItemsMap.remove(key);
            } else {
                lineItemsMap.put(key, count - qty);
            }
        }
    }

    @Override
    public void removeAllProduct() {
        lineItemsMap = null;
    }
}
