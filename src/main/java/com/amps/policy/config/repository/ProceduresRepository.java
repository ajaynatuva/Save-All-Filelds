package com.amps.policy.config.repository;

import com.amps.policy.config.model.Procedures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProceduresRepository extends JpaRepository<Procedures, Integer> {
	@Query(value = "select * from policy.policy_procedures where policy_id =:id order by action_fk,cpt_from,dos_from asc" ,nativeQuery = true)
    List<Procedures> findByPolicyId(@Param("id") int id);
}