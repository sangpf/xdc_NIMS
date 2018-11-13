package com.newIns.dao.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newIns.model.page.Page;
import com.newIns.model.page.PageVo;

public interface PageDao {
	
	// 列表查询
	List<PageVo> listPage(Map<String, Object> hashMap);

	// 查询调查投放页面
	List<Page> findSurveyPage(Map<String, Object> hashMap);

	// 查询测评投放页面
	List<Page> findAssessPage(Map<String, Object> hashMap);

	// 查询投票投放页面
	List<Page> findVotePage(Map<String, Object> hashMap);

	// 查询报告页面
	List<Page> findReportPage(Map<String, Object> hashMap);

	//查询推文页面
	List<Page> findTweetPage(Map<String, Object> hashMap);
	
	// 添加页面
	void insertPage(Map<String, Object> hashMap);
	
	// 修改状态
	void changePageStatus(Map<String, Object> hashMap);

	// 修改是否置顶状态
	void changeTopStatus(Map<String, Object> hashMap);

	// 修改 showOrder
	void changeShowOrderStatus(HashMap<String, Object> hashMap2);

	// 设置定时
	void setTimer(Map<String, Object> hashMap);
	
	
}
