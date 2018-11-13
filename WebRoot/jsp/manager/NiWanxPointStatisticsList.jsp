<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html>
  <head>
    
    
    <title>统计管理-玩校积分</title>
    
	
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
    
    <!-- 玩校积分内容部分 -->
   

      <!-- Main content -->
      
        <div class="panel panel-default">
          <div class="panel-body">
            <form class="form-horizontal"  method="post" action="${ctx}/platform/niWanxPointStatisticsList.do" id ="wanxPointStatisticsListForm">
           
              <div class="row mb10">
                <div class="col-sm-2">
                  <label class="select-label">渠道</label>
                  <select class="form-control select-sm" id="deliveryChannel" name="deliveryChannel" style="width:75%;">
                    <option value="">请选择渠道</option>
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
                      <button type="button" onclick="searchWanxPointStatistics()" class="btn btn-info btn-flat">Go!</button>
                   </span>
                   
                </div>
              </div>

            
            
     
           </form>
          </div>

        </div>
		
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="button-group">
             
                <button type="button" class="btn btn-default btn-sm"  onclick="exportWanxPointStatisticsSheet();" style="width: 12%;">导出表格</button>              
                
				
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
                  <th>渠道</th>
                  <th>总积分</th>
                  <th>定奖</th>
                  <th>抽奖</th>
                  <th>活动</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                   <c:forEach items="${niWanxPointStatisticsList}"  var="niWanxPointStatistics" varStatus="n">
                      <tr>
                        <td>
                        <input type="checkbox" id = "checkWanxPointStatistics" name="checkWanxPointStatistics" value="${niWanxPointStatistics.date}">                       
                        </td>
                        <td>${n.index+1}</td>
                        <td>${niWanxPointStatistics.date}</td>
                        <td>
                        	<c:if test="${niWanxPointStatistics.qnChannel == 1}">玩校</c:if>
                            <c:if test="${niWanxPointStatistics.qnChannel == 2}">微信</c:if>
                            <c:if test="${niWanxPointStatistics.qnChannel == 3}">APP</c:if>
                        </td>
                        <td>${niWanxPointStatistics.sumPoint}</td>
                        <td>${niWanxPointStatistics.fixedPoint } </td>
						<td>${niWanxPointStatistics.lotteryPoint }</td>
                        <td>${niWanxPointStatistics.activityPoint}</td>
                        <td>查看</td>
                      </tr>               
                  </c:forEach>

                </tbody>
              </table>
             </div> 
           </div> 
        </div>
   
    </div>
    
    <!-- 玩校积分内容部分结束 -->
    

  <div class="control-sidebar-bg"></div>
  
  <!-- ./wrapper -->
  <script src="${ctx}/static/js/moment.min.js"></script>
  <script src="${ctx}/js/validate.js"></script>
  
  <script>

  
  var wanxPointContentL = 20;
  //按条件查询玩校积分
  function searchWanxPointStatistics(){
	  $('#wanxPointStatisticsListForm').submit();
  }
  
  //将页面中展示的数据导出表格
   function exportWanxPointStatisticsSheet(){
	 var checkWanxPointStatistics_arr=document.getElementsByName("checkWanxPointStatistics");
	 var date="";
	 for(var i=0;i<checkWanxPointStatistics_arr.length;i++){
		 date=checkWanxPointStatistics_arr[i].value+"!"+date;
	 }
	 
	 window.location.href="${ctx}/platform/exportWanxPointStatisticsSheet.do?date="+date;

  } 
  
  
  </script>
</body>
</html>
