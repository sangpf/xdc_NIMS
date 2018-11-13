<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
	 <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>编辑抽奖组合</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${ctx}/BS/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css">
  <link rel="stylesheet" href="${ctx }/BS/plugins/datatables/dataTables.bootstrap.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <link rel="stylesheet" href="${ctx }/BS/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="${ctx }/BS/dist/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="${ctx }/BS/plugins/iCheck/flat/blue.css">
  <link rel="stylesheet" href="${ctx }/BS/plugins/morris/morris.css">
  <link rel="stylesheet" href="${ctx }/BS/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
  <link rel="stylesheet" href="${ctx }/BS/plugins/datepicker/datepicker3.css">
  <link rel="stylesheet" href="${ctx }/BS/plugins/daterangepicker/daterangepicker-bs3.css">
  <link rel="stylesheet" href="${ctx }/BS/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <style>
        .mb10 {
            margin-bottom: 10px;
        }

        .select-label {
            float: left;
            line-height: 30px;
            font-size: 12px;
            margin-right: 10px;
        }

        .select-sm {
            height: 30px;
            font-size: 12px;
        }

        .ft-12 {
            font-size: 12px;
        }

        .pd-15 {
            padding: 0 15px;
        }

        .button-group button {
            width: 100px;
            margin: auto 10px;
        }
    </style>
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
         		<tr>
         			<td col width="80" colspan="2"><label>&nbsp;&nbsp;抽奖ID&nbsp;: &nbsp;</label></td> 
         			<td colspan="3"><input id="lotteryId" value="${lottery.lotteryId}" type="text" maxlength="28" style="width: 250px;height: 30px;" class="form-control" disabled="disabled"></td>
         		</tr>
         		
         		<!-- 一等奖 -->
         		
         		<tr>
         			<td colspan="1">&nbsp;&nbsp;</td>     
         			<td colspan="1"><label>一等奖</label></td>         			    			
         		</tr>
         		<tr>
         			<td col width="80" colspan="2"><label>&nbsp;&nbsp;奖品ID&nbsp;: &nbsp;</label></td> 
					<td colspan="3"><input id="award1Id"  onblur="searchAward1()" value="${lottery.award1Id}" type="text" maxlength="28" style="width: 250px;height: 30px;" class="form-control" placeholder="请输入奖品Id"></td>         			
         		</tr>
          		<tr>
         			<td colspan="2">&nbsp;</td>         			
         			<td col width="40px" colspan="1" id="award1Name"></td>
         			<td col width="20px" colspan="1" id="award1Status">
         				<c:if test="${award1.status == 1}">&nbsp;无效</c:if>
                        <c:if test="${award1.status == 2}">&nbsp;有效</c:if>
                        <c:if test="${award1.status == 3}">&nbsp;失效</c:if>
                    </td>
         			<td col width="20px">库存</td>
         		</tr>
         		<tr>
         			<td col width="80" colspan="2"><label>&nbsp;&nbsp;设置数值&nbsp;: &nbsp;</label></td> 
					<td colspan="3"><input id="award1Rate" value="${lottery.award1Rate}" type="text" maxlength="28" style="width: 250px;height: 30px;" class="form-control" placeholder="请输入中奖数量或概率"></td>         			
         		</tr>

     		    
     		    <!-- 二等奖 -->
     		    
     		    <tr>
         			<td colspan="1">&nbsp;&nbsp;</td>     
         			<td colspan="1"><label>二等奖</label></td>         			    			
         		</tr>
         		<tr>
         			<td col width="80" colspan="2"><label>&nbsp;&nbsp;奖品ID&nbsp;: &nbsp;</label></td> 
					<td colspan="3"><input id="award2Id" onblur="searchAward2()" value="${lottery.award2Id}" type="text" maxlength="28" style="width: 250px;height: 30px;" class="form-control" placeholder="请输入奖品Id" ></td>         			
         		</tr>
          		<tr>
         			<td colspan="2">&nbsp;</td>         			
         			<td col width="40px" colspan="1" id="award2Name"></td>
         			<td col width="20px" colspan="1">
         				<c:if test="${award2.status == 1}">&nbsp;无效</c:if>
                        <c:if test="${award2.status == 2}">&nbsp;有效</c:if>
                        <c:if test="${award2.status == 3}">&nbsp;失效</c:if>
                    </td>
         			<td col width="20px">库存</td>
         		</tr>        		
         		<tr>
         			<td col width="80" colspan="2"><label>&nbsp;&nbsp;设置数值&nbsp;: &nbsp;</label></td> 
					<td colspan="3"><input id="award2Rate" value="${lottery.award2Rate}" type="text" maxlength="28" style="width: 250px;height: 30px;" class="form-control" placeholder="请输入中奖数量或概率" ></td>         			
         		</tr>

         		
         		
         	  <!-- 三等奖  -->
         		<tr>
         			<td colspan="1">&nbsp;&nbsp;</td>     
         			<td colspan="1"><label>三等奖</label></td>         			    			
         		</tr>
         		<tr>
         			<td col width="80" colspan="2"><label>&nbsp;&nbsp;奖品ID&nbsp;: &nbsp;</label></td> 
					<td colspan="3"><input id="award3Id" onblur="searchAward3()" value="${lottery.award3Id}" type="text" maxlength="28" style="width: 250px;height: 30px;" class="form-control" placeholder="请输入奖品Id" ></td>         			
         		</tr>
         		<tr>
         			<td colspan="2">&nbsp;</td>         			
         			<td col width="40px" colspan="1"id="award3Name"></td>
         			<td col width="20px" colspan="1">
         				<c:if test="${award3.status == 1}">&nbsp;无效</c:if>
                        <c:if test="${award3.status == 2}">&nbsp;有效</c:if>
                        <c:if test="${award3.status == 3}">&nbsp;失效</c:if>
                    </td>
         			<td col width="20px">库存</td>
         		</tr> 
         		<tr>
         			<td col width="80" colspan="2"><label>&nbsp;&nbsp;设置数值&nbsp;: &nbsp;</label></td> 
					<td colspan="3"><input id="award3Rate" value="${lottery.award3Rate}" type="text" maxlength="28" style="width: 250px;height: 30px;" class="form-control" placeholder="请输入中奖数量或概率" ></td>         			
         		</tr>

         		<tr>
         			<td colspan="2"><label>&nbsp;&nbsp;抽奖说明&nbsp;: &nbsp;</label></td> 
         			<td colspan="5"><textarea id="comment" class="form-control" style="width: 500px;" rows="3" placeholder="Enter ..."></textarea></td>
         		</tr>		       		
         	</table>
	         
           <div class="box-footer">
           <!--   <button type="button" onclick="searchAward();" class="btn btn-primary">查询</button>  -->
              <button type="button" onclick="updatelottery();" class="btn btn-primary">保存</button>
              <button type="button" onclick="cancelAdd();" class="btn btn-primary">取消</button>
           </div>
           
         </form>
                
       </div>

