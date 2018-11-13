<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
	 <meta charset="utf-8">
  <title>添加抽奖组合</title>
</head>

<body class="hold-transition skin-blue sidebar-mini">

       <!-- Main content -->
        <div class="box box-primary">

         <!-- form start -->
         <form>
         	<table  style="border-spacing: 10px;border-collapse: separate;" >
         		<tr>
         			<td col width="80" colspan="2"><label>&nbsp;&nbsp;抽奖名称&nbsp;: &nbsp;</label></td> 
         			<td colspan="3"><input id="lotteryName" value="${lottery.lotteryName}" type="text" maxlength="28" style="width: 250px;height: 30px;" class="form-control" placeholder="请输入抽奖组合名称"></td>
         		</tr>
         		
         		
         		<!-- 一等奖 -->
         		
         		<tr>
         			<td colspan="1">&nbsp;&nbsp;<input id="award1" name="award1" type="radio" checked style="width: 10;"></td>     
         			<td colspan="1"><label>一等奖</label></td>         			    			
         		</tr>
         		<tr>
         			<td col width="80" colspan="2"><label>&nbsp;&nbsp;奖品名称&nbsp;: &nbsp;</label></td> 
					 <td colspan="3"><select class="form-control select-sm" id="award1Id" style="width:250px;" name = "award1Id">
                      <option value=""></option>
                     <c:forEach items="${awardList}" var="awardItem">
                    <option value="${awardItem.awardId}">${awardItem.awardName}</option>
                     </c:forEach>
                     </td>
					<%-- <td colspan="3"><input id="award1Id"  onblur="searchAward1()" value="${lottery.award1Id}" type="text" maxlength="28" style="width: 250px;height: 30px;" class="form-control" placeholder="请输入奖品Id"></td>     --%>     			
         		</tr>
<%--           		<tr>
         			<td colspan="2">&nbsp;</td>         			
         			<td col width="40px" colspan="1" id="award1Name">未查询到奖品信息</td>
         			<td col width="20px" colspan="1" id="award1Status">
         				<c:if test="${award1.status == 1}">&nbsp;无效</c:if>
                        <c:if test="${award1.status == 2}">&nbsp;有效</c:if>
                        <c:if test="${award1.status == 3}">&nbsp;失效</c:if>
                    </td>
         			<td col width="20px"></td>
         		</tr> --%>
         		<tr>
         			<td col width="80" colspan="2"><label>&nbsp;&nbsp;设置数值&nbsp;: &nbsp;</label></td> 
					<td colspan="3"><input id="award1Rate" value="${lottery.award1Rate}" type="text" maxlength="28" style="width: 250px;height: 30px;" class="form-control" placeholder="请输入中奖数量或概率"></td>         			
         		</tr>
         		<tr>
         			<td colspan="2"></td>
         			<td colspan="4" width="200px"><font  color="red" >*大于等于1表示中奖数目，小于1是概率，如0.05，下同。</font></td>
         		</tr>

     		    
     		    <!-- 二等奖 -->
     		    
     		    <tr>
         			<td colspan="1">&nbsp;&nbsp; <input id="award2" name="award2" type="radio" style="width: 10;"  onclick="setAward2();"> </td>     
         			<td colspan="1"><label>二等奖</label></td>         			    			
         		</tr>
         		<tr>
         			<td col width="80" colspan="2"><label>&nbsp;&nbsp;奖品名称&nbsp;: &nbsp;</label></td> 
         			<td colspan="3"><select class="form-control select-sm" id="award2Id" style="width:250px;" name = "award2Id" disabled="disabled">
                     <option value=""></option>
                     <c:forEach items="${awardList}" var="awardItem">                    
                    <option value="${awardItem.awardId}">${awardItem.awardName}</option>
                     </c:forEach>
                     </td>
					<%-- <td colspan="3"><input id="award2Id" onblur="searchAward2()" value="${lottery.award1Id}" type="text" maxlength="28" style="width: 250px;height: 30px;" class="form-control" placeholder="请输入奖品Id" disabled="disabled"></td>    --%>      			
         		</tr>
