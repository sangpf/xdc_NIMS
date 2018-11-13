<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>推文管理</title>
</head>
<body class="hold-transition skin-blue ">
       <div class="box box-primary">
         <div class="box-header with-border">
           <h3 class="box-title">推文信息</h3>
         </div>
         <!-- form start -->
         <form id="pageListForm" action="${ctx}/platform/searchQueByIdOrName.do" method="post">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         		<tr>
         			<td><label>&nbsp;&nbsp;推文标题&nbsp;: &nbsp;</label></td> 
         			<td colspan="2">
         				<input type="hidden" id="tweetId" name="tweetId" value="${niTweet.tweetId }"> 
         				<input type="text" style="width: 300px;height: 30px;" id="tweetTitle" name="tweetTitle" value="${niTweet.tweetTitle }">
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;推文地址&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<input type="text" style="width: 500px;height: 30px;" id="tweetUrl" name="tweetUrl" value="${niTweet.tweetUrl }">
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;选择图片&nbsp;: &nbsp;</label></td>
         			<td colspan="2"><input type="file" name="uploadImgmes" id="uploadImgmes" onchange="PreviewImage(this)" value=""> </td>
         		</tr>
         		<tr>
         			<td><label></label></td>
         			<td colspan="3"><img height="150px" width="230px" id="imgurl" src="${niTweet.picUrl }" class="img-rounded">
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;作者&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<input type="text" style="width: 300px;height: 30px;" id="author" name="author" value="${niTweet.author }">
         			</td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;发布时间&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<input type="text" style="width: 300px;height: 30px;" id="pTime" name="pTime" value="${pTime_Str }">
         			</td>
         		</tr> 
         		<tr>
         			<td><label>&nbsp;&nbsp;文章标签&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<input type="text" style="width: 300px;height: 30px;" id="tag" name="tag" value="${niTweet.tag }">
         			</td>
         		</tr>         		        		
         		
         	</table>
         </form>
	        <div id="errorMsg" style="color: red;"></div>
           <div class="box-footer">
             <input type="button" id="saveBtn" onclick="saveTweet()" class="btn btn-primary" value="保存"> 
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
	function saveTweet(){
		  var tweetTitle = $("#tweetTitle").val();
		  var tweetUrl = $("#tweetUrl").val();
		  var author = $("#author").val(); 
		  var pTime = $("#pTime").val();
		  var tag = $("#tag").val();
		  
		  var errorMsg = $('#errorMsg');
		  errorMsg.html(" ");
		  
		  if(tweetTitle == ""){
			  errorMsg.html("&nbsp;&nbsp;&nbsp;&nbsp;请输入推文标题");
			  return false;
		  }else if(tweetUrl == ""){
			  errorMsg.html("&nbsp;&nbsp;&nbsp;&nbsp;请输入推文URL地址");
			  return false;
		  }else if(author == ""){
			  errorMsg.html("&nbsp;&nbsp;&nbsp;&nbsp;请输入推文作者");
			  return false;
		  }else if(pTime == ""){
			  errorMsg.html("&nbsp;&nbsp;&nbsp;&nbsp;请输入文章发布时间");
			  return false;
		  }else if(tag == ""){
			  errorMsg.html("&nbsp;&nbsp;&nbsp;&nbsp;请输入推文标签");
			  return false;
		  }
		  
		  var formData = new FormData($("#pageListForm")[0]);
		    $.ajax({  
		         url: "${ctx}/platform/saveNiTweet.do",
		         type: 'POST',  
		         data: formData,  
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
		         success: function (data) {
		        	 showConfirmDialog_saveQueJump(data.msg, function(check){
		        		 if(check){
		        			 window.location.href = "${ctx}/platform/findAllTweets.do";
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
