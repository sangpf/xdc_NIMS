<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>玩校联合登录地址配置</title>
</head>
<body class="hold-transition skin-blue ">
	<div class="box box-primary">
	
         <!-- form start -->
         <form id="pageListForm">
         	<table style="border-spacing: 10px;border-collapse: separate;">
         		
         		<tr></tr>
         		<tr>
         			<td style="color: red;"><label>&nbsp;&nbsp; 注意事项  &nbsp;: &nbsp;</label></td> 
         			<td style="font-size: 16px;font-style: normal;">
         				<p>生成玩校联合登录短地址功能, 使用到第三方公司新浪的生成短地址工具,用于将长链接转换成短链接</p>
         				<p>所以在使用该功能前, 需要先打开新浪的登录地址, 需要输入帐号登录  , 我们已经提供了帐号密码 , 请填写下方的帐号密码用于登录</p>
         				<p>登录后,将原问卷地址粘贴到相应的栏目下(第一栏), 然后点击生成按钮即可 </p>
         				<p style="color: red;">账户号 : 18813151120     密码 : sang123456  </p>
         				<p style="color: red;">另外,新浪的登录有时候需要多点击几次才能登录</p>
         			</td>
         		</tr>
         		
         	    <tr>
         			<td><label>&nbsp;&nbsp; 原问卷地址 &nbsp;: &nbsp;</label></td> 
         			<td>
         				<input type="text" id="originalUrl" name="originalUrl" value="" class="form-control">
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp; 生成长链接 &nbsp;: &nbsp;</label></td> 
         			<td>
         				<input type="text" id="longUrl" name="longUrl" value="" class="form-control">
         			</td>
         		</tr>
         		
         		<tr>
         			<td><label>&nbsp;&nbsp; 生成短链接 &nbsp;: &nbsp;</label></td> 
         			<td>
         				<input type="text" id="shortUrl" name="shortUrl" value="" class="form-control">
         			</td>
         		</tr>

         	</table>
	         
           <div class="box-footer">
             <button type="button" onclick="login_xl()" class="btn btn-primary">登录</button>
             <button type="button" onclick="generate_url()" class="btn btn-primary">生成</button>
           </div>
           
         </form>
       </div>
	<!-- /.box -->
	<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script>
	//添加超级调查页面
	function generate_url(){

		var originalUrl = document.getElementById("originalUrl").value;
		
	    $.ajax({
	         url: "${ctx}/platform/get_wanxLoginUrl.do",
	         type: 'POST',  
	         dataType : "json",
	         data: {
	        	 originalUrl : originalUrl
	         },   
	         success: function (data) {
	        	 
	        	 var url_short = data.url_short;
	        	 
	        	 if(typeof(url_short)=="undefined"){
					showMessageDialog("先点击登录");	
	        		
	        	 } 
	        	 
	        	 document.getElementById("longUrl").value = data.wanxAuthorizeUrl_long;
	        	 document.getElementById("shortUrl").value = url_short;
	         },  
	         error: function (data) {
	             showMessageDialog("网络异常");  
	         } 
	    });
		
	}
	
	// 登录新浪
	function login_xl(){
		
		$.ajax({
	    	url : "${ctx}/platform/XinLang_WeiBoLogin.do",
	    	dataType : "json",
	    	type : "post",
	    	success : function(data){
	    		if(data.loggedIn == true){
	    			// 已经登录
	    		}else{
	    			window.location.href = data.locationUrl;
	    		}
	    	},
	    	error : function(){
	    		showMessageDialog("网络异常");  
	    	}
	    	
	    }); 
	}
	
	// 点击 text 全选文本内容
	$(function(){
	    $(":text").click(function() {
	        $(this).select();
	    });

	});
	
	</script>
</body>
</html>
