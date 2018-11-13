<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>上传测试问卷</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

</head>
<body class="hold-transition skin-blue ">
       <div class="box box-primary">
         <div class="box-header with-border">
         </div>
         <!-- form start -->
         <form id="pageListForm">
         	<table style="border-spacing: 10px;border-collapse: separate;">    		
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;测评问卷Excel模版&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<input type="file" name="assessQuestion_file">
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;测评问卷内图&nbsp;: &nbsp;</label></td>
         			<td><input width="50px" type="file" name="assessQuestion_pic" onchange="previewImage_picUrl(this);" value=""></td>
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
 <script src="http://pv.sohu.com/cityjson?ie=utf-8"></script> 
  <script src="${ctx}/js/validate.js"></script> 
  <script type="text/javascript">
  //预览问卷
  function lookQuestionnaire(){
	  if(aqnId == ""){
		  showMessageDialog("请先添加测评问卷!");
		  return;
	  }
	  window.location.href = "http://101.200.169.159:8080/wanxSite/assess_content.html?id="+aqnId+"&deliveryId=113";
  }
  
  //上传问卷
  	function uploadQuestionnaire(){
  		var formData = new FormData($('#pageListForm')[0]);
	     $.ajax({
	          url: "${ctx}/platform/uploadNiAssessQuestionnaire.do",  
	          type: 'post',  
	          data: formData,  
	          async: false,  
	          cache: false,  
	          contentType: false,  
	          processData: false,  
	          success: function (data) {
	        	  
	        	  if(!data.success){
	        		  var aqnId = data.errorCode;
	        		  
	        		  $.ajax({
	        			  url : "${ctx}/platform/deleteNiAssessQuestionnaire.do?sqnId_list="+aqnId,
	        			  dataType : "json",
	        			  type : "post",
	        			  success : function(data){
	        				  
	        			  },
	        			  error : function(){
	        				  showMessageDialog("网络异常");
	        			  }
	        			  
	        		  });
	        		  
	        	  }
	        	  
           		  showConfirmDialog_saveQueJump(data.msg, function(check){
           			  if(check){
           				  window.location.href = "${ctx}/platform/niAssessQuestionnaireList.do";
           			  }
           		  });
            		  
	          },  
	          error : function(){
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
		  window.location.href = "${ctx}/platform/niAssessQuestionnaireList.do";
	  }

  </script>
</body>

</html>


