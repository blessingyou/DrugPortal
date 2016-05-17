package com.versionsystem.service.repo.role;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import com.versionsystem.persistence.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long>,QueryDslPredicateExecutor<Role> {

	public Role findById(long id);
	
	
}
