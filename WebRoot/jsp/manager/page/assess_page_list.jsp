<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>测一发页面</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
</head>

<body class="hold-transition skin-blue sidebar-mini">
	<div class="box box-primary">
		<table style="padding: 100px;margin: 10px;">
			<tr>
				<td style="width: 100px;padding: 3px;"><a href="${ctx}/platform/loadDaily3UpdateList.do">
				<button class="btn btn-block btn-primary" style="">页面栏目</button></a></td>
				<td style="width: 100px;padding: 3px;"><a href="${ctx}/platform/loadSuperList.do">
					<button class="btn btn-block btn-primary">合作问卷</button></a></td>
				<td style="width: 100px;padding: 3px;"><a　href="${ctx}/platform/loadAssessList.do">
					<button class="btn btn-block btn-primary" style="background:green">专业测评</button></a></td>
				<td style="width: 100px;padding: 3px;"><a href="${ctx}/platform/loadReportList.do">
					<button class="btn btn-block btn-primary">数字报告</button></a></td>
			</tr>
		</table>
		<!-- 内容部分 -->


		<!-- Main content -->
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" method="post" action="${ctx}/platform/loadAssessList.do" id="pageListForm">
						<table style="border-spacing: 10px;border-collapse: separate;">
							<tr>
								<td><select class="form-control select-sm" name="choose"
									id="choose" >
										<option value="0">问卷名称</option>
										<option value="1">投放Id</option>
								</select></td>
								<td><input id="aqnName" name="aqnName" type="text"
									class="form-control"></td>
								<td><label>页面状态</label></td>
								<td><select class="form-control select-sm"
									name="pageStatus" id="pageStatus">
										<option value="">不限</option>
										<option value="1">待发布</option>
										<option value="2">定时发布</option>
										<option value="3">已发布</option>
								</select></td>

								<td><label> 栏目名称 </label></td>
								
								<td>
									<select class="form-control" name="assessListCategory" id="assessListCategory">
										<option value="professialAssessQnList">专业测评</option>
									</select>
								</td>
								
								<td><span class="input-group-btn">
										<button type="button" onclick="searchAssessQuestionnaire()" class="btn btn-info btn-flat">查询</button>
								</span></td>
							</tr>
						</table>		
					</form>				
				</div>
				<div class="panel-body">
					<div class="button-group">
						
						<button type="button" onclick="addAssess()"
							class="btn btn-default btn-sm">添加</button>
						<button type="button" onclick="postAssess()"
							class="btn btn-default btn-sm">发布</button>
						<button type="button" onclick="revokeAssess()"
							class="btn btn-default btn-sm">撤销</button>
						<button type="button" onclick="deleteAssessFromList()"
							class="btn btn-default btn-sm">删除</button>
						<button type="button" onclick="timerAssessUpdate()"
							class="btn btn-default btn-sm">定时器</button>
					</div>
				</div>
			</div>
	</div>
	<div class="panel panel-default">
		<div class="box">
			<!-- /.box-header -->
			<div class="box-body">
				<table id="example1" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th></th>
							<th>选择</th>
							<th>投放ID</th>
							<th>标题</th>
							<th>页面状态</th>
							<th>投放状态</th>
							<th>发布时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${niAssess}" var="assess" varStatus="n">
							<c:if test="${assess.isTop==2}">
								<c:if test="${assess.pageStatus!=3}">
									<tr style="background:#87CEFA">
										<td><c:if test="${assess.isTop==2}">${n.index+1}</c:if> 
											<c:if test="${assess.isTop==1}"><i class="fa fa-fw fa-chevron-up"></i></c:if></td>
										<td>
											<input type="checkbox" id="checkniAssess" name="checkniAssess" value="${assess.deliveryId }"> 
											<input type="hidden" value="${assess.pageStatus}" name="deleStat" id="deleStat">   <!-- 页面状态 -->
											<input type="hidden" value="${assess.status}" name="status" id="status">   		   <!-- 投放状态 -->
											<input type="hidden" value="${assess.showOrder}" name="showorder" id="showorder"></td>
										<td>${assess.deliveryId }</td>
										<td>${assess.showTitle }</td>
										<td><c:if test="${assess.pageStatus== 2}">定时发布</c:if> <c:if
												test="${assess.pageStatus == 1 }">待发布</c:if> <c:if
												test="${assess.pageStatus == 3}">已发布</c:if></td>
												
										<td><c:if test="${assess.status== 2}">投放中</c:if> <c:if
												test="${assess.status == 1}">待投放</c:if> <c:if
												test="${assess.status == 3}">暂停中</c:if> <c:if
												test="${assess.status == 4}">人工终止</c:if> <c:if
												test="${assess.status == 5}">时间完成</c:if> <c:if
												test="${assess.status == 6}">数量完成</c:if></td>
												
										<td><fmt:formatDate value="${assess.pTime }" type="both" /></td>
										
										<%-- <td><fmt:formatDate value="${assess.uTime }" type="both" /></td> --%>
										
										<td>
											<button type="button"
												onclick="editAssessList(${assess.showOrder },${assess.pageStatus})"
												class="btn btn-default btn-sm">编辑</button>
											<button type="button"
												onclick="moveUp(${n.index+1},${assess.deliveryId },${assess.showOrder },${assess.pageStatus})"
												class="btn btn-default btn-sm">上移</button>
											<button type="button"
												onclick="moveDown(${n.index+1},${assess.deliveryId },${assess.showOrder },${assess.pageStatus})"
												class="btn btn-default btn-sm">下移</button>
											<button type="button" id="top"
												onclick="changeIsTop(${assess.deliveryId},${assess.isTop},${assess.pageStatus})"
												class="btn btn-default btn-sm">
												<c:if test="${assess.isTop==2}">置顶</c:if>
												<c:if test="${assess.isTop==1}">取消</c:if>
											</button>
										</td>
									</tr>
								</c:if>
								<c:if test="${assess.pageStatus==3}">
									<tr>
									<td><c:if test="${assess.isTop==2}">${n.index+1}</c:if> <c:if
											test="${assess.isTop==1}">
											<i class="fa fa-fw fa-chevron-up"></i>
										</c:if></td>
										<td>
								<input type="checkbox" id="checkniAssess" name="checkniAssess" value="${assess.deliveryId }"> 
								<input type="hidden" value="${assess.pageStatus}" name="deleStat" id="deleStat">
								<input type="hidden" value="${assess.status}" name="status" id="status">
								<input type="hidden" value="${assess.showOrder}" name="showorder" id="showorder">
									</td>
										<td>${assess.deliveryId }</td>
										<td>${assess.showTitle }</td>
										<td><c:if test="${assess.pageStatus== 2}">定时发布</c:if> <c:if
												test="${assess.pageStatus == 1 }">待发布</c:if> <c:if
												test="${assess.pageStatus == 3}">已发布</c:if></td>
										<td><c:if test="${assess.status== 2}">投放中</c:if> <c:if
												test="${assess.status == 1}">待投放</c:if> <c:if
												test="${assess.status == 3}">暂停中</c:if> <c:if
												test="${assess.status == 4}">人工终止</c:if> <c:if
												test="${assess.status == 5}">时间完成</c:if> <c:if
												test="${assess.status == 6}">数量完成</c:if></td>
												
										<td><fmt:formatDate value="${assess.pTime }" type="both" /></td>
										<%-- <td><fmt:formatDate value="${assess.uTime }" type="both" /></td> --%>
										
										<td>
											<button type="button"
												onclick="editAssessList(${assess.showOrder },${assess.pageStatus})"
												class="btn btn-default btn-sm">编辑</button>
											<button type="button"
												onclick="moveUp(${n.index+1},${assess.deliveryId },${assess.showOrder },${assess.pageStatus})"
												class="btn btn-default btn-sm">上移</button>
											<button type="button"
												onclick="moveDown(${n.index+1},${assess.deliveryId },${assess.showOrder },${assess.pageStatus})"
												class="btn btn-default btn-sm">下移</button>
											<button type="button" id="top"
												onclick="changeIsTop(${assess.deliveryId},${assess.isTop},${assess.pageStatus})"
												class="btn btn-default btn-sm">
												<c:if test="${assess.isTop==2}">置顶</c:if>
												<c:if test="${assess.isTop==1}">取消</c:if>
											</button>
										</td>
									</tr>
								</c:if>
							</c:if>
							<c:if test="${assess.isTop==1}">
								<tr style="background:#FFDAB9">
								<td><c:if test="${assess.isTop==2}">${n.index+1}</c:if> <c:if
										test="${assess.isTop==1}">
										<i class="fa fa-fw fa-chevron-up"></i>
									</c:if></td>
									<td>
									<input type="checkbox" id="checkniAssess" name="checkniAssess" value="${assess.deliveryId }"> 
									<input type="hidden" value="${assess.pageStatus}" name="deleStat" id="deleStat">
									<input type="hidden" value="${assess.status}" name="status" id="status">
									<input type="hidden" value="${assess.showOrder}" name="showorder" id="showorder"></td>
									<td>${assess.deliveryId }</td>
									<td>${assess.showTitle }</td>
									<td><c:if test="${assess.pageStatus== 2}">定时发布</c:if> <c:if
											test="${assess.pageStatus == 1 }">待发布</c:if> <c:if
											test="${assess.pageStatus == 3}">已发布</c:if></td>
									<td><c:if test="${assess.status== 2}">投放中</c:if> <c:if
											test="${assess.status == 1}">待投放</c:if> <c:if
											test="${assess.status == 3}">暂停中</c:if> <c:if
											test="${assess.status == 4}">人工终止</c:if> <c:if
											test="${assess.status == 5}">时间完成</c:if> <c:if
											test="${assess.status == 6}">数量完成</c:if></td>
											
									<td><fmt:formatDate value="${assess.pTime }" type="both" /></td>
									<%-- <td><fmt:formatDate value="${assess.uTime }" type="both" /></td> --%>
									
									<td>
										<button type="button"
											onclick="editAssessList(${assess.showOrder },${assess.pageStatus})"
											class="btn btn-default btn-sm">编辑</button>
										<button type="button"
											onclick="moveUp(${n.index+1},${assess.deliveryId },${assess.showOrder },${assess.pageStatus})"
											class="btn btn-default btn-sm">上移</button>
										<button type="button"
											onclick="moveDown(${n.index+1},${assess.deliveryId },${assess.showOrder },${assess.pageStatus})"
											class="btn btn-default btn-sm">下移</button>
										<button type="button" id="top"
											onclick="changeIsTop(${assess.deliveryId},${assess.isTop},${assess.pageStatus})"
											class="btn btn-default btn-sm">
											<c:if test="${assess.isTop==2}">置顶</c:if>
											<c:if test="${assess.isTop==1}">取消</c:if>
										</button>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- /.box-body -->
		</div>
	</div>

	<!-- 内容部分结束 -->
	<div class="control-sidebar-bg"></div>
	<script src="${ctx}/static/js/moment.min.js"></script>
	<script src="${ctx}/js/validate.js"></script>
	<script>
		//发布测评问卷
		function postAssess() {
			var assessListCategory = $("#assessListCategory").val();  //获取测评类型
			
			var checkniAssess = document.getElementsByName("checkniAssess");   //复选框对象
			var poststatus = document.getElementsByName("status");    //投放状态
			
			var count = 0;
			var deliveryId = "";
			for (var i = 0; i < checkniAssess.length; i++) {
				if (checkniAssess[i].checked) {
					var stat = poststatus[i].value;
					if (stat != 2) {
						alert("只能发布投放中的问卷,请重新选择");
						return;
					}
				}
				if (checkniAssess[i].checked) {
					count++;
					deliveryId = checkniAssess[i].value + "!" + deliveryId;
				}

			}
			if (count == 0) {
				alert("请选择要发布的测评问卷");
				return;
			}
			$.ajax({
				type : "post",
				dataType : "json",
				url : "${ctx}/platform/postAssess.do",
				data : {
					deliveryId : deliveryId,
					assessListCategory : assessListCategory
				},
				success : function(data) {
					if (data.success) {
						alert("发布测评成功");
						window.location.href = "${ctx}/platform/loadAssessList.do?assessListCategory="+assessListCategory;
					}
				}
			});
		}
		 
		 
		//撤销测评
		function revokeAssess() {
			var assessListCategory = $("#assessListCategory").val();  //获取测评类型
			
			var checkniAssess = document.getElementsByName("checkniAssess");
			var count = 0;
			var deliveryId = "";
			for (var i = 0; i < checkniAssess.length; i++) {
				if (checkniAssess[i].checked) {
					count++;
					deliveryId = checkniAssess[i].value + "!" + deliveryId;
				}

			}
			if (count == 0) {
				alert("请选择要撤销的问卷");
				return;
			}
			showConfirmDialog("确定要撤销吗?",function(check) {
						if (check) {
							$.ajax({
								type : "post",
								dataType : "json",
								url : "${ctx}/platform/revokeAssess.do",
								data : {
									deliveryId : deliveryId,
									assessListCategory : assessListCategory
								},
								success : function(data) {
									if (data.success) {
										alert("撤销测评成功");
										window.location.href = "${ctx}/platform/loadAssessList.do?assessListCategory="+assessListCategory;
									}
								}
							});
						}
					});
		}
		//刷新测评列表
		function refreshAssess() {
			var assessListCategory = $("#assessListCategory").val();  //获取测评类型
			window.location.href = "${ctx}/platform/loadAssessList.do?assessListCategory="+assessListCategory;
		}
		//更改置顶状态
		function changeIsTop(deliveryId, isTop, pageStatus) {
			var assessListCategory = $("#assessListCategory").val();  //获取测评类型
			
			//当状态为置顶则取消置顶
			if (pageStatus != 3) {
				alert("必须为发布状态才能调整位置！");
				return;
			}
			if (isTop == 1) {
				$.ajax({
							type : "post",
							dataType : "json",
							url : "${ctx}/platform/topCancelAssess.do",
							data : {
								deliveryId : deliveryId,
								assessListCategory : assessListCategory								
							},
							success : function(data) {
								if (data.success) {
									alert("取消置顶成功");
									window.location.href = "${ctx}/platform/loadAssessList.do?assessListCategory="+assessListCategory;
								}
							}
						});
			}
			if (isTop == 2) {
				$.ajax({
					type : "post",
					dataType : "json",
					url : "${ctx}/platform/topAssess.do",
					data : {
						deliveryId : deliveryId,
						assessListCategory : assessListCategory
					},
					success : function(data) {
						if (data.success) {
							alert("置顶成功");
							window.location.href = "${ctx}/platform/loadAssessList.do?assessListCategory="+assessListCategory;
						}
					}
				});
			}
		}

		//跳转到添加页面
		function addAssess() {
			window.location.href = "${ctx}/platform/add_AssessPage.do";
		}
		//查询
		function searchAssessQuestionnaire() {	
			var aqnNameL = 22;
			var aqnName = $("#aqnName").val();
			if (aqnName.trim().length > aqnNameL) {
				alert("问卷名称不能超过" + aqnNameL + "字符");
				return;
			}
			$('#pageListForm').submit();
		}

		function editAssessList(showOrder, editStat) {
			var assessListCategory = $("#assessListCategory").val();  //获取测评类型
			
			if (editStat != 1) {
				alert("只能编辑待发布状态的问卷,请先撤销问卷");
				return;
			}
			window.location.href = "${ctx}/platform/editAssessList.do?showOrder="+showOrder+"&assessListCategory="+assessListCategory;
		}
		
