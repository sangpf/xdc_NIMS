<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>真相</title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="box box-primary">
		<table style="padding: 100px;margin: 10px;">
			<tr>
				<td style="width: 100px;padding: 3px;"><a
					href="${ctx}/platform/loadDaily3UpdateList.do"><button
							type="button" class="btn btn-block btn-primary">页面栏目</button></a></td>
				<td style="width: 100px;padding: 3px;"><a
					href="${ctx}/platform/loadSuperList.do"><button type="button"
							class="btn btn-block btn-primary">合作问卷</button></a></td>
				<td style="width: 100px;padding: 3px;"><a
					href="${ctx}/platform/loadAssessList.do"><button type="button"
							class="btn btn-block btn-primary">专业测评</button></a></td>
				<td style="width: 100px;padding: 3px;"><a
					href="${ctx}/platform/loadReportList.do"><button type="button"
							class="btn btn-block btn-primary" style="background:green">数字报告</button></a></td>
			</tr>
		</table>
		<!-- 内容部分 -->

		<!-- Main content -->
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" method="post" action="${ctx}/platform/loadReportList.do" id="pageListForm">
						<table style="border-spacing: 10px;border-collapse: separate;">
							<tr>
								<td><select class="form-control select-sm" name="choose"
									id="choose">
										<option value="0">报告名称</option>
										<option value="1">报告Id</option>
								</select></td>
								<td><input id="reportName" name="reportName" type="text"
									class="form-control"></td>
								<td><label>页面状态</label></td>
								<td><select class="form-control select-sm"
									name="pageStatus" id="pageStatus">
										<option value="">不限</option>
										<option value="1">待发布</option>
										<option value="2">定时发布</option>
										<option value="3">已发布</option>
								</select></td>

								<td><label>报告状态</label></td>
								<td width="160"><select class="form-control select-sm"
									name="reportStatus" id="reportStatus">
										<option value="">不限</option>
										<option value="1">草稿</option>
										<option value="2">定稿</option>
										<option value="3">废弃</option>
								</select></td>
								<td><span class="input-group-btn">
										<button type="button" onclick="searchReportQuestionnaire()"
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
				</div>
				</form>
				<div class="panel-body">
					<div class="button-group">

						<button type="button" onclick="addReport()"
							class="btn btn-default btn-sm">添加</button>
						<button type="button" onclick="postReport()"
							class="btn btn-default btn-sm">发布</button>
						<button type="button" onclick="revokeReport()"
							class="btn btn-default btn-sm">撤销</button>
						<button type="button" onclick="deleteReportFromList()"
							class="btn btn-default btn-sm">删除</button>
						<button type="button" onclick="refreshReport()"
							class="btn btn-default btn-sm">刷新</button>
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
							<th>报告ID</th>
							<th>标题</th>
							<th>页面状态</th>
							<th>报告状态</th>
							<th>发布时间</th>
							<th>更新时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${niReport}" var="report" varStatus="n">
							<c:if test="${report.isTop==2}">
								<c:if test="${report.pageStatus==1}">
									<tr style="background:#87CEFA">
										<td><input type="checkbox" id="checkniReport"
											name="checkniReport" value="${report.reportId }"> <input
											type="hidden" value="${report.pageStatus}" name="deleStat"
											id="deleStat"><input type="hidden"
											value="${report.reportStatus}" name="postStatus"
											id="postStatus"><input type="hidden"
											value="${report.showOrder}" name="showorder" id="showorder"></td>
										<td><c:if test="${report.isTop==2}">${n.index+1}</c:if> <c:if
												test="${report.isTop==1}">
												<i class="fa fa-fw fa-chevron-up"></i>
											</c:if></td>
										<td>${report.reportId }</td>
										<td>${report.reportTitle }</td>
										<td><c:if test="${report.pageStatus== 2}">定时发布</c:if> <c:if
												test="${report.pageStatus == 1}">待发布</c:if> <c:if
												test="${report.pageStatus == 3}">已发布</c:if></td>
										<td><c:if test="${report.reportStatus == 1}">草稿</c:if> <c:if
												test="${report.reportStatus == 2}">定稿</c:if> <c:if
												test="${report.reportStatus == 3}">废弃</c:if></td>
										<td><fmt:formatDate value="${report.pTime }" type="both" /></td>
										<td><fmt:formatDate value="${report.uTime }" type="both" /></td>
										<td>
											<button type="button"
												onclick="editReportList(${report.showOrder },${report.pageStatus })"
												class="btn btn-default btn-sm">编辑</button>
											<button type="button" onclick="moveUp(${n.index+1},${report.reportId },${report.showOrder },${report.pageStatus})"
												class="btn btn-default btn-sm">上移</button>
											<button type="button" onclick="moveDown(${n.index+1},${report.reportId },${report.showOrder },${report.pageStatus})"
												class="btn btn-default btn-sm">下移</button>
											<button type="button" id="top"
												onclick="changeIsTop(${report.reportId},${report.isTop},${report.pageStatus})"
												class="btn btn-default btn-sm">
												<c:if test="${report.isTop==2}">置顶</c:if>
												<c:if test="${report.isTop==1}">取消</c:if>
											</button>
										</td>
									</tr>
								</c:if>
								<c:if test="${report.pageStatus==3}">
									<tr>
										<td><input type="checkbox" id="checkniReport"
											name="checkniReport" value="${report.reportId }"> <input
											type="hidden" value="${report.pageStatus}" name="deleStat"
											id="deleStat"><input type="hidden"
											value="${report.reportStatus}" name="postStatus"
											id="postStatus"><input type="hidden"
											value="${report.showOrder}" name="showorder" id="showorder"></td>
										<td><c:if test="${report.isTop==2}">${n.index+1}</c:if> <c:if
												test="${report.isTop==1}">
												<i class="fa fa-fw fa-chevron-up"></i>
											</c:if></td>
										<td>${report.reportId }</td>
										<td>${report.reportTitle }</td>
										<td><c:if test="${report.pageStatus== 2}">定时发布</c:if> <c:if
												test="${report.pageStatus == 1}">待发布</c:if> <c:if
												test="${report.pageStatus == 3}">已发布</c:if></td>
										<td><c:if test="${report.reportStatus == 1}">草稿</c:if> <c:if
												test="${report.reportStatus == 2}">定稿</c:if> <c:if
												test="${report.reportStatus == 3}">废弃</c:if></td>
										<td><fmt:formatDate value="${report.pTime }" type="both" /></td>
										<td><fmt:formatDate value="${report.uTime }" type="both" /></td>
										<td>
											<button type="button"
												onclick="editReportList(${report.showOrder },${report.pageStatus })"
												class="btn btn-default btn-sm">编辑</button>
											<button type="button" onclick="moveUp(${n.index+1},${report.reportId },${report.showOrder },${report.pageStatus})"
												class="btn btn-default btn-sm">上移</button>
											<button type="button" onclick="moveDown(${n.index+1},${report.reportId },${report.showOrder },${report.pageStatus})"
												class="btn btn-default btn-sm">下移</button>
											<button type="button" id="top"
												onclick="changeIsTop(${report.reportId},${report.isTop},${report.pageStatus})"
												class="btn btn-default btn-sm">
												<c:if test="${report.isTop==2}">置顶</c:if>
												<c:if test="${report.isTop==1}">取消</c:if>
											</button>
										</td>
									</tr>
								</c:if>
							</c:if>
							<c:if test="${report.isTop==1}">
								<tr style="background:#FFDAB9">
									<td><input type="checkbox" id="checkniReport"
										name="checkniReport" value="${report.reportId }"> <input
										type="hidden" value="${report.pageStatus}" name="deleStat"
										id="deleStat"><input type="hidden"
										value="${report.reportStatus}" name="postStatus"
										id="postStatus"><input type="hidden"
											value="${report.showOrder}" name="showorder" id="showorder"></td>
									<td><c:if test="${report.isTop==2}">${n.index+1}</c:if> <c:if
											test="${report.isTop==1}">
											<i class="fa fa-fw fa-chevron-up"></i>
										</c:if></td>
									<td>${report.reportId }</td>
									<td>${report.reportTitle }</td>
									<td><c:if test="${report.pageStatus== 2}">定时发布</c:if> <c:if
											test="${report.pageStatus == 1}">待发布</c:if> <c:if
											test="${report.pageStatus == 3}">已发布</c:if></td>
									<td><c:if test="${report.reportStatus == 1}">草稿</c:if> <c:if
											test="${report.reportStatus == 2}">定稿</c:if> <c:if
											test="${report.reportStatus == 3}">废弃</c:if></td>
									<td><fmt:formatDate value="${report.pTime }" type="both" /></td>
									<td><fmt:formatDate value="${report.uTime }" type="both" /></td>
									<td>
										<button type="button"
											onclick="editReportList(${report.showOrder },${report.pageStatus })"
											class="btn btn-default btn-sm">编辑</button>
										<button type="button" onclick="moveUp(${n.index+1},${report.reportId },${report.showOrder },${report.pageStatus})"
											class="btn btn-default btn-sm">上移</button>
										<button type="button" onclick="moveDown(${n.index+1},${report.reportId },${report.showOrder },${report.pageStatus})"
											class="btn btn-default btn-sm">下移</button>
										<button type="button" id="top"
											onclick="changeIsTop(${report.reportId},${report.isTop},${report.pageStatus})"
											class="btn btn-default btn-sm">
											<c:if test="${report.isTop==2}">置顶</c:if>
											<c:if test="${report.isTop==1}">取消</c:if>
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
	</div>
	</section>
	</div>

	<!-- 内容部分结束 -->


	<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->
	<%-- <script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script> --%>
	<script
		src="${ctx}/static/js/moment.min.js"></script>
	<script src="${ctx}/js/validate.js"></script>
	<script>
		//发布报告报告
		function postReport() {
			var checkniReport = document.getElementsByName("checkniReport");
			var postreportStatus = document.getElementsByName("postStatus");
			var count = 0;
			var reportId = "";
			for (var i = 0; i < checkniReport.length; i++) {
				if (checkniReport[i].checked) {
					var stat = postreportStatus[i].value;
					if (stat != 2) {
						alert("只能发布定稿状态的报告,请重新选择");
						return;
					}
				}
				if (checkniReport[i].checked) {
					count++;
					reportId = checkniReport[i].value + "!" + reportId;
				}

			}
			if (count == 0) {
				alert("请选择要发布的报告报告");
				return;
			}
			$
					.ajax({
						type : "post",
						dataType : "json",
						url : "${ctx}/platform/postReportList.do",
						data : {
							reportId : reportId
						},
						success : function(data) {
							if (data.success) {
								alert("发布报告成功");
								window.location.href = "${ctx}/platform/loadReportList.do";
							}
						}
					});
			/* window.location.href="${ctx}/NiSurveyQuestionnaire/updateNiSurveyQuestionnaire.do?sqnid="+sqnid; */
		}
		//撤销报告
		function revokeReport() {
			var checkniReport = document.getElementsByName("checkniReport");
			var count = 0;
			var reportId = "";
			for (var i = 0; i < checkniReport.length; i++) {
				if (checkniReport[i].checked) {
					count++;
					reportId = checkniReport[i].value + "!" + reportId;
				}

			}
			if (count == 0) {
				alert("请选择要撤销的报告");
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
										url : "${ctx}/platform/revokeReportList.do",
										data : {
											reportId : reportId
										},
										success : function(data) {
											if (data.success) {
												alert("撤销报告成功");
												window.location.href = "${ctx}/platform/loadReportList.do";
											}
										}
									});
						}
					});

		}
		//刷新报告列表
		function refreshReport() {
			window.location.href = "${ctx}/platform/loadReportList.do";

		}
		//更改置顶状态
		function changeIsTop(reportId, isTop, pageStatus) {
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
							url : "${ctx}/platform/topCancelReport.do",
							data : {
								reportId : reportId
							},
							success : function(data) {
								if (data.success) {
									alert("取消置顶成功");
									window.location.href = "${ctx}/platform/loadReportList.do";
								}
							}
						});
			}

			if (isTop == 2) {
				$
						.ajax({
							type : "post",
							dataType : "json",
							url : "${ctx}/platform/topReport.do",
							data : {
								reportId : reportId
							},
							success : function(data) {
								if (data.success) {
									alert("置顶成功");
									window.location.href = "${ctx}/platform/loadReportList.do";
								}
							}
						});
			}
		}

		//跳转到添加页面
		function addReport() {
			window.location.href = "${ctx}/platform/addReportToList.do";
		}
		//查询
		function searchReportQuestionnaire() {
			var reportNameL = 22;
			var reportName = $("#reportName").val();
			if (reportName.trim().length > reportNameL) {
				alert("报告名称不能超过" + reportNameL + "字符");
				return;
			}
			$('#pageListForm').submit();
		}

		function editReportList(showOrder, editStat) {
			if (editStat != 1) {
				alert("只能编辑待发布状态的报告,请先撤销报告");
				return;
			}
			window.location.href = "${ctx}/platform/editReportList.do?showOrder="
					+ showOrder;
		}
		//删除
		function deleteReportFromList() {
			var checkniReport = document.getElementsByName("checkniReport");
			var deleStats = document.getElementsByName("deleStat");
			var count = 0;
			var reportId = "";
			for (var i = 0; i < checkniReport.length; i++) {
				if (checkniReport[i].checked) {
					var stat = deleStats[i].value;
					if (stat != 1) {
						alert("只能删除待发布状态的报告,请重新选择");
						return;
					}
				}
				if (checkniReport[i].checked) {
					count++;
					reportId = checkniReport[i].value + "!" + reportId;
				}
			}
			if (count == 0) {
				alert("请选择要删除的报告");
				return;
			}
			showConfirmDialog(
					"确定要删除吗?",
					function(check) {
						if (check) {
							$
									.ajax({
										url : "${ctx}/platform/deleteReportFromList.do",
										type : "post",
										data : {
											reportId : reportId
										},
										dataType : "json",
										success : function(data) {
											alert(data.msg);
											if (data.success) {
												window.location.href = "${ctx}/platform/loadReportList.do";
											}
										}
									});
						}
					});
		}
		function moveUp(a, reportId, showOrder, pageStatus) {
			var index = a - 2;
			if (pageStatus != 3) {
				alert("必须为发布状态才能调整位置！");
				return;
			}
			if (a == 1) {
				alert("无法继续上移！");
				return;
			}
			var reportIds = document.getElementsByName("checkniReport");
			var showOrders = document.getElementsByName("showorder");
			var deleStats = document.getElementsByName("deleStat");
			var lastReportId = reportIds[index].value;
			var lastShowOrder = showOrders[index].value;
			var lastPageStat = deleStats[index].value;
			if (lastPageStat == 1) {
				alert("无法继续上移！");
				return;
			}
			$
					.ajax({
						url : "${ctx}/platform/moveUpReport.do",
						type : "post",
						data : {
							reportId : reportId,
							showOrder : showOrder,
							lastReportId : lastReportId,
							lastShowOrder : lastShowOrder
						},
						dataType : "json",
						success : function(data) {
							//	alert(data.msg);
							if (data.success) {
								window.location.href = "${ctx}/platform/loadReportList.do";
							}
						}
					});
		}
		function moveDown(a, reportId, showOrder, pageStatus) {
			var index = a;
			if (pageStatus != 3) {
				alert("必须为发布状态才能调整位置！");
				return;
			}
			var reportIds = document.getElementsByName("checkniReport");
			var showOrders = document.getElementsByName("showorder");
			if (index >= reportIds.length) {
				alert("无法继续下移");
				return;
			}
			var nextReportId = reportIds[index].value;
			var nextShowOrder = showOrders[index].value;
			$
					.ajax({
						url : "${ctx}/platform/moveDownReport.do",
						type : "post",
						data : {
							reportId : reportId,
							showOrder : showOrder,
							nextReportId : nextReportId,
							nextShowOrder : nextShowOrder
						},
						dataType : "json",
						success : function(data) {
							//	alert(data.msg);
							if (data.success) {
								window.location.href = "${ctx}/platform/loadReportList.do";
							}
						}
					});
		}
	</script>
</body>

</html>