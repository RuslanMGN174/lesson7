package ru.knyazev.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.knyazev.lesson7.entites.Product;
import ru.knyazev.lesson7.entites.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductDTO> findAll() {
        return repository.findAll().stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Optional<ProductDTO> findById(long id) {
        return repository.findById(id)
                .map(ProductDTO::new);
    }

    @Transactional
    @Override
    public void save(ProductDTO product) {
        repository.save(new Product(product));
    }

    @Transactional
    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
