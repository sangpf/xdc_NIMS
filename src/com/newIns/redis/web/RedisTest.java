package com.newIns.redis.web;

import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/redis")
public class RedisTest extends AbstractJUnit4SpringContextTests{
//
//	   @Autowired  
//	   private IUserService userService;  
//	   
//	    /** 
//	     * 新增 
//	     * <br>------------------------------<br> 
//	     */
//	   @RequestMapping("/testAddUser.do")
//	    public void testAddUser() {  
//	        User user = new User();  
//	        user.setId("user1");  
//	        user.setName("java2000_wl");  
//	        boolean result = userService.add(user);  
//	    }  
//	      
//	    /** 
//	     * 批量新增 普通方式 
//	     * <br>------------------------------<br> 
//	     */  
//	    public void testAddUsers1() {  
//	        List<User> list = new ArrayList<User>();  
//	        for (int i = 10; i < 50000; i++) {  
//	            User user = new User();  
//	            user.setId("user" + i);  
//	            user.setName("java2000_wl" + i);  
//	            list.add(user);  
//	        }  
//	        long begin = System.currentTimeMillis();  
//	        for (User user : list) {  
//	            userService.add(user);  
//	        }  
//	        System.out.println(System.currentTimeMillis() -  begin);  
//	    }  
//	      
//	    /** 
//	     * 批量新增 pipeline方式 
//	     * <br>------------------------------<br> 
//	     */  
//	    public void testAddUsers2() {  
//	        List<User> list = new ArrayList<User>();  
//	        for (int i = 10; i < 1500000; i++) {  
//	            User user = new User();  
//	            user.setId("user" + i);  
//	            user.setName("java2000_wl" + i);  
//	            list.add(user);  
//	        }  
//	        long begin = System.currentTimeMillis();  
//	        boolean result = userService.add(list);  
//	        System.out.println(System.currentTimeMillis() - begin);  
//	    }  
//	      
//	    /** 
//	     * 修改 
//	     * <br>------------------------------<br> 
//	     */  
//	    public void testUpdate() {  
//	        User user = new User();  
//	        user.setId("user1");  
//	        user.setName("new_password");  
//	        boolean result = userService.update(user);  
//	    }  
//	      
//	    /** 
//	     * 通过key删除单个 
//	     * <br>------------------------------<br> 
//	     */  
//	    public void testDelete() {  
//	        String key = "user1";  
//	        userService.delete(key);  
//	    }  
//	      
//	    /** 
//	     * 批量删除 
//	     * <br>------------------------------<br> 
//	     */  
//	    public void testDeletes() {  
//	        List<String> list = new ArrayList<String>();  
//	        for (int i = 0; i < 10; i++) {  
//	            list.add("user" + i);  
//	        }  
//	        userService.delete(list);  
//	    }  
//	      
//	    /** 
//	     * 获取 
//	     * <br>------------------------------<br> 
//	     */  
//	    public void testGetUser() {  
//	        String id = "user1";  
//	        User user = userService.get(id);  
//	    }  
//	  
//	    /** 
//	     * 设置userService 
//	     * @param userService the userService to set 
//	     */  
//	    public void setuserService(IUserService userService) {  
//	        this.userService = userService;  
//	    }  
}
