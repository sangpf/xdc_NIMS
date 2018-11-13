<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>真相—编辑页</title>
</head>
<body class="hold-transition skin-blue ">
	<!-- general form elements -->
	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">真相—编辑</h3>
		</div>
		<!-- form start -->
		<table style="border-spacing: 10px;border-collapse: separate;">
			<tr>
				<td><label>&nbsp;&nbsp;报告Id&nbsp;: &nbsp;</label></td>
				<td><input id="reportId" type="text" value="${ reportId}"
					onfocus="if (value =='请输入投放Id'){value =''}"
					onblur="if (value ==''){value='请输入投放Id'}" />

					<button type="button"
						onclick="searchReportTitleByIdForEdit(${showOrder })"
						class="btn btn-primary">查询</button></td>
			</tr>
			<tr>
				<td><label>&nbsp;&nbsp;报告题目&nbsp;&nbsp;</label></td>
				<td><input id="reportTitle" value="${reportTitle}" type="hidden">${reportTitle}</input></td>
			</tr>
		</table>
		<div class="box-body"></div>

		<div class="box-footer">

			<button type="button" onclick="replaceReport(${showOrder })"
				class="btn btn-primary">保存</button>
			<button type="button" onclick="cancleEdit()" class="btn btn-primary">取消</button>
		</div>
	</div>
	<!-- /.box -->
	<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script>
		//查询问卷题目
		function searchReportTitleByIdForEdit(showOrder) {
			var reportId = document.getElementById("reportId").value;
			if (reportId == "请输入投放Id" || reportId == ""
					|| reportId == null) {
				alert("请输入投放Id");
			} else {
				window.location.href = "${ctx}/platform/searchReportTitleByIdForEdit.do?reportId="
						+ reportId + "&showOrder=" + showOrder;

			}
		}
		//取消编辑轮播图
		function cancleEdit() {
			window.location.href = "${ctx}/platform/loadReportList.do";
		}
		//在相同位置替换一条投放
		function replaceReport(showOrder) {
			var reportId = document.getElementById("reportId").value;
			$
					.ajax({
						url : "${ctx}/platform/replaceReport.do",
						data : {
							reportId : reportId,
							showOrder : showOrder
						},
						type : "post",
						dataType : "json",
						success : function(data) {
							alert(data.msg);
							if (data.msg == "替换成功") {
								window.location.href = "${ctx}/platform/loadReportList.do";
							} else
								window.location.href = "${ctx}/platform/editReportList.do?showOrder="+showOrder;
						}
					});
		}
	</script>
</body>
</html>
