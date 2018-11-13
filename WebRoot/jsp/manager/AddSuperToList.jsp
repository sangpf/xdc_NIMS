<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>超级调查——添加</title>
</head>
<body class="hold-transition skin-blue ">
	<!-- general form elements -->
	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">超级调查—添加</h3>
		</div>
		<!-- form start -->
		<table style="border-spacing: 10px;border-collapse: separate;">
			<tr>
				<td><label>&nbsp;&nbsp;添加投放&nbsp;: &nbsp;</label></td>
				<td><input id="deliveryId" type="text" value="${ deliveryId}"
					onfocus="if (value =='请输入投放Id'){value =''}"
					onblur="if (value ==''){value='请输入投放Id'}" /></td>
			</tr>
			<tr>
				<td><label>&nbsp;&nbsp;问卷类型&nbsp;: &nbsp;</label></td>
				<td><select name="qnType" id="qnType">
						<option value="">请选择</option>
						<option value="1">调查</option>
						<option value="3">投票</option>
				</select>
					<button type="button" onclick="searchQnTitleById()"
						class="btn btn-primary">查询</button></td>
			</tr>
			<tr>
				<td><label>&nbsp;&nbsp;问卷题目&nbsp;&nbsp;</label></td>
				<td><input id="qnTitle" value="${qnTitle}" type="hidden">${qnTitle}</input></td>
			</tr>
		</table>
		<div class="box-body"></div>

		<div class="box-footer">

			<button type="button" onclick="addSuperDelivery(${qnType},${deliveryStatus})"
				class="btn btn-primary">保存</button>
			<button type="button" onclick="cancleEdit()" class="btn btn-primary">取消</button>
		</div>
	</div>
	<!-- /.box -->
	<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script>
		//查询问卷题目
		function searchQnTitleById() {
			var deliveryId = document.getElementById("deliveryId").value;
			var qnType = document.getElementById("qnType").value;
			if (deliveryId == "请输入投放Id" || deliveryId == ""
					|| deliveryId == null || qnType == "") {
				alert("未输入投放Id或未选择问卷类型");
			} else {
				window.location.href = "${ctx}/platform/searchQnTitleById.do?deliveryId="
						+ deliveryId + "&qnType=" + qnType;

			}
		}
		//取消编辑轮播图
		function cancleEdit() {
			window.location.href = "${ctx}/platform/loadSuperList.do";
		}
		//保存添加一条投放至列表
		function addSuperDelivery(qnType,deliveryStatus) {
			if (deliveryStatus != 2) {
				alert("请选择投放中的问卷");
			} else {
				var deliveryId = document.getElementById("deliveryId").value;
				$
						.ajax({
							url : "${ctx}/platform/addSuperDelivery.do",
							data : {
								deliveryId : deliveryId,
								qnType : qnType
							},
							type : "post",
							dataType : "json",
							success : function(data) {
								alert(data.msg);
								if (data.msg == "添加成功") {
									window.location.href = "${ctx}/platform/loadSuperList.do";
								} else
									window.location.href = "${ctx}/platform/addSuperToList.do";
							}
						});
			}
		}
	</script>
</body>
</html>
