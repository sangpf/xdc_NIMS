<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>统计管理-报告统计</title>
  <link rel="stylesheet" href="${ctx}/js/sang.css" type="text/css">
  </head>
  
<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    
    <!-- 报告统计内容部分 -->
   

      <!-- Main content -->
      
        <div class="panel panel-default">
          <div class="panel-body">
            <form class="form-horizontal"  method="post" action="${ctx}/platform/niReportStatisticsList.do" id ="reportStatisticsListForm">
           
              <div class="row mb10">
                <div class="col-sm-2">
                  <select class="form-control select-sm" id="reportDetail" name = "reportDetail">
                    <option value="0">报告id</option>
                    <option value="1">问卷名称</option>
                  </select>
                </div>
                <div class="input-group input-group-sm col-sm-3">
                  <input id="reportContent" type="text" class="form-control" name = "reportContent">
                  <span class="input-group-btn">
                      <button type="button" onclick="searchReportStatistics()" class="btn btn-info btn-flat">Go!</button>
                   </span>
                </div>
              </div>
              <div class="row mb10">
                <div class="col-sm-2">
                  <label class="select-label">渠道</label>
                  <select class="form-control select-sm" id="reportChannel" name="reportChannel" style="width:75%;">
                    <option value="">请选择渠道</option>
                    <option value="1">玩校</option>
                    <option value="2">微信</option>
                    <option value="3">APP</option>
                  </select>
                </div>
                <div class="col-sm-3">
                
                  <label class="select-label">状态</label>
                  <select class="form-control select-sm" id="reportStatus" name="reportStatus" style="width:75%;">
                    <option value="">请选择报告状态</option>
                    <option value="1">草稿</option>
                    <option value="2">完成</option>
                    <option value="3">发布</option>
                    <option value="4">废弃</option>
                  </select>
                
                </div>
              </div>
            
            
              <div class="row mb10">
              
               <div class="col-sm-2">
				  <label class="select-label">类型</label>
                  <select class="form-control select-sm" id="selectReportType"  name="selectReportType"  style="width:75%;">
                  	<option value="">请选择类型</option>
                  	<option value="1">调查</option>
                  	<option value="2">测评</option>
                  	<option value="3">投票</option>
                  </select>
               
                	</div>
               </div>
           
           </form>
          </div>

        </div>
		
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="button-group">
             
                <button type="button" class="btn btn-default btn-sm"  onclick="exportReportStatisticsSheet();" style="width: 12%;">导出表格</button>              
                
				
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
                  <th>报告id</th>
                  <th>问卷名称</th>
                  <th>渠道</th>
                  <th>状态</th>
                  <th>类型</th>
                  <th>阅读次数</th>
                  <th>阅读修正数</th>
                  <th>分享次数</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                   <c:forEach items="${niReportStatisticsList}"  var="niReportStatistics" varStatus="n">
                   	  <!-- 玩校渠道 -->
                      <tr>
                        <td>
                        <input type="checkbox" id = "checkReportStatistics" name="checkReportStatistics" value="${niReportStatistics.reportId }">
                        <input type="hidden" id="channel" name="channel" value="${niReportStatistics.channel }" >                       
                        <input type="hidden" id="type" name="type" value="${niReportStatistics.qnType }" >
                        </td>
                        <td>${n.index+1}</td>
                        <td>${niReportStatistics.reportId}</td>
                        <td>
                        <div style="width: 172px;white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
                        ${niReportStatistics.qnName}
                        </div>
                        </td>
                        <td>
                        	<c:if test="${niReportStatistics.channel == 1}">玩校</c:if>
                            <c:if test="${niReportStatistics.channel == 2}">微信</c:if>
                            <c:if test="${niReportStatistics.channel == 3}">APP</c:if>
                        </td>
                        <td>
                        	<c:if test="${niReportStatistics.reportStatus == 1}">草稿</c:if>
                            <c:if test="${niReportStatistics.reportStatus == 2}">完成</c:if>
                            <c:if test="${niReportStatistics.reportStatus == 3}">发布</c:if>
                            <c:if test="${niReportStatistics.reportStatus == 4}">废弃</c:if>             
                        </td>
                         <td>
	                         <c:if test="${niReportStatistics.qnType == 1}">调查</c:if> 
	                         <c:if test="${niReportStatistics.qnType == 2}">测评</c:if> 
	                         <c:if test="${niReportStatistics.qnType == 3}">投票</c:if>  
                         </td>
                         <td>
                         	 <%-- <c:if test="${niReportStatistics.channel == 1}">${niReportStatistics.wanxViewNum }</c:if> 
	                         <c:if test="${niReportStatistics.channel == 2}">${niReportStatistics.weixViewNum }</c:if> 
	                         <c:if test="${niReportStatistics.channel == 3}">${niReportStatistics.appViewNum }</c:if>  --%>
	                         ${niReportStatistics.readNum } 
                         </td>
						 <td>${niReportStatistics.correctNum }</td>
                         <td>${niReportStatistics.shareNum}</td>
                         <td>查看</td>
                      </tr>               
                  </c:forEach>

                </tbody>
              </table>
             </div> 
           </div> 
        </div>
   
    </div>
    
    <!-- 报告统计内容部分结束 -->
    

  <div class="control-sidebar-bg"></div>
  
  <!-- ./wrapper -->
  <script src="${ctx}/static/js/moment.min.js"></script>
  <script src="${ctx}/js/validate.js"></script>
  
  <script>

  
  var reportContentL = 20;
  //按条件查询报告统计表
  function searchReportStatistics(){
	  
	  var reportDetail=$("#reportDetail").val();
	  var reportContent = $("#reportContent").val();
	  
	  if(reportDetail!=null && reportContent!=null && reportDetail.length !=0 && reportContent.length !=0){
		  //判断输入长度
		   if(reportContent.trim().length>reportContentL){
			   showMessageDialog("报告id或问卷名称不能超过"+reportContentL+"个字符!");
				  return;
		   }else{
				  if(reportDetail==0){
					  //id
					  
					  if(checkNumber(reportContent) == false){
						  showMessageDialog("您输入的不是数字,请重新输入!");
						  return;
					  }else if(checkNumber(reportContent) == true){
						  if(checkNum(reportContent) == false){
							  //再判断是否为0开头
							  showMessageDialog("您输入的数字不能以0开头,请重新输入!");
							  return;
						  }
					  }
				  }else if(reportDetail==1){
					  //名称
					  if(checkQuote(reportContent) == true){
						  showMessageDialog("不能包含特殊字符!");
					  		return;
					  }else if(reportContent.trim().length == 0){
						  showMessageDialog("不能输入空字符串,请重新输入!");
						  return;
					  }
				  }
		   }
		  

	  }

	  
	  $('#reportStatisticsListForm').submit();
	  

  }
  
  //将页面中展示的数据导出表格
