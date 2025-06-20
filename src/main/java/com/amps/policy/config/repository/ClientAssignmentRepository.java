package com.amps.policy.config.repository;

import java.util.List;

import com.amps.policy.config.dto.ClientAssignmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amps.policy.config.model.ClientAssignment;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAssignmentRepository extends JpaRepository<ClientAssignment, Integer> {
//    List<ClientAssignmentDTO> findByPolicyId(Integer policy_id);

    @Query(value = "SELECT * FROM policy.client_assignment where policy_id =:policy_id", nativeQuery = true)
    List<ClientAssignment> findByPolicyId(@Param("policy_id") int policy_id );
     static List<ClientAssignment> saveAll(List<ClientAssignment> clientAssignments) {
        return ClientAssignmentRepository.saveAll(clientAssignments);
    }
}
