package org.com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Property {

	static public String ADMIN_MODULE = "admin";
	static public String FINANCE_MODULE = "finance";
	static public String USER_MODULE = "user";
	static Properties props = new Properties();
	static Properties adminProps = new Properties();
	static Properties finProps = new Properties();
	static Properties userProps = new Properties();
	static String strFileName;
	
	private Property(){}
	
	public void loadProperties(String propertiesFile, Properties properties) {
		File f = new File(propertiesFile);
		if (f.exists()) {
			try {
				FileInputStream in = new FileInputStream(f);
				properties.load(in);
				in.close();
			}catch (Exception e) {}
		}
	}
	
	
	public Property(String strFileName) {
		new Property(strFileName, null);
	}
	
	public Property(String strFileName, String module) {
		if(module == null) {
			loadProperties(strFileName, props);
		}else if(module.equalsIgnoreCase(ADMIN_MODULE)) {
			loadProperties(strFileName, adminProps);
		}else if(module.equalsIgnoreCase(FINANCE_MODULE)) {
			loadProperties(strFileName, finProps);
		}else if(module.equalsIgnoreCase(FINANCE_MODULE)) {
			loadProperties(strFileName, finProps);
		}else if(module.equalsIgnoreCase(USER_MODULE)) {
			loadProperties(strFileName, userProps);
		}
	}
	
	public static String getProperty(String strKey) {
		return getProperty(strKey, null);
	}
	
	public static String getProperty(String strKey, String module) {
		try {
			if(module == null) {
				return props.getProperty(strKey);
			}else if(module.equalsIgnoreCase(ADMIN_MODULE)) {
				return adminProps.getProperty(strKey);
			}else if(module.equalsIgnoreCase(FINANCE_MODULE)) {
				return finProps.getProperty(strKey);
			}else if(module.equalsIgnoreCase(USER_MODULE)) {
				return userProps.getProperty(strKey);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}

