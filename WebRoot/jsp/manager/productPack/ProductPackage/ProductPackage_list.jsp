<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title> 产品包列表</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${ctx}/js/sang.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    
    <!-- 内容部分 -->
        <div class="panel panel-default">
          <div class="panel-body">
            <form class="form-horizontal"  method="post" action="${ctx}/platform/list_ProductPackage.do" id="searchform">
                <div class="input-group input-group-sm col-sm-3">
                  <input id="tweetIdOrName" name="tweetIdOrName" type="text" class="form-control" placeholder="输入学校id或名称查询">
                  <span class="input-group-btn">
                      <button type="button" onclick="search_ProductPackage()" class="btn btn-info btn-flat">查询</button>
                   </span>
                </div>
            </form>
          </div>
        </div>
        
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="button-group">
                <button type="button" onclick="add_ProductPackage();" class="btn btn-default btn-sm">添加</button>
                <button type="button" onclick="update_ProductPackage_status();" class="btn btn-default btn-sm">上下架</button>
                <button type="button" onclick="dele_ProductPackage();" class="btn btn-default btn-sm">删除</button>
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
                  <th>ID</th>
                  <th>渠道</th>
                  <th>状态</th>
                  <th>标题</th>
                  <th>原价</th>
                  <th>优惠价</th>
                  <th>标签1</th>
                  <th>标签2</th>
                  <th>标签3</th>
                  <th>产品介绍</th>
                  <th style="width: 35px;">操作</th>
                  <th style="width: 35px;">内容列表</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list_ProductPackage }" var="ProductPackage" varStatus="n">
	                 <tr>
	                  <td>
	                  	<input type="checkbox" name="check_ProductPackage" value="${ProductPackage.id }">
	                  	<input type="hidden" name="productPackage_Stat" value="${ProductPackage.status }">
	                  </td>
	                  <td>${n.index+1}</td>
	                  <td>${ProductPackage.id }</td>
	                  <td>
	                  	<c:if test="${ProductPackage.channelId == 1}">玩校</c:if>
	                  	<c:if test="${ProductPackage.channelId == 2}">微信</c:if>
	                  </td>
	                  
	                  <td>
	                  	<c:if test="${ProductPackage.status == 1}">已上架</c:if>
	                  	<c:if test="${ProductPackage.status == 2}">待上架</c:if>
	                  </td>
	                  
	                  <td>${ProductPackage.title }</td>
	                  <td>${ProductPackage.price }</td>
	                  <td>${ProductPackage.discount }</td>
                	  <td>${ProductPackage.tag1 }</td>
	                  <td>${ProductPackage.tag2 }</td>
	                  <td>${ProductPackage.tag3 }</td>
	                  <td>
                  		<div style="width: 250px;white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
                  			${ProductPackage.introduce }
                  		</div>
	                  </td>
	                  <td>
	                  	  <input type="button" value="编辑" class="btn btn-default btn-sm" onclick="edit_ProductPackage(${ProductPackage.id})">
	                  </td>
	                  <td>
	                  	  <input type="button" value="查看" class="btn btn-default btn-sm" onclick="select_product_manage(${ProductPackage.id})">
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
 
 // 上下架
 function update_ProductPackage_status(){
	  var check_ProductPackage_arr = document.getElementsByName("check_ProductPackage");
	  var productPackage_Stat_arr = document.getElementsByName("productPackage_Stat");
	  
	  var count = 0;
	  var productPackageId_list = "";
	  for(var i=0;i<check_ProductPackage_arr.length; i++){
		  
		  if(check_ProductPackage_arr[i].checked){
			  // 当前记录状态 
			  var productPackage_Stat = productPackage_Stat_arr[i].value;
			  
			  count++;
			  productPackageId_list = check_ProductPackage_arr[i].value+"!"+productPackageId_list;
		  }
	  }
	  
	  if(count == 0){
		  showMessageDialog("未选中");
		  return false;
	  }
	  
	  $.ajax({
		  url : "${ctx}/platform/update_ProductPackage_status.do",
		  data : {
			  productPackageId_list : productPackageId_list
		  },
		  dataType : "json",
		  type : "post",
		  success : function(data){
				showMessageDialog(data.msg, function(){
					if(data.success){
						window.location.href = "${ctx}/platform/list_ProductPackage.do";
					}
				});
		  }, 
		  error : function(){
			  showMessageDialog("网络异常");
		  }
		  
		  
	  });
	  
	  
 }
 
 
  //添加
  function add_ProductPackage(){
	  window.location.href = "${ctx}/platform/add_ProductPackage.do";
  }
  
  // 查询产品包下的内容
	function select_product_manage(productId){
		
		window.location.href = "${ctx}/platform/select_product_manage.do?productId="+productId;
	}

  //删除
  function deleSchoolMember(){
	  var checkSchoolMember_arr = document.getElementsByName("checkSchoolMember");
	  
	  var count = 0;
	  var schoolId_arr = "";
	  
	  for(var i=0;i<checkSchoolMember_arr.length;i++){
		  if(checkSchoolMember_arr[i].checked){
			  count++;
			  schoolId_arr = checkSchoolMember_arr[i].value + "|" +schoolId_arr;
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
					  url:"${ctx}/platform/dele_SchoolMember.do?schoolId_arr="+schoolId_arr,
					  success:function(data){
						  
						  showMessageDialog(data.msg, function(){
							  if(data.success){
								  window.location.href = "${ctx}/platform/findAll_SchoolMember.do";
							  }
						  });
					  }
				  }); 
		  }
	  });

  }
  
  // 编辑
  function edit_ProductPackage(packageId){
	  
	  window.location.href = "${ctx}/platform/add_ProductPackage.do?productPackage_Id="+packageId;
  }
  
  function dele_ProductPackage(){
	  
  }
  </script>
</body>

</html>