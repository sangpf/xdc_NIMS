<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>用户信息管理</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<style>
.mb10 {
	margin-bottom: 10px;
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
					<form class="form-horizontal" method="post"
						action="${ctx}/platform/showUserInfoList.do" id="searchform">
						<div class="row mb10">
							<div class="col-sm-2">
								<select class="form-control select-sm" name="schoolName"
									id="schoolName">
									<option value="0">学校名称</option>
									<option value="1">用户ID</option>
									<option value="2">手机号码</option>
								</select>
							</div>
							<div class="input-group input-group-sm col-sm-3">
								<input id="schoolNameOrUserIdOrPhone"
									name="schoolNameOrUserIdOrPhone" type="text"
									class="form-control"> <span class="input-group-btn">
									<button type="button" onclick="searchSueryUser()"
										class="btn btn-info btn-flat">搜索</button>
								</span>
							</div>
						</div>

						<div class="row mb10">
							<div class="ft-12 pd-15">
								<label class="select-label">注册时间</label>
								<div class="input-group col-sm-3">
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
									<input type="text" class="form-control pull-right"
										id="reservation" name="reservation" style="height:30px;"
										placeholder="请选择日期">
								</div>
							</div>
						</div>
						<div>
							<div class="row mb10">
								<div class="ft-12 pd-15">
									<label class="select-label">年级</label> <select
										class="form-control select-sm" name="userGrade" id="userGrade"
										style="width: 10%;" disabled="disabled">
										<option value="">不限</option>
										<option value="1">大一</option>
										<option value="2">大二</option>
										<option value="3">大三</option>
										<option value="4">大四</option>
										<option value="5">研一</option>
										<option value="6">研二</option>
										<option value="7">研三</option>
										<option value="8">博一</option>
										<option value="9">博二</option>
									</select>
								</div>
							</div>

							<div class="row mb10">
								<div class="ft-12 pd-15">
									<label class="select-label">性别</label> <select
										class="form-control select-sm" name="gender" id="gender"
										style="width: 10%;">
										<option value="">不限</option>
										<option value="1">男</option>
										<option value="2">女</option>
									</select>
								</div>
							</div>

							<div class="row nb10">
								<div class="ft-12 pd-15">
									<label class="select-label">状态</label> <select
										class="form-control select-sm" name="userStats" id="userStats"
										style="width: 10%;" disabled="disabled">
										<option value="">不限</option>
										<option value="1">活跃</option>
										<option value="2">非活跃</option>
									</select>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="panel panel-default">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Data Table With Full Features</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th></th>
										<th>编号</th>
										<th>用户id</th>
										<th>用户名</th>
										<th>手机号</th>
										<th>性别</th>
										<th>学校</th>
										<th>注册渠道</th>
										<th>首次登陆时间</th>
										<th>注册时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${NiUserInfoList}" var="nuil" varStatus="n">
										<tr>
											<td><input type="checkbox" id="danxuan"
												name="checkniUserInfo" value="${nuil.userId}"></td>
											<td>${n.index+1}</td>
											<td>${nuil.userId}</td>
											<td>${nuil.userName}</td>
											<td>${nuil.phone}</td>
											<td><c:if test="${nuil.gender == 1}">男</c:if> <c:if
													test="${nuil.gender == 2}">女</c:if></td>

											<td>${nuil.schoolName}</td>
											<td><c:if test="${nuil.userChannel == 1}">APP</c:if> <c:if
													test="${nuil.userChannel == 2}">玩校</c:if> <c:if
													test="${nuil.userChannel == 3}">微信</c:if> <c:if
													test="${nuil.userChannel == 4}">QQ</c:if></td>
											<!-- 用户首次登陆时间 -->
											<td><fmt:formatDate value="${nuil.userFirstCTime}"
													pattern="yyyy-MM-dd" /></td>
											<!-- 注册时间 -->
											<td><fmt:formatDate value="${nuil.userCTime}"
													pattern="yyyy-MM-dd" /></td>
											<td><button type="button"
													onclick="showDetailUserInformation(${nuil.userId})"
													class="btn btn-default btn-sm">查看</button>
										</tr>
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


	<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->
	<script
		src="${ctx}/static/js/moment.min.js"></script>
	<script>
		$(function() {
			$("#example1").DataTable();
		});
		$('#reservation').daterangepicker();

		//问卷名称长度限制
		var sqnnameN = 10;
		//发布机构名称长度限制
		var publishernameN = 10;
		//条件查询
		function searchRequest() {
			var sqnname = $("#sqnname").val();
			var publishername = $("#publishername").val();
			var start = $("#start").val();
			var starttime2 = $("#starttime2").val();
			var staus = $("#staus").val();
			var sqnclassid = $("#sqnclassid").val();
			if (sqnname.trim().length > sqnnameN) {
				alert("问卷名称长度不能超过" + sqnnameN + "个字符");
				return;
			} else if (checkQuote(sqnname) == true) {
				alert("问卷名称不能包含特殊字符!");
				return;
			} else if (publishername.trim().length > publishernameN) {
				alert("发布机构名称长度不能超过" + publishernameN + "个字符");
				return;
			} else if (checkQuote(publishername) == true) {
				alert("发布机构名称不能包含特殊字符");
				return;
			} else if (start && starttime2 && starttime2 < start) {
				alert("截至时间应大于开始时间");
				return;
			} else {
				$("#pageListForm").submit();
			}

		}

		var maxLength = 20;
		//查询
		function searchSueryUser() {

			var schoolNameOrUserIdOrPhone = $("#schoolNameOrUserIdOrPhone")
					.val();

			if (schoolNameOrUserIdOrPhone.trim().length > maxLength) {
				alert("问卷名称或发布机构名称不能超过" + maxLength + "字符");
				return;
			} else if (checkQuote(schoolNameOrUserIdOrPhone) == true) {
				alert("不能包含特殊字符");
				return;
			}
			$('#searchform').submit();
		}

		function showDetailUserInformation(userid) {
			window.location.href = "${ctx}/platform/showDetailUserInformation.do?userId="
					+ userid;
		}
	</script>


</body>

</html>