package ru.knyazev.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.knyazev.lesson7.service.ProductDTO;
import ru.knyazev.lesson7.service.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listPage(
            Model model,
            @RequestParam("productFilter") Optional<String> productFilter) {

        List<ProductDTO> productDTOList;

        if (productFilter.isPresent() && !productFilter.get().isEmpty()){
            productDTOList = productService.findWithFilter(productFilter.get());
        } else {
            productDTOList = productService.findAll();
        }

        model.addAttribute("products", productDTOList);
        return "product";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("product") ProductDTO product, BindingResult result) {
        if (result.hasErrors()) {
            return "product_form";
        }
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "product_form";
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService
                .findById(id)
                .orElseThrow(NotFoundException::new));
        return "product_form";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id) {
        productService.delete(id);
        return "redirect:/product";
    }
}