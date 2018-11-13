<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>添加作者</title>
</head>
<body class="hold-transition skin-blue ">
       <div class="box box-primary">
         <div class="box-header with-border">
           <h3 class="box-title">作者信息</h3>
         </div>
         <!-- form start -->
         <form id="pageListForm">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         		<tr>
         			<td><label>&nbsp;&nbsp;作者 id&nbsp;: &nbsp;</label></td> 
         			<td colspan="2">  ${author.id }
         				<input type="hidden" id="authorId" name="authorId" value="${author.id }">
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;作者姓名&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<input type="text" style="width: 200px;height: 30px;" id="userName" name="userName" value="${author.userName }">
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;作者头像&nbsp;: &nbsp;</label></td>
         			<td colspan="2"><input type="file" name="uploadImgmes" onchange="PreviewImage(this)" value=""> </td>
         		</tr>
         		<tr>
         			<td><label></label></td>
         			<td colspan="2"><img height="150px" width="150px" id="imgurl" src="${author.headImg }" class="img-rounded">
         		</tr>
         		<tr>
         		<tr>
         		<tr>
         		<tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;作者简介&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<textarea rows="10" cols="50" id="introduce" name="introduce">${author.introduce }</textarea>
         			</td>
         		</tr>
         		
         	</table>
         </form>
	        <div id="errorMsg" style="color: red;"></div>
	        
           <div class="box-footer">
             <input type="button" id="saveBtn" onclick="saveAuthor()" class="btn btn-primary" value="保存"> 
           </div>
           
       </div>
<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/js/validate.js"></script> 
<script type="text/javascript">
$('#pTime').datepicker({
    autoclose: true,format: 'yyyy-mm-dd'
 	});
	//预览图片
	function PreviewImage(imgFile){
		var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
		if (!pattern.test(imgFile.value)) {
			alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
			imgFile.focus();
		} else {
			var path;
			if (document.all)//IE 
			{
				imgFile.select();
				path = document.selection.createRange().text;
				document.getElementById("imgurl").innerHTML = "";
				document.getElementById("imgurl").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\""
						+ path + "\")";//使用滤镜效果 
			} else//FF 
			{
				path = URL.createObjectURL(imgFile.files[0]);
				$("#imgurl").attr("src", path);
			}
		}
	}
	
	//保存推文
	function saveAuthor(){
		
		var authorId = $.trim($("#authorId").val());
		var userName = $.trim($("#userName").val());
		var introduce = $.trim($("#introduce").val());
				  
		  var errorMsg = $('#errorMsg');
		  errorMsg.html(" ");
		  
		  if(userName == ""){
			  errorMsg.html("&nbsp;&nbsp;&nbsp;&nbsp;请输入作者姓名");
			  return false;
		  }else if(introduce == ""){
			  errorMsg.html("&nbsp;&nbsp;&nbsp;&nbsp;请输入作者简介");
			  return false;
		  }
		  
		  var formData = new FormData($("#pageListForm")[0]);
		    $.ajax({
		         url: "${ctx}/platform/saveAuthor.do",
		         type: 'POST',  
		         data: formData,  
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
		         success: function (data) {
		        	 
		        	 showConfirmDialog(data.msg, function(check){
		        		 if(check){
		        			 window.location.href = "${ctx}/platform/authorList.do";
		        		 }
		        	 });
		         },  
		         error: function (data) {
		             showMessageDialog("网络异常");  
		         } 
		    });
		  
	}
	
</script>
</body>
</html>
