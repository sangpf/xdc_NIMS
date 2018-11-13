<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>投放管理-测评问卷管理</title>
</head>
<body class="hold-transition skin-blue ">
 <!-- general form elements -->
       <div class="box box-primary">
 	       <table style="padding: 100px;margin: 10px;">
	           	<tr>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="uestionnairePage();" class="btn btn-block btn-primary" >问卷</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="messagePage();" class="btn btn-block btn-primary">信息</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="setupPage();" class="btn btn-block btn-primary">设置</button></td>
	   	            <td style="width: 100px;padding: 3px;">
	   	            	<button type="button" onclick="operatePage();" class="btn btn-block btn-primary"  style="background-color: #BC8F8F">运营调整</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="choseTemplatePage();" class="btn btn-block btn-primary">选择模版</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="paySetPage();" class="btn btn-block btn-primary">付费测评</button></td>	 	
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="resultPage();" class="btn btn-block btn-primary">结果页</button></td>           			
	           	</tr>
           </table>
         <!-- form start -->
         <form>
         	<table style="border-spacing: 10px;border-collapse: separate;">
         		<tr>
         			<td><label>&nbsp;&nbsp;计划采集份数&nbsp;: &nbsp;</label></td> <td colspan="1"><input id="sqnName" type="text" maxlength="28" style="width: 270px;height: 30px;" class="form-control" value="${niAssessDeliveryWanx.collectNum }" disabled="disabled"></td>
         			<td><label>&nbsp;&nbsp;上线时间&nbsp;: &nbsp;</label></td> 
         		<td colspan="1">
         			<fmt:formatDate value="${niAssessDeliveryWanx.bTime }" pattern="yyyy-MM-dd"/></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;实际收集份数&nbsp;: &nbsp;</label></td> <td colspan="1"><input id="trueOrderNum" type="text" maxlength="28" style="width: 270px;height: 30px;" class="form-control" value="${realCount }" disabled ></td>
         			<td><label>&nbsp;&nbsp;页面显示份数&nbsp;: &nbsp;</label></td> <td colspan="1"><input id="showNum" type="text" maxlength="28" style="width: 270px;height: 30px;" class="form-control" value="" disabled="disabled"></td>
         		</tr>    
          		<tr>
         			<td><label>&nbsp;&nbsp;启用数据调整&nbsp;: &nbsp;</label></td> <td><input id="datau1" name="datau" type="radio" onclick="startChangeNum();" ></td>
         			<td><label>&nbsp;&nbsp;关闭数据调整&nbsp;: &nbsp;</label></td> <td><input id="datau2" name="datau" type="radio" checked="checked"></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;模拟增长数值&nbsp;: &nbsp;</label></td> <td colspan="5"><input id="simulationNum" type="text" maxlength="28" style="width: 270px;height: 30px;" class="form-control" value="" disabled="disabled" ></td>
         		</tr>        		     		
         		<tr>
         			<td><label>&nbsp;&nbsp;时间&nbsp;: &nbsp;</label></td>
         		    <td colspan="5">
         		    	<select id="publisherTime" style="width: 170px;height: 30px;">
         		    		<option value="0.5">0.5小时</option>
         		    		<option value="1">1小时</option>
         		    		<option value="2">2小时</option>
          		    		<option value="5">5小时</option>
         		    		<option value="10">10小时</option>
         		    		<option value="15">15小时</option>
         		    		<option value="24">24小时</option>          		    		
         		    	</select>
         		</tr>   
          		<tr>
         			<td><label>&nbsp;&nbsp;目标增长数量&nbsp;: &nbsp;</label></td> <td colspan="5"><input id="publisherNum" type="text" maxlength="28" style="width: 270px;height: 30px;" class="form-control" value="" disabled="disabled" ></td>
         		</tr>        	     		
         	</table>
           <div class="box-footer">
             <button type="button" onclick="addDeliveryWanxOperate();" class="btn btn-primary">Submit</button>
           </div>
           
         </form>
       </div>
          <!-- /.box -->
<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/js/validate.js"></script>
<script type="text/javascript">

//投放管理id
var deliveryid = "";
$(function(){
	 deliveryid = "${deliveryid}";
});

//请求显示运营调整数据
$(function(){
	setInterval("changeShowNum()",1000*60);
});    
function changeShowNum(){
	   if(deliveryid == ""){
		   showMessageDialog("未选择问卷");
		   return;
	   }
	   if(sqnId == ""){
		   showMessageDialog("未选择问卷");
		   return;
	   }
	   $.ajax({
		   url:'${ctx}/platform/niAssessDeliveryWanxOperateShow.do',
		   type:'post',
		   dataType:'json',
		   data:{
			   deliveryid : deliveryid,
			   sqnId : sqnId
		   },
		   success:function(data){
			   $('#showNum').val(data.collectednum);
			   $('#simulationNum').val(data.moniNum);
		   }
	   });
	   
}

   //启用数据调整
   function startChangeNum(){
	   $("#publisherNum").attr("disabled",false);
   }
   //保存运营调整
   function addDeliveryWanxOperate(){
	   var publisherTime = $('#publisherTime').val();
	   var publisherNum = $('#publisherNum').val();
	   
	   var datauCheck = document.getElementById("datau1");
	   if(datauCheck.checked){
		   if(publisherNum == ""){
			   showMessageDialog("请输入目标增长数");
			   return;
		   }
		   if(!checkNum(publisherNum)){
			   showMessageDialog("目标增长数只能输入数字");
			   return;
		   }
		   
		   $.ajax({
			   url:'${ctx}/platform/niAssessDeliveryWanxOperateSave.do',
			   type:'post',
			   dataType:'json',
			   data:{
				   publisherTime : publisherTime,
				   publisherNum : publisherNum,
				   deliveryid : deliveryid,
				   sqnId : sqnId
			   },
			   success:function(data){
				   showMessageDialog(data.msg);
			   }
			   
		   });
	   }
	   
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
		window.location.href = "${ctx}/platform/assessDelivery_ResultPage.do?deliveryid="+deliveryid;
	}
</script>
</body>
</html>
