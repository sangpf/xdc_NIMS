<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>编辑轮播广告</title>
</head>
<body class="hold-transition skin-blue ">
	<!-- general form elements -->
	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">轮播广告</h3>
		</div>
		<!-- form start -->
		<form>
			<table style="border-spacing: 10px;border-collapse: separate;">
				<tr>
					<td><label>&nbsp;&nbsp;广告名称&nbsp;: &nbsp;</label></td>
					<td><select class="form-control select-sm" name="selectAdName"
						id="adInfoList">
							<c:forEach items="${adInfoList}" var="niAdInfo" varStatus="n">
								<option value="${ niAdInfo.adTitle}">${ niAdInfo.adTitle}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><label>&nbsp;&nbsp;对应轮播id&nbsp;&nbsp;</label><input id="carouselId" value="${editCarousel}" type="hidden">${editCarousel}</input></td>
				</tr>
				<tr>
					<td><label>&nbsp;&nbsp;选择图片&nbsp;: &nbsp;</label></td>
				</tr>
			</table>
			<div class="box-body"></div>

			<div class="box-footer">
				<button type="button" onclick="replaceCarousel()"
					class="btn btn-primary">保存</button>
				<button type="button" onclick="cancleEdit()" class="btn btn-primary">取消</button>
			</div>

		</form>
	</div>
	<!-- /.box -->
	<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script>
		//取消编辑轮播图
		function cancleEdit() {
			window.location.href = "${ctx}/platform/loadCarouselList.do";
		}
		//替换轮播图
		function replaceCarousel() {
			var adTitle = document.getElementById("adInfoList").value;
			var carouselId = document.getElementById("carouselId").value;
			$
					.ajax({
						url : "${ctx}/platform/replaceCarousel.do",
						data : {
							adTitle : adTitle,
							carouselId : carouselId
						},
						type : "post",
						dataType : "json",
						success : function(data) {
							if (data.success) {
								alert(data.msg);
								window.location.href = "${ctx}/platform/loadCarouselList.do";
							}
						}
					});
		}
	</script>
</body>
</html>
