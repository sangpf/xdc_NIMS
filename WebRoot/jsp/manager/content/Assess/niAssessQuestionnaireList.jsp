<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>测评问卷管理</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${ctx}/js/sang.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    
    <!-- 内容部分 -->
      <!-- Main content -->
        <div class="panel panel-default">
          <div class="panel-body">
            <form class="form-horizontal"  method="post" action="${ctx}/platform/niAssessQuestionnaireList.do" id="searchform">
              <div class="row mb10">
                <div class="col-sm-2">
                  <select class="form-control select-sm" name="sqnpublish" id="sqnpublish">
                    <option value="0">问卷id</option>
                    <option value="1">问卷名称</option>
                  </select>
                </div>
                
                <div class="input-group input-group-sm col-sm-3">
                  <input id="sqnNameOrpublisherName" name="sqnNameOrpublisherName" type="text" class="form-control">
                  <span class="input-group-btn">
                      <button type="button" onclick="searchSueryQuestion()" class="btn btn-info btn-flat">查询</button>
                   </span>
                </div>
              </div>
              
              <div class="row mb10">
                <div class="col-sm-2">
                  <label class="select-label">分类</label>
                  <select class="form-control select-sm" name="qnclassid" id="qnclassid" style="width:75%;">
                    <option value="" selected="selected">请选择分类</option>
                    <c:forEach items="${niAqnclassDict_list }" var="na">
	                    <option value="${na.aqnclassid }">${na.aqnclassname }</option>
                    </c:forEach>
                  </select>
                </div>

	              <div class="row nb10">
	                  <label class="select-label">状态</label>
	                  <select class="form-control select-sm" name="surQueryStats" id="surQueryStats" style="width: 10%;">
	                  	<option value="">不限</option>
	                  	<option value="1">草稿</option>
	                  	<option value="2">定稿</option>
	                  	<option value="3">废弃</option>
	                  </select>
	              </div>
              
              </div>
          </form>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="button-group">

                <button type="button" onclick="addNiAssessQuestionnaire();" class="btn btn-default btn-sm">添加</button>
                <button type="button" onclick="editSurveyQuestionnaire1()" id="assess1" value="1" class="btn btn-default btn-sm">定稿</button>
                <button type="button" onclick="changeAssessQuestionnaire()" class="btn btn-default btn-sm">修改</button>
                <button type="button" onclick="editSurveyQuestionnaire2()" id="assess2" value="2"  class="btn btn-default btn-sm">废弃</button>
                <button type="button" onclick="editSurveyQuestionnaire3()" id="assess3" value="3"  class="btn btn-default btn-sm">恢复</button>
                <button type="button" onclick="deleteNiAssessQuestionnaire()" id="" value=""  class="btn btn-default btn-sm">删除</button>
                
                <button type="button" class="btn btn-warning btn-sm" id="exportText">导出模版文件</button>
                
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
                  <th>序号</th>
                  <th>id</th>
                  <th>问卷名称</th>
                  <th>分类</th>
                  <th>题数</th>
                  <th>编写者</th>
                  <th>状态</th>
                  <th>发布者</th>
                  <th>定稿时间</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${NiAssessQuestionnaires }" var="nsq" varStatus="n">
	                 <tr>
	                  <td>
	                  	<input type="checkbox" id="danxuan" name="checkniSurveyQuestionnaire" value="${nsq.aqnid }">
	                  	<input type="hidden" id="n_staus" name="n_staus" value="${nsq.staus}">
	                  </td>
	                  <td>${n.index+1}</td>
	                  <td>${nsq.aqnid }</td>
	                  <td>
	                  		<div style="width: 250px;white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
	                  			${nsq.aqnname }
	                  		</div>
	                  </td>
	                  <td>${nsq.aqnClassName}</td>
	                  <td>${nsq.questionQty }</td>
	                  <td>${nsq.editor }</td>
	                  <td>
	                  	<c:if test="${nsq.staus == 1}">草稿</c:if>
	                  	<c:if test="${nsq.staus == 2}">定稿</c:if>
	                  	<c:if test="${nsq.staus == 3}">废弃</c:if>
	                  </td>
	                  <td>${nsq.publishername }</td>
	                  <td><fmt:formatDate value="${nsq.ctime }" pattern="yyyy-MM-dd"/> </td>
	                  <td>
	                  	  <input type="button" value="编辑" onclick="editQue(${nsq.aqnid})">
	                  	  <input type="button" value="预览" onclick="viewQue(${nsq.aqnid})">
	                  </td>
	                </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
        </div>
    </div>
 
  <script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
  <script src="${ctx}/static/js/moment.min.js"></script>
  <script src="${ctx}/js/validate.js"></script>
 <script>
 
  /**
   * 导出文本文件
   */
  $("#exportText").on('click',function(){
	  
	  var checkniSurveyQuestionnaire_arr = document.getElementsByName("checkniSurveyQuestionnaire");
	  
	  var count = 0;
	  var sqnId = "";
	  for(var i =0 ; i<checkniSurveyQuestionnaire_arr.length ; i++){
		  if(checkniSurveyQuestionnaire_arr[i].checked){
			  
			  count++;
			  sqnId = checkniSurveyQuestionnaire_arr[i].value;
		  }
	  }
	  if(count > 1 || count == 0){
		  showMessageDialog("请选择要导出的一份问卷");
		  return;
	  }
	  
	  window.open("${ctx}/platform/NiAssessQuestionnaire_download_qn.do?sqnId="+sqnId );
     // window.open("${ctx}/platform/NiAssessQuestionnaire_download_qn.do?sqnId="+sqnId, "_blank");
      
      //window.location.href = "${ctx}/platform/NiAssessQuestionnaire_download_qn.do?sqnId="+sqnId;
  });
  
  
  
  //添加问卷
  function addNiAssessQuestionnaire(){
	  window.location.href = "${ctx}/platform/NiAssessQuestionnaireAdd.do";
  }
  //预览问卷
  function viewQue(aqnId){
	  
	  window.location.href = "${ctx}/platform/niAssesQue_View.do?aqnId="+aqnId;
  }

  //编辑问卷
  function editQue(aqnId){
	  
	  window.location.href = "${ctx}/platform/editNiAssessQuestionnaire.do?aqnId="+aqnId;
  }
  
