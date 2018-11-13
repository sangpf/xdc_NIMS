package com.newIns.tools;

public class EhcacheTools {
	
	/*
	第一步：生成CacheManager对象
	第二步：生成Cache对象
	第三步：向Cache对象里添加由key,value组成的键值对的Element元素 */
	//创建
	//指定ehcache.xml的位置
	/*String fileName="G:\\workspace01\\NewIns\\WebRoot\\ehcache.xml";
	CacheManager manager = CacheManager.create(fileName);
	//取出所有的cacheName
	String[] cacheNames = manager.getCacheNames();
	for(int i=0;i<cacheNames.length;i++){
		String cacheName = cacheNames[i];
		//根据cacheName生成一个Cache对象
		Cache cache = manager.getCache(cacheName);
		
		//加入元素
		Element element01 = new Element("001", "001");
		cache.put(element01);
		
		Element element = cache.get("001");
		log.info("==============>> 从cache中获取元素"+element.toString());
		
	}*/
}
