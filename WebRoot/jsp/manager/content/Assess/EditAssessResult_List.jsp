<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>测评结果页列表页面</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${ctx}/js/sang.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">

   	    <table style="padding: 100px;margin: 10px;">
           	<tr>
           		<td style="width: 100px;padding: 3px;">
           			<button type="button" onclick="editAssessQuestion();" class="btn btn-block btn-primary" >问卷编辑</button></td>
           		<td style="width: 100px;padding: 3px;">
           			<button type="button" onclick="editAssessResult_list();" class="btn btn-block btn-primary" style="background-color: #BC8F8F">结果页编辑</button></td>
           	</tr>
          </table>
        <div class="box-header with-border" style="font-size: 18px;color: blue;">
        	  当前问卷id : ${aqnId}
        	   <c:if test="${aqnId == '' || null || 'undefined'}">空</c:if> | 
        	   
        	   <!-- 0简单测评模型；1多维度加总型模型;2 大五模型; 3 MBTI模型 4, 多维度组合输出模型 -->
        	 测评类型 : 
        	 <c:if test="${aqnCategory == 0}">简单测评模型</c:if>
        	 <c:if test="${aqnCategory == 1}">多维度加总型模型</c:if>
        	 <c:if test="${aqnCategory == 2}">大五模型</c:if>
        	 <c:if test="${aqnCategory == 3}">MBTI模型</c:if>
        	 <c:if test="${aqnCategory == 4}">多维度组合输出模型关联输出型</c:if> | 
        	 
        	 结果页显示方式 : ${resutShowType}
        </div>
  <div class="wrapper">
  
        <div class="panel panel-default">
          <div class="box">
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>维度编号</th>
                  
                  <th>区间编号</th>
                  
                  <%-- <c:if test="${aqnCategory ==0 || aqnCategory ==2 }"> --%>
               	    <th>上线</th>
                  	<th>下线</th>
                  <%-- </c:if> --%>
                  
                 <%-- <c:if test="${aqnCategory == 3 || aqnCategory == 4 }"> --%>
 					<th>联合维度编号</th>
                 <%--  </c:if> --%>
                 
                  <th>概览描述</th>
                  <th>详情描述</th>
                  
                  <%-- <c:if test="${aqnCategory ==0 || aqnCategory ==2 }"> --%>
                  	<th>上下限</th>
                  <%-- </c:if> --%>
                  

                  
                  <th>结果详情</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${AssessResult_list }" var="assessResult" varStatus="n">
                 <tr>
	                  <td>${assessResult.dimension }
	                  </td>
					  

					  
					  <td>${assessResult.section }
					  </td>
					  
					  <%-- <c:if test="${aqnCategory ==0 || aqnCategory ==2 }"> --%>
						  <td>${assessResult.intervalBegin }
						  </td>
						  <td>${assessResult.intervalEnd }
						  </td>
	                  <%-- </c:if> --%>
					  
					  <%-- <c:if test="${aqnCategory == 3 || aqnCategory == 4 }"> --%>
	 					 <td>${assessResult.dimensionStr }</td>
	                  <%-- </c:if> --%>
	                  
					  <td>
                  		<div style="width: 250px;height:25px; white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
                  			${assessResult.resultSummary }
                  		</div>
	                  </td>
	                  <td>
                  		<div style="width: 250px;height:25px; white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
                  			${assessResult.resultDetail }
                  		</div>
	                  </td>
	                  
	                 <%-- <c:if test="${aqnCategory ==0 || aqnCategory ==2 }"> --%>
	                  <td>
	                  	  <input type="button" value="编辑" 
	           				 onclick="editAssessResult_Section('${aqnId}','${aqnCategory }','${assessResult.dimension }',
	            					 '${assessResult.dimensionStr }','${assessResult.section }')" >
	                  </td>
					<%-- </c:if> --%>

	                  <td>
	                  	  <input type="button" value="编辑" 
				            onclick="editAssessResult('${aqnId}','${aqnCategory }','${assessResult.dimension }',
				           			 '${assessResult.dimensionStr }','${assessResult.section }','${resutShowType}' )" >
	                  </td>	                  
	               </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
        </div>
    </div>

  <script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
  <script src="${ctx}/static/js/moment.min.js"></script>
  <script src="${ctx}/js/validate.js"></script>
 <script>
 
 var aqnId = "";
 $(function(){
 	aqnId = "${aqnId}";

 });
	 
 	//编辑单个结果页
	 function editAssessResult(aqnId,aqnCategory,dimension,dimensionStr,section,resutShowType){
 window.location.href = 
	 "${ctx}/platform/editAssessResult.do?aqnId="+aqnId+"&aqnCategory="+aqnCategory+
			 "&dimension="+dimension+"&dimensionStr="+dimensionStr+"&section="+section+"&resutShowType="+resutShowType;
	 }
 // 编辑测评结果区间设置
 function editAssessResult_Section(aqnId,aqnCategory,dimension,dimensionStr,section){
	 window.location.href = 
		 "${ctx}/platform/EditAssessResult_Section.do?aqnId="+aqnId+"&aqnCategory="+aqnCategory+
				 "&dimension="+dimension+"&dimensionStr="+dimensionStr+"&section="+section;
	 
 }
 	
	  // 跳转到问卷编辑
	  function editAssessQuestion(){
		  window.location.href = "${ctx}/platform/editNiAssessQuestionnaire.do?aqnId="+aqnId;
	  }
	  // 保存结果页
	  function editAssessResult_list(){
		  window.location.href = "${ctx}/platform/editAssessResult_list.do?aqnId="+aqnId;
	  }
	 
  </script>
</body>

</html>