<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>轮播广告管理</title>
</head>

<body class="hold-transition skin-blue sidebar-mini">
	<div class="box box-primary">

		<!-- 内容部分 -->
		<table style="padding: 100px;margin: 10px;">
			<tr>
				<td style="width: 100px;padding: 3px;">
					<a href="${ctx}/platform/loadCarouselList.do"><button type="button" class="btn btn-block btn-primary" style="background:green">轮播图</button></a></td>
				
				<td style="width: 100px;padding: 3px;">
					<a href="${ctx}/platform/loadBannerList.do"><button type="button" class="btn btn-block btn-primary">Banner</button></a></td>
			</tr>
		</table>
		<div class="box-header with-border">
			<h3 class="box-title">广告位管理—轮播图</h3>
		</div>

		<!-- Main content -->
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="button-group">
						<a href="">
							<button type="button" onclick="postCarousel()"
								class="btn btn-default btn-sm">发布</button>
						</a> <a href="">
							<button type="button" onclick="revokeCarousel()"
								class="btn btn-default btn-sm">撤销</button>
						</a> <a href="">
							<button type="button" onclick="refreshCarousel()"
								class="btn btn-default btn-sm">刷新</button>
						</a>
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
									<th>图片预览</th>
									<th>广告id</th>
									<th>名称</th>
									<th>状态</th>
									<th>渠道</th>
									<!-- <th>发布时间</th> -->
									<th>更新时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${carouselList}" var="carousel" varStatus="n">
									<tr>
										<td><input type="checkbox" id="checkniCarousel"
											name="checkniCarousel" value="${carousel.adId }"><input
										type="hidden" value="${carousel.carouselId }" name="carouselId"
										id="carouselId"><input
										type="hidden" value="${carousel.showOrder }" name="showOrder"
										id="showOrder"></td>
										<td>${n.index+1}</td>
										<td><img height="40px" width="60px" id="picprevice" src="${contextPath}/..${carousel.adImg }"
											class="img-rounded"></td>
										<td>${carousel.adId }</td>
										<td>${carousel.adTitle }</td>
										<td><c:if test="${carousel.status== 0}">待发布</c:if> <c:if
												test="${carousel.status == 1}">发布</c:if></td>
										
										<td>
											<c:if test="${carousel.channelId == 1}">完校</c:if>
											<c:if test="${carousel.channelId == 2}">微信</c:if>
										</td>
										<%-- <td><fmt:formatDate value="${carousel.pTime }"
												type="both" /></td> --%>
												
										<td><fmt:formatDate value="${carousel.uTime }" type="both" /></td>
										<td>
											<button type="button" onclick="editCarousel(${carousel.carouselId })"
												class="btn btn-default btn-sm">编辑</button>
												
											<button type="button" onclick="moveUp(${n.index+1},${carousel.carouselId },${carousel.showOrder })"
												class="btn btn-default btn-sm">上移</button>
												
											<button type="button" onclick="moveDown(${n.index+1},${carousel.carouselId },${carousel.showOrder })"
												class="btn btn-default btn-sm">下移</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- /.box-body -->
				</div>
			</div>
	</div>

	<!-- 内容部分结束 -->
	<!-- 编辑轮播图模态框 -->
	<div class="modal fade" id="editCarousel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">编辑轮播图</h4>
				</div>
				<!-- /.modal-modal-header -->
				<div class="modal-body">
					<form id="editpage" method="POST"
						action="${ctx}/platform/uploadAdPicture.do"
						enctype="multipart/form-data">
						<table style="border-spacing: 10px;border-collapse: separate;">
							<tr>
								<td><label>&nbsp;&nbsp;广告名称&nbsp;: &nbsp;</label></td>
								<td>
									<select class="form-control select-sm" name="selectAdName" id="adInfoList" style="width:130px">
										
									</select>
								</td>
							</tr>
							<tr>
								<td><label>&nbsp;&nbsp;对应轮播id&nbsp;&nbsp;</label></td>
								<td id="carouselidTag"></td>
							</tr>
							<tr>
								<td><label>&nbsp;&nbsp;选择图片&nbsp;: &nbsp;</label></td>
								<td><input type="file" name="uploadImgmes"
									id="uploadImgmes" value="${url}"></td>
								<td><input type="button" onclick="uploadpic();"
									value="点击上传"></td>
							</tr>
							<tr>
								<td><img height="80px" width="120px" id="imgurl" src=""
									class="img-rounded"></td>
							</tr>
						</table>
					</form>
				</div>
				<!-- /.modal-body -->
				<div class="modal-footer">
					<button type="button" onclick="replaceCarousel()"
						class="btn btn-primary">保存</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						aria-hidden="true">取消</button>
				</div>
				<!-- /.modal-footer -->
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<div class="control-sidebar-bg"></div>
	
	<script src="${ctx}/static/js/moment.min.js"></script>
	<script>
		//图片路径
		var imgurl = "";
		//图片的存储路径
		var jdbcUrl = "";
		//发布轮播广告
		function postCarousel() {
			var checkniCarousel = document.getElementsByName("checkniCarousel");
			var count = 0;
			var adId = "";
			for (var i = 0; i < checkniCarousel.length; i++) {
				if (checkniCarousel[i].checked) {
					count++;
					adId = checkniCarousel[i].value + "!" + adId;
				}

			}
			if (count == 0) {
				alert("请选择要发布的广告");
				return;
			}
			$.ajax({
						type : "post",
						dataType : "json",
						url : "${ctx}/platform/postCarousel.do",
						data : {
							adId : adId
						},
						success : function(data) {
							if (data.success) {
								alert(data.msg);
								window.location.href = "${ctx}/platform/loadCarouselList.do";
							}
						}
					});
			/* window.location.href="${ctx}/NiSurveyQuestionnaire/updateNiSurveyQuestionnaire.do?sqnid="+sqnid; */
		}
		//撤销轮播广告
		function revokeCarousel() {
			var checkniCarousel = document.getElementsByName("checkniCarousel");
			var count = 0;
			var adId = "";
			for (var i = 0; i < checkniCarousel.length; i++) {
				if (checkniCarousel[i].checked) {
					count++;
					adId = checkniCarousel[i].value + "!" + adId;
				}

			}
			if (count == 0) {
				alert("请选择要撤销的广告");
				return;
			}
			$.ajax({
						type : "post",
						dataType : "json",
						url : "${ctx}/platform/revokeCarousel.do",
						data : {
							adId : adId
						},
						success : function(data) {
							if (data.success) {
								alert("撤销广告成功");
								window.location.href = "${ctx}/platform/loadCarouselList.do";
							}
						}
					});
			/* window.location.href="${ctx}/NiSurveyQuestionnaire/updateNiSurveyQuestionnaire.do?sqnid="+sqnid; */
		}
		//刷新轮播广告
		function refreshCarousel() {
			window.location.href = "${ctx}/platform/loadCarouselList.do";

		}
		//跳转到Banner管理页面
		function loadBannerList() {
			window.location.href = "${ctx}/platform/loadBannerList.do";
		}

		// 打开编辑轮播图页面
		function editCarousel(carouselId) {
			
			window.location.href = "${ctx}/platform/jumpEditCarorse.do?carouselId="+carouselId;
			
			// 弹出模态框编辑轮播图, 后改为打开编辑页面
/* 				$.ajax({
						url : "${ctx}/platform/editCarousel.do",
						type : "get",
						success : function(data) {
							$('#editCarousel').modal('show');

							jQuery("#adInfoList").find("option").remove();
							$.each(data, function(i, item) {
								jQuery("#adInfoList").append(
										"<option value="+ item.adTitle+">"
												+ item.adTitle + "</option>");
							});
							document.getElementById("carouselidTag").innerHTML = carouselId;
							document.getElementById("uploadImgmes").value = "";
							$("#imgurl").attr("src", "");
						},
						error : function(data) {
							showMessageDialog("请求失败");
						}
					}); */

		}

		//替换轮播图
		function replaceCarousel(carouselId) {
			var adTitle = document.getElementById("adInfoList").value;
			var carouselId = document.getElementById("carouselidTag").innerHTML;
				$.ajax({
						url : "${ctx}/platform/replaceCarousel.do",
						data : {
							adTitle : adTitle,
							carouselId : carouselId,
							adImg : jdbcUrl
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
		//上传图片
		function uploadpic() {
			var formData = new FormData($("#editpage")[0]);
			$.ajax({
				url : '${ctx}/platform/uploadAdPicture.do',
				type : 'POST',
				data : formData,
				async : false,
				cache : false,
				contentType : false,
				processData : false,
				success : function(data) {
					imgurl = data.url;
					jdbcUrl = data.jdbcUrl;
					$("#imgurl").attr("src", imgurl);
				},
				error : function(data) {
					alert(data.msg);
				}
			});
		}
		
		//上下移
		function moveUp(a, carouselId, showOrder) {
			var index = a - 2;
			if (a == 1) {
				alert("无法继续上移！");
				return;
			}
			var carouselIds = document.getElementsByName("carouselId");
			var showOrders = document.getElementsByName("showOrder");
			var lastCarouselId = carouselIds[index].value;
			var lastShowOrder = showOrders[index].value;
			$.ajax({
						url : "${ctx}/platform/moveUpCarousel.do",
						type : "post",
						data : {
							carouselId : carouselId,
							showOrder : showOrder,
							lastCarouselId : lastCarouselId,
							lastShowOrder : lastShowOrder
						},
						dataType : "json",
						success : function(data) {
							//	alert(data.msg);
							if (data.success) {
								window.location.href = "${ctx}/platform/loadCarouselList.do";
							}
						}
					});
		}
		//下移
		function moveDown(a, carouselId, showOrder) {
			var index = a;
			var carouselIds = document.getElementsByName("carouselId");
			var showOrders = document.getElementsByName("showOrder");
			if (index >= carouselIds.length) {
				alert("无法继续下移");
				return;
			}
			var nextCarouselId = carouselIds[index].value;
			var nextShowOrder = showOrders[index].value;
			$.ajax({
						url : "${ctx}/platform/moveDownCarousel.do",
						type : "post",
						data : {
							carouselId : carouselId,
							showOrder : showOrder,
							nextCarouselId : nextCarouselId,
							nextShowOrder : nextShowOrder
						},
						dataType : "json",
						success : function(data) {
							//	alert(data.msg);
							if (data.success) {
								window.location.href = "${ctx}/platform/loadCarouselList.do";
							}
						}
					});
		}
	</script>
</body>

</html>