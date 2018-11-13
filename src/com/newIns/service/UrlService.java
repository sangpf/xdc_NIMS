package com.newIns.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newIns.tools.AjaxResult;


public interface UrlService {

	AjaxResult get_wanxLoginUrl(HttpServletRequest request,
			HttpServletResponse response);

	
}
