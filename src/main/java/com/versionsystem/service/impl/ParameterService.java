package com.versionsystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.versionsystem.common.ApplicationParas;
import com.versionsystem.common.FilterRequest;
//import com.versionsystem.persistence.model.QUserId;
import com.versionsystem.persistence.model.SystemParameter;
import com.versionsystem.persistence.model.UserId;
//import com.versionsystem.persistence.model.UserId.COLUMNS;
import com.versionsystem.service.repo.SystemParasRepository;
import com.versionsystem.web.model.ClientParas;

@Service
public class ParameterService {

	@Autowired
	private SystemParasRepository systemParasRepository;
	private Logger logger = Logger.getLogger(ParameterService.class);
	public ClientParas findByModule(String name) {
		ClientParas p=new ClientParas();
		List<SystemParameter> l = systemParasRepository.findByModuleName(name);
		for(SystemParameter o:l){
			if(ApplicationParas.APP_GRID_COLUMNMENU.equals(o.getParameterKey()))
				p.setAppGridColumnMenu("true".equals(o.getParameterValue())?true:false);			
			else if(ApplicationParas.APP_GRID_FILTERABLE.equals(o.getParameterKey()))
				p.setAppGridFilterable("true".equals(o.getParameterValue())?true:false);
			else if(ApplicationParas.APP_GRID_EDITABLE.equals(o.getParameterKey()))
				p.setAppGridEditable("true".equals(o.getParameterValue())?true:false);
			else if(ApplicationParas.APP_GRID_GROUPABLE.equals(o.getParameterKey()))
				p.setAppGridGroupable("true".equals(o.getParameterValue())?true:false);
			else if(ApplicationParas.APP_GRID_PAGEABLE.equals(o.getParameterKey()))
				p.setAppGridPageable("true".equals(o.getParameterValue())?true:false);
			else if(ApplicationParas.APP_GRID_PAGESIZE.equals(o.getParameterKey()))
				p.setAppGridPageSize(Integer.parseInt(o.getParameterValue()));
			else if(ApplicationParas.APP_GRID_REORDERABLE.equals(o.getParameterKey()))
				p.setAppGridReorderable("true".equals(o.getParameterValue())?true:false);
			else if(ApplicationParas.APP_GRID_RESIZEABLE.equals(o.getParameterKey()))
				p.setAppGridResizeable("true".equals(o.getParameterValue())?true:false);
			else if(ApplicationParas.APP_GRID_SELECTABLE.equals(o.getParameterKey()))
				p.setAppGridSelectable("true".equals(o.getParameterValue())?true:false);
			else if(ApplicationParas.APP_GRID_SORTABLE.equals(o.getParameterKey()))
				p.setAppGridSortable("true".equals(o.getParameterValue())?true:false);
		}
		return p;
	}
	
	private Predicate toPredicate(final List<FilterRequest> filters) {
		logger.info("Entering predicates :: " + filters);
		//QUserId user = QUserId.userId1;
		BooleanExpression result = null;

		try {
			/*for (FilterRequest filter : filters) {

				COLUMNS column = COLUMNS.valueOf(filter.getProperty()
						.toUpperCase());
				BooleanExpression expression = null;

				switch (column) {
				case USERNAME:
					if (filter.getValue() != null
							&& !"".equals(filter.getValue())) {
						expression = user.userName.like("%" + filter.getValue()
								+ "%");
					}
					break;
				case USERID:
					if (filter.getValue() != null
							&& !"".equals(filter.getValue())) {
						expression = user.userId.like("%"
								+ filter.getValue() + "%");
					}
					break;
				case EMAIL:
					if (filter.getValue() != null
							&& !"".equals(filter.getValue())) {
						expression = user.email.like("%" + filter.getValue()
								+ "%");
					}
					break;
				
				}
				if (expression != null) {
					if (result != null) {
						result = result.and(expression);
					} else {
						result = expression;
					}
				}
			}*/
		} catch (Exception ex) {
			logger.error(ex);
		}
		logger.info("Result Predicate :: "
				+ (result != null ? result.toString() : ""));

		logger.info("Exiting predicates");
		return result;
	}

}
