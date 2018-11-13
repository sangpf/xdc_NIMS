<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>测评问卷信息管理</title>
  </head>
  
  <body>
	     <!-- Horizontal Form -->
          <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">录入测评问卷信息</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form id="pageListForm" class="form-horizontal">
              <div class="box-body">
              
                <div class="form-group">
                  <label for="inputEmail3" class="col-sm-2 control-label">问卷标题</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="aqnName" style="width: 450px">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputEmail3" class="col-sm-2 control-label">问卷简介</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="aqnSummary" style="width: 450px">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputEmail3" class="col-sm-2 control-label">题目数量</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="questionNum" style="width: 450px">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputEmail3" class="col-sm-2 control-label">测评模型</label>
                  <div class="col-sm-10">
                    <!-- <input type="text" class="form-control" name="aqnCategory" style="width: 450px"> -->
                    <select name="aqnCategory" style="width: 200px;height: 35px;">
                    	<option value="0">简单测评模型</option>
                    	<option value="1">多维度测评模型</option>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputEmail3" class="col-sm-2 control-label">出题人</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="publisherName" style="width: 450px">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputEmail3" class="col-sm-2 control-label">问卷分类</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="aqnClassId" style="width: 450px">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputEmail3" class="col-sm-2 control-label">问卷编辑人</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="editor" style="width: 450px">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="inputEmail3" class="col-sm-2 control-label">问卷图片</label>
                  <div class="col-sm-10">
                    <input type="file" onchange="PreviewheadImage(this);" name="headImg" style="width: 350px;height: 35px">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputPassword3" class="col-sm-2 control-label">图片预览</label>
                  <div class="col-sm-10">
                     <img id="headImg" alt="" src="" style="width: 300px;height: 200px">
                  </div>
                </div>
                
                
              </div>
              
              <!-- /.box-body -->
              <div class="box-footer">
                <button type="button" class="btn btn-default">取消</button>
                <button type="button" onclick="saveNiAssessQuestionnaire();" class="btn btn-info">确定</button>
              </div>
              <!-- /.box-footer -->
            </form>
          </div>  	
    
  <script src="${ctx}/static/js/validate.js"></script>
  <script type="text/javascript">
  		
  //保存轮播图
  function saveNiAssessQuestionnaire(){
	  var formData = new formData($("#pageListForm"));
	  $.ajax({
			url : "${ctx}/platform/saveTeacherInformation.do",
			type : 'POST',
			data : formData,
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			success : function(data) {
				showMessageDialog(data.msg);
			},
			error:function(data) {
				showMessageDialog("网络异常");
			}
	  });
  }
  
  
//预览头像图片
  function PreviewheadImage(imgFile){
  	var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
  	if (!pattern.test(imgFile.value)) {
  		alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
  		imgFile.focus();
  	} else {
  		var path;
  		if (document.all)//IE 
  		{
  			imgFile.select();
  			path = document.selection.createRange().text;
  			document.getElementById("headImg").innerHTML = "";
  			document.getElementById("headImg").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\""
  					+ path + "\")";//使用滤镜效果 
  		} else//FF 
  		{
  			path = URL.createObjectURL(imgFile.files[0]);
  			$("#headImg").attr("src", path);
  		}
  	}
  }
  
  </script>
  </body>
</html>
