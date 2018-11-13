<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>测评问卷编辑</title>
  <link rel="stylesheet" href="${ctx}/BS/plugins/datepicker/datepicker3.css">
  
</head>
<body class="hold-transition skin-blue ">
       <div class="box box-primary">
    	    <table style="padding: 100px;margin: 10px;">
	           	<tr>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="editAssessQuestion();" class="btn btn-block btn-primary" style="background-color: #BC8F8F">问卷编辑</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="editAssessResult_list();" class="btn btn-block btn-primary">结果页编辑</button></td>
	           	</tr>
           </table>
        <div class="box-header with-border" style="font-size: 20px">
        	   当前问卷id为 : ${aqnId}
        	   <c:if test="${aqnId == '' || null || 'undefined'}">空</c:if>
        </div>
         <div class="box-header with-border">
           <h3 class="box-title">问卷信息编辑</h3>
         </div>
         <!-- form start -->
         <form id="pageListForm" method="post" enctype="multipart/form-data">
         	<table style="border-spacing: 10px;border-collapse: separate;">
    	         <tr>
         			<td><label>&nbsp;&nbsp;问卷 ID&nbsp;: &nbsp;</label></td> 
         			<td colspan="5"><input style="width: 700px;height: 30px;" class="form-control" name="aqnId" value="${aqnId}" id="aqnId"></td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;问卷名称&nbsp;: &nbsp;</label></td> 
         			<td colspan="5"><input style="width: 700px;height: 30px;" class="form-control" name="qe_sqnname" value="${niAssessQuestionnaire.aqnname }" >
         							<input type="hidden" name="qe_sqnid" value="${niAssessQuestionnaire.aqnid }" >
     				</td>
         		</tr>
         		
         		 <tr>
         			<td><label>&nbsp;&nbsp;问卷副标题&nbsp;: &nbsp;</label></td> 
         			<td colspan="5">
         					<input style="width: 700px;height: 30px;" class="form-control" name="subtitle" value="${niAssessQuestionnaire.subtitle }" >
     				</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;发布者&nbsp;: &nbsp;</label></td> 
         			<td colspan="5"><input style="width: 700px;height: 30px;" class="form-control" name="qe_publisherName" value="${niAssessQuestionnaire.publishername }"></td>
         		</tr>
                <tr>
         			<td><label>&nbsp;&nbsp;编辑人&nbsp;: &nbsp;</label></td> 
         			<td colspan="5"><input style="width: 700px;height: 30px;" class="form-control" name="qe_editor" value="${niAssessQuestionnaire.editor }"></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;问卷作者&nbsp;: &nbsp;</label></td> 
         			<td colspan="5">
         				<select name="authorId" style="width: 200px;height: 30px;"> 
         					<c:forEach items="${author_List }" var="author" >
	         					<option <c:if test="${author.id == authorId }">selected="selected"</c:if> value="${author.id }">${author.userName }</option>
         					</c:forEach>
         				</select>
         				
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;显示方式&nbsp;: &nbsp;</label></td> 
         			<td colspan="5">
         				<select name="showType" style="width: 200px;height: 30px;"> 
         					<option value="1">整页显示</option>
         					<option value="2" <c:if test="${niAssessQuestionnaire.showType == 2 }">selected="selected"</c:if>>分页显示</option>
         				</select>
         				
         			</td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;结果页显示方式&nbsp;: &nbsp;</label></td> 
         			<td colspan="5">
         				<select name="resutShowType" style="width: 200px;height: 30px;"> 
         					<option value="1">方式一</option>
         					<option value="2" <c:if test="${niAssessQuestionnaire.resutShowType == 2 }">selected="selected"</c:if>>方式二</option>
         				</select>
         				
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;建议回收数量&nbsp;: &nbsp;</label></td> 
         			<td colspan="5"><input style="width: 700px;height: 30px;" class="form-control" name="qe_collectNum" value="${niAssessQuestionnaire.recommandqty }" ></td>
         		</tr>
                <tr>
         			<td><label>&nbsp;&nbsp;问卷简介&nbsp;: &nbsp;</label></td> 
         			<!-- 富文本编辑器 -->
					<td colspan="5">
		                 <textarea id="qe_Profile" name="qe_Profile" type="text/plain" style="width:600px;height:200px;">${niAssessQuestionnaire.aqnsummary }</textarea>
		                 
		                 <%-- <textarea style="width:500px;height:200px;">${niAssessQuestionnaire.aqnsummary }</textarea> --%>
		                 
				    </td>
         		</tr>
         		      		
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;选择图片&nbsp;: &nbsp;</label></td>
         			<td colspan="2"><input width="50px" type="file" name="uploadImgmes" onchange="PreviewImage(this);"
         			id="uploadImgmes" value="${url}"> </td>
         		</tr>
                <tr>
         			<td><label></label></td> 
         			<td colspan="3"><img height="150px" width="230px" id="imgurl" src="${niAssessQuestionnaire.picpath}" class="img-rounded"></td>
         		</tr>           		
         		
				<!-- 遍历每个问题 -->
				<c:forEach items="${arrayList }" var="queMap">
					<tr>
						<td><label style="font-size: 20px;">第${queMap.questionnum }题:</label></td>
					</tr>
					
					<tr>
						<td><label>&nbsp;&nbsp;问题提干&nbsp;: &nbsp;</label></td>
						<td colspan="3">
							<input style="width: 550px;height: 35px;" class="form-control" name="sqTitle" value="${queMap.aqtitle}">
							
							<%-- <textarea class="sqTitle" id="${queMap.aqid }" name="sqTitle" type="text/plain" style="width:550px;height:55px;">${queMap.aqtitle}</textarea> --%>
							
							<input type="hidden" name="sqid" value="${queMap.aqid }">
							<input type="hidden" name="optionnum" value="${queMap.optionnum }">
						</td>
					</tr>
					<tr>
	         			<td><label>&nbsp;&nbsp;提干图片&nbsp;: &nbsp;</label></td>
	         			<td><input type="file" name="titleImg" onchange="PreviewTitleImage(this,'${queMap.questionnum}');">
	         				<img id="${queMap.questionnum}" src="${queMap.titleimgurl}" style="width: 230px;"></td>
	         		</tr>					
					
					<tr></tr>
					<tr></tr>
					
					<!-- 遍历每个问题的选项 -->
					<c:forEach items="${queMap.optionList }"  var="optDes">
						<tr>
							<td><label>&nbsp;&nbsp;选项描述&nbsp;: &nbsp;</label></td>
							<td colspan="3">
							  <input style="width: 450px;height: 35px;" class="form-control" name="optionDes" value="${optDes}"></td>
					    </tr>
					</c:forEach>
					
				</c:forEach>  
				
         	</table>
         	
         	<div id="back_to_top">返回顶部</div>
	        
           <div class="box-footer">
             <button type="button" onclick="addDeliveryWanxMes();" class="btn btn-primary">保存</button>
             <input type="button" id="saveBtn" onclick="back_toList()" class="btn btn-primary" value="回列表"> 
           </div>
           
         </form>
       </div>