//定稿
  function editSurveyQuestionnaire1(){
	  var assess = $("#assess1").val();
	  var checkniSurveyQuestionnaire_arr = document.getElementsByName("checkniSurveyQuestionnaire");
	  var staus_arr = document.getElementsByName("n_staus");
	  var count = 0;
	  var sqnid = "";
	  for(var i=0;i<checkniSurveyQuestionnaire_arr.length;i++){
		  if(checkniSurveyQuestionnaire_arr[i].checked){
			  //判断当前被选中项的问卷状态
			  if(staus_arr[i].value != 1){
				  showMessageDialog("只能发布草稿状态的问卷");
				  return;
			  }
			  count++;
			  sqnid = checkniSurveyQuestionnaire_arr[i].value + "!" +sqnid;
		  }
		  
	  }
	  if(count == 0){
		  showMessageDialog("请选择要编辑的问卷");
		  return;
	  }
	   $.ajax({
		  type:"post",
		  dataType:"json",
		  url:"${ctx}/platform/releaseNiAssessQuestionnaire.do?sqnid="+sqnid+"&assess="+assess,
		  success:function(data){
			  showMessageDialog(data.msg, function(){
				  if(data.success){
					  window.location.href = "${ctx}/platform/niAssessQuestionnaireList.do";
				  }
			  });
		  }
	  }); 
  }
  
  //修改
  function changeAssessQuestionnaire(){
	  var checkniSurveyQuestionnaire_arr = document.getElementsByName("checkniSurveyQuestionnaire");
	  var staus_arr = document.getElementsByName("n_staus");
	  var count = 0;
	  var sqnid = "";
	  for(var i=0;i<checkniSurveyQuestionnaire_arr.length;i++){
		  if(checkniSurveyQuestionnaire_arr[i].checked){
			  //判断当前被选中项的问卷状态
			  if(staus_arr[i].value != 2){
				  showMessageDialog("只能修改定稿状态的问卷");
				  return;
			  }
			  count++;
			  sqnid = checkniSurveyQuestionnaire_arr[i].value + "!" +sqnid;
		  }
		  
	  }
	  if(count == 0){
		  showMessageDialog("请选择要编辑的问卷");
		  return;
	  }
	   $.ajax({
		  type:"post",
		  dataType:"json",
		  url:"${ctx}/platform/changeNiAssessQuestionnaire.do?sqnid="+sqnid,
		  success:function(data){
			  showMessageDialog(data.msg, function(){
				  if(data.success){
					  window.location.href = "${ctx}/platform/niAssessQuestionnaireList.do";
				  }
			  });
		  }
	  }); 
  }
  