<%--           		<tr>
         			<td colspan="2">&nbsp;</td>         			
         			<td col width="40px" colspan="1" id="award2Name">未查询到奖品信息</td>
         			<td col width="20px" colspan="1">
         				<c:if test="${award2.status == 1}">&nbsp;无效</c:if>
                        <c:if test="${award2.status == 2}">&nbsp;有效</c:if>
                        <c:if test="${award2.status == 3}">&nbsp;失效</c:if>
                    </td>
         			<td col width="20px"></td>
         		</tr>  --%>       		
         		<tr>
         			<td col width="80" colspan="2"><label>&nbsp;&nbsp;设置数值&nbsp;: &nbsp;</label></td> 
					<td colspan="3"><input id="award2Rate" value="${lottery.award2Rate}" type="text" maxlength="28" style="width: 250px;height: 30px;" class="form-control" placeholder="请输入中奖数量或概率" disabled="disabled"></td>         			
         		</tr>

         		
         		
         	  <!-- 三等奖  -->
         		<tr>
         			<td colspan="1">&nbsp;&nbsp;<input id="award3" name="award3" type="radio" style="width: 10;"  onclick="setAward3();" ></td>     
         			<td colspan="1"><label>三等奖</label></td>         			    			
         		</tr>
         		<tr>
         			<td col width="80" colspan="2"><label>&nbsp;&nbsp;奖品ID&nbsp;: &nbsp;</label></td> 
					<td colspan="3"><select class="form-control select-sm" id="award3Id" style="width:250px;" name = "award3Id" disabled="disabled">
                     <option value=""></option>
                     <c:forEach items="${awardList}" var="awardItem">                  
                    <option value="${awardItem.awardId}">${awardItem.awardName}</option>
                     </c:forEach>
                     </td>
					<%-- <td colspan="3"><input id="award3Id" onblur="searchAward3()" value="${lottery.award3Id}" type="text" maxlength="28" style="width: 250px;height: 30px;" class="form-control" placeholder="请输入奖品Id" disabled="disabled"></td>       --%>   			
         		</tr>
<%--          		<tr>
         			<td colspan="2">&nbsp;</td>         			
         			<td col width="40px" colspan="1"id="award3Name">未查询到奖品信息</td>
         			<td col width="20px" colspan="1">
         				<c:if test="${award3.status == 1}">&nbsp;无效</c:if>
                        <c:if test="${award3.status == 2}">&nbsp;有效</c:if>
                        <c:if test="${award3.status == 3}">&nbsp;失效</c:if>
                    </td>
         			<td col width="20px"></td>
         		</tr>  --%>
         		<tr>
         			<td col width="80" colspan="2"><label>&nbsp;&nbsp;设置数值&nbsp;: &nbsp;</label></td> 
					<td colspan="3"><input id="award3Rate" value="${lottery.award3Rate}" type="text" maxlength="28" style="width: 250px;height: 30px;" class="form-control" placeholder="请输入中奖数量或概率" disabled="disabled"></td>         			
         		</tr>

         		<tr>
         			<td colspan="2"><label>&nbsp;&nbsp;抽奖说明&nbsp;:&nbsp;</label></td> 
         			<td colspan="5"><textarea id="comment" class="form-control" style="width: 500px;" rows="3" placeholder="Enter ..."></textarea></td>
         		</tr>		       		
         	</table>
	         
           <div class="box-footer">
           <!--   <button type="button" onclick="searchAward();" class="btn btn-primary">查询</button>  -->
              <button type="button" onclick="addlottery();" class="btn btn-primary">保存</button>
              <button type="button" onclick="cancelAdd();" class="btn btn-primary">取消</button>
           </div>
           
         </form>
                
       </div>

<!-- /.content -->


<!-- jQuery 2.2.0 -->
<script src="${pageContext.request.contextPath}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
 <script src="${ctx}/js/validate.js"></script>

<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<!-- <script>$.widget.bridge('uibutton', $.ui.button);</script> -->

