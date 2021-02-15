package com.temzu.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private Integer id;
    private String title;
    private int cost;

    @Override
    public String toString() {
        return String.format("%-3d %-10s %d", id, title, cost);
    }
}
