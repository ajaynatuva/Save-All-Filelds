package com.amps.policy.config.repository;

import com.amps.policy.config.model.LobLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LobLookUpRepository extends JpaRepository<LobLookUp, Integer> {

}
