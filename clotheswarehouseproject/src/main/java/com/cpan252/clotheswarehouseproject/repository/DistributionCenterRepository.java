package com.cpan252.clotheswarehouseproject.repository;

import com.cpan252.clotheswarehouseproject.model.DistributionCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DistributionCenterRepository extends JpaRepository<DistributionCenter, Long> {
    DistributionCenter findByName(String name);

    @Query("SELECT dc FROM DistributionCenter dc LEFT JOIN FETCH dc.itemsAvailable WHERE dc.id = :id")
    DistributionCenter findByIdWithItems(@Param("id") Long id);
}
