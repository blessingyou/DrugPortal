package com.versionsystem.common;

public class FilterRequest {

	private String property;
	private Object value;

	public FilterRequest(String property, Object value) {
		super();
		this.property = property;
		this.value = value;
	}

	public FilterRequest() {

	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String toString() {
		return "Property: " + property + " -- Value: " + value;
	}
}
