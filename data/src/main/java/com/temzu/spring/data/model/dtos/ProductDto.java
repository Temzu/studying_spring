package com.temzu.spring.data.model.dtos;

import com.temzu.spring.data.model.Category;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private int price;
    private Category category;

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategoryDto() {
        return category;
    }

    public void setCategoryDto(Category category) {
        this.category = category;
    }
}
