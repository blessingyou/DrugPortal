package com.versionsystem.service;

import java.util.Properties;

import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.commons.configuration.DatabaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("domainConfigService")
public class ConfigService {
 
 @Autowired
 @Qualifier("domainConfiguration")
 DatabaseConfiguration domainConfiguration;
 
 private Properties properties;
 
 public String getProperty(String key) {
	 if (properties == null) {
		 properties = ConfigurationConverter.getProperties(domainConfiguration);
	 }
	 return properties.getProperty(key);
 }
} 