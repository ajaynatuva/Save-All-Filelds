package com.amps.policy.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amps.policy.config.model.States;
import org.springframework.stereotype.Repository;

@Repository
public interface StatesRepository extends JpaRepository<States, String>{
	@Query(value = "select * from states", nativeQuery = true)
	List<States> getStatesData();
}
