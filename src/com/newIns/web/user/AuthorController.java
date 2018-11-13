package com.newIns.web.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.service.user.AuthorService;
import com.newIns.tools.AjaxResult;

@Controller
@RequestMapping("/platform")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	// 列表查询 作者
	@RequestMapping("/authorList.do")
	public String author_List(HttpServletRequest request,Model model){
		
		authorService.author_List(request,model);
		
		return "manager/user/author_List";
	}
	
	// 添加 作者
	@RequestMapping("/addAuthor.do")
	public String addAuthor(HttpServletRequest request,Model model){
		
		authorService.addAuthor(request,model);
		return "manager/user/author_add";
	}
	
	// 保存作者信息
	@ResponseBody
	@RequestMapping("/saveAuthor.do")
	public AjaxResult saveAuthor(HttpServletRequest request,@RequestParam("uploadImgmes") MultipartFile file){
		
		return authorService.saveAuthor(request,file);
		
	}
	
}
