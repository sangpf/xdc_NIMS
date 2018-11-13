package weibo4j.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 该类可以封装成工具类   读取properties配置文件 ，获取文件中数据
 * @author Administrator
 *
 */
public class WeiboConfig {
	public WeiboConfig(){}
	private static Properties props = new Properties(); 
	static{
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("wbconfig.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		return props.getProperty(key);
	}

    public static void updateProperties(String key,String value) {    
            props.setProperty(key, value); 
    } 
}
