<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html>
  <head>
    
    
    <title>统计管理-抽奖统计</title>
    
	
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
    
    <!-- 抽奖统计内容部分 -->
   

      <!-- Main content -->
      
        <div class="panel panel-default">
          <div class="panel-body">
            <form class="form-horizontal"  method="post" action="${ctx}/platform/niLotteryStatisticsList.do" id ="lotteryStatisticsListForm">
           
              <div class="row mb10">
                <div class="col-sm-2">
                  <select class="form-control select-sm" id="lotteryDetail" name = "lotteryDetail">
                    <option value="0">投放id</option>
                    <option value="1">问卷名称</option>
                  </select>
                </div>
                <div class="input-group input-group-sm col-sm-3">
                  <input id="lotteryContent" type="text" class="form-control" name = "lotteryContent" onmouseover="this.focus()" placeholder="请输入投放id或问卷名称" >
                  <span class="input-group-btn">
                      <button type="button" onclick="searchLotteryStatistics()" class="btn btn-info btn-flat">Go!</button>
                   </span>
                </div>
              </div>
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
                <div class="col-sm-3">
                
                  <label class="select-label">状态</label>
                  <select class="form-control select-sm" id="deliveryStatus" name="deliveryStatus" style="width:75%;">
                    <option value="">请选择投放状态</option>
                    <option value="1">待投放</option>
                    <option value="2">投放中</option>
                    <option value="3">暂停中</option>
                    <option value="4">人工终止</option>
                    <option value="5">时间完成</option>
                    <option value="6">数量完成</option>
                  </select>
                
                </div>
              </div>
            
            
     
           </form>
          </div>

        </div>
		
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="button-group">
             
                <button type="button" class="btn btn-default btn-sm"  onclick="exportLotteryStatisticsSheet();" style="width: 12%;">导出表格</button>              
                
				
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
                  <th>投放id</th>
                  <th>问卷名称</th>
                  <th>渠道</th>
                  <th>状态</th>
                  <th>类型</th>
                  <th>有效订单数</th>
                  <th>参与抽奖人数</th>
                  <th>三等奖名称</th>
                  <th>三等奖奖品ID</th>
                  <th>领取人数</th>
                  <th>二等奖名称</th>
                  <th>二等奖奖品ID</th>
                  <th>领取人数</th>
                  <th>一等奖名称</th>
                  <th>一等奖奖品ID</th>
                  <th>领取人数</th>
                </tr>
                </thead>
                <tbody>
                   <c:forEach items="${niLotteryStatisticsList}"  var="niLotteryStatistics" varStatus="n">
                      <tr>
                        <td>
                        <input type="checkbox" id = "checkLotteryStatistics" name="checkLotteryStatistics" value="${niLotteryStatistics.deliveryId }">
                        <input type="hidden" id="channel" name="channel" value="${niLotteryStatistics.channel }" >                       
                        <input type="hidden" id="type" name="type" value="${niLotteryStatistics.type }" >
                        </td>
                        <td>${n.index+1}</td>
                        <td>${niLotteryStatistics.deliveryId}</td>
                        <td>
                        <div style="width: 100px;white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
                        ${niLotteryStatistics.qnName}
                        </div>
                        </td>
                        <td>
                        	<c:if test="${niLotteryStatistics.channel == 1}">玩校</c:if>
                            <c:if test="${niLotteryStatistics.channel == 2}">微信</c:if>
                            <c:if test="${niLotteryStatistics.channel == 3}">APP</c:if>
                        </td>
                        <td>
                        	<c:if test="${niLotteryStatistics.status == 1}">待投放</c:if>
                            <c:if test="${niLotteryStatistics.status == 2}">投放中</c:if>
                            <c:if test="${niLotteryStatistics.status == 3}">暂停中</c:if>
                            <c:if test="${niLotteryStatistics.status == 4}">人工终止</c:if>
                            <c:if test="${niLotteryStatistics.status == 5}">时间完成</c:if>
                            <c:if test="${niLotteryStatistics.status == 6}">数量完成</c:if>             
                        </td>
                         <td>
	                         <c:if test="${niLotteryStatistics.type == 1}">调查</c:if> 
	                         <c:if test="${niLotteryStatistics.type == 2}">测评</c:if> 
	                         <c:if test="${niLotteryStatistics.type == 3}">投票</c:if>  
                         </td>
                         <td>${niLotteryStatistics.validOrderNum } </td>
						 <td>${niLotteryStatistics.takePartNum }</td>
                         <td>${niLotteryStatistics.award3Name}</td>
                         <td>${niLotteryStatistics.award3Id}</td>
                         <td>${niLotteryStatistics.award3ReceiveNum}</td>
                         <td>${niLotteryStatistics.award2Name}</td>
                         <td>${niLotteryStatistics.award2Id}</td>
                         <td>${niLotteryStatistics.award2ReceiveNum}</td>
                         <td>${niLotteryStatistics.award1Name}</td>
                         <td>${niLotteryStatistics.award1Id}</td>
                         <td>${niLotteryStatistics.award1ReceiveNum}</td>
                      </tr>               
                  </c:forEach>

                </tbody>
              </table>
             </div> 
           </div> 
        </div>
   
    </div>
    
    <!-- 抽奖统计内容部分结束 -->
    

  <div class="control-sidebar-bg"></div>
  
  <!-- ./wrapper -->
  <script src="${ctx}/static/js/moment.min.js"></script>
  <script src="${ctx}/js/validate.js"></script>
  
  <script>

  
  var lotteryContentL = 20;
  //按条件查询抽奖统计表
  function searchLotteryStatistics(){
	  
	  var lotteryDetail=$("#lotteryDetail").val();
	  var lotteryContent = $("#lotteryContent").val();
	  
	  if(lotteryDetail!=null && lotteryContent!=null && lotteryDetail.length !=0 && lotteryContent.length !=0){
		  //判断输入长度
		   if(lotteryContent.trim().length>lotteryContentL){
			   showMessageDialog("报告id或问卷名称不能超过"+lotteryContentL+"个字符!");
				  return;
		   }else{
				  if(lotteryDetail==0){
					  //id
					  
					  if(checkNumber(lotteryContent) == false){
						  showMessageDialog("您输入的不是数字,请重新输入!");
						  return;
					  }else if(checkNumber(lotteryContent) == true){
						  if(checkNum(lotteryContent) == false){
							  //再判断是否为0开头
							  showMessageDialog("您输入的数字不能以0开头,请重新输入!");
							  return;
						  }
					  }
				  }else if(lotteryDetail==1){
					  //名称
					  if(checkQuote(lotteryContent) == true){
						  showMessageDialog("不能包含特殊字符!");
					  		return;
					  }else if(lotteryContent.trim().length == 0){
						  showMessageDialog("不能输入空字符串,请重新输入!");
						  return;
					  }
				  }
		   }
		  

	  }

	  
	  $('#lotteryStatisticsListForm').submit();
	  

  }
  
  //将页面中展示的数据导出表格
   function exportLotteryStatisticsSheet(){
	 var checkLotteryStatistics_arr=document.getElementsByName("checkLotteryStatistics");
	 var channel_arr=document.getElementsByName("channel");
	 var type_arr=document.getElementsByName("type");
	 var deliveryId="";
	 var channel="";
	 var type="";
	 for(var i=0;i<checkLotteryStatistics_arr.length;i++){
		 deliveryId=checkLotteryStatistics_arr[i].value+"!"+deliveryId;
		 channel=channel_arr[i].value+"!"+channel;
		 type=type_arr[i].value+"!"+type;
	 }
	 
	 window.location.href="${ctx}/platform/exportLotteryStatisticsSheet.do?deliveryId="+deliveryId+"&channel="+channel+"&type="+type;

  } 
  
  
  </script>
</body>
</html>
