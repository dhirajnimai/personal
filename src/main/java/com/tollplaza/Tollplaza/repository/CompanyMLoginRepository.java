package com.tollplaza.Tollplaza.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tollplaza.Tollplaza.entity.CompanyMasterLoginEntity;

@Repository
public interface CompanyMLoginRepository extends JpaRepository<CompanyMasterLoginEntity,Integer> {
	
	@Query("from CompanyMasterLoginEntity cr where cr.userId.userId=:userId")
	public Optional<CompanyMasterLoginEntity> findByUserId(@Param("userId")String userId);
	
	

}
