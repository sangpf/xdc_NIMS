package com.newIns.tools.jedis;


public class RedisUtils {
	
	public static void setObject(String key,Object value){
		RedisOps.setObject(key, value);
	}
	
	public static Object getObject(String key){
		Object object = RedisOps.getObject(key);
		return object;
	}
	
    public static void deleteByKey(String key){
    	RedisOps.delete(key);
    }
    
    public static void main(String[] args) {
    	//----------------测试对象存储-----
    	User user = new User(99, "wanghua");
    	
//    	set("user", user);
    	
//    	User user2 = (User) get("user");
//    	System.out.println(user2.toString());
    	
    	
    	//---------------测试集合存储---------
    	
//    	List<User> arrayList = new ArrayList<>();
//    	
//    	arrayList.add(new User(99, "wanghua"));
//    	arrayList.add(new User(88, "liming"));
//    	
//    	set("arrayList", arrayList);
    	
    	
    	
    	deleteByKey("niAqnclassDictMapper:selectList");
    	deleteByKey("assessQuestionnaireService:selectList");
	}
    
}
