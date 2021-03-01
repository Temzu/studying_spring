package com.temzu.jpa_hiber.services;

import com.temzu.jpa_hiber.JpaHiberPart2Application;
import com.temzu.jpa_hiber.entities.Customer;
import com.temzu.jpa_hiber.entities.Product;
import com.temzu.jpa_hiber.repositories.CustomerRepository;
import com.temzu.jpa_hiber.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ShopService {
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setConsumerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Set<Product> getProductsByConsumerId(Long id) {
        return Set.copyOf(customerRepository.getCustomerById(id).getProducts());
    }

    public Set<Customer> getCustomersByProductId(Long id) {
        return Set.copyOf(productRepository.getProductById(id).getCustomers());
    }

    public int getCostProductAtTimeOfPurchase(Long id, LocalDate localDate) {
        return 2;
    }

    private static ConfigurableApplicationContext context;
    public static void main(String[] args) {
        context = SpringApplication.run(JpaHiberPart2Application.class, args);
        ShopService shopService = context.getBean(ShopService.class);
        System.out.println(shopService.getCustomersByProductId(1L));
        System.out.println(shopService.getProductsByConsumerId(3L));
        System.out.println(shopService.getProductsByConsumerId(4L));

    }

}
