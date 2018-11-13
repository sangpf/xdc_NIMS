<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>测评问卷投放-添加信息</title>
  <link rel="stylesheet" href="${ctx}/BS/plugins/datepicker/datepicker3.css">
  <link href="${ctx}/static/assessResult/css/mui.min.css" rel="stylesheet"/>
</head>
<body class="hold-transition skin-blue ">
 <!-- general form elements -->
       <div class="box box-primary">
 	       <table style="padding: 200px;margin: 10px;">
	           	<tr>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="uestionnairePage();" class="btn btn-block btn-primary" >问卷</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="messagePage();" class="btn btn-block btn-primary">信息</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="setupPage();" class="btn btn-block btn-primary">设置</button></td>
	   	            <td style="width: 100px;padding: 3px;">
	   	            	<button type="button" onclick="operatePage();" class="btn btn-block btn-primary">运营调整</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="choseTemplatePage();" class="btn btn-block btn-primary">选择模版</button></td>
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="paySetPage();" class="btn btn-block btn-primary">付费测评</button></td>	   
	           		<td style="width: 100px;padding: 3px;">
	           			<button type="button" onclick="resultPage();" class="btn btn-block btn-primary"  style="background-color: #BC8F8F">结果页</button></td>	        			
	           	</tr>
           </table>
         <div class="box-header with-border" style="font-size: 20px">
         	  当前投放id为 : ${deliveryid}    <%-- ${resutShowType }  ${assessDelivery.shareAbstract } --%>  |
         	  
         	 结果页显示样式 : ${resutShowType }
         	   
         	   <c:if test="${deliveryid == '' || null || 'undefined'}">空</c:if>
         	   
         </div>
         <%-- <input id="resutShowType" value="${assessDelivery.resutShowType }"> --%>
         <div style="margin-left:20%;">
			<span style="font-size: 13px;">结果页显示方式</span>
			<select id="change-active" style="width: 100px;">
				<option value="1">样式一</option>
				<option value="2">样式二</option>
			</select>
		</div>
		<button id="ok" style="margin-left: 75%;margin-top: -2%;position: absolute;">确认</button>
         <!-- 结果页样式二 -->
         <div id="new-resut" style="display:none;margin-top:5%">
         	<div id="new-resut">
         		<div class="mui-col-xs-12">
			        <span class="share_explain-title">你是一个不太会感到孤独的人</span>
			        <div class="share_explain-icon">
			         	<img src="img/index/Vline.png" alt="竖线" />
			        </div>
			        <div class="share_explain-body">
			        </div>
		       </div>
        	</div>
         </div>
         <!-- form start -->
         <div id="result-head" style="display: none">
         	<!--报告说明开始-->
    		<div id="assess-result-reportdescription" class="assess-result-reportdescription ">
    			<!--报告说明头部开始-->
    			<table width="50%">
    				<tbody><tr>
    					<td width="25%"><hr></td>
    					<td class="assess-result-reportdescription-text" width="50%"><p id="bgsm-title">报告说明</p></td>
    					<td width="25%"><hr></td>
    				</tr>
    			</tbody></table>
    			<!--报告说明头部结束-->
    			<!--报告说明内容开始-->
    			<div id='assessresult-baogao-content' class="assessresult-baogao-content">
    				
    			</div>
    			<!--报告说明内容结束-->
    		</div>
    		<!--报告说明结束-->
         </div>

         <div id="result-tail" style="display: none">
         	<!--操作区域开始-->
    		<div id="operation-box" class="operation-box" up-tapped="false" down-tapped="false">
    			<!--操作按钮区域开始-->
    			<div class="mui-row">
    				<div id="‘zhun-tap’" class=" mui-col-sm-3 mui-col-xs-3 operation-row" onclick="assessThumbsUp('thumbup','Up')">
    					<p class="operation-true-text">准</p>
    					<p id="operation-true-datanumber" class="operation-true-number">0</p>
    				</div>
    				<div id="‘buzhun-tap’" class=" mui-col-sm-3 mui-col-xs-3 operation-row" onclick="assessThumbsUp('thumbdown','Down')">
    					<p class="operation-false-text">不准</p>
    					<p id="operation-false-datanumber" class="operation-false-number">0</p>
    				</div>
    				<div id="donate-show" class=" mui-col-sm-3 mui-col-xs-3 operation-row">
    					<p class="operation-reward-text">赏</p>
    					<p id="operation-data-num" class="operation-reward-number">0</p>
    				</div>
    			</div>
    			<!--操作按钮区域结束-->
    			<!--操作区域文本容器开始-->
    			<div id="operation-contentbox" class="operation-contentbox">
    				
    			</div>
    			<!--操作区域文本容器结束-->
    		</div>
    		<!--操作区域结束-->
    		<!--展开，收起模块开始-->
    		<div class="behavior-box">
    			<div id="behavior-n-box" state="true" onclick="changeStyle()">
    				<img id="behavior-img" src="images/tap-dowm.png">
    				<p id="behavior-text">查看详细报告</p>
    			</div>
    			
    		</div>
    		<!--展开，收起模块结束-->
         </div>
         <!-- 富文本录入区域-->
         <!-- 样式一 -->
         <div id="style_one" style="margin-top:5%">
	         <div class="head-text" style="margin-left:20%;margin-top:5%">
					<p><input id="head-title" class="head-title" type="text" size="10" value="报告说明"></p>
					<textarea id="qe_Profile" name="qe_Profile" type="text/plain" style="width:700px;height:250px;">
							<ul style="list-style-type: disc;" class=" list-paddingleft-2">
							    <li>
							        <p>
							            <span style="font-size: 14px; color: rgb(0, 0, 0);">本报告旨在通过专业测试提供您的生活费管理水平能力</span>
							        </p>
							    </li>
							    <li>
							        <p>
							            <span style="font-size: 14px; color: rgb(0, 0, 0);">测评结论对生活费管理水平分为，管理能力强、管理能力一般，管理能力弱三个层级</span><br/>
							        </p>
							    </li>
							</ul>
					</textarea>
			</div>
	        <div id="tail-text" style="margin-left:20%;margin-top: 5%">
				<p><input id="tail-title" class="tail-title"  type="text" size="10" value="引导" readonly="readonly"></p>
				<textarea id="qe_Profile1" name="qe_Profile1" type="text/plain" style="width:700px;height:250px;">
					<p style="text-indent: 2em;">
						<span style="font-size: 14px; color:#008000;">欢迎大家关注“心发现”，我们还有更多的专业测评问卷和心理报告。请在“拉钩”首页上部导航内寻找“心发现”，点击进入！</span>
					</p>
				</textarea>
	        </div>
        </div>
        <!-- 样式二 -->
        <div id="style_two" style="display:none;margin-top:5%">
			<div id="tail-text" style="margin-left:20%;margin-top: 5%">
				<p><input id="tail-title" class="tail-title"  type="text" size="10" value="引导" readonly="readonly"></p>
				<textarea id="qe_Profile3" name="qe_Profile1" type="text/plain" style="width:700px;height:250px;">
					<p style="text-indent: 2em;">
						<span style="font-size: 14px; color:#008000;">欢迎大家关注“心发现”，我们还有更多的专业测评问卷和心理报告。请在“拉钩”首页上部导航内寻找“心发现”，点击进入！</span>
					</p>
				</textarea>
	        </div>
			
        </div>
        
        
        <div id='data-headResultDetail' style='display:none;'>${assessDelivery.headResultDetail}</div>
        <div id='data-tailResultDetail' style='display:none;'>${assessDelivery.tailResultDetail}</div>
        
       </div>
