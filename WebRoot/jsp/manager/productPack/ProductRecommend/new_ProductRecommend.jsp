<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>添加 --推荐秘籍源问卷内容列表</title>
</head>
<body class="hold-transition skin-blue ">
	<div class="box box-primary">

         <!-- form start -->
         <form id="pageListForm">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         		
         		<tr></tr>
         		
         	    <tr>
         			<td><label>&nbsp;&nbsp;原内容类型&nbsp;: &nbsp;</label></td> 
         			<td>
         				<select name="sourceType" class="form-control">
         					<option <c:if test="${sourceType == 1 }">selected="selected"</c:if> value="1">调查投放</option>
         					<option <c:if test="${sourceType == 2 }">selected="selected"</c:if> value="2">测评投放</option>
         					<option <c:if test="${sourceType == 3 }">selected="selected"</c:if> value="3">投票投放</option>
         					
         					<option <c:if test="${sourceType == 4 }">selected="selected"</c:if> value="4">报告</option>
         					<option <c:if test="${sourceType == 5 }">selected="selected"</c:if> value="5">文章</option>
         					
         				</select>
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;原内容 ID&nbsp;: &nbsp;</label></td> 
         			<td>
         				<input type="text" name="sourceId" value="${sourceId }" class="form-control" placeholder=" 输入投放 ID">
         			</td>
         		</tr>

         	    <tr>
         			<td><label>&nbsp;&nbsp;目标内容类型&nbsp;: &nbsp;</label></td> 
         			<td>
         				<select name="targetType" id="targetType" class="form-control">
         					<option value="1">调查投放</option>
         					<option value="2">测评投放</option>
         					<option value="3">投票投放</option>
         					
         					<option value="4">报告</option>
         					<option value="5">文章</option>
         				</select>
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;目标内容 ID&nbsp;: &nbsp;</label></td> 
         			<td>
         				<input type="text" id="targetId" name="targetId" value="" class="form-control" placeholder=" 输入投放 ID">
         			</td>
         		</tr>
         		
         	</table>
	         
           <div class="box-footer">
             <button type="button" onclick="save_ProductRecommend()" class="btn btn-primary">保存</button>
             <button type="button" onclick="back_tolist()" class="btn btn-primary">回列表</button>
           </div>
           
         </form>
       </div>
	<!-- /.box -->
	<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script>
	//添加超级调查页面
	function save_ProductRecommend(){
		
	    var formData = new FormData($("#pageListForm")[0]);
	    $.ajax({
	         url: "${ctx}/platform/save_ProductRecommend.do",
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
		window.location.href = "${ctx}/platform/list_ProductRecommend.do";
	}
	</script>
</body>
</html>
