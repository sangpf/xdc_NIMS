package com.newIns.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;

import com.newIns.service.UrlService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.PropUtil;

@RequestMapping("/platform")
@Controller
public class UrlController {
	private static Logger log = Logger.getLogger(UrlController.class);
	
	@Autowired
	private UrlService urlService;
	
	// 打开页面
	@RequestMapping("/new_wanxUrl_page.do")
	public String new_wanxUrl_page(HttpServletRequest request,Model model){
		
		return "manager/wanxUrl/new_wanxUrl";
	}
	
	/**
	 * 生成玩校 联合登录地址
	 */
	@ResponseBody
	@RequestMapping("/get_wanxLoginUrl.do")
	public AjaxResult get_wanxLoginUrl(HttpServletRequest request,HttpServletResponse response){
		
		return urlService.get_wanxLoginUrl(request,response);
	}
	
	/**
	 * 新浪微博授权回掉地址
	 * @param request
	 * @throws WeiboException
	 */
	@RequestMapping("/XinLang_WeiBoAuthCallBack.do")
	public void callBack(HttpServletRequest request,HttpServletResponse response) throws WeiboException{
		
		HttpSession session = request.getSession();
		
		String code = request.getParameter("code");
		log.info("===============>>新浪微博临时 code :"+code);
		
		Oauth oauth = new Oauth();
		//根据 code 获取token 对象
		AccessToken accessTokenByCode = oauth.getAccessTokenByCode(code);
		
		String accessToken = accessTokenByCode.getAccessToken();
		//将token存入session
		session.setAttribute("wbaccess_Token", accessToken);
		log.info("=============>> token " + accessToken);
		
		String wanx_loginUrl_page = PropUtil.getValue_from_propties("wbconfig.properties", "wanx_loginUrl_page");
		//跳转到 管理后台
		try {
			response.sendRedirect(wanx_loginUrl_page);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 请求新浪三方授权接口
	 * @throws WeiboException
	 */
	@RequestMapping("/XinLang_WeiBoLogin.do")
	@ResponseBody
	public AjaxResult wbOauth(HttpServletRequest request) throws WeiboException{
		AjaxResult ajaxResult = new AjaxResult();
		//从session中获取token,判断用户是否已经登陆
		HttpSession session = request.getSession();
		
		Object attribute = session.getAttribute("wbaccess_Token");
		
		String accessToken = null;
		if(attribute != null){
			accessToken = (String) attribute;
		}
		
		if(accessToken!=null){
			//用户已经登陆
			ajaxResult.put("loggedIn", true);
			
		}else{
			//用户未登陆
			
			Oauth oauth = new Oauth();
			// 封装请求路径 
			String url = oauth.authorize("code");
			// 请求用户授权Token 
//			BareBonesBrowserLaunch.openURL(url);
			
			ajaxResult.put("loggedIn", false);
			ajaxResult.put("locationUrl", url);
			
		}
		
		return ajaxResult;
	}
	
	
}
