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
    public List<ru.knyazev.lesson7.service.ProductDTO> findAll() {
        return repository.findAll().stream()
                .map(ru.knyazev.lesson7.service.ProductDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ru.knyazev.lesson7.service.ProductDTO> findWithFilter(String productFilter) {
        return repository.findProductByTitleLike(productFilter).stream()
                .map(ru.knyazev.lesson7.service.ProductDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Optional<ru.knyazev.lesson7.service.ProductDTO> findById(long id) {
        return repository.findById(id)
                .map(ru.knyazev.lesson7.service.ProductDTO::new);
    }

    @Transactional
    @Override
    public void save(ru.knyazev.lesson7.service.ProductDTO product) {
        Product productToSave = new Product(product);
        repository.save(productToSave);
        if (product.getId() == null){
            product.setId(productToSave.getId());
        }
    }

    @Transactional
    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}