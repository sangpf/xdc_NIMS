<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>广告管理</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
</head>

<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- 内容部分 -->


		<!-- Main content -->
		<section class="content">
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" method="post"
						action="${ctx}/platform/loadAdInfoList.do" id="pageListForm">
						<div class="row mb10">
							<div class="col-sm-2">
								<select class="form-control select-sm" id="searchOption"
									name="searchOption">
									<option value="">请选择</option>
									<option value="1">广告Id</option>
									<option value="2">广告名称</option>
								</select>
							</div>
							<div class="input-group input-group-sm col-sm-3">
								<input id="searchContent" name="searchContent" type="text"
									class="form-control"> <span class="input-group-btn">
									<button type="button" onclick="searchAdInfo()"
										class="btn btn-info btn-flat">Go!</button>
								</span>
							</div>
						</div>
						<div class="row mb10">
							<div class="col-sm-2">
								<label class="select-label">来源</label> <select name="adType"
									id="adType" class="form-control select-sm" style="width:75%;">
									<option value="">请选择</option>
									<option value="1">外部</option>
									<option value="2">内部</option>
								</select>
							</div>
							<div class="col-sm-2">
								<label class="select-label">状态</label> <select name="adStatus"
									id="adStatus" class="form-control select-sm" style="width:75%;">
									<option value="">不限</option>
									<option value="1">有效</option>
									<option value="2">无效</option>
								</select>
							</div>
						</div>
				</div>
				</form>
			</div>
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="button-group">
						<a
							href="${pageContext.request.contextPath }/platform/NiAdInfoAdd.do">
							<button type="button" class="btn btn-default btn-sm">添加</button>
						</a>

						<button type="button" onclick="changeAdInfoStatus(1)"
							class="btn btn-default btn-sm">启用</button>

						<button type="button" onclick="changeAdInfoStatus(2)"
							class="btn btn-default btn-sm">撤销</button>

						<button type="button" onclick="deleteAdInfo()"
							class="btn btn-default btn-sm">删除</button>
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
									<th>来源</th>
									<th>图片预览</th>
									<th>状态</th>
									<th>创建时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${niAdInfo}" var="adItem" varStatus="n">
									<tr>
										<td><input type="checkbox" id="checkNiAdInfo"
											name="checkNiAdInfo" value="${adItem.adId }"> <input
											type="hidden" id="adItemStatus" name="adItemStatus"
											value="${adItem.adStatus}"></td>
										<td>${n.index+1}</td>
										<td>${adItem.adId }</td>
										<td>${adItem.adTitle }</td>
										<td><c:if test="${adItem.adType == 1}">外部</c:if> <c:if
												test="${adItem.adType == 2}">内部</c:if></td>
										<td><img height="40px" width="60px" id="picprevice"
											src="${contextPath}/..${adItem.adImg }" class="img-rounded"></td>
										<td><c:if test="${adItem.adStatus == 1}">有效</c:if> <c:if
												test="${adItem.adStatus == 2}">无效</c:if></td>
										<td><fmt:formatDate value="${adItem.adCTime}"
												pattern="yyyy-MM-dd" /></td>

										<td>
											<button type="button"
												onclick="toEditPage(${adItem.adId},${adItem.adStatus})"
												class="btn btn-default btn-sm">编辑</button>
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
	<!-- ./wrapper -->
	<script
		src="${ctx}/static/js/moment.min.js"></script>
	<script src="${ctx}/js/validate.js"></script>
	<script>
		//分页查询
		var adNameOrpublisherL = 20;
		function searchAdInfo() {
			$('#pageListForm').submit();
		}

		//更改状态
		function changeAdInfoStatus(adStatus) {
			var checkNiAdInfo = document.getElementsByName("checkNiAdInfo");
			var count = 0;
			var adId = "";
			for (var i = 0; i < checkNiAdInfo.length; i++) {

				if (checkNiAdInfo[i].checked) {
					count++;
					adId = checkNiAdInfo[i].value + "!" + adId;
				}

			}
			if (count == 0) {
				showMessageDialog("请选择广告");
				return;
			}

			$
					.ajax({
						type : "post",
						dataType : "json",
						url : "${ctx}/platform/changeAdInfoStatus.do",
						data : {
							adId : adId,
							adStatus : adStatus
						},
						success : function(data) {
							if (data.success) {
								showMessageDialog(
										data.msg,
										function() {
											if (data.success) {
												window.location.href = "${ctx}/platform/loadAdInfoList.do";
											}
										})

							}
						}
					});
		}
		
		//批量删除广告
		function deleteAdInfo() {
			var checkNiAdInfo = document.getElementsByName("checkNiAdInfo");
			var adStatus = document.getElementsByName("adItemStatus");
			var count = 0;
			var adId = "";
			for (var i = 0; i < checkNiAdInfo.length; i++) {
				if (checkNiAdInfo[i].checked) {
					if (adStatus[i].value != 2) {
						showMessageDialog("只能删除无效的广告！");
						return;
					}
					count++;
					adId = checkNiAdInfo[i].value + "!" + adId;
				}
			}
			if (count == 0) {
				alert("请选择要删除的广告");
				return;
			}
			showConfirmDialog(
					"确定要删除广告吗?",
					function(check) {
						if (check) {
							$
									.ajax({
										url : "${ctx}/platform/deleteAdInfo.do?adId="
												+ adId,
										type : "post",
										dataType : "json",
										success : function(data) {
											if (data.success) {
												showMessageDialog(
														data.msg,
														function() {
															if (data.success) {
																window.location.href = "${ctx}/platform/loadAdInfoList.do";
															}
														})

											}
										}
									});
						}
					});

		}
		//跳转到编辑页面
		function toEditPage(adId, adStatus) {
			if (adStatus != 1) {
				showMessageDialog("只能编辑有效的广告", function() {
					window.location.href = "${ctx}/platform/loadAdInfoList.do";
				});
				return;
			}
			window.location.href = "${ctx}/platform/editNiAdInfo.do?adId="+adId;
		}
	</script>
</body>

</html>