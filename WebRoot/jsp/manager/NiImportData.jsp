<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<title>从玩校数据库中导入信息</title>
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
.edit {
	width: 50px;
	height: 100px;
}

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

.div-height {
	height: 200px
}

.btn-style {
	margin: 100px 100px 100px 100px;
}
</style>
<head>

<title>数据导入</title>
</head>
</head>


<body class="hold-transition skin-blue sidebar-mini">
	<!-- jQuery 2.2.0 -->
	<script
		src="${pageContext.request.contextPath}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<script
		src="${ctx}/static/js/moment.min.js"></script>
	<script src="${ctx}/js/validate.js"></script>
	<script type="text/javascript" language="javaScript">
		var currentValue = 0;
		var currentValueDic = 0;
		var totalNum = 0;
		var currentNum = 0;
		var xmlHttp = false; //全局变量，用于记录XMLHttpRequest对象
		function createXMLHttpRequest() { //创建XMLHttpRequest对象的方法
			if (window.ActiveXObject) { //Inetrnet Explorer时，创建XMLHttpRequest对象的方法
				try {
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					try {
						xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
						//旧版本的Inetrnet Explorer，创建XMLHttpRequest对象
					} catch (e) {
						window.alert("创建XMLHttpRequest对象错误" + e);
					}
				}
			} else if (window.XMLHttpRequest) { //mozilla时，创建XMLHttpRequest对象的方法
				xmlHttp = new XMLHttpRequest();
			}
			if (!(xmlHttp)) { //未成功创建XMLHttpRequest对象
				window.alert("创建XMLHttpRequest对象异常！");
			}
		}

		//启动进度条的方法
		function importData() {
			createXMLHttpRequest(); //创建XMLHttpRequest对象
			currentValue = 0;
			xmlHttp.onreadystatechange = callBack;
			//指定onreadystatechange属性值，用于指定状态正常时的处理函数
			xmlHttp.open("GET",
					"${ctx}/platform/processController.do?flag=start", true);
			xmlHttp.send(null);
		}

		//开始进行进度条显示的处理函数
		function callBack() {
			//window.alert("callBack()");
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) { //status状态正常时
					setTimeout("aginRun()", 1000);
					//每隔1000毫秒（1秒）执行一次“aginRun()”函数
				}
			}
		}

		/*  //清除用于显示进度条的span的内容
		 function clearBar() {
		 for (var i = 0; i < 10; i++) { //根据span元素的id，分别清楚其显示内容
		 var sp = document.getElementById("sp" + i);
		 sp.innerHTML = clear; //首先清空span元素的内容
		 sp.style.backgroundColor = "white"; //设置span元素的背景色
		 }
		 } */
		//设置用于显示进度条的span元素的显示内容
		function aginRun() {
			createXMLHttpRequest(); //创建XMLHttpRequest对象
			xmlHttp.onreadystatechange = aginCallBack;
			//指定状态正常时的处理函数为“aginCallBack”
			document.getElementById("start").disabled = true; //设置按钮不可用
			xmlHttp.open("GET",
					"${ctx}/platform/processController.do?flag=run", true);
			//window.alert(" " + number);
			xmlHttp.send(null);
		}

		//进度条执行时的函数
		function aginCallBack() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					currentValue = xmlHttp.responseXML
							.getElementsByTagName("percent")[0].firstChild.data;
					totalNum = xmlHttp.responseXML
							.getElementsByTagName("total")[0].firstChild.data;
					currentNum = xmlHttp.responseXML
							.getElementsByTagName("current")[0].firstChild.data;
					document.getElementById("total").innerHTML = totalNum;
					document.getElementById("current").innerHTML = currentNum;
					if (currentValue < 100) {
						currentValueDic = parseInt(currentValue, 10);
						document.getElementById("percent").innerHTML = currentValue
								+ "%";
						setTimeout("aginRun()", 1000);
					} else {
						currentValueDic = 100;
						document.getElementById("percent").innerHTML = "数据导入完成！";
					}
					$(function() {
						$("#progressbar").progressbar({
							value : currentValueDic
						});
					});
				}
			}

		}
	</script>
	<!--    <script>

    </script> -->
	<div class="box box-primary">
		<div style="height: 500px;">
			<button id="start" name="start" type="button"
				class="btn btn-info btn-flat"
				style="margin-top: 20px;margin-left: 20px" onclick="importData()">开始从玩校数据库中导入信息</button>
			<div id="progressbar"
				style="margin-left: 20px;width: 1000px;margin-top: 20px; height: 30px;background-color: white;border: 1px solid gray;"></div>
			<div id="percent" name="percent"
				style="margin-left: 20px;height: 30px;width : 1000px; background-color: yellow;font-size: 20px;">未开始</div>
			<div
				style="background-color: yellow;margin-left: 20px;margin-top: 20px;width: 1000px;">
				<div>
					<label>数据总量：</label><span id="total" name="total">0</span>
				</div>
				<div style="margin-top: 20px;">
					<label>完成导入：</label><span id="current" name="current">0</span>
				</div>
			</div>
		</div>
		<div class="div-height"></div>



	</div>

</body>

</html>