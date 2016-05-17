package com.versionsystem.persistence.model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;



public class IsoCalendarJsonSerializer extends JsonSerializer<Calendar> {  
    @Override
    public void serialize(Calendar value, JsonGenerator gen, SerializerProvider sp) throws 
        IOException, JsonProcessingException {      

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        
        String formattedDate = formatter.format(value.getTime());

        gen.writeString(formattedDate);
    }
}