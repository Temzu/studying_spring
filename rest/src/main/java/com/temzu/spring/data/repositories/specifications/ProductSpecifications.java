package com.temzu.spring.data.repositories.specifications;

import com.temzu.spring.data.model.entities.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.stream.Collectors;

public class ProductSpecifications {
    private static Specification<Product> priceLesserOrEqualsThan(int maxPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);    }

    private static Specification<Product> priceGreaterOrEqualsThan(int minPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    private static Specification<Product> priceBetween(int first, int second) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(root.get("price"), first, second);
    }

    private static Specification<Product> titleContaining(String titlePart) {
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), String.format("%%%s%%", titlePart.toLowerCase()));
    }



    public static Specification<Product> build(MultiValueMap<String, String> params) {
        Specification<Product> spec = Specification.where(null);

        if (params.containsKey("min_price") && !params.getFirst("min_price").isBlank()) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(Integer.parseInt(params.getFirst("min_price"))));
        }
        if (params.containsKey("max_price") && !params.getFirst("max_price").isBlank()) {
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(Integer.parseInt(params.getFirst("max_price"))));
        }
        if (params.containsKey("between") && !params.get("between").stream().allMatch(String::isBlank)) {
            List<String> between = params.get("between").stream().limit(2).collect(Collectors.toList());
            spec = spec.and(ProductSpecifications.priceBetween(Integer.parseInt(between.get(0)), Integer.parseInt(between.get(1))));
        }
        if (params.containsKey("title") && !params.getFirst("title").isBlank()) {
            spec = spec.and(ProductSpecifications.titleContaining(params.getFirst("title")));
        }
        return spec;
    }
}