<script>
//设置二等奖
function setAward2(){

	$('#award2Id').attr("disabled",false);
	$('#award2Rate').attr("disabled",false);
}

//设置三等奖
function setAward3(){
	//alert("hello");
	$('#award3Id').attr("disabled",false);
	$('#award3Rate').attr("disabled",false);
	
}

//添加奖品
 function addlottery(){
	  var lotteryName = $('#lotteryName').val();   //奖品名称
	  var award1Id = $('#award1Id').val();   //一等奖ID	  	 
	  var award1Rate = $('#award1Rate').val(); //一等奖名称	  
	  var award2Id = $('#award2Id').val();   //二等奖ID	 
	  var award2Rate = $('#award2Rate').val(); //二等奖名称
	  var award3Id = $('#award3Id').val();   //三等奖ID	  
	  var award3Rate = $('#award3Rate').val(); //三等奖名称 
	  var comment = $('#comment').val(); //
	  
	  if(lotteryName==''){
		  showMessageDialog("请输入抽奖组合名称");
		  return;
	  }
	  if(award1Id=='' | award1Rate==''){
		  showMessageDialog("请设置一等奖的ID或数量");
		  return;
	  }
	  

	  $.ajax({
		  url:'${ctx}/platform/addLottery.do',
		  type:'post',
		  dataType:'json',
		  data:{			 
 			  lotteryName : lotteryName,
			  award1Id : award1Id,
			  award1Rate : award1Rate,
 			  award2Rate:award2Rate,
 			  award2Id :award2Id,
			  award3Id : award3Id,
			  award3Rate : award3Rate, 
			  comment : comment			 
		  },
		  success:function(data){
			  showMessageDialog(data.msg,function(){
				  if(data.success){
					  window.location.href = "${ctx}/platform/lotteryList.do";
				  } 
			  })
		  }
		  
	  });
	  
}

//搜索一等奖奖品信息
  function searchAward1(){
	var awardId = document.getElementById("award1Id").value;
	  $.ajax({
		  url:'${ctx}/platform/searchAward.do',
		  type:'post',
		  dataType:'json',
		  data:{			 
			  awardId : awardId	 
		  },
		  success:function(data){	
			 console.log(123)
				document.getElementById("award1Id").value=data.awardId;
				document.getElementById("award1Name").innerHTML=data.awardName;			  
		  },
		  error: function(data) {
			  document.getElementById("award1Name").innerHTML="未查询到奖品信息"; 
		  }
	  });

}  

//搜索二等奖奖品信息
  function searchAward2(){
	var awardId = document.getElementById("award2Id").value;
	  $.ajax({
		  url:'${ctx}/platform/searchAward.do',
		  type:'post',
		  dataType:'json',
		  data:{			 
			  awardId : awardId	 
		  },
		  success:function(data){		
			  document.getElementById("award2Id").value=data.awardId;
			  document.getElementById("award2Name").innerHTML=data.awardName;
		  },
		  error: function(data) {
			  document.getElementById("award2Name").innerHTML="未查询到奖品信息"; 
		  }		  
	  });

} 

//搜索三等奖奖品信息
  function searchAward3(){
	var awardId = document.getElementById("award3Id").value;
	  $.ajax({
		  url:'${ctx}/platform/searchAward.do',
		  type:'post',
		  dataType:'json',
		  data:{			 
			  awardId : awardId	 
		  },
		  success:function(data){		
			  document.getElementById("award3Id").value=data.awardId;
			  document.getElementById("award3Name").innerHTML=data.awardName;
		  },
		  error: function(data){
			  document.getElementById("award3Name").innerHTML="未查询到奖品信息"; 
		  }
	  });

} 
//取消添加 
function cancelAdd(){
		showConfirmDialog(
			"放弃添加抽奖组合？",
			function(check){
				if(check){
					window.location.href = "${ctx}/platform/lotteryList.do";
				}
			})
	}
//判断返回对象是否为空
 function isEmptyObject(obj){ 
		  for ( var name in obj ) { 
		  return false; 
		  } 
		  return true; 
		  } 
</script>
</body>

</html>