<!-- 引入富文本编辑器   begin -->
<script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/lang/zh-cn/zh-cn.js"></script>
<!-- 引入富文本编辑器   end -->
<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/js/validate.js"></script> 


<script type="text/javascript">
var deliveryid = "";
$(function(){
	 deliveryid = "${deliveryid}";
	 //实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue1 = UE.getEditor('qe_Profile');
	$(".sqTitle").each(function(i){
	   UE.getEditor(this.id);
	});
	var ue2 = UE.getEditor('qe_Profile1');
	$(".sqTitle").each(function(i){
	   UE.getEditor(this.id);
	});
	var ue4 = UE.getEditor('qe_Profile3');
	$(".sqTitle").each(function(i){
	   UE.getEditor(this.id);
	});
	//将富文本操作对象保存起来
	var ues={
		ue1:ue1,
		ue2:ue2,
		ue4:ue4
	}
	
	//获取头数据
	var headResultDetail=document.getElementById("data-headResultDetail").innerHTML;
	var tailResultDetail=document.getElementById("data-tailResultDetail").innerHTML;
	ue1.addListener('ready',function(){
		iniEditor(headResultDetail,tailResultDetail,ues);
		ClickOk(ues);
	});
	
	//调用选择样式函数
	changeShowContent();
});

	// 问卷
	function uestionnairePage(){
		window.location.href = "${ctx}/platform/niAssessDeliveryWanxNewJump.do?deliveryid="+deliveryid;
	}
	//跳转到信息添加页面
	function messagePage(){
		window.location.href = "${ctx}/platform/niAssessDeliveryWanxMESJump.do?deliveryid="+deliveryid;
	}
	//跳转到设置
	function setupPage(){
		window.location.href = "${ctx}/platform/niAssessDeliveryWanxSetJump.do?deliveryid="+deliveryid;
	}
	// 选择模版
	function choseTemplatePage(){
		window.location.href = "${ctx}/platform/choseAssessTemplatePage.do?deliveryid="+deliveryid;
	}
	//运营
	function operatePage(){
		window.location.href = "${ctx}/platform/niAssessDeliveryWanxOperateJump.do?deliveryid="+deliveryid;
	}
	// 付费测评
	function paySetPage(){
		window.location.href = "${ctx}/platform/assessDelivery_PaySetPage.do?deliveryid="+deliveryid;
	}
	function resultPage(){
		window.location.href = "${ctx}/platform/assessDelivery_ResultPage.do?deliveryid="+deliveryid;
	}
	/**
	*点击ok按钮保存输入的内容
	*/
	function ClickOk(ues){
		document.getElementById("ok").addEventListener("click",function(){
			console.log("点击确定了");
			var style=sessionStorage.getItem("style");
			if(style==''||style==null||style=="null"){
				style=1;
			}
			//判断当前选择的样式几
			if(style==1||style=="1"){
				//进入这里表示要保存样式一的内容
				submitTypeOne(ues);
			}else if(style==2||style=="2"){
				//进入这里表示要保存样式二的内容
				submitTypeTwo(ues);
			}
			
		});
	}
	/**
	*
	*初始化页面数据
	*/
	function iniEditor(headResultDetail,tailResultDetail,ues){
		//声明结果页展示类型
		var resultShowType="${resutShowType }";
		//判断两个参数是否为空
		if(headResultDetail!=''&&headResultDetail!=null&&headResultDetail!="null"&&tailResultDetail!=''&&tailResultDetail!=null&&tailResultDetail!="null"){
			//判断头数据是否为空
			if(headResultDetail!=''&&headResultDetail!=null&&headResultDetail!="null"){
				console.log("头内容是:"+headResultDetail);
				//将头数据设置到隐藏模版中
				var headTemplate=document.getElementById('result-head');
				headTemplate.innerHTML=headResultDetail;
				//获取隐藏模版中的内容
				var headContent=document.getElementById('assessresult-baogao-content').innerHTML;
				//再将头数据设置到富文本编辑器中
				ues.ue1.setContent(headContent);
				//将头数据的标题设置到富文本的input标签中
				var headTitle=document.getElementById('bgsm-title').innerHTML;
				document.getElementById('tail-title').value=headTitle;
			}
			if(headResultDetail==''||headResultDetail==null||headResultDetail=="null"){
				console.log("尾内容是:"+tailResultDetail);
				//将尾数据设置到隐藏模版中
				var tailTemplate=document.getElementById('result-tail');
				tailTemplate.innerHTML=tailResultDetail;
				//获取隐藏模版中的内容
				var tailContent=document.getElementById('operation-contentbox').innerHTML;
				//再将头数据设置到富文本编辑器中
				ues.ue2.setContent(tailContent);
				//设置下拉框选中样式二
				$("#change-active").find("option").last().attr("selected","selected");
				sessionStorage.setItem("style", 2);
				$("#style_two").css("display","block");
				$("#style_one").css("display","none");
				if(tailResultDetail!=''){
					console.log("尾内容是:"+tailResultDetail);
					//将尾数据设置到隐藏模版中
					var tailTemplate=document.getElementById('result-tail');
					tailTemplate.innerHTML=tailResultDetail;
					//获取隐藏模版中的内容
					var tailContent=document.getElementById('operation-contentbox').innerHTML;
					//再将头数据设置到富文本编辑器中
					ues.ue4.setContent(tailContent);
				}
			}
				
			}
		
		
	}
	/**
	*选择下拉框展示不同内容
	*/
	function changeShowContent(){
			$("#change-active").bind("change",function(){
				var valueStr=$("#change-active option:selected").val();
				if(valueStr==1){
					//将页面数值保存在session中
					sessionStorage.setItem("style", 1);
					$("#style_one").css("display","block");
					$("#style_two").css("display","none");
				}else if(valueStr==2){
					sessionStorage.setItem("style", 2);
					$("#style_two").css("display","block");
					$("#style_one").css("display","none");
				}
			})
		}
	
	/**
	*
	*提交样式一的内容
	*/
	function submitTypeOne(ues){
			//获取头部富文本的内容
			var headText=ues.ue1.getContent();
			//获取尾部富文本的内容
			var tailText=ues.ue2.getContent();
			//获取头部外框架
			var headBox=document.getElementById("result-head");
			//获取尾部外框架
			var tailBox=document.getElementById("result-tail"); 
			//获取头部内容框架
			var headContentBox=document.getElementById("assessresult-baogao-content");
			//获取尾部内容框架
			var tailContentBox=document.getElementById("operation-contentbox");
			//将头部富文本的内容放到头部内容框架中
			headContentBox.innerHTML=headText;
			//将尾部富文本的内容放到尾部内容框架中
			tailContentBox.innerHTML=tailText;
			
			var headBox_content = headBox.innerHTML;
			var tailBox_content = tailBox.innerHTML;
			
			//console.log("保存的头部是:"+headBox_content);
			//console.log("保存的尾部是:"+tailBox_content);

			Ajax_saveAssessResult(headBox_content,tailBox_content);
	}
	/**
	*
	*提交样式二的内容
	*/
	function submitTypeTwo(ues){
			//获取尾部富文本的内容
			var tailText=ues.ue4.getContent();
			//获取尾部外框架
			var tailBox=document.getElementById("result-tail"); 
			//获取尾部内容框架
			var tailContentBox=document.getElementById("operation-contentbox");
			//将尾部富文本的内容放到尾部内容框架中
			tailContentBox.innerHTML=tailText;
			
			var tailBox_content = tailBox.innerHTML;
			
			console.log("保存的尾部是:"+tailBox_content);
			Ajax_saveAssessResult("null",tailBox_content);
			
	}
	/**
	*
	*提交ajax
	*/
	function Ajax_saveAssessResult(headContent,tailContent){
		var resutShowType=sessionStorage.getItem("style");
		$.ajax({
			url : "${ctx}/platform/saveAssessResult_head_tail.do",
			data : {
				deliveryId : deliveryid,
				headBox_content : headContent,
				tailBox_content : tailContent,
				//resutShowType : resutShowType
			},
			dataType : "json",
			type : "post",
			success : function(data){
				alert(data.msg);
					
			}
				
		})
	}
</script>
</body>
</html>
