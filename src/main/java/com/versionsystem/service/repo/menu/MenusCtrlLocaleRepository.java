package com.versionsystem.service.repo.menu;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.versionsystem.persistence.model.MenusCtrlLocale;

public interface MenusCtrlLocaleRepository extends JpaRepository<MenusCtrlLocale, Long>,QueryDslPredicateExecutor<MenusCtrlLocale> {

	MenusCtrlLocale findByMenusCtrlIdAndLocale(long meunId,String locale);
	List<MenusCtrlLocale> findByMenusCtrlSysRole(String roleId);
	List<MenusCtrlLocale> findByMenusCtrlId(long menuId);

	
}
