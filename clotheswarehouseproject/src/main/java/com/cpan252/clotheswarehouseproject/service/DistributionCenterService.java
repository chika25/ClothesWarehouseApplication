package com.cpan252.clotheswarehouseproject.service;

import com.cpan252.clotheswarehouseproject.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpan252.clotheswarehouseproject.repository.DistributionCenterRepository;
import com.cpan252.clotheswarehouseproject.model.Item;
import com.cpan252.clotheswarehouseproject.model.DistributionCenter;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DistributionCenterService {

    private final DistributionCenterRepository distributionCenterRepository;
    private final ItemRepository itemRepository;

    public DistributionCenterService(DistributionCenterRepository distributionCenterRepository, ItemRepository itemRepository) {
        this.distributionCenterRepository = distributionCenterRepository;
        this.itemRepository = itemRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
//    public void addItemToDistributionCenter(Long distributionCenterId, List<Item> items) {
//        Optional<DistributionCenter> optionalCenter = distributionCenterRepository.findById(distributionCenterId);
//
//        optionalCenter.ifPresent(center -> {
//            if (center.getItemsAvailable() == null) {
//                center.setItemsAvailable(new ArrayList<>());
//            }
//
//            // Set the distribution center for each item and add to the distribution center
//            for (Item item : items) {
//                item.setDistributionCenter(center);
//                center.getItemsAvailable().add(item);
//            }
//
//            distributionCenterRepository.save(center);
//        });
//    }
    public void addItemToDistributionCenter(Long distributionCenterId, List<Item> items) {
        DistributionCenter distributionCenter = distributionCenterRepository.findById(distributionCenterId)
                .orElseThrow(() -> new RuntimeException("Distribution Center not found"));

        for (Item item : items) {
            item.setDistributionCenter(distributionCenter);
            distributionCenter.getItemsAvailable().add(item);
            itemRepository.save(item);
        }

        distributionCenterRepository.save(distributionCenter);
    }
}
