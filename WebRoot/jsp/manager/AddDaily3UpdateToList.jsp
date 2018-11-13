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
         			<td><label>&nbsp;&nbsp;选择投放类型&nbsp;: &nbsp;</label></td> 
         			<td colspan="2">
         				<select name="qnType" id="qnType" style="width: 190px;height: 30px;">
         					<option value="1">调查投放</option>
         					<option <c:if test="${qnType_Str == 2 }">selected="selected"</c:if> value="2">测评投放</option>
         					<option <c:if test="${qnType_Str == 3 }">selected="selected"</c:if> value="3">投票投放</option>
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
         			<td><label>&nbsp;&nbsp;选择栏目名称&nbsp;: &nbsp;</label></td> 
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
             <button type="button" id="addBtn" onclick="addDaily3UpdateDelivery()" class="btn btn-primary">保存fdsa</button>
           </div>
           
         </form>
       </div>
	<!-- /.box -->
	<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script>
	//添加超级调查页面
	function addDaily3UpdateDelivery(){
		var qnType = $("#qnType").val();
		var deliveryId = $("#deliveryId").val();
		var superListCategory = $("#superListCategory").val();
		
		if(deliveryId == ""){
			showMessageDialog("投放ID为空");
			return false;
		}
		
		$.ajax({
			url : "${ctx}/platform/addDaily3UpdateDelivery.do",
			data : {
				qnType : qnType,
				deliveryId : deliveryId,
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
	
	
	/* 
		//查询问卷题目
		function searchQnTitleById() {
			var deliveryId = document.getElementById("deliveryId").value;
			var qnType = document.getElementById("qnType").value;
			if (deliveryId == "请输入投放Id" || deliveryId == ""
					|| deliveryId == null || qnType == "") {
				alert("未输入投放Id或未选择问卷类型");
			} else {
				window.location.href = "${ctx}/platform/d3searchQnTitleById.do?deliveryId="
						+ deliveryId + "&qnType=" + qnType;

			}
		}
		//取消编辑轮播图
		function cancleEdit() {
			window.location.href = "${ctx}/platform/loadDaily3UpdateList.do";
		}
		//保存添加一条投放至列表
		function addDaily3UpdateDelivery(qnType,deliveryStatus) {
			if (deliveryStatus != 2) {
				alert("请选择投放中的问卷");
			} else {
				var deliveryId = document.getElementById("deliveryId").value;
				$
						.ajax({
							url : "${ctx}/platform/addDaily3UpdateDelivery.do",
							data : {
								deliveryId : deliveryId,
								qnType : qnType
							},
							type : "post",
							dataType : "json",
							success : function(data) {
								alert(data.msg);
								if (data.msg == "添加成功") {
									window.location.href = "${ctx}/platform/loadDaily3UpdateList.do";
								} else
									window.location.href = "${ctx}/platform/addDaily3UpdateToList.do";
							}
						});
			}
		} */
	</script>
</body>
</html>
