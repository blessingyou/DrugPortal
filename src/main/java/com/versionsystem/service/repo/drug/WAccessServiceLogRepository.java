package com.versionsystem.service.repo.drug;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.versionsystem.persistence.model.WAccessServiceLog;

public interface WAccessServiceLogRepository extends JpaRepository<WAccessServiceLog, Long>,QueryDslPredicateExecutor<WAccessServiceLog> {

}
