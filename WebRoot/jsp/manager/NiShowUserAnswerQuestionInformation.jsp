<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>

<head>
<title>用户答题信息</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/BS/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${ctx}/static/css/font-awesome.min.css">
<!-- Ionicons -->
<!-- DataTables -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/BS/plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/BS/dist/css/AdminLTE.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/BS/dist/css/skins/_all-skins.min.css">
<!-- iCheck -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/BS/plugins/iCheck/flat/blue.css">
<!-- Morris chart -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/BS/plugins/morris/morris.css">
<!-- jvectormap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/BS/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<!-- Date Picker -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/BS/plugins/datepicker/datepicker3.css">
<!-- Daterange picker -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/BS/plugins/daterangepicker/daterangepicker-bs3.css">
<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/BS/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
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

<body>
	<div class="box box-primary">
		<table style="padding: 100px;margin: 10px;">
			<tr>
				<td style="width: 100px;padding: 3px;"><a href="${ctx}/platform/showDetailUserInformation.do?userId=${param.userId}"><button
							type="button" class="btn btn-block btn-primary">基础信息</button></a></td>
				<td style="width: 100px;padding: 3px;"><a
					href="${ctx}/platform/showUserAnswerQuestionInformation.do?userId=${param.userId}"><button
							type="button" class="btn btn-block btn-primary">答题信息</button></a></td>
			</tr>
			<tr>
				<td><label style="margin: 20px">问卷信息</label></td>
				<td><button type="button" id="download"
						class="btn btn-block btn-primary">下载答题记录</button></td>
			</tr>
		</table>


		<hr style="border: 1px dashed black;margin: 10px;" />
		<table style="border-spacing: 10px;border-collapse: separate;">
			<tr>
				<td><label>答题次数：</label></td>
				<td><input id="userId" value="" type="text"
					style="height: 30px;" class="form-control" name="userId"
					readonly="readonly"></td>
				<td><label>最后一次答题时间：</label></td>
				<td><input id="userCTime" value="" type="datetime"
					style="height: 30px;" class="form-control" name="userCTime"
					readonly="readonly"></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><label>调查：</label></td>
				<td><input id="userId" value="" type="text"
					style="height: 30px;" class="form-control" name="userId"
					readonly="readonly"></td>
				<td><label>测评：</label></td>
				<td><input id="userCTime" value="" type="datetime"
					style="height: 30px;" class="form-control" name="userCTime"
					readonly="readonly"></td>
				<td><label>投票：</label></td>
				<td><input type="text" style="height: 30px;"
					class="form-control" readonly="readonly"></td>
			</tr>
		</table>
		<hr style="border: 1px solid gray;margin: 10px;" />
		<label style="margin-left: 30px">奖励信息</label>
		<hr style="border: 1px dashed;margin: 10px" />
		<table style="border-spacing: 10px;border-collapse: separate;">

			<tr>
				<td><label>奖励次数：</label></td>
				<td><input id="userId" value="" type="text"
					style="height: 30px;" class="form-control" name="userId"
					readonly="readonly"></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><label>定奖：</label></td>
				<td><input id="userId" value="" type="text"
					style="height: 30px;" class="form-control" name="userId"
					readonly="readonly"></td>
				<td><label>抽奖：</label></td>
				<td><input id="userCTime" value="" type="datetime"
					style="height: 30px;" class="form-control" name="userCTime"
					readonly="readonly"></td>
				<td></td>
				<td></td>
			</tr>
			
			<tr>
				<td><label>实物奖品：</label></td>
				<td><input id="userId" value="" type="text"
					style="height: 30px;" class="form-control" name="userId"
					readonly="readonly"></td>
				<td><label>积分：</label></td>
				<td><input id="userCTime" value="" type="datetime"
					style="height: 30px;" class="form-control" name="userCTime"
					readonly="readonly"></td>
				<td><label>实体卡卷：</label></td>
				<td><input type="text" style="height: 30px;"
					class="form-control" readonly="readonly"></td>
			</tr>
			
			<tr>
				<td><label>电子卡卷：</label></td>
				<td><input id="userId" value="" type="text"
					style="height: 30px;" class="form-control" name="userId"
					readonly="readonly"></td>
				<td><label>虚拟物品：</label></td>
				<td><input id="userCTime" value="" type="datetime"
					style="height: 30px;" class="form-control" name="userCTime"
					readonly="readonly"></td>
				<td><label>其它：</label></td>
				<td><input type="text" style="height: 30px;"
					class="form-control" readonly="readonly"></td>
			</tr>
			
			<tr>
				<td><label>累计积分（分）：</label></td>
				<td><input id="userId" value="" type="text"
					style="height: 30px;" class="form-control" name="userId"
					readonly="readonly"></td>
				<td><label>累计金额（元）：</label></td>
				<td><input id="userCTime" value="" type="datetime"
					style="height: 30px;" class="form-control" name="userCTime"
					readonly="readonly"></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
</body>
</html>
