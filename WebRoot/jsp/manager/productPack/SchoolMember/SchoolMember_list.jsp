<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title> 集体会员列表</title>
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
                  <input id="tweetIdOrName" name="tweetIdOrName" type="text" class="form-control" placeholder="输入学校id或名称查询">
                  <span class="input-group-btn">
                      <button type="button" onclick="searchSchoolMember()" class="btn btn-info btn-flat">查询</button>
                   </span>
                </div>
            </form>
          </div>
        </div>
        
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="button-group">
                <button type="button" onclick="addSchoolMember();" class="btn btn-default btn-sm">添加</button>
                <button type="button" onclick="deleSchoolMember();" class="btn btn-default btn-sm">删除</button>
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
                  <th>学校ID</th>
                  <th>学校名称</th>
                  <th>开通时间</th>
                  <th>结束时间</th>
                  <!-- <th style="width: 30px">操作</th> -->
                  <th style="width: 80px;">已购产品包</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${SchoolMember_list }" var="schoolMember" varStatus="n">
	                 <tr>
	                  <td>
	                  	<input type="checkbox" name="checkSchoolMember" value="${schoolMember.schoolId }">
	                  </td>
	                  <td>${n.index+1}</td>
	                  <td>${schoolMember.schoolId }</td>
	                  <td>${schoolMember.schoolName }</td>
	                  <td>
	                  		<fmt:formatDate value="${schoolMember.bTime }" pattern="yyyy-MM-dd"/>
	                  </td>
	                  <td>
	                  		<fmt:formatDate value="${schoolMember.eTime }" pattern="yyyy-MM-dd"/>
	                  </td>
	                  <%-- <td>
	                  	  <input type="button" value="编辑" onclick="editSchoolMember(${schoolMember.schoolId})">
	                  </td> --%>
	                  <td><input type="button" value="查询" onclick="search_School_ProductPackage(${schoolMember.schoolId})"></td>
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
  function addSchoolMember(){
	  window.location.href = "${ctx}/platform/add_SchoolMember.do";
  }

  function search_School_ProductPackage(schoolId){
	  
	  window.location.href = "${ctx}/platform/search_School_ProductPackage.do?schoolId="+schoolId;
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

  </script>
</body>

</html>