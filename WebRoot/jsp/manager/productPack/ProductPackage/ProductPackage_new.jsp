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
         <div class="box-header with-border">
           <h3 class="box-title">产品包信息</h3>
         </div>
         
         <form id="pageListForm">
         	<table style="border-spacing: 10px;border-collapse: separate;" >

         		<tr>
         			<td><label>&nbsp;&nbsp;标题&nbsp;: &nbsp;</label></td>
         			<td>
         				<input style="width: 300px;" class="form-control" name="title" value="${productPackage.title }">
         				<input type="hidden" value="${productPackage.id }" name="packageId">
         			</td>
         			
         			<td><label>&nbsp;&nbsp;副标题&nbsp;: &nbsp;</label></td>
         			<td>
         				<input style="width: 300px;" class="form-control" name="subtitle" value="${productPackage.subtitle }">
         			</td>
         		</tr> 
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;置顶图&nbsp;: &nbsp;</label></td>
         			<td><input width="50px" type="file" name="picUrl" onchange="previewImage_picUrl(this);" value="${productPackage.picUrl }"></td>
         		</tr>
                <tr>
         			<td><label></label></td> 
         			<td><img height="150px" width="230px" id="picUrl_view" src="${productPackage.picUrl }" class="img-rounded"></td>
         		</tr>
				
				<tr>
         			<td><label>&nbsp;&nbsp;解锁图&nbsp;: &nbsp;</label></td>
         			<td><input width="50px" type="file" name="unlockPic" onchange="previewImage_unlockPic(this);" value="${productPackage.unlockPic }"> </td>
         		
         			<td><label>&nbsp;&nbsp;未解锁图&nbsp;: &nbsp;</label></td>
         			<td><input width="50px" type="file" name="unlockedPic" onchange="previewImage_unlockedPic(this);" value="${productPackage.unlockedPic }"> </td>
         		</tr>
                <tr>
         			<td><label></label></td>
         			<td><img height="150px" width="230px" id="unlockPic_view" src="${productPackage.unlockPic }" class="img-rounded"></td>
         			
         			<td><label></label></td> 
         			<td><img height="150px" width="230px" id="unlockedPic_view" src="${productPackage.unlockedPic }" class="img-rounded"></td>
         		</tr>

				<tr>
         			<td><label>&nbsp;&nbsp;待上架图&nbsp;: &nbsp;</label></td>
         			<td><input width="50px" type="file" name="pendingOpenPic" onchange="previewImage_pendingOpenPic(this);" value="${productPackage.pendingOpenPic }"> </td>
         		</tr>
                <tr>
         			<td><label></label></td> 
         			<td><img height="150px" width="230px" id="pendingOpenPic_view" src="${productPackage.pendingOpenPic }" class="img-rounded"></td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;价格&nbsp;: &nbsp;</label></td>
         			<td><input class="form-control" name="price" value="${productPackage.price }" placeholder="单位 : 元"></td>
         			
         			<td><label>&nbsp;&nbsp;优惠价格&nbsp;: &nbsp;</label></td>
         			<td><input class="form-control" name="discount" value="${productPackage.discount }" placeholder="单位 : 元"></td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;栏目1&nbsp;: &nbsp;</label></td>
         			<td><input class="form-control" name="column1Name" value="${productPackage.column1Name }" ></td>
         			
         			<td><label>&nbsp;&nbsp;栏目2&nbsp;: &nbsp;</label></td>
         			<td><input class="form-control" name="column2Name" value="${productPackage.column2Name }" ></td>
         		</tr>
         		 
         		<tr>
         			<td><label>&nbsp;&nbsp;标签1&nbsp;: &nbsp;</label></td>
         			<td><input class="form-control" name="tag1" value="${productPackage.tag1 }"></td>
         			
         			<td><label>&nbsp;&nbsp;标签2&nbsp;: &nbsp;</label></td>
         			<td><input class="form-control" name="tag2" value="${productPackage.tag2 }"></td>
         			
         			<td><label>&nbsp;&nbsp;标签3&nbsp;: &nbsp;</label></td>
         			<td><input class="form-control" name="tag3" value="${productPackage.tag3 }" style="width: 300px;"></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;广告&nbsp;: &nbsp;</label></td>
         			<td>
         				<select class="form-control" name="adId">
         					<c:forEach items="${List_NiAdInfo }" var="adInfo">
	         					<option value="${adInfo.adId }" <c:if test="${productPackage.adId == adInfo.adId}">selected="selected"</c:if> >${adInfo.adTitle }</option>
         					</c:forEach>
         				</select>
         			</td>
         		</tr> 
 
          		<tr>
         			<td><label>&nbsp;&nbsp;渠道&nbsp;: &nbsp;</label></td>
         			<td>
         				<select class="form-control" name="channelId">
	         				<option value="1">完美校园</option>
	         				<option value="2" <c:if test="${productPackage.channelId == 2 }">selected="selected"</c:if>>微信</option>
         				</select>
         			</td>
         		</tr> 
         		   		         		        	
         		<tr>
         			<td><label>&nbsp;&nbsp;产品包简介&nbsp;: &nbsp;</label></td>
         			<td><textarea name="introduce" class="form-control">${productPackage.introduce }</textarea></td>
         		</tr>      		

         	</table>
         </form>
	       <div id="errorMsg" style="color: red;"></div>
	       
           <div class="box-footer">
             <input type="button" id="saveBtn" onclick="save_ProductPackage()" class="btn btn-primary" value="保存"> 
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
  
  // 预览解锁图
  function previewImage_unlockPic(imgFile){
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
				document.getElementById("unlockPic_view").innerHTML = "";
				document.getElementById("unlockPic_view").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\""
						+ path + "\")";//使用滤镜效果 
			} else//FF 
			{
				path = URL.createObjectURL(imgFile.files[0]);
				$("#unlockPic_view").attr("src", path);
			}
		}
	  
  }
  
  // 预览未 解锁图
  function previewImage_unlockedPic(imgFile){
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
				document.getElementById("unlockedPic_view").innerHTML = "";
				document.getElementById("unlockedPic_view").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\""
						+ path + "\")";//使用滤镜效果 
			} else//FF 
			{
				path = URL.createObjectURL(imgFile.files[0]);
				$("#unlockedPic_view").attr("src", path);
			}
		}
  }
  
  // 预览待上架图
  function previewImage_pendingOpenPic(imgFile){
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
				document.getElementById("pendingOpenPic_view").innerHTML = "";
				document.getElementById("pendingOpenPic_view").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\""
						+ path + "\")";//使用滤镜效果 
			} else//FF 
			{
				path = URL.createObjectURL(imgFile.files[0]);
				$("#pendingOpenPic_view").attr("src", path);
			}
		}
}
  
  
	//保存
	function save_ProductPackage(){
		
		  var formData = new FormData($("#pageListForm")[0]);
		    $.ajax({
		         url: "${ctx}/platform/save_ProductPackage.do",
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
		
		window.location.href = "${ctx}/platform/list_ProductPackage.do";
	}
	
</script>
</body>
</html>
