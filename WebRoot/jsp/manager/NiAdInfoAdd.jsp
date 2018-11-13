<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>添加/编辑广告</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
</head>
<body class="hold-transition skin-blue sidebar-mini">

	<!-- Main content -->
	<div class="box box-primary">

		<!-- form start -->
		<form id="addpage" method="POST"
			action="${ctx}/platform/addNiAdInfo.do" enctype="multipart/form-data">
			<table style="border-spacing: 10px;border-collapse: separate;">
				<tr>
					<td><label>&nbsp;&nbsp;广告名称&nbsp;: &nbsp;</label></td>
					<td>
						<input id="adTitle" name="adTitle" value="${ad.adTitle}" type="text" style="width: 200px;height: 30px;" class="form-control">
						<input id="adId" name="adId" value="${ad.adId}" type="hidden" class="form-control"></td>
				</tr>
				<tr>
					<td><label>&nbsp;&nbsp;广告来源&nbsp;:&nbsp; </label></td>
					<td>
						<select class="form-control" style="width: 100px" id="adType" name="adType">
							<option  value="1">商业</option>
							<option <c:if test="${ad.adType == 2}">selected="selected"</c:if> value="2">普通</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><label>&nbsp;&nbsp;链接地址&nbsp;: &nbsp;</label></td>
					<td><input type="text" id="adLink"
						name="adLink" value="${ad.adLink }" style="width: 700px;height: 30px;" class="form-control"></td>
				</tr>
				<tr>
					<td><label>&nbsp;&nbsp;选择图片&nbsp;: &nbsp;</label></td>
					<td colspan="2"><input type="file" name="uploadImgmes" id="uploadImgmes" onchange="PreviewImage(this)" value="${url}"></td>
						
				</tr>
				
                <tr>
         			<td><label></label></td>
         			<td>
						<img height="150px" width="230px" id="imgurl" src="${ad.adImg }" class="img-rounded"></td>
					<td></td>
         		</tr>
				
				<tr>
					<td><label>&nbsp;&nbsp;替换广告 ID&nbsp;:&nbsp; </label></td>
					<td>
					<input id="replaceAdId" name="replaceAdId" value="${ad.replaceAdId}" type="text" style="width: 100px;" class="form-control">
					</td>
				</tr>
				
			</table>

			<div class="box-footer">
				<button type="button" onclick="saveAdInfo(${isEdit});" class="btn btn-primary">保存</button>
				<button type="button" onclick="cancelAdd();" class="btn btn-primary">取消</button>
			</div>

		</form>

	</div>

	<!-- /.content -->

	<script>
		//图片路径
		var imgurl = "";
		//图片的存储路径
		var jdbcUrl = "";
		//保存 添加或编辑
		function saveAdInfo(isEdit) {
			//验证广告名称是否输入
			var adTitle = $("#adTitle").val();
			if (adTitle == "") {
				alert("请输入广告名称");
				return;
			}
			
			//验证是否选择问卷类型
			var adType = document.getElementById("adType").value;
			if (adType == "") {
				alert("请选择广告来源");
				return;
			}

			//验证是否输入广告作者
			var adLink = document.getElementById("adLink").value;
			if (adLink == "") {
				alert("输入广告连接地址");
				return;
			}

			var formData = new FormData($("#addpage")[0]);
			if (isEdit == 1) {//编辑
				$.ajax({
					url : '${ctx}/platform/didEditAdInfo.do',
					type : 'POST',
					data : formData,
					async : false,
					cache : false,
					contentType : false,
					processData : false,
					success : function(data) {
						showMessageDialog(data.msg);
					},
					error : function(data) {
						showMessageDialog("网络异常");
					}
				});
			} else {//添加
				$.ajax({
					url : '${ctx}/platform/addNiAdInfo.do',
					type : 'POST',
					data : formData,
					async : false,
					cache : false,
					contentType : false,
					processData : false,
					success : function(data) {
						showMessageDialog(data.msg);
					},
					error : function(data) {
						showMessageDialog("网络异常");
					}
				});
			}
		}

		//取消添加
		function cancelAdd() {
			window.location.href = "${ctx}/platform/loadAdInfoList.do";
		}

		//预览本地图片

		function PreviewImage(imgFile) {
			var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
			if (!pattern.test(imgFile.value)) {
				alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
				imgFile.focus();
			} else {
				var path;
				if (document.all)//IE 
				{
					imgFile.select();
					path = document.selection.createRange().text;
					document.getElementById("imgurl").innerHTML = "";
					document.getElementById("imgurl").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\""
							+ path + "\")";//使用滤镜效果 
				} else//FF 
				{
					path = URL.createObjectURL(imgFile.files[0]);
					$("#imgurl").attr("src", path);
				}
			}
		}
	</script>
</body>

</html>


