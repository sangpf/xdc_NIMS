package com.newIns.web.content;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newIns.model.VqnItem;
import com.newIns.service.LoadVqnService;
import com.newIns.service.NiVoteQuestionnaireService;

@RequestMapping("/queView")
@Controller
public class NiVoteQuestionnaire_ViewController {
	@Resource
	private NiVoteQuestionnaireService niVoteQuestionnaireService;
	@Autowired
	private LoadVqnService loadVqnService;
	@Autowired
	private VqnItem vqnItem;
	/**
	 * @Title: loadVqn  
	 * @Author: Guan
	 * @Description: 预览投票问卷
	 * @param request
	 * @return VqnItem
	 * @Time 2016年11月11日 下午7:05:33
	 */
	@RequestMapping(value = "/loadVqn.do", method = RequestMethod.GET)
	@ResponseBody
	public VqnItem loadVqn(HttpServletRequest request){
		
		String vqnIdStr = request.getParameter("vqnId").trim();
		
		int vqnId = 0;
		if(vqnIdStr != null){
			vqnId = Integer.parseInt(vqnIdStr);
		}
		
		vqnItem = loadVqnService.loadVqn(vqnId);
		return vqnItem;
	}
	
	/**
	 * 新预览  跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/niVoteQue_View_jump.do")
	public String niVoteQue_View_jump(HttpServletRequest request,Model model){

		niVoteQuestionnaireService.niVoteQue_View_jump(request, model);
		
		return "manager/content/vote/vote_content";
	}
	
}
