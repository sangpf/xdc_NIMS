<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>投放管理信息-投票问卷编辑</title>
  <link rel="stylesheet" href="${ctx}/BS/plugins/datepicker/datepicker3.css">
</head>
<body class="hold-transition skin-blue ">
       <div class="box box-primary">
         <div class="box-header with-border">
           <h3 class="box-title">问卷信息编辑</h3>
         </div>
         <!-- form start -->
         <form id="pageListForm" method="post" enctype="multipart/form-data">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         		<tr>
         			<td><label>&nbsp;&nbsp;问卷名称&nbsp;: &nbsp;</label></td> 
         			<td colspan="5"><input style="width: 700px;height: 30px;" class="form-control" name="qe_sqnname" value="${niVoteQuestionnaire.vqnname }" id="qe_sqnname">
         							<input type="hidden" name="qe_sqnid" value="${niVoteQuestionnaire.vqnid }" id="qe_sqnid">
         				</td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;发布者&nbsp;: &nbsp;</label></td> 
         			<td colspan="5"><input style="width: 700px;height: 30px;" class="form-control" name="qe_publisherName" value="${niVoteQuestionnaire.publishername }" id="qe_publisherName"></td>
         		</tr>
                <tr>
         			<td><label>&nbsp;&nbsp;编辑人&nbsp;: &nbsp;</label></td> 
         			<td colspan="5"><input style="width: 700px;height: 30px;" class="form-control" name="qe_editor" value="${niVoteQuestionnaire.editor }" id="qe_editor"></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;建议回收数量&nbsp;: &nbsp;</label></td> 
         			<td colspan="5"><input style="width: 700px;height: 30px;" class="form-control" name="qe_collectNum" value="${niVoteQuestionnaire.recommandqty }" id="qe_collectNum"></td>
         		</tr>
                <tr>
         			<td><label>&nbsp;&nbsp;问卷简介&nbsp;: &nbsp;</label></td> 
         			 <!-- 富文本编辑器 -->
					<td colspan="5">
		                 <textarea id="qe_Profile" name="qe_Profile" type="text/plain" style="width:700px;height:250px;">${niVoteQuestionnaire.vqnsummary }</textarea>
				    </td>
         		</tr>
         		      		
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;问卷图片&nbsp;: &nbsp;</label></td>
         			<td colspan="2"><input width="50px" type="file" name="uploadImgmes" onchange="PreviewImage(this);"
         			id="uploadImgmes" value="${url}"> </td>
         		</tr>
                <tr>
         			<td><label></label></td> 
         			<td colspan="3"><img height="150px" width="230px" id="imgurl" src="${niVoteQuestionnaire.picpath}" class="img-rounded"></td>
         		</tr>
         		
         		
        		<tr>
					<td><label>&nbsp;&nbsp;问题类型&nbsp;: &nbsp;</label></td>
					<td colspan="4">
						<c:if test="${niVoteQuestionnaire.questiontype == 1 }">
							<select name="questionType_edit">
								<option value="1">单选</option>
								<option value="2">多选</option>
							</select>
						</c:if>
						<c:if test="${niVoteQuestionnaire.questiontype == 2 }">
							<select name="questionType_edit">
								<option value="2">多选</option>								
								<option value="1">单选</option>
							</select>
						</c:if>
						<c:if test="${niVoteQuestionnaire.questiontype == 3 }">
							<input type="hidden" name="questionType_edit" value="3"> 数字天空
						</c:if>
						<c:if test="${niVoteQuestionnaire.questiontype == 4 }">
							<input type="hidden" name="questionType_edit" value="4"> 文本填空
						</c:if>
					</td>
				</tr> 	
				  <c:if test="${niVoteQuestionnaire.questiontype == 2 }">
						<tr>
							<!-- 1:单选；2：多选；3：数字填空；4：文本填空；5：语音填空；6:五级量表；7：百分量表；0:题间说明 -->
							<td><label>&nbsp;&nbsp;最少选项 : </label></td>
							<td colspan="1">
								<input style="width: 250px;height: 35px;" class="form-control" name="optMinNum" value="${niVoteQuestionnaire.optMinNum}">
							</td>
							
							<td><label>最多选项</label></td>
							<td colspan="1">
								<input style="width: 250px;height: 35px;" class="form-control" name="optMaxNum" value="${niVoteQuestionnaire.optMaxNum}">
							</td>
						</tr>
					</c:if>	
         		
				<tr>
					<td><label>&nbsp;&nbsp;问卷提干&nbsp;: &nbsp;</label></td>
					<td colspan="3">
						<input style="width: 500px;height: 35px;" class="form-control" name="sqTitle" value="${niVoteQuestionnaire.vqtitle}">
						<!-- 付文本 -->
						<%-- <textarea id="sqTitle" name="sqTitle" type="text/plain" style="width:500px;height:35px;">${niVoteQuestionnaire.vqtitle}</textarea> --%>
						
						<input type="hidden" name="optionnum" value="${niVoteQuestionnaire.optionnum }">
					</td>
				</tr>
					
				<tr>
         			<td><label>&nbsp;&nbsp;提干图片&nbsp;: &nbsp;</label></td>
         			<td><input type="file" name="titleImg" onchange="PreviewTitleImage(this);">
         				<img id="titleImg" src="${niVoteQuestionnaire.qImgUrl }" style="width: 230px;"></td>
         		</tr>
         		<tr></tr>
         		<tr></tr>
         		<tr></tr>
         		<tr></tr>
					
				<!-- 遍历每个问题的选项 -->
				<c:forEach items="${optionList}"  var="opt">
					<tr>
						<td><label>&nbsp;&nbsp;选项描述&nbsp;: &nbsp;</label></td>
						<td colspan="3">
						  
						  <input style="width: 450px;height: 35px;" class="form-control" name="optionDes" value="${opt.optionDes}">
						</td>
				    </tr>
				    <tr>
				    	<td><label>&nbsp;&nbsp;选项图片&nbsp;: &nbsp;</label></td>
				    	<td><input type="file" name="optionPic" onchange="PreviewOptionImage(this,'${opt.optionFlag}');">
				    	<img id="${opt.optionFlag}" src="${opt.optionPic}" style="width: 230px;"></td>
				    </tr>
				</c:forEach>
         		
         	</table>
	         
           <div class="box-footer">
             <button type="button" onclick="addDeliveryWanxMes();" class="btn btn-primary">保存</button>
             <button type="button" onclick="back_list();" class="btn btn-primary">回列表</button>
           </div>
           
         </form>
       </div>
