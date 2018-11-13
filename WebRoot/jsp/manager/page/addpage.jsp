<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>添加页面</title>
</head>
<body class="hold-transition skin-blue ">
	<div class="box box-primary">
         <div class="box-header with-border">
           <h3 class="box-title">添加页面</h3>
         </div>
         <!-- form start -->
         <form action="${ctx}/platform/searchDaily3UpdateByDevId.do" method="post">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         	    <tr>
         			<td><label>&nbsp;&nbsp;通用模板类型&nbsp;: &nbsp;</label></td> 
         			<td colspan="2">
         				<select name="qnType" id="qnType" style="width: 190px;height: 30px;">
         					<option value="1">调查投放</option>
         					<option <c:if test="${qnType_Str == 2 }">selected="selected"</c:if> value="2">测评投放</option>
         					<option <c:if test="${qnType_Str == 3 }">selected="selected"</c:if> value="3">投票投放</option>
         					
         					<option <c:if test="${qnType_Str == 4 }">selected="selected"</c:if> value="4">报告</option>
         					<option <c:if test="${qnType_Str == 5 }">selected="selected"</c:if> value="5">推文</option>
         				</select>
         			</td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;通用模板 ID&nbsp;: &nbsp;</label></td> 
         			<td colspan="2">
         				<input type="text" id="itemId" name="itemId" value="${itemId }" style="width: 190px;height: 30px;" placeholder=" 输入投放 ID 查询">
         			</td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;栏目名称&nbsp;: &nbsp;</label></td> 
         			<td colspan="2">
         				<select name="columnId" id="columnId" style="width: 190px;height: 30px;">
         					<c:forEach items="${listColumn}" var="column">
	         					<option <c:if test="${columnId == column.id }">selected="selected"</c:if> 
	         						value="${column.id }">${column.title }</option>
         					</c:forEach>
         				</select>
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;渠道名称&nbsp;: &nbsp;</label></td> 
         			<td colspan="2">
         				<select name="channelId" id="channelId" style="width: 190px;height: 30px;">
         					<c:forEach items="${listChannel}" var="channel">
	         					<option <c:if test="${channelId == channel.id }">selected="selected"</c:if> 
	         						value="${channel.id }">${channel.title }</option>
         					</c:forEach>
         				</select>
         			</td>
         		</tr>
         		
         	</table>
	         
           <div class="box-footer">
             <button type="button" id="addBtn" onclick="savePage()" class="btn btn-primary">保存</button>
           </div>
           
         </form>
       </div>
	<!-- /.box -->
	<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script>
	// 保存通用模版页面
	function savePage(){
		var qnType = $("#qnType").val();
		var itemId = $("#itemId").val();
		var columnId = $("#columnId").val();
		var channelId = $("#channelId").val();
		
		if(itemId == ""){
			showMessageDialog("投放ID为空");
			return false;
		}
		
		$.ajax({
			url : "${ctx}/light/savePage.do",
			data : {
				qnType : qnType,
				itemId : itemId,
				columnId : columnId,
				channelId : channelId
			},
			dataType : "json",
			type : "post",
			success : function(data){
				showConfirmDialog_saveQueJump(data.msg, function(check){
					if(check){
						window.location.href = "${ctx}/light/listPage.do?channelId=2";
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
