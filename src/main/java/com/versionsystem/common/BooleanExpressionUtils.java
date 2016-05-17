package com.versionsystem.common;

import com.mysema.query.types.expr.BooleanExpression;

public class BooleanExpressionUtils {
	
	public static BooleanExpression and(BooleanExpression result,BooleanExpression expression) {
		if (expression != null) {
			if (result != null) {
				result = result.and(expression);
			} else {
				result = expression;
			}
		}
		return result;
	}
	public static BooleanExpression or(BooleanExpression result,BooleanExpression expression) {
		if (expression != null) {
			if (result != null) {
				result = result.or(expression);
			} else {
				result = expression;
			}
		}
		return result;
	}

}
