<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>定时发布时间</title>
  <link rel="stylesheet" href="${ctx}/BS/bootstrap/css/bootstrap-datetimepicker.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
 <div class="box box-primary">
         <!-- form start -->
         <form id="pageListForm">
         	<table style="border-spacing: 10px;border-collapse: separate;">
			<tr>
			<tr>
			    <tr>
         			<td><label>&nbsp;&nbsp;时间设置范例&nbsp;:&nbsp;</label></td> 
					<td colspan="3"><label>采用24小时的时间制  </label></td> 
         		</tr>
				 <tr>
         			<td><label>&nbsp;&nbsp;&nbsp;&nbsp;</label></td> 
					<td colspan="3"><label>2017-01-01 天 05 时 05 分     </label></td> 
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;&nbsp;&nbsp;</label></td> 
					<td colspan="3"><label>2017-15-15 天 15 时 35 分     </label></td> 
         		</tr>
         		
         		<tr>
		         	<td><label>&nbsp;&nbsp;选择发布时间&nbsp;: &nbsp;</label></td> 
		         	<td>
		         		<input type="text" value="" style="width: 140px;height: 30px" id="datetimepicker"> 天</td>
		         	<td><input type="text" id="timer_shi" style="width: 80px;height: 30px"> 时</td>
		         	<td><input type="text" id="timer_fen" style="width: 80px;height: 30px"> 分
		         		<input type="hidden" id="deliveryId" value="${deliveryId_Str }" >
		         		<input type="hidden" id="qnType" value="${qnType_Str }">
		         		<input type="hidden" id="superListCategory" value="${superListCategory_Str }">
		         		</td>
         		</tr>
        		
         	</table>
         	
	         
           <div class="box-footer">
             &nbsp;<button type="button" onclick="addTimerSuperList();" class="btn btn-primary">确定</button>
           </div>
           
         </form>
       </div>
  
  <script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
  <script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
  <script src="${ctx}/BS/bootstrap/js/bootstrap-datetimepicker.js"></script>
  <script src="${ctx}/js/validate.js"></script>
  <script type="text/javascript">
 	$('#datetimepicker').datepicker({
	     autoclose: true,
	     format: 'yyyy-mm-dd'
	   });
	   
 	//保存时间
 	function addTimerSuperList(){
 		var timer_date = $('#datetimepicker').val();
 		var timer_shi = $('#timer_shi').val();
 		var timer_fen = $('#timer_fen').val();
 		var superListCategory = $('#superListCategory').val();
 		
 		var deliveryId = $('#deliveryId').val();
 		var qnType = $('#qnType').val();
 		if(timer_date == ""){
 			showMessageDialog("请选择日期时间");
 			return false;
 		}else if(timer_shi == ""){
 			showMessageDialog("未录入小时");
 			return false;
 		}else if(timer_fen == ""){
 			showMessageDialog("未录入分钟");
 			return false;
 		}else if(!checkNumber(timer_shi)){
 			showMessageDialog("小时录入格式不正确");
 			return false;
 		}else if(parseInt(timer_shi) > 23 || parseInt(timer_shi) < 0 ){
 			showMessageDialog("小时录入格式不正确");
 			return false;
 		}else if(!checkNumber(timer_fen)){
 			showMessageDialog("分钟录入格式不正确");
 			return false;
 		}else if(parseInt(timer_fen) > 59 || parseInt(timer_fen) < 0 ){
 			showMessageDialog("分钟录入格式不正确");
 			return false;
 		}
 		
 		var timer_dateStr = timer_date+"-"+timer_shi+"-"+timer_fen+"-"+"00";
 		
 		$.ajax({
 			url : "${ctx}/platform/superListTimerSave.do",
 			data:{
 				deliveryId : deliveryId,
 				qnType : qnType,
 				superListCategory : superListCategory,
 				timer_dateStr : timer_dateStr
 			},
 			type:'post',
 			dataType:'json',
 			success:function(data){
 				//showMessageDialog(data.msg);
 				var superListCategory = data.superListCategory;
 				showConfirmDialog_timeSuperList(data.msg, function(check){
 					if(check){
 						window.location.href = "${ctx}/platform/loadSuperList.do?superListCategory_Str_GO="+superListCategory;
 					}
 				});
 			}
 			
 		});
 		
 	}
 	
 	
  </script>
</body>

</html>