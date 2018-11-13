package com.newIns.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.newIns.model.NiOaManageUser;
import com.newIns.service.NiOaManageUserService;

public class XdcRealm extends AuthorizingRealm{
	private static Logger log = Logger.getLogger(XdcRealm.class);
	@Resource
	private NiOaManageUserService niUserService;

	//授权
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("执行了授权的方法");
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//获得实体
		NiOaManageUser niOaManageUser = (NiOaManageUser) arg0.getPrimaryPrincipal();
		//查询该用户所拥有的权限
		int oaUserId = niOaManageUser.getOaUserId();
		List<String> authNameList = niUserService.findAuthNamebyUserId(oaUserId);
		
		//给当前用户添加权限
		simpleAuthorizationInfo.addStringPermissions(authNameList);
		
		return simpleAuthorizationInfo;
	}

	//认证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0)
			throws AuthenticationException {

		System.out.println("执行了认证的方法");
		UsernamePasswordToken token= (UsernamePasswordToken) arg0;
		
		String username = token.getUsername();
		char[] password = token.getPassword();
		
		NiOaManageUser niUser = niUserService.findByAccount(username);
		if(niUser!=null){
			//数据库中存储的加密的密码
			String passWord2 = niUser.getOaPassword();  
			
			//令牌中的密码,即是用户输入的密码
			//将该密码加密  
			Md5Hash md5Hash = new Md5Hash(new String(password), username, 2);
			
			if(!passWord2.trim().equals(md5Hash.toString()) ){
				log.info("===================>>用户名,密码错误!");
				return null;
			}
		}else{
			log.info("===================>>登录的账户不存在");
			return null;
		}
		// 三个参数 分别为: 用户对象 , 用户密码 , realm名称
		return new SimpleAuthenticationInfo(niUser, new String(password), getName());
	}
	
}
