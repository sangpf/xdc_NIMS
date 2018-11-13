<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>测评问卷投放-添加问卷</title>
</head>
<body class="hold-transition skin-blue ">
 <!-- general form elements -->
       <div class="box box-primary">
 	       <table style="padding: 100px;margin: 10px;">
	           	<tr>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="uestionnairePage();" class="btn btn-block btn-primary" style="background-color: #BC8F8F">问卷</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="messagePage();" class="btn btn-block btn-primary">信息</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="setupPage();" class="btn btn-block btn-primary">设置</button></td>
	   	            <td style="width: 100px;padding: 3px;">
	   	            	<button type="button" onclick="operatePage();" class="btn btn-block btn-primary">运营调整</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="choseTemplatePage();" class="btn btn-block btn-primary">选择模版</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="paySetPage();" class="btn btn-block btn-primary">付费测评</button></td>	  
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="resultPage();" class="btn btn-block btn-primary">结果页</button></td>	
	           	</tr>
           </table>
         <div class="box-header with-border" style="font-size: 20px">
         	   当前投放id为 : ${deliveryid}
         	   <c:if test="${deliveryid == '' || null || 'undefined'}">空</c:if>
         </div>
         <!-- form start -->
         <form action="${ctx}/platform/searchAssByIdOrName.do" method="post">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         		<tr>
         			<td><label>&nbsp;&nbsp;选择问卷&nbsp;: &nbsp;</label></td> 
         			<td colspan="2">
         				<input type="text" id="searchIdorName" name="searchIdorName" value="${searchIdorName }" placeholder="输入问卷id,或名称">
         				<input type="submit" value="查询问卷">
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;问卷信息&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<select id="searchId" style="width: 500px;height: 35px;">
         					<c:forEach items="${niAssessQuestionnaireList}" var="niAssessQuestionnaire">
	         					<option value="${niAssessQuestionnaire.aqnid}">问卷名称:${niAssessQuestionnaire.aqnname }&nbsp;&nbsp;&nbsp;&nbsp;问卷id:${niAssessQuestionnaire.aqnid}</option>
         					</c:forEach>
         				</select>
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;选择渠道&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<select id="channelId" style="width: 500px;height: 35px;">
         					<c:forEach items="${listChannel}" var="channel">
	         					<option value="${channel.id}">${channel.title }</option>
         					</c:forEach>
         				</select>
         			</td>
         		</tr>
         	</table>
	         
           <div class="box-footer">
             <button type="button" id="addBtn" onclick="addDeliveryWanxQuenner()" class="btn btn-primary">保存</button>
           </div>
           
         </form>
       </div>
<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/js/validate.js"></script>   
<script type="text/javascript">
//投放管理id
var deliveryid = "";
$(function(){
	deliveryid = "${deliveryid}";
});
	//预览问卷
	function lookQuestionnaire(){
		  var aqnId = document.getElementById("aqnId").value;
		  window.location.href = "${ctx}/platform/niAssesQue_View.do?aqnId="+aqnId;
	}
	//保存测评问卷
	function addDeliveryWanxQuenner(){
		var sqnId = $.trim($("#searchId").val());
		var channelId = $.trim($("#channelId").val());
		
		if(sqnId == ""){
			showMessageDialog("问卷 Id 为空");
			return false;
		}
		if(channelId == ""){
			showMessageDialog("未选择渠道");
			return false;
		}
		
		$.ajax({
			url:'${ctx}/platform/niAssessDeliveryWanxSave.do',
			type:'post',
			dataType:'json',
			data:{
				sqnId : sqnId,
				deliveryid : deliveryid,
				channelId : channelId
			},
			success:function(data){
				deliveryid = data.deliveryid;
				showMessageDialog(data.msg);
				
			},
			error:function(){
				showMessageDialog("网络异常");
			}
		});
	}
	
	// 问卷
	function uestionnairePage(){
		window.location.href = "${ctx}/platform/niAssessDeliveryWanxNewJump.do?deliveryid="+deliveryid;
	}
	//跳转到信息添加页面
	function messagePage(){
		window.location.href = "${ctx}/platform/niAssessDeliveryWanxMESJump.do?deliveryid="+deliveryid;
	}
	//跳转到设置
	function setupPage(){
		window.location.href = "${ctx}/platform/niAssessDeliveryWanxSetJump.do?deliveryid="+deliveryid;
	}
	// 选择模版
	function choseTemplatePage(){
		window.location.href = "${ctx}/platform/choseAssessTemplatePage.do?deliveryid="+deliveryid;
	}
	//运营
	function operatePage(){
		window.location.href = "${ctx}/platform/niAssessDeliveryWanxOperateJump.do?deliveryid="+deliveryid;
	}
	// 付费测评
	function paySetPage(){
		window.location.href = "${ctx}/platform/assessDelivery_PaySetPage.do?deliveryid="+deliveryid;
	}
	function resultPage(){
		window.location.href = "${ctx}/platform/assessDelivery_resultPage.do?deliveryid="+deliveryid;
	}
	function resultPage(){
		window.location.href = "${ctx}/platform/assessDelivery_ResultPage.do?deliveryid="+deliveryid;
	}
</script>
</body>
</html>
