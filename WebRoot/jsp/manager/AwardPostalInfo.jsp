<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
	 <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>奖品发放信息</title>
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
         	<table style="border-spacing: 10px;border-collapse: separate;" >
         		<tr>
         			<td col width="80"><label>&nbsp;&nbsp;奖品名称&nbsp;: &nbsp;</label></td> 
         			<td width="400" colspan="3">${postalInfo.awardName}</td>         			
         		</tr>
         		<tr>
         			<td col width="80"><label>&nbsp;&nbsp;奖品ID&nbsp;: &nbsp;</label></td> 
         			<td width="400" colspan="3">${postalInfo.awardId}</td>         			
         		</tr>
         		<tr>
         			<td col width="80"><label>&nbsp;&nbsp;收件人&nbsp;: &nbsp;</label></td> 
         			<td width="400" colspan="3">${postalInfo.recipientName}</td>         			
         		</tr>
          		<tr>
         			<td col width="80"><label>&nbsp;&nbsp;联系电话&nbsp;: &nbsp;</label></td> 
         			<td width="400" colspan="3">${postalInfo.recipientPhone}</td>         			
         		</tr>        			     		       		
           		<tr>
         			<td col width="80"><label>&nbsp;&nbsp;省份&nbsp;: &nbsp;</label></td> 
         			<td width="400" colspan="3">${postalInfo.province}</td>         			
         		</tr> 
         		<tr>
         			<td col width="80"><label>&nbsp;&nbsp;邮寄地址&nbsp;: &nbsp;</label></td> 
         			<td width="400" colspan="3">${postalInfo.mailAddress}</td>         			
         		</tr>
         		<tr>
         			<td col width="80"><label>&nbsp;&nbsp;邮编&nbsp;: &nbsp;</label></td> 
         			<td width="400" colspan="3">${postalInfo.postCode}</td>         			
         		</tr>         		
         	</table>
	         
           <div class="box-footer">
              <button type="button" onclick="closePage();" class="btn btn-primary">关闭</button>
           </div>
           
         </form>
                
       </div>

<!-- /.content -->


<!-- jQuery 2.2.0 -->
<script src="${pageContext.request.contextPath}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>

<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<!-- <script>$.widget.bridge('uibutton', $.ui.button);</script> -->

<script>

//关闭页面
function closePage(){
	window.location.href = "${ctx}/platform/awardPayList.do";
}
</script>
</body>

</html>


