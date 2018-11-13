<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>测评结果编辑</title>
</head>
<body class="hold-transition skin-blue ">
       <div class="box box-primary">
         <div class="box-header with-border">
           <h3 class="box-title">测评结果编辑</h3>
         </div>
         <!-- form start -->
         <form id="pageListForm" action="${ctx}/platform/searchQueByIdOrName.do" method="post">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         		<tr>
         			<td><label>&nbsp;&nbsp;测评问卷 id &nbsp;: &nbsp;</label></td> 
         			<td colspan="2">
         				<input type="hidden" id="aqnId" name="aqnId" value="${aqnId }">
         				${aqnId }
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;测评问卷类型&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<input type="hidden" id="aqnCategory" name="aqnCategory" value="${aqnCategory }">
         				<c:if test="${aqnCategory == 0}">
         					简单测评模型
         				</c:if>
         				<c:if test="${aqnCategory == 1}">
         					多维度加总型模型
         				</c:if>
         				<c:if test="${aqnCategory == 2}">
         					大五模型
         				</c:if>
         				<c:if test="${aqnCategory == 3}">
         					MBTI模型
         				</c:if>
         				<c:if test="${aqnCategory == 4}">
         					多维度组合输出模型关联输出型 
         				</c:if>
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp;维度编号&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<input type="hidden" id="dimension" name="dimension" value="${dimension }">
         				${dimension }
         			</td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;联合维度编号&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<input type="hidden" id="dimensionStr" name="dimensionStr" value="${dimensionStr }">
         				${dimensionStr }
         			</td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;区间编号&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<input type="hidden" id="section" name="section" value="${section }">
         				${section }
         			</td>
         		</tr>        		        		
         		<tr>
         			<td><label>&nbsp;&nbsp;上限&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<input type="text" style="width: 300px;height: 30px;" <c:if test="${assessResult.intervalBegin == null}">disabled="disabled"</c:if>
         					id="intervalBegin" name="intervalBegin" value="${assessResult.intervalBegin }">
         			</td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;下限&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<input type="text" style="width: 300px;height: 30px;" <c:if test="${assessResult.intervalEnd == null}">disabled="disabled"</c:if> 
         					 id="intervalEnd" name="intervalEnd" value="${assessResult.intervalEnd }">
         			</td>
         		</tr>         		
         		<tr>
         			<td><label>&nbsp;&nbsp;概览描述&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<textarea style="width:300px;height: 70px;" id="resultSummary" name="resultSummary">${assessResult.resultSummary }</textarea>
         			</td>
         		</tr> 
         		<tr>
         			<td><label>&nbsp;&nbsp;优先级&nbsp;: &nbsp;</label></td>
         			<td colspan="2">
         				<input type="text" <c:if test="${assessResult.priority == null}">disabled="disabled"</c:if>
         					style="width: 300px;height: 30px;" id="priority" name="priority" value="${assessResult.priority }">
         			</td>
         		</tr>
         		
         	</table>
         </form>
	        <div id="errorMsg" style="color: red;"></div>
           <div class="box-footer">
             <input type="button" onclick="saveResult_Section()" class="btn btn-primary" value="保存"> 
             <input type="button" onclick="backToList()" class="btn btn-primary" value="返回">
           </div>
           
       </div>
<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/js/validate.js"></script> 
<script type="text/javascript">

   function backToList(){
	   
	   var aqnId = "${aqnId}";
	   
	   window.location.href = "${ctx}/platform/editAssessResult_list.do?aqnId="+aqnId;
   }
	
	//保存 
	function saveResult_Section(){
		
		var aqnId = $.trim($("#aqnId").val());
		var aqnCategory = $.trim($("#aqnCategory").val());
		var dimension = $.trim($("#dimension").val());
		var dimensionStr = $.trim($("#dimensionStr").val());
		var section = $.trim($("#section").val());
		var intervalBegin = $.trim($("#intervalBegin").val());
		var intervalEnd = $.trim($("#intervalEnd").val());
		var resultSummary = $.trim($("#resultSummary").val());
		var priority = $.trim($("#priority").val());
		
/* 		  var errorMsg = $('#errorMsg');
		  errorMsg.html(" ");
		  if(tweetTitle == ""){
			  errorMsg.html("&nbsp;&nbsp;&nbsp;&nbsp;请输入推文标题");
			  return false;
		  } */
		  
		  var formData = new FormData($("#pageListForm")[0]);
		    $.ajax({
		         url: "${ctx}/platform/save_AssessResult_Section.do",
		         type: 'POST',  
		         data: formData,  
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
		         success: function (data) {
		        	 showConfirmDialog_saveQueJump(data.msg, function(check){
		        		 if(check){
		        			 window.location.href = "${ctx}/platform/editAssessResult_list.do?aqnId="+aqnId;
		        		 }
		        	 });
		         },  
		         error: function (data) {
		             showMessageDialog("网络异常");  
		         } 
		    });
		  
	}
	
</script>
</body>
</html>
