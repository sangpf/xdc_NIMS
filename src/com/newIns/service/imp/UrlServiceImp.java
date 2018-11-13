package com.newIns.service.imp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import weibo4j.ShortUrl;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONObject;

import com.newIns.service.UrlService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.PropUtil;

@Service
public class UrlServiceImp implements UrlService {
	
	private static Logger log = Logger.getLogger(UrlServiceImp.class);

	public AjaxResult get_wanxLoginUrl(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = new AjaxResult();
		
		String originalUrl = request.getParameter("originalUrl");
		
		String value_from_propties = PropUtil.getValue_from_propties("wanxUrl.properties", "redirect_uri")+originalUrl;
		
		String encode_redirect_uri = "";
		try {
			encode_redirect_uri = URLEncoder.encode(value_from_propties, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return AjaxResult.errorResult();
		}
		
		String wanxAuthorizeUrl_long = PropUtil.getValue_from_propties("wanxUrl.properties", "wanxAuthorizeBaseUrl")+encode_redirect_uri;
		
		ajaxResult.put("wanxAuthorizeUrl_long", wanxAuthorizeUrl_long);
		
		//-----------------生成短链接----------------
		String accessToken = null;
		HttpSession session = request.getSession();
		Object attribute = session.getAttribute("wbaccess_Token");
		if(attribute != null){
			accessToken = (String) attribute;
		}
		// 生成短链接
		ShortUrl shortUrl = new ShortUrl(accessToken);
		
		try {
			JSONObject longToShortUrl = shortUrl.longToShortUrl(wanxAuthorizeUrl_long);
			
			JSONArray jsonArray = longToShortUrl.getJSONArray("urls");
			
			JSONObject jsonObject = jsonArray.getJSONObject(0);
			
			Object object = jsonObject.get("url_short");
			
			String url_short = "";
			if(object != null){
				url_short = (String) object;
			}
			
			log.info(longToShortUrl);
			log.info("--------------------------->>生成短链接 :"+url_short);
			
			ajaxResult.put("url_short", url_short);
			
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult();
		}
		
		
		ajaxResult.put("msg", "成功");
		ajaxResult.put("success", true);
		return ajaxResult;
	}


}
