<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>调查问卷投放-设置信息</title>
  <link rel="stylesheet" href="${ctx}/BS/plugins/datepicker/datepicker3.css">
</head>
<body class="hold-transition skin-blue ">
 <!-- general form elements -->
       <div class="box box-primary">
 	       <table style="padding: 100px;margin: 10px;">
	           	<tr>
	           		<td style="width: 100px;padding: 3px;"><button type="button" onclick="uestionnairePage();" class="btn btn-block btn-primary" >问卷</button></td>
	           		<td style="width: 100px;padding: 3px;"><button type="button" onclick="messagePage();" class="btn btn-block btn-primary" style="background-color: #BC8F8F" >信息</button></td>
	           		<td style="width: 100px;padding: 3px;"><button type="button" onclick="setupPage();" class="btn btn-block btn-primary">设置</button></td>
	   	            <td style="width: 100px;padding: 3px;"><button type="button" onclick="operatePage();" class="btn btn-block btn-primary">运营调整</button></td>
	           		<td style="width: 100px;padding: 3px;"><button type="button" onclick="choseTemplatePage();" class="btn btn-block btn-primary">选择模版</button></td>
	           	</tr>
           </table>
         <div class="box-header with-border" style="font-size: 20px">
         	   当前投放id为 : ${deliveryid}
         	   <c:if test="${deliveryid == '' || null || 'undefined'}">空</c:if>
         </div>
         <!-- form start -->
         <form id="pageListForm" method="post" action="${ctx}/platform/uploadPicture.do" enctype="multipart/form-data">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         		<tr>
         			<td><label>&nbsp;&nbsp;问卷名称&nbsp;: &nbsp;</label></td> <td colspan="5"><input id="sqnName" type="text" style="width: 700px;height: 30px;" class="form-control" value="${niSurveyQuestionnaire.sqnname}" disabled="disabled" placeholder="Enter ..."></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;发布机构&nbsp;: &nbsp;</label></td> <td colspan="5"><input id="publisherName" type="text" style="width: 700px;height: 30px;" class="form-control" value="${niSurveyQuestionnaire.publishername}" disabled="disabled" placeholder="Enter ..."></td>
         		</tr>
                <tr>
         			<td><label>&nbsp;&nbsp;问卷显示标题&nbsp;: &nbsp;</label></td> <td colspan="5"><input id="showTitle" name="showTitle" type="text" maxlength="28" style="width: 700px;height: 30px;" class="form-control" value="${niSurveyDeliveryWanx.showtitle }"  placeholder="Enter ..."></td>
         		</tr>
  				<tr>
         			<td><label>&nbsp;&nbsp;答题后结语&nbsp;: &nbsp;</label></td>
         			<td colspan="5"><input id="resultMsg" name="resultMsg" type="text" style="width: 700px;height: 30px;" class="form-control" value="${niSurveyDeliveryWanx.resultMsg }"></td>
         		</tr>
  				<tr>
         			<td><label>&nbsp;&nbsp;标题标签&nbsp;: &nbsp;</label></td> 
         			<td colspan="5"><input id="titleTag" name="titleTag" type="text" style="width: 700px;height: 30px;" class="form-control" value="${niSurveyDeliveryWanx.titleTag }"></td>
         		</tr>              		
         		<tr>
         			<td><label>&nbsp;&nbsp;发布时间&nbsp;: &nbsp;</label></td> 
         <td colspan="1"><input type="text"  value="${btime_str}" class="form-control pull-right" id="datepicker" name="datepicker" ></td> 
         			<td><label>&nbsp;&nbsp;截止时间&nbsp;: &nbsp;</label></td> 
         <td colspan="2"><input type="text" value="${etime_str}" class="form-control pull-right" id="datepicker1" name="datepicker1" ></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;计划采集份数&nbsp;: &nbsp;</label></td> 
         			<td colspan="5"><input id="collectnum" name="collectnum" type="text" style="width: 700px;height: 30px;" class="form-control" value="${niSurveyDeliveryWanx.collectnum}"></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;内简介&nbsp;: &nbsp;</label></td> <td colspan="5"><textarea id="" class="form-control" style="width: 700px;" rows="3"  placeholder="不能修改 ..." disabled>${niSurveyQuestionnaire.sqnsummary}</textarea></td>
         		</tr>
          		<tr>
         			<td><label>&nbsp;&nbsp;外简介&nbsp;: &nbsp;</label></td> <td colspan="5"><textarea id="showdes" name="showdes" class="form-control" style="width: 700px;" rows="3" placeholder="Enter ...">${niSurveyDeliveryWanx.showdes}</textarea></td>
         		</tr> 
         		<tr>
         			<td><label>&nbsp;&nbsp;外标签&nbsp;: &nbsp;</label></td>  
         			<td colspan="1"><input id="tag1str" name="tag1str" type="text" maxlength="28" style="width: 120px;height: 30px;" class="form-control" value="${niSurveyDeliveryWanx.tag1str}" placeholder="标签1 ..."></td>
					<td><input id="tag2str" name="tag2str" type="text"  style="width: 120px;height: 30px;" class="form-control" value="${niSurveyDeliveryWanx.tag2str}" placeholder="标签2 ..."></td>
					<td><input id="tag3str" name="tag3str" type="text"  style="width: 120px;height: 30px;" class="form-control" value="${niSurveyDeliveryWanx.tag3str}" placeholder="标签3 ..."></td>
					<td><input id="tag4str" name="tag4str" type="text"  style="width: 120px;height: 30px;" class="form-control" value="${niSurveyDeliveryWanx.tag4str}" placeholder="标签4 ..."></td>
					<td><input id="tag5str" name="tag5str" type="text"  style="width: 120px;height: 30px;" class="form-control" value="${niSurveyDeliveryWanx.tag5str}" placeholder="标签5 ..."></td>
         		</tr>
         		
                <tr>
         			<td><label>&nbsp;&nbsp;相关推荐&nbsp;&nbsp;</label></td>  
         			<td><input id="relatedStr1" name="relatedStr1" type="text" style="width: 200px;height: 30px;" class="form-control" value="${niSurveyDeliveryWanx.relatedStr1}" placeholder="相关推荐1"></td>
         			<td><input id="relatedUrl1" name="relatedUrl1" type="text" style="width: 200px;height: 30px;" class="form-control" value="${niSurveyDeliveryWanx.relatedUrl1}" placeholder="相关推荐1路径"></td>
         		</tr>  
                <tr>
         			<td><label>&nbsp;&nbsp;&nbsp;&nbsp;</label></td>  
         			<td><input id="relatedStr2" name="relatedStr2" type="text" style="width: 200px;height: 30px;" class="form-control" value="${niSurveyDeliveryWanx.relatedStr2}" placeholder="相关推荐2"></td>
         			<td><input id="relatedUrl2" name="relatedUrl2" type="text" style="width: 200px;height: 30px;" class="form-control" value="${niSurveyDeliveryWanx.relatedUrl2}" placeholder="相关推荐2路径"></td>
         		</tr>
                <tr>
         			<td><label>&nbsp;&nbsp;&nbsp;&nbsp;</label></td>  
         			<td><input id="relatedStr3" name="relatedStr3" type="text" style="width: 200px;height: 30px;" class="form-control" value="${niSurveyDeliveryWanx.relatedStr3}" placeholder="相关推荐3"></td>
         			<td><input id="relatedUrl3" name="relatedUrl3" type="text" style="width: 200px;height: 30px;" class="form-control" value="${niSurveyDeliveryWanx.relatedUrl3}" placeholder="相关推荐3路径"></td>
         		</tr>
         		
         		     		
         		<tr>
         		
         			<td><label>&nbsp;&nbsp;选择图片&nbsp;: &nbsp;</label></td> 
         			<td colspan="1"><input width="50px" type="file" name="uploadImgmes" onchange="PreviewImage(this);" id="uploadImgmes" value="${url}"> </td>
         			
         			<td colspan="3"><img height="150px" width="230px" id="imgurl" src="${niSurveyDeliveryWanx.img }" class="img-rounded"></td>
         		</tr>
         	</table>
	         <div class="box-header with-border">
	           <h3 class="box-title">奖励信息</h3>
	           <table style="border-spacing: 10px;border-collapse: separate;">
	         		<tr>
	         			<td><label>&nbsp;&nbsp;启动定奖&nbsp;: &nbsp;</label></td>
	         			<td style="color: red;"><label>&nbsp;&nbsp;只能选择一种奖励方式&nbsp;&nbsp;</label></td>
	         		</tr>
	          		<tr>
	         			<td><label>&nbsp;&nbsp;奖品id&nbsp;: &nbsp;</label></td> 
	         			<td colspan="5">
	         				<select id="awardid" name="awardid">
	         					<c:forEach items="${NiAward_list }" var="na">
		         					<option value="${na.awardId }" <c:if test="${na.awardId == niSurveyDeliveryWanx.award1id }">selected="selected"</c:if> >${na.awardName }</option>
	         					</c:forEach>
	         				</select>
	         			</td>
	         		</tr>
	          		<tr>
	         			<td><label>&nbsp;&nbsp;启动随机抽奖&nbsp;: &nbsp;</label></td>
	         		</tr>
	         		<tr>
	         			<td><label>&nbsp;&nbsp;抽奖id&nbsp;: &nbsp;</label></td> 
	         			<td colspan="5"><input id="lotteryid" name="lotteryid" value="${niSurveyDeliveryWanx.lotteryid }" type="text" style="width: 300px;height: 30px;" class="form-control"></td>
	         		</tr>         	
	         		

	          		<tr>
	         			<td><label>&nbsp;&nbsp;启动倾向抽奖&nbsp;: &nbsp;</label></td>
	         		</tr>
	         		<tr>
	         			<td><label>&nbsp;&nbsp;倾向抽奖id&nbsp;: &nbsp;</label></td>
	         			<td colspan="5"><input id="preLotteryid" name="preLotteryid" value="" type="text" style="width: 200px;height: 30px;" class="form-control"></td>
	         		</tr>  	 	         			        		        			           		
	           </table>
	         </div>
	         
           <div class="box-footer">
             <button type="button" onclick="addDeliveryWanxMes();" class="btn btn-primary">保存</button>
           </div>
           
         </form>
       </div>
