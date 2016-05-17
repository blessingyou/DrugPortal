package com.versionsystem.service.repo;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.versionsystem.persistence.model.UserId;

public interface UserRepository extends JpaRepository<UserId, Long>,QueryDslPredicateExecutor<UserId> {

	UserId findByUserId(String userId);
	List<UserId> findByMobileDeviceIdOrderBySecuritycodeGenTimeDesc(String regId);
	List<UserId> findByPassword(String password);
	UserId findByUserIdAndPassword(String userId,String password);
	Page<UserId> findByUserIdLike(String userId, Pageable pageable);

	/*
	@Query("select u from userid u where u.role.role = :role")
	Page<UserId> findByRole(@Param("role") Integer role, Pageable pageable);*/
}
