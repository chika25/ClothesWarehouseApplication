package com.cpan252.distributioncentremanagersapi.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ItemDto {
    private Long id;
    private String name;
    private String brand;
    private Integer yearOfCreation;
    private BigDecimal price;
    private LocalDate createdAt;
    private int quantity;

    public String getName() {
        return name;
    }
    public String getBrand() {
        return brand;
    }

    public Integer getYearOfCreation() {
        return yearOfCreation;
    }
    public BigDecimal getPrice(){
        return price;
    }

    public LocalDate getCreatedAt(){
        return createdAt;
    }

    public int getQuantity(){
        return quantity;
    }
    @Override
    public String toString() {
        return "ItemDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", yearOfCreation=" + yearOfCreation +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", quantity=" + quantity +
                '}';
    }
}
