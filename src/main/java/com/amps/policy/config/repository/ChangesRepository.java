package com.amps.policy.config.repository;

import com.amps.policy.config.model.Changes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangesRepository extends JpaRepository<Changes, Integer> {

	@Query(value = "select * from policy.policy_changes where policy_id_fk in (select policy_id from policy.policy "
			+ " where policy_id =:id) order by updated_on desc", nativeQuery = true)
	List<Changes> findChangesById(@Param("id") int id);

	// for procs tab
	@Query(value = "select * from policy.policy_changes where policy_id_fk in (select policy_id from policy.policy \r\n"
			+ " where policy_id =:id) and is_open_b=1", nativeQuery = true)
	List<Changes> GetOpenBChangesDataById(@Param("id") int id);

	
}
