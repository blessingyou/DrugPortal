package com.versionsystem.service.repo.menu;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.versionsystem.persistence.model.MenusCtrlAccess;

public interface MenusCtrlAccessRepository extends JpaRepository<MenusCtrlAccess, Long>,QueryDslPredicateExecutor<MenusCtrlAccess> {
	
	public List<MenusCtrlAccess> findByMenusCtrlIdOrderByParameterKeyAsc(long menuId);


	
}
