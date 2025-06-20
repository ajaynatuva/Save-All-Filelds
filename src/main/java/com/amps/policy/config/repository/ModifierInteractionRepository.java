package com.amps.policy.config.repository;

import com.amps.policy.config.model.ModifierInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModifierInteractionRepository extends JpaRepository<ModifierInteraction, Integer> {

}
