package com.amps.policy.config.repository;

import com.amps.policy.config.model.ProductTypeLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeLookUpRepository extends JpaRepository<ProductTypeLookUp, Integer> {

}
