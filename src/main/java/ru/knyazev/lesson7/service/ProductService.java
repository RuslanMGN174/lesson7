package ru.knyazev.lesson7.service;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDTO> findAll();

    Optional<ProductDTO> findById(long id);

    void save(ProductDTO product);

    void delete(long id);
}