<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/js/validate.js"></script>
<script>

$(function () {
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('qe_Profile');
    
    UE.getEditor('sqTitle');
    
  });
  
//预览提干图片
function PreviewTitleImage(imgFile){
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
			document.getElementById("titleImg").innerHTML = "";
			document.getElementById("titleImg").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\""
					+ path + "\")";//使用滤镜效果 
		} else//FF 
		{
			path = URL.createObjectURL(imgFile.files[0]);
			$("#titleImg").attr("src", path);
		}
	}
}

//预览标题图片
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

//预览选项图片
function PreviewOptionImage(imgFile,data){
	
	var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
	if (!pattern.test(imgFile.value)) {
		alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
		imgFile.focus();
	} else {
		//alert(data);
		var path;
		if (document.all)//IE 
		{
			imgFile.select();
			path = document.selection.createRange().text;
			document.getElementById(data).innerHTML = "";
			document.getElementById(data).style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\""
					+ path + "\")";//使用滤镜效果 
		} else//FF
		{
			path = URL.createObjectURL(imgFile.files[0]);
			$("#"+data).attr("src", path);
		}
	}
}

  //保存信息
  function addDeliveryWanxMes(){
	  
	  var formData = new FormData($("#pageListForm")[0]);
	  
	  $.ajax({
			url : "${ctx}/platform/updateNiVoteQuestionnaire_edit.do",
			type : 'POST',
			data : formData,
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			success : function(data) {
				showMessageDialog(data.msg);
			},
			error : function(data) {
				showMessageDialog("网络异常");
			}
	  });
	
  }
  
  function back_list(){
	  window.location.href = "${ctx}/platform/NiVoteQuestionnaireList.do";
  }

</script>
</body>
</html>
