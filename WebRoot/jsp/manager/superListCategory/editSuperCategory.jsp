<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>列表管理-超级调查管理</title>
</head>
<body class="hold-transition skin-blue ">
 <!-- general form elements -->
       <div class="box box-primary">
         <div class="box-header with-border">
           <h3 class="box-title">编辑超级调查列表</h3>
         </div>
         <!-- form start -->
         <form action="${ctx}/platform/searchSuperListByDevId.do" method="post">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         	    <tr>
         			<td><label>&nbsp;&nbsp;选择投放类型&nbsp;: &nbsp;</label></td> 
         			<td colspan="2">
         				<select name="qnType" id="qnType" style="width: 190px;height: 30px;">
         					<option value="1">调查投放</option>
         					<option <c:if test="${qnType_Str == 2 }">selected="selected"</c:if> value="2">投票投放</option>
         				</select>
         			</td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;选择投放 ID&nbsp;: &nbsp;</label></td> 
         			<td colspan="2">
         				<input type="text" id="deliveryId" name="deliveryId" value="${deliveryId_Str }" style="width: 190px;height: 30px;" placeholder=" 输入投放 ID 查询">
         			</td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;选择页面类型&nbsp;: &nbsp;</label></td> 
         			<td colspan="2">
         				<select name="superListCategory" id="superListCategory" style="width: 190px;height: 30px;">
         					<c:forEach items="${superListCategoryList}" var="superListCategory">
	         					<option <c:if test="${superListCategory_Str == superListCategory.srtId }">selected="selected"</c:if> 
	         						value="${superListCategory.srtId }">${superListCategory.strName }</option>
         					</c:forEach>
         				</select>
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;<input type="submit" value="点击查询">&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<div style="font-size: 15px;color: blue;">${showTitle}</div>
         				<input type="hidden" id="showTitle" value="${showTitle}">
         				<input type="hidden" id="showState" value="${showState}">
         			</td>
         		</tr>
         	</table>
	         
           <div class="box-footer">
             <button type="button" id="addBtn" onclick="addSuperListCategory()" class="btn btn-primary">保存</button>
           </div>
           
         </form>
       </div>
<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/js/validate.js"></script>   
<script type="text/javascript">
	
	//添加超级调查页面
	function addSuperListCategory(){
		var qnType = $("#qnType").val();
		var deliveryId = $("#deliveryId").val();
		var superListCategory = $("#superListCategory").val();
		
		if(deliveryId == ""){
			showMessageDialog("投放ID为空");
			return false;
		}
		
		$.ajax({
			url : "${ctx}/platform/saveSuperListCategory.do",
			data : {
				qnType : qnType,
				deliveryId : deliveryId,
				superListCategory : superListCategory
			},
			dataType : "json",
			type : "post",
			success : function(data){
				showMessageDialog(data.msg);
			},
			error : function(){
				showMessageDialog("网络异常!");
			}
			
		});
		
	}

	
</script>
</body>
</html>
