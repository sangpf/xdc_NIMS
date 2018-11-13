<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>投票问卷管理</title>
</head>

<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    
      <!-- Main content -->
      <section class="content">
        <div class="panel panel-default">
          <div class="panel-body">
            <form class="form-horizontal"  method="post" action="${ctx}/platform/NiVoteQuestionnaireList.do" id="pageListForm">
              <div class="row mb10">
                <div class="col-sm-2">
                  <select class="form-control select-sm" name="sqnpublish" id="sqnpublish">
                    <option value="2">问卷id</option>
                    <option value="0">问卷名称</option>
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
                  <select class="form-control select-sm" id="qnclassid" name="qnclassid" style="width:75%;">
                    <option value="" selected="selected">请选择分类</option>
                    <c:forEach items="${niVqnclassDict_list }" var="nd">
                  		  <option value="${nd.vqnclassid }">${nd.vqnclassname }</option>
                    </c:forEach>
                  </select>
                </div>
                
               <div class="row nb10">
                <p class="ft-12 pd-15">
                  <label class="select-label">状态</label>
                  <select class="form-control select-sm" id="surQueryStats" name="surQueryStats" style="width: 10%;">
                  	<option value="">不限</option>
                  	<option value="1">草稿</option>
                  	<option value="2">定稿</option>
                  	<option value="3">废弃</option>
                  </select>
                </p>
              	</div>
              </div>

          </form>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="button-group">
            
                <button type="button" onclick="addVoteQuestionnaire();" class="btn btn-default btn-sm">添加</button>
                <button type="button" onclick="publishingVoteQuestionnaire()" class="btn btn-default btn-sm">定稿</button>
                <button type="button" onclick="changeVoteQuestionnaire()" class="btn btn-default btn-sm">修改</button>
                <button type="button" onclick="abandonedVoteQuestionnaire()" class="btn btn-default btn-sm">废弃</button>
                <button type="button" onclick="regenerationVoteQuestionnaire()" class="btn btn-default btn-sm">恢复</button>
                <button type="button" onclick="deleteNiVoteQuestionnaire()" class="btn btn-default btn-sm">删除</button>
                
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
                  <th>id</th>
                  <th>问卷名称</th>
                  <th>分类</th>
                  <th>编写者</th>
                  <th>状态</th>
                  <th>发布者</th>
                  <th>定稿时间</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${NiVoteQuestionnaires}" var="nsq" varStatus="n">
	                <tr>
	                  <td>
		                  <input type="checkbox" id="checkniSurveyQuestionnaire" name="checkniSurveyQuestionnaire" value="${nsq.vqnid }">
		              	  <input type="hidden" id="n_staus" name="n_staus" value="${nsq.status}">   
		              </td>
	                  <td>${n.index+1}</td>
	                  <td>${nsq.vqnid }</td>
	                  <td>
	                  	<div style="width: 350px;white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
	                  		${nsq.vqnname }
	                  	</div>
	                  </td>
	                  <td>${nsq.vqnClassName }</td>
	                  <td>${nsq.editor }</td>
	                  <td>
	                  	<c:if test="${nsq.status == 1}">草稿</c:if>
	                  	<c:if test="${nsq.status == 2}">定稿</c:if>
	                  	<c:if test="${nsq.status == 3}">废弃</c:if>
	                  </td>
	                  <td>${nsq.publishername }</td>
	                  <td>
	                  	<fmt:formatDate value="${nsq.ctime }" pattern="yyyy-MM-dd"/>
	                  </td>
	                  <td>
	                  	  <input type="button" value="编辑" onclick="editQue(${nsq.vqnid})">
	                  	  <input type="button" value="预览" onclick="viewQue(${nsq.vqnid})">
	                  </td>
	                </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
    </section>
    </div>
  
  <div class="control-sidebar-bg"></div>
  <script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
  <script src="${ctx}/static/js/moment.min.js"></script>
  <script src="${ctx}/js/validate.js"></script>
  
  <script>
  
  //预览问卷
  function viewQue(vqnId){
	  window.location.href = "${ctx}/platform/niVoteQue_View.do?vqnId="+vqnId;
  }

  
  //编辑问卷
  function editQue(vqnId){
	  
	  window.location.href = "${ctx}/platform/findOneNiVoteQuestionnaire.do?vqnId="+vqnId;
  }
  
