package com.cpan252.distributioncentremanagersapi.repository;

import org.springframework.data.repository.CrudRepository;
import com.cpan252.distributioncentremanagersapi.model.ManagerModel;
public interface ManagerRepository extends CrudRepository<ManagerModel, Long> {
    ManagerModel findByUsername(String username);
}
