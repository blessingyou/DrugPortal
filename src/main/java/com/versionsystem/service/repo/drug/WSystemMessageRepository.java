package com.versionsystem.service.repo.drug;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.versionsystem.persistence.model.UserId;
import com.versionsystem.persistence.model.WSystemMessage;
import com.versionsystem.persistence.model.WWebServiceKey;

public interface WSystemMessageRepository extends JpaRepository<WSystemMessage, Long>,QueryDslPredicateExecutor<WSystemMessage> {

	WSystemMessage findByMsgType(String msgType);
	

	/*
	@Query("select u from userid u where u.role.role = :role")
	Page<UserId> findByRole(@Param("role") Integer role, Pageable pageable);*/
}
