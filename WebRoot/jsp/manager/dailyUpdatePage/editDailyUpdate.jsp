<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>每日三更——添加</title>
</head>
<body class="hold-transition skin-blue ">
	<div class="box box-primary">
         <div class="box-header with-border">
           <h3 class="box-title">添加三更列表</h3>
         </div>
         <!-- form start -->
         <form action="${ctx}/platform/searchDaily3UpdateByDevId.do" method="post">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         	    <tr>
         			<td><label>&nbsp;&nbsp;通用模板类型&nbsp;: &nbsp;</label></td> 
         			<td colspan="2" >
         				<input type="hidden" value="${itemId }" id="itemId">
         				<select name="contentType" id="contentType" style="width: 190px;height: 30px;">
         					<option value="1">调查投放</option>
         					<option <c:if test="${contentType == 2 }">selected="selected"</c:if> value="2">测评投放</option>
         					<option <c:if test="${contentType == 3 }">selected="selected"</c:if> value="3">投票投放</option>
         					
         					<option <c:if test="${contentType == 4 }">selected="selected"</c:if> value="4">报告</option>
         					<option <c:if test="${contentType == 5 }">selected="selected"</c:if> value="5">文章</option>
         				</select>
         			</td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;通用模板 ID&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<input type="text" id="contentId" name="contentId" value="${contentId }" 
         					style="width: 190px;height: 30px;" placeholder=" 输入内容 ID 查询">
         			</td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;三更栏目名称&nbsp;: &nbsp;</label></td> 
         			<td colspan="2">
         				<select name="superListCategory" id="superListCategory" style="width: 190px;height: 30px;">
         					<c:forEach items="${superListCategoryList}" var="superListCategory">
	         					<option <c:if test="${listCategory == superListCategory.srtId }">selected="selected"</c:if> 
	         						value="${superListCategory.srtId }">${superListCategory.strName }</option>
         					</c:forEach>
         				</select>
         			</td>
         		</tr>
         		
<%--          		<tr>
         			<td><label>&nbsp;&nbsp;<input type="submit" value="点击查询">&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<div style="font-size: 15px;color: blue;">${showTitle}</div>
         				<input type="hidden" id="showTitle" value="${showTitle}">
         				<input type="hidden" id="showState" value="${showState}">
         			</td>
         		</tr> --%>
         	</table>
	         
           <div class="box-footer">
             <button type="button" id="addBtn" onclick="addDaily3UpdateDelivery()" class="btn btn-primary">保存</button>
           </div>
           
         </form>
       </div>
	<!-- /.box -->
	<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script>
	//添加超级调查页面
	function addDaily3UpdateDelivery(){
		var itemId = $("#itemId").val();
		var contentType = $("#contentType").val();
		var contentId = $("#contentId").val();
		var superListCategory = $("#superListCategory").val();
		
		if(contentId == ""){
			showMessageDialog("模板ID为空");
			return false;
		}
		
		$.ajax({
			url : "${ctx}/platform/saveEditDaily3UpdateDelivery.do",
			data : {
				itemId : itemId,
				contentType : contentType,
				contentId : contentId,
				superListCategory : superListCategory
			},
			dataType : "json",
			type : "post",
			success : function(data){
			//	showMessageDialog(data.msg);
				showConfirmDialog_saveQueJump(data.msg, function(check){
					if(check){
						window.location.href = "${ctx}/platform/loadDaily3UpdateList.do?superListCategory_Str_GO="+superListCategory;
					}
				});
			},
			error : function(){
				showMessageDialog("网络异常!");
			}
			
		});
		
	}
	</script>
</body>
</html>
