<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>添加 -- 产品包内容表</title>
</head>
<body class="hold-transition skin-blue ">
	<div class="box box-primary">

         <!-- form start -->
         <form id="pageListForm">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         	
         	    <tr>
         			<td><label>&nbsp;&nbsp;产品包名称 &nbsp;: &nbsp;</label></td> 
         			<td>
         				<input type="hidden" id="packageId" name="packageId" value="${productPackage.id }">
         				<input type="text" id="packageTitle" name="packageTitle" value="${productPackage.title }" disabled="disabled" class="form-control">
         			</td>
         		</tr>
         		
         	    <tr>
         			<td><label>&nbsp;&nbsp;通用内容类型&nbsp;: &nbsp;</label></td> 
         			<td>
         				<select name="itemType" id="itemType" class="form-control">
         					<option value="1">调查投放</option>
         					<option <c:if test="${qnType_Str == 2 }">selected="selected"</c:if> value="2">测评投放</option>
         					<option <c:if test="${qnType_Str == 3 }">selected="selected"</c:if> value="3">投票投放</option>
         					
         					<option <c:if test="${qnType_Str == 4 }">selected="selected"</c:if> value="4">报告</option>
         					<option <c:if test="${qnType_Str == 5 }">selected="selected"</c:if> value="5">文章</option>
         				</select>
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;通用内容 ID&nbsp;: &nbsp;</label></td> 
         			<td>
         				<input type="text" id="itemId" name="itemId" value="${itemId }" class="form-control" placeholder=" 输入投放 ID">
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;显示位置 &nbsp;: &nbsp;</label></td> 
         			<td>
         				<select name="columId" id="columId" class="form-control">
         					<option value="1">产品包首页列表</option>
         					<option value="2">顶部左侧栏目</option>
         					<option value="3">顶部右侧栏目</option>
         				</select>
         			</td>
         		</tr>

         	</table>
	         
           <div class="box-footer">
             <button type="button" onclick="save_productManage()" class="btn btn-primary">保存</button>
             <button type="button" onclick="back_tolist()" class="btn btn-primary">回列表</button>
           </div>
           
         </form>
       </div>
	<!-- /.box -->
	<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script>
	//添加超级调查页面
	function save_productManage(){
		
	    var formData = new FormData($("#pageListForm")[0]);
	    $.ajax({
	         url: "${ctx}/platform/save_productManage.do",
	         type: 'POST',  
	         data: formData,  
	         async: false,  
	         cache: false,  
	         contentType: false,  
	         processData: false,  
	         success: function (data) {
	        	 
	        	 showMessageDialog(data.msg);
	         },  
	         error: function (data) {
	             showMessageDialog("网络异常");  
	         } 
	    });
		
	}
	
	function back_tolist(){
		
		var packageId = document.getElementById("packageId").value;
		
		window.location.href = "${ctx}/platform/select_product_manage.do?productId="+packageId;
	}
	</script>
</body>
</html>
