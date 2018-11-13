<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title> 集体会员 --已购买产品包列表</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${ctx}/js/sang.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    
    <!-- 内容部分 -->
        <div class="panel panel-default">
          <div class="panel-body">
            <form class="form-horizontal"  method="post" action="${ctx}/platform/findAll_SchoolMember.do" id="searchform">
                <div class="input-group input-group-sm col-sm-3">
                	<input type="hidden" value="${schoolDict.schoolId }" id="schoolId">
                  		学校名称 : ${schoolDict.schoolName }
                </div>
            </form>
          </div>
        </div>
        
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="button-group">
                <button type="button" onclick="add_School_ProductPackage(${schoolDict.schoolId });" class="btn btn-default btn-sm">添加产品包</button>
                <button type="button" onclick="dele_School_ProductPackage();" class="btn btn-default btn-sm">删除</button>
                <button type="button" onclick="back_toList()" class="btn btn-default btn-sm">回列表</button>
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
                  <th>产品包ID</th>
                  <th>产品包标题</th>
                  <th>渠道</th>
                  <th>开通时间</th>
                  <th>结束时间</th>
                  <th>状态</th>
                  <th style="width: 30px">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listProductPackage }" var="productPackage" varStatus="n">
	                 <tr>
	                  <td>
	                  	<input type="checkbox" name="checkSchoolMember" value="${productPackage.id }">
	                  </td>
	                  <td>${n.index+1}</td>
	                  <td>${productPackage.packagetId }</td>
	                  <td>${productPackage.title }</td>
	                  <td>
	                  	<c:if test="${productPackage.channelId == 1}">完美校园</c:if>
	                  	<c:if test="${productPackage.channelId == 2}">微信</c:if>
	                  </td>
	                  <td>
	                  		<fmt:formatDate value="${productPackage.bTime }" pattern="yyyy-MM-dd"/>
	                  </td>
	                  <td>
	                  		<fmt:formatDate value="${productPackage.eTime }" pattern="yyyy-MM-dd"/>
	                  </td>
	                  
	                  <td>
	                  	<c:if test="${productPackage.status == 0}">无效</c:if>
	                  	<c:if test="${productPackage.status == 1}">有效</c:if>
	                  </td>
	                  
	                  <td>
	                  	  <input type="button" value="编辑" onclick="editSchoolMember(${productPackage.id})">
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

 // 跳转到添加页面
  function add_School_ProductPackage(schoolId){
	  
	  window.location.href = "${ctx}/platform/add_School_ProductPackage.do?schoolId="+schoolId;
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
  
  function back_toList(){
	  window.location.href = "${ctx}/platform/findAll_SchoolMember.do";
  }

  </script>
</body>

</html>