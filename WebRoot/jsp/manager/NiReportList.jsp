<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>调查报告管理</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  
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
    
    <!-- 内容部分 -->
   

 <!-- Main content -->
        <section class="content">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form class="form-horizontal" method="post" action="${ctx}/platform/NiReportList.do" id="pageListForm">
                        <div class="row mb10">
                            <div class="col-sm-2">
                                <select class="form-control select-sm" id="searchOption" name="searchOption">
                                    <option value="">请选择</option>                                  
                                    <option value="1">报告名称</option>
                                    <option value="2">发布机构名称</option>
                                    <option value="3">报告id</option>
                                </select>
                            </div>
                            <div class="input-group input-group-sm col-sm-3">
                                <input id="searchContent" name="searchContent" type="text" class="form-control">
                 				 <span class="input-group-btn">
                    			  <button type="button" onclick="searchReport()" class="btn btn-info btn-flat">Go!</button>
                   				 </span>
                            </div>
                        </div>
                        <div class="row mb10">
                            <div class="col-sm-2">
                                <label class="select-label">类型</label>
                                <select name="qnType" id="qnType" class="form-control select-sm" style="width:75%;" >
                                    <option value="">请选择类型</option>
                                    <option value="1">调查</option>
                                    <option value="2">测评</option>
                                    <option value="3">投票</option>
                                </select>
                            </div>
                            <div class="col-sm-2">
                                <label class="select-label">分类</label>
                                <select name="reportClassId" id="reportClassId" class="form-control select-sm" style="width:75%;">
                                    <option value="">请选择分类</option>
                                    <option value="1">严肃</option>
                                    <option value="2">商业</option>
                                    <option value="3">社会</option>
                                    <option value="4">娱乐</option>
                                    <option value="5">学术</option>
                                    <option value="6">生活</option>
                                    <option value="7">学习</option>
                                    <option value="8">情感</option>
                                    <option value="9">科学</option>
                                    <option value="10">其他</option> 
                                </select>
                            </div>
                            <div class="input-group col-sm-3">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" name="reservation" class="form-control pull-right" id="reservation" style="height:30px;"
                                       placeholder="请选择日期">
                            </div>
                        </div>
                        <div class="row nb10">
                        	 <div class="col-sm-2">
								<label class="select-label">状态</label>
                                <select name="reportStatus" id="reportStatus" class="form-control select-sm" style="width:75%;">
                                    <option value="">不限</option>
                                    <option value="1">草稿</option>
                                    <option value="2">定稿</option>   
                                    <option value="3">废弃</option>                                 
                                </select>
                              </div>
                        </div>
                </div>
               </form>
            </div>
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="button-group">
                        <a href="${pageContext.request.contextPath }/platform/NiReportAdd.do">
                            <button type="button" class="btn btn-default btn-sm">添加</button>
                        </a>
                        
                            <button type="button" onclick="releaseReport()" class="btn btn-default btn-sm">定稿</button>
                        
                        	<button type="button" onclick="modifyReport()" class="btn btn-default btn-sm">修改</button>
                      
                           <button type="button" onclick="discardReport()" class="btn btn-default btn-sm">废弃</button>
                           
                            <button type="button" onclick="restoreReport()"  class="btn btn-default btn-sm">恢复</button>
                       
                       
                            <button type="button" onclick="deleteReport()"  class="btn btn-default btn-sm">删除</button>
                        </a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="box">
                    <div class="box-body">
                        <table id="example1" class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <%--<th><input id="quanxuan" type="checkbox">&nbsp;</th>--%>
                                <th></th>
                                <th>编号</th>
                                <th>ID</th>
                                <th>名称</th>
                                <th>发布机构</th>
                                <th>类型</th>
                                <th>分类</th>
                                <th>编写者</th>
                                <th>状态</th>
                                <th>发布者</th>
                                <th>定稿时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${niReportList}" var="reportItem" varStatus="n">
                                <tr>
                                    <td>
                                    	<input type="checkbox" id = "checkNiReport" name="checkNiReport" value="${reportItem.reportId }">
                                    	<input type="hidden" id="reportItemStatus" name="reportItemStatus" value="${reportItem.reportStatus}">
                                    </td>
                                    <td>${n.index+1}</td>
                                    <td>${reportItem.reportId }</td>
                                    <td>${reportItem.reportTitle }</td>
                                    <td>${reportItem.publisher }</td>
                                    <td>
                                        <c:if test="${reportItem.qnType == 1}">调查</c:if>
                                        <c:if test="${reportItem.qnType == 2}">测评</c:if>
                                        <c:if test="${reportItem.qnType == 3}">投票</c:if>
                                    </td>
                                    <td>${reportItem.reportClassName}</td>
                                    <td>${reportItem.author}</td>
                                    <td>
                                        <c:if test="${reportItem.reportStatus == 1}">草稿</c:if>
                                        <c:if test="${reportItem.reportStatus == 2}">定稿</c:if>
                                        <c:if test="${reportItem.reportStatus == 3}">废弃</c:if>
                                    </td>
                                    <td>${reportItem.editor}</td>
                                    <td><fmt:formatDate value="${reportItem.pTime}" pattern="yyyy-MM-dd"/></td>
                                    
                                    <td>
 										<button type="button" onclick="toEditPage(${reportItem.reportId},${reportItem.reportStatus})" class="btn btn-default btn-sm">编辑</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
    </div>
    </section>
    <!-- /.content -->
    
    <!-- 内容部分结束 -->
    

  <div class="control-sidebar-bg"></div>
  </div>
  <!-- ./wrapper -->
	<script src="${ctx}/static/js/moment.min.js"></script>
	  <script src="${ctx}/js/validate.js"></script>
  <script>

 
  
  //分页查询
  var reportNameOrpublisherL = 20;
  function searchReport(){
	  $('#pageListForm').submit();
  }
  

  //批量定稿（草稿->定稿）
  function releaseReport(){
	  var checkNiReport = document.getElementsByName("checkNiReport");
	  var count = 0;
	  var reportStatus = document.getElementsByName("reportItemStatus");
	  var reportId = "";
	  for(var i=0;i<checkNiReport.length;i++){
		  
		  if(checkNiReport[i].checked){
			  if(reportStatus[i].value!=1){
				  showMessageDialog("只能选择草稿状态的报告定稿！");
				  return;
			  }			  
			  count++;
			  reportId = checkNiReport[i].value+"!"+reportId;
		  }
		  
	  }
	  if(count == 0){
		  showMessageDialog("请选择要定稿的报告");
		  return;
	  }
	  
 	   $.ajax({
		  type:"post",
		  dataType:"json",
		  url:"${ctx}/platform/releaseReport.do?reportId="+reportId,		
			success:function(data){			  
			  if(data.success){					 
				  showMessageDialog(data.msg,function(){
						 if(data.success){
							 window.location.href = "${ctx}/platform/NiReportList.do";
						  }
					  })
				 				  
			  }
		  }
	  });  
  }
  //批量修改（定稿——>草稿）
  function modifyReport(){
	  var checkNiReport = document.getElementsByName("checkNiReport");
	  var reportStatus = document.getElementsByName("reportItemStatus");
	  var count = 0 ;
	  var reportId = "";
	  for(var i=0;i<checkNiReport.length;i++){
		  if(checkNiReport[i].checked){
			  if(reportStatus[i].value!=2){
				  showMessageDialog("只能修改定稿状态的报告！");
				  return;
			  }		
			  count++;
			  reportId = checkNiReport[i].value+"!"+reportId;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择要修改的报告");
		  return;
	  }
	  showConfirmDialog("确定要修改报告吗?", function(check){
		  if(check){
			  $.ajax({
				  url:"${ctx}/platform/modifyReport.do",
			  	  type:"post",
				  dataType:"json",
				  data:{
					  reportId : reportId					 
				  },
				  success:function(data){
					  showMessageDialog(data.msg,function(){
						  if(data.success){					 						  								 
								window.location.href = "${ctx}/platform/NiReportList.do";
										  }
					  });					
					  }
				  });
		  }
	  });
	  

	  
  }
  
  //批量废弃（草稿——>废弃）
  function discardReport(){
	  var checkNiReport = document.getElementsByName("checkNiReport");
	  var reportStatus = document.getElementsByName("reportItemStatus");
	  var count = 0 ;
	  var reportId = "";
	  for(var i=0;i<checkNiReport.length;i++){
		  if(checkNiReport[i].checked){
			  if(reportStatus[i].value!=1){
				  showMessageDialog("只能废弃草稿状态的报告！");
				  return;
			  }		
			  count++;
			  reportId = checkNiReport[i].value+"!"+reportId;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择要废弃的报告");
		  return;
	  }
	  
	  showConfirmDialog("确定要废弃报告吗?", function(check){
		  if(check){
			  $.ajax({
				  url:"${ctx}/platform/discardReport.do?reportId="+reportId,
			  	  type:"post",
				  dataType:"json",
				  success:function(data){
					  if(data.success){					 
						  showMessageDialog(data.msg,function(){
								 if(data.success){
									 window.location.href = "${ctx}/platform/NiReportList.do";
								  }
							  })
						 				  
					  }
				  }
				  });
		  }
	  });

  }
 //批量恢复报告（废弃——>草稿）
  function restoreReport(){
	  var checkNiReport = document.getElementsByName("checkNiReport");
	  var reportStatus = document.getElementsByName("reportItemStatus");
	  var count = 0 ;
	  var reportId = "";
	  for(var i=0;i<checkNiReport.length;i++){
		  if(checkNiReport[i].checked){
			  if(reportStatus[i].value!=3){
				  showMessageDialog("只能恢复废弃状态的报告！");
				  return;
			  }		
			  count++;
			  reportId = checkNiReport[i].value+"!"+reportId;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择要恢复的报告");
		  return;
	  }
	  $.ajax({
	  url:"${ctx}/platform/restoreReport.do?reportId="+reportId,
  	  type:"post",
	  dataType:"json",
	  success:function(data){
		  if(data.success){					 
			  showMessageDialog(data.msg,function(){
					 if(data.success){
						 window.location.href = "${ctx}/platform/NiReportList.do";
					  }
				  })
			 				  
		  }
	  }
	  });
  }
  //批量删除报告
  function deleteReport(){
	  var checkNiReport = document.getElementsByName("checkNiReport");
	  var reportStatus = document.getElementsByName("reportItemStatus");
	  var count = 0 ;
	  var reportId = "";
	  for(var i=0;i<checkNiReport.length;i++){
		  if(checkNiReport[i].checked){
			  if(reportStatus[i].value!=3){
				  showMessageDialog("只能删除废弃状态的报告！");
				  return;
			  }
			  count++;
			  reportId = checkNiReport[i].value+"!"+reportId;
		  }
	  }
	  if(count == 0){
		  alert("请选择要删除的报告");
		  return;
	  }
	  showConfirmDialog("确定要删除报告吗?", function(check){
		  if(check){
			  $.ajax({
				  url:"${ctx}/platform/deleteReport.do?reportId="+reportId,
			  	  type:"post",
				  dataType:"json",
				  success:function(data){
					  if(data.success){					 
						  showMessageDialog(data.msg,function(){
								 if(data.success){
									 window.location.href = "${ctx}/platform/NiReportList.do";
								  }
							  })
						 				  
					  }
				  }
				  });
		  }
	  });

  }
  //跳转到编辑页面
  function toEditPage(reportId,reportStatus){
	  if(reportStatus!=1){
		  showMessageDialog("只能编辑草稿状态的报告",function(){
			  window.location.href = "${ctx}/platform/NiReportList.do";
		  });
		  return;
	  }
	  window.location.href = "${ctx}/platform/editNiReport.do?reportId="+reportId;
/* 	  $.ajax({
		  url:"${ctx}/platform/editNiReport.do",
	  	  type:"post",
		  dataType:"json",
		  data:{
			  reportId : reportId
		  },
		  }); */
  }


  </script>
</body>

</html>