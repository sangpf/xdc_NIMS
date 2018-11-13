<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<title>添加/编辑报告</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>AdminLTE 2 | Dashboard</title>
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

	<!-- Main content -->
	<div class="box box-primary">

		<!-- form start -->
		<form id="addpage" method="POST"
			action="${ctx}/platform/addNiReport.do" enctype="multipart/form-data">
			<table style="border-spacing: 10px;border-collapse: separate;">
				<tr>
					<td><label>&nbsp;&nbsp;报告名称&nbsp;: &nbsp;</label></td>
					<td colspan="2"><input id="reportTitle" name="reportTitle"
						value="${report.reportTitle}" type="text" maxlength="28"
						style="width: 200px;height: 30px;" class="form-control"
						placeholder="请输入报告名称"><input id="reportId" name="reportId"
						value="${report.reportId}" type="hidden" class="form-control"></td>
					<td><label>&nbsp;&nbsp;问卷ID&nbsp;: &nbsp;</label></td>
					<td colspan="1"><input type="text" id="qnId" name="qnId"
						value="${report.qnId}" style="width: 200px;height: 30px;"
						placeholder="请输入所关联的问卷ID" class="form-control"></td>
				</tr>
				<tr>
					<td><label>&nbsp;&nbsp;问卷类型&nbsp;:&nbsp; </label></td>
					<td colspan="2"><select id="qnType" name="qnType">
							<option value="">请选择</option>
							<option value="1">调查</option>
							<option value="2">测评</option>
							<option value="3">投票</option>
					</select><input id="qnTypeVal" name="qnTypeVal"
						value="${report.qnType}" type="hidden" class="form-control"></td>
					<td><label>&nbsp;&nbsp;所属类别&nbsp;: &nbsp;</label></td>
					<td colspan="1"><select id="reportClassId"
						name="reportClassId">
							<option value="">请选择分类</option>
							<option value="1">严肃</option>
							<option value="2">商业</option>
							<option value="3">社会</option>
							<option value="4">娱乐</option>
							<option value="5">学术</option>
							<option value="6">生活</option>
							<option value="7">学习</option>
							<option value="8">情感</option>
							<option value="9">科学</option>
							<option value="10">其他</option>
					</select><input id="reportClassIdVal" name="reportClassIdVal"
						value="${report.reportClassId}" type="hidden" class="form-control"></td>
				</tr>
				<tr>
					<td><label>&nbsp;&nbsp;发布机构&nbsp;: &nbsp;</label></td>
					<td colspan="2"><input type="text" id="publisher"
						name="publisher" value="${report.publisher }"
						style="width: 200px;height: 30px;" class="form-control"></td>
					<td><label>&nbsp;&nbsp;报告作者&nbsp;: &nbsp;</label></td>
					<td colspan="2"><input type="text" id="author" name="author"
						value="${report.author }" style="width: 200px;height: 30px;"
						class="form-control"></td>
				</tr>
				<tr>
					<td><label>&nbsp;&nbsp;选择报告列表图&nbsp;: &nbsp;</label></td>
					<td colspan="2"><input type="file" name="uploadImgmes"
						id="uploadImgmes" onchange="PreviewImage(this)" value="${url}"></td>
					<td colspan="3"><img height="90px" width="140px" id="imgurl"
						src="" class="img-rounded"></td>
					<td></td>
				</tr>
				<tr>
					<td style="width:140px"><label>&nbsp;&nbsp;选择报告Zip文件&nbsp;:
							&nbsp;</label></td>
					<td colspan="2"><input type="file" name="uploadZip"
						id="uploadZip" value="${url}"></td>
				</tr>
				<tr>
					<td><label>&nbsp;&nbsp;问卷回收数量&nbsp;: &nbsp;</label></td>
					<td colspan="5"><input id="qnCollectedNum"
						name="qnCollectedNum" value="${report.qnCollectedNum}" type="text"
						maxlength="28" style="width: 200px;height: 30px;"
						class="form-control"></td>
				</tr>
				<tr>
					<td><label>&nbsp;&nbsp;阅读人数基准值&nbsp;: &nbsp;</label></td>
					<td colspan="5"><input id="baseViewNum" name="baseViewNum"
						value="${report.baseViewNum }" type="text" maxlength="28"
						style="width: 200px;height: 30px;" class="form-control"></td>
				</tr>

				<tr>
					<td><label>&nbsp;&nbsp;外标签&nbsp;: &nbsp;</label></td>
					<td><input id="tag1Str" name="tag1Str" type="text"
						maxlength="28" style="width: 120px;height: 30px;"
						class="form-control" placeholder="外标签1 " disabled='disabled'></td>
					<td><input id="tag2Str" name="tag2Str" type="text"
						maxlength="28" style="width: 120px;height: 30px;"
						class="form-control" placeholder="外标签2 " disabled='disabled'></td>
					<td><input id="tag3Str" name="tag3Str" type="text"
						maxlength="28" style="width: 120px;height: 30px;"
						class="form-control" placeholder="外标签3 " disabled='disabled'></td>
					<td><input id="tag4Str" name="tag4Str" type="text"
						maxlength="28" style="width: 120px;height: 30px;"
						class="form-control" placeholder="外标签4 " disabled='disabled'></td>
				</tr>
				<tr>
					<td><label>&nbsp;&nbsp;内标签&nbsp;: &nbsp;</label></td>
					<td><input id="tag1Id" name="tag1Id" type="text"
						maxlength="28" style="width: 120px;height: 30px;"
						class="form-control" placeholder="内标签1 " disabled='disabled'></td>
					<td><input id="tag2Id" name="tag2Id" type="text"
						maxlength="28" style="width: 120px;height: 30px;"
						class="form-control" placeholder="内标签2 " disabled='disabled'></td>
					<td><input id="tag3Id" name="tag3Id" type="text"
						maxlength="28" style="width: 120px;height: 30px;"
						class="form-control" placeholder="内标签3 " disabled='disabled'></td>
					<td><input id="tag4Id" name="tag4Id" type="text"
						maxlength="28" style="width: 120px;height: 30px;"
						class="form-control" placeholder="内标签4 " disabled='disabled'></td>
				</tr>

				<tr>
					<td><label>&nbsp;&nbsp;简介&nbsp;: &nbsp;</label></td>
					<td colspan="5"><input id="summary" name="summary" value="${report.summary }"
							class="form-control" style="width: 700px;" rows="3"
							placeholder="Enter ..."></td>
				</tr>

			</table>

			<div class="box-footer">
				<button type="button" onclick="save(${isEdit});"
					class="btn btn-primary">保存</button>
				<button type="button" onclick="cancelAdd();" class="btn btn-primary">取消</button>
			</div>

		</form>

	</div>

	<!-- /.content -->


	<!-- jQuery 2.2.0 -->
	<script
		src="${pageContext.request.contextPath}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>

	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<!-- <script>$.widget.bridge('uibutton', $.ui.button);</script> -->
	<script
		src="${pageContext.request.contextPath}/BS/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/BS/plugins/daterangepicker/daterangepicker.js"></script>
	<script
		src="${pageContext.request.contextPath}/BS/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/BS/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script
		src="${ctx}/static/js/moment.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/BS/plugins/daterangepicker/daterangepicker.js"></script>
	<script
		src="${pageContext.request.contextPath}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script
		src="${pageContext.request.contextPath}/BS/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/BS/plugins/fastclick/fastclick.js"></script>
	<script src="${pageContext.request.contextPath}/BS/dist/js/app.min.js"></script>
	<script src="${pageContext.request.contextPath}/BS/dist/js/demo.js"></script>
	<script src="${pageContext.request.contextPath}/js/validate.js"></script>
	<script>
	//将问卷类型自动选择option
	qnTypeVal = document.getElementById("qnTypeVal").value;
    qnType = document.getElementById("qnType");
    for(i=0;i<qnType.length;i++){
        if(qnType[i].value==qnTypeVal)
            qnType[i].selected = true;
    }
    //所属类别自动选择option
    reportClassIdVal = document.getElementById("reportClassIdVal").value;
    reportClassId = document.getElementById("reportClassId");
    for(i=0;i<reportClassId.length;i++){
        if(reportClassId[i].value==reportClassIdVal)
            reportClassId[i].selected = true;
    }
		//图片路径
		var imgurl = "";
		//图片的存储路径
		var jdbcUrl = "";
		//保存 添加或编辑
		function save(isEdit) {
			//验证输入qnId类型为数字
			var qnId = $("#qnId").val();
			if (!checkNumber(qnId) || qnId == "") {
				alert("请输入数字型问卷Id");
				return;
			}
			//验证报告名称是否输入
			var reportTitle = $("#reportTitle").val();
			if (reportTitle == "") {
				alert("请输入报告名称");
				return;
			}
			
			if(isEdit!=1){
				//如果为添加报告，则验证是否选择列表图；编辑则不验证
				var uploadImgmes = document.getElementById("uploadImgmes").value;
				if (uploadImgmes.length == 0) {
					alert("请选择上传图片");
					return;
				}
			}
			//验证是否选择问卷类型
			var qnType = document.getElementById("qnType").value;
			if (qnType == "") {
				alert("请选择问卷类型");
				return;
			}
			//验证是否选择所属类别
			var reportClassId = document.getElementById("reportClassId").value;
			if (reportClassId == "") {
				alert("请选择所属类别");
				return;
			}
			if(isEdit!=1){
				//验证是否选择报告Zip包，编辑不验证
				var uploadZip = document.getElementById("uploadZip").value;
				if (uploadZip.length == 0) {
					alert("请选择上传报告Zip包");
					return;
				}
			}
			//验证是否输入发布机构
			var publisher = document.getElementById("publisher").value;
			if (publisher == "") {
				alert("输入发布机构");
				return;
			}

			//验证是否输入报告作者
			var author = document.getElementById("author").value;
			if (author == "") {
				alert("输入报告作者");
				return;
			}
			//验证是否输入问卷回收数量
			var qnCollectedNum = document.getElementById("qnCollectedNum").value;
			if (qnCollectedNum == "" || !checkNumber(qnId)) {
				alert("问卷回收数量请输入数字");
				return;
			}
			//验证阅读人数基准值
			var baseViewNum = document.getElementById("baseViewNum").value;
			if (baseViewNum == "" || !checkNumber(baseViewNum)) {
				alert("阅读人数基准值请输入数字");
				return;
			}

			var formData = new FormData($("#addpage")[0]);
			if (isEdit == 1) {
				$
						.ajax({
							url : '${ctx}/platform/didEditReport.do',
							type : 'POST',
							data : formData,
							async : false,
							cache : false,
							contentType : false,
							processData : false,
							success : function(data) {
								if (data.success) {
									alert(data.msg);
									window.location.href = "${ctx}/platform/NiReportList.do";
								}
							},
							error : function(data) {
								alert(data.msg);
							}
						})
			} else {
				$
						.ajax({
							url : '${ctx}/platform/addNiReport.do',
							type : 'POST',
							data : formData,
							async : false,
							cache : false,
							contentType : false,
							processData : false,
							success : function(data) {
								if (data.success) {
									alert(data.msg);
									window.location.href = "${ctx}/platform/NiReportList.do";
								}
							},
							error : function(data) {
								alert(data.msg);
							}
						});
			}
		}

		//取消添加
		function cancelAdd() {
			window.location.href = "${ctx}/platform/NiReportList.do";
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


