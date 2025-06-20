package com.amps.policy.config.repository;

import com.amps.policy.config.model.ModifierPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModifierPriorityRepository extends JpaRepository<ModifierPriority, Integer> {

}