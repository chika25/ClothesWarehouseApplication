package com.cpan252.clotheswarehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cpan252.clotheswarehouseproject.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByBrand(String brand);
    List<Item> findByName(String name);
}
