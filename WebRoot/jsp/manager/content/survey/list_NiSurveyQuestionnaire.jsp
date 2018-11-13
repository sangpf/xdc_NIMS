<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>调查问卷管理</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="${ctx}/js/sang.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    
    <!-- 内容部分 -->
        <div class="panel panel-default">
          <div class="panel-body">
            <form class="form-horizontal"  method="post" action="${ctx}/platform/NiSurveyQuestionnaireList.do" id="pageListForm">
              <div class="row mb10">
              
                <div class="col-sm-2">
                  <select class="form-control select-sm" name="sqnpublish" id="sqnpublish">
                    <option value="0">问卷  Id</option>
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
                  		<c:forEach items="${niSqnclassDict_list }" var="ns">
                    		<option value="${ns.sqnclassid }">${ns.sqnclassname }</option>
                  		</c:forEach>
                  </select>
                </div>

				 <div class="row nb10">
                  <label class="select-label">问卷状态</label>
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
                <button type="button" onclick="addSurveyQuestionnaire()" 	class="btn btn-default btn-sm">添加</button>
                <button type="button" onclick="publishingSurveyQuestionnaire()" class="btn btn-default btn-sm">定稿</button>
                 <button type="button" onclick="changeSurveyQuestionnaire()" class="btn btn-default btn-sm">修改</button>
                <button type="button" onclick="abandonedSurveyQuestionnaire()" class="btn btn-default btn-sm">废弃</button>
                <button type="button" onclick="regenerationSurveyQuestionnaire()" class="btn btn-default btn-sm">恢复</button>
                <button type="button" onclick="deleteSurveyQuestionnaire()" class="btn btn-default btn-sm">删除</button>
            </div>
          </div>
        </div>
        <div class="panel panel-default">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th></th>
                  <th>编号</th>
                  <th>id</th>
                  <th>问卷名称</th>
                  <th>分类</th>
                  <th>题数</th>
                  <th>发布者</th>
                  <th>状态</th>
                  <th>编辑人</th>
                  <th>定稿时间</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${niSurveyQuestionnaires}" var="nsq" varStatus="n">
	                <tr>
	                  <td>
	                  	<input type="checkbox" id="checkniSurveyQuestionnaire" name="checkniSurveyQuestionnaire" value="${nsq.sqnid }">
	                  	<input type="hidden" id="n_staus" name="n_staus" value="${nsq.staus}">
	                  </td>
	                  <td>${n.index+1}</td>
	                  <td>${nsq.sqnid }</td>
	                  <td>
	                  	<div style="width: 250px;white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
		                  	${nsq.sqnname }
	                  	</div>
	                  </td>
	                  <td>${nsq.sqnClassName}</td>
	                  <td>${nsq.questionQty }</td>
	                  <td>${nsq.publishername }</td>
	                  <td>
	                  	<c:if test="${nsq.staus == 1}">草稿</c:if>
	                  	<c:if test="${nsq.staus == 2}">定稿</c:if>
	                  	<c:if test="${nsq.staus == 3}">废弃</c:if>
	                  </td>
	                  <td>${nsq.editor }</td>
	                  <td>
	                  	<fmt:formatDate value="${nsq.ctime }" pattern="yyyy-MM-dd"/>
	                  </td>
	                  <td>
	                  	  <input type="button" value="编辑" onclick="editQue(${nsq.sqnid})">
	                  	  <input type="button" value="预览" onclick="viewQue(${nsq.sqnid})">
	                  </td>
	                </tr>
                </c:forEach>
                </tbody>
              </table>
        </div>
    </div>
    
    <!-- 内容部分结束 -->
  <script src="${ctx}/static/js/moment.min.js"></script>
  <script src="${ctx}/js/validate.js"></script>
  
  <script>
  
  //预览问卷
  function viewQue(aqnId){
	  if(aqnId == ""){
		  showMessageDialog("请先上传问卷!");
		  return;
	  }
      window.location.href = "${ctx}/platform/niSurveyQuestionnaireView.do?sqnId="+aqnId;
  }
  //添加问卷
  function addSurveyQuestionnaire(){
	  window.location.href = "${ctx}/platform/NiSurveyQuestionnaireAdd.do";
  }
  
  //显示图片路径
  var imgurl = "";
  //数据库图片路径
  var jdbcUrl = "";
  //上传图片
  function uploadpic(){
	  var sqnId_show = $("#sqnId_show").val();
	  var formData = new FormData($("#pageListFormEditQue")[0]);
	  $.ajax({
		  url: '${ctx}/platform/uploadNiSurveyQuestionnairePic.do?sqnId='+sqnId_show, 
		  type:'post',
		  data: formData,
		  dataType:'json',
          async: false,  
          cache: false,  
          contentType: false,  
          processData: false,  
		  success:function(data){
             imgurl = data.url;
             jdbcUrl=data.jdbcUrl;
             $(".img-rounded").attr("src",imgurl);
		  }
		  
	  });
  }
  
  //保存编辑后的问卷
  function editSurveyQuestionnaireSave(){
		 var sqnId_show = $.trim($("#sqnId_show").val());   //问卷id
		 var qe_sqnname = $.trim($("#qe_sqnname").val());    //问卷名称
		 var qe_editor= $.trim($("#qe_editor").val());   // 出题人
		 var qe_publisherName = $.trim($("#qe_publisherName").val());  //发布机构
		 var qe_collectNum= $.trim($("#qe_collectNum").val());   // 建议回收数量
		 var qe_Classification= $.trim($("#qe_Classification").val());   // 问卷分类
		 var qe_Profile= $.trim($("#qe_Profile").val());   // 问卷简介
	  	 
		 $.ajax({
			 url:'${ctx}/platform/editSurveyQuestionnaireSave.do',
			 type:'post',
			 data:{
				 sqnId_show : sqnId_show,
				 qe_sqnname : qe_sqnname,
				 qe_editor : qe_editor,
				 qe_publisherName : qe_publisherName,
				 qe_collectNum : qe_collectNum,
				 qe_Classification : qe_Classification,
				 qe_Profile : qe_Profile,
				 jdbcUrl : jdbcUrl
			 },
			 dataType:'json',
			 success:function(data){
				 $("#editque").modal("hide");
				 showMessageDialog(data.msg);
			 }
			 
		 });
  }
  
  //编辑问卷 弹框
  function editQue(sqnId){
	  
	  window.location.href = "${ctx}/platform/findOneNiSurveyQuestionnaire.do?sqnId="+sqnId;
  }
  
  //删除调查问卷
  function deleteSurveyQuestionnaire(){
	  var checkniSurveyQuestionnaire_arr = document.getElementsByName("checkniSurveyQuestionnaire");
	  var staus_arr = document.getElementsByName("n_staus");
	  
	  var count = 0 ;
	  var sqnId_list = "";
	  for(var i=0;i<checkniSurveyQuestionnaire_arr.length;i++){
		  if(checkniSurveyQuestionnaire_arr[i].checked){
			  //判断当前被选中项的问卷状态
			  if(staus_arr[i].value == 2){
				  showMessageDialog("不能删除定稿状态的问卷");
				  return;
			  }
			  count++;
			  sqnId_list = checkniSurveyQuestionnaire_arr[i].value+"!"+sqnId_list;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择问卷");
		  return;
	  }
	  showConfirmDialog("要删除选中问卷吗?", function(check){
		  if(check){
			  $.ajax({
				  url:"${ctx}/platform/deleteSurveyQuestionnaire.do?sqnId_list="+sqnId_list,
			  	  type:"post",
				  dataType:"json",
				  success:function(data){
					  window.location.href = "${ctx}/platform/NiSurveyQuestionnaireList.do";
					  
				  }
			  });
		  }
	  });
  }
  
  //恢复问卷
  function regenerationSurveyQuestionnaire(){
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
		  url:"${ctx}/platform/regenerationSurveyQuestionnaire.do?sqnid="+sqnid,
	  	  type:"post",
		  dataType:"json",
		  success:function(data){
			  showMessageDialog(data.msg, function(){
				  if(data.success){
					  window.location.href = "${ctx}/platform/NiSurveyQuestionnaireList.do";
				  }
			  });
		  }
	  });
	  
  }
  
  //废弃问卷
  function abandonedSurveyQuestionnaire(){
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
				  url:"${ctx}/platform/abandonedSurveyQuestionnaire.do?sqnid="+sqnid,
			  	  type:"post",
				  dataType:"json",
				  success:function(data){
					  showMessageDialog(data.msg, function(){
						  if(data.success){
							  window.location.href = "${ctx}/platform/NiSurveyQuestionnaireList.do";
						  }
					  });
				  }
			  });
		  }
	  });

  }
  
  //修改问卷
  function changeSurveyQuestionnaire(){
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
		  showMessageDialog("请选择要修改的问卷");
		  return;
	  }
	  $.ajax({
		  url:"${ctx}/platform/changeSurveyQuestionnaire.do?sqnid="+sqnid,
	  	  type:"post",
		  dataType:"json",
		  success:function(data){
			  showMessageDialog(data.msg, function(){
				  if(data.success){
					  window.location.href = "${ctx}/platform/NiSurveyQuestionnaireList.do";
				  }
			  });
		  }
	  });
  }
  
  //批量发布调查问卷
  function publishingSurveyQuestionnaire(){
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
		  url:"${ctx}/platform/updateNiSurveyQuestionnaire.do?sqnid="+sqnid,
	  	  type:"post",
		  dataType:"json",
		  success:function(data){
			  showMessageDialog(data.msg, function(){
				  if(data.success){
					  window.location.href = "${ctx}/platform/NiSurveyQuestionnaireList.do";
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
		  showMessageDialog("问卷名称不能超过"+sqnNameOrpublisherNameL+"字符");
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