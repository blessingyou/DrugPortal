package com.versionsystem.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.versionsystem.service.ConfigService;


@SuppressWarnings("serial")

public class KendoAwareObjectMapper extends ObjectMapper {
	
	
	    public KendoAwareObjectMapper() {
    	//indent the json output so it is easier to read
    	configure(SerializationFeature.INDENT_OUTPUT, true);
        
        //Wite/Read dates as ISO Strings
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        
        //dateFormat.setTimeZone(TimeZone.getTimeZone(configService.getProperty(ApplicationParas.APP_TIMEZONE)));
        this.setDateFormat(dateFormat);
       
    }

}
