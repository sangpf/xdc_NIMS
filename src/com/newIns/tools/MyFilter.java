package com.newIns.tools;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * 自定义授权过滤器
 * @author 11409
 *
 */
public class MyFilter extends AuthorizationFilter{

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object arg2) throws Exception {
		//获取主题
		Subject subject = getSubject(request, response);
		//获取权限列表
		String[] perms = (String[]) arg2;
		if(perms==null || perms.length==0){
			return true;
		}
		for (String p : perms) {
			if(subject.isPermitted(p)){   //如果有一个权限符合,也为符合
				return true;
			}
		}
		
		return false;
	}
	
}
