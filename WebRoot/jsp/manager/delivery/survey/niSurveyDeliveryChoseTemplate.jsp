<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>调查问卷投放-替换模版</title>
</head>
<body class="hold-transition skin-blue ">
       <div class="box box-primary">
 	       <table style="padding: 100px;margin: 10px;">
    	<tr>
			<td style="width: 100px;padding: 3px;"><button type="button" onclick="uestionnairePage();" class="btn btn-block btn-primary" >问卷</button></td>
			<td style="width: 100px;padding: 3px;"><button type="button" onclick="messagePage();" class="btn btn-block btn-primary">信息</button></td>
			<td style="width: 100px;padding: 3px;"><button type="button" onclick="setupPage();" class="btn btn-block btn-primary">设置</button></td>
			<td style="width: 100px;padding: 3px;"><button type="button" onclick="operatePage();" class="btn btn-block btn-primary">运营调整</button></td>
			<td style="width: 100px;padding: 3px;"><button type="button" onclick="choseTemplatePage();" class="btn btn-block btn-primary" style="background-color: #BC8F8F">选择模版</button></td>
    	</tr>
           </table>
         <div class="box-header with-border" style="font-size: 20px">
         	   当前投放id为 : ${deliveryid}
         	   <c:if test="${deliveryid == '' || null || 'undefined'}">空</c:if>
         </div>
         <!-- form start -->
         <form id="searchForm" action="${ctx}/platform/screenSurveyTemplatePage.do" method="post">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         		<tr>
         			<td><label>&nbsp;&nbsp;模版投放ID&nbsp;: &nbsp;</label></td> 
         			<td colspan="2">
         				<input type="text" id="choseSurveyTemplateDevId" name="choseSurveyTemplateDevId" value="" placeholder="输入调查投放 id">
         			</td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;渠道名称&nbsp;: &nbsp;</label></td> 
         			<td colspan="2">
         				<select id="choseChannelId" name="choseChannelId" style="width: 250px;height: 35px;">
         					<c:forEach items="${listChannel }" var="channel">
         						<option <c:if test="${channel.id == choseChannelId }">selected="selected"</c:if> value="${channel.id }">${channel.title }</option>
         					</c:forEach>
         				</select>
         				<input type="submit" value="筛选模版" style="width: 100px;height: 35px;">
         			</td>
         		</tr>
         		<tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;选择模版&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<input type="hidden" id="SurveyDeliveryId" name="SurveyDeliveryId" value="${deliveryid}">
         				<select id="TemplateDeliveryId" style="width: 500px;height: 35px;">
         					<c:forEach items="${selectList}" var="SurveyDelivery">
	         					<option value="${SurveyDelivery.deliveryid}">
	         						<c:if test="${SurveyDelivery.channelId ==1 }">完美校园</c:if>
	         						<c:if test="${SurveyDelivery.channelId ==2 }">微信</c:if>
	         							 &nbsp;&nbsp;&nbsp;${SurveyDelivery.deliveryid }
	         					  		 &nbsp;&nbsp;&nbsp;${SurveyDelivery.showtitle }</option>
         					</c:forEach>
         				</select>
         			</td>
         		</tr>
         	</table>
         </form>
	         
           <div class="box-footer">
             <input type="button" id="saveBtn" onclick="saveTemplate()" class="btn btn-primary" value="执行复制"> 
           </div>
           
       </div>
<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/js/validate.js"></script> 
<script type="text/javascript">
	//投放管理id
	var deliveryid = "";
	$(function(){
		deliveryid = "${deliveryid}";
	});
	
	// 保存模版
	function saveTemplate(){
		
		var SurveyDeliveryId = $.trim($("#SurveyDeliveryId").val());
		
		if(SurveyDeliveryId == ""){
			showMessageDialog("将要替换的投放id为空");
			return false;
		}
		
		  // 模版投放id
		  var TemplateDeliveryId = $("#TemplateDeliveryId").val();
		  // 原投放id
		  var SurveyDeliveryId = $("#SurveyDeliveryId").val();
		  
		  $.ajax({
			  url:'${ctx}/platform/saveSurveyTemplatePage.do',
			  type:'post',
			  data:{
				  TemplateDeliveryId : TemplateDeliveryId,
				  SurveyDeliveryId : SurveyDeliveryId
			  },
			  dataType:'json',
			  success:function(data){
				  showMessageDialog(data.msg);
			  },
			  error:function(){
				  showMessageDialog("网络异常");
			  }
			  
		  });
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
