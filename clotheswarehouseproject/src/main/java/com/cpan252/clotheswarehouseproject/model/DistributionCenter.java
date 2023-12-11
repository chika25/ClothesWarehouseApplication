package com.cpan252.clotheswarehouseproject.model;

import com.cpan252.clotheswarehouseproject.model.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DistributionCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "distributionCenter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Item> itemsAvailable;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    public List<Item> getItemsAvailable(){return this.itemsAvailable;}

    public void setItemsAvailable(List<Item> itemsAvailable) {
        this.itemsAvailable = itemsAvailable;
        if (itemsAvailable != null) {
            for (Item item : itemsAvailable) {
                item.setDistributionCenter(this);
            }
        }
    }
    public Long getId() {
        return id;
    }

    public Item findItemById(Long itemId) {
        return itemsAvailable.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }
    @Override
    public String toString() {
        return "DistributionCenter{id=" + id + ", name='" + name + "'}";
    }
}
