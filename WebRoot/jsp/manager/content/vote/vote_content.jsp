<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<!-- <html> -->
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

  <div>
    <div id="list" style="padding:0 10px;">

    </div>
    <div id="subbtn" class="mui-content-padded" style="margin: 10px 15px;">
      <button class="mui-btn xdc-btn-submit mui-btn-block">仅提供预览</button>
    </div>
  </div>
  <div class="ad">
  <a id="adLink" href="" ><img id="ad-complete" src=""  style="width:100%"></a>
	</div>
  <script type="text/javascript" src="http://pingjs.qq.com/h5/stats.js" name="MTAH5" sid="500158849" cid="500158852"></script>
  <!--测试用腾讯云后台需要添加的代码，此时需要注释掉上面一段代码-->
  <!--<script type="text/javascript" src="http://pingjs.qq.com/h5/stats.js" name="MTAH5" sid="500303154" cid="500303156"  opts="{&quot;senseHash&quot;:false,&quot;autoReport&quot;:false,&quot;performanceMonitor&quot;:false}" ></script>-->

</body>

<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/js/mustache.min.js"></script>
<script>

    $(function(){
 	    //存放选项
 	    var resultTemplate = "\n  {{#voteOptionData}}\n    <p style=\"margin-bottom:0px;\">\n      <span>{{des}}</span>\n    </p>\n    <div class=\"mui-card\">\n      <div class=\"graph\">\n        <div class=\"green trans\">\n        </div>\n        <div class=\"vote-answer\">\n          <p>{{optionPercent}}%</p>\n        </div>\n      </div>\n    </div>\n  {{/voteOptionData}}\n  <div class=\"mui-content\">\n    <div class=\"mui-content-padded\">\n      <p style=\"font-size:12px\">\n        共<span id=\"num\">{{participant}}</span>人参与\n      </p>\n    </div>\n  </div>\n  {{#correctAnswer}}\n    <p>\n      正确答案是{{correctAnswer}}\n    </p>\n  {{/correctAnswer}}\n  ";

 	    var template = "\n    <h5>\n      {{vqTitle}}\n      <font color=\"red\">(单选)</font>\n    </h5>\n    <div>\n      <form class=\"mui-input-group\" id=\"q{{questionNum}}\" name=\"xdc-single-choice\">\n        {{#options}}\n          <div class=\"mui-input-row mui-radio mui-left\">\n            <label>{{optionDes}}</label>\n            <input name=\"vote\" value=\"{{optionOrder}}\" type=\"radio\"></input>\n          </div>\n        {{/options}}\n      </form>\n    </div>\n  ";

 		//在题目模块 添加了标题图 <img class=\"img-titlePic\" src=\"" +baseUrl+ "{{picPath}}\" alt=\"title-Picture\" style=\"width:100%;\">\n
 	    var titleTemplate = "\n       <div class=\"mui-content-padded\">\n         <h4 id=\"title\">\n             【{{questionQty}}题】{{vqnName}}\n           </h4>\n         <p class=\"title-desc\">\n           <span>出题人：{{publisherName}}</span> 类型：{{vqnClassName}}\n         </p>\n         <img class=\"img-titlePic\"  src=\"${picPath}\"  alt=\"title-Picture\"  style=\"width:100%;\">\n    <p>\n           {{{vqnSummary}}}\n         </p>\n       </div>\n     ";


 	    var title = document.getElementById("title");
 	    var list = document.getElementById("list");
 	    
 	    var vqnId = "${vqnId}";
 		
 		//下面让我们来判断一下该投票问卷是否答过，如果答过就展示投票统计结果；否则就加载问卷页面；
 		var answered = localStorage.getItem('answered');
 		
        $.ajax("${ctx}/queView/loadVqn.do", {
            data: {
                vqnId: vqnId
            },
            dataType: 'json',
            type: 'get',
            timeout: 10000,
            success: function (data) {
                var titleFragment = Mustache.render(titleTemplate, data);
                title.innerHTML = titleFragment;
                var fragment = Mustache.render(template, data);
                list.innerHTML = fragment;
                data.options.forEach(function (op) {
                    results.push(op.optionDes);
                });
				
				//在fetch（）的ajax成功以后再加载投票统计，否则fetch和getVoteResult是异步的ajax请求，如果fetch较慢，则会把统计页面再次覆盖。
				if(answered == '1'){//答过
					mui.ajax('/NewIns/wanx/getVoteResult', {
						data: {
							vqnId: window.location.search.split('&')[0].split('=')[1],
						},
						dataType: 'json',
						type: 'get',
						timeout: 10000,
						success: function (data) {
							if(data.success == 'true') {
								drawResult(data);
							} else {
								mui.toast('网络似乎很繁忙啊,好像出错了额...');
							}
						},
						error:function(){
							mui.toast('网络似乎很繁忙啊,好像出错了额...');
						}
					});	
				}
			}
        });
    	
    });

</script>

</html>