<!-- /.content -->


<!-- jQuery 2.2.0 -->
<script src="${pageContext.request.contextPath}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>

<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<!-- <script>$.widget.bridge('uibutton', $.ui.button);</script> -->

<script>
//保存编辑信息
 function updatelottery(){
	  var lotteryName = $('#lotteryName').val();   //奖品名称
	  var lotteryId = $('#lotteryId').val(); //抽奖组合ID
	  var award1Id = $('#award1Id').val();   //一等奖ID	  	 
	  var award1Rate = $('#award1Rate').val(); //一等奖名称	  
	  var award2Id = $('#award2Id').val();   //二等奖ID	 
	  var award2Rate = $('#award2Rate').val(); //二等奖名称
	  var award3Id = $('#award3Id').val();   //三等奖ID	  
	  var award3Rate = $('#award3Rate').val(); //三等奖名称 
	  var comment = $('#comment').val(); //
	  

	  $.ajax({
		  url:'${ctx}/platform/updateLottery.do',
		  type:'post',
		  dataType:'json',
		  data:{			 
 			  lotteryName : lotteryName,
 			  lotteryId:lotteryId,
			  award1Id : award1Id,
			  award1Rate : award1Rate,
 			  award2Rate:award2Rate,
 			  award2Id :award2Id,
			  award3Id : award3Id,
			  award3Rate : award3Rate, 
			  comment : comment			 
		  },
		  success:function(data){
			  if(data.success){
				  alert(data.msg);
				  window.location.href = "${ctx}/platform/lotteryList.do";
			  }
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

</script>
</body>

</html>


