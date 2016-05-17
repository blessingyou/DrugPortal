package com.versionsystem.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtils {
	Logger logger = Logger.getLogger(JsonUtils.class);

	public static List<FilterRequest> getListFromJsonArray(String data) {

		List<FilterRequest> values = new ArrayList<FilterRequest>();

		try {
			ObjectMapper mapper = new ObjectMapper();

			values = mapper.readValue(data,
					new TypeReference() {
					});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return values;
	}
}
