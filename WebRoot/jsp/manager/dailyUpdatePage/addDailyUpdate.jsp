<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>添加 -- 页面栏目</title>
</head>
<body class="hold-transition skin-blue ">
	<div class="box box-primary">

         <!-- form start -->
         <form id="pageListForm">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         	    <tr>
         			<td><label>&nbsp;&nbsp;通用内容类型&nbsp;: &nbsp;</label></td> 
         			<td>
         				<select name="qnType" id="qnType" class="form-control">
         					<option value="1">调查投放</option>
         					<option <c:if test="${qnType_Str == 2 }">selected="selected"</c:if> value="2">测评投放</option>
         					<option <c:if test="${qnType_Str == 3 }">selected="selected"</c:if> value="3">投票投放</option>
         					
         					<option <c:if test="${qnType_Str == 4 }">selected="selected"</c:if> value="4">报告</option>
         					<option <c:if test="${qnType_Str == 5 }">selected="selected"</c:if> value="5">文章</option>
         				</select>
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;通用模板 ID&nbsp;: &nbsp;</label></td> 
         			<td>
         				<input type="text" id="deliveryId" name="deliveryId" value="${deliveryId_Str }" class="form-control" placeholder=" 输入投放 ID">
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;栏目名称&nbsp;: &nbsp;</label></td> 
         			<td>
         				<select name="superListCategory" id="superListCategory" class="form-control">
         					<c:forEach items="${daily3update_CategoryList}" var="daily3update_Category">
	         					<option <c:if test="${superListCategory_Str == daily3update_Category.categoryKey }">selected="selected"</c:if> 
	         						value="${daily3update_Category.categoryKey }">${daily3update_Category.categoryValue }</option>
         					</c:forEach>
         				</select>
         			</td>
         		</tr>

         	</table>
	         
           <div class="box-footer">
             <button type="button" onclick="addDaily3UpdateDelivery()" class="btn btn-primary">保存</button>
             <button type="button" onclick="back_tolist()" class="btn btn-primary">回列表</button>
           </div>
           
         </form>
       </div>
	<!-- /.box -->
	<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script>
	//添加超级调查页面
	function addDaily3UpdateDelivery(){
		var deliveryId = $("#deliveryId").val();
		
		if(deliveryId == ""){
			showMessageDialog("投放ID为空");
			return false;
		}
		
	    var formData = new FormData($("#pageListForm")[0]);
	    $.ajax({
	         url: "${ctx}/platform/addDaily3UpdateDelivery.do",
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
		
		var superListCategory = document.getElementById("superListCategory").value;
		
		window.location.href = "${ctx}/platform/loadDaily3UpdateList.do?superListCategory_Str_GO="+superListCategory;
	}
	</script>
</body>
</html>
