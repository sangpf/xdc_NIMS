package com.newIns.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.newIns.model.NiUserBaseInformation;
import com.newIns.model.NiUserDetailInformation;
import com.newIns.service.NiUserInformationService;
import com.newIns.tools.AjaxResult;

/**
 * @Description 用户信息列表controller
 * @author wanq
 * 
 */
@Controller
@RequestMapping("/platform")
public class NiUserInformationController {
	
	@Resource
	private NiUserInformationService userInformationService;

	@RequestMapping("/showUserInfoList.do")
	public ModelAndView loadUserInfoList(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String schoolName = request.getParameter("schoolName"); // 获取第一个下拉框参数
		String schoolNameOrUserIdOrPhone = request
				.getParameter("schoolNameOrUserIdOrPhone"); // 获取第一个搜索框参数
		String reservation = request.getParameter("reservation"); // 获取时间框参数
		String userGrade = request.getParameter("userGrade"); // 获取年级下拉框参数
		String gender = request.getParameter("gender"); // 获取性别下拉框参数
		String userStats = request.getParameter("userStats"); // 获取状态下拉框参数

		String startTime = "";
		String endTime = "";

		if (reservation != null && !reservation.trim().equals("")) {
			String[] split = reservation.split("-");
			startTime = split[0];
			endTime = split[1];
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startTime1 = "";
		String endTime1 = "";

		if (startTime != null && !startTime.trim().equals("")) {
			Date date = new Date(startTime);
			Date date2 = new Date(endTime);
			startTime1 = format.format(date);
			endTime1 = format.format(date2);
		}

		/* 根据第一个下拉框传递的value值确定查询条件是学校名称、用户id、手机号码 */
		if (schoolName != null && !schoolName.trim().equals("")) {
			if (schoolName.trim().equals("0")) {
				hashMap.put("schoolName", schoolNameOrUserIdOrPhone.trim());
			} else if (schoolName.trim().equals("1")) {
				hashMap.put("userId", schoolNameOrUserIdOrPhone.trim());
			} else if (schoolName.trim().equals("2")) {
				hashMap.put("phone", schoolNameOrUserIdOrPhone.trim());
			}
		}

		if (startTime != null && !startTime.trim().equals("")) {
			hashMap.put("startTime", startTime1);
			hashMap.put("endTime", endTime1);
		}

		// if (userGrade != null && !userGrade.trim().equals("")) {
		// hashMap.put("grade", Integer.valueOf(userGrade));
		// }

		if (gender != null && !gender.trim().equals("")) {
			hashMap.put("gender", Integer.valueOf(gender));
		}

		// if (userStats != null && !userStats.trim().equals("")) {
		// hashMap.put("userstatus", Integer.valueOf(userStats));
		// }
		List<NiUserBaseInformation> selectList = null;
		if (hashMap.size() == 0) {
			selectList = userInformationService.selectList();
		} else {
			// 根据hashMap中的查询条件获取用户信息数据list
			selectList = userInformationService.selectListByParams(hashMap);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manager/NiUserInformation");
		modelAndView.addObject("NiUserInfoList", selectList);
		return modelAndView;
	}

	@RequestMapping("/showDetailUserInformation.do")
	public ModelAndView showDetailUserInformation(HttpServletRequest request) {
		String userIdStr = request.getParameter("userId");
		int userId = Integer.parseInt(userIdStr);
		NiUserDetailInformation niUserDetailInformation = userInformationService
				.selectByPrimaryKey(userId);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manager/NiShowDetailUserInformation");
		modelAndView.addObject("niUserInformation", niUserDetailInformation);
		return modelAndView;
	}

	@RequestMapping("/saveDetailUserInformation.do")
	@ResponseBody
	public AjaxResult saveDetailUserInformation(HttpServletRequest request) {
		JSONObject jsonObject = JSONObject.fromObject(request
				.getParameter("modifiedField"));
		Pattern pattern = Pattern.compile("[0-9]");
		String userid = (String) jsonObject.get("userid");
		Integer byWay = null;
		if (jsonObject.get("byWay") != null
				&& pattern.matcher((String) jsonObject.get("byWay")).matches()) {
			byWay = Integer.parseInt((String) jsonObject.get("byWay"));
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		String regTimeStr = (String) jsonObject.get("regTime");
		String modifiedRegTime = "";
		if (regTimeStr != null && !regTimeStr.trim().equals("")) {
			Date regTime = new Date(regTimeStr);
			modifiedRegTime = format.format(regTime);
		}

		String imei = (String) jsonObject.get("imei");
		String username = (String) jsonObject.get("username");
		String nickname = (String) jsonObject.get("nickname");
		String phoneNum = (String) jsonObject.get("phoneNum");
		String email = (String) jsonObject.get("email");
		String name = (String) jsonObject.get("name");

		Integer sex = null;
		if (jsonObject.get("sex") != null
				&& pattern.matcher((String) jsonObject.get("sex")).matches()) {
			sex = Integer.parseInt((String) jsonObject.get("sex"));
		}

		String birthdayStr = (String) jsonObject.get("birthday");

		String modifiedBirthday = "";
		if (birthdayStr != null && !birthdayStr.trim().equals("")) {
			Date birthday = new Date(birthdayStr);
			modifiedBirthday = format.format(birthday);
		}

		Integer nation = null;
		if (jsonObject.get("nation") != null
				&& pattern.matcher((String) jsonObject.get("nation")).matches()) {
			nation = Integer.parseInt((String) jsonObject.get("nation"));
		}

		Integer politicalStatus = null;

		if (jsonObject.get("politicalStatus") != null
				&& pattern.matcher((String) jsonObject.get("politicalStatus"))
						.matches()) {
			politicalStatus = Integer.parseInt((String) jsonObject
					.get("politicalStatus"));
		}

		Integer educationLevel = null;

		if (jsonObject.get("educationLevel") != null
				&& pattern.matcher((String) jsonObject.get("educationLevel"))
						.matches()) {
			educationLevel = Integer.parseInt((String) jsonObject
					.get("educationLevel"));
		}

		Integer occupation = null;

		if (jsonObject.get("occupation") != null
				&& pattern.matcher((String) jsonObject.get("occupation"))
						.matches()) {
			occupation = Integer
					.parseInt((String) jsonObject.get("occupation"));
		}

		String resident = (String) jsonObject.get("resident");

		Integer maritalStatus = null;
		if (jsonObject.get("maritalStatus") != null
				&& pattern.matcher((String) jsonObject.get("maritalStatus"))
						.matches()) {
			maritalStatus = Integer.parseInt((String) jsonObject
					.get("maritalStatus"));
		}

		Integer hasChildren = null;

		if (jsonObject.get("hasChildren") != null
				&& pattern.matcher((String) jsonObject.get("hasChildren"))
						.matches()) {
			hasChildren = Integer.parseInt((String) jsonObject
					.get("hasChildren"));
		}

		String nationality = (String) jsonObject.get("nationality");

		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		if (userid != null && !userid.trim().equals("")) {
			hashMap.put("userid", userid);
		}
		if (byWay != null) {
			hashMap.put("byWay", byWay);
		}
		if (modifiedRegTime != null && !modifiedRegTime.trim().equals("")) {
			hashMap.put("modifiedRegTime", modifiedRegTime);
		}
		if (imei != null && !imei.trim().equals("")) {
			hashMap.put("imei", imei);
		}
		if (username != null && !username.trim().equals("")) {
			hashMap.put("username", username);
		}
		if (nickname != null && !nickname.trim().equals("")) {
			hashMap.put("nickname", nickname);
		}
		if (phoneNum != null && !phoneNum.trim().equals("")) {
			hashMap.put("phoneNum", phoneNum);
		}
		if (email != null && !email.trim().equals("")) {
			hashMap.put("email", email);
		}
		if (name != null && !name.trim().equals("")) {
			hashMap.put("name", name);
		}
		if (sex != null) {
			hashMap.put("sex", sex);
		}
		if (modifiedBirthday != null && !modifiedBirthday.trim().equals("")) {
			hashMap.put("modifiedBirthday", modifiedBirthday);
		}
		if (nation != null) {
			hashMap.put("nation", nation);
		}
		if (politicalStatus != null) {
			hashMap.put("politicalStatus", politicalStatus);
		}
		if (educationLevel != null) {
			hashMap.put("educationLevel", educationLevel);
		}
		if (occupation != null) {
			hashMap.put("occupation", occupation);
		}
		if (resident != null && !resident.trim().equals("")) {
			hashMap.put("resident", resident);
		}
		if (maritalStatus != null) {
			hashMap.put("maritalStatus", maritalStatus);
		}
		if (hasChildren != null) {
			hashMap.put("hasChildren", hasChildren);
		}
		if (nationality != null && !nationality.trim().equals("")) {
			hashMap.put("nationality", nationality);
		}
		return null;
	}

	@RequestMapping("/showUserAnswerQuestionInformation.do")
	public ModelAndView showUserAnswerQuestionInformation(
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manager/NiShowUserAnswerQuestionInformation");
		return modelAndView;
	}

}
