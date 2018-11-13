<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
	 <meta charset="utf-8">
  <title>添加倾向抽奖规则</title>
</head>

<body class="hold-transition skin-blue sidebar-mini">

        <div class="box box-primary">
         <!-- form start -->
         <form>
         	<table  style="border-spacing: 10px;border-collapse: separate;" >
         		<tr>
         			<td><label>&nbsp;&nbsp;规则名称&nbsp;: &nbsp;</label></td> 
         			<td colspan="3"><input id="ruleName_add" type="text" style="width: 250px;height: 30px;" class="form-control"></td>
         		</tr>
         		
         		
         		<!-- 高区间 -->
         		<tr></tr>
         		<tr>
         			<td colspan="1"><label>高区间</label></td>         			    			
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;奖品名称&nbsp;: &nbsp;</label></td> 
					<td colspan="3">
					<!-- <input type="text" id="histAwardId_add" style="width: 250px;height: 30px;" class="form-control"> -->
						<select id="histAwardId_add">
							<c:forEach items="${niAwardList}" var="niAward">
								<option value="${niAward.awardId }">${niAward.awardName }</option>
							</c:forEach>
						</select>
					</td>
					
					<td><label>&nbsp;&nbsp;中奖概率&nbsp;: &nbsp;</label></td> 
					<td colspan="1"><input type="text" id="highRate_add" style="width: 250px;height: 30px;" class="form-control"></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;起始值&nbsp;: &nbsp;</label></td> 
					<td colspan="3"><input type="text" id="highBegin_add" style="width: 250px;height: 30px;" class="form-control"></td>
					
					<td><label>&nbsp;&nbsp;结束值&nbsp;: &nbsp;</label></td> 
					<td colspan="1"><input type="text" id="highEnd_add" style="width: 250px;height: 30px;" class="form-control"></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;奖品总数&nbsp;: &nbsp;</label></td> 
					<td colspan="3"><input type="text" id="highAwardTotal_add" style="width: 250px;height: 30px;" class="form-control"></td>
         		</tr>
         		

     		    
     		    <tr></tr>
     		    <tr>
         			<td colspan="1"><label>中区间</label></td>         			    			
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;奖品名称&nbsp;: &nbsp;</label></td> 
					<td colspan="3">
						<!-- <input type="text" id="midAwardId_add" style="width: 250px;height: 30px;" class="form-control"> -->
						<select id="midAwardId_add">
							<c:forEach items="${niAwardList}" var="niAward">
								<option value="${niAward.awardId }">${niAward.awardName }</option>
							</c:forEach>
						</select>
					</td>
					
					<td><label>&nbsp;&nbsp;中奖概率&nbsp;: &nbsp;</label></td> 
					<td colspan="1"><input type="text" id="midRate_add" style="width: 250px;height: 30px;" class="form-control"></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;起始值&nbsp;: &nbsp;</label></td> 
					<td colspan="3"><input type="text" id="midBegin_add" style="width: 250px;height: 30px;" class="form-control"></td>
					
					<td><label>&nbsp;&nbsp;结束值&nbsp;: &nbsp;</label></td> 
					<td colspan="1"><input type="text" id="midEnd_add" style="width: 250px;height: 30px;" class="form-control"></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;奖品总数&nbsp;: &nbsp;</label></td> 
					<td colspan="3"><input type="text" id="midAwardTotal_add" style="width: 250px;height: 30px;" class="form-control"></td>
         		</tr>
         		
         		
         	    <tr></tr>
         		<tr>
         			<td colspan="1"><label>低区间</label></td>         			    			
         		</tr>
         		         		<tr>
         			<td><label>&nbsp;&nbsp;奖品名称&nbsp;: &nbsp;</label></td> 
					<td colspan="3">
						<!-- <input type="text" id="lowAwardId_add" style="width: 250px;height: 30px;" class="form-control"> -->
						<select id="lowAwardId_add">
							<c:forEach items="${niAwardList}" var="niAward">
								<option value="${niAward.awardId }">${niAward.awardName }</option>
							</c:forEach>
						</select>
					</td>
					
					<td><label>&nbsp;&nbsp;中奖概率&nbsp;: &nbsp;</label></td> 
					<td colspan="1"><input type="text" id="lowRate_add" style="width: 250px;height: 30px;" class="form-control"></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;起始值&nbsp;: &nbsp;</label></td> 
					<td colspan="3"><input type="text" id="lowBegin_add" style="width: 250px;height: 30px;" class="form-control"></td>
					
					<td><label>&nbsp;&nbsp;结束值&nbsp;: &nbsp;</label></td> 
					<td colspan="1"><input type="text" id="lowEnd_add" style="width: 250px;height: 30px;" class="form-control"></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;奖品总数&nbsp;: &nbsp;</label></td> 
					<td colspan="3"><input type="text" id="lowAwardTotal_add" style="width: 250px;height: 30px;" class="form-control"></td>
         		</tr>

         		<tr>
         			<td><label>&nbsp;&nbsp;备注&nbsp;: &nbsp;</label></td> 
					<td colspan="3"><input type="text" id="comment_add" style="width: 450px;height: 30px;" class="form-control"></td>
         		</tr>		       		
         	</table>
           <div style="color: red;text-align:center;font-size: 22px;" id="errorMsg"></div> 
	         
           <div class="box-footer">
              <button type="button" onclick="lotteryPreferAdd_Save();" class="btn btn-primary">保存</button>
              <button type="button" onclick="cancelAdd();" class="btn btn-primary">取消</button>
           </div>
           
           
         </form>
                
       </div>


 <script src="${ctx}/js/validate.js"></script>
