<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>统计管理-广告统计</title>
    
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	   
  <link rel="stylesheet" href="${ctx}/js/sang.css">
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
  </style>
  
  </head>
  
<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    
    <!-- 广告统计内容部分 -->
   

      <!-- Main content -->
      
        <div class="panel panel-default">
          <div class="panel-body">
            <form class="form-horizontal"  method="post" action="${ctx}/platform/niAdStatisticsList.do" id ="adStatisticsListForm">
           
              <div class="row mb10">
                <div class="col-sm-2">
                  <select class="form-control select-sm" id="adDetail" name = "adDetail">
                    <option value="0">广告id</option>
                    <option value="1">广告名称</option>
                  </select>
                </div>
                <div class="input-group input-group-sm col-sm-3">
                  <input id="adContent" type="text" class="form-control" name = "adContent">
                  <span class="input-group-btn">
                      <button type="button" onclick="searchAdStatistics()" class="btn btn-info btn-flat">Go!</button>
                   </span>
                </div>
              </div>
              <div class="row mb10">
                <div class="col-sm-2">
                  <label class="select-label">产品</label>
                  <select class="form-control select-sm" id="adChannel" name="adChannel" style="width:75%;">
                    <option value="">请选择渠道</option>
                    <option value="1">玩校</option>
                    <option value="2">微信</option>
                    <option value="3">APP</option>
                  </select>
                </div>
                <div class="col-sm-3">
                
                  <label class="select-label">广告状态</label>
                  <select class="form-control select-sm" id="adStatus" name="adStatus" style="width:75%;">
                    <option value="">请选择广告状态</option>
                    <option value="1">有效</option>
                    <option value="2">无效</option>
                  </select>
                
                </div>
              </div>
            
           
           </form>
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
                  <th>广告id</th>
                  <th>广告名称</th>
                  <th>广告位置</th>
                  <th>状态</th>
                  <th>产品</th>
                  <th>浏览量</th>
                  <th>点击量</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                   <c:forEach items="${niAdStatisticsList}" var="adStatisticsList" varStatus="n">
                      <tr>
                        <td><input type="checkbox" id = "checkAdStatistics" name="checkAdStatistics" value="${adStatisticsList.adId }"></td>
                        <td>${n.index+1}</td>
                        <td>${adStatisticsList.adId}</td>
                        <td>${adStatisticsList.adTitle}</td>
                        <td>
                        	<c:if test="${adStatisticsList.adPosCode =='wanx_c1' }">玩校轮播1广告</c:if>
                        	<c:if test="${adStatisticsList.adPosCode =='wanx_c2' }">玩校轮播2广告</c:if>
                        	<c:if test="${adStatisticsList.adPosCode =='wanx_c3' }">玩校轮播3广告</c:if>
                        	<c:if test="${adStatisticsList.adPosCode =='wanx_c4' }">玩校轮播4广告</c:if>
                        	<c:if test="${adStatisticsList.adPosCode =='wanx_c5' }">玩校轮播5广告</c:if>
                        	<c:if test="${adStatisticsList.adPosCode =='wanx_banner1' }">玩校底部广告</c:if>
                        	<c:if test="${adStatisticsList.adPosCode =='wanx_banner2' }">玩校超级上部广告</c:if>
                        	<c:if test="${adStatisticsList.adPosCode =='wanx_banner3' }">玩校测一发上部广告</c:if>
                        	<c:if test="${adStatisticsList.adPosCode =='wanx_banner4' }">玩校真相上部广告</c:if>
                        	<c:if test="${adStatisticsList.adPosCode =='wanx_banner5' }">玩校答题结果页广告</c:if>
                        </td>
                        <td>
                        	<c:if test="${adStatisticsList.adStatus == 1}">有效</c:if>
                            <c:if test="${adStatisticsList.adStatus == 2}">无效</c:if>                 
                        </td>
                         <td>
                         	<c:if test="${adStatisticsList.adChannel == 1}">玩校</c:if>
                            <c:if test="${adStatisticsList.adChannel == 2}">微信</c:if>
                            <c:if test="${adStatisticsList.adChannel == 3}">APP</c:if>    
						</td>
						 <td>${adStatisticsList.adViewCount}</td>
                         <td>${adStatisticsList.adTapCount}</td>
                         <td>查看</td>
                       
                    
                      </tr>
                  </c:forEach>

                </tbody>
              </table>
            </div>
          </div>
        </div>
    
    </div>
    
    <!-- 广告统计内容部分结束 -->
    

  <div class="control-sidebar-bg"></div>
  
  <!-- ./wrapper -->
  <script src="${ctx}/static/js/moment.min.js"></script>
  <script src="${ctx}/js/validate.js"></script>
  
  <script>

  
  var adContentL = 20;
  //按条件查询广告统计表
  function searchAdStatistics(){
	  
	  var adDetail=$("#adDetail").val();
	  var adContent = $("#adContent").val();
	  
	  if(adDetail!=null && adContent!=null && adDetail.length !=0 && adContent.length !=0){
		  //判断输入长度
		   if(adContent.trim().length>adContentL){
			   showMessageDialog("广告id或广告名称不能超过"+adContentL+"个字符!");
				  return;
		   }else{
				  if(adDetail==0){
					  //id
					  
					  if(checkNumber(adContent) == false){
						  showMessageDialog("您输入的不是数字,请重新输入!");
						  return;
					  }else if(checkNumber(adContent) == true){
						  if(checkNum(adContent) == false){
							  //再判断是否为0开头
							  showMessageDialog("您输入的数字不能以0开头,请重新输入!");
							  return;
						  }
					  }
				  }else if(adDetail==1){
					  //名称
					  if(checkQuote(adContent) == true){
						  showMessageDialog("不能包含特殊字符!");
					  		return;
					  }else if(adContent.trim().length == 0){
						  showMessageDialog("不能输入空字符串,请重新输入!");
						  return;
					  }
				  }
		   }
		  

	  }
/* 	  if(adContent.trim().length>adContentL){
		  alert("广告id或广告名称不能超过"+adContentL+"字符");
		  return;
	  }else if(checkQuote(adContentL) == true){
		  alert("不能包含特殊字符");
		  return;
	  } */
	  
	  $('#adStatisticsListForm').submit();
	  

  }
  </script>
</body>
</html>
