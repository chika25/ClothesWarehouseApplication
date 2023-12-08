package com.cpan252.clotheswarehouseproject.repository;

import com.cpan252.clotheswarehouseproject.model.DistributionCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistributionCenterRepository extends JpaRepository<DistributionCenter, Long> {
    DistributionCenter findByName(String name);
    Optional<DistributionCenter> findById(Long id);
}
