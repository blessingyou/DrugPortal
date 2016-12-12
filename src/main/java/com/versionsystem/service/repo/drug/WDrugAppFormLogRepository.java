package com.versionsystem.service.repo.drug;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.versionsystem.persistence.model.WDrugAppFormLog;

public interface WDrugAppFormLogRepository extends JpaRepository<WDrugAppFormLog, Long>,QueryDslPredicateExecutor<WDrugAppFormLog> {

	/*
	@Query("select u from userid u where u.role.role = :role")
	Page<UserId> findByRole(@Param("role") Integer role, Pageable pageable);*/
}
