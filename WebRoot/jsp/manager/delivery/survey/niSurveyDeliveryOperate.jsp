<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>投放管理-调查问卷管理</title>
</head>
<body class="hold-transition skin-blue ">
       <div class="box box-primary">
 	       <table style="padding: 100px;margin: 10px;">
	           	<tr>
	           		<td style="width: 100px;padding: 3px;"><button type="button" onclick="uestionnairePage();" class="btn btn-block btn-primary" >问卷</button></td>
	           		<td style="width: 100px;padding: 3px;"><button type="button" onclick="messagePage();" class="btn btn-block btn-primary">信息</button></td>
	           		<td style="width: 100px;padding: 3px;"><button type="button" onclick="setupPage();" class="btn btn-block btn-primary">设置</button></td>
	   	            <td style="width: 100px;padding: 3px;"><button type="button" onclick="operatePage();" class="btn btn-block btn-primary" style="background-color: #BC8F8F" >运营调整</button></td>
	           		<td style="width: 100px;padding: 3px;"><button type="button" onclick="choseTemplatePage();" class="btn btn-block btn-primary">选择模版</button></td>
	           	</tr>
           </table>
         <div class="box-header with-border" style="font-size: 20px">
         	   当前投放id为 : ${deliveryid}
         	   <c:if test="${deliveryid == '' || null || 'undefined'}">空</c:if>
         </div>
         <!-- form start -->
         <form>
         	<table style="border-spacing: 10px;border-collapse: separate;">
         		<tr>
         			<td><label>&nbsp;&nbsp;计划采集份数&nbsp;: &nbsp;</label></td> <td colspan="1"><input id="sqnName" type="text" maxlength="28" style="width: 270px;height: 30px;" class="form-control" value="${niSurveyDeliveryWanx.collectnum}" disabled="disabled"></td>
         			<td><label>&nbsp;&nbsp;上线时间&nbsp;: &nbsp;</label></td> <td colspan="1"><fmt:formatDate value="${niSurveyDeliveryWanx.btime}" pattern="yyyy-MM-dd"/><!--  <input id="sqnName" type="text" maxlength="28" style="width: 270px;height: 30px;" class="form-control"  value="" disabled > --></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;实际收集份数&nbsp;: &nbsp;</label></td> <td colspan="1"><input id="trueOrderNum" type="text" maxlength="28" style="width: 270px;height: 30px;" class="form-control" value="${trueOrderNum}" disabled ></td>
         			<td><label>&nbsp;&nbsp;页面显示份数&nbsp;: &nbsp;</label></td> <td colspan="1"><input id="showNum" type="text" maxlength="28" style="width: 270px;height: 30px;" class="form-control" value="${niSurveyDeliveryWanx.collectednum}" disabled="disabled"></td>
         		</tr>    
          		<tr>
         			<td><label>&nbsp;&nbsp;启用数据调整&nbsp;: &nbsp;</label></td>
         			<td><label>&nbsp;&nbsp;关闭数据调整&nbsp;: &nbsp;</label></td>
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
         			<td><label>&nbsp;&nbsp;目标增长数量&nbsp;: &nbsp;</label></td> <td colspan="5"><input id="publisherNum" type="text" maxlength="28" style="width: 270px;height: 30px;" class="form-control" value="" placeholder="Enter ..." disabled="disabled" ></td>
         		</tr>        	     		
         	</table>
           <div class="box-footer">
             <button type="button" onclick="addDeliveryWanxOperate();" class="btn btn-primary">保存</button>
           </div>
           
         </form>
       </div>
          <!-- /.box -->
<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/js/validate.js"></script>
<script type="text/javascript">

var deliveryid = "";
$(function(){
	deliveryid = "${deliveryid}";
});

$(function(){
	setInterval("changeShowNum()",1000*60);
}); 
//点击单选
function startChangeNum(){
	$('#publisherNum').attr("disabled",false);
}

//请求访问显示收集份数
function changeShowNum(){
	if(sqnId == "" || deliveryid == ""){
		showMessageDialog("未选择问卷");
		return;
	}
	$.ajax({
		url:'${ctx}/platform/findShowOrderNum.do',
		type:'post',
		dataType:'json',
		data:{
			sqnId : sqnId,
			deliveryid : deliveryid
		},
		success:function(data){
			$('#showNum').val(data.collectednum);
			$('#simulationNum').val(data.simulationNum);
		}
	});
}

//保存运营调整设置信息
function addDeliveryWanxOperate(){
	var showNum = $('#showNum').val();  //显示收集份数
	var publisherTime = $('#publisherTime').val();   //时间
	var publisherNum = $('#publisherNum').val();    //目标增长数量
	var trueOrderNum = ${trueOrderNum};   // 真实数据
	if(sqnId == "" || deliveryid == ""){
		showMessageDialog("未选择问卷");
		return;
	}
	var datauCheck = document.getElementById("datau1");
	//开启运营调整才会提交
	if(datauCheck.checked){
		if(publisherNum.trim()==""){
			showMessageDialog("请输入目标增长数");
			return;
		}
		
		if(!checkNum(publisherNum)){
			showMessageDialog("目标增长数只能输入首位非0数字");
			return;
		}
		
		$.ajax({
			url:'${ctx}/platform/niSurveyDeliveryWanxOperateSave.do',
			type:'post',
			dataType:'json',
			data:{
				showNum : showNum ,
				publisherTime : publisherTime,
				publisherNum : publisherNum,
				deliveryid : deliveryid,
				trueOrderNum : trueOrderNum
			},
			success:function(data){
				showMessageDialog(data.msg);
			}
		});
	}else{
		//关闭运营调整
	}

}

//问卷
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
