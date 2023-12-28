package com.tollplaza.Tollplaza.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tollplaza.Tollplaza.entity.TollMRole;
import com.tollplaza.Tollplaza.entity.TollMUser;

@Repository
public interface TollMRepository extends JpaRepository<TollMUser,String> {

	@Query(value="select * from toll_m_user",nativeQuery = true)
	List<TollMUser> findAllDetails();
	
	
	@Query(value="select * from toll_m_user tu where tu.email_id=:emailId",nativeQuery = true)
	Optional<TollMUser> findByEmailId(@Param("emailId")String emailId);
	

}
