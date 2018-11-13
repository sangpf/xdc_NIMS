<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>添加学校会员</title>
</head>
<body class="hold-transition skin-blue ">
       <div class="box box-primary">
         <div class="box-header with-border">
           <h3 class="box-title">学校会员信息</h3>
         </div>
         <!-- form start -->
         <form id="pageListForm">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         	
          		<tr>
         			<td><label>&nbsp;&nbsp;搜索工具&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<input type="text" class="form-control" style="width: 300px;" id="schoolName" name="schoolName" value="" placeholder="例如：输入'北京'查询">
         			</td>
         			<td>
         				<input type="button" id="saveBtn" onclick="searchSchool()" class="btn btn-primary" value="查询学校"> 
         			</td>
         		</tr>        		
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;学校名称&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<select class="form-control" id="schoolId_Name" name="schoolId_Name">
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
             <input type="button" id="saveBtn" onclick="saveSchoolMember()" class="btn btn-primary" value="保存"> 
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

  // 查询学校列表
  function searchSchool(){
	  
	  var tbody=window.document.getElementById("schoolId_Name"); 
	  var formData = new FormData($("#pageListForm")[0]);
	  
	    $.ajax({
	         url: "${ctx}/platform/searchSchool_byName.do",
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
	                    var list_data = data.SchoolDict_list;
	  
	                    for (i in list_data) {
	                        /* str += "<tr>" +  
	                        "<td>" + data[i].hotel_seq + "</td>" +  
	                        "<td>" + data[i].hotel_name + "</td>" +  
	                        "<td>" + data[i].order_no + "</td>" +  
	                        "<td>" + data[i].user_phone + "</td>" +  
	                        "<td>" + data[i].create_time + "</td>" +  
	                        "<td>" + data[i].user_id + "</td>" +  
	                        "<td>" + data[i].cellid + "</td>" +  
	                        "<td>" + data[i].gps_city + "</td>" +  
	                        "<td>" + data[i].cell_city + "</td>" +  
	                        "<td>" + data[i].distance + "</td>" +  
	                        "</tr>"; */ 
	                        
	                        str += "<option value=" + list_data[i].schoolId +"|"+ list_data[i].schoolName + ">" + list_data[i].schoolName + "</option>";
	                    }
	                    tbody.innerHTML = str;
	        		    
	        	 }
	         },
	         error: function (data) {
	             showMessageDialog("网络异常");  
	         } 
	    });
	  
  }
	
	//保存
	function saveSchoolMember(){
		
		var schoolId_name = document.getElementById("schoolId_Name").value;
		
		var eTime = document.getElementById("eTime").value;
				  
		  var errorMsg = $('#errorMsg');
		  errorMsg.html(" ");
		  
		  if(schoolId_name == ""){
			  errorMsg.html("&nbsp;&nbsp;&nbsp;&nbsp;请选择学校");
			  return false;
		  }else if(eTime == ""){
			  errorMsg.html("&nbsp;&nbsp;&nbsp;&nbsp;请输入结束时间");
			  return false;
		  }
		  
		  var formData = new FormData($("#pageListForm")[0]);
		    $.ajax({
		         url: "${ctx}/platform/save_SchoolMember.do",
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
	
	// 回列表
	function return_list(){
		
		window.location.href = "${ctx}/platform/findAll_SchoolMember.do";
	}
	
</script>
</body>
</html>
