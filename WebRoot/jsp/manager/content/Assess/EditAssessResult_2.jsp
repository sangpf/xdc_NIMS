<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>通用测评结果模板_样式2</title>
</head>
<body>
	<link href="/NIMS/static/assessResult/css/mui.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/NIMS/static/assessResult/css/index.css" />
    <link rel="stylesheet" href="/NIMS/static/assessResult/css/wangEditor.css" />
		
		<div class="qe_Profile">
			<p><input class="title-text" type="text" size="6" value="分值区域" readonly="readonly"></p>
			<p style="color:red" >Tip:复制摘要正文到富文本输入框即可</p>
			<textarea id="qe_Profile9" name="qe_Profile9" type="text/plain" style="width:700px;height:250px;">
				<p style="text-indent: 2em;">
				    摘要正文<br/>
				</p>
			</textarea>
		</div>
		
		<div class="qe_Profile">
			<p><input id="title-text-two" class="title-text" type="text" size="6" value="摘要"><input id='check-box2' type="checkbox" name="check-box" value="2" ></p>
			<textarea id="qe_Profile2" name="qe_Profile2" type="text/plain" style="width:700px;height:250px;">
				<p style="text-indent: 2em;">
				    <strong>摘要</strong>
				</p>
				<p style="text-indent: 2em;">
				    <strong><br/></strong>
				</p>
				<p style="text-indent: 2em;">
				    摘要正文<br/>
				</p>
			</textarea>
		</div>
		<div class="qe_Profile">
			<p><input id="title-text-three"  value="你的表现" class="title-text" type="text" size="6"><input id='check-box3' type="checkbox" name="check-box" value="3" ></p>
			<textarea id="qe_Profile3" name="qe_Profile3" type="text/plain" style="width:700px;height:250px;">
				<p style="text-indent: 2em;">
				    你可能会出现下列表现中的某种情况:
				</p>
				<p style="text-indent: 2em;">
				    1、第一条
				</p>
				<p style="text-indent: 2em;">
				    2、第二条
				</p>
				<p style="text-indent: 2em;">
				    3、第三条
				</p>
				<p style="text-indent: 2em;">
				    <br/>
				</p>
				<p style="text-indent: 0em;">
				    <span style="color:#008000;">#积极因素#</span>正文
				</p>
				<p style="text-indent: 0em;">
				    <span style="color:#008000;">#消极因素#</span>正文
				</p>
				<p style="text-indent: 0em;">
				    <br/>
				</p>
			</textarea>
		</div>
		<div class="qe_Profile">
			<p><input id="title-text-four"  value="原因分析" class="title-text" type="text" size="6"><input id='check-box4' type="checkbox" name="check-box" value="4" ></p>
			<textarea id="qe_Profile4" name="qe_Profile4" type="text/plain" style="width:700px;height:250px;">
				<p style="text-indent: 2em;">
				    原因综述
				</p>
				<p style="text-indent: 2em;">
				    1、第一条				
				</p>
				<p style="text-indent: 2em;">
				    2、第二条				
				</p>
				<p style="text-indent: 2em;">
				    3、第三条				
				</p>
				<p style="text-indent: 2em;">
				    <br/>
				</p>
			</textarea>
		</div>
		<div class="qe_Profile">
			<p><input id="title-text-five" value="小故事" class="title-text" type="text" size="6"><input id='check-box5' type="checkbox" name="check-box" value="5" ></p>
			<textarea id="qe_Profile5" name="qe_Profile5" type="text/plain" style="width:700px;height:250px;"></textarea>
		</div>
		<div class="qe_Profile">
			<p><input id="title-text-six" value="小贴士" class="title-text" type="text" size="6"><input id='check-box6' type="checkbox" name="check-box" value="6" ></p>
			<textarea id="qe_Profile6" name="qe_Profile6" type="text/plain" style="width:700px;height:250px;">
				<p style="text-indent: 2em;">
				    建议综述
				</p>
				<p style="text-indent: 2em;">
				    1、第一条
				</p>
				<p style="text-indent: 2em;">
				    2、第二条
				</p>
				<p style="text-indent: 2em;">
				    3、第三条
				</p>
				<p style="text-indent: 2em;">
				    <br/>
				</p>
			</textarea>
		</div>
		<div class="qe_Profile">
			<p><input id="title-text-seven"  value="推荐阅读" class="title-text" type="text" size="6"><input id='check-box7' type="checkbox" name="check-box" value="7" ></p>
			<textarea id="qe_Profile7" name="qe_Profile7" type="text/plain" style="width:700px;height:250px;"></textarea>
		</div>
		<div class="qe_Profile">
			<p><input id="title-text-eight" value="活动提示" class="title-text" type="text" size="6"><input id='check-box8' type="checkbox" name="check-box" value="8" ></p>
			<textarea id="qe_Profile8" name="qe_Profile8" type="text/plain" style="width:700px;height:250px;"></textarea>
		</div>
		<div id="insert-ok" type="button" class="mui-btn mui-btn-green insert-ok" style="margin-left:42%">确认!</div>
		<div id="back_list" type="button" class="mui-btn mui-btn-green insert-ok">回列表!</div>
		
		<div id="resultArea" class="resultArea" >
	    <!--测评结果报告内容-->
	    <div id="result" class="assess-result-report" style='display:none'>
    		<!--摘要开始-->
    		<div id="assessresult-abstract" class="assessresult-abstract">
    			<!--摘要头部开始-->
    			<table width="50%">
    				<tbody><tr>
    					<td width="25%"><hr></td>
    					<td class="assess-result-reportdescription-text" width="50%"><p id="zy-title" >摘要</p></td>
    					<td width="25%"><hr></td>
    				</tr>
    			</tbody></table>
    			<!--摘要头部结束-->
    			<!--摘要内容模块开始-->
    			<div id="assessresult_abstract-content_box" class="assessresult-abstract-content-box">
    				
    			</div>
    			<!--摘要内容模块结束-->
    		</div>
    		<!--摘要结束-->
    		<!--你的表现开始-->
    		<div id="assessresult-yourperformance" class="assessresult-yourperformance">
    			<!--你的表现头部开始-->
    			<table width="50%">
    				<tbody><tr>
    					<td width="25%"><hr></td>
    					<td class="assess-result-reportdescription-text" width="50%"><p id="ndbx-title" >你的表现</p></td>
    					<td width="25%"><hr></td>
    				</tr>
    			</tbody></table>
    			<!--你的表现头部结束-->
    			<!--你的表现内容开始-->
    			<div id="assessresult-yourperformance-content-box" class="assessresult-yourperformance-content-box">
    				
    			</div>
    			<!--你的表现内容结束-->
    		</div>
    		<!--你的表现结束-->
    		<!--原因分析开始-->
    		<div id="reasonanalysis-box" class="reasonanalysis-box">
    			<!--原因分析头部开始-->
    			<table width="50%">
    				<tbody><tr>
    					<td width="25%"><hr></td>
    					<td class="assess-result-reportdescription-text" width="50%"><p id="yy-title" >原因分析</p></td>
    					<td width="25%"><hr></td>
    				</tr>
    			</tbody></table>
    			<!--原因分析头部结束-->
    			<!--原因分析内容开始-->
    			<div id="reasonanalysis-contentbox" class="reasonanalysis-contentbox">
    				
    			</div>
    			<!--原因分析内容结束-->
    		</div>
    		<!--原因分析结束-->
    		<!--小故事开始-->
    		<div id="littlestory-box" class="littlestory-box">
    			<!--小故事头部开始-->
    			<table width="50%">
    				<tbody><tr>
    					<td width="25%"><hr></td>
    					<td class="assess-result-reportdescription-text" width="50%"><p id="xgs-title" >小故事</p></td>
    					<td width="25%"><hr></td>
    				</tr>
    			</tbody></table>
    			<!--小故事头部结束-->
    			<!--小故事内容开始-->
    			<div id="littlestory-contentbox" class="littlestory-contentbox">
    				
    			</div>
    		</div>
    		<!--小故事结束-->
    		<!--小贴士开始-->
    		<div id="tips-box" class="tips-box">
    			<!--小贴士头部开始-->
    			<table width="50%">
    				<tbody><tr>
    					<td width="25%"><hr></td>
    					<td class="assess-result-reportdescription-text" width="50%"><p id="xts-title" >小贴士</p></td>
    					<td width="25%"><hr></td>
    				</tr>
    			</tbody></table>
    			<!--小贴士头部结束-->
    			<!--小贴士内容开始-->
    			<div id="tips-contentbox" class="tips-contentbox">
    				
    			</div>
    			<!--小贴士内容结束-->
    		</div>
    		<!--小贴士结束-->
    		<!--推荐阅读开始-->
    		<div id="recommended-box" class="recommended-box">
    			<!--推荐阅读头部开始-->
    			<table width="50%">
    				<tbody><tr>
    					<td width="25%"><hr></td>
    					<td class="assess-result-reportdescription-text" width="50%"><p id="tjyd-title" >推荐阅读</p></td>
    					<td width="25%"><hr></td>
    				</tr>
    			</tbody></table>
    			<!--推荐阅读头部结束-->
    			<!--推荐内容容器开始-->
    			<div id="recommended-contentbox" class="recommended-contentbox">
    				
    			</div>
    			<!--推荐内容容器结束-->
    		</div>
    		<!--推荐阅读结束-->
    		<!--活动提示开始-->
    		<div id="activity-box" class="activity-box">
    			<!--活动提示头部开始-->
    			<table width="50%">
    				<tbody><tr>
    					<td width="25%"><hr></td>
    					<td class="assess-result-reportdescription-text" width="50%"><p id="hdts-title" >活动提示</p></td>
    					<td width="25%"><hr></td>
    				</tr>
    			</tbody></table>
    			<!--活动提示头部结束-->
    			<!--活动提示内容容器开始-->
    			<div id="activity-contentbox" class="activity-contentbox">
    				
    			</div>
    			<!--活动提示内容容器结束-->
    		</div>
    		<!--活动提示结束-->
    		<input type="hidden" id="abstract_content" value="">
	    </div>

	</div>
	<input type="hidden" id="aqnId" value="${aqnId}">
    <input type="hidden" id="dimension" value="${dimension }">
    <div id='data-resultDetail' style='display:none;'>${resultDetail}</div>
    <input type="hidden" id="dimensionStr" value="${dimensionStr }">
    <input type="hidden" id="section" value="${section}">
    <input type="hidden" id="aqnCategory" value="${aqnCategory}">
	</div>
  <script  src="${ctx}/static/assessResult/js/wangEditor.js"></script>
  <script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
  <script src="${ctx}/static/js/moment.min.js"></script>
  <script src="${ctx}/static/assessResult/js/mui.min.js"></script>
  <script src="${ctx}/static/assessResult/js/currency.js"></script>
  <script src="${ctx}/js/validate.js"></script>	
 <script type="text/javascript">
	 $(function (){
	    //实例化编辑器
	    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	    //2
		var ue2 = UE.getEditor('qe_Profile2');
		    
		    $(".sqTitle").each(function(i){
		    	UE2.getEditor(this.id);
		    });
		//3
		var ue3 = UE.getEditor('qe_Profile3');
		    
		    $(".sqTitle").each(function(i){
		    	UE3.getEditor(this.id);
		    });
		//4
		var ue4 = UE.getEditor('qe_Profile4');
		    
		    $(".sqTitle").each(function(i){
		    	UE4.getEditor(this.id);
		    });
		 //5
		var ue5 = UE.getEditor('qe_Profile5');
		    
		    $(".sqTitle").each(function(i){
		    	UE5.getEditor(this.id);
		    });
		 //6
		var ue6 = UE.getEditor('qe_Profile6');
		    
		    $(".sqTitle").each(function(i){
		    	UE6.getEditor(this.id);
		    });
		  //7
		var ue7 = UE.getEditor('qe_Profile7');
		    
		    $(".sqTitle").each(function(i){
		    	UE7.getEditor(this.id);
		    });
		  //8
		var ue8 = UE.getEditor('qe_Profile8');
		    
		    $(".sqTitle").each(function(i){
		    	UE8.getEditor(this.id);
		    });
		var ue9 = UE.getEditor('qe_Profile9');
		    
		    $(".sqTitle").each(function(i){
		    	UE9.getEditor(this.id);
		    });
		    //保存富文本节点
		    var ues={
		    	ue2:ue2,
		    	ue3:ue3,
		    	ue4:ue4,
		    	ue5:ue5,
		    	ue6:ue6,
		    	ue7:ue7,
		    	ue8:ue8,
		    	ue9:ue9
		    }
		 //调用初始化富文本内容函数
		 var content=document.getElementById('data-resultDetail').innerHTML;
		 ue2.addListener("ready",function(){
		  setContent(content,ues);
		   //调用点击确认，复制全部模板内容函数
		 	copyTemplate(ues);
		   back_List();
		 });
		
		
	 });
	 
	// 点击确认后  返回列表
	 function backToList(){
 		var aqnId = $("#aqnId").val();
 		window.location.href = "${ctx}/platform/editAssessResult_list.do?aqnId="+aqnId;
	 }
	
	function back_List(){
		document.getElementById('back_list').addEventListener('tap', function(){
	 		var aqnId = $("#aqnId").val();
	 		window.location.href = "${ctx}/platform/editAssessResult_list.do?aqnId="+aqnId;
		});
	}
	 
