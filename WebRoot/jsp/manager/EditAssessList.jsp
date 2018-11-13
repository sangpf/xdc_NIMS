<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>列表管理-测一发</title>
</head>
<body class="hold-transition skin-blue ">
	<!-- general form elements -->
	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">测一发</h3>
		</div>
		<!-- form start -->
		<table style="border-spacing: 10px;border-collapse: separate;">
			<tr>
				<td><label>&nbsp;&nbsp;替换投放&nbsp;: &nbsp;</label></td>
				<td><input id="deliveryId" type="text" value="${ deliveryId}"
					onfocus="if (value =='请输入投放Id'){value =''}"
					onblur="if (value ==''){value='请输入投放Id'}" />

					<button type="button"
						onclick="searchAqnTitleByIdForEdit(${showOrder })"
						class="btn btn-primary">查询</button></td>
			</tr>
			<tr>
				<td><label>&nbsp;&nbsp;问卷题目&nbsp;&nbsp;</label></td>
				<td><input id="aqnTitle" value="${aqnTitle}" type="hidden">${aqnTitle}</input></td>
			</tr>
		</table>
		<div class="box-body"></div>

		<div class="box-footer">

			<button type="button" onclick="replaceAssessDelivery(${showOrder })"
				class="btn btn-primary">保存</button>
			<button type="button" onclick="cancleEdit()" class="btn btn-primary">取消</button>
		</div>
	</div>
	<!-- /.box -->
	<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script>
		//读取cookie
        function getCookie(name)
	    {
	        var regExp = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	        var arr = document.cookie.match(regExp);
	        if (arr == null) {
	            return null;
	        }
	        return unescape(arr[2]);
	    }
	    var selectedIndex = getCookie("assessListCategory");

	    //根据cookie确定当前测评类别
		var assessListCategory = "funnyAssessQnList";
		switch(selectedIndex){
			case '0': assessListCategory="funnyAssessQnList";break;
			case '1': assessListCategory="professialAssessQnList";break;
		}
		//查询问卷题目
		function searchAqnTitleByIdForEdit(showOrder) {
			var deliveryId = document.getElementById("deliveryId").value;
			if (deliveryId == "请输入投放Id" || deliveryId == ""
					|| deliveryId == null) {
				alert("请输入投放Id");
			} else {
				window.location.href = "${ctx}/platform/searchAqnTitleByIdForEdit.do?deliveryId="
						+ deliveryId + "&showOrder=" + showOrder;

			}
		}
		//取消编辑
		function cancleEdit() {
			window.location.href = "${ctx}/platform/loadAssessList.do?assessListCategory="+assessListCategory;
		}
		//在相同位置替换一条投放
		function replaceAssessDelivery(showOrder) {
			var deliveryId = document.getElementById("deliveryId").value;
			$
					.ajax({
						url : "${ctx}/platform/replaceAssessDelivery.do",
						data : {
							deliveryId : deliveryId,
							assessListCategory : assessListCategory,
							showOrder : showOrder
						},
						type : "post",
						dataType : "json",
						success : function(data) {
							alert(data.msg);
							if (data.msg == "替换成功") {
								window.location.href = "${ctx}/platform/loadAssessList.do?assessListCategory="+assessListCategory;
							} else
								window.location.href = "${ctx}/platform/editAssessList.do?showOrder="+showOrder+"&assessListCategory="+assessListCategory;
						}
					});
		}
	</script>
</body>
</html>