//废弃问卷
  function abandonedVoteQuestionnaire(){
	  var checkniSurveyQuestionnaire_arr = document.getElementsByName("checkniSurveyQuestionnaire");
	  var staus_arr = document.getElementsByName("n_staus");
	  
	  var count = 0 ;
	  var sqnid = "";
	  for(var i=0;i<checkniSurveyQuestionnaire_arr.length;i++){
		  if(checkniSurveyQuestionnaire_arr[i].checked){
			  //判断当前被选中项的问卷状态
			  if(staus_arr[i].value != 1){
				  showMessageDialog("只能废弃草稿状态的问卷");
				  return;
			  }
			  
			  count++;
			  sqnid = checkniSurveyQuestionnaire_arr[i].value+"!"+sqnid;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择问卷");
		  return;
	  }
	  showConfirmDialog("要废弃问卷吗?", function(check){
		  if(check){
			  $.ajax({
				  url:"${ctx}/platform/abandonedVoteQuestionnaire.do?sqnid="+sqnid,
			  	  type:"post",
				  dataType:"json",
				  success:function(data){
					  showMessageDialog(data.msg, function(){
						  if(data.success){
							  window.location.href = "${ctx}/platform/NiVoteQuestionnaireList.do";
						  }
					  });
				  }
			  });
		  }
	  });

  }
  
//恢复问卷
  function regenerationVoteQuestionnaire(){
	  var checkniSurveyQuestionnaire_arr = document.getElementsByName("checkniSurveyQuestionnaire");
	  var staus_arr = document.getElementsByName("n_staus");
	  
	  var count = 0 ;
	  var sqnid = "";
	  for(var i=0;i<checkniSurveyQuestionnaire_arr.length;i++){
		  if(checkniSurveyQuestionnaire_arr[i].checked){
			  //判断当前被选中项的问卷状态
			  if(staus_arr[i].value != 3){
				  showMessageDialog("只能恢复废弃状态的问卷");
				  return;
			  }
			  
			  count++;
			  sqnid = checkniSurveyQuestionnaire_arr[i].value+"!"+sqnid;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择问卷");
		  return;
	  }
	  $.ajax({
		  url:"${ctx}/platform/regenerationVoteQuestionnaire.do?sqnid="+sqnid,
	  	  type:"post",
		  dataType:"json",
		  success:function(data){
			  showMessageDialog(data.msg, function(){
				  if(data.success){
					  window.location.href = "${ctx}/platform/NiVoteQuestionnaireList.do";
				  }
			  });
		  }
	  });
  }
  
  //删除
  function deleteNiVoteQuestionnaire(){
	//删除调查问卷
		  var checkniSurveyQuestionnaire_arr = document.getElementsByName("checkniSurveyQuestionnaire");
		  var staus_arr = document.getElementsByName("n_staus");
		  
		  var count = 0 ;
		  var sqnid = "";
		  for(var i=0;i<checkniSurveyQuestionnaire_arr.length;i++){
			  if(checkniSurveyQuestionnaire_arr[i].checked){
				  //判断当前被选中项的问卷状态
				  if(staus_arr[i].value == 2){
					  showMessageDialog("不能删除定稿状态的问卷");
					  return;
				  }
				  count++;
				  sqnid = checkniSurveyQuestionnaire_arr[i].value+"!"+sqnid;
			  }
		  }
		  if(count == 0){
			  showMessageDialog("请选择问卷");
			  return;
		  }
		  showConfirmDialog("要删除选中问卷吗?", function(check){
			  if(check){
				  $.ajax({
					  url:"${ctx}/platform/deleteNiVoteQuestionnaire.do?sqnid="+sqnid,
				  	  type:"post",
					  dataType:"json",
					  success:function(data){
						  window.location.href = "${ctx}/platform/NiVoteQuestionnaireList.do";
					  }
				  });
			  }
		  });

	  }
  
  //修改
  function changeVoteQuestionnaire(){
	  var checkniSurveyQuestionnaire_arr = document.getElementsByName("checkniSurveyQuestionnaire");
	  var staus_arr = document.getElementsByName("n_staus");
	  var count = 0 ;
	  var sqnid = "";
	  for(var i=0;i<checkniSurveyQuestionnaire_arr.length;i++){
		  if(checkniSurveyQuestionnaire_arr[i].checked){
			  //判断当前被选中项的问卷状态
			  if(staus_arr[i].value != 2){
				  showMessageDialog("只能修改定稿状态的问卷");
				  return;
			  }
			  
			  count++;
			  sqnid = checkniSurveyQuestionnaire_arr[i].value+"!"+sqnid;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择要操作的问卷");
		  return;
	  }
	  $.ajax({
		  url:"${ctx}/platform/changeVoteQuestionnaire.do?sqnid="+sqnid,
	  	  type:"post",
		  dataType:"json",
		  success:function(data){
			  showMessageDialog(data.msg, function(){
				  if(data.success){
					  window.location.href = "${ctx}/platform/NiVoteQuestionnaireList.do";
				  }
			  });
		  }
	  });
  }
  
  //发布
  function publishingVoteQuestionnaire(){
	  var checkniSurveyQuestionnaire_arr = document.getElementsByName("checkniSurveyQuestionnaire");
	  var staus_arr = document.getElementsByName("n_staus");
	  
	  var count = 0 ;
	  var sqnid = "";
	  for(var i=0;i<checkniSurveyQuestionnaire_arr.length;i++){
		  if(checkniSurveyQuestionnaire_arr[i].checked){
			  //判断当前被选中项的问卷状态
			  if(staus_arr[i].value != 1){
				  showMessageDialog("只能发布草稿状态的问卷");
				  return;
			  }
			  
			  count++;
			  sqnid = checkniSurveyQuestionnaire_arr[i].value+"!"+sqnid;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择要发布的问卷");
		  return;
	  }
	  $.ajax({
		  url:"${ctx}/platform/publishingVoteQuestionnaire.do?sqnid="+sqnid,
	  	  type:"post",
		  dataType:"json",
		  success:function(data){
			  showMessageDialog(data.msg, function(){
				  if(data.success){
					  window.location.href = "${ctx}/platform/NiVoteQuestionnaireList.do";
				  }
			  });
		  }
	  });
  }
  
  //添加
  function addVoteQuestionnaire(){
	  window.location.href = "${ctx}/platform/NiVoteQuestionnaireAdd.do";
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
	  
	  $('#pageListForm').submit();
  }
  
  </script>
</body>

</html>