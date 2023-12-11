package com.cpan252.clotheswarehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpan252.clotheswarehouseproject.repository.DistributionCenterRepository;
import com.cpan252.clotheswarehouseproject.model.Item;
import com.cpan252.clotheswarehouseproject.model.DistributionCenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DistributionCenterService {
    @Autowired
    private DistributionCenterRepository distributionCenterRepository;

//    public void addItemToDistributionCenter(Long distributionCenterId, Item item) {
//        Optional<DistributionCenter> optionalCenter = distributionCenterRepository.findById(distributionCenterId);
//
//        optionalCenter.ifPresent(center -> {
//            if (center.getItemsAvailable() == null) {
//                center.setItemsAvailable(new ArrayList<>());
//            }
//            item.setDistributionCenter(center);  // Set the distribution center for the item
//            center.getItemsAvailable().add(item);
//            distributionCenterRepository.save(center);
//        });
//    }
public void addItemToDistributionCenter(Long distributionCenterId, List<Item> items) {
    Optional<DistributionCenter> optionalCenter = distributionCenterRepository.findById(distributionCenterId);

    optionalCenter.ifPresent(center -> {
        if (center.getItemsAvailable() == null) {
            center.setItemsAvailable(new ArrayList<>());
        }

        // Set the distribution center for each item and add to the distribution center
        for (Item item : items) {
            item.setDistributionCenter(center);
            center.getItemsAvailable().add(item);
        }

        distributionCenterRepository.save(center);
    });
}

}
