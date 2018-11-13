<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
	 <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>添加奖品</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${ctx}/BS/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css">
  <link rel="stylesheet" href="${ctx }/BS/plugins/datatables/dataTables.bootstrap.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <link rel="stylesheet" href="${ctx }/BS/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="${ctx }/BS/dist/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="${ctx }/BS/plugins/iCheck/flat/blue.css">
  <link rel="stylesheet" href="${ctx }/BS/plugins/morris/morris.css">
  <link rel="stylesheet" href="${ctx }/BS/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
  <link rel="stylesheet" href="${ctx }/BS/plugins/datepicker/datepicker3.css">
  <link rel="stylesheet" href="${ctx }/BS/plugins/daterangepicker/daterangepicker-bs3.css">
  <link rel="stylesheet" href="${ctx }/BS/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <style>
        .mb10 {
            margin-bottom: 10px;
        }

        .select-label {
            float: left;
            line-height: 30px;
            font-size: 12px;
            margin-right: 10px;
        }

        .select-sm {
            height: 30px;
            font-size: 12px;
        }

        .ft-12 {
            font-size: 12px;
        }

        .pd-15 {
            padding: 0 15px;
        }

        .button-group button {
            width: 100px;
            margin: auto 10px;
        }
    </style>
</head>

<body   class="hold-transition skin-blue sidebar-mini" >

       <!-- Main content -->
        <div class="box box-primary">

         <!-- form start -->
         <form id="pageListForm" method="post" action="${ctx}/platform/addAward.do" enctype="multipart/form-data">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         		<tr>
         			<td col width="80"><label>&nbsp;&nbsp;货品名称&nbsp;: &nbsp;</label></td>      			
         			<%-- <td colspan="4"><input name="awardPoolId" id="awardPoolId" onblur="searchAwardPool()"value="${award.awardPoolId}" type="text" maxlength="28" style="width: 200px;height: 30px;" class="form-control" placeholder="请输入奖品类别ID"></td> --%>
         		  <td colspan="4"><select class="form-control select-sm" id="awardPoolId" style="width:50%;" name = "awardPoolId">
                     <c:forEach items="${awardPoolList}" var="awardPoolItem">
                    <option value="${awardPoolItem.awardPoolId}">${awardPoolItem.awardPoolName}</option>
                     </c:forEach>
                     </td>
                  </select>
         		</tr>
<!--          		<tr>
         			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td> 
         			<td col width="80">货品名称:</td>
         			<td col colspan="1" id="awardPoolName"></td>
         			<td col colspan="1" id="status"> </td>
         			<td col colspan="1" id="awardType"></td>
                   
         		</tr> -->
         		<tr>
         			<td><label>&nbsp;&nbsp;发放数量&nbsp;:&nbsp;</label></td>
         			<td colspan="4"><input name="awardNum" id="awardNum"  type="text" maxlength="28" style="width: 200px;height: 30px;" class="form-control" placeholder="请输入发放数量"></td>     			
         		</tr>
         		<tr>
         			<td><label>&nbsp;&nbsp;奖品名称&nbsp;:&nbsp;</label></td> 
         			<td colspan="4"><input name="awardName" id="awardName"  type="text" maxlength="28" style="width: 200px;height: 30px;" class="form-control" placeholder="请输入奖品名称"></td>     			         			
         		</tr> 
         		<tr>
					<td><label>&nbsp;&nbsp;选择图片&nbsp;:&nbsp;</label></td>
					<td colspan="4"><input type="file" name="uploadImage"
						id="uploadImage" onchange="PreviewImage(this)" value="${url}"></td>
					<td colspan="3"><img height="40px" width="60px" id="imgurl"
						src="" class="img-rounded"></td>
					<td></td>
				</tr>   
				<tr>
					<td><label>&nbsp;&nbsp;中奖提示&nbsp;:&nbsp;</label></td>
					<td colspan="5"><textarea name="comment" id="comment" class="form-control" style="width: 500px;" rows="3" placeholder="请输入中奖提示信息"></textarea></td>
				</tr>     		     		       		
         	</table>
	         
           <div class="box-footer">
            <!--  <button type="button" onclick="addAward();" class="btn btn-primary">保存</button> -->
            <input  type="button" onclick="addAward();" class="btn btn-primary" value="保存" />
              <button type="button" onclick="cancelAdd();" class="btn btn-primary">取消</button>
           </div>
           
         </form>
                
       </div>

<!-- /.content -->


<!-- jQuery 2.2.0 -->
<script src="${pageContext.request.contextPath}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>

<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<!-- <script>$.widget.bridge('uibutton', $.ui.button);</script> -->
  <script src="${ctx}/static/js/moment.min.js"></script>
  <script src="${ctx}/js/validate.js"></script>
<script>

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
			  	document.getElementById("awardType").innerHTML=data.awardType;
				if(data.status==1){
					document.getElementById("status").innerHTML="无效";	
				}
				else if(data.status==2){
					document.getElementById("status").innerHTML="有效";	
				}
				else {
					document.getElementById("status").innerHTML="失效";
				}
			  			  
		  },
		  error: function(data) {
			  document.getElementById("awardPoolName").innerHTML="未查询到奖品信息"; 
			  document.getElementById("status").innerHTML="";
			  document.getElementById("awardType").innerHTML="";
		  }
	  });
	//window.location.href = "${ctx}/platform/searchAwardPool.do?awardPoolId="+awardPoolId
}
//添加奖品
function addAward(){
/* 	  var awardPoolId = $('#awardPoolId').val();  //奖品类别ID
	  var awardNum = $('#awardNum').val(); //发放数量
	  var awardName = $('#awardName').val();   //奖品名称 */
	  var formData = new FormData($( "#pageListForm")[0]);	 
		var uploadImage = document.getElementById("uploadImage").value;
		if (uploadImage.length == 0) {
			showMessageDialog("请选择上传图片");
			return;
		}
	  
	  $.ajax({
		  url:'${ctx}/platform/addAward.do',
		  type:'post',
		 /*  dataType:'json', */
/* 		  data:{
			  awardPoolId : awardPoolId,
			  awardNum : awardNum,
			  awardName : awardName,
		  }, */
		  data:formData, 
	         async: false,  
	         cache: false,  
	         contentType: false,  
	         processData: false, 
		  success:function(data){
			  showMessageDialog(data.msg,function(){
				 if(data.success){
					  window.location.href = "${ctx}/platform/awardList.do";
				  }
			  })			 
		  }	  
	  });	  
}

//取消添加，跳转到列表页
function cancelAdd(){
	showConfirmDialog(
		"放弃添加奖品？",
		function(check){
			if(check){
				window.location.href = "${ctx}/platform/awardList.do";
			}
		})
}

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


