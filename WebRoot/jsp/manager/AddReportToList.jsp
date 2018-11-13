<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>列表管理-真相</title>
</head>
<body class="hold-transition skin-blue ">
	<!-- general form elements -->
	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">真相—添加</h3>
		</div>
		<!-- form start -->
		<table style="border-spacing: 10px;border-collapse: separate;">
			<tr>
				<td><label>&nbsp;&nbsp;添加报告&nbsp;: &nbsp;</label></td>
				<td><input id="reportId" type="text" value="${ reportId}"
					onfocus="if (value =='请输入报告Id'){value =''}"
					onblur="if (value ==''){value='请输入报告Id'}" />

					<button type="button" onclick="searchReportTitleById()"
						class="btn btn-primary">查询</button></td>
			</tr>
			<tr>
				<td><label>&nbsp;&nbsp;报告题目&nbsp;&nbsp;</label></td>
				<td><input id="reportTitle" value="${reportTitle}" type="hidden">${reportTitle}</input></td>
			</tr>
		</table>
		<div class="box-body"></div>

		<div class="box-footer">

			<button type="button" onclick="addReportDelivery(${reportStatus})"
				class="btn btn-primary">保存</button>
			<button type="button" onclick="cancleEdit()" class="btn btn-primary">取消</button>
		</div>
	</div>
	<!-- /.box -->
	<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script>
		//查询问卷题目
		function searchReportTitleById() {
			var reportId = document.getElementById("reportId").value;
			if (reportId == "请输入投放Id" || reportId == ""
					|| reportId == null) {
				alert("请输入投放Id");
			} else {
				window.location.href = "${ctx}/platform/searchReportTitleById.do?reportId="
						+ reportId;

			}
		}
		//取消编辑轮播图
		function cancleEdit() {
			window.location.href = "${ctx}/platform/loadReportList.do";
		}
		//保存添加一条投放至列表
		function addReportDelivery(reportStatus) {
			if (reportStatus != 2) {
				alert("请选择定稿状态的报告");
			} else {
				var reportId = document.getElementById("reportId").value;
				$
						.ajax({
							url : "${ctx}/platform/addReport.do",
							data : {
								reportId : reportId
							},
							type : "post",
							dataType : "json",
							success : function(data) {
								alert(data.msg);
								if (data.msg == "添加成功") {
									window.location.href = "${ctx}/platform/loadReportList.do";
								} else
									window.location.href = "${ctx}/platform/addReportToList.do";
							}
						});
			}
		}
	</script>
</body>
</html>
