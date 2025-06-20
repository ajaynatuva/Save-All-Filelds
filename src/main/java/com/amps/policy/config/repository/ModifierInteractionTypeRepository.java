package com.amps.policy.config.repository;

import com.amps.policy.config.model.ModifierInteractionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModifierInteractionTypeRepository extends JpaRepository<ModifierInteractionType, Integer> {

}