<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/js/validate.js"></script> 
<script>
 $('#datepicker').datepicker({
     autoclose: true,format: 'yyyy-mm-dd'
   });
 $('#datepicker1').datepicker({
  autoclose: true,format: 'yyyy-mm-dd'
 });
  
var deliveryid = "";
$(function(){
	deliveryid = "${deliveryid}";
});
//图片路径
var imgurl = "";
//图片的存储路径
var jdbcUrl= "";
  //保存信息
  function addDeliveryWanxMes(){
	  
	  var formData = new FormData($("#pageListForm")[0]);
	  
	  $.ajax({
			url : '${ctx}/platform/niSurveyDeliveryWanxSaveMES.do?deliveryid='+deliveryid,
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
// 问卷
function uestionnairePage(){
	window.location.href = "${ctx}/platform/niSurveyDeliveryWanxAddQue.do?deliveryid="+deliveryid;
}
// 选择模版
function choseTemplatePage(){
	window.location.href = "${ctx}/platform/choseSurveyTemplatePage.do?deliveryid="+deliveryid;
}
//点击信息
function messagePage(){
	window.location.href = "${ctx}/platform/niSurveyDeliveryWanxAddMES.do?deliveryid="+deliveryid;
}
//点击设置
function setupPage(){
	window.location.href = "${ctx}/platform/niSurveyDeliveryWanxSetUPJump.do?deliveryid="+deliveryid;
}
// 运营调整
function operatePage(){
	window.location.href = "${ctx}/platform/niSurveyDeliveryWanxOperateJump.do?deliveryid="+deliveryid;
}
</script>
</body>
</html>