/**
 * 点击确认，复制全部模板内容
 */
function copyTemplate(ues){
	//监听点击确认按钮
	document.getElementById('insert-ok').addEventListener('tap',function(){
		//获取隐藏域中的aqnCategory数值
		var aqnCategory=document.getElementById("aqnCategory").value;
		//获取对应模块中每个富文本中的内容
		var zy= ues.ue2.getContent();
		var ndbx=ues.ue3.getContent();
		var yyfx=ues.ue4.getContent();
		var xgs=ues.ue5.getContent();
		var xts=ues.ue6.getContent();
		var tjyd=ues.ue7.getContent();
		var hdts=ues.ue8.getContent();
		var abstractStr=ues.ue9.getContent();
		//再将富文本中的内容放置到对应的隐藏模版模块中
		//分值区域
		$("#abstract_content").val(abstractStr);
		//摘要
		document.getElementById('assessresult_abstract-content_box').innerHTML=zy;
		//你的表现
		document.getElementById('assessresult-yourperformance-content-box').innerHTML=ndbx;
		//原因分析
		document.getElementById('reasonanalysis-contentbox').innerHTML=yyfx;
		//小故事
		document.getElementById('littlestory-contentbox').innerHTML=xgs;
		//小贴士
		document.getElementById('tips-contentbox').innerHTML=xts;
		//推荐阅读
		document.getElementById('recommended-contentbox').innerHTML=tjyd;
		//活动提示
		document.getElementById('activity-contentbox').innerHTML=hdts;
		//将未选中的富文本对应的模版中的模块隐藏
		//摘要
		var chrckbox2=document.getElementById('check-box2').checked;
		if(chrckbox2==false){
			//进入这里表示摘要没被勾选，隐藏
			document.getElementById('assessresult-abstract').style.display='none';
		}else{
			//进入这里表示摘要被勾选，显示
			document.getElementById('assessresult-abstract').style.display='block';
		}
		//你的表现
		var chrckbox3=document.getElementById('check-box3').checked;
		if(chrckbox3==false){
			//进入这里表示你的表现没被勾选，隐藏
			document.getElementById('assessresult-yourperformance').style.display='none';
		}else{
			//进入这里表示你的表现被勾选，显示
			document.getElementById('assessresult-yourperformance').style.display='block';
		}
		//原因分析
		var chrckbox4=document.getElementById('check-box4').checked;
		if(chrckbox4==false){
			//进入这里表示原因分析没被勾选，隐藏
			document.getElementById('reasonanalysis-box').style.display='none';
		}else{
			//进入这里表示原因分析被勾选，显示
			document.getElementById('reasonanalysis-box').style.display='block';
		}
		//小故事
		var chrckbox5=document.getElementById('check-box5').checked;
		if(chrckbox5==false){
			//进入这里表示小故事没被勾选，隐藏
			document.getElementById('littlestory-box').style.display='none';
		}else{
			//进入这里表示小故事被勾选，显示
			document.getElementById('littlestory-box').style.display='block';
		}
		//小贴士
		var chrckbox6=document.getElementById('check-box6').checked;
		if(chrckbox6==false){
			//进入这里表示小贴士没被勾选，隐藏
			document.getElementById('tips-box').style.display='none';
		}else{
			//进入这里表示小贴士被勾选，显示
			document.getElementById('tips-box').style.display='block';
		}
		//推荐阅读
		var chrckbox7=document.getElementById('check-box7').checked;
		if(chrckbox7==false){
			//进入这里表示推荐阅读没被勾选，隐藏
			document.getElementById('recommended-box').style.display='none';
		}else{
			//进入这里表示推荐阅读被勾选了，显示
			document.getElementById('recommended-box').style.display='block';
		}
		//活动提示
		var chrckbox8=document.getElementById('check-box8').checked;
		if(chrckbox8==false){
			//进入这里表示报告说明没被勾选，隐藏
			document.getElementById('activity-box').style.display='none';
		}else{
			//进入这里表示活动提示被勾选了，显示
			document.getElementById('activity-box').style.display='block';
		}
		//大五模型特殊处理
		if(aqnCategory==2||aqnCategory=='2'){
			//进入这里表示当前测评问卷是'大五'模型的，将你的表现以下(包括你的表现)的所有模块顶部距离设置为0px
			//你的表现
			document.getElementById('assessresult-yourperformance').style.marginTop='0px';
			//原因分析
			document.getElementById('reasonanalysis-box').style.marginTop='0px';
			//小故事
			document.getElementById('littlestory-box').style.marginTop='0px';
			//小贴士
			document.getElementById('tips-box').style.marginTop='0px';
			//推荐阅读
			document.getElementById('recommended-box').style.marginTop='0px';
			//活动提示
			document.getElementById('activity-box').style.marginTop='0px';
			
		}
		//获取八个模块中富文本的标题栏中的内容
		//第二个模块
		var zytitle=document.getElementById('title-text-two').value;
		//第三个模块
		var ndbxtitle=document.getElementById('title-text-three').value;
		//第四个模块
		var yyfxtitle=document.getElementById('title-text-four').value;
		//第五个模块
		var xgstitle=document.getElementById('title-text-five').value;
		//第六个模块
		var xtstitle=document.getElementById('title-text-six').value;
		//第七个模块
		var tjydtitle=document.getElementById('title-text-seven').value;
		//第八个模块
		var hdtstitle=document.getElementById('title-text-eight').value;
		//将八个标题栏的内容保存到隐藏模版中
		//摘要
		document.getElementById('zy-title').innerHTML=zytitle;
		//你的表现
		document.getElementById('ndbx-title').innerHTML=ndbxtitle;
		//原因分析
		document.getElementById('yy-title').innerHTML=yyfxtitle;
		//小故事
		document.getElementById('xgs-title').innerHTML=xgstitle;
		//小贴士
		document.getElementById('xts-title').innerHTML=xtstitle;
		//推荐阅读
		document.getElementById('tjyd-title').innerHTML=tjydtitle;
		//活动提示
		document.getElementById('hdts-title').innerHTML=hdtstitle;
		//获取隐藏模版中的内容
		var content=document.getElementById('result').innerHTML;
		 var aqnId = $("#aqnId").val();
		 var dimension = $("#dimension").val();
		 var dimensionStr = $("#dimensionStr").val();
		 var section = $("#section").val();
		 var aqnCategory = $("#aqnCategory").val();
		 console.log('保存内容是:'+content);
		//Ajax请求保存模版
		$.ajax({
			url : "${ctx}/platform/saveAssessResult.do",
			data : {
				aqnId : aqnId,
				dimension : dimension,
				dimensionStr : dimensionStr,
				section : section,
				resultDetail : content,
				aqnCategory : aqnCategory
			},
			dataType : "json",
			type : "post",
			success : function(data){
				alert(data.msg);
				//保存完毕后返回列表
				backToList();
			}
			
		});
		
	});
	
}
/**
*初始化富文本内容
**/
function setContent(content,ues){
	//判断要设置的内容是否为空
	if(content!=''&content!=null&content!="null"){
		//进入这里表示不是空的就替换掉隐藏固定模版
		document.getElementById('result').innerHTML=content;
		//判断是否存在分值区域元素
		if($("#abstract_content").length > 0) {
    		//元素存在时执行的代码
    		var abstract_content=$("#abstract_content").val();
    		ues.ue9.setContent(abstract_content);
		}else{
			//元素不存在时执行
			//给初始化的模版中追加分值区域存储模版
			var temp='<input type="hidden" id="abstract_content" value="">';
			$("#result").append(temp);
		}
		//获取隐藏的模版内容，获取隐藏模版每个模块的标题
		//摘要
		var zy=document.getElementById('assessresult_abstract-content_box').innerHTML;
		//摘要标题
		var zytitle=document.getElementById('zy-title').innerHTML;
		//你的表现
		var ndbx=document.getElementById('assessresult-yourperformance-content-box').innerHTML;
		//你的表现标题
		var ndbxtitle=document.getElementById('ndbx-title').innerHTML;
		//原因分析
		var yyfx=document.getElementById('reasonanalysis-contentbox').innerHTML;
		//原因分析标题
		var yyfxtitle=document.getElementById('yy-title').innerHTML;
		//小故事
		var xgs=document.getElementById('littlestory-contentbox').innerHTML;
		//小故事标题
		var xgstitle=document.getElementById('xgs-title').innerHTML;
		//小贴士
		var xts=document.getElementById('tips-contentbox').innerHTML;
		//小贴士标题
		var xtstitle=document.getElementById('xts-title').innerHTML;
		//推荐阅读
		var tjyd=document.getElementById('recommended-contentbox').innerHTML;
		//推荐阅读标题
		var tjydtitle=document.getElementById('tjyd-title').innerHTML;
		//活动提示
		var hdts=document.getElementById('activity-contentbox').innerHTML;
		//活动提示标题
		var hdtstitle=document.getElementById('hdts-title').innerHTML;
		
		//将标题放到对应的input标签中
		//第二个模块
		document.getElementById('title-text-two').value=zytitle;
		//第三个模块
		document.getElementById('title-text-three').value=ndbxtitle;
		//第四个模块
		document.getElementById('title-text-four').value=yyfxtitle;
		//第五个模块
		document.getElementById('title-text-five').value=xgstitle;
		//第六个模块
		document.getElementById('title-text-six').value=xtstitle;
		//第七个模块
		document.getElementById('title-text-seven').value=tjydtitle;
		//第八个模块
		document.getElementById('title-text-eight').value=hdtstitle;
		//最后将每个模块的内容放到对应的富文本编辑器中
		ues.ue2.setContent(zy);
		ues.ue3.setContent(ndbx);
		ues.ue4.setContent(yyfx);
		ues.ue5.setContent(xgs);
		ues.ue6.setContent(xts);
		ues.ue7.setContent(tjyd);
		ues.ue8.setContent(hdts);
		//初始化checkbox
		//如果摘要模块是显示状态就勾选按钮
		if(document.getElementById('assessresult-abstract').style.display=='block'){
			//勾选摘要
			document.getElementById('check-box2').checked=true;
		}
		//如果你的表现模块是显示状态就勾选按钮
		if(document.getElementById('assessresult-yourperformance').style.display=='block'){
			//勾选你的表现
			document.getElementById('check-box3').checked=true;
		}
		//如果原因分析模块是显示状态就勾选按钮
		if(document.getElementById('reasonanalysis-box').style.display=='block'){
			//勾选你的原因分析
			document.getElementById('check-box4').checked=true;
		}
		//如果小故事模块是显示状态就勾选按钮
		if(document.getElementById('littlestory-box').style.display=='block'){
			//勾选你的小故事
			document.getElementById('check-box5').checked=true;
		}
		//如果小贴士是显示状态就勾选按钮
		if(document.getElementById('tips-box').style.display=='block'){
			//勾选你的小贴士
			document.getElementById('check-box6').checked=true;
		}
		//如果推荐阅读是显示状态就勾选按钮
		if(document.getElementById('recommended-box').style.display=='block'){
			//勾选推荐阅读
			document.getElementById('check-box7').checked=true;
		}
		//如果活动提示是显示状态就勾选按钮
		if(document.getElementById('activity-box').style.display=='block'){
			//勾选活动提示
			document.getElementById('check-box8').checked=true;
		}
	}
}

  </script>

</body>

</html>