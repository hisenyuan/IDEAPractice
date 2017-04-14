package com.hisen.redis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class PropertiesUtil {
	
	private static final String FILE="/redis.properties";
	private static Properties properties;
	
	static {
    	properties = new Properties();
    	InputStream is = PropertiesUtil.class.getResourceAsStream(FILE);
    	try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public static String getProperty(String key) {
		InputStream in = PropertiesUtil.class.getResourceAsStream(FILE);
		Properties p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			new RuntimeException(e.getMessage());
		}
		return p.getProperty(key);
	}
	
	private static final String getOrgProp(String key) {
    	key = handleKey(key);
    	return getProp(key);
    }
    
    private static final String getProp(String key) {
    	 try {
         	String result = properties.getProperty(key); 
         	result = handleString(result);
             return result;
         } catch (Exception e) {
             return "";
         }
    }
	public static final String getString(String key) {
	    String orgKey = OrganizationHolder.getOrgKey();
	       if(StringUtils.isEmpty(orgKey)){
	    	   return getProp(key);
	       }else{
	    	   return getOrgProp(key);
	       }
	    }
	
	private static String handleString(String result){
    	Pattern p = Pattern.compile("(\\{[^\\{\\}]*\\})");
    	Matcher m = p.matcher(result);
    	while(m.find()){
        	Pattern ptmp = Pattern.compile("([^\\{\\}]*)");
        	Matcher mtmp = ptmp.matcher(m.group());
        	mtmp.find(1);
    		result = result.replace(m.group(), properties.getProperty(mtmp.group()));
    	}
    	m = p.matcher(result);
    	if(m.find()){
    		result = handleString(result);
    	}
    	return result;
    }
    private static String handleKey(String key){
    	String orgCode = OrganizationHolder.getOrgKey();
    	if(StringUtils.isEmpty(orgCode)){
    		return key;
    	}
    	return String.format("%2$s.%1$s", key,orgCode);
    }
	
}
