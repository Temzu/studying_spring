package com.temzu.spring_boot.controllers;

import com.temzu.spring_boot.exceptions.ProductNotFoundException;
import com.temzu.spring_boot.model.Product;
import com.temzu.spring_boot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/{id}/info/")
    public String showProduct(Model model, @PathVariable("id") Long id) throws ProductNotFoundException {
        Optional<Product> product = productService.getProductById(id);
//        if (product.isEmpty()) {
//            model.addAttribute("error", new ProductNotFoundException(id));
//            return "error";
//        }
        if (product.isEmpty())
            throw new ProductNotFoundException(id);
        model.addAttribute("selectedProduct", product.get());
        return "info";
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(@PathVariable("id") Long id) {
        productService.removeById(id);
        return "redirect:/products";
    }

    @PostMapping("/add")
    public String addOrUpdateProduct(@ModelAttribute Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/products";
    }
}
