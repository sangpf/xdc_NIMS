<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>登录</title>
<link rel="stylesheet" href="${ctx }/static/css/normalize.css" />
<link rel="stylesheet" href="${ctx }/static/css/backstage.css" />
<link rel="stylesheet" href="${ctx }/static/css/font-awesome.min.css">
</head>
<body class="login">
    <header class="login-head">
        <div class="login-head-container">
            <h1>业务管理平台</h1>
        </div>
    </header>
    <div class="login-body">
        <div class="login-body-container">
            <h2>账户登录</h2>
            <form action="" autocomplete="off">
                <ul>
                    <li>
                        <i class="fa fa-w fa-user"></i>
                        <input type="text" class="useraccountOremail" name="useraccountOremail" id="useraccountOremail" placeholder="用户名" autocomplete="off">
                    </li>
                    <li>
                        <i class="fa fa-w fa-lock"></i>
                        <input type="text" class="userPassword" name="userPassword" id="userPassword" placeholder="密码" autocomplete="off" onfocus="this.type='password'" onblur="this.type='password'">
                    </li>
                    <li>
                    	<ul class="login-func">
                    		<li>
		                        <input type="checkbox" name="remember" id="remember">
		                        <label for="remember">记住登录信息</label>
                    		</li>
                    		<li>
		                        <input type="checkbox" name="autologin" id="autologin">
		                        <label for="autologin">自动登录</label>
                    		</li>
                    	</ul>
                    </li>
                    <li>
                        <input type="button" class="loginbtn" value="登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录" onclick="loginUser()">
                    </li>
                    <div class="userErrorMsg" id="userErrorMsg"></div>
                </ul>
            </form>
            <div class="login-loading" id="login-loading"></div>
        </div>
    </div>
    <footer class="login-foot">
        <div class="login-foot-container">
            <p>Copyright &copy; 2016 新洞察 超级问卷 | All Rights Reserved</p>
        </div>
    </footer>
	<script src="${ctx}/static/js/jquery-2.1.1.min.js"></script>
    <script src="${ctx}/static/js/jquery.cookie.js"></script>
    <script type="text/javascript">
    // 读取cookie
    $(document).ready(function(){
    	// 根据是否记住登录信息，初始化各组件
        if ($.cookie("rmbUser") == "true") { 
            $("#remember").prop("checked", true);
            $("#autologin").prop("disabled", false);
            $("#useraccountOremail").val($.cookie("account")); 
            $("#userPassword").val($.cookie("password")).attr('type','password');  
        } else {
            $("#remember").prop("checked", false);
            $("#autologin").prop("disabled", true).next('label').css('color','#777');
            $("#useraccountOremail").val(""); 
            $("#userPassword").val(""); 
        }

        // 根据是否自动登录，初始化
        if (localStorage.getItem("autologin") == "true") {
        	$("#autologin").prop("checked", true);
        	loginUser();
        } else {
			$("#autologin").prop("checked", false);
        }
    })

    //enter键绑定登录功能
    $(document).keydown(function(event) {
        if (event.keyCode == "13") {
            loginUser();
        }
    });

    // 根据“记住登录信息”改变“自动登录”状态
    $('#remember').change(function(event) {
    	if($(this).prop("checked")){
            $("#autologin").prop("disabled", false).next('label').css('color','#333');
    	} else {
            $("#autologin").prop({
            	'disabled': true,
            	'checked': false
            }).next('label').css('color','#777');
    	}
    });

    function loginUser() {
        var errorMsg = $("#userErrorMsg");
        var account = $.trim($("#useraccountOremail").val());
        var password = $.trim($("#userPassword").val());
        var loading = $("#login-loading");
        errorMsg.html(" ");

        // 合法性验证
        if (account == "") {
            errorMsg.html("请输入用户名");
            $("#useraccountOremail").focus();
            return false;
        } else if (password == "") {
            errorMsg.html("请输入密码");
            $("#userPassword").focus();
            return false;
        }

        // 记住用户名密码
        if ($("#remember").prop("checked")) {
            //存储一个带期限的cookie 
            var expires = 14;
            $.cookie("rmbUser", "true", {
                expires: expires
            }); 
            $.cookie("account", account, {
                expires: expires
            });
            $.cookie("password", password, {
                expires: expires
            });
        } else {
            $.cookie("rmbUser", "false", {
                expire: -1
            });
            $.cookie("account", "", {
                expires: -1
            });
            $.cookie("password", "", {
                expires: -1
            });
        }

		// 自动登录
        if ($("#autologin").prop("checked")) {
            localStorage.setItem("autologin", "true");
        } else {
            localStorage.setItem("autologin", "false");
        }

        // 请求后台接口
        $.ajax({
            url: '${ctx}/managerUser/manageUserlogin.do',
            type: 'post',
            dataType: 'json',
            data: {
                account: account,
                password: password
            },
            beforeSend: function() {
                // 加载动画
                loading.fadeIn('fast');
            },
            success: function(data) {
                if (data.success) {
                    /* showMessageDialog("登录成功"); */
                    window.location.href = "${ctx}/platform/NiSurveyQuestionnaireList.do";
                } else {
                    loading.fadeOut('fast');
                    errorMsg.html(data.error);
                    $("#useraccountOremail").focus().select();
                    $('#userPassword').val('');
                }
            }
        });
    }
    </script>
</body>

</html>
