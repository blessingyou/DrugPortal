package com.versionsystem.service.repo.menu;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.versionsystem.persistence.model.LastestMenuList;

public interface LastestMenuListRepository extends JpaRepository<LastestMenuList, Long>,QueryDslPredicateExecutor<LastestMenuList> {

	LastestMenuList findByUserIdAndMenuId(String userId,long menuId);
	List<LastestMenuList> findByUserIdOrderByDisplaySeqAsc(String userId);
	
	List<LastestMenuList> findByMenuId(long menuid);
	//Page<MenusCtrl> findByFormDescLike(String formDesc, Pageable pageable);

	
}
