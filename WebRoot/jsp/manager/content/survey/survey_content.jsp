<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
<head>
  <title>超级调查</title>
  <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <!--引入mui css-->
  <link rel="stylesheet" href="${ctx}/css/mui.css" />
  <!--css样式-->
  <link rel="stylesheet" type="text/css" href="${ctx}/css/app.css" />
  <link rel="stylesheet" type="text/css" href="${ctx}/css/mui.picker.min.css" />
  <link rel="stylesheet" type="text/css" href="${ctx}/css/xdc_survey.css" />
  <!--mui js-->
  <script src="${ctx}/js/mui.min.js"></script>
  <script src="${ctx}/js/mui.picker.min.js"></script>
  <script src="${ctx}/js/config.js"></script>
  <style>
    h5 {
      padding-top: 15px;
      /*font-size: 1em;*/
    }
    
    label {
      /*font-size: 15px;*/
    }
    
    .field-contain label {
      width: auto;
      padding-right: 0;
    }
    
    .field-contain input[type='text'] {
      width: 40px;
      height: 30px;
      padding: 5px 0;
      float: none;
      text-align: center;
    }
    
    .ui-alert {
      text-align: center;
      padding: 20px 10px;
      font-size: 16px;
    }
  </style>
</head>

<body>


  <div class="mui-content" id="title" style="padding-left: 20px;">
  	<!--
  		这里包括题目，出题人，类型 
  		标题图， 题目的简介
  	-->
  </div>

  <div class="mui-content" style="padding-top:10px;padding-left: 20px;">
    <div id="list">
    	<!--
    		表单中的题目和选项
    	-->
    </div>
    <div class="mui-content">
      <div class="mui-content-padded">
        <p class="statement">
          声明：本次为匿名调查，您提供的信息将受到保护，请仔细作答，对不正常试卷将做无效处理！
        </p>
      </div>
    </div>
    <div id="subbtn" class="mui-content-padded" style="margin: 10px 15px;">
      <button class="mui-btn xdc-btn-submit mui-btn-block">仅提供预览</button>
    </div>

  </div>

  <script type="text/javascript" src="http://pingjs.qq.com/h5/stats.js" name="MTAH5" sid="500158849" cid="500158852"></script>
  <!--测试用腾讯云后台需要添加的代码，此时需要注释掉上面一段代码-->

<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/js/mustache.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		var titleTemplate = "\n     <div class=\"mui-content-padded\">\n       <h4 id=\"title\">\n           【{{questionQty}}题】{{sqnName}}\n         </h4>\n       <p class=\"title-desc\">\n         <span>出题人：{{publisherName}}</span> 类型：{{sqnClassName}}\n       </p>\n      <img class=\"img-titlePic\"  src=\"${picPath}\" alt=\"title-Picture\" style=\"width:300px;\">\n         <p style=\"line-height: 1.4;text-align:justify;\">\n         {{{sqnSummary}}}\n       </p>\n     </div>\n   ";

	    var template = "\n    <h5>\n      {{questionNum}}.{{sqTitle}}\n      <font color=\"red\">(单选)</font>\n    </h5>\n    <div>\n      <form class=\"mui-input-group\" id=\"q{{questionNum}}\" name=\"xdc-single-choice\" data-id=\"{{sqId}}\" data-isRequired=\"{{required}}\">\n        {{#options}}\n          <div class=\"mui-input-row mui-radio mui-left\">\n            <label>{{optionDes}}</label>\n            <input name=\"q{{questionNum}}\" value=\"{{optionOrder}}\" type=\"radio\"></input>\n          </div>\n        {{/options}}\n      </form>\n    </div>\n  ";
	    var templateMuti = "\n    <h5>\n      {{questionNum}}.{{sqTitle}}\n      <font color=\"red\">(多选)</font>\n    </h5>\n    <div>\n      <form class=\"mui-input-group\" id=\"q{{questionNum}}\" name=\"xdc-multiple-choice\" data-id=\"{{sqId}}\" data-isRequired=\"{{required}}\">\n        {{#options}}\n          <div class=\"mui-input-row mui-checkbox mui-left\">\n            <label>{{optionDes}}</label>\n            <input name=\"q{{questionNum}}\" value=\"{{optionOrder}}\" type=\"checkbox\"></input>\n          </div>\n        {{/options}}\n      </form>\n    </div>\n  ";

	    var templateNumInput = "\n    <h5>\n      {{questionNum}}.{{sqTitle}}\n      <font color=\"red\">(填空)</font>\n    </h5>\n    <div>\n      <form class=\"mui-input-group\" id=\"q{{questionNum}}\" name=\"xdc-self-define\" data-id=\"{{sqId}}\" data-isRequired=\"{{required}}\">\n       <div class=\"mui-input-row style='margin:10px 5px;'\" style=\"height:70px;\">\n            <textarea name=\"q{{questionNum}}\" maxlength=\"9\" oninput=\"handleCommentInputChangeNum(event)\" placeholder=\"请在这里输入数字答案\" onkeyup=\"this.value=this.value.replace(/[^0-9.]/g,'')\"></textarea>\n          </div>\n      </form>\n    </div>\n  ";
	    var templateTxtInput = "\n    <h5>\n      {{questionNum}}.{{sqTitle}}\n      <font color=\"red\">(填空)</font>\n    </h5>\n    <div>\n      <form class=\"mui-input-group\" id=\"q{{questionNum}}\" name=\"xdc-self-define\" data-id=\"{{sqId}}\" data-isRequired=\"{{required}}\">\n       <div class=\"mui-input-row style='margin:10px 5px;'\" style=\"height:70px;\">\n            <textarea name=\"q{{questionNum}}\" maxlength=\"140\" oninput=\"handleCommentInputChange(event)\" placeholder=\"请在这里输入答案\"></textarea>\n          </div>\n      </form>\n    </div>\n  ";

	    var title = document.getElementById("title");
	    var list = document.getElementById("list");	
	    
	    var sqnId = "${sqnId}";
		
		$.ajax("${ctx}/queView/loadSqn.do", {
			data: {
				sqnId : sqnId
			},
			dataType: 'json',
			type: 'get',
			timeout: 10000,
			success: function (data) {
				var titleFragment = Mustache.render(titleTemplate, data);
				title.innerHTML = titleFragment;
				var fragment = '';
				for (var i = 0, length = data.questions.length; i < length; i++) {
					if (data.questions[i].questionType == 1) {
						fragment = Mustache.render(template, data.questions[i]);
					} else if (data.questions[i].questionType == 2) {
						fragment = Mustache.render(templateMuti, data.questions[i]);
					} else if (data.questions[i].questionType == 3) {
						fragment = Mustache.render(templateNumInput, data.questions[i]);
					} else {
						fragment = Mustache.render(templateTxtInput, data.questions[i]);
					}
					list.innerHTML += fragment;
				}
			}
		});
		
		
	});	
	
</script>

</body>

</html>
