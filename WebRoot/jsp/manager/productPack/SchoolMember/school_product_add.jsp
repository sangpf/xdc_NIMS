<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>添加产品包</title>
</head>
<body class="hold-transition skin-blue ">
       <div class="box box-primary">
         <!-- form start -->
         <form id="pageListForm">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         	
          		<tr>
         			<td>
         				<input type="hidden" id="schoolId" value="${schoolId }"> 
         			</td>
         			
         		</tr>        		
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;产品包名称&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<select class="form-control" id="packagetId">
         					<c:forEach items="${list_ProductPackage }" var="ProductPackage">
         						<option value="${ProductPackage.id }">${ProductPackage.title }</option>
         					</c:forEach>
         				</select>
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;到期时间&nbsp;: &nbsp;</label></td>
         			<td><input type="text" value="" class="form-control" id="eTime" name="eTime"></td>
         		</tr>
         		
         	</table>
         </form>
	        <div id="errorMsg" style="color: red;"></div>
	        
           <div class="box-footer">
             <input type="button" id="saveBtn" onclick="save_School_Product()" class="btn btn-primary" value="保存"> 
             <input type="button" id="saveBtn" onclick="return_list()" class="btn btn-primary" value="回列表"> 
           </div>
           
       </div>
<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/js/validate.js"></script> 
<script type="text/javascript">
  // 时间 
  $('#eTime').datepicker({
    autoclose: true,format: 'yyyy-mm-dd'
  });
	
	//保存
	function save_School_Product(){
		
		var packagetId = document.getElementById("packagetId").value;
		var schoolId = document.getElementById("schoolId").value;
		
		var eTime = document.getElementById("eTime").value;
				  
		  var errorMsg = $('#errorMsg');
		  errorMsg.html(" ");
		  
		  if(packagetId == ""){
			  errorMsg.html("&nbsp;&nbsp;&nbsp;&nbsp;请选择产品包");
			  return false;
		  }else if(eTime == ""){
			  errorMsg.html("&nbsp;&nbsp;&nbsp;&nbsp;请输入结束时间");
			  return false;
		  }
		  
		    $.ajax({
				url : "${ctx}/platform/save_School_ProductPackage.do",
				dataType : "json",
				type : "post",
				data : {
					schoolId : schoolId,
					packagetId : packagetId,
					eTime : eTime
				},
				success : function (data){
					showMessageDialog(data.msg);
				}
		    });
		  
	}
	
	// 回列表
	function return_list(){
		var schoolId = document.getElementById("schoolId").value;
		
		window.location.href = "${ctx}/platform/search_School_ProductPackage.do?schoolId="+schoolId;
	}
	
</script>
</body>
</html>
