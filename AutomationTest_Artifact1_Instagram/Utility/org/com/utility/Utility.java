package org.com.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utility {
  public static Object fetchinvokedetails(String key) throws IOException {
//	  FileInputStream fis=new FileInputStream("./Appconfig/configapp/Appconfig1.java");
	  FileInputStream fis=new FileInputStream("./config/config.properties");
	  Properties pt=new Properties();
	  pt.load(fis);
	  return pt.get(key);
	  
  }
}
