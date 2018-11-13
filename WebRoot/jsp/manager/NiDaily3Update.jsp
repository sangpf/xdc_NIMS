<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>每日三更</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
</head>

<body class="hold-transition skin-blue sidebar-mini">
	<div class="box box-primary">
		<table style="padding: 100px;margin: 10px;">
			<tr>
				<td style="width: 100px;padding: 3px;"><a
					href="${ctx}/platform/loadDaily3UpdateList.do"><button
							type="button" class="btn btn-block btn-primary"
							style="background:green">每日三更</button></a></td>
				<td style="width: 100px;padding: 3px;"><a
					href="${ctx}/platform/loadSuperList.do"><button type="button"
							class="btn btn-block btn-primary">超级调查</button></a></td>
				<td style="width: 100px;padding: 3px;"><a
					href="${ctx}/platform/loadAssessList.do"><button type="button"
							class="btn btn-block btn-primary">测一发</button></a></td>
				<td style="width: 100px;padding: 3px;"><a
					href="${ctx}/platform/loadReportList.do"><button type="button"
							class="btn btn-block btn-primary">真相</button></a></td>
			</tr>
		</table>
		<div class="box-header with-border">
			<h3 class="box-title">列表管理—每日三更</h3>
		</div>
		<!-- 内容部分 -->


		<!-- Main content -->
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" method="post" action="${ctx}/platform/loadDaily3UpdateList.do" id="pageListForm">
						<table style="border-spacing: 10px;border-collapse: separate;">
							<tr>
								<td><select class="form-control select-sm" name="choose"
									id="choose">
										<option value="0">问卷名称</option>
										<option value="1">投放Id</option>
								</select></td>
								<td><input id="qnName" name="qnName" type="text"
									class="form-control"></td>
								<td><label>页面状态</label></td>
								<td><select class="form-control select-sm"
									name="pageStatus" id="pageStatus">
										<option value="">不限</option>
										<option value="1">待发布</option>
										<option value="2">定时发布</option>
										<option value="3">已发布</option>
								</select></td>

								<td><label>投放状态</label></td>
								<td width="160"><select class="form-control select-sm"
									name="deliveryStatus" id="deliveryStatus">
										<option value="">不限</option>
										<option value="1">待投放</option>
										<option value="2">投放中</option>
										<option value="3">暂停中</option>
										<option value="4">人工中止</option>
										<option value="5">时间完成</option>
										<option value="6">数量完成</option>
								</select></td>
								<td><label>类型</label></td>
								<td width="160"><select class="form-control select-sm"
									name="qnType" id="qnType">
										<option value="">不限</option>
										<option value="1">调查</option>
								</select></td>
								
								<td><label>&nbsp;栏目名称</label></td>
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
											class="btn btn-info btn-flat">Go!</button>
								</span></td>
							</tr>
						</table>
						<div class="input-group col-sm-3">
							<div class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</div>
							<input type="text" class="form-control pull-right"
								name="reservation" id="reservation" style="height:30px;"
								placeholder="请选择日期">
						</div>
				</form>
				</div>
				<div class="panel-body">
					<div class="button-group">

						<button type="button" onclick="addDaily3Update()" class="btn btn-default btn-sm">添加</button>
						<button type="button" onclick="postDaily3Update()" class="btn btn-default btn-sm">发布</button>
						<button type="button" onclick="revokeDaily3Update()"
							class="btn btn-default btn-sm">撤销</button>
						<button type="button" onclick="deleteDaily3UpdateFromList()"
							class="btn btn-default btn-sm">删除</button>
						<button type="button" onclick="refreshDaily3Update()"
							class="btn btn-default btn-sm">刷新</button>
							
						<button type="button" onclick="timerDaily3Update()"
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
							<th>编号</th>
							<th>投放ID</th>
							<th>标题</th>
							<th>问卷类型</th>
							<th>页面状态</th>
							<th>投放状态</th>
							<th>发布时间</th>
							<th>更新时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${niDaily3Update}" var="daily3updateSurvey"
							varStatus="n">
							<c:if test="${daily3updateSurvey.isTop==2}">
								<c:if test="${daily3updateSurvey.pageStatus!=3}">
									<tr style="background:#87CEFA">
										<td width="20px"><input type="checkbox"
											id="checkniDaily3Update" name="checkniDaily3Update"
											value="${daily3updateSurvey.deliveryId }"> <input
											type="hidden" value="${daily3updateSurvey.pageStatus}"
											name="deleStat" id="deleStat"><input type="hidden"
											value="${daily3updateSurvey.qnType}" name="qnTypes"
											id="qnTypes"><input type="hidden"
											value="${daily3updateSurvey.status}" name="status"
											id="status"><input type="hidden"
											value="${daily3updateSurvey.showOrder}" name="showorder"
											id="showorder"></td>
										<td><c:if test="${daily3updateSurvey.isTop==2}">${n.index+1}</c:if>
											<c:if test="${daily3updateSurvey.isTop==1}">
												<i class="fa fa-fw fa-chevron-up"></i>
											</c:if></td>
										<td>${daily3updateSurvey.deliveryId }</td>
										<td width="200px">${daily3updateSurvey.showTitle }</td>
										<td><c:if test="${daily3updateSurvey.qnType==1}">调查</c:if>
											<c:if test="${daily3updateSurvey.qnType==2}">测评</c:if> <c:if
												test="${daily3updateSurvey.qnType==3}">投票</c:if></td>
										<td><c:if test="${daily3updateSurvey.pageStatus== 2}">定时发布</c:if>
											<c:if test="${daily3updateSurvey.pageStatus == 1}">待发布</c:if>
											<c:if test="${daily3updateSurvey.pageStatus == 3}">已发布</c:if></td>
										<td><c:if test="${daily3updateSurvey.status== 2}">投放中</c:if>
											<c:if test="${daily3updateSurvey.status == 1}">待投放</c:if> <c:if
												test="${daily3updateSurvey.status == 3}">暂停中</c:if> <c:if
												test="${daily3updateSurvey.status == 4}">人工终止</c:if> <c:if
												test="${daily3updateSurvey.status == 5}">时间完成</c:if> <c:if
												test="${daily3updateSurvey.status == 6}">数量完成</c:if></td>
										<td><fmt:formatDate value="${daily3updateSurvey.pTime }"
												type="both" /></td>
										<td><fmt:formatDate value="${daily3updateSurvey.uTime }"
												type="both" /></td>
										<td>
											<button type="button"
												onclick="editDaily3UpdateList(${daily3updateSurvey.showOrder },${daily3updateSurvey.pageStatus})"
												class="btn btn-default btn-sm">编辑</button>
											<button type="button"
												onclick="moveUp(${n.index+1},${daily3updateSurvey.deliveryId },${daily3updateSurvey.qnType },${daily3updateSurvey.showOrder },${daily3updateSurvey.pageStatus})"
												class="btn btn-default btn-sm">上移</button>
											<button type="button"
												onclick="moveDown(${n.index+1},${daily3updateSurvey.deliveryId },${daily3updateSurvey.qnType },${daily3updateSurvey.showOrder },${daily3updateSurvey.pageStatus})"
												class="btn btn-default btn-sm">下移</button>
											<button type="button" id="top"
												onclick="changeIsTop(${daily3updateSurvey.deliveryId},${daily3updateSurvey.isTop},${daily3updateSurvey.qnType},${daily3updateSurvey.pageStatus})"
												class="btn btn-default btn-sm">
												<c:if test="${daily3updateSurvey.isTop==2}">置顶</c:if>
												<c:if test="${daily3updateSurvey.isTop==1}">取消</c:if>
											</button>
											
										</td>
									</tr>
								</c:if>
								<c:if test="${daily3updateSurvey.pageStatus==3}">
									<tr>
										<td width="20px"><input type="checkbox"
											id="checkniDaily3Update" name="checkniDaily3Update"
											value="${daily3updateSurvey.deliveryId }"> <input
											type="hidden" value="${daily3updateSurvey.pageStatus}"
											name="deleStat" id="deleStat"><input type="hidden"
											value="${daily3updateSurvey.qnType}" name="qnTypes"
											id="qnTypes"><input type="hidden"
											value="${daily3updateSurvey.status}" name="status"
											id="status"><input type="hidden"
											value="${daily3updateSurvey.showOrder}" name="showorder"
											id="showorder"></td>
										<td><c:if test="${daily3updateSurvey.isTop==2}">${n.index+1}</c:if>
											<c:if test="${daily3updateSurvey.isTop==1}">
												<i class="fa fa-fw fa-chevron-up"></i>
											</c:if></td>
										<td>${daily3updateSurvey.deliveryId }</td>
										<td width="200px">${daily3updateSurvey.showTitle }</td>
										<td><c:if test="${daily3updateSurvey.qnType==1}">调查</c:if>
											<c:if test="${daily3updateSurvey.qnType==2}">测评</c:if> <c:if
												test="${daily3updateSurvey.qnType==3}">投票</c:if></td>
										<td><c:if test="${daily3updateSurvey.pageStatus== 2}">定时发布</c:if>
											<c:if test="${daily3updateSurvey.pageStatus == 1}">待发布</c:if>
											<c:if test="${daily3updateSurvey.pageStatus == 3}">已发布</c:if></td>
										<td><c:if test="${daily3updateSurvey.status== 2}">投放中</c:if>
											<c:if test="${daily3updateSurvey.status == 1}">待投放</c:if> <c:if
												test="${daily3updateSurvey.status == 3}">暂停中</c:if> <c:if
												test="${daily3updateSurvey.status == 4}">人工终止</c:if> <c:if
												test="${daily3updateSurvey.status == 5}">时间完成</c:if> <c:if
												test="${daily3updateSurvey.status == 6}">数量完成</c:if></td>
										<td><fmt:formatDate value="${daily3updateSurvey.pTime }"
												type="both" /></td>
										<td><fmt:formatDate value="${daily3updateSurvey.uTime }"
												type="both" /></td>
										<td>
											<button type="button"
												onclick="editDaily3UpdateList(${daily3updateSurvey.showOrder },${daily3updateSurvey.pageStatus})"
												class="btn btn-default btn-sm">编辑</button>
											<button type="button"
												onclick="moveUp(${n.index+1},${daily3updateSurvey.deliveryId },${daily3updateSurvey.qnType },${daily3updateSurvey.showOrder },${daily3updateSurvey.pageStatus})"
												class="btn btn-default btn-sm">上移</button>
											<button type="button"
												onclick="moveDown(${n.index+1},${daily3updateSurvey.deliveryId },${daily3updateSurvey.qnType },${daily3updateSurvey.showOrder },${daily3updateSurvey.pageStatus})"
												class="btn btn-default btn-sm">下移</button>
											<button type="button" id="top"
												onclick="changeIsTop(${daily3updateSurvey.deliveryId},${daily3updateSurvey.isTop},${daily3updateSurvey.qnType},${daily3updateSurvey.pageStatus})"
												class="btn btn-default btn-sm">
												<c:if test="${daily3updateSurvey.isTop==2}">置顶</c:if>
												<c:if test="${daily3updateSurvey.isTop==1}">取消</c:if>
											</button>
										</td>
									</tr>
								</c:if>
							</c:if>
							<c:if test="${daily3updateSurvey.isTop==1}">
								<tr style="background:#FFDAB9">
									<td width="20px"><input type="checkbox"
										id="checkniDaily3Update" name="checkniDaily3Update"
										value="${daily3updateSurvey.deliveryId }"> <input
										type="hidden" value="${daily3updateSurvey.pageStatus}"
										name="deleStat" id="deleStat"><input type="hidden"
										value="${daily3updateSurvey.qnType}" name="qnTypes"
										id="qnTypes"><input type="hidden"
										value="${daily3updateSurvey.status}" name="status" id="status"><input
										type="hidden" value="${daily3updateSurvey.showOrder}"
										name="showorder" id="showorder"></td>
									<td><c:if test="${daily3updateSurvey.isTop==2}">${n.index+1}</c:if>
										<c:if test="${daily3updateSurvey.isTop==1}">
											<i class="fa fa-fw fa-chevron-up"></i>
										</c:if></td>
									<td>${daily3updateSurvey.deliveryId }</td>
									<td width="200px">${daily3updateSurvey.showTitle }</td>
									<td><c:if test="${daily3updateSurvey.qnType==1}">调查</c:if>
										<c:if test="${daily3updateSurvey.qnType==2}">测评</c:if> <c:if
											test="${daily3updateSurvey.qnType==3}">投票</c:if></td>
									<td><c:if test="${daily3updateSurvey.pageStatus== 2}">定时发布</c:if>
										<c:if test="${daily3updateSurvey.pageStatus == 1}">待发布</c:if>
										<c:if test="${daily3updateSurvey.pageStatus == 3}">已发布</c:if></td>
									<td><c:if test="${daily3updateSurvey.status== 2}">投放中</c:if>
										<c:if test="${daily3updateSurvey.status == 1}">待投放</c:if> <c:if
											test="${daily3updateSurvey.status == 3}">暂停中</c:if> <c:if
											test="${daily3updateSurvey.status == 4}">人工终止</c:if> <c:if
											test="${daily3updateSurvey.status == 5}">时间完成</c:if> <c:if
											test="${daily3updateSurvey.status == 6}">数量完成</c:if></td>
									<td><fmt:formatDate value="${daily3updateSurvey.pTime }"
											type="both" /></td>
									<td><fmt:formatDate value="${daily3updateSurvey.uTime }"
											type="both" /></td>
									<td>
										<button type="button"
											onclick="editDaily3UpdateList(${daily3updateSurvey.showOrder },${daily3updateSurvey.pageStatus})"
											class="btn btn-default btn-sm">编辑</button>
										<button type="button"
											onclick="moveUp(${n.index+1},${daily3updateSurvey.deliveryId },${daily3updateSurvey.qnType },${daily3updateSurvey.showOrder },${daily3updateSurvey.pageStatus})"
											class="btn btn-default btn-sm">上移</button>
										<button type="button"
											onclick="moveDown(${n.index+1},${daily3updateSurvey.deliveryId },${daily3updateSurvey.qnType },${daily3updateSurvey.showOrder },${daily3updateSurvey.pageStatus})"
											class="btn btn-default btn-sm">下移</button>
										<button type="button" id="top"
											onclick="changeIsTop(${daily3updateSurvey.deliveryId},${daily3updateSurvey.isTop},${daily3updateSurvey.qnType},${daily3updateSurvey.pageStatus})"
											class="btn btn-default btn-sm">
											<c:if test="${daily3updateSurvey.isTop==2}">置顶</c:if>
											<c:if test="${daily3updateSurvey.isTop==1}">取消</c:if>
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

