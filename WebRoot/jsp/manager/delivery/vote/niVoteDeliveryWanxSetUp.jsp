<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>投放管理信息-投票问卷管理</title>
</head>
<body class="hold-transition skin-blue ">
 <!-- general form elements -->
       <div class="box box-primary">
 	       <table style="padding: 100px;margin: 10px;">
	           	<tr>
	           		<td style="width: 100px;padding: 3px;"><button type="button" onclick="uestionnairePage();" class="btn btn-block btn-primary">问卷</button></td>
	           		<td style="width: 100px;padding: 3px;"><button type="button" onclick="messagePage();" class="btn btn-block btn-primary">信息</button></td>
	           		<td style="width: 100px;padding: 3px;"><button type="button" onclick="setupPage();" style="background-color: #BC8F8F" class="btn btn-block btn-primary">设置</button></td>
	   	            <td style="width: 100px;padding: 3px;"><button type="button" onclick="operatePage();" class="btn btn-block btn-primary">运营调整</button></td>
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
         			<td><label>&nbsp;&nbsp;编辑审核条件&nbsp;: &nbsp;</label></td> 
         			<td>
         				<input type="hidden" id="_sqnId" value="${aqnId}">
         				<input type="hidden" id="_deliveryid" value="${deliveryid}">
         			</td>
         		</tr>
          		<tr>
         			<td><label>&nbsp;&nbsp;答题时长&nbsp;: &nbsp;</label></td> 
         			<td colspan="3">
         		<input id="conditionId" type="text" maxlength="28" style="width: 150px;height: 30px;" <c:if test="${niVoteDeliveryWanx.lTime != null }">value="${niVoteDeliveryWanx.lTime/60 }"</c:if>   class="form-control" placeholder="请输入分 ...">
         			</td> 
         			<td>分钟</td>
         		</tr>       		
         	</table>
         <div class="box-header with-border">
           <h3 class="box-title">广告位</h3>
         </div>	  
         	 <table style="border-spacing: 10px;border-collapse: separate;">
         		<tr>
         			<td><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编辑广告位&nbsp;: &nbsp;</label></td> 
         		</tr>
          		<tr>
         			<td><label>&nbsp;&nbsp;广告id&nbsp;: &nbsp;</label></td> 
         			<td colspan="3">
         			<select id="advertisementId">
         				<c:forEach items="${niAdInfoList }" var="ad">
         					<option value="${ad.adId }" <c:if test="${niVoteDeliveryWanx.adid == ad.adId}">selected="selected"</c:if> >${ad.adTitle }</option>
         				</c:forEach>
         			</select>
         			</td>
         		</tr>        	 	 
         	 </table>
                
           <div class="box-footer">
             <button type="button" onclick="addDeliveryWanxSetUP()" class="btn btn-primary">保存</button>
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
	function addDeliveryWanxSetUP(){
		var conditionId = $("#conditionId").val();  //答题时长
		var advertisementId = $('#advertisementId').val();   //广告id
		
		$.ajax({
			url:'${ctx}/platform/niVoteDeliveryWanxSetUpSave.do',
			data:{
				conditionId : conditionId,
				advertisementId : advertisementId,
				deliveryid : deliveryid
			},
			type:"post",
			dataType:'json',
			success:function(data){
				showMessageDialog(data.msg);
			}
		});
	}

	// 选择模版
	function choseTemplatePage(){
		window.location.href = "${ctx}/platform/choseVoteTemplatePage.do?deliveryid="+deliveryid;
	}
	//点击信息
	function messagePage(){
		window.location.href = "${ctx}/platform/niVoteDeliveryWanxSaveMESJump.do?deliveryid="+deliveryid;
	}
	//点击设置
	function setupPage(){
		window.location.href = "${ctx}/platform/niVoteDeliveryWanxSetUpJump.do?deliveryid="+deliveryid;
	}
	//问卷
	function uestionnairePage(){
		window.location.href = "${ctx}/platform/niVoteDeliveryWanxAdd.do?deliveryid="+deliveryid;
	}
	//运营
	function operatePage(){
		window.location.href = "${ctx}/platform/niVoteDeliveryWanxOperateJump.do?deliveryid="+deliveryid;
	}
</script>
</body>
</html>
