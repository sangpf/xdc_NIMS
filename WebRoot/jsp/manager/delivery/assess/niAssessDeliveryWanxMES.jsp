<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>测评问卷投放-添加信息</title>
  <link rel="stylesheet" href="${ctx}/BS/plugins/datepicker/datepicker3.css">
</head>
<body class="hold-transition skin-blue ">
 <!-- general form elements -->
       <div class="box box-primary">
 	       <table style="padding: 200px;margin: 10px;">
	           	<tr>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="uestionnairePage();" class="btn btn-block btn-primary" >问卷</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="messagePage();" class="btn btn-block btn-primary" style="background-color: #BC8F8F">信息</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="setupPage();" class="btn btn-block btn-primary">设置</button></td>
	   	            <td style="width: 100px;padding: 3px;">
	   	            	<button type="button" onclick="operatePage();" class="btn btn-block btn-primary">运营调整</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="choseTemplatePage();" class="btn btn-block btn-primary">选择模版</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="paySetPage();" class="btn btn-block btn-primary">付费测评</button></td>	   
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="resultPage();" class="btn btn-block btn-primary">结果页</button></td>	        			
	           	</tr>
           </table>
         <div class="box-header with-border" style="font-size: 20px">
         	   当前投放id为 : ${deliveryid}
         	   <c:if test="${deliveryid == '' || null || 'undefined'}">空</c:if>
         </div>
         <!-- form start -->
         <form id="pageListForm" method="post" enctype="multipart/form-data">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         		<tr>
         			<td><label>问卷名称</label></td>
         			<td>${niAssessQuestionnaire.aqnname }
         			</td>
         		</tr>
                <tr>
         			<td><label>投放标题</label></td> 
         			<td colspan="2"><input id="showTitle" name="showTitle" type="text" class="form-control" value="${niAssessDeliveryWanx.showTitle }"></td>
         		</tr>

  				<tr>
         			<td><label>标题标签&nbsp;: &nbsp;</label></td> 
         			<td><input id="titleTag" name="titleTag" type="text" class="form-control" value="${niAssessDeliveryWanx.titleTag }"></td>
         		</tr>         		       		
         		<tr>
         			<td><label>发布时间</label></td> 
       				<td><input type="text" value="${b_time}" class="form-control" id="datepicker" name="datepicker"></td> 
       				
         			<td style="float: right;"><label>截止时间&nbsp;&nbsp;&nbsp;</label></td> 
         			<td><input type="text" value="${e_time}" class="form-control" id="datepicker1" name="datepicker1"></td>
         		</tr>
         		<tr>
         			<td><label>计划采集份数</label></td> 
         			<td><input id="collectnum" name="collectnum" type="text"  class="form-control" value="${niAssessDeliveryWanx.collectNum}"></td>
         		</tr>
          		<tr>
         			<td><label>外简介</label></td> 
         			<td><textarea id="showdes" name="showdes" class="form-control">${niAssessDeliveryWanx.showDes}</textarea></td>
         		</tr> 
         		<tr>
         			<td><label>外标签</label></td>  
         			<td><input id="tag1Str" name="tag1Str" type="text" class="form-control" value="${niAssessDeliveryWanx.tag1Str}" placeholder="标签1 ..."></td>
					<td><input id="tag2Str" name="tag2Str" type="text" class="form-control" value="${niAssessDeliveryWanx.tag2Str}" placeholder="标签2 ..."></td>
					<td><input id="tag3Str" name="tag3Str" type="text" class="form-control" value="${niAssessDeliveryWanx.tag3Str}" placeholder="标签3 ..."></td>
         		</tr>
         		<tr>
         			<td><label></label></td> 
					<td><input id="tag4Str" name="tag4Str" type="text" class="form-control" value="${niAssessDeliveryWanx.tag4Str}" placeholder="标签4 ..."></td>
					<td><input id="tag5Str" name="tag5Str" type="text" class="form-control" value="${niAssessDeliveryWanx.tag5Str}" placeholder="标签5 ..."></td>
         		</tr>
         		
                <tr>
         			<td><label>相关推荐</label></td>  
         			<td><input id="relatedStr1" name="relatedStr1" type="text" class="form-control" value="${niAssessDeliveryWanx.relatedStr1}" placeholder="相关推荐1"></td>
         			<td><input id="relatedUrl1" name="relatedUrl1" type="text" class="form-control" value="${niAssessDeliveryWanx.relatedUrl1}" placeholder="相关推荐1路径"></td>
         		</tr>  
                <tr>
         			<td><label></label></td>  
         			<td><input id="relatedStr2" name="relatedStr2" type="text" class="form-control" value="${niAssessDeliveryWanx.relatedStr2}" placeholder="相关推荐2"></td>
         			<td><input id="relatedUrl2" name="relatedUrl2" type="text" class="form-control" value="${niAssessDeliveryWanx.relatedUrl2}" placeholder="相关推荐2路径"></td>
         		</tr>
                <tr>
         			<td><label></label></td>  
         			<td><input id="relatedStr3" name="relatedStr3" type="text" class="form-control" value="${niAssessDeliveryWanx.relatedStr3}" placeholder="相关推荐3"></td>
         			<td><input id="relatedUrl3" name="relatedUrl3" type="text" class="form-control" value="${niAssessDeliveryWanx.relatedUrl3}" placeholder="相关推荐3路径"></td>
         		</tr>         		
         		         
         		<tr>
         			<td><label>选择列表图</label></td>
         			<td><input type="file" name="uploadImgmes" id="uploadImgmes" onchange="PreviewImage(this)" value=""> </td>
         			<td><img height="150px" width="230px" id="imgurl" src="${niAssessDeliveryWanx.img}" class="img-rounded">
         		</tr>
         	</table>
         	
	         <div class="box-header with-border">
	           <h3 class="box-title">奖励信息</h3>
	           <table style="border-spacing: 10px;border-collapse: separate;">
	         		<tr>
	         			<td><label>启动奖励</label></td>
	         		</tr>
	         	    <tr>
	         			<td><label>奖品ID</label></td> 
	         			<td>
	         				<select id="awardid" name="awardid">
	         					<c:forEach items="${NiAward_list }" var="na">
		         					<option value="${na.awardId }" <c:if test="${na.awardId == niAssessDeliveryWanx.award1Id }">selected="selected"</c:if>>${na.awardName }</option>
	         					</c:forEach>
	         				</select>
	         			</td>
	         		</tr>
	         		
	          		<tr>
	         			<td><label>启动抽奖</label></td>
	         		</tr>
	         		<tr>
	         			<td><label>抽奖ID</label></td>
	         		 	<td><input id="lotteryid" name="lotteryid" value="${niAssessDeliveryWanx.lotteryId }" type="text" class="form-control"></td>
	         		</tr>         		        		        			           		
	           </table>
	         </div>
	         
           <div class="box-footer">
             <button type="button" onclick="addDeliveryWanxMes();" class="btn btn-primary">保存</button>
           </div>
           
         </form>
       </div>
