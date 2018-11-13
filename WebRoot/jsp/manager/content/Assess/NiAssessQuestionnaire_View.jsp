<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
</head>
<body>
	<input style="width:55%" id="input" type="hidden" name="input" value="${que_url}" placeholder="URL" >
	<br><br>
	
	<div style="margin:0 auto;text-align: center;">
		<iframe frameborder="1" id="iframe" frameborder="0" width="375" height="667" src=""></iframe> 
	</div>

<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		var aqnId = "${aqnId}";
		document.getElementById('iframe').src = "${ctx}/queView/niAssesQue_View_Jump.do?aqnId="+aqnId;
	});

</script>
</body>

</html>
