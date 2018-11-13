<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html manifest="newIns.manifest">

<head>
  <meta charset="utf-8">
  <title>超级调查</title>
  <META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
  <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"> 
  <META HTTP-EQUIV="Expires" CONTENT="0"> 
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

  <div class="mui-content" id="title">
  </div>

  <div class="mui-content" style="padding:0 10px;">
    <div id="list">

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
  <!--<script type="text/javascript" src="http://pingjs.qq.com/h5/stats.js" name="MTAH5" sid="500303154" cid="500303156"  opts="{&quot;senseHash&quot;:false,&quot;autoReport&quot;:false,&quot;performanceMonitor&quot;:false}" ></script>-->

</body>

<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/js/mustache.min.js"></script>
<script type="text/javascript">
    $(function(){
		//在题目模块 添加了标题图 <img class=\"img-titlePic\" src=\"" + baseUrl+ "{{picPath}}\" alt=\"title-Picture\" style=\"width:100%;\">\n
		var titleTemplate = "\n     <div class=\"mui-content-padded\">\n       <h4 id=\"title\">\n           【{{questionQty}}题】{{aqnName}}\n         </h4>\n       <p class=\"title-desc\">\n         <span>出题人：{{publisherName}}</span> 类型：{{aqnClassName}}\n       </p>\n        <img class=\"img-titlePic\" src=\"${picPath}\" alt=\"title-Picture\" style=\"width:100%;\">\n           <p style=\"line-height: 1.4;text-align:justify;\">\n         {{{aqnSummary}}}\n       </p>\n     </div>\n   ";

		var template = "\n      <h5>\n        {{questionNum}}.{{aqTitle}}\n        <font color=\"red\">(单选)</font>\n      </h5>\n      <div>\n        <form class=\"mui-input-group\" id=\"q{{questionNum}}\" name=\"xdc-single-choice\"  data-id=\"{{aqId}}\">\n          {{#options}}\n            <div class=\"mui-input-row mui-radio mui-left\">\n              <label>{{optionDes}}</label>\n              <input name=\"q{{questionNum}}\" value=\"{{optionOrder}}\" type=\"radio\"></input>\n            </div>\n          {{/options}}\n        </form>\n      </div>\n    ";

		var title = document.getElementById("title");
		var list = document.getElementById("list");
		
		var aqnId = "${aqnId}";

		$.ajax("${ctx}/queView/loadAqn.do", {
			data: {
				aqnId: aqnId
			},
			dataType: 'json',
			type: 'get',
			timeout: 10000,
			success: function(data) {
				var titleFragment = Mustache.render(titleTemplate, data);
				title.innerHTML = titleFragment;
				for(var i = 0, length = data.questions.length; i < length; i++) {
					var fragment = Mustache.render(template, data.questions[i]);
					list.innerHTML += fragment;
				}
			}
		});
    });
    
</script>

</html>