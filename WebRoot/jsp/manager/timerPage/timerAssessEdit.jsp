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
         <form id="pageListForm" method="post" action="${ctx}/platform/uploadPicture.do" enctype="multipart/form-data">
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
		         		<input type="hidden" id="deliveryId" value="${deliveryId_arr }" >
		         		<input type="hidden" id="assessListCategory" value="${assessListCategory}" >
		         		</td>
         		</tr>
        		
         	</table>
         	
	         
           <div class="box-footer">
             &nbsp;<button type="button" onclick="addAssessTimer();" class="btn btn-primary">确定</button>
           </div>
           
         </form>
       </div>
  
  <script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
  <script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
  <script src="${ctx}/BS/bootstrap/js/bootstrap-datetimepicker.js"></script>
  <script src="${ctx}/js/validate.js"></script>
  <script type="text/javascript">
 		//读取cookie
        function getCookie(name)
	    {
	        var regExp = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	        var arr = document.cookie.match(regExp);
	        if (arr == null) {
	            return null;
	        }
	        return unescape(arr[2]);
	    }
	    var selectedIndex = getCookie("assessListCategory");

	    //根据cookie确定当前测评类别
		var assessListCategory = "funnyAssessQnList";
		switch(selectedIndex){
			case '0': assessListCategory="funnyAssessQnList";break;
			case '1': assessListCategory="professialAssessQnList";break;
		}
 	$('#datetimepicker').datepicker({
	     autoclose: true,
	     format: 'yyyy-mm-dd'
	   });
	   
 	//保存时间
 	function addAssessTimer(){
 		var timer_date = $('#datetimepicker').val();
 		var timer_shi = $('#timer_shi').val();
 		var timer_fen = $('#timer_fen').val();
 		
 		var deliveryId = $('#deliveryId').val();
 		var assessListCategory = $('#assessListCategory').val();
 		
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
 			url : "${ctx}/platform/saveAssessTimer.do",
 			data:{
 				deliveryId : deliveryId,
 				timer_dateStr : timer_dateStr,
 				assessListCategory : assessListCategory
 			},
 			type:'post',
 			dataType:'json',
 			success:function(data){
 				showMessageDialog(data.msg);
 				window.location.href = "${ctx}/platform/loadAssessList.do?assessListCategory="+assessListCategory;
 			}
 			
 		});
 		
 	}
 	
 	
  </script>
</body>

</html>