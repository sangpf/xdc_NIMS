package com.newIns.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.dao.user.AuthorDao;
import com.newIns.model.user.Author;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.FileUtil;
import com.newIns.tools.StrUtils;

@Service
public class AuthorServiceImp implements AuthorService {
	
	@Autowired
	private AuthorDao authorDao;
	
	// 列表查询作者 
	public void author_List(HttpServletRequest request, Model model) {
		
		Map<String, Object> dataMap = new HashMap<>();
		
		List<Author> author_List = authorDao.author_List(dataMap);
		
		model.addAttribute("author_List", author_List);
		
	}
	
	// 添加 或者编辑  作者 
	public void addAuthor(HttpServletRequest request, Model model) {
		
		// 如果参数中 有  authorId  则为编辑
		String authorId_str = request.getParameter("authorId");
		if(StrUtils.isNotEmpty(authorId_str)){
			Integer authorId = Integer.valueOf(authorId_str);
			
			Author author = authorDao.selectOneById(authorId);
			
			if(author != null){
				model.addAttribute("author", author);
			}
		}
	}

	// 保存 作者
	public AjaxResult saveAuthor(HttpServletRequest request, MultipartFile file) {
		
		//上传图片
		Map<String, Object> uploadFile = null;
		try {
			uploadFile = FileUtil.uploadFile(request, file, "img/manage/article/author/");
		} catch (Exception e1) {
			e1.printStackTrace();
			return AjaxResult.errorResult();
		}
		//获取图片的存储路径
		String headImg = (String) uploadFile.get("jdbcUrl"); 
		
		String authorId_str = request.getParameter("authorId");
		String userName = request.getParameter("userName");
		String introduce = request.getParameter("introduce");
		
		Author author = new Author();
		author.setUserName(userName);
		author.setIntroduce(introduce);
		author.setHeadImg(headImg);
		
		// 判断是新增 还是编辑
		if(StrUtils.isNotEmpty(authorId_str)){
			// 编辑
			Integer authorId = Integer.valueOf(authorId_str);
			
			author.setId(authorId);
			
			try {
				authorDao.updateById(author);
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("编辑失败");
			}
			
		}else{
			// 新增
			
			try {
				authorDao.insert(author);
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("新增作者失败");
			}
		}
		
		return AjaxResult.successResult();
	}

}
