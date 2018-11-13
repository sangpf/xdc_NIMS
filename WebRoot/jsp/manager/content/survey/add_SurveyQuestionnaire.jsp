<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>上传调查问卷模版</title>
</head>
<body class="hold-transition skin-blue ">
       <div class="box box-primary">
         <div class="box-header with-border">
         </div>
         <!-- form start -->
         <form id="pageListForm">
         	<table style="border-spacing: 10px;border-collapse: separate;">    		
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;调查问卷Excel模版&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<input type="file" name="surveyQuestion_file">
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;调查问卷内图&nbsp;: &nbsp;</label></td>
         			<td><input width="50px" type="file" name="surveyQuestion_pic" onchange="previewImage_picUrl(this);" value=""></td>
         		</tr>
                <tr>
         			<td><label></label></td>
         			<td><img height="150px" width="230px" id="picUrl_view" src="" class="img-rounded"></td>
         		</tr>
         		
         	</table>
         </form>
	        <div id="errorMsg" style="color: red;"></div>
	        
           <div class="box-footer">
             <input type="button" id="saveBtn" onclick="uploadQuestionnaire()" class="btn btn-primary" value="保存"> 
             <input type="button" id="saveBtn" onclick="back_toList()" class="btn btn-primary" value="回列表"> 
           </div>
           
       </div>
<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/js/validate.js"></script> 
<script type="text/javascript">
	//保存
	function uploadQuestionnaire(){
	  
	  var formData = new FormData($("#pageListForm")[0]);
	    $.ajax({
	         url: "${ctx}/platform/save_surveyQuestionnaire.do",
	         type: 'POST',  
	         data: formData,  
	         async: false,  
	         cache: false,  
	         contentType: false,  
	         processData: false,  
	         success: function (data) {
	        	 
	        	 showMessageDialog(data.msg);
	        		 
	        	 if(!data.success){
	        		 var sqnId = data.errorCode;
	        		 $.ajax({
	        			 url : "${ctx}/platform/deleteSurveyQuestionnaire.do?sqnId_list="+sqnId,
	        			 dataType : "JSON",
	        			 type : "POST",
	        			 success : function(data_new){
	        				 
	        			 },
	        			 error : function(data_new){
	        				 showMessageDialog("网络异常");  
	        			 }
	        			 
	        		 });
	        	 }
	        		 
	         },  
	         error: function (data) {
	             showMessageDialog("网络异常");  
	         } 
	    });
	}
	  // 预览图
	  function previewImage_picUrl(imgFile){
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
					document.getElementById("picUrl_view").innerHTML = "";
					document.getElementById("picUrl_view").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\""
							+ path + "\")";//使用滤镜效果 
				} else//FF 
				{
					path = URL.createObjectURL(imgFile.files[0]);
					$("#picUrl_view").attr("src", path);
				}
			}
	  }
	  
	  function back_toList(){
		  
		  window.location.href = "${ctx}/platform/NiSurveyQuestionnaireList.do";
	  }
	
</script>
</body>
</html>