//定时器
function timerAssessUpdate(){
	var assessListCategory = $("#assessListCategory").val();  //获取测评类型
	
	var niAssessDevId_arr = document.getElementsByName("checkniAssess"); //投放id
	var pageStats_arr = document.getElementsByName("deleStat");   //页面状态
	var devStatus_arr = document.getElementsByName("status");   //投放状态
	
	var count = 0;
	var deliveryId = "";
	for(var i=0; i<niAssessDevId_arr.length ; i++){
		if(niAssessDevId_arr[i].checked){
			count ++;
			
			deliveryId = niAssessDevId_arr[i].value;
			
			var devStatus = devStatus_arr[i].value;
			var pageStat = pageStats_arr[i].value;
			
			if(devStatus != 2){
				showMessageDialog("只能选择投放中问卷,请重新选择");
				return false;
			}else if(pageStat != 1){
				showMessageDialog("只能选择页面状态为待发布的问卷,请重新选择");
				return false;
			}
		}
	}
	
	if(count == 0){
		showMessageDialog("请选择至少一条记录");
		return false;
	}else if(count > 1){
		showMessageDialog("只能选择一条记录");
		return false;
	}
	
	window.location.href = "${ctx}/platform/jumpAssessTimerPage.do?deliveryId="+deliveryId+"&assessListCategory="+assessListCategory;
}
		
		//删除
		function deleteAssessFromList() {
			var assessListCategory = $("#assessListCategory").val();  //获取测评类型
			
			var checkniAssess = document.getElementsByName("checkniAssess");
			var deleStats = document.getElementsByName("deleStat");
			var count = 0;
			var deliveryId = "";
			for (var i = 0; i < checkniAssess.length; i++) {
				if (checkniAssess[i].checked) {
					var stat = deleStats[i].value;
					if (stat != 1) {
						alert("只能删除待发布状态的问卷,请重新选择");
						return;
					}
				}
				if (checkniAssess[i].checked) {
					count++;
					deliveryId = checkniAssess[i].value + "!" + deliveryId;
				}
			}
			if (count == 0) {
				alert("请选择要删除的问卷");
				return;
			}
			showConfirmDialog(
					"确定要删除吗?",
					function(check) {
						if (check) {
							$.ajax({
								url : "${ctx}/platform/deleteAssessFromList.do",
								type : "post",
								data : {
									deliveryId : deliveryId,
									assessListCategory : assessListCategory
								},
								dataType : "json",
								success : function(data) {
									alert(data.msg);
									if (data.success) {
										window.location.href = "${ctx}/platform/loadAssessList.do?assessListCategory="+assessListCategory;
									}
								}
							});
						}
					});
		}
		//a为当前条目的下一个
		function moveUp(a, deliveryId, showOrder, pageStatus) {
			var assessListCategory = $("#assessListCategory").val();  //获取测评类型
			
			var index = a - 2;	//index为当前条目的上一个
			if (pageStatus != 3) {
				alert("必须为发布状态才能调整位置！");
				return;
			}
			if (a == 1) {
				alert("无法继续上移！");
				return;
			}
			var deliveryIds = document.getElementsByName("checkniAssess");
			var showOrders = document.getElementsByName("showorder");
			var deleStats = document.getElementsByName("deleStat");
			var lastDeliveryId = deliveryIds[index].value;
			var lastShowOrder = showOrders[index].value;
			var lastPageStat = deleStats[index].value;
			if (lastPageStat == 1) {
				alert("无法继续上移！");
				return;
			}
			$.ajax({
						url : "${ctx}/platform/moveUpAssess.do",
						type : "post",
						data : {
							deliveryId : deliveryId,
							showOrder : showOrder,
							lastDeliveryId : lastDeliveryId,
							lastShowOrder : lastShowOrder,
							assessListCategory : assessListCategory
						},
						dataType : "json",
						success : function(data) {
							//	alert(data.msg);
							if (data.success) {
								window.location.href = "${ctx}/platform/loadAssessList.do?assessListCategory="+assessListCategory;
							}
						}
					});
		}
		function moveDown(a, deliveryId, showOrder, pageStatus) {
			var assessListCategory = $("#assessListCategory").val();  //获取测评类型
			
			var index = a;
			if (pageStatus != 3) {
				alert("必须为发布状态才能调整位置！");
				return;
			}
			var deliveryIds = document.getElementsByName("checkniAssess");
			var showOrders = document.getElementsByName("showorder");
			if (index >= deliveryIds.length) {
				alert("无法继续下移");
				return;
			}
			var nextDeliveryId = deliveryIds[index].value;
			var nextShowOrder = showOrders[index].value;
			$.ajax({
					url : "${ctx}/platform/moveDownAssess.do",
					type : "post",
					data : {
						deliveryId : deliveryId,
						showOrder : showOrder,
						nextDeliveryId : nextDeliveryId,
						nextShowOrder : nextShowOrder,
						assessListCategory : assessListCategory
					},
					dataType : "json",
					success : function(data) {
						//	alert(data.msg);
						if (data.success) {
							window.location.href = "${ctx}/platform/loadAssessList.do?assessListCategory="+assessListCategory;
						}
					}
				});
		}
		
	</script>
</body>

</html>