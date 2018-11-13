"use strict"; +

function() {
	mui.init({
		swipeBack: true, //启用右滑关闭功能
		beforeback: function() {
			MtaH5.clickStat('3302');
		}
	});
	
	function loadAqn(){
		MtaH5.clickStat('3300')
		//在题目模块 添加了标题图 <img class=\"img-titlePic\" src=\"" + baseUrl+ "{{picPath}}\" alt=\"title-Picture\" style=\"width:100%;\">\n
		var titleTemplate = "\n     <div class=\"mui-content-padded\">\n       <h4 id=\"title\">\n           【{{questionQty}}题】{{aqnName}}\n         </h4>\n       <p class=\"title-desc\">\n         <span>出题人：{{publisherName}}</span> 类型：{{aqnClassName}}\n       </p>\n        <img class=\"img-titlePic\" src=\"" + baseUrl+ "{{picPath}}\" alt=\"title-Picture\" style=\"width:100%;\">\n           <p style=\"line-height: 1.4;text-align:justify;\">\n         {{{aqnSummary}}}\n       </p>\n     </div>\n   ";

		var template = "\n      <h5>\n        {{questionNum}}.{{aqTitle}}\n        <font color=\"red\">(单选)</font>\n      </h5>\n      <div>\n        <form class=\"mui-input-group\" id=\"q{{questionNum}}\" name=\"xdc-single-choice\"  data-id=\"{{aqId}}\">\n          {{#options}}\n            <div class=\"mui-input-row mui-radio mui-left\">\n              <label>{{optionDes}}</label>\n              <input name=\"q{{questionNum}}\" value=\"{{optionOrder}}\" type=\"radio\"></input>\n            </div>\n          {{/options}}\n        </form>\n      </div>\n    ";

		var title = document.getElementById("title");
		var list = document.getElementById("list");
		
		var aqnId = "${aqnId}";

		mui.ajax('${ctx}/platform/loadAqn.do', {
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
	}


	
}();