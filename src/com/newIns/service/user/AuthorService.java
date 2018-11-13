package com.newIns.service.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.tools.AjaxResult;

public interface AuthorService {

	void author_List(HttpServletRequest request, Model model);

	void addAuthor(HttpServletRequest request, Model model);

	AjaxResult saveAuthor(HttpServletRequest request, MultipartFile file);

	
}
