package com.newIns.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newIns.model.NiOaManageUser;
import com.newIns.service.NiOaManageUserService;
import com.newIns.tools.AjaxResult;

@Controller
@RequestMapping("/managerUser")
public class LoginController {
	private static Logger log = Logger.getLogger(LoginController.class);
	@Resource
	private NiOaManageUserService niOaManageUserService;
	
	/**
	 * 跳转登录
	 * @return
	 */
	@RequestMapping("/returnLoginPage.do")
	public String returnLoginPage(){
		
		return "auth/userLogin";
	}
	
	/**
	 * 添加用户
	 */
	@RequestMapping("/addManageUser.do")
	public void addUser(){
		
		NiOaManageUser niOaManageUser = new NiOaManageUser();
		// 1, 原始密码  2,盐  3,散列次数
		Md5Hash md5 = new Md5Hash("beijing123", "beijing", 2);
		
		niOaManageUser.setOaUserName("beijing");
		niOaManageUser.setOaPassword(md5.toString());
		
		try {
			int addUser = niOaManageUserService.addUser(niOaManageUser);
			if(addUser>0){
			}else{
				log.info("===============后台添加用户失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("===============后台添加用户异常");
		}
		
	}
	
	/**
	 * 用户登录
	 */
	@RequestMapping(value="/manageUserlogin.do")
	@ResponseBody
	public AjaxResult LoginUser(HttpServletRequest request,HttpServletResponse response){
		AjaxResult errorResult = AjaxResult.errorResult("");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		UsernamePasswordToken token = new UsernamePasswordToken(account,password);
		
		Subject subject = SecurityUtils.getSubject();
		
		try {
			subject.login(token);
			request.getSession().setAttribute("user", subject.getPrincipal());
			return AjaxResult.successResult("登录成功");
			
		} catch (AuthenticationException e) {
			e.printStackTrace();
			errorResult.put("error", "用户名或密码错误");
			return errorResult;
		}
		
	
	}
	
	/**
	 * 注销
	 * @param request
	 */
	@RequestMapping("/manageUserSingOut.do")
	public String singOut(HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/managerUser/returnLoginPage.do";
	}
	
}
