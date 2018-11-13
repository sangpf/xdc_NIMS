<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>调查问卷投放-添加问卷</title>
</head>
<body class="hold-transition skin-blue ">
       <div class="box box-primary">
 	       <table style="padding: 100px;margin: 10px;">
	           	<tr>
	           		<td style="width: 100px;padding: 3px;"><button type="button" onclick="uestionnairePage();" class="btn btn-block btn-primary" style="background-color: #BC8F8F" >问卷</button></td>
	           		<td style="width: 100px;padding: 3px;"><button type="button" onclick="messagePage();" class="btn btn-block btn-primary">信息</button></td>
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
         <form id="searchForm" action="${ctx}/platform/searchQueByIdOrName.do" method="post">
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
         					<c:forEach items="${niSurveyQuestionnaireList}" var="niSurveyQuestionnaire">
	         					<option value="${niSurveyQuestionnaire.sqnid}">问卷名称:${niSurveyQuestionnaire.sqnname }&nbsp;&nbsp;问卷id:${niSurveyQuestionnaire.sqnid}</option>
         					</c:forEach>
         				</select>
         			</td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;选择渠道&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<select id="channelId" style="width: 200px;height: 35px;">
         					<c:forEach items="${listChannel}" var="channel">
	         					<option value="${channel.id}">${channel.title }</option>
         					</c:forEach>
         				</select>
         			</td>
         		</tr>
         	</table>
         </form>
	         
           <div class="box-footer">
             <input type="button" id="saveBtn" onclick="addDeliveryWanxMes()" class="btn btn-primary" value="保存"> 
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
	//预览问卷
	function lookQuestionnaire(){
		  var sqn_Id = $.trim($("#sqnId").val());
		
		  if(sqn_Id == ""){
			  showMessageDialog("请先上传问卷!");
			  return false;
		  }
		  window.location.href = "${ctx}/platform/niSurveyQuestionnaireView.do?sqnId="+sqn_Id;
	}
	
	//添加问卷
	function addDeliveryWanxMes(){
		  var searchId = $.trim($("#searchId").val());   // 问卷id
		  var channelId = $.trim($("#channelId").val());

		  if(searchId == ""){
			  showMessageDialog("先选择问卷");
			  return false;
		  }
		  if(channelId == ""){
			  showMessageDialog("先选择渠道");
			  return false; 
		  }
		  
		  $.ajax({
			  url:'${ctx}/platform/niSurveyDeliveryWanxSaveQue.do',
			  type:'post',
			  data:{
				  sqnId : searchId,
				  deliveryid : deliveryid,
				  channelId : channelId
			  },
			  dataType:'json',
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
