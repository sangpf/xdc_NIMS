<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>推荐秘籍内容列表</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${ctx}/js/sang.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    
    <!-- 内容部分 -->
        <div class="panel panel-default">
          <div class="panel-body">
            <form class="form-horizontal"  method="post" action="${ctx}/platform/list_ProductRecommend.do" id="searchform">
                	<table style="border-spacing: 5px;border-collapse: separate;">
						<tr>
							<td>原内容 ID : ${sourceId}</td>

							<td>
								<input type="hidden" id="sourceId" value="${sourceId}">
								<input type="hidden" id="sourceType" value="${sourceType}">
							</td>
							
							<td>原内容类型 : 
								<c:if test="${sourceType == 1 }">调查</c:if>
								<c:if test="${sourceType == 2 }">测评</c:if>
								<c:if test="${sourceType == 3 }">投票</c:if>
								<c:if test="${sourceType == 4 }">推文</c:if>
							</td>
							<td></td>
							
						</tr>
				</table>	
            </form>
          </div>
        </div>
        
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="button-group">
                <button type="button" onclick="add_ProductRecommend_target();" class="btn btn-default btn-sm">添加</button>
                <button type="button" onclick="dele_ProductRecommend_target();" class="btn btn-default btn-sm">删除</button>
                <button type="button" onclick="back_toList();" class="btn btn-default btn-sm">回原列表</button>
            </div>
          </div>
        </div>
        
        <div class="panel panel-default">
          <div class="box">
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th></th>
                  <th>序号</th>
                  <th>目标内容id</th>
                  <th>目标内容类型</th>
                  <th>目标内容标题</th>
                  <th>排序</th>
                  <th>操作</th>
                </tr>         
                </thead>
                <tbody>
                <c:forEach items="${list_ProductRecommend_Vo }" var="productRecommend_Vo" varStatus="n">
	                 <tr>
	                  <td>
	                  	<input type="checkbox" name="check_ProductRecommend_Id" value="${productRecommend_Vo.id }">
	                  	<input type="hidden" name="showOrder" value="${productRecommend_Vo.showOrder }">
	                  </td>
	                  <td>${n.index+1}</td>
	                  
	                  <td>${productRecommend_Vo.targetId }</td>
	                  <td>
	                  	<c:if test="${productRecommend_Vo.targetType == 1}">调查</c:if>
	                  	<c:if test="${productRecommend_Vo.targetType == 2}">测评</c:if>
	                  	<c:if test="${productRecommend_Vo.targetType == 3}">投票</c:if>
	                  	<c:if test="${productRecommend_Vo.targetType == 4}">推文</c:if>
	                  </td>
	                  <td>${productRecommend_Vo.targetTitle }</td>
	                  
	                  <td>${productRecommend_Vo.showOrder }</td>
	                  
	                  <td>
						<button type="button" 
										onclick="moveUp(${n.index+1},${productRecommend_Vo.id},${productRecommend_Vo.showOrder},${sourceId},${sourceType})" 
										class="btn btn-default btn-sm">上移</button>
						<button type="button" 
										onclick="moveDown(${n.index+1},${productRecommend_Vo.id},${productRecommend_Vo.showOrder},${sourceId},${sourceType})" 
										class="btn btn-default btn-sm">下移</button>
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
  //添加
  function add_ProductRecommend_target(){
	  
	  var sourceId = document.getElementById("sourceId").value;
	  var sourceType = document.getElementById("sourceType").value;
	  
	  window.location.href = "${ctx}/platform/add_ProductRecommend.do?sourceId="+sourceId+"&sourceType="+sourceType;
  }
  
  function moveUp(a,productRecommendId,showOrder,sourceId,sourceType){
	    
		var index = a - 2;
		if (a == 1) {
			alert("无法继续上移！");
			return;
		}
		var productRecommendId_arr = document.getElementsByName("check_ProductRecommend_Id");
		var showOrder_arr = document.getElementsByName("showOrder");
		
		var last_productRecommendId = productRecommendId_arr[index].value;
		var last_showOrder = showOrder_arr[index].value;
		
		$.ajax({
				url : "${ctx}/platform/move_Up_Down_productRecommendId.do",
				type : "post",
				data : {
					productRecommendId : productRecommendId,
					showOrder : showOrder,
					last_productRecommendId : last_productRecommendId,
					last_showOrder : last_showOrder
				},
				dataType : "json",
				success : function(data) {
					if (data.success) {
						window.location.href = "${ctx}/platform/list_ProductRecommend_target.do?sourceId="+sourceId+"&sourceType="+sourceType;
					}
				}
			});
	  
  }
   
  function moveDown(a,productRecommendId,showOrder,sourceId,sourceType){
	    
    	var index = a;

		var productRecommendId_arr = document.getElementsByName("check_ProductRecommend_Id");
		var showOrder_arr = document.getElementsByName("showOrder");
		
		if (index >= productRecommendId_arr.length) {
			alert("无法继续下移");
			return;
		}
		var last_productRecommendId = productRecommendId_arr[index].value;
		var last_showOrder = showOrder_arr[index].value;
		$.ajax({
			url : "${ctx}/platform/move_Up_Down_productRecommendId.do",
			type : "post",
			data : {
				productRecommendId : productRecommendId,
				showOrder : showOrder,
				last_productRecommendId : last_productRecommendId,
				last_showOrder : last_showOrder
			},
			dataType : "json",
			success : function(data) {
				if (data.success) {
					window.location.href = "${ctx}/platform/list_ProductRecommend_target.do?sourceId="+sourceId+"&sourceType="+sourceType;
				}
			}
		});
	  
  }
  
  //删除
  function dele_ProductRecommend_target(){
	  
	  var sourceId = document.getElementById("sourceId").value;
	  var sourceType = document.getElementById("sourceType").value;
	  var check_ProductRecommend_Id_arr = document.getElementsByName("check_ProductRecommend_Id");
	  
	  var count = 0;
	  var productRecommend_Id_arr = "";
	  
	  for(var i=0;i<check_ProductRecommend_Id_arr.length;i++){
		  if(check_ProductRecommend_Id_arr[i].checked){
			  count++;
			  productRecommend_Id_arr = check_ProductRecommend_Id_arr[i].value + "|" +productRecommend_Id_arr;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("选择为空");
		  return;
	  }
	  showConfirmDialog("要删除选中项吗?", function(check){
		  if(check){
			   $.ajax({
				  type:"post",
				  dataType:"json",
				  url:"${ctx}/platform/dele_ProductRecommend_Id_arr.do?productRecommend_Id_arr="+productRecommend_Id_arr,
				  success:function(data){
					  
					  showMessageDialog(data.msg, function(){
						  if(data.success){
							  window.location.href = "${ctx}/platform/list_ProductRecommend_target.do?sourceId="+sourceId+"&sourceType="+sourceType;
						  }
					  });
				  }
			  }); 
		  }
	  });

  }

  function back_toList(){
	  window.location.href = "${ctx}/platform/list_ProductRecommend.do";
  }

  </script>
</body>

</html>