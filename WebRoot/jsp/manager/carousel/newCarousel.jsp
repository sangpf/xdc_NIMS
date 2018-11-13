<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>轮播图编辑</title>
</head>
<body class="hold-transition skin-blue ">
       <div class="box box-primary">
         <div class="box-header with-border">
         </div>
         <!-- form start -->
         	<table style="border-spacing: 10px;border-collapse: separate;">
         		<tr>
         			<td><label>&nbsp;&nbsp;选择广告&nbsp;: &nbsp;</label></td> 
         			<td colspan="2">
         				<input type="hidden" id="carouselId" value="${niCarousel.carouselId }"> 
         				<select id="adId">
         					<c:forEach items="${adInfoList}" var="adInfo">
	         					<option value="${adInfo.adId }" <c:if test="${adInfo.adId == niCarousel.adId}">selected="selected"</c:if>> ${adInfo.adTitle }</option>
	         					
         					</c:forEach>
         				</select>
         			</td>

         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;选择渠道&nbsp;: &nbsp;</label></td> 
         			<td colspan="2">
         				<select id="channelId">
         					<c:forEach items="${listChannel}" var="channel">
	         					<option value="${channel.id }" <c:if test="${channel.id == niCarousel.channelId}">selected="selected"</c:if>> 
	         						<c:if test="${channel.id == 1}">玩校</c:if>
	         						<c:if test="${channel.id == 2}">微信</c:if>
	         					</option>
	         					
         					</c:forEach>
         				</select>
         			</td>
         		</tr>
         		
         	</table>
	        <div id="errorMsg" style="color: red;"></div>
           <div class="box-footer">
             <input type="button" id="saveBtn" onclick="saveCarousel()" class="btn btn-primary" value="保存"> 
           </div>
           
       </div>
<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/js/validate.js"></script> 
<script type="text/javascript">
 	
	//保存推文
	function saveCarousel(){
		var carouselId = $.trim($("#carouselId").val());
		var adId = $.trim($("#adId").val());
		var channelId = $.trim($("#channelId").val());
		  
		    $.ajax({  
		         url: "${ctx}/platform/save_edit_Carorse.do",
		         type: 'POST',  
		         data: {
		        	 carouselId : carouselId,
		        	 adId : adId,
		        	 channelId : channelId
		         },
		         success: function (data) {
						showMessageDialog(data.msg);
		         },  
		         error: function (data) {
		             showMessageDialog("网络异常");  
		         } 
		    });
		  
	}
	
</script>
</body>
</html>
