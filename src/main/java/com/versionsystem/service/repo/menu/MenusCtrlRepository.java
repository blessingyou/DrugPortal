package com.versionsystem.service.repo.menu;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.versionsystem.persistence.model.MenusCtrl;

public interface MenusCtrlRepository extends JpaRepository<MenusCtrl, Long>,QueryDslPredicateExecutor<MenusCtrl> {

	MenusCtrl findById(String id);
	List<MenusCtrl> findBySeqNoStartingWith(String seqNo);
	List<MenusCtrl> findBySeqNoAndSysRole(String seqNo,String sysRole);
	List<MenusCtrl> findBySysRoleOrderByDispalySeqNoAsc(String sysRole);
	
	//Page<MenusCtrl> findByFormDescLike(String formDesc, Pageable pageable);

	
}
