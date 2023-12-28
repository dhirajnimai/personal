package com.tollplaza.Tollplaza.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tollplaza.Tollplaza.entity.TollMRole;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface TollMRoleRepository extends JpaRepository<TollMRole, Integer> {

	@Query(value="select * from toll_m_role tu where tu.roll_name=:rollName",nativeQuery = true)
	Optional<TollMRole> findByRoleName(@Param("rollName") String rollName);

	

	

	

}
