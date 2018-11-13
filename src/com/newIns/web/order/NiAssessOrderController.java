package com.newIns.web.order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newIns.dao.assess.NiAssessOrderMapper;

@Controller
@RequestMapping("/platform")
@PropertySource(value = "classpath:jdbc.properties")
public class NiAssessOrderController {
	private static Logger log = Logger.getLogger(NiAssessOrderController.class); 
	
	@Resource
	private NiAssessOrderMapper niAssessOrderMapper;
	
	// 数据库连接信息
//	@Value("${jdbcUrl}")
//	private String jdbcUrl;
//	@Value("${user}")
//	private String user;
//	@Value("${password}")
//	private String password;
	
	// 根据投放id 查询订单表中的数据
	@RequestMapping("/getAssessOrder_count_by_deliveryId.do")
	public void getAssessOrder_count_by_deliveryId(Integer deliveryId){
		int count_order_deliveryId = niAssessOrderMapper.get_count_order_by_deliveryId(deliveryId);
		
		log.info("---------------->> 测试数据 ,  根据投放id 查询订单表中的数据  , 使用mybatis查询 :"+count_order_deliveryId);
	}
	
	// 根据投放id 查询订单表中的数据
	@RequestMapping("/getAssessOrder_count_by_deliveryId_jdbc.do")
	public void getAssessOrder_count_by_deliveryId_jdbc(Integer deliveryId){
		
//        String URL= "jdbc:mysql://101.200.166.221:3306/newInsdb?characterEncoding=UTF-8";
//        String USER="developer";
//        String PASSWORD="xindongchadb";
        
        String URL= "jdbc:mysql://rm-bp1427gl4p8l1m3nl.mysql.rds.aliyuncs.com:3306/newInsdb?characterEncoding=UTF-8";
        String USER="xdc";
        String PASSWORD="Y83kldjdA";
        
        //1.加载驱动程序
        try {
			Class.forName("com.mysql.jdbc.Driver");
			//2.获得数据库链接
			Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
			//3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
			String s = "SELECT COUNT(*) AS orderNum FROM ni_assess_order WHERE deliveryId = ? ";
			PreparedStatement pst=conn.prepareStatement(s);
			
			pst.setInt(1, deliveryId);
			
			ResultSet rs=pst.executeQuery();
			//4.处理数据库的返回结果(使用ResultSet类)
			while(rs.next()){
			    System.out.println("---------------->> 测试数据 ,  根据投放id 查询订单表中的数据  使用jdbc查询 :"+ rs.getString("orderNum"));
			}        
			//关闭资源
			rs.close();
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
         String URL="jdbc:mysql://101.200.166.221:3306/newInsdb?characterEncoding=UTF-8";
         String USER="developer";
         String PASSWORD="xindongchadb";
         //1.加载驱动程序
         Class.forName("com.mysql.jdbc.Driver");
         //2.获得数据库链接
         Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
         //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
         String s = "SELECT COUNT(*) AS orderNum FROM ni_assess_order WHERE deliveryId = ? ";
         PreparedStatement pst=conn.prepareStatement(s);
         
         pst.setInt(1, 224);
         
         ResultSet rs=pst.executeQuery();
         //4.处理数据库的返回结果(使用ResultSet类)
         while(rs.next()){
             System.out.println(rs.getString("orderNum"));
         }        
         //关闭资源
         rs.close();
         pst.close();
         conn.close();
     }
	
	
}
