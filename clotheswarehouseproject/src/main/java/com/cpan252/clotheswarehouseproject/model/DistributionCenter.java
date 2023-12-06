package com.cpan252.clotheswarehouseproject.model;

import com.cpan252.clotheswarehouseproject.model.Item;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DistributionCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;


//    @ElementCollection
    @OneToMany(mappedBy = "distributionCenter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itemsAvailable;
    @NotNull
    private double latitude;

    @NotNull
    private double longitude;
}
