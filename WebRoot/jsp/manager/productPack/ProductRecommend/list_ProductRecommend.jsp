<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title> 推荐秘籍列表</title>
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
							<td>原内容 ID</td>
							<td>
			                	<input type="text" value="" id="sourceId" name="sourceId" >
							</td>
							<td></td>
							
							<td>原内容类型</td>
							<td style="width: 160px;">
								<select class="form-control select-sm" name="sourceType" id="sourceType">
									<option value="">全部</option>
									<option value="1">调查</option>
									<option value="2">测评</option>
									<option value="3">投票</option>
									<option value="4">推文</option>
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
                <button type="button" onclick="add_ProductRecommend();" class="btn btn-default btn-sm">添加</button>
                <button type="button" onclick="dele_ProductRecommend();" class="btn btn-default btn-sm">删除</button>
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
                  <th>源内容 ID</th>
                  <th>源内容类型</th>
                  <th>源内容标题</th>
                  <th>关联内容</th>
                </tr>         
                </thead>
                <tbody>
                <c:forEach items="${list_ProductRecommend_Vo }" var="productRecommend_Vo" varStatus="n">
	                 <tr>
	                  <td>
	                  	<input type="checkbox" name="check_ProductManage_Id" value="${productRecommend_Vo.id }">
	                  	<input type="hidden" name="showOrder" value="${productRecommend_Vo.showOrder }">
	                  </td>
	                  <td>${n.index+1}</td>
	                  
	                  <td>${productRecommend_Vo.sourceId }</td>
	                  <td>
	                  	<c:if test="${productRecommend_Vo.sourceType == 1}">调查</c:if>
	                  	<c:if test="${productRecommend_Vo.sourceType == 2}">测评</c:if>
	                  	<c:if test="${productRecommend_Vo.sourceType == 3}">投票</c:if>
	                  	<c:if test="${productRecommend_Vo.sourceType == 4}">推文</c:if>
	                  </td>
	                  <td>${productRecommend_Vo.sourceTitle }</td>

	                  <td>
						<button type="button" onclick="select_Recommend_target(${productRecommend_Vo.sourceId },${productRecommend_Vo.sourceType})" 
								class="btn btn-default btn-sm">查看</button>
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
  function add_ProductRecommend(){
	  
	  window.location.href = "${ctx}/platform/add_ProductRecommend.do";
  }
  
  // 查询 目标列表
  function select_Recommend_target(sourceId,sourceType){
	  
	  window.location.href = "${ctx}/platform/list_ProductRecommend_target.do?sourceId="+sourceId+"&sourceType="+sourceType;
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