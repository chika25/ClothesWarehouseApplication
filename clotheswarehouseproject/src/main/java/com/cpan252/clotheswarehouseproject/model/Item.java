package com.cpan252.clotheswarehouseproject.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;

    @NotBlank
    private String brand;

    @Min(2000)
    private Integer year_of_creation;

    @DecimalMin(value = "0.1", inclusive = true)
    private BigDecimal price;

    @Builder.Default
    private LocalDate createdAt = LocalDate.now();

    @Min(1)
    private int quantity;
    @ManyToOne(optional = true)
    @JoinColumn(name = "distribution_center_id")
    private DistributionCenter distributionCenter;
}
