<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>列表管理-测一发</title>
</head>
<body class="hold-transition skin-blue ">
	<div class="box box-primary">
		<!-- form start -->
		<form id="searchForm">
		<table style="border-spacing: 10px;border-collapse: separate;">
			<tr>
				<td><label>&nbsp;&nbsp;搜索工具&nbsp;: &nbsp;</label></td>
				
				<td><input class="form-control" style="width: 300px;" id="deliveryId_Name" name="deliveryId_Name" type="text" value="" placeholder="输入测评投放id,或者投放标题搜索"/></td>
				
				<td><button type="button" onclick="search_AssessDelivey()" class="btn btn-primary">查询</button></td>
			</tr>
			
			<tr>
				<td><label>&nbsp;&nbsp; 选择投放 &nbsp;&nbsp;</label></td>
				<td>
					<select class="form-control" id="assess_delivey_id">
					</select>
				</td>
			</tr>
			
			<tr>
				<td><label>&nbsp;&nbsp;选择投放栏目&nbsp;&nbsp;</label></td>
				<td>
					<select class="form-control select-sm" id="assessListCategory">
						<option value="professialAssessQnList">专业测评</option>
					</select>
				</td>
			</tr>
		</table>
		</form>
		<div id="errorMsg" style="color: red;"></div>
		<div class="box-footer">

			<button type="button" onclick="save_Assess_page()" class="btn btn-primary">保存</button>
			<button type="button" onclick="back_toTist()" class="btn btn-primary">回列表</button>
		</div>
	</div>
	<!-- /.box -->
	<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script>
		
		//查询 投放
		function search_AssessDelivey(){
			
			var tbody = document.getElementById("assess_delivey_id");
			var formData = new FormData($("#searchForm")[0]);
			  
		    $.ajax({
		         url: "${ctx}/platform/search_assess_delivey.do",
		         type: 'POST',  
		         data: formData,  
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
		         success: function (data) {
		        	 
		        	 if(!data.success){
		        		 showMessageDialog(data.msg);
		        	 }else{
		        		 // 拼接列表
		                    var str = "";
		                    var list_data = data.assessDelivery_list;
		  
		                    for (i in list_data) {
		                    	
		                        str += "<option value=" + list_data[i].deliveryId + ">" + list_data[i].showTitle + "</option>";
		                    }
		                    tbody.innerHTML = str;
		        		    
		        	 }
		         },
		         error: function (data) {
		             showMessageDialog("网络异常");  
		         } 
		    });
			
		}
		
		// 保存
		function save_Assess_page(){
			var assess_deliveyId = document.getElementById("assess_delivey_id").value;
			var assessListCategory = document.getElementById("assessListCategory").value;
			
			  var errorMsg = $('#errorMsg');
			  errorMsg.html(" ");
			  
			  if(assess_deliveyId == ""){
				  errorMsg.html("&nbsp;&nbsp;&nbsp;&nbsp;请选择测评投放");
				  return false;
			  }else if(assessListCategory == ""){
				  errorMsg.html("&nbsp;&nbsp;&nbsp;&nbsp;请选择栏目");
				  return false;
			  }
			
			  $.ajax({
				  url : "${ctx}/platform/save_Assess_Page.do",
				  type : 'post',
				  dataType : 'json',
				  data : {
					  assess_deliveyId : assess_deliveyId,
					  assessListCategory : assessListCategory
				  },
				  success:function(data){
					  showMessageDialog(data.msg);
				  },
				  error: function (data) {
			             showMessageDialog("网络异常");  
			      } 
				  
			  });
			
		}
		
		//　返回列表
		function back_toTist(){
			window.location.href = "${ctx}/platform/loadAssessList.do";
		}

	</script>
</body>
</html>
