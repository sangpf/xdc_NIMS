<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html>
  <head>
    
    
    <title>统计管理-用户统计</title>
    
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	   
  <link rel="stylesheet" href="${ctx}/js/sang.css" type="text/css">
 <style>
    .mb10 {
      margin-bottom: 1px;
    }
    
    .select-label {
      float: left;
      line-height: 30px;
      font-size: 12px;
      margin-right: 10px;
    }
    
    .select-sm {
      height: 30px;
      font-size: 12px;
    }
    
    .ft-12 {
      font-size: 12px;
    }
    
    .pd-15 {
      padding: 0 15px;
    }
    
    .button-group button {
      width: 100px;
      margin: auto 10px;
    }
     .panel {
   	  margin-bottom: 10px;
	}
  </style>
  
  </head>
  
<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    
    <!-- 用户统计内容部分 -->
   

      <!-- Main content -->
      
        <div class="panel panel-default">
          <div class="panel-body">
            <form class="form-horizontal"  method="post" action="${ctx}/platform/niUserStatisticsList.do" id ="userStatisticsListForm">
           
              <div class="row mb10">
                <div class="col-sm-2">
                  <label class="select-label">渠道</label>
                  <select class="form-control select-sm" id="deliveryChannel" name="deliveryChannel" style="width:75%;">
                    <option value="">未实现该功能</option>
                    <option value="1">玩校</option>
                    <option value="2">微信</option>
                    <option value="3">APP</option>
                  </select>
                </div>
                <div class="input-group input-group-sm col-sm-3" style="width: 285px">
              
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <input type="text" class="form-control pull-right" name="reservation" id="reservation" style="height:30px;" placeholder="请选择日期">
               
                  <span class="input-group-btn">
                      <button type="button" onclick="searchUserStatistics()" class="btn btn-info btn-flat">Go!</button>
                   </span>
                   
                </div>
              </div>

            
            
     
           </form>
          </div>

        </div>
		
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="button-group">
             
                <button type="button" class="btn btn-default btn-sm"  onclick="exportUserStatisticsSheet();" style="width: 12%;">导出表格</button>              
                
				
            </div>
          </div>
        </div>

	
        <div class="panel panel-default">
           <div class="box">
             <div class="box-body"> 
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th></th>
                  <th>编号</th>
                  <th>时间</th>
                  <th>登陆人数</th>
                  <th>初访用户数</th>
                  <th>活跃用户数</th>
                  <th>答题人数</th>
                  <th>提交人数</th>
                  <th>分享报告人数</th>
                  <th>奖励领取人数</th>
                </tr>
                </thead>
                <tbody>
                   <c:forEach items="${niUserStatisticsList}"  var="niUserStatistics" varStatus="n">
                      <tr>
                        <td>
                        <input type="checkbox" id = "checkUserStatistics" name="checkUserStatistics" value="${niUserStatistics.deliveryId }">
                        <input type="hidden" id="channel" name="channel" value="${niUserStatistics.channel }" >                       
                        <input type="hidden" id="type" name="type" value="${niUserStatistics.type }" >
                        </td>
                        <td>${n.index+1}</td>
                        <td>${niUserStatistics.deliveryId}</td>
                        <td>
                        <div style="width: 172px;white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
                        ${niUserStatistics.qnName}
                        </div>
                        </td>
                        <td>
                        	<c:if test="${niUserStatistics.channel == 1}">玩校</c:if>
                            <c:if test="${niUserStatistics.channel == 2}">微信</c:if>
                            <c:if test="${niUserStatistics.channel == 3}">APP</c:if>
                        </td>
                        <td>
                        	<c:if test="${niUserStatistics.status == 1}">待投放</c:if>
                            <c:if test="${niUserStatistics.status == 2}">投放中</c:if>
                            <c:if test="${niUserStatistics.status == 3}">暂停中</c:if>
                            <c:if test="${niUserStatistics.status == 4}">人工终止</c:if>
                            <c:if test="${niUserStatistics.status == 5}">时间完成</c:if>
                            <c:if test="${niUserStatistics.status == 6}">数量完成</c:if>             
                        </td>
                         <td>
	                         <c:if test="${niUserStatistics.type == 1}">调查</c:if> 
	                         <c:if test="${niUserStatistics.type == 2}">测评</c:if> 
	                         <c:if test="${niUserStatistics.type == 3}">投票</c:if>  
                         </td>
                         <td>${niUserStatistics.finishNum } </td>
						 <td>${niUserStatistics.awardName }</td>
                         <td>${niUserStatistics.awardId}</td>
                      </tr>               
                  </c:forEach>

                </tbody>
              </table>
             </div> 
           </div> 
        </div>
   
    </div>
    
    <!-- 用户统计内容部分结束 -->
    

  <div class="control-sidebar-bg"></div>
  
  <!-- ./wrapper -->
  <script src="${ctx}/static/js/moment.min.js"></script>
  <script src="${ctx}/js/validate.js"></script>
  
  <script>

  
  var userContentL = 20;
  //按条件查询用户统计表
  function searchUserStatistics(){
	  
	  var userDetail=$("#userDetail").val();
	  var userContent = $("#userContent").val();
	  
	  if(userDetail!=null && userContent!=null && userDetail.length !=0 && userContent.length !=0){
		  //判断输入长度
		   if(userContent.trim().length>userContentL){
			   showMessageDialog("报告id或问卷名称不能超过"+userContentL+"个字符!");
				  return;
		   }else{
				  if(userDetail==0){
					  //id
					  
					  if(checkNumber(userContent) == false){
						  showMessageDialog("您输入的不是数字,请重新输入!");
						  return;
					  }else if(checkNumber(userContent) == true){
						  if(checkNum(userContent) == false){
							  //再判断是否为0开头
							  showMessageDialog("您输入的数字不能以0开头,请重新输入!");
							  return;
						  }
					  }
				  }else if(userDetail==1){
					  //名称
					  if(checkQuote(userContent) == true){
						  showMessageDialog("不能包含特殊字符!");
					  		return;
					  }else if(userContent.trim().length == 0){
						  showMessageDialog("不能输入空字符串,请重新输入!");
						  return;
					  }
				  }
		   }
		  

	  }

	  
	  $('#userStatisticsListForm').submit();
	  

  }
  
  //将页面中展示的数据导出表格
   function exportUserStatisticsSheet(){
	 var checkUserStatistics_arr=document.getElementsByName("checkUserStatistics");
	 var channel_arr=document.getElementsByName("channel");
	 var type_arr=document.getElementsByName("type");
	 var deliveryId="";
	 var channel="";
	 var type="";
	 for(var i=0;i<checkUserStatistics_arr.length;i++){
		 deliveryId=checkUserStatistics_arr[i].value+"!"+deliveryId;
		 channel=channel_arr[i].value+"!"+channel;
		 type=type_arr[i].value+"!"+type;
	 }
	 
	 window.location.href="${ctx}/platform/exportUserStatisticsSheet.do?deliveryId="+deliveryId+"&channel="+channel+"&type="+type;

  } 
  
  
  </script>
</body>
</html>
