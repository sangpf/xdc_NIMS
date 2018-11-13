package com.newIns.tools;

import java.util.HashSet;

/**
 * 服务器运行期间存储问卷的md5值
 * @author Sang
 *
 */
public class QueTool{
	
	private static HashSet<String> quemd5Set;
	//私有化空参数构造方法,不让外界构造该类对象
	private QueTool() {}
	
	public static synchronized HashSet<String> getQuemd5Set() {
		if(quemd5Set==null){
			quemd5Set = new HashSet<String>();
		}
		
		return quemd5Set;
	}
	
}
