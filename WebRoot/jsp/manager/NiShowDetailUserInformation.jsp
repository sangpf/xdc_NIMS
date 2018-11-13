<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<title>用户基本信息</title>
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

<body class="hold-transition skin-blue sidebar-mini">
	<div class="box box-primary">
		<table style="padding: 100px;margin: 10px;">
			<tr>
				<td style="width: 100px;padding: 3px;"><button type="button"
						class="btn btn-block btn-primary"
						onclick="showUserDetailInformation(${niUserInformation.userId})">基础信息</button></td>
				<td style="width: 100px;padding: 3px;"><button type="button"
						class="btn btn-block btn-primary"
						onclick="showUserAnswerQuestionInformation(${niUserInformation.userId})">答题信息</button></td>
			</tr>
		</table>
		<form class="form-horizontal" id="userInformationDetailForm">

			<table style="border-spacing: 10px;border-collapse: separate;"
				id="stableTable">
				<tr>
					<td>
						<button type="button" class="btn btn-primary"
							onclick="editTable()" disabled="disabled">编辑</button>
					</td>
					<td></td>
					<td><label>玩校用户名：</label></td>
					<td><input type="text" style="height: 30px;"
						class="form-control" readonly="readonly"></td>
					<td><label>玩校openid：</label></td>
					<td><input id="wanxOpenId"
						value="${niUserInformation.wanxOpenId}" type="text"
						style="height: 30px;" class="form-control" name="wanxOpenId"></td>
					<td><label>绑定一卡通：</label></td>
					<td><input id="bindCard" value="${niUserInformation.bindCard}"
						type="text" style="height: 30px;" class="form-control"
						name="bindCard"></td>
				</tr>
				<tr>
					<td><label>UID：</label></td>
					<td><input id="userId" value="${niUserInformation.userId}"
						type="text" style="height: 30px;" class="form-control"
						name="userId"></td>
					<td><label>渠道：</label></td>
					<td><select id="userChannel"
						style="width: 170px;height: 30px;" name="userChannel">
							<option value="">--</option>
							<option value="1">APP</option>
							<option value="2">玩校</option>
							<option value="3">微信</option>
							<option value="4">QQ</option>
					</select></td>
					<td><label>注册时间：</label></td>
					<td><input id="userCTime"
						value="${niUserInformation.userCTime}" type="datetime"
						style="height: 30px;" class="form-control" name="userCTime"></td>
					<td><label>IMEI：</label></td>
					<td><input type="text" style="height: 30px;"
						class="form-control" value="${niUserInformation.bindPhoneIMEI}"></td>
				</tr>
				<tr>
					<td><label>用户名/<br />登录名
					</label></td>
					<td><input id="userName" value="${niUserInformation.userName}"
						type="text" style="height: 30px;" class="form-control"
						name="userName"></td>
					<td><label>昵称：</label></td>
					<td><input id="wanxNickname"
						value="${niUserInformation.wanxNickname}" type="text"
						style="height: 30px;" class="form-control" name="wanxNickname"></td>
					<td><label>手机号码：</label></td>
					<td><input id="phone" value="${niUserInformation.phone}"
						type="tel" style="height: 30px;" class="form-control" name="phone"></td>
					<td><label>邮箱地址：</label></td>
					<td><input id="email" value="${niUserInformation.email}"
						type="email" style="height: 30px;" class="form-control"
						name="email"></td>
				</tr>
				<tr>
					<td><label>姓名：</label></td>
					<td><input id="userName" value="${niUserInformation.userName}"
						type="text" style="height: 30px;" class="form-control"
						name="userName"></td>
					<td><label>性别：</label></td>
					<td><select id="sex" style="width: 170px;height: 30px;"
						name="gender">
							<option value="">--</option>
							<option value="1">男</option>
							<option value="2">女</option>
					</select></td>
					<td><label>生日：</label></td>
					<td><input id="birthday" value="${niUserInformation.birthday}"
						type="date" style="height: 30px;" class="form-control"
						name="birthday"></td>
					<td><label>民族：</label></td>
					<td><input id="race" value="${niUserInformation.race}"
						type="text" style="height: 30px;" class="form-control" name="race"></td>
				</tr>
				<tr>
					<td><label>政治面貌：</label></td>
					<td><select id="politicsStatus"
						style="width: 170px;height: 30px;" name="politicsStatus">
							<option value="">--</option>
							<option value="1">群众</option>
							<option value="2">共青团员</option>
							<option value="3">中共预备党员</option>
							<option value="4">中共党员</option>
							<option value="5">无党派</option>
							<option value="6">其他党派</option>
					</select></td>
					<td><label>教育程度：</label></td>
					<td><select id="degree" style="width: 170px;height: 30px;"
						name="degree">
							<option value="">--</option>
							<option value="1">小学</option>
							<option value="2">初中</option>
							<option value="3">中专</option>
							<option value="4">高中</option>
							<option value="5">专科</option>
							<option value="6">本科</option>
							<option value="7">硕士研究生</option>
							<option value="8">博士研究生</option>
							<option value="9">自学考试</option>
							<option value="10">成人教育</option>
							<option value="11">电大开放教育</option>
							<option value="12">网络教育</option>
					</select></td>
					<td><label>职业：</label></td>
					<td><select id="jobPosition"
						style="width: 170px;height: 30px;" name="jobPosition"
						disabled="disabled">
							<option value="">--</option>
							<option value="1">学生</option>
							<option value="2">非学生</option>
					</select></td>
					<td><label>常驻地：</label></td>
					<td><input id="address" value="${niUserInformation.address}"
						type="text" style="height: 30px;" class="form-control"
						name="address"></td>
				</tr>
				<tr>
					<td><label>婚姻状况：</label></td>
					<td><select id="marriage" style="width: 170px;height: 30px;"
						name="marriage">
							<option value="">--</option>
							<option value="1">未婚</option>
							<option value="2">已婚</option>
					</select></td>
					<td><label>有无子女：</label></td>
					<td><select id="hasChildren"
						style="width: 170px;height: 30px;" name="hasChildren">
							<option value="">--</option>
							<option value="0">无</option>
							<option value="1">有</option>
					</select></td>
					<td><label>国籍：</label></td>
					<td><input id="nationality"
						value="${niUserInformation.nationality}" type="text"
						style="height: 30px;" class="form-control" name="nationality"></td>
					<td><label>注册渠道：</label></td>
					<td><input id="userChannel"
						value="${niUserInformation.userChannel}" type="text"
						style="height: 30px;" class="form-control"></td>
				</tr>
			</table>
			<hr style="height: 10px;" />
			<table style="border-spacing: 10px;border-collapse: separate;">
				<tr>
					<td><label>地区：</label></td>
					<td><input id="regionName"
						value="${niUserInformation.regionName}" type="text"
						style="height: 30px;" class="form-control" name="regionName"></td>
					<td><label>学校名称：</label></td>
					<td><input id="schoolName"
						value="${niUserInformation.schoolName}" type="text"
						style="height: 30px;" class="form-control" name="schoolName"></td>
					<td><label>重点：</label></td>
					<td><input type="text" style="height: 30px;"
						class="form-control" readonly="readonly" " id="is985Or211"
						name="is985Or211"></td>
				</tr>
				<tr>
					<td><label>学院：</label></td>
					<td><input id="college" value="${niUserInformation.college}"
						type="text" style="height: 30px;" class="form-control"
						name="college"></td>
					<td><label>系：</label></td>
					<td><input id="department"
						value="${niUserInformation.department}" type="text"
						style="height: 30px;" class="form-control" name="department"></td>
					<td><label>专业：</label></td>
					<td><input id="major" value="${niUserInformation.major}"
						type="text" style="height: 30px;" class="form-control"
						name="major"></td>
				</tr>
				<tr>
					<td><label>班级：</label></td>
					<td><input id="classId" value="${niUserInformation.classId}"
						type="text" style="height: 30px;" class="form-control"
						name="classId"></td>
					<td><label>学号：</label></td>
					<td><input id="userSn" value="${niUserInformation.userSn}"
						type="text" style="height: 30px;" class="form-control"
						name="userSn"></td>
					<td><label>职务：</label></td>
					<td><input id="jobTitle" value="${niUserInformation.jobTitle}"
						type="text" style="height: 30px;" class="form-control"
						name="jobTitle"></td>
				</tr>
				<tr>
					<td><label>入学时间：</label></td>
					<td><input id="enrolDate"
						value="${niUserInformation.enrolDate}" type="date"
						style="height: 30px;" class="form-control" name="enrolDate"></td>
				</tr>
			</table>
			<div class="box-footer">
				<button type="button" onclick="saveDetailUserInformation()"
					class="btn btn-primary" disabled="disabled">保存</button>
				<button type="button" class="btn btn-primary" disabled="disabled">取消</button>
			</div>
		</form>
	</div>
	<!-- ./wrapper -->
	<!-- jQuery 2.2.0 -->
	<script
		src="${pageContext.request.contextPath}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<script
		src="${ctx}/static/js/moment.min.js"></script>
	<script src="${ctx}/js/validate.js"></script>
	<script type="text/javascript">
		$('input').attr("readonly", "readonly");
		$('select').attr("disabled", "disabled");
	</script>
	<script type="text/javascript">
		if (${niUserInformation.bindCard == 0}) {
			document.getElementById("bindCard").value = '否';
		} else if (${niUserInformation.bindCard == 1}) {
			document.getElementById("bindCard").value = '是';
		}

		if (${niUserInformation.userChannel != null}) {
			$("#userChannel").val(${niUserInformation.userChannel});
		}
		if (${niUserInformation.gender != null}) {
			$("#sex").val(${niUserInformation.gender});
		}
		if (${niUserInformation.politicsStatus != null}) {
			$("#politicsStatus").val(${niUserInformation.politicsStatus});
		}
		if (${niUserInformation.degree != null}) {
			$("#degree").val(${niUserInformation.degree});
		}
		if (${niUserInformation.jobPosition != null}) {
			$("#jobPosition").val(${niUserInformation.jobPosition});
		}
		if (${niUserInformation.marriage != null}) {
			$("#marriage").val(${niUserInformation.marriage});
		}
		if(${niUserInformation.hasChildren != null}) {
			$("#hasChildren").val(${niUserInformation.hasChildren});
		}
		var temp = '';
		if(${niUserInformation.is211 == 1}) {
			temp += '211';
		}
		if(${niUserInformation.is985 == 1}) {
			temp += ' 985';
		}
		$("#is985Or211").val(temp);
		
	</script>
	<script type="text/javascript">
		function showUserDetailInformation(userId) {
			window.location.href = "${ctx}/platform/showDetailUserInformation.do?userId="
					+ userId;
		}
		function showUserAnswerQuestionInformation(userId) {
			window.location.href = "${ctx}/platform/showUserAnswerQuestionInformation.do?userId="
					+ userId;
		}
	</script>
</body>

</html>