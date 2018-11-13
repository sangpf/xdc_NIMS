package com.newIns.tools;

import redis.clients.jedis.Jedis;

public class JedisUtils {
	
	public static void main(String[] args) {
		
		test1();
	}
	
    public static void test1(){
        //设置redis 主机ip和端口
        Jedis jedis = new Jedis("101.200.166.221",6379);
        //保存数据 
        jedis.set("name", "jedis");
        //获取数据 
//        String name = jedis.get("name");
//        System.out.println(name);
        //释放资源
        jedis.close();
    }
	
	
}