<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/js/validate.js"></script>
<script>

var aqnId = "";
$(function(){
	aqnId = "${aqnId}";
});

 $(function (){
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('qe_Profile');
    
    $(".sqTitle").each(function(i){
    	UE.getEditor(this.id);
    });
    
    //当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失
    $(function () {
        $(window).scroll(function(){
            if ($(window).scrollTop()>500){
                $("#back_to_top").fadeIn(500);
            }
            else
            {
                $("#back_to_top").fadeOut(500);
            }
        });

        //当点击跳转链接后，回到页面顶部位置
        $("#back_to_top").click(function(){
            if ($('html').scrollTop()) {
                $('html').animate({ scrollTop: 0 }, 500);//动画效果
                return false;
            }
            $('body').animate({ scrollTop: 0 }, 500);
            return false;
        });
    });
    
 }); 
  
//预览提干图片
function PreviewTitleImage(imgFile,data){
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

//预览本地图片
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

  //保存信息
  function addDeliveryWanxMes(){
	  var formData = new FormData($("#pageListForm")[0]);
	  $.ajax({
			url : "${ctx}/platform/updateNiAssessQuestionnaire_edit.do",
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
  
  
  // 跳转到问卷编辑
  function editAssessQuestion(){
	  window.location.href = "${ctx}/platform/editNiAssessQuestionnaire.do?aqnId="+aqnId;
  }
  // 结果页编辑
  function editAssessResult_list(){
	  window.location.href = "${ctx}/platform/editAssessResult_list.do?aqnId="+aqnId;
  }
  
  function back_toList(){
	  window.location.href = "${ctx}/platform/niAssessQuestionnaireList.do";
  }

</script>
</body>
</html>
