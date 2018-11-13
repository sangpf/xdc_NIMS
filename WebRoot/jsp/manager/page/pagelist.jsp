<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>页面管理</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${ctx}/js/sang.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
 		<table style="padding: 100px;margin: 10px;">
			<tr>
				<td style="width: 100px;padding: 3px;"><a href="${ctx}/platform/loadDaily3UpdateList.do">
					<button type="button" class="btn btn-block btn-primary" style="background:green">每日三更</button></a></td>
				<td style="width: 100px;padding: 3px;"><a href="${ctx}/platform/loadSuperList.do">
					<button type="button" class="btn btn-block btn-primary">超级调查</button></a></td>
				<td style="width: 100px;padding: 3px;"><a href="${ctx}/platform/loadAssessList.do">
					<button type="button" class="btn btn-block btn-primary">测一发</button></a></td>
				<td style="width: 100px;padding: 3px;"><a href="${ctx}/platform/loadReportList.do">
					<button type="button" class="btn btn-block btn-primary">真相</button></a></td>
			</tr>
		</table>
  <div class="wrapper">
    <!-- 内容部分 -->
        <div class="panel panel-default">
          <div class="panel-body">
				<form class="form-horizontal" method="post" action="${ctx}/platform/loadDaily3UpdateList.do" id="pageListForm">
						<table style="border-spacing: 10px;border-collapse: separate;">
							<tr>
								<td><label>页面状态</label></td>
								<td><select class="form-control select-sm"
									name="pageStatus" id="pageStatus">
										<option value="">不限</option>
										<option value="1">待发布</option>
										<option value="2">定时发布</option>
										<option value="3">已发布</option>
								</select></td>
								
								<td><label>内容类型</label></td>
								<td style="width: 160px;"><select class="form-control select-sm"
									name="qnType" id="qnType">
										<option value="">不限</option>
										<option value="1">调查</option>
								</select></td>
								
								<td><label>&nbsp;栏目</label></td>
								<td style="width: 160px;">
									<select class="form-control select-sm" style="font-size: 16px;height: 35px;" name="superListCategory_Str_GO" id="superListCategory_Str_GO">
										<c:forEach items="${superListCategory_list }" var="superListCategory">
											<option <c:if test="${superListCategory_Str == superListCategory.srtId}">selected="selected"</c:if> value="${superListCategory.srtId}">${superListCategory.strName}</option>
										</c:forEach>
									</select>
								</td>
								
								<td><label>&nbsp;渠道</label></td>
								<td style="width: 160px;">
									<select class="form-control select-sm" style="font-size: 16px;height: 35px;" name="superListCategory_Str_GO" id="superListCategory_Str_GO">
										<c:forEach items="${superListCategory_list }" var="superListCategory">
											<option <c:if test="${superListCategory_Str == superListCategory.srtId}">selected="selected"</c:if> value="${superListCategory.srtId}">${superListCategory.strName}</option>
										</c:forEach>
									</select>
								</td>
								
								<td><span class="input-group-btn">
										<button type="button"
											onclick="searchDaily3UpdateQuestionnaire()"
											class="btn btn-info btn-flat">查询</button>
								</span></td>
							</tr>
						</table>
				</form>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="button-group">
				<button type="button" onclick="addPage()" class="btn btn-default btn-sm">添加</button>
				<button type="button" onclick="postPage()" class="btn btn-default btn-sm">发布</button>
				<button type="button" onclick="revokePage()" class="btn btn-default btn-sm">撤销</button>
				<button type="button" onclick="deletePage()" class="btn btn-default btn-sm">删除</button>
				<button type="button" onclick="timerPage()" class="btn btn-default btn-sm">定时器</button>
            </div>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="box">
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th style="width: 15px"></th>
                  <th></th>
                  <th>ID</th>
                  <th>内容ID</th>
                  <th>内容类型</th>
                  <th>页面状态</th>
                  <th>操作人</th>
                  <th>发布时间</th>
                  <th>栏目</th>
                  <th>渠道</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listPage }" var="page" varStatus="n">
                	<!-- 置顶 -->
                	<c:if test="${page.isTop == 1 }">
                		<tr style="background:#DDA0DD">
		                  <td>${n.index+1}</td>
		                  <td>
		                  	<input type="checkbox" name="checkpage" value="${page.itemId }">
		                  	<input type="hidden" name="checkStatus" value="${page.pageStatus }">
		                  	<input type="hidden" name="checkShowOrder" value="${page.showOrder }">
		                  </td>
		                  <td>${page.itemId }</td> <!-- 通用页面 ID itemID -->
		                  <td>${page.contentId }</td> <!-- 内容ID   数据库字段 tweetId,reportId,deliveryId,   -->
		                  <td>
		                  	 <c:if test="${page.contentType == 1}">
		                  	 	调查投放
		                  	 </c:if>
		                  	 <c:if test="${page.contentType == 2}">
		                  	 	测评投放
		                  	 </c:if>
	                	 	 <c:if test="${page.contentType == 3}">
		                  	 	投票投放
		                  	 </c:if>
		                  	 <c:if test="${page.contentType == 4}">
		                  	 	报告
		                  	 </c:if>
		                  	 <c:if test="${page.contentType == 5}">
		                  	 	推文
		                  	 </c:if>
		                  </td>
		                  <td>
		                  	<c:if test="${page.pageStatus == 1}">
		                  		待发布
		                  	</c:if>
		                  	<c:if test="${page.pageStatus == 2}">
		                  		定时发布
		                  	</c:if>
		                  	<c:if test="${page.pageStatus == 3}">
		                  		已发布
		                  	</c:if>
		                  
		                  </td>
		                  <td>${page.operator }</td>
		                  <td><fmt:formatDate pattern="yyyy-MM-dd" value="${page.lastUTime }"/></td>
		                  <td>${page.columnTitle }</td>
		                  <td>${page.channelTitle }</td>
		                  <td>
		                  	  <input type="button" value="上移" onclick="moveUp(${n.index+1},${page.itemId },${page.showOrder},${page.pageStatus},${page.columnId})">
		                  	  <input type="button" value="下移" onclick="moveDown(${n.index+1},${page.itemId },${page.showOrder},${page.pageStatus},${page.columnId})">
		                  	  <%-- <input type="button" value="编辑" onclick="editNiTweet(${page.itemId })"> --%>
		                  	  <input type="button" value="取消" onclick="changeIsTop(${page.itemId},${page.isTop},${page.pageStatus},${page.columnId})">
		                  </td>
	                </tr>
                	</c:if>
                	<!-- 不置顶 -->
                	<c:if test="${page.isTop == 2 }">
                		<c:if test="${page.pageStatus != 3 }">
                			<tr style="background:#B0E0E6">
		                  <td>${n.index+1}</td>
		                  <td>
		                  	<input type="checkbox" name="checkpage" value="${page.itemId }">
		                  	<input type="hidden" name="checkStatus" value="${page.pageStatus }">
		                  	<input type="hidden" name="checkShowOrder" value="${page.showOrder }">
		                  </td>
		                  <td>${page.itemId }</td>
		                  <td>${page.contentId }</td>
		                  <td>
		                  	 <c:if test="${page.contentType == 1}">
		                  	 	调查投放
		                  	 </c:if>
		                  	 <c:if test="${page.contentType == 2}">
		                  	 	测评投放
		                  	 </c:if>
	                	 	 <c:if test="${page.contentType == 3}">
		                  	 	投票投放
		                  	 </c:if>
		                  	 <c:if test="${page.contentType == 4}">
		                  	 	报告
		                  	 </c:if>
		                  	 <c:if test="${page.contentType == 5}">
		                  	 	推文
		                  	 </c:if>
		                  </td>
		                  <td>
		                  	<c:if test="${page.pageStatus == 1}">
		                  		待发布
		                  	</c:if>
		                  	<c:if test="${page.pageStatus == 2}">
		                  		定时发布
		                  	</c:if>
		                  	<c:if test="${page.pageStatus == 3}">
		                  		已发布
		                  	</c:if>
		                  
		                  </td>
		                  <td>${page.operator }</td>
		                  <td><fmt:formatDate pattern="yyyy-MM-dd" value="${page.lastUTime }"/></td>
		                  <td>${page.columnTitle }</td>
		                  <td>${page.channelTitle }</td>
		                  <td>
		                      <input type="button" value="上移" onclick="moveUp(${n.index+1},${page.itemId },${page.showOrder},${page.pageStatus},${page.columnId})">
		                  	  <input type="button" value="下移" onclick="moveDown(${n.index+1},${page.itemId },${page.showOrder},${page.pageStatus},${page.columnId})">
		                  	 <%--  <input type="button" value="编辑" onclick="editNiTweet(${page.itemId })"> --%>
		                  	  <input type="button" value="置顶" onclick="">
		                  </td>
		                </tr>
                		</c:if>
                		<c:if test="${page.pageStatus == 3 }">
                			<tr>
			                  <td>${n.index+1}</td>
			                  <td>
			                  	<input type="checkbox" name="checkpage" value="${page.itemId }">
			                  	<input type="hidden" name="checkStatus" value="${page.pageStatus }">
			                  	<input type="hidden" name="checkShowOrder" value="${page.showOrder }">
			                  </td>
			                  <td>${page.itemId }</td>
			                  <td>${page.contentId }</td>
			                  <td>
			                  	 <c:if test="${page.contentType == 1}">
			                  	 	调查投放
			                  	 </c:if>
			                  	 <c:if test="${page.contentType == 2}">
			                  	 	测评投放
			                  	 </c:if>
		                	 	 <c:if test="${page.contentType == 3}">
			                  	 	投票投放
			                  	 </c:if>
			                  	 <c:if test="${page.contentType == 4}">
			                  	 	报告
			                  	 </c:if>
			                  	 <c:if test="${page.contentType == 5}">
			                  	 	推文
			                  	 </c:if>
			                  </td>
			                  <td>
			                  	<c:if test="${page.pageStatus == 1}">
			                  		待发布
			                  	</c:if>
			                  	<c:if test="${page.pageStatus == 2}">
			                  		定时发布
			                  	</c:if>
			                  	<c:if test="${page.pageStatus == 3}">
			                  		已发布
			                  	</c:if>
			                  
			                  </td>
			                  <td>${page.operator }</td>
			                  <td><fmt:formatDate pattern="yyyy-MM-dd" value="${page.lastUTime }"/></td>
			                  <td>${page.columnTitle }</td>
			                  <td>${page.channelTitle }</td>
			                  <td>
			                      <input type="button" value="上移" onclick="moveUp(${n.index+1},${page.itemId },${page.showOrder},${page.pageStatus},${page.columnId})">
			                  	  <input type="button" value="下移" onclick="moveDown(${n.index+1},${page.itemId },${page.showOrder},${page.pageStatus},${page.columnId})">
			                  	  <%-- <input type="button" value="编辑" onclick="editNiTweet(${page.itemId })"> --%>
			                  	  <input type="button" value="置顶" onclick="">
			                  </td>
			                </tr>
                		</c:if>
                		
                	</c:if>
                	

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
  //添加
  function addPage(){
	  window.location.href = "${ctx}/light/addPageJump.do";
  }

  function moveDown(a,itemId,showOrder,pageStatus,columnId){
	  	var index = a;
		var itemIds = document.getElementsByName("checkpage");
		var showOrders = document.getElementsByName("checkShowOrder");
		
		if (index >= itemIds.length) {
			alert("无法继续下移");
			return;
		}
		
		var nextitemId = itemIds[index].value;
		var nextShowOrder = showOrders[index].value;
		
		$.ajax({
			url : "${ctx}/light/moveDownPage.do",
			type : "post",
			data : {
				itemId : itemId,
				showOrder : showOrder,
				nextitemId : nextitemId,
				nextShowOrder : nextShowOrder
			},
			dataType : "json",
			success : function(data) {
				if (data.success) {
					window.location.href = "${ctx}/light/listPage.do?channelId=2";
				}
			}
		});
		
	  
	  
  }
  
  function moveUp(a,itemId,showOrder,pageStatus,columnId){
		var index = a -2;
		if (a == 1) {
			alert("无法继续上移");
			return false;
		}
		
		var itemIds = document.getElementsByName("checkpage");
		var showOrders = document.getElementsByName("checkShowOrder");
	  
		var lastItemId = itemIds[index].value;
		var lastShowOrder = showOrders[index].value;
		
		$.ajax({
			url : "${ctx}/light/moveUpPage.do",
			type : "post",
			data : {
				itemId : itemId,
				showOrder : showOrder,
				lastItemId : lastItemId,
				lastShowOrder : lastShowOrder
			},
			dataType : "json",
			success : function(data) {
				if (data.success) {
					window.location.href = "${ctx}/light/listPage.do?channelId=2";
				}
			}
		});
		
		
  }
  
  // 置顶 
  function changeIsTop(itemId,isTop,pageStatus,columnId){
	  
	  $.ajax({
		  url : "${ctx}/light/setTopPage.do?itemId="+itemId+"&isTop="+isTop,
		  type: "post",
		  dataType: "json",
		  success:function(data){
			  if(data.success){
				  window.location.href = "${ctx}/light/listPage.do?channelId=2";
			  }
			  
		  }
		  
	  });
  }
  
  // 定时器
  function timerPage(){
	  var itemIds_arr = document.getElementsByName("checkpage");
	  var checkStatus_arr = document.getElementsByName("checkStatus");
	  
	  var count = 0;
	  var itemId = "";
	  for(var i=0; i<itemIds_arr.length; i++){
		  if( itemIds_arr[i].checked ){
			  
			  var pageStatus = checkStatus_arr[i].value;
			  
			  if(pageStatus != 1){
				  showMessageDialog("只能选择待发布");
				  return false;
			  }
			  
			  itemId = itemIds_arr[i].value;
			  
			  count ++;
		  }
		  
	  }
	  
	  if(count == 0){
		  showMessageDialog("至少一条记录");
		  return false;
	  }else if(count > 1){
		  showMessageDialog("只能选择一条记录");
		  return false;
	  }
	  
	  window.location.href = "${ctx}/light/jumpTimerPage.do?itemId="+itemId;
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
  
  // 发布
  function postPage(){
	  var checkPage_arr = document.getElementsByName("checkpage");
	  var pageStaus_arr = document.getElementsByName("checkStatus");
	  var count = 0;
	  var itemId = "";
	  for(var i=0;i<checkPage_arr.length;i++){
		  if(checkPage_arr[i].checked){
			  //判断当前被选中项的问卷状态
			  /* if(pageStaus_arr[i].value != 1){
				  showMessageDialog("只能选择待发布状态");
				  return;
			  } */
			  count++;
			  itemId = checkPage_arr[i].value + "!" +itemId;
		  }
		  
	  }
	  if(count == 0){
		  showMessageDialog("未选择");
		  return;
	  }
	   $.ajax({
		  type:"post",
		  dataType:"json",
		  url:"${ctx}/light/changePageStatus.do?itemId="+itemId+"&status="+1,
		  success:function(data){
			  showMessageDialog(data.msg, function(){
				  if(data.success){
					  window.location.href = "${ctx}/light/listPage.do?channelId=2";
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
  function deletePage(){
	  var checkPage_arr = document.getElementsByName("checkpage");
	  var pageStaus_arr = document.getElementsByName("checkStatus");
	  var count = 0;
	  var itemId = "";
	  for(var i=0;i<checkPage_arr.length;i++){
		  if(checkPage_arr[i].checked){
			  //判断当前被选中项的问卷状态
			  if(pageStaus_arr[i].value != 1){
				  showMessageDialog("只能删除待发布");
				  return;
			  }
			  count++;
			  itemId = checkPage_arr[i].value + "!" +itemId;
		  }
		  
	  }
	  if(count == 0){
		  showMessageDialog("未选择");
		  return;
	  }
	  showConfirmDialog("要删除问卷吗？", function(check){
		  if(check){
			   $.ajax({
					  type:"post",
					  dataType:"json",
					  url:"${ctx}/light/changePageStatus.do?itemId="+itemId+"&status="+3,
					  success:function(data){
						  showMessageDialog(data.msg, function(){
							  if(data.success){
								  window.location.href = "${ctx}/light/listPage.do?channelId=2";
							  }
						  });
					  }
				  }); 
		  }
	  });

  }
  
   // 撤销
  function revokePage(){
	  var checkPage_arr = document.getElementsByName("checkpage");
	  var pageStaus_arr = document.getElementsByName("checkStatus");
	  var count = 0;
	  var itemId = "";
	  for(var i=0;i<checkPage_arr.length;i++){
		  if(checkPage_arr[i].checked){
			  //判断当前被选中项的问卷状态
			  if(pageStaus_arr[i].value != 3){
				  showMessageDialog("只能撤销发布中");
				  return;
			  }
			  count++;
			  itemId = checkPage_arr[i].value + "!" +itemId;
		  }
		  
	  }
	  if(count == 0){
		  showMessageDialog("未选择");
		  return;
	  }
	   $.ajax({
		  type:"post",
		  dataType:"json",
		  url:"${ctx}/light/changePageStatus.do?itemId="+itemId+"&status="+2,
		  success:function(data){
			  showMessageDialog(data.msg, function(){
				  if(data.success){
					  window.location.href = "${ctx}/light/listPage.do?channelId=2";
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