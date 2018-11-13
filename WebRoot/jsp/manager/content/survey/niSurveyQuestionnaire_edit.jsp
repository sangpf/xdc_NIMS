<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>投放管理信息-调查问卷管理</title>
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
         			<td colspan="5"><input style="width: 700px;height: 30px;" class="form-control" name="qe_sqnname" value="${niSurveyQuestionnaire.sqnname }" id="qe_sqnname">
         							<input type="hidden" name="qe_sqnid" value="${niSurveyQuestionnaire.sqnid }" id="qe_sqnid">
         				</td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;发布者&nbsp;: &nbsp;</label></td> 
         			<td colspan="5"><input style="width: 700px;height: 30px;" class="form-control" name="qe_publisherName" value="${niSurveyQuestionnaire.publishername }" id="qe_publisherName"></td>
         		</tr>
                <tr>
         			<td><label>&nbsp;&nbsp;编辑人&nbsp;: &nbsp;</label></td> 
         			<td colspan="5"><input style="width: 700px;height: 30px;" class="form-control" name="qe_editor" value="${niSurveyQuestionnaire.editor }" id="qe_editor"></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;建议回收数量&nbsp;: &nbsp;</label></td> 
         			<td colspan="5"><input style="width: 700px;height: 30px;" class="form-control" name="qe_collectNum" value="${niSurveyQuestionnaire.recommandqty }" id="qe_collectNum"></td>
         		</tr>
                <tr>
         			<td><label>&nbsp;&nbsp;问卷简介&nbsp;: &nbsp;</label></td> 
         			<!-- 富文本编辑器 -->
					<td colspan="5">
		                 <textarea id="qe_Profile" name="qe_Profile" type="text/plain" style="width:700px;height:250px;">${niSurveyQuestionnaire.sqnsummary }</textarea>
				    </td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;页面显示&nbsp;: &nbsp;</label></td> 
         			<td>
         				<select style="width: 100px;height: 35px;" id="showType" name="showType">
         					<option value="1">整页显示</option>
         					<option <c:if test="${niSurveyQuestionnaire.showType == 2 }">selected="selected"</c:if> value="2">分页显示</option>
         				</select>
         			</td>
         		</tr>     		
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;选择图片&nbsp;: &nbsp;</label></td>
         			<td colspan="2"><input width="50px" type="file" name="uploadImgmes" onchange="PreviewImage(this);"
         			id="uploadImgmes" value="${url}"> </td>
         		</tr>
                <tr>
         			<td><label></label></td> 
         			<td colspan="3"><img height="150px" width="230px" id="imgurl" src="${niSurveyQuestionnaire.picpath}" class="img-rounded"></td>
         		</tr>           		
         		
				<!-- 遍历每个问题 -->
				<c:forEach items="${arrayList }" var="queMap">
					<tr>
						<td><label style="font-size: 20px;">第${queMap.questionnum }题:</label></td>
					</tr>
					
					<tr>
						<td><label>&nbsp;&nbsp;问题类型&nbsp;: &nbsp;</label></td>
						<td colspan="4">
								<c:if test="${queMap.questiontype == 1 }">
									<select id="questionType_edit" name="questionType_edit">
										<option value="1">单选</option>
										<option value="2">多选</option>
									</select>
								</c:if>
								<c:if test="${queMap.questiontype == 2 }">
									<select id="questionType_edit" name="questionType_edit">
										<option value="2">多选</option>
										<option value="1">单选</option>
									</select>
								</c:if>
								<c:if test="${queMap.questiontype == 3 }">
									<input type="hidden" name="questionType_edit" value="3"> 数字天空
								</c:if>
								<c:if test="${queMap.questiontype == 4 }">
									<input type="hidden" name="questionType_edit" value="4"> 文本填空
								</c:if>
								<c:if test="${queMap.questiontype == 5 }">
									<input type="hidden" name="questionType_edit" value="5"> 语音填空
								</c:if>
						</td>
					</tr>
					
					<c:if test="${queMap.questiontype == 2 }">
						<tr>
							<!-- 1:单选；2：多选；3：数字填空；4：文本填空；5：语音填空；6:五级量表；7：百分量表；0:题间说明 -->
							<td><label>&nbsp;&nbsp;最少选项 : </label></td>
							<td colspan="1">
								<input style="width: 250px;height: 35px;" class="form-control" name="optMinNum" value="${queMap.optMinNum}">
							</td>
							
							<td><label>最多选项</label></td>
							<td colspan="1">
								<input style="width: 250px;height: 35px;" class="form-control" name="optMaxNum" value="${queMap.optMaxNum}">
							</td>
						</tr>
					</c:if>
					
					<!-- 下面这段代码 是为了解决后台获取 optMinNum 出现空指针, 并没有传递实际的数据-->
					<c:if test="${queMap.questiontype != 2 }">
						<tr>
							<!-- 1:单选；2：多选；3：数字填空；4：文本填空；5：语音填空；6:五级量表；7：百分量表；0:题间说明 -->
							<td colspan="1">
								<input type="hidden" style="width: 250px;height: 35px;" class="form-control" name="optMinNum" value="">
							</td>
							
							<td colspan="1">
								<input type="hidden" style="width: 250px;height: 35px;" class="form-control" name="optMaxNum" value="">
							</td>
						</tr>
					</c:if>
					
					
					
					<tr>
						<td><label>&nbsp;&nbsp;问题提干&nbsp;: &nbsp;</label></td>
						<td colspan="3">
							<input style="width: 550px;height: 35px;" class="form-control" name="sqTitle" value="${queMap.sqtitle}">
							
					    <!-- 富文本编辑器 -->
		               <%--  <textarea class="sqTitle" id="${queMap.sqid }" name="sqTitle" type="text/plain" style="width:550px;height:35px;">${queMap.sqtitle}</textarea> --%>
				    	
							<input type="hidden" name="sqid" value="${queMap.sqid }">
							<input type="hidden" name="optionnum" value="${queMap.optionnum }">
						</td>
					</tr>
					<tr>
	         			<td><label>&nbsp;&nbsp;提干图片&nbsp;: &nbsp;</label></td>
	         			<td><input type="file" name="titleImg" onchange="PreviewTitleImage(this,'${queMap.questionnum}');">
	         				<img id="${queMap.questionnum}" src="${queMap.titleimgurl }" style="width: 230px;"></td>
	         		</tr>
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
             <button type="button" onclick="back_toList();" class="btn btn-primary">回列表</button>
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
    
    $(".sqTitle").each(function(i){
    	//alert(this.id);
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
			url : "${ctx}/platform/updateNiSurveyQuestionnaire_edit.do",
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
  
  function back_toList(){
	  
	  window.location.href = "${ctx}/platform/NiSurveyQuestionnaireList.do";
  }

</script>
</body>
</html>