<script>
		
//添加倾向抽奖规则
function lotteryPreferAdd_Save(){
	var errorMsg = $('#errorMsg');
	var ruleName_add = $('#ruleName_add').val();
	
	var histAwardId_add = $('#histAwardId_add').val();
	var highRate_add = $('#highRate_add').val();   //高区间中奖概率
	var highBegin_add =$('#highBegin_add').val();
	var highEnd_add = $('#highEnd_add').val();
	var highAwardTotal_add = $('#highAwardTotal_add').val(); //1等奖奖品总数
	
	var midAwardId_add = $('#midAwardId_add').val();
	var midRate_add = $('#midRate_add').val();  //中奖概率
	var midBegin_add = $('#midBegin_add').val();
	var midEnd_add = $('#midEnd_add').val();
	var midAwardTotal_add = $('#midAwardTotal_add').val();
	
	var lowAwardId_add = $('#lowAwardId_add').val();
	var lowRate_add = $('#lowRate_add').val();   //中奖概率
	var lowBegin_add = $('#lowBegin_add').val();
	var lowEnd_add = $('#lowEnd_add').val();
	var lowAwardTotal_add = $('#lowAwardTotal_add').val(); //奖品总数
	
	var comment_add = $('#comment_add').val();
	
	if(ruleName_add == ""){
		errorMsg.html("请录入规则名称");
		return false;
	}
	
	else if(histAwardId_add == ""){
		errorMsg.html("高区间奖品id未录入");
		return false;
	}else if(!checkNumber(histAwardId_add)){
		errorMsg.html("高区间奖品id格式录入有误");
		return false;
	}
	
	else if(highRate_add == ""){
		errorMsg.html("高区间中奖概率未录入");
		return false;
	}else if(parseInt(highRate_add) > 1 ){
		errorMsg.html("高区间中奖概率不能大于1");
		return false;
	}
	
	else if(highBegin_add == ""){
		errorMsg.html("高区间起始值未录入");
		return false;
	}else if(!checkNumber(highBegin_add)){
		errorMsg.html("高区间起始值格式录入有误");
		return false;
	}
	
	else if(highEnd_add == ""){
		errorMsg.html("高区间结束值未录入");
		return false;
	}else if(!checkNumber(highEnd_add)){
		errorMsg.html("高区间结束值格式录入有误");
		return false;
	}
	
	else if(parseInt(highBegin_add) >= parseInt(highEnd_add)){
		errorMsg.html("高区间起始值不能大于或等于结束值");
		return false;
	}
	
	else if(highAwardTotal_add == ""){
		errorMsg.html("高区间奖品总数未录入");
		return false;
	}else if(!checkNumber(highAwardTotal_add)){
		errorMsg.html("高区间奖品总数格式录入有误");
		return false;
	}
	//-----------------中区间-----
	else if(midAwardId_add == ""){
		errorMsg.html("中区间奖品id未录入");
		return false;
	}else if(!checkNumber(midAwardId_add)){
		errorMsg.html("中区间奖品id格式录入有误");
		return false;
	}
	
	else if(midRate_add == ""){
		errorMsg.html("中区间中奖概率未录入");
		return false;
	}else if(parseInt(midRate_add) >1 ){
		errorMsg.html("中区间中奖概率不能大于1");
		return false;
	}
	
	else if(midBegin_add == ""){
		errorMsg.html("中区间起始值未录入");
		return false;
	}else if(!checkNumber(midBegin_add)){
		errorMsg.html("中区间起始值格式录入有误");
		return false;
	}
	else if(midEnd_add == ""){
		errorMsg.html("中区间结束值未录入");
		return false;
	}else if(!checkNumber(midEnd_add)){
		errorMsg.html("中区间结束值格式录入有误");
		return false;
	}
	
	else if(parseInt(midBegin_add) >= parseInt(midEnd_add)){
		errorMsg.html("中区间起始值不能大于或等于结束值");
		return false;
	}
	
	else if(midAwardTotal_add == ""){
		errorMsg.html("中区间奖品总数未录入");
		return false;
	}else if(!checkNumber(midAwardTotal_add)){
		errorMsg.html("中区间奖品总数格式录入有误");
		return false;
	}
	
	//----------------地区间--
	else if(lowAwardId_add == ""){
		errorMsg.html("低区间奖品id未录入");
		return false;
	}else if(!checkNumber(lowAwardId_add)){
		errorMsg.html("低区间奖品id格式录入有误");
		return false;
	}
	
	else if(lowRate_add == ""){
		errorMsg.html("低区间中奖概率未录入");
		return false;
	}else if(parseInt(lowRate_add) >1 ){
		errorMsg.html("低区间中奖概率不能大于1");
		return false;
	}
	
	else if(lowBegin_add == ""){
		errorMsg.html("低区间起始值未录入");
		return false;
	}else if(!checkNumber(lowBegin_add)){
		errorMsg.html("低区间起始值格式录入有误");
		return false;
	}
	else if(lowEnd_add == ""){
		errorMsg.html("低区间结束值未录入");
		return false;
	}else if(!checkNumber(lowEnd_add)){
		errorMsg.html("低区间结束值格式录入有误");
		return false;
	}
	else if(parseInt(lowBegin_add) >= parseInt(lowEnd_add)){
		errorMsg.html("低区间起始值不能大于或等于结束值");
		return false;
	}
	
	else if(lowAwardTotal_add == ""){
		errorMsg.html("低区间奖品总数未录入");
		return false;
	}else if(!checkNumber(lowAwardTotal_add)){
		errorMsg.html("低区间奖品总数格式录入有误");
		return false;
	}
	
	else if(parseInt(lowEnd_add) >= parseInt(midBegin_add)){
		errorMsg.html("低区间结束值不能大于等于中区间起始值");
		return false;
	}
	else if(parseInt(midEnd_add) >= parseInt(highBegin_add)){
		errorMsg.html("中区间结束值不能大于等于高区间起始值");
		return false;
	}
	
	$.ajax({
		url : "${ctx}/platform/lotteryPreferPageAdd.do",
		type : "post",
		dataType : "json",
		data : {
			ruleName_add : ruleName_add,
			
			histAwardId_add : histAwardId_add,
			highRate_add : highRate_add,
			highBegin_add : highBegin_add,
			highEnd_add : highEnd_add,
			highAwardTotal_add : highAwardTotal_add,
			
			midAwardId_add : midAwardId_add,
			midRate_add : midRate_add,
			midBegin_add : midBegin_add,
			midEnd_add : midEnd_add,
			midAwardTotal_add : midAwardTotal_add,
			
			lowAwardId_add : lowAwardId_add,
			lowRate_add : lowRate_add,
			lowBegin_add : lowBegin_add,
			lowEnd_add : lowEnd_add,
			lowAwardTotal_add : lowAwardTotal_add, 
			comment_add : comment_add
		},
		success : function(data) {
			if(!data.success && data.errorCode == '001'){
				errorMsg.html(data.error);
				return false;
			}
			//$('#lotteryPreferAdd_Modal').modal('hide');
			showMessageDialog(data.msg,function(){
				if(data.success){
					window.location.href = "${ctx}/platform/lotteryPreferPageList.do";
				}
			});
		},
		error : function(){
			showMessageDialog("网络异常");
		}
		
	});
	
}

</script>
</body>
</html>


