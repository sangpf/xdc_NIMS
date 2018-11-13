<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>奖品管理</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
</head>

<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    
    <!-- 内容部分 -->

      <!-- Main content -->
      <section class="content">
        <div class="panel panel-default">
          <div class="panel-body">
            <form class="form-horizontal"  method="post" action="${ctx}/platform/awardList.do" id ="pageListForm">
              <div class="row mb10">
                <div class="col-sm-2">
                  <select class="form-control select-sm" id="searchOption" name = "searchOption">
                    <option value="">请选择</option>
                    <option value="1">奖品名称</option>
                    <option value="2">提供者</option>
                    <option value="3">奖品ID</option>
                  </select>
                </div>
                <div class="input-group input-group-sm col-sm-3">
                  <input id="searchContent" type="text" class="form-control" name = "searchContent">
                  <span class="input-group-btn">
                      <button type="button" onclick="searchAward()" class="btn btn-info btn-flat">Go!</button>
                   </span>
                </div>
              </div>
              <div class="row mb10">
                <div class="col-sm-3">
                  <label class="select-label">市场价格</label>
                  <select class="form-control select-sm" id="price" style="width:50%;" name = "price">
                    <option value="">不限</option>
                    <option value="1">5元以下</option>
                    <option value="2">5-10元</option>
                    <option value="3">10-20元</option>
                    <option value="4">20-50元</option>
                    <option value="5">50-100元</option>
                    <option value="6">100-200元</option>
                    <option value="7">200-500元</option>
                    <option value="8">500-1000元</option>
                    <option value="9">1000元以上</option>
                  </select>
                </div>
               <div class="col-sm-3">
                <p class="ft-12 pd-15">
                  <label class="select-label">类型</label>
                  <select class="form-control select-sm" id="awardType" style="width: 50%;" name="awardType">
                  	<option value="">不限</option>
                  	<option value="1">实物</option>
                  	<option value="2">外链</option>
                  	<option value="3">玩校积分</option>

                  </select>
                </p>
                </div>
              <div class="col-sm-3">
                <label class="select-label">货品状态</label>
               	 <select class="form-control select-sm" id="status" style="width: 50%;" name="status">
                  	<option value="">不限</option>
                  	<option value="1">无效</option>
                  	<option value="2">有效</option>
                  	<option value="3">失效</option>
                </select>
                </div>
              </div>              
           </form>
          </div>

        </div>
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="button-group">
              <a href="${ctx}/platform/awardAdd.do">
                <button type="button" class="btn btn-default btn-sm">添加</button>
              </a>
              <a href="#">
                <button type="button" onclick="deleteAward()" class="btn btn-default btn-sm">删除</button>
              </a>
            </div>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">奖品列表</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th></th>
                  <th>编号</th>
                  <th>奖品ID</th>
                  <th>奖品名称</th>
                  <th>提供者</th>
                  <th>类型</th>
                  <th>数量</th>
                  <th>市场价格</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                   <c:forEach items="${awardList}" var="awardItem" varStatus="n">
                      <tr>
                        <td><input type="checkbox" id = "checkAward" name="checkAward" value="${awardItem.awardId }"></td>
                        <td>${n.index+1}</td>
                        <td>${awardItem.awardId}</td>
                        <td>${awardItem.awardName}</td>
                        <td>${awardItem.provider}</td>
                        <td>
                        	<c:if test="${awardItem.awardType == 1}">实物</c:if>
                         	<c:if test="${awardItem.awardType == 2}">外链</c:if>
                         	<c:if test="${awardItem.awardType == 3}">玩校积分</c:if>
                        </td>
                        <td>${awardItem.awardNum}</td>
                        <td>${awardItem.price}元</td>                       
                        <td>
                        <button type="button" onclick="toAwardEditPage(${awardItem.awardId})" class="btn btn-default btn-sm">编辑</button>
                         </td>
                      </tr>
                  </c:forEach>

                </tbody>
              </table>
            </div>
          </div>
        </div>
    </section>
    </div>
    
    <!-- 内容部分结束 -->
    <!-- 编辑奖品模态框 -->
    <form id="pageEditForm" method="post" action="${ctx}/platform/updateAward.do" enctype="multipart/form-data">
    <div class="modal fade" id="editAward" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	 <div class="modal-dialog">
    	 	<div class="modal-content">
    	 		 <div class="modal-header">
            		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
           			<h4 class="modal-title" id="myModalLabel">编辑奖品</h4>
           		</div><!-- /.modal-modal-header -->
           		<div class="modal-body">
           		<form>
           		<table style="border-spacing: 10px;border-collapse: separate;">
         		<tr>
         			<td col width="80"><label>&nbsp;&nbsp;货品ID&nbsp;: &nbsp;</label></td> 
         			<td colspan="4"><input id="awardPoolId" name="awardPoolId" onblur="searchAwardPool()"  type="text" maxlength="28" style="width: 200px;height: 30px;" class="form-control" readonly = "readonly" placeholder="请输入奖品类别ID"></td>
         		</tr>
         		<tr>
         			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td> 
         			<td col width="80">货品名称:</td>
         			<td col colspan="1" id="awardPoolName"></td>
         			<td col colspan="1" id="Awardstatus"></td>
         			<td col colspan="1" id="Type"></td>
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;奖品ID&nbsp;:&nbsp;</label></td>
         			<td colspan="3"><input id="awardId"  name="awardId" type="text" maxlength="28" style="width: 200px;height: 30px;" class="form-control" readonly = "readonly"></td>     			
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;奖品名称&nbsp;:&nbsp;</label></td> 
         			<td colspan="3"><input id="awardName"  name="awardName" type="text" maxlength="28" style="width: 200px;height: 30px;" class="form-control" placeholder="请输入奖品名称"></td>     			         			
         		</tr> 
         		<tr>
         			<td><label>&nbsp;&nbsp;发放数量&nbsp;:&nbsp;</label></td>
         			<td colspan="3"><input id="awardNum"   name="awardNum" type="text" maxlength="28" style="width: 200px;height: 30px;" class="form-control" placeholder="请输入发放数量"></td>     			
         		</tr>
         		<tr>
					<td><label>&nbsp;&nbsp;选择图片&nbsp;: &nbsp;</label></td>
					<td colspan="4"><input type="file" name="uploadImage"
						id="uploadImage" onchange="PreviewImage(this)" value="${url}"></td>
						
 					<td><img height="40px" width="60px" id="imgurl" src="" class="img-rounded"></td> 
					<td></td>
				</tr>   
         		<tr>
					<td><label>&nbsp;&nbsp;中奖提示&nbsp;:&nbsp;</label></td>
					<td colspan="4"><textarea name="comment" id="comment" class="form-control" style="width: 300px;" rows="3" placeholder="请输入中奖提示信息"></textarea></td>
				</tr>      		       		
         	</table>
           		</form>           			
           		</div><!-- /.modal-body -->
           		<div class="modal-footer">
            		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            		<button type="button" class="btn btn-primary" onclick="updateAward();">提交更改</button>
         		</div><!-- /.modal-footer -->
    	 	</div><!-- /.modal-content -->
    	 </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</form>
  <div class="control-sidebar-bg"></div>
  </div>
  <!-- ./wrapper -->
  <script src="${ctx}/static/js/moment.min.js"></script>
  <script src="${ctx}/js/validate.js"></script>
  <script>
  
  //分页查询
  function searchAward(){
	  $('#pageListForm').submit();
  }
  
  //删除奖品
  function deleteAward(){
	  var checkAward = document.getElementsByName("checkAward");
	  var count = 0 ;
	  var awardId = "";
	  for(var i=0;i<checkAward.length;i++){
		  if(checkAward[i].checked){
			  count++;
			  awardId = checkAward[i].value+"!"+awardId;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择要删除的奖品");
		  return;
	  }
		
	  showConfirmDialog(
				"确定要删除奖品吗?",
				function(check) {
					if (check) {
						  $.ajax({
							  url:"${ctx}/platform/deleteAward.do?awardId="+awardId,
						  	  type:"post",
							  dataType:"json",
							  success:function(data){
								  showMessageDialog(data.msg,function(){
								  if(data.success){
									  window.location.href = "${ctx}/platform/awardList.do";
								  }
							  })
							  }
							  });

					}
				});
 }
  
  //跳到编辑模态框
  function toAwardEditPage(awardId){	 
	 /*  $.ajax({
		  url:"${ctx}/platform/toAwardEditPage.do",
	  	  type:"post",
		  dataType:"json",
		  data:{
			  awardId : awardId
		  },
		  success:function(data){
			  $('#editAward').modal('show');		 
				  $(".img-rounded").attr("src",data.awardPic);
				  
				  document.getElementById("awardPoolId").value=data.awardPoolId; 
				  document.getElementById("awardPoolName").innerHTML=data.awardPoolName;
				  document.getElementById("Type").innerHTML=data.awardType;
 				   document.getElementById("awardId").value=data.awardId;
				  document.getElementById("awardName").value=data.awardName;
				  document.getElementById("awardNum").value=data.awardNum; 
				  document.getElementById("comment").value=data.comment;
				  document.getElementById("uploadImage").value=data.awardPic;
				  
				  if(data.status==1){
						document.getElementById("Awardstatus").innerHTML="无效";	
					}
					else if(data.status==2){
						document.getElementById("Awardstatus").innerHTML="有效";	
					}
					else {
						document.getElementById("Awardstatus").innerHTML="失效";
					}

		  },
		  error: function(data) {
			  showMessageDialog("请求失败");
		  }
		  }); */
	  window.location.href = "${ctx}/platform/toAwardEditPage.do?awardId="+awardId;
  }
//根据货品ID（即奖品类别ID）查询货品信息
  function searchAwardPool(){
		var awardPoolId = $('#awardPoolId').val();
		$.ajax({
			  url:'${ctx}/platform/searchAwardPool.do',
			  type:'post',
			  dataType:'json',
			  data:{			 
				  awardPoolId : awardPoolId	 
			  },
			  success:function(data){	
				  document.getElementById("awardPoolName").innerHTML=data.awardPoolName;
				  	document.getElementById("Type").innerHTML=data.awardType;
					if(data.status==1){
						document.getElementById("Awardstatus").innerHTML="无效";	
					}
					else if(data.status==2){
						document.getElementById("Awardstatus").innerHTML="有效";	
					}
					else {
						document.getElementById("Awardstatus").innerHTML="失效";
					}
				  			  
			  },
			  error: function(data) {
				  document.getElementById("awardPoolName").innerHTML="未查询到奖品信息"; 
				  document.getElementById("Awardstatus").innerHTML="";
				  document.getElementById("Type").innerHTML="";
				  }
		  });
		//window.location.href = "${ctx}/platform/searchAwardPool.do?awardPoolId="+awardPoolId
	}

  //提交奖品编辑信息
  function updateAward(){
/* 	  var awardPoolId = $('#awardPoolId').val();  //奖品类别ID
	  var awardId = $('#awardId').val(); //奖品Id
	  var awardNum = $('#awardNum').val(); //发放数量
	  var awardName = $('#awardName').val();   //奖品名称 */
	  var formData = new FormData($( "#pageEditForm")[0]);	  
	  $.ajax({
		  url:'${ctx}/platform/updateAward.do',
		  type:'post',
		  /*  dataType:'json', 
		  data:{
			  awardPoolId : awardPoolId,
			  awardNum : awardNum,
			  awardName : awardName,
			  awardId : awardId
		  }, */
		  data:formData, 
	         async: false,  
	         cache: false,  
	         contentType: false,  
	         processData: false, 
		  success:function(data){
			  
			  if(data.success){	
				  $('#editAward').modal('hide');
				  showMessageDialog(data.msg,function(){
						 if(data.success){
							 window.location.href = "${ctx}/platform/awardList.do";
						  }
					  })
				 				  
			  }
		  }
		  
	  });
	  
}
  //本地图片预览
  function PreviewImage(imgFile) {
		var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
		if (!pattern.test(imgFile.value)) {
			showMessageDialog("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
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
  </script>
</body>

</html>