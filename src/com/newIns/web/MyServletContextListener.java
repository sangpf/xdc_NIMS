package com.newIns.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.newIns.dao.NiDaily3updateWanxMapper;
import com.newIns.dao.NiSuperListMapper;
import com.newIns.dao.assess.AssessDeliveryDao;
import com.newIns.dao.assess.NiAssessListMapper;
import com.newIns.dao.assess.NiAssessOrderMapper;
import com.newIns.dao.survey.NiSurveyOrderMapper;
import com.newIns.dao.survey.SurveyDeliveryDao;
import com.newIns.dao.vote.NiVoteOrderMapper;
import com.newIns.dao.vote.VoteDeliveryDao;

public class MyServletContextListener implements ServletContextListener{
	private static Logger log = Logger.getLogger(MyServletContextListener.class);
	//销毁时加载
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("=================>>服务器关闭的时候执行监听器!");
	}

	/**
	 * 初始化时加载
	 */
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("=================>>服务器启动的时候执行监听器!");
		ServletContext servletContext = sce.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		//调查投放
		SurveyDeliveryDao niSurveyDeliveryWanxMapper = webApplicationContext.getBean(SurveyDeliveryDao.class);
		//测评投放
		AssessDeliveryDao niAssessDeliveryWanxMapper = webApplicationContext.getBean(AssessDeliveryDao.class);
		//投票投放
		VoteDeliveryDao niVoteDeliveryWanxMapper = webApplicationContext.getBean(VoteDeliveryDao.class);
		//订单
		NiSurveyOrderMapper niSurveyOrderMapper = webApplicationContext.getBean(NiSurveyOrderMapper.class);
		NiAssessOrderMapper niAssessOrderMapper = webApplicationContext.getBean(NiAssessOrderMapper.class);
		NiVoteOrderMapper niVoteOrderMapper = webApplicationContext.getBean(NiVoteOrderMapper.class);
		
		//三更页面
		NiDaily3updateWanxMapper niDaily3updateWanxMapper = webApplicationContext.getBean(NiDaily3updateWanxMapper.class);
		//超级调查
		NiSuperListMapper niSuperListMapper = webApplicationContext.getBean(NiSuperListMapper.class);
		//测一发页面
		NiAssessListMapper niAssessListMapper = webApplicationContext.getBean(NiAssessListMapper.class);
		
		
		Runnable scheduledTaskTool = scheduledTaskTool(
				niSurveyDeliveryWanxMapper,niAssessDeliveryWanxMapper,niVoteDeliveryWanxMapper,
				niSurveyOrderMapper,niAssessOrderMapper,niVoteOrderMapper,
				niDaily3updateWanxMapper,niSuperListMapper,niAssessListMapper
				);
		Thread thread = new Thread(scheduledTaskTool);
		thread.start();
	}
	
	/**
	 * 定时任务工具
	 */
	public Runnable scheduledTaskTool(
			final SurveyDeliveryDao niSurveyDeliveryWanxMapper,
			final AssessDeliveryDao niAssessDeliveryWanxMapper,
			final VoteDeliveryDao niVoteDeliveryWanxMapper,
			final NiSurveyOrderMapper niSurveyOrderMapper,
			final NiAssessOrderMapper niAssessOrderMapper,
			final NiVoteOrderMapper niVoteOrderMapper,
			final NiDaily3updateWanxMapper niDaily3updateWanxMapper,
			final NiSuperListMapper niSuperListMapper,
			final NiAssessListMapper niAssessListMapper){
		//修改定时器时间间隔
		final long timeInterval = 1000*60*5;  // 生产环境 五分钟
		//final long timeInterval = 1000*5;   // 测试环境 时间 五秒钟
		
		Runnable runnable = new Runnable() {
			public void run() {
				while(true){
					try {
						Thread.sleep(timeInterval);
						log.info("============================>>执行自动检查投放管理问卷是否已经数量完成 , 或者时间完成!");
						
						//-------------测评问卷
						//定期检查 , 判断时间完成
						int updateStatuByeTime = niAssessDeliveryWanxMapper.updateStatuByeTime();
						if(updateStatuByeTime>0){
							log.info("============================>>测评问卷时间完成成功条数!"+updateStatuByeTime);
						}
						//定期检查 , 判断数量完成
						//niAssessDeliveryWanxMapper.updateStatuByTagNum();
						
						//-------------调查问卷
						//时间完成
						int updateStatuByeTime2 = niSurveyDeliveryWanxMapper.updateStatuByeTime();
						if(updateStatuByeTime2>0){
							log.info("============================>>调查问卷时间完成成功条数!"+updateStatuByeTime2);
						}
						//数量完成
						//niSurveyDeliveryWanxMapper.updateStatuByTagNum();
						
						//-------------投票问卷
						//时间完成
						int updateStatuByeTime3 = niVoteDeliveryWanxMapper.updateStatuByeTime();
						if(updateStatuByeTime3>0){
							log.info("============================>>投票问卷时间完成成功条数!"+updateStatuByeTime3);
						}
						//数量完成
						//niVoteDeliveryWanxMapper.updateStatuByTagNum();
						
						//  三更页面发布 检测设定发布时间  将待发布状态页面
						niDaily3updateWanxMapper.updateStateByTimer();
						log.info("============================>>检测三更列表页面定时发布!");
						
						// 超姐调查页面 定时发布
						niSuperListMapper.updateStateByTimer();
						log.info("============================>>检测超级调查页面定时发布!");
						
						//测一发页面 定时检测 发布
						niAssessListMapper.updateStateByTimer();
						log.info("============================>>检测测一发页面定时发布!");
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		return runnable;
	}
	
	
}
