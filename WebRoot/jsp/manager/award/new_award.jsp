<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>奖品信息</title>
</head>
<body class="hold-transition skin-blue ">
       <div class="box box-primary">
         <div class="box-header with-border">
           <h3 class="box-title">奖品信息</h3>
         </div>
         
         <form id="pageEditForm">
         	<table style="border-spacing: 10px;border-collapse: separate;" >

         		<tr>
         			<td><label>&nbsp;&nbsp; 货品 ID&nbsp;: &nbsp;</label></td>
         			<td>
         				<input type="hidden" style="width: 300px;" class="form-control" name="awardPoolId" value="${award.awardPoolId }">
         				${award.awardPoolId }
         			</td>
         			
         			<td><label>&nbsp;&nbsp; 货品名称&nbsp;: &nbsp;</label></td>
         			<td>
         				<input type="hidden" style="width: 300px;" class="form-control" name="awardPoolName" value="${award.awardPoolName }">
         				${award.awardPoolName }
         			</td>
         		</tr> 
 
         		<tr>
         			<td><label>&nbsp;&nbsp; 奖品 ID&nbsp;: &nbsp;</label></td>
         			<td>
         				<input type="hidden" style="width: 300px;" class="form-control" name="awardId" value="${award.awardId }">
         				${award.awardId }
         			</td>
         			
         			<td><label>&nbsp;&nbsp; 奖品名称&nbsp;: &nbsp;</label></td>
         			<td>
         				<input style="width: 300px;" class="form-control" name="awardName" value="${award.awardName }">
         			</td>
         		</tr> 
 
          		<tr>
         			<td><label>&nbsp;&nbsp; 发放数量&nbsp;: &nbsp;</label></td>
         			<td>
         				<input style="width: 300px;" class="form-control" name="awardNum" value="${award.awardNum }">
         			</td>
         		</tr> 
         		        		        		
         		<tr>
         			<td><label>&nbsp;&nbsp;置顶图&nbsp;: &nbsp;</label></td>
         			<td><input width="50px" type="file" name="uploadImage" onchange="previewImage_picUrl(this);" value="${award.awardPic }"></td>
         		</tr>
                <tr>
         			<td><label></label></td> 
         			<td><img height="150px" width="230px" id="picUrl_view" src="${award.awardPic }" class="img-rounded"></td>
         		</tr>
         		    
         		<tr>
         		</tr>

         		        		         		        	
         		<tr>
         			<td><label>&nbsp;&nbsp;中奖提示 &nbsp;: &nbsp;</label></td>
         			<td><textarea name="comment" class="form-control">${award.comment }</textarea></td>
         		</tr>      		

         	</table>
         </form>
	       <div id="errorMsg" style="color: red;"></div>
	       
           <div class="box-footer">
             <input type="button" id="saveBtn" onclick="save_Award()" class="btn btn-primary" value="保存"> 
             <input type="button" id="saveBtn" onclick="return_list()" class="btn btn-primary" value="回列表"> 
           </div>
           
       </div>
<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/js/validate.js"></script> 
<script type="text/javascript">

  // 预览置顶图
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
  
  //提交奖品编辑信息
  function save_Award(){
	  var formData = new FormData($( "#pageEditForm")[0]);	  
	  $.ajax({
		 url:"${ctx}/platform/updateAward.do",
         type: 'POST',  
         data: formData,  
         async: false,  
         cache: false,  
         contentType: false,  
         processData: false,  
		  success:function(data){
			  
			  showMessageDialog(data.msg);
		  }
		  
	  });
}
	
	// 回列表
	function return_list(){
		
		window.location.href = "${ctx}/platform/awardList.do";
	}
	
</script>
</body>
</html>
