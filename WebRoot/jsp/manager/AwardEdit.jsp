<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
	 <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>编辑奖品</title>
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
         	<table style="border-spacing: 10px;border-collapse: separate;">
         		<tr>
         			<td col width="80"><label>&nbsp;&nbsp;货品ID&nbsp;: &nbsp;</label></td> 
         			<td colspan="4"><input id="awardPoolId" onblur="searchAwardPool()" value="${award.awardPoolId}" type="text" maxlength="28" style="width: 200px;height: 30px;" class="form-control" placeholder="请输入奖品类别ID"></td>
         		</tr>
         		<tr>
         			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td> 
         			<td col width="80">货品名称:</td>
         			<td col colspan="1" id="awardPoolName">${award.awardPoolName}</td>
         			<td col colspan="1" id="status">
         				<c:if test="${award.status == 1}">无效</c:if>
                        <c:if test="${award.status == 2}">有效</c:if>
                        <c:if test="${award.status == 3}">失效</c:if>
         			</td>
         			<td col colspan="1" id="awardType">${award.awardType}</td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;奖品ID&nbsp;:&nbsp;</label></td>
         			<td colspan="3"><input id="awardId"  value="${award.awardId}" type="text" maxlength="28" style="width: 200px;height: 30px;" class="form-control" disabled="disabled"></td>     			
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;奖品名称 &nbsp;:&nbsp;</label></td> 
         			<td colspan="3"><input id="awardName"  value="${award.awardName}" type="text" maxlength="28" style="width: 200px;height: 30px;" class="form-control" placeholder="请输入奖品名称"></td>     			         			
         		</tr> 
         		<tr>
         			<td><label>&nbsp;&nbsp;发放数量&nbsp;:&nbsp;</label></td>
         			<td colspan="3"><input id="awardNum"  value="${award.awardNum}" type="text" maxlength="28" style="width: 200px;height: 30px;" class="form-control" placeholder="请输入发放数量"></td>     			
         		</tr>
        		     		       		
         	</table>
	         
           <div class="box-footer">
             <button type="button" onclick="updateAward();" class="btn btn-primary">保存</button>
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

//根据货品ID（即奖品类别ID）查询货品信息
function searchAwardPool(){
	var awardPoolId = $('#awardPoolId').val();
	$.ajax({
		  url:'${ctx}/platform/searchAwardPool.do',
		  type:'post',
		  dataType:'json',
		  data:{			 
			  awardPoolId : awardPoolId	 
		  },
		  success:function(data){	
			  	console.log(123)
				document.getElementById("awardPoolName").innerHTML=data.awardPoolName;
			  	document.getElementById("awardType").innerHTML=data.awardType;
				if(data.status==1){
					document.getElementById("status").innerHTML="无效";	
				}
				else if(data.status==2){
					document.getElementById("status").innerHTML="有效";	
				}
				else {
					document.getElementById("status").innerHTML="失效";
				}
			  			  
		  },
		  error: function(data) {
			  document.getElementById("awardPoolName").innerHTML="未查询到奖品信息"; 
			  document.getElementById("status").innerHTML="";
			  document.getElementById("awardType").innerHTML="";
			  }
	  });
	//window.location.href = "${ctx}/platform/searchAwardPool.do?awardPoolId="+awardPoolId
}
//提交奖品编辑信息
function updateAward(){
	  var awardPoolId = $('#awardPoolId').val();  //奖品类别ID
	  var awardId = $('#awardId').val(); //奖品Id
	  var awardNum = $('#awardNum').val(); //发放数量
	  var awardName = $('#awardName').val();   //奖品名称
	  	  
	  $.ajax({
		  url:'${ctx}/platform/updateAward.do',
		  type:'post',
		  dataType:'json',
		  data:{
			  awardPoolId : awardPoolId,
			  awardNum : awardNum,
			  awardName : awardName,
			  awardId : awardId
		  },
		  success:function(data){
			  if(data.success){
				  alert(data.msg);
				  window.location.href = "${ctx}/platform/awardList.do";
			  }
		  }
		  
	  });
	  
}
</script>
</body>

</html>


