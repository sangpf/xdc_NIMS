<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>投放管理信息-投票问卷管理</title>
  <link rel="stylesheet" href="${ctx}/BS/plugins/datepicker/datepicker3.css">
</head>
<body class="hold-transition skin-blue ">
 <!-- general form elements -->
       <div class="box box-primary">
 	       <table style="padding: 100px;margin: 10px;">
	           	<tr>
	           		<td style="width: 100px;padding: 3px;"><button type="button" onclick="uestionnairePage();" class="btn btn-block btn-primary">问卷</button></td>
	           		<td style="width: 100px;padding: 3px;"><button type="button" onclick="messagePage();" style="background-color: #BC8F8F" class="btn btn-block btn-primary">信息</button></td>
	           		<td style="width: 100px;padding: 3px;"><button type="button" onclick="setupPage();" class="btn btn-block btn-primary">设置</button></td>
	   	            <td style="width: 100px;padding: 3px;"><button type="button" onclick="operatePage();" class="btn btn-block btn-primary">运营调整</button></td>
	           		<td style="width: 100px;padding: 3px;"><button type="button" onclick="choseTemplatePage();" class="btn btn-block btn-primary">选择模版</button></td>
	           	</tr>
           </table>
         <div class="box-header with-border" style="font-size: 20px">
         	   当前投放id为 : ${deliveryid}
         	   <c:if test="${deliveryid == '' || null || 'undefined'}">空</c:if>
         </div>
         <!-- form start -->
         <form id="pageListForm" method="post" action="" enctype="multipart/form-data">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         		<tr>
         			<td><label>&nbsp;&nbsp;问卷名称&nbsp;: &nbsp;</label></td> <td colspan="5"><input id="sqnName" type="text" maxlength="28" style="width: 700px;height: 30px;" class="form-control" value="${niVoteQuestionnaire.vqnname}" disabled="disabled" placeholder="Enter ..."></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;发布机构&nbsp;: &nbsp;</label></td> <td colspan="5"><input id="publisherName" type="text" maxlength="28" style="width: 700px;height: 30px;" class="form-control" value="${niVoteQuestionnaire.publishername}" disabled="disabled" placeholder="Enter ..."></td>
         		</tr>
                <tr>
         			<td><label>&nbsp;&nbsp;问卷显示标题&nbsp;: &nbsp;</label></td> <td colspan="5"><input id="showTitle" name="showTitle" type="text" maxlength="28" style="width: 700px;height: 30px;" class="form-control" value="${niVoteDeliveryWanx.showtitle }" placeholder="Enter ..."></td>
         		</tr>
                <tr>
         			<td><label>&nbsp;&nbsp;答题后结语&nbsp;: &nbsp;</label></td> <td colspan="5"><input id="resultMsg" name="resultMsg" type="text" style="width: 700px;height: 30px;" class="form-control" value="${niVoteDeliveryWanx.resultMsg }"></td>
         		</tr>
         		
    			<tr>
         			<td><label>&nbsp;&nbsp;标题标签&nbsp;: &nbsp;</label></td> 
         			<td colspan="5"><input id="titleTag" name="titleTag" type="text" style="width: 700px;height: 30px;" class="form-control" value="${niVoteDeliveryWanx.titleTag }"></td>
         		</tr>                		
         		<tr>
         			<td><label>&nbsp;&nbsp;发布时间&nbsp;: &nbsp;</label></td> 
       					    <td><input type="text"  class="form-control pull-right" value="${btime_str}" id="datepicker" name="datepicker"></td> 
         			<td><label>&nbsp;&nbsp;截止时间&nbsp;: &nbsp;</label></td> 
         					<td><input type="text"  class="form-control pull-right" value="${etime_str}" id="datepicker1" name="datepicker1"></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;计划采集份数&nbsp;: &nbsp;</label></td> <td colspan="5"><input value="${niVoteDeliveryWanx.collectnum }" id="collectnum" name="collectnum" type="text" maxlength="10" style="width: 700px;height: 30px;" class="form-control" placeholder="Enter ..."></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;内简介&nbsp;: &nbsp;</label></td> <td colspan="5"><textarea id="" class="form-control" style="width: 700px;" rows="3" placeholder="不能修改 ..." disabled>${niVoteQuestionnaire.vqnsummary}</textarea></td>
         		</tr>
          		<tr>
         			<td><label>&nbsp;&nbsp;外简介&nbsp;: &nbsp;</label></td> <td colspan="5"><textarea id="showdes" name="showdes" class="form-control" style="width: 700px;" rows="3" placeholder="Enter ...">${niVoteDeliveryWanx.showdes }</textarea></td>
         		</tr> 
         		<tr>
         			<td><label>&nbsp;&nbsp;外标签&nbsp;: &nbsp;</label></td>  
         			<td><input id="tag1str" name="tag1str" type="text" maxlength="28" style="width: 120px;height: 30px;" class="form-control" value="${niVoteDeliveryWanx.tag1str }" placeholder="标签1 ..."></td>
					<td><input id="tag2str" name="tag2str" type="text" maxlength="28" style="width: 120px;height: 30px;" class="form-control" value="${niVoteDeliveryWanx.tag2str }" placeholder="标签2 ..."></td>
					<td><input id="tag3str" name="tag3str" type="text" maxlength="28" style="width: 120px;height: 30px;" class="form-control" value="${niVoteDeliveryWanx.tag3str }" placeholder="标签3 ..."></td>
					<td><input id="tag4str" name="tag4str" type="text" maxlength="28" style="width: 120px;height: 30px;" class="form-control" value="${niVoteDeliveryWanx.tag4str }" placeholder="标签4 ..."></td>
					<td><input id="tag5str" name="tag5str" type="text" maxlength="28" style="width: 120px;height: 30px;" class="form-control" value="${niVoteDeliveryWanx.tag5str }" placeholder="标签5 ..."></td>
         		</tr>   
         		
                <tr>
         			<td><label>&nbsp;&nbsp;相关推荐&nbsp;&nbsp;</label></td>  
         			<td><input id="relatedStr1" name="relatedStr1" type="text" style="width: 200px;height: 30px;" class="form-control" value="${niVoteDeliveryWanx.relatedStr1}" placeholder="相关推荐1"></td>
         			<td><input id="relatedUrl1" name="relatedUrl1" type="text" style="width: 200px;height: 30px;" class="form-control" value="${niVoteDeliveryWanx.relatedUrl1}" placeholder="相关推荐1路径"></td>
         		</tr>  
                <tr>
         			<td><label>&nbsp;&nbsp;&nbsp;&nbsp;</label></td>  
         			<td><input id="relatedStr2" name="relatedStr2" type="text" style="width: 200px;height: 30px;" class="form-control" value="${niVoteDeliveryWanx.relatedStr2}" placeholder="相关推荐2"></td>
         			<td><input id="relatedUrl2" name="relatedUrl2" type="text" style="width: 200px;height: 30px;" class="form-control" value="${niVoteDeliveryWanx.relatedUrl2}" placeholder="相关推荐2路径"></td>
         		</tr>
                <tr>
         			<td><label>&nbsp;&nbsp;&nbsp;&nbsp;</label></td>  
         			<td><input id="relatedStr3" name="relatedStr3" type="text" style="width: 200px;height: 30px;" class="form-control" value="${niVoteDeliveryWanx.relatedStr3}" placeholder="相关推荐3"></td>
         			<td><input id="relatedUrl3" name="relatedUrl3" type="text" style="width: 200px;height: 30px;" class="form-control" value="${niVoteDeliveryWanx.relatedUrl3}" placeholder="相关推荐3路径"></td>
         		</tr>      
         		
         		<tr>
         			<td><label>选择列表图</label></td>
         			<td><input type="file" name="uploadImgmes" id="uploadImgmes" onchange="PreviewImage(this)" value=""> </td>
         			<td><img height="150px" width="230px" id="imgurl" src="${niVoteDeliveryWanx.img}" class="img-rounded">
         		</tr>
         		
         	</table>
	         <div class="box-header with-border">
	           <h3 class="box-title">奖励信息</h3>
	           <table style="border-spacing: 10px;border-collapse: separate;">
	         		<tr>
	         			<td><label>&nbsp;&nbsp;启动奖励&nbsp;: &nbsp;</label></td>
	         		</tr>
	        	    <tr>
	         			<td><label>&nbsp;&nbsp;奖品id&nbsp;: &nbsp;</label></td> 
	         			<td colspan="5">
	         				<select id="awardid" name="awardid">
	         					<c:forEach items="${NiAward_list }" var="na">
		         					<option value="${na.awardId }" <c:if test="${na.awardId == niVoteDeliveryWanx.award1id}">selected="selected"</c:if> >${na.awardName }</option>
	         					</c:forEach>
	         				</select>
	         			</td>
	         		</tr>
	          		<tr>
	         			<td><label>&nbsp;&nbsp;启动抽奖&nbsp;: &nbsp;</label></td>
	         		</tr>
	         		<tr>
	         			<td><label>&nbsp;&nbsp;抽奖id&nbsp;: &nbsp;</label></td>
	         	 <td colspan="5"><input id="lotteryid" name="lotteryid" type="text" maxlength="28" value="${niVoteDeliveryWanx.lotteryid }" style="width: 300px;height: 30px;" class="form-control" placeholder="Enter ..."></td>
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
 
//投放管理id
 var deliveryid = "";
 $(function(){
	 deliveryid = "${deliveryid}";
 });

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
	
 //保存信息
 function addDeliveryWanxMes(){
	 var formData = new FormData($("#pageListForm")[0]);
	 
	    $.ajax({
	         url: "${ctx}/platform/niVoteDeliveryWanxSaveMES.do?deliveryid="+deliveryid,
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
