<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>推文管理</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${ctx}/js/sang.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    
    <!-- 内容部分 -->
      <!-- Main content -->
        <div class="panel panel-default">
          <div class="panel-body">
            <form class="form-horizontal"  method="post" action="${ctx}/platform/findAllTweets.do" id="searchform">
              <div class="row mb10">
                <div class="col-sm-2">
                  <select class="form-control select-sm" name="searchType" id="searchType">
                    <option value="0">推文ID</option>
                    <option value="1">推文名称</option>
                  </select>
                </div>
                <div class="input-group input-group-sm col-sm-3">
                  <input id="tweetIdOrName" name="tweetIdOrName" type="text" class="form-control">
                  <span class="input-group-btn">
                      <button type="button" onclick="searchTweet()" class="btn btn-info btn-flat">查询</button>
                   </span>
                </div>
              </div>
          </form>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="button-group">
                <button type="button" onclick="addNiTweet();" class="btn btn-default btn-sm">添加</button>
                <button type="button" onclick="deleteNiTweet()" class="btn btn-default btn-sm">删除</button>
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
                  <th style="width: 30px;">序号</th>
                  <th>ID</th>
                  <th>图片</th>
                  <th>标题</th>
                  <!-- <th>地址</th> -->
                  <th>作者</th>
                  <th>标签</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${niTweets }" var="tweet" varStatus="n">
	                 <tr>
	                  <td>
	                  	<input type="checkbox" name="checkniTweet" value="${tweet.tweetId }">
	                  </td>
	                  <td>${n.index+1}</td>
	                  <td>${tweet.tweetId }</td>
	                  <td><img style="width: 100px;height: 70px;" src="${tweet.picUrl }"></td>
	                  <td>
	                  		<div style="width: 250px;white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
	                  			${tweet.tweetTitle }
	                  		</div>
	                  </td>
	                  <%-- <td>${tweet.tweetUrl }</td> --%>
	                  <td>${tweet.author }</td>
	                  <td>${tweet.tag }</td>
	                  <td>
	                  	  <input type="button" value="编辑" onclick="editNiTweet(${tweet.tweetId})">
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
  //添加推文
  function addNiTweet(){
	  window.location.href = "${ctx}/platform/addNiTweetJump.do";
  }
  
  //预览问卷
  function viewQue(aqnId){
	  
	  window.location.href = "${ctx}/platform/niAssesQue_View.do?aqnId="+aqnId;
  }
  
  //显示图片路径
  var imgurl = "";
  //数据库图片路径
  var jdbcUrl = "";
  //上传图片
  function uploadpic(){
	  var aqnId_show = $("#aqnId_show").val();
	  var formData = new FormData($("#pageListFormEditQue")[0]);
	  $.ajax({
		  url: '${ctx}/platform/uploadNiSurveyQuestionnairePic.do?sqnId='+aqnId_show, 
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
  
  // 编辑推文 页面
  function editNiTweet(tweetId){
	  window.location.href = "${ctx}/platform/editNiTweetJump.do?tweetId="+tweetId;
  }
  
//保存编辑后的问卷
  function editSurveyQuestionnaireSave(){
		 var aqnId_show = $.trim($("#aqnId_show").val());   //问卷id
		 var qe_sqnname = $.trim($("#qe_sqnname").val());    //问卷名称
		 var qe_editor= $.trim($("#qe_editor").val());   // 出题人
		 var qe_publisherName = $.trim($("#qe_publisherName").val());  //发布机构
		 var qe_collectNum= $.trim($("#qe_collectNum").val());   // 建议回收数量
		 var qe_Classification= $.trim($("#qe_Classification").val());   // 问卷分类
		 var qe_Profile= $.trim($("#qe_Profile").val());   // 问卷简介
	  	 
		 $.ajax({
			 url:'${ctx}/platform/editNiAssessQuestionnaireSave.do',
			 type:'post',
			 data:{
				 aqnId_show : aqnId_show,
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
  
  // 批量删除推文
  function deleteNiTweet(){
	  var checkniTweet_arr = document.getElementsByName("checkniTweet");
	  var count = 0;
	  var tweetId = "";
	  for(var i=0;i<checkniTweet_arr.length; i++){
		  if(checkniTweet_arr[i].checked){
			  //判断是否被选中
			  count++;
			  tweetId = checkniTweet_arr[i].value + "!" + tweetId;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择要删除的选项");
		  return;
	  }
	  
	  $.ajax({
		  url : "${ctx}/platform/deleteTweetById.do",
		  dataType : "json",
		  type : "post",
		  data : {
			  tweetId : tweetId
		  },
		  success : function(data){
			  showMessageDialog(data.msg,function(){
				  if(data.success){
					  window.location.href = "${ctx}/platform/findAllTweets.do";
				  }
			  });
		  },
		  error:function(){
			  showMessageDialog("网络异常");
		  }
	  });
	  
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
	  var sqnid = "";
	  for(var i=0;i<checkniSurveyQuestionnaire_arr.length;i++){
		  if(checkniSurveyQuestionnaire_arr[i].checked){
			  //判断当前被选中项的问卷状态
			  if(staus_arr[i].value != 3){
				  showMessageDialog("只能删除废弃状态的问卷");
				  return;
			  }
			  count++;
			  sqnid = checkniSurveyQuestionnaire_arr[i].value + "!" +sqnid;
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
					  url:"${ctx}/platform/deleteNiAssessQuestionnaire.do?sqnid="+sqnid,
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

  var sqnNameOrpublisherNameL = 20;
  //查询
  function searchTweet(){
	  
	  var searchType = $("#searchType").val();
	  var tweetIdOrName = $("#tweetIdOrName").val();
	  
	  if(checkQuote(tweetIdOrName) == true){
		  showMessageDialog("查询条件不能包含特殊字符");
		  return;
	  }
	  if(searchType == 0){
		  
		  if(!checkNumber(tweetIdOrName)){
			  showMessageDialog("ID只能输入数字");
			  return;
		  }
	  }
	  
	  $('#searchform').submit();
  }
  </script>
</body>

</html>