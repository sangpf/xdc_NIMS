<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title> 产品包下内容列表</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${ctx}/js/sang.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    
    <!-- 内容部分 -->
        <div class="panel panel-default">
          <div class="panel-body">
            <form class="form-horizontal"  method="post" action="${ctx}/platform/select_product_manage.do" id="searchform">
                	<table style="border-spacing: 5px;border-collapse: separate;">
						<tr>
						
							<td>产品包名称 : ${ProductPackage.title }
			                	<input type="hidden" value="${ProductPackage.id }" id="productPackageId" name="productId">
							</td>
							<td></td>
							<td style="width: 160px;">
								<select class="form-control select-sm" name="columId" id="columId">
									<option value="1">产品包首页列表</option>
									<option <c:if test="${columId == 2 }">selected="selected"</c:if> value="2">顶部左侧栏目</option>
									<option <c:if test="${columId == 3 }">selected="selected"</c:if> value="3">顶部右侧栏目</option>
								</select>
							</td>
							
							<td><span class="input-group-btn">
									<button type="button" onclick="submit()" class="btn btn-info btn-flat">查询</button>
							</span></td>
						</tr>
				</table>	
            </form>
          </div>
        </div>
        
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="button-group">
                <button type="button" onclick="add_ProductManage();" class="btn btn-default btn-sm">添加</button>
                <button type="button" onclick="dele_ProductManage();" class="btn btn-default btn-sm">删除</button>
                <button type="button" onclick="back_product_list();" class="btn btn-default btn-sm">回产品包列表</button>
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
                  <th>内容id</th>
                  <th>内容类型</th>
                  <th>内容标题</th>
                  <th>显示顺序</th>
                  <th>状态</th>
                  <th>显示位置</th>
                  <th>操作</th>
                </tr>         
                </thead>
                <tbody>
                <c:forEach items="${list_ProductManage_Vo }" var="ProductManage_Vo" varStatus="n">
	                 <tr>
	                  <td>
	                  	<input type="checkbox" name="check_ProductManage_Id" value="${ProductManage_Vo.id }">
	                  	<input type="hidden" name="showOrder" value="${ProductManage_Vo.showOrder }">
	                  </td>
	                  <td>${n.index+1}</td>
	                  <td>${ProductManage_Vo.itemId }</td>
	                  <td>
	                  	<c:if test="${ProductManage_Vo.itemType == 1}">调查</c:if>
	                  	<c:if test="${ProductManage_Vo.itemType == 2}">测评</c:if>
	                  	<c:if test="${ProductManage_Vo.itemType == 3}">投票</c:if>
	                  	<c:if test="${ProductManage_Vo.itemType == 4}">报告</c:if>
	                  	<c:if test="${ProductManage_Vo.itemType == 5}">推文</c:if>
	                  	<!-- 1 调查，2 测评，3 投票，4 报告 5.推文 -->
	                  </td>
	                  <td>
              			<div style="width: 300px;white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
                  			${ProductManage_Vo.showTitle }
                  		</div>
	                  </td>
	                  <td>${ProductManage_Vo.showOrder }</td>
                	  <td>
                	  	<c:if test="${ProductManage_Vo.state == 1}">有效</c:if>
                	  	<c:if test="${ProductManage_Vo.state == 2}">废弃</c:if>
                	  </td>
	                  <td>
	                  	<c:if test="${ProductManage_Vo.columId == 1}">产品包首页</c:if>
	                  	<c:if test="${ProductManage_Vo.columId == 2}">顶部左侧栏目</c:if>
	                  	<c:if test="${ProductManage_Vo.columId == 3}">顶部右侧栏目</c:if>
	                  </td>
	                  <td>
						<button type="button" 
										onclick="moveUp(${n.index+1},${ProductManage_Vo.id},${ProductManage_Vo.showOrder},${ProductManage_Vo.columId})" 
										class="btn btn-default btn-sm">上移</button>
						<button type="button" 
										onclick="moveDown(${n.index+1},${ProductManage_Vo.id},${ProductManage_Vo.showOrder},${ProductManage_Vo.columId})" 
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
  function add_ProductManage(){
	  
	  var productPackageId = document.getElementById("productPackageId").value;
	  window.location.href = "${ctx}/platform/add_ProductManage.do?productPackageId="+productPackageId;
  }
  
  function moveUp(a, productManageId, showOrder ,columId){
	    
	    var productId = document.getElementById("productPackageId").value;
		
		var index = a - 2;
		if (a == 1) {
			alert("无法继续上移！");
			return;
		}
		var productManageId_arr = document.getElementsByName("check_ProductManage_Id");
		var showOrder_arr = document.getElementsByName("showOrder");
		
		var last_productManageId = productManageId_arr[index].value;
		var last_showOrder = showOrder_arr[index].value;
		
		$.ajax({
				url : "${ctx}/platform/move_Up_Down_productManage.do",
				type : "post",
				data : {
					productManageId : productManageId,
					showOrder : showOrder,
					last_productManageId : last_productManageId,
					last_showOrder : last_showOrder
				},
				dataType : "json",
				success : function(data) {
					if (data.success) {
						window.location.href = "${ctx}/platform/select_product_manage.do?productId="+productId+"&columId="+columId;
					}
				}
			});
	  
  }
   
  function moveDown(a, productManageId, showOrder ,columId){
	    var productId = document.getElementById("productPackageId").value;
	    
    	var index = a;

		var productManageId_arr = document.getElementsByName("check_ProductManage_Id");
		var showOrder_arr = document.getElementsByName("showOrder");
		
		if (index >= productManageId_arr.length) {
			alert("无法继续下移");
			return;
		}
		var last_productManageId = productManageId_arr[index].value;
		var last_showOrder = showOrder_arr[index].value;
		$.ajax({
			url : "${ctx}/platform/move_Up_Down_productManage.do",
			type : "post",
			data : {
				productManageId : productManageId,
				showOrder : showOrder,
				last_productManageId : last_productManageId,
				last_showOrder : last_showOrder
			},
			dataType : "json",
			success : function(data) {
				if (data.success) {
					window.location.href = "${ctx}/platform/select_product_manage.do?productId="+productId+"&columId="+columId;
				}
			}
		});
	  
  }
  
  //删除
  function dele_ProductManage(){
	  
	  var productId = document.getElementById("productPackageId").value;  // 产品包id
	  var check_ProductManage_Id_arr = document.getElementsByName("check_ProductManage_Id");
	  
	  var count = 0;
	  var productManage_Id_arr = "";
	  
	  for(var i=0;i<check_ProductManage_Id_arr.length;i++){
		  if(check_ProductManage_Id_arr[i].checked){
			  count++;
			  productManage_Id_arr = check_ProductManage_Id_arr[i].value + "|" +productManage_Id_arr;
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
				  url:"${ctx}/platform/dele_ProductManage_Id_arr.do?productManage_Id_arr="+productManage_Id_arr,
				  success:function(data){
					  
					  showMessageDialog(data.msg, function(){
						  if(data.success){
							  window.location.href = "${ctx}/platform/select_product_manage.do?productId="+productId;
						  }
					  });
				  }
			  }); 
		  }
	  });

  }

  function back_product_list(){
	  window.location.href = "${ctx}/platform/list_ProductPackage.do";
  }

  </script>
</body>

</html>