//废弃
  function editSurveyQuestionnaire2(){
	  var assess = $("#assess2").val();
	  var checkniSurveyQuestionnaire_arr = document.getElementsByName("checkniSurveyQuestionnaire");
	  var staus_arr = document.getElementsByName("n_staus");
	  var count = 0;
	  var sqnid = "";
	  for(var i=0;i<checkniSurveyQuestionnaire_arr.length;i++){
		  if(checkniSurveyQuestionnaire_arr[i].checked){
			  //判断当前被选中项的问卷状态
			  if(staus_arr[i].value != 1){
				  showMessageDialog("只能废弃草稿状态的问卷");
				  return;
			  }
			  count++;
			  sqnid = checkniSurveyQuestionnaire_arr[i].value + "!" +sqnid;
		  }
		  
	  }
	  if(count == 0){
		  showMessageDialog("请选择要编辑的问卷");
		  return;
	  }
	  showConfirmDialog("要废弃问卷吗？", function(check){
		  if(check){
			   $.ajax({
					  type:"post",
					  dataType:"json",
					  url:"${ctx}/platform/releaseNiAssessQuestionnaire.do?sqnid="+sqnid+"&assess="+assess,
					  success:function(data){
						  showMessageDialog(data.msg, function(){
							  if(data.success){
								  window.location.href = "${ctx}/platform/niAssessQuestionnaireList.do";
							  }
						  });
					  }
				  }); 
		  }
	  });

  }
  
//恢复
  function editSurveyQuestionnaire3(){
	  var assess = $("#assess3").val();
	  var checkniSurveyQuestionnaire_arr = document.getElementsByName("checkniSurveyQuestionnaire");
	  var staus_arr = document.getElementsByName("n_staus");
	  var count = 0;
	  var sqnid = "";
	  for(var i=0;i<checkniSurveyQuestionnaire_arr.length;i++){
		  if(checkniSurveyQuestionnaire_arr[i].checked){
			  //判断当前被选中项的问卷状态
			  if(staus_arr[i].value != 3){
				  showMessageDialog("只能恢复废弃状态的问卷");
				  return;
			  }
			  count++;
			  sqnid = checkniSurveyQuestionnaire_arr[i].value + "!" +sqnid;
		  }
		  
	  }
	  if(count == 0){
		  showMessageDialog("请选择要编辑的问卷");
		  return;
	  }
	   $.ajax({
		  type:"post",
		  dataType:"json",
		  url:"${ctx}/platform/releaseNiAssessQuestionnaire.do?sqnid="+sqnid+"&assess="+assess,
		  success:function(data){
			  showMessageDialog(data.msg, function(){
				  if(data.success){
					  window.location.href = "${ctx}/platform/niAssessQuestionnaireList.do";
				  }
			  });
		  }
	  }); 
  }
  
  //删除
  function deleteNiAssessQuestionnaire(){
	  var checkniSurveyQuestionnaire_arr = document.getElementsByName("checkniSurveyQuestionnaire");
	  var staus_arr = document.getElementsByName("n_staus");
	  
	  var count = 0;
	  var sqnId_list = "";
	  for(var i=0;i<checkniSurveyQuestionnaire_arr.length;i++){
		  if(checkniSurveyQuestionnaire_arr[i].checked){
			  //判断当前被选中项的问卷状态
			  if(staus_arr[i].value == 2){
				  showMessageDialog("不能删除定稿状态的问卷");
				  return;
			  }
			  count++;
			  sqnId_list = checkniSurveyQuestionnaire_arr[i].value + "!" +sqnId_list;
		  }
		  
	  }
	  if(count == 0){
		  showMessageDialog("请选择问卷");
		  return;
	  }
	  showConfirmDialog("要删除选中问卷吗?", function(check){
		  if(check){
			   $.ajax({
					  type:"post",
					  dataType:"json",
					  url:"${ctx}/platform/deleteNiAssessQuestionnaire.do?sqnId_list="+sqnId_list,
					  success:function(data){
						  window.location.href = "${ctx}/platform/niAssessQuestionnaireList.do";
						  
					  }
				  }); 
		  }
	  });

  }

  var sqnNameOrpublisherNameL = 20;
  //查询
  function searchSueryQuestion(){
	  
	  var sqnNameOrpublisherName = $("#sqnNameOrpublisherName").val();
	  
	  if(sqnNameOrpublisherName.trim().length>sqnNameOrpublisherNameL){
		  showMessageDialog("问卷名称或发布机构名称不能超过"+sqnNameOrpublisherNameL+"字符");
		  return;
	  }else if(checkQuote(sqnNameOrpublisherName) == true){
		  showMessageDialog("不能包含特殊字符");
		  return;
	  }
	  
	  $('#searchform').submit();
  }
  </script>
</body>

</html>