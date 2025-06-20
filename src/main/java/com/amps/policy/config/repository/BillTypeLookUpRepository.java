package com.amps.policy.config.repository;

import com.amps.policy.config.model.BillTypeLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillTypeLookUpRepository extends JpaRepository<BillTypeLookUp, String> {
	
	@Query(value="SELECT * FROM source.bill_type_lkp WHERE bill_type_lkp.bill_type=:billType",nativeQuery = true)
	List<BillTypeLookUp> findByBillType(@Param("billType") String billType);

}
