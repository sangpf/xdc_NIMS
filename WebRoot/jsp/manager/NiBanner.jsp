<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Banner广告管理</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<style>
.mb10 {
	margin-bottom: 1px;
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

.panel {
	margin-bottom: 10px;
}
</style>
</head>

<body class="hold-transition skin-blue sidebar-mini">
	<div class="box box-primary">

		<!-- 内容部分 -->
		<table style="padding: 100px;margin: 10px;">
			<tr>
				<td style="width: 100px;padding: 3px;"><a
					href="${ctx}/platform/loadCarouselList.do"><button
							type="button" class="btn btn-block btn-primary">轮播图</button></a></td>
				<td style="width: 100px;padding: 3px;"><a
					href="${ctx}/platform/loadBannerList.do"><button type="button"
							class="btn btn-block btn-primary" style="background:green">Banner</button></a></td>
			</tr>
		</table>
		<div class="box-header with-border">
			<h3 class="box-title">广告位管理—Banner</h3>
		</div>

		<!-- Main content -->
		<section class="content">

			<div class="panel panel-default">
				<div class="panel-body">
					<div class="button-group">
						<a href="">
							<button type="button" onclick="postBanner()"
								class="btn btn-default btn-sm">发布</button>
						</a> <a href="">
							<button type="button" onclick="revokeBanner()"
								class="btn btn-default btn-sm">撤销</button>
						</a> <a href="">
							<button type="button" onclick="refreshBanner()"
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
									<th>广告位名称</th>
									<th>图片预览</th>
									<th>广告id</th>
									<th>名称</th>
									<th>状态</th>
									<th>发布时间</th>
									<th>更新时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${niBanner}" var="banner" varStatus="n">
									<tr>
										<td><input type="checkbox" id="checkniBanner"
											name="checkniBanner" value="${banner.adPos }"></td>
										<td>${n.index+1}</td>
										<td>${banner.adPosDes }</td>
										<td><img height="40px" width="60px" id="picprevice" src="${contextPath}/..${banner.adImg }"
											class="img-rounded"></td>
										<td>${banner.adId }</td>
										<td>${banner.adTitle }</td>
										<td><c:if test="${banner.status== 0}">待发布</c:if> <c:if
												test="${banner.status == 1}">发布</c:if></td>
										<td><fmt:formatDate value="${banner.pTime }" type="both" /></td>
										<td><fmt:formatDate value="${banner.uTime }" type="both" /></td>
										<td>
											<button type="button"
												onclick="editBanner('${banner.adPosDes }')"
												class="btn btn-default btn-sm">编辑</button>
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
	</section>
	<!-- /.content -->

	</div>

	<!-- 内容部分结束 -->
	<!-- 编辑轮播图模态框 -->
	<div class="modal fade" id="editBanner" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">编辑Banner广告位</h4>
				</div>
				<!-- /.modal-modal-header -->
				<div class="modal-body">
					<form id="editpage" method="POST"
						action="${ctx}/platform/uploadAdPicture.do"
						enctype="multipart/form-data">
						<table style="border-spacing: 10px;border-collapse: separate;">
							<tr>
								<td><label>&nbsp;&nbsp;广告名称&nbsp;: &nbsp;</label></td>
								<td><select class="form-control select-sm"
									name="selectAdName" id="adInfoList" style="width:130px">
								</select></td>
							</tr>
							<tr>
								<td><label>&nbsp;&nbsp;广告位名称&nbsp;&nbsp;</label></td>
								<td id="banneridTag"></td>
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
					<button type="button" onclick="replaceBanner()"
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
	<!-- /.modal -->

	<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->
	<%-- <script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script> --%>
	<script
		src="${ctx}/static/js/moment.min.js"></script>
	<script>
			//图片路径
		var imgurl = "";
		//图片的存储路径
		var jdbcUrl = "";
		//发布轮播广告
		function postBanner() {
			var checkniBanner = document.getElementsByName("checkniBanner");
			var count = 0;
			var adId = "";
			for (var i = 0; i < checkniBanner.length; i++) {
				if (checkniBanner[i].checked) {
					count++;
					adId = checkniBanner[i].value + "!" + adId;
				}

			}
			if (count == 0) {
				alert("请选择要发布的广告");
				return;
			}
			$
					.ajax({
						type : "post",
						dataType : "json",
						url : "${ctx}/platform/postBanner.do?adId=" + adId,
						success : function(data) {
							if (data.success) {
								alert("发布广告成功");
								top.location.href = "${ctx}/platform/loadBannerList.do";
							}
						}
					});
			/* window.location.href="${ctx}/NiSurveyQuestionnaire/updateNiSurveyQuestionnaire.do?sqnid="+sqnid; */
		}
		//撤销轮播广告
		function revokeBanner() {
			var checkniBanner = document.getElementsByName("checkniBanner");
			var count = 0;
			var adId = "";
			for (var i = 0; i < checkniBanner.length; i++) {
				if (checkniBanner[i].checked) {
					count++;
					adId = checkniBanner[i].value + "!" + adId;
				}

			}
			if (count == 0) {
				alert("请选择要撤销的广告");
				return;
			}
			$
					.ajax({
						type : "post",
						dataType : "json",
						url : "${ctx}/platform/revokeBanner.do?adId=" + adId,
						success : function(data) {
							if (data.success) {
								alert("撤销广告成功");
								top.location.href = "${ctx}/platform/loadBannerList.do";
							}
						}
					});
			/* window.location.href="${ctx}/NiSurveyQuestionnaire/updateNiSurveyQuestionnaire.do?sqnid="+sqnid; */
		}
		//刷新轮播广告
		function refreshBanner() {
			window.location.href = "${ctx}/platform//loadBannerList.do"

		}
		//跳转到轮播广告管理
		function LoadCarousel() {
			window.location.href = "${ctx}/platform//loadCarouselList.do"
		}
		//编辑banner广告
		function editBanner(adPosDes) {
			$
					.ajax({
						url : "${ctx}/platform/editBanner.do",
						type : "get",
						success : function(data) {
							$('#editBanner').modal('show');

							jQuery("#adInfoList").find("option").remove();
							$.each(data, function(i, item) {
								jQuery("#adInfoList").append(
										"<option value="+ item.adTitle+">"
												+ item.adTitle + "</option>");
							});
							document.getElementById("banneridTag").innerHTML = adPosDes;
							document.getElementById("uploadImgmes").value = "";
							$("#imgurl").attr("src", "");
						},
						error : function(data) {
							showMessageDialog("请求失败");
						}
					});

			//	window.location.href = "${ctx}/platform/editBanner.do?bannerId="
			//			+ bannerId;
		}
		
		//替换banner
		function replaceBanner() {
			var adTitle = document.getElementById("adInfoList").value;
			var adPosDes = document.getElementById("banneridTag").innerHTML;
			$
					.ajax({
						url : "${ctx}/platform/replaceBanner.do",
						data : {
							adTitle : adTitle,
							adPosDes : adPosDes,
							adImg : jdbcUrl
						},
						type : "post",
						dataType : "json",
						success : function(data) {
							if (data.success) {
								alert(data.msg);
								window.location.href = "${ctx}/platform/loadBannerList.do";
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
	</script>
</body>

</html>