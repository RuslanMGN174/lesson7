package ru.knyazev.lesson7.service;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDTO> findAll();

    List<ProductDTO> findWithFilter(String productFilter);

    Optional<ProductDTO> findById(long id);

    void save(ProductDTO product);

    void delete(long id);
}
