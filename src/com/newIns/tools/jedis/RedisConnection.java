package com.newIns.tools.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnection {
    private static String HOST = "101.200.166.221";
    private static int PORT = 6379;

    private static JedisPool jedisPool = null;

    /*
     * 初始化redis连接池
     * */
    private static void initPool(){
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(200);//最大连接数
            config.setMaxIdle(50);//最大空闲连接数
            config.setMinIdle(8);  //设置最小空闲数
            config.setMaxWaitMillis(10000);//获取可用连接的最大等待时间
            
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);
            //Idle时进行连接扫描
            config.setTestWhileIdle(true);
            //表示idle object evitor两次扫描之间要sleep的毫秒数
            config.setTimeBetweenEvictionRunsMillis(30000);
            //表示idle object evitor每次扫描的最多的对象数
            config.setNumTestsPerEvictionRun(10);
            //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
            config.setMinEvictableIdleTimeMillis(60000);
            
            jedisPool = new JedisPool(config, HOST, PORT);
//            jedisPool = new JedisPool(config, HOST, PORT, 10000, "sangpf");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 获取jedis实例
     * */
    public synchronized static Jedis getJedis() {
        try {
            if(jedisPool == null){
                initPool();
            }
            Jedis jedis = jedisPool.getResource();
//            jedis.auth("redis");//密码
            return jedis;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