<div class="modal fade" id="timer_Modal"  role="dialog" aria-hidden="true">
    	 <div class="modal-dialog" >
    	 	<div class="modal-content" >
    	 	
    	 		<div class="modal-header">
            		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
           			<h4 class="modal-title" id="myModalLabel">选择发布时间</h4>
           		</div>
           		
           		<div class="modal-body">
	           		<div class="form-group">
	 					<label class="control-label col-sm-2" >时间</label>
	  					<div class="col-sm-9">
	  						<input type="text" class="form-control " id="ruleName_add">
						</div>
					</div>
					
    	 		</div>
    	 	
    	 		<div class="modal-footer">
            		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            		<button type="button" onclick="lotteryPreferAdd_Save();" class="btn btn-primary">保存</button>
         		</div>
    	 </div>
    </div>
</div>
	<!-- 内容部分结束 -->


	<div class="control-sidebar-bg"></div>
	<script src="${ctx}/static/js/moment.min.js"></script>
	<script src="${ctx}/js/validate.js"></script>
	<script>
		//发布超级调查问卷
		function postDaily3Update() {
			var checkniDaily3Update = document
					.getElementsByName("checkniDaily3Update");
			var qnTypes = document.getElementsByName("qnTypes");
			var poststatus = document.getElementsByName("status");
			var count = 0;
			var deliveryId = "";
			var qnType = "";
			for (var i = 0; i < checkniDaily3Update.length; i++) {
				if (checkniDaily3Update[i].checked) {
					var stat = poststatus[i].value;
					if (stat != 2) {
						alert("只能发布投放中的问卷,请重新选择");
						return;
					}
				}
				if (checkniDaily3Update[i].checked) {
					qnType = qnTypes[i].value + "!" + qnType;
				}
				if (checkniDaily3Update[i].checked) {
					count++;
					deliveryId = checkniDaily3Update[i].value + "!"
							+ deliveryId;

				}

			}
			if (count == 0) {
				alert("请选择要发布的问卷");
				return;
			}
			$
					.ajax({
						type : "post",
						dataType : "json",
						url : "${ctx}/platform/postDaily3Update.do",
						data : {
							deliveryId : deliveryId,
							qnType : qnType
						},
						success : function(data) {
							if (data.success) {
								alert(data.msg);
								window.location.href = "${ctx}/platform/loadDaily3UpdateList.do";
							}
						}
					});
			/* window.location.href="${ctx}/NiSurveyQuestionnaire/updateNiSurveyQuestionnaire.do?sqnid="+sqnid; */
		}
		//撤销超级问卷
		function revokeDaily3Update() {
			var checkniDaily3Update = document
					.getElementsByName("checkniDaily3Update");
			var qnTypes = document.getElementsByName("qnTypes");
			var count = 0;
			var deliveryId = "";
			var qnType = "";
			for (var i = 0; i < checkniDaily3Update.length; i++) {
				if (checkniDaily3Update[i].checked) {
					count++;
					deliveryId = checkniDaily3Update[i].value + "!"
							+ deliveryId;
					qnType = qnTypes[i].value + "!" + qnType;
				}

			}
			if (count == 0) {
				alert("请选择要撤销的问卷");
				return;
			}
			showConfirmDialog(
					"确定要撤销吗?",
					function(check) {
						if (check) {
							$
									.ajax({
										type : "post",
										dataType : "json",
										url : "${ctx}/platform/revokeDaily3Update.do",
										data : {
											deliveryId : deliveryId,
											qnType : qnType
										},
										success : function(data) {
											if (data.success) {
												alert(data.msg);
												window.location.href = "${ctx}/platform/loadDaily3UpdateList.do";
											}
										}
									});
						}
					});

			/* window.location.href="${ctx}/NiSurveyQuestionnaire/updateNiSurveyQuestionnaire.do?sqnid="+sqnid; */
		}
		//刷新超级问卷列表
		function refreshDaily3Update() {
			window.location.href = "${ctx}/platform/loadDaily3UpdateList.do";

		}
		
		//定时器
		function timerDaily3Update(){
			
			var checkniDaily3Update_arr = document.getElementsByName("checkniDaily3Update");
			var qnTypes_arr = document.getElementsByName("qnTypes");
			var status_arr = document.getElementsByName("status");
			var pageStat_arr = document.getElementsByName("deleStat");
			
			var count = 0;
			var deliveryId = "";
			var qnType = "";
			for(var i =0 ; i<checkniDaily3Update_arr.length ; i++){
				if(checkniDaily3Update_arr[i].checked){
					count ++;
					
					deliveryId = checkniDaily3Update_arr[i].value;
					qnType = qnTypes_arr[i].value;
					
					var devStatus = status_arr[i].value;
					var pageStat = pageStat_arr[i].value;
					
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
			
			window.location.href = "${ctx}/platform/jumpDaily3UpdateTimerPage.do?deliveryId="+deliveryId+"&qnType="+qnType;
			
		}
		
		//更改置顶状态
		function changeIsTop(deliveryId, isTop, qnType, pageStatus) {
			if (pageStatus != 3) {
				alert("必须为发布状态才能调整位置！");
				return;
			}
			//当状态为置顶则取消置顶
			if (isTop == 1) {
				$
						.ajax({
							type : "post",
							dataType : "json",
							url : "${ctx}/platform/topCancelDaily3Update.do",
							data : {
								deliveryId : deliveryId,
								qnType : qnType
							},
							success : function(data) {
								if (data.success) {
									alert("取消置顶成功");
									window.location.href = "${ctx}/platform/loadDaily3UpdateList.do";
								}
							}
						});
			}

			if (isTop == 2) {
				$
						.ajax({
							type : "post",
							dataType : "json",
							url : "${ctx}/platform/topDaily3Update.do",
							data : {
								deliveryId : deliveryId,
								qnType : qnType
							},
							success : function(data) {
								if (data.success) {
									alert("置顶成功");
									window.location.href = "${ctx}/platform/loadDaily3UpdateList.do";
								}
							}
						});
			}
		}

		//跳转到添加页面
		function addDaily3Update() {
			window.location.href = "${ctx}/platform/addDaily3UpdateToList.do";
		}
		//查询
		function searchDaily3UpdateQuestionnaire() {
			var qnNameL = 22;
			var qnName = $("#qnName").val();
			if (qnName.trim().length > qnNameL) {
				alert("问卷名称不能超过" + qnNameL + "字符");
				return;
			}
			$('#pageListForm').submit();
		}

		function editDaily3UpdateList(showOrder, editStat) {
			if (editStat != 1) {
				alert("只能编辑待发布状态的问卷,请先撤销问卷");
				return;
			}
			window.location.href = "${ctx}/platform/editDaily3UpdateList.do?showOrder="
					+ showOrder;
		}
		//删除
		function deleteDaily3UpdateFromList() {
			var checkniDaily3Update = document
					.getElementsByName("checkniDaily3Update");
			var deleStats = document.getElementsByName("deleStat");
			var qnTypes = document.getElementsByName("qnTypes");
			var count = 0;
			var deliveryId = "";
			var qnType = "";
			for (var i = 0; i < checkniDaily3Update.length; i++) {
				if (checkniDaily3Update[i].checked) {
					var stat = deleStats[i].value;
					if (stat != 1) {
						alert("只能删除待发布状态的问卷,请重新选择");
						return;
					}
				}
				if (checkniDaily3Update[i].checked) {
					count++;
					deliveryId = checkniDaily3Update[i].value + "!"
							+ deliveryId;
					qnType = qnTypes[i].value + "!" + qnType;
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
							$
									.ajax({
										url : "${ctx}/platform/deleteDaily3UpdateFromList.do",
										type : "post",
										data : {
											deliveryId : deliveryId,
											qnType : qnType
										},
										dataType : "json",
										success : function(data) {
											alert(data.msg);
											if (data.success) {
												window.location.href = "${ctx}/platform/loadDaily3UpdateList.do";
											}
										}
									});
						}
					});
		}
		function moveUp(a, deliveryId, qnType, showOrder, pageStatus) {
			var index = a - 2;
			if (pageStatus != 3) {
				alert("必须为发布状态才能调整位置！");
				return;
			}
			if (a == 1) {
				alert("无法继续上移！");
				return;
			}
			var deliveryIds = document.getElementsByName("checkniDaily3Update");
			var qnTypes = document.getElementsByName("qnTypes");
			var showOrders = document.getElementsByName("showorder");
			var deleStats = document.getElementsByName("deleStat");
			var lastDeliveryId = deliveryIds[index].value;
			var lastQnType = qnTypes[index].value;
			var lastShowOrder = showOrders[index].value;
			var lastPageStat = deleStats[index].value;
			if (lastPageStat == 1) {
				alert("无法继续上移！");
				return;
			}
			$
					.ajax({
						url : "${ctx}/platform/moveUpDaily3Update.do",
						type : "post",
						data : {
							deliveryId : deliveryId,
							qnType : qnType,
							showOrder : showOrder,
							lastDeliveryId : lastDeliveryId,
							lastQnType : lastQnType,
							lastShowOrder : lastShowOrder
						},
						dataType : "json",
						success : function(data) {
							//	alert(data.msg);
							if (data.success) {
								window.location.href = "${ctx}/platform/loadDaily3UpdateList.do";
							}
						}
					});
		}
		function moveDown(a, deliveryId, qnType, showOrder, pageStatus) {
			var index = a;
			if (pageStatus != 3) {
				alert("必须为发布状态才能调整位置！");
				return;
			}
			var deliveryIds = document.getElementsByName("checkniDaily3Update");
			var qnTypes = document.getElementsByName("qnTypes");
			var showOrders = document.getElementsByName("showorder");
			if (index >= deliveryIds.length) {
				alert("无法继续下移");
				return;
			}
			var nextDeliveryId = deliveryIds[index].value;
			var nextQnType = qnTypes[index].value;
			var nextShowOrder = showOrders[index].value;
			$
					.ajax({
						url : "${ctx}/platform/moveDownDaily3Update.do",
						type : "post",
						data : {
							deliveryId : deliveryId,
							qnType : qnType,
							showOrder : showOrder,
							nextDeliveryId : nextDeliveryId,
							nextQnType : nextQnType,
							nextShowOrder : nextShowOrder
						},
						dataType : "json",
						success : function(data) {
							//	alert(data.msg);
							if (data.success) {
								window.location.href = "${ctx}/platform/loadDaily3UpdateList.do";
							}
						}
					});
		}
	</script>
</body>

</html>