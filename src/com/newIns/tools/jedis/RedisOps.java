package com.newIns.tools.jedis;

import redis.clients.jedis.Jedis;

public class RedisOps {

    public static void set(String key,String value){
        Jedis jedis = RedisConnection.getJedis();
        jedis.set(key, value);
        jedis.close();
    }
    
    public static String get(String key){
        Jedis jedis = RedisConnection.getJedis();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }
    
    public static void setObject(String key,Object object){
        Jedis jedis = RedisConnection.getJedis();
        
        if(object != null){
        	
        	jedis.set(key.getBytes(), SerializeUtil.serizlize(object));
        }
        
        jedis.close();
    }
    
    public static Object getObject(String key){
        Jedis jedis = RedisConnection.getJedis();
        byte[] bytes = jedis.get(key.getBytes());
        jedis.close();
        
        if(bytes != null){
        	return SerializeUtil.deserialize(bytes);
        }else{
        	return null;
        }
        
    }
    
    public static void delete(String key){
    	Jedis jedis = RedisConnection.getJedis();
    	
    	jedis.del(key);
    	jedis.close();
    }
    
}