<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/js/validate.js"></script> 
<script>
 $('#datepicker').datepicker({
     autoclose: true,format: 'yyyy-mm-dd'
   });
 $('#datepicker1').datepicker({
  autoclose: true,format: 'yyyy-mm-dd'
 });

 var deliveryid = "";
 $(function(){
	 deliveryid = "${deliveryid}";
 });
 
 //保存信息
 function addDeliveryWanxMes(){
	  var formData = new FormData($("#pageListForm")[0]);
	    $.ajax({  
	         url: "${ctx}/platform/niAssessDeliveryWanxMESSave.do?deliveryid="+deliveryid,  
	         type: 'POST',  
	         data: formData,  
	         async: false,  
	         cache: false,  
	         contentType: false,  
	         processData: false,  
	         success: function (data) {
	        	 showMessageDialog(data.msg);
	         },  
	         error: function (data) {
	             showMessageDialog("网络异常");  
	         } 
	    });
	 
 }
	
	//预览图片
	function PreviewImage(imgFile){
		var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
		if (!pattern.test(imgFile.value)) {
			alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
			imgFile.focus();
		} else {
			var path;
			if (document.all)//IE 
			{
				imgFile.select();
				path = document.selection.createRange().text;
				document.getElementById("imgurl").innerHTML = "";
				document.getElementById("imgurl").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\""
						+ path + "\")";//使用滤镜效果 
			} else//FF 
			{
				path = URL.createObjectURL(imgFile.files[0]);
				$("#imgurl").attr("src", path);
			}
		}
	}
	
	// 问卷
	function uestionnairePage(){
		window.location.href = "${ctx}/platform/niAssessDeliveryWanxNewJump.do?deliveryid="+deliveryid;
	}
	//跳转到信息添加页面
	function messagePage(){
		window.location.href = "${ctx}/platform/niAssessDeliveryWanxMESJump.do?deliveryid="+deliveryid;
	}
	//跳转到设置
	function setupPage(){
		window.location.href = "${ctx}/platform/niAssessDeliveryWanxSetJump.do?deliveryid="+deliveryid;
	}
	// 选择模版
	function choseTemplatePage(){
		window.location.href = "${ctx}/platform/choseAssessTemplatePage.do?deliveryid="+deliveryid;
	}
	//运营
	function operatePage(){
		window.location.href = "${ctx}/platform/niAssessDeliveryWanxOperateJump.do?deliveryid="+deliveryid;
	}
	// 付费测评
	function paySetPage(){
		window.location.href = "${ctx}/platform/assessDelivery_PaySetPage.do?deliveryid="+deliveryid;
	}
	function resultPage(){
		window.location.href = "${ctx}/platform/assessDelivery_ResultPage.do?deliveryid="+deliveryid;
	}
	
</script>
</body>
</html>
