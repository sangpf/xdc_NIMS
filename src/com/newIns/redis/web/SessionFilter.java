package com.newIns.redis.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.GenericFilterBean;

public class SessionFilter extends GenericFilterBean {

	private RedisTemplate redisTemplate;
	
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {
    	
        HttpServletRequest re = (HttpServletRequest)request;  
        HttpServletResponse res = (HttpServletResponse)response;  
        
        TerryHttpServletRequestWrapper wrapper = new TerryHttpServletRequestWrapper(re,res,redisTemplate);  
        chain.doFilter(wrapper, response);  
        
    }

    public RedisTemplate getRedisTemplate() {  
        return redisTemplate;  
    }  
    public void setRedisTemplate(RedisTemplate redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }  
    
}