/*    function exportReportStatisticsSheet(){
	 var checkReportStatistics_arr=document.getElementsByName("checkReportStatistics");
	 var channel_arr=document.getElementsByName("channel");
	 var type_arr=document.getElementsByName("type");
	 var reportId="";
	 var channel="";
	 var type="";
	 for(var i=0;i<checkReportStatistics_arr.length;i++){
		 reportId=checkReportStatistics_arr[i].value+"!"+reportId;
		 channel=channel_arr[i].value+"!"+channel;
		 type=type_arr[i].value+"!"+type;
	 }
	 
	 window.location.href="${ctx}/platform/exportReportStatisticsSheet.do?reportId="+reportId+"&channel="+channel+"&type="+type;

  } */
  
   //将页面中展示的数据导出表格    注释 :测试新接口
   function exportReportStatisticsSheet(){
		 var checkReportStatistics_arr=document.getElementsByName("checkReportStatistics");
		 var channel_arr=document.getElementsByName("channel");
		 var type_arr=document.getElementsByName("type");
		 var reportId="";
		 var channel="";
		 var type="";
		 for(var i=0;i<checkReportStatistics_arr.length;i++){
			 reportId=checkReportStatistics_arr[i].value+"!"+reportId;
			 channel=channel_arr[i].value+"!"+channel;
			 type=type_arr[i].value+"!"+type;
		 }
		 
		 window.location.href="${ctx}/platform/exportReportStatisticsSheet_new.do?reportId="+reportId+"&channel="+channel+"&type="+type;

	  } 
  
  
  </script>
</body>
</html>
