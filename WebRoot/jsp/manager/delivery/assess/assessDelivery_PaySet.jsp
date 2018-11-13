<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>测评问卷投放-添加设置</title>
</head>
<body class="hold-transition skin-blue ">
 <!-- general form elements -->
       <div class="box box-primary">
 	       <table style="padding: 100px;margin: 10px;">
	           	<tr>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="uestionnairePage();" class="btn btn-block btn-primary">问卷</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="messagePage();" class="btn btn-block btn-primary">信息</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="setupPage();" class="btn btn-block btn-primary" >设置</button></td>
	   	            <td style="width: 100px;padding: 3px;">
	   	            	<button type="button" onclick="operatePage();" class="btn btn-block btn-primary">运营调整</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="choseTemplatePage();" class="btn btn-block btn-primary">选择模版</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="paySetPage();" class="btn btn-block btn-primary" style="background-color: #BC8F8F">付费测评</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="resultPage();" class="btn btn-block btn-primary">结果页</button></td> 		           			
	           	</tr>
           </table>
           
         <div class="box-header with-border" style="font-size: 20px">
         	   当前投放id为 : ${deliveryid}
         	   <c:if test="${deliveryid == '' || null || 'undefined'}">空</c:if>
         </div>
         
         <form>
         	<table style="border-spacing: 10px;border-collapse: separate;">
          		<tr>
         			<td><label>&nbsp;&nbsp;设置价格&nbsp;: &nbsp;</label></td> 
         			<td><input id="price" type="text" value="${assessDelivery.price }" style="width: 150px;height: 30px;" class="form-control"></td>
         			<td>元</td>
         		</tr>
          		<tr>
         			<td><label>&nbsp;&nbsp;优惠信息&nbsp;: &nbsp;</label></td> 
         			<td><input id="discountInfo" type="text" value="${assessDelivery.discountInfo }" style="width: 150px;height: 30px;" class="form-control"></td> 
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;页面标签 1&nbsp;: &nbsp;</label></td> 
         			<td><input id="payTag1" type="text" value="${assessDelivery.payTag1 }" style="width: 150px;height: 30px;" class="form-control"></td> 
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;页面标签 2&nbsp;: &nbsp;</label></td> 
         			<td><input id="payTag2" type="text" value="${assessDelivery.payTag2 }" style="width: 150px;height: 30px;" class="form-control"></td> 
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;页面标签 3&nbsp;: &nbsp;</label></td> 
         			<td><input id="payTag3" type="text" value="${assessDelivery.payTag3 }" style="width: 150px;height: 30px;" class="form-control"></td> 
         		</tr>
         		
    		     <tr>
    		     <!-- 角棋类型:0.没有角棋，1.PRO专业版， 2.HOT，3.NEW -->
         			<td><label>&nbsp;&nbsp;角旗 &nbsp;: &nbsp;</label></td> 
         			<td>
         				<select id="cornerFlag"  style="height: 30px;width: 150px">
         					<option  value="0">无角旗</option>
         					<option <c:if test="${assessDelivery.cornerFlag == 1 }">selected="selected"</c:if> value="1">PRO专业版</option>
         					<option <c:if test="${assessDelivery.cornerFlag == 2 }">selected="selected"</c:if> value="2">HOT</option>
         					<option <c:if test="${assessDelivery.cornerFlag == 3 }">selected="selected"</c:if> value="3">NEW</option>
         				</select>
         			</td> 
         		</tr>
         		
         		
         	</table>  
                
           <div id="errorMsg" style="color: red;"></div>
           
           <div class="box-footer">
             <button type="button" onclick="AssessDelivery_pay()" class="btn btn-primary">保存</button>
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
  
	//保存设置
	function AssessDelivery_pay(){
		
		var price = $.trim($("#price").val());
		var discountInfo = $.trim($("#discountInfo").val());
		var payTag1 = $.trim($("#payTag1").val());
		var payTag2 = $.trim($("#payTag2").val());
		var payTag3 = $.trim($("#payTag3").val());
		
		var cornerFlag = $.trim($("#cornerFlag").val());
		
		  var errorMsg = $("#errorMsg");
		  errorMsg.html(" ");
		  
		if(!checkPrice(price)){
			 errorMsg.html("&nbsp;&nbsp;&nbsp;&nbsp;价格格式录入有误");
			return false;
		}
		else if(discountInfo == ""){
			  errorMsg.html("&nbsp;&nbsp;&nbsp;&nbsp;请输入优惠信息");
			  return false;
		}
		
		
		$.ajax({
			url:'${ctx}/platform/assessDelivery_PaySet_Save.do',
			data:{
				deliveryId : deliveryid,
				price : price,
				discountInfo : discountInfo,
				payTag1 : payTag1,
				payTag2 : payTag2,
				payTag3 : payTag3,
				cornerFlag : cornerFlag
			},
			type:"post",
			dataType:'json',
			success:function(data){
				showMessageDialog(data.msg);
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
		window.location.href = "${ctx}/platform/assessDelivery_ResultPage.do?deliveryid="+deliveryid;
	}
</script>
</body>
</html>
