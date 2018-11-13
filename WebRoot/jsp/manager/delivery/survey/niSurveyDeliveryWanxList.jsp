<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>投放问卷管理-调查问卷管理</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
</head>

<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    
    <!-- 内容部分 -->

      <!-- Main content -->
      <section class="content">
        <div class="panel panel-default">
          <div class="panel-body">
           <form class="form-horizontal" action="${ctx}/platform/niSurveyDeliveryWanxList.do"  method="post" id="formSearch">
              <div class="row mb10">
                <div class="col-sm-2">
                  <select class="form-control select-sm" id="sqnpublish" name="sqnpublish">
                  	<option value="2">投放id</option>
                    <option value="0">问卷名称</option>
                    <option value="1">出题人</option>
                  </select>
                </div>
                <div class="input-group input-group-sm col-sm-3">
                  <input id="sqnNameOrEditor" type="text" name="sqnNameOrEditor" class="form-control">
                  <span class="input-group-btn">
                      <button type="button" onclick="searchSueryQuestion()" class="btn btn-info btn-flat">查询</button>
                   </span>
                </div>
              </div>
              <div class="row mb10">
                <div class="col-sm-2">
                  <label class="select-label">报告</label>
                  <select class="form-control select-sm" id="qnclassid" name="qnclassid" style="width:75%;">
                    <option value="0">不限</option>
                    <option value="1">有</option>
                    <option value="2">无</option>
                  </select>
                </div>
                <div class="col-sm-2">
                    <label class="select-label">广告</label>
                  <select class="form-control select-sm" id="adclassid" name="adclassid" style="width:75%;">
                    <option value="0">不限</option>
                    <option value="1">有</option>
                    <option value="2">无</option>
                  </select>
                </div>
              </div>
              <div class="col-sm-16">
                  <label class="select-label">状态</label>
                  <div class="checkbox" style="display:inline-block;">
                    <label>
                      <input type="checkbox" value="0" onclick="checkAll();" id="cheall" name="checkbox1">
                   	     不限
                    </label>
                  </div>
                  <div class="checkbox" style="display:inline-block;">
                    <label>
                      <input type="checkbox" value="1" id="che1" name="checkbox1">
                    	  待投放
                    </label>
                  </div>
                    <div class="checkbox" style="display:inline-block;">
                    <label>
                      <input type="checkbox" value="2" id="che2" name="checkbox1">
                  	    投放中
                    </label>
                  </div>                
                  <div class="checkbox" style="display:inline-block;">
                    <label>
                      <input type="checkbox" value="3" id="che3" name="checkbox1">
                      	暂停中
                    </label>
                  </div>                  
                  <div class="checkbox" style="display:inline-block;">
                    <label>
                      <input type="checkbox" value="4" id="che4" name="checkbox1">
                  	   人工终止
                    </label>
                  </div>
                   <div class="checkbox" style="display:inline-block;">
                    <label>
                      <input type="checkbox" value="5" id="che5" name="checkbox1">
                    	  时间完成
                    </label>
                  </div>                 
                   <div class="checkbox" style="display:inline-block;">
                    <label>
                      <input type="checkbox" value="6" id="che6" name="checkbox1">
                    	 数量完成
                    </label>
                  </div>         
                  <input type="hidden" id="txt_spCodes" name="checkStats">                            
              </div>
          </form>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="button-group">
                <button type="button" onclick="addQuestionnaire();" class="btn btn-default btn-sm">添加</button>
                <button type="button" onclick="putQuestionnaire()" class="btn btn-default btn-sm">投放</button>
                <button type="button" onclick="withdrawnQuestionnaire()" class="btn btn-default btn-sm">撤回</button>
                <button type="button" onclick="stopQuestionnaire()" class="btn btn-default btn-sm">暂停</button>
                <button type="button" onclick="regenerationQuestionnaire()" class="btn btn-default btn-sm">恢复</button>
                <button type="button" onclick="overQuestionnaire()" class="btn btn-default btn-sm">终止</button>
            </div>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="box">
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th></th>
                  <th style="width: 10px;">编号</th>
                  <th>投放Id</th>
                  <th>问卷名称</th>
                  <th>出题人</th>
                  <th>状态</th>
                  <th>渠道</th>
                  <th>发布时间</th>
                  <th>截止时间</th>
                  <!-- <th>操作时间</th> -->
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${niSurveyDeliveryWanxs}" var="ns" varStatus="n">
	                <tr>
	                  <td><input type="checkbox" id="checkniSurveyQuestionnaire" name="checkniSurveyQuestionnaire" value="${ns.deliveryid }">
	                  	  <input type="hidden" value="${ns.status}" name="deleStat" id="deleStat"> 
	                  	  <input type="hidden" value="${ns.qstatus }" name="qstatus" id="qstatus">
	                  </td>
	                  <td>${n.index+1}</td>
	                  <td>${ns.deliveryid }</td>
	                  <td>
	                  	<div style="width: 280px;white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
	                  		${ns.sqnName }
	                  	</div>
	                  </td>
	                  <td>${ns.editor }</td>
	                  <td>
	                  <!-- 1待投放；2投放中；3暂停中；4人工终止；5时间完成；6数量完成 -->
	                  	<c:if test="${ns.status == 1 }">待投放</c:if>
	                  	<c:if test="${ns.status == 2 }">投放中</c:if>
	                  	<c:if test="${ns.status == 3 }">暂停中</c:if>
	                  	<c:if test="${ns.status == 4 }">人工终止</c:if>
	                  	<c:if test="${ns.status == 5 }">时间完成</c:if>
	                  	<c:if test="${ns.status == 6 }">数量完成</c:if>
	                  </td>
	                  <td>
	                  	<c:if test="${ns.channelId == 1}">完美校园</c:if>
	                  	<c:if test="${ns.channelId == 2}">微信</c:if>
	                  </td>
	                  <td>
	                  	<fmt:formatDate value="${ns.btime }" pattern="yyyy-MM-dd"/>
	                  </td>
	                  <td>
	                  	<fmt:formatDate value="${ns.etime }" pattern="yyyy-MM-dd"/>
	                  </td>
	                  <td>
	                 	 <input type="button" onclick="editniSurveyDeliveryWanx(${ns.deliveryid },${ns.sqnid });" value="编辑">
	                  </td>
	                </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
    </section>
    </div>
    
    <!-- 内容部分结束 -->
  <div class="modal fade" id="editCarousel" tabindex="-1" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" >×</button>
					<h4 class="modal-title" id="myModalLabel">编辑调查问卷投放管理</h4>
				</div>
				<div class="modal-body">
					<form id="pageListForm" method="POST" action="${ctx}/platform/niSurveyDeliveryWanxEditorSave.do" enctype="multipart/form-data">
						<table style="border-spacing: 10px;border-collapse: separate;">
							<tr>
								<td><label>投放id:</label></td> 
								<td colspan="3"><input readonly="readonly" style="width: 300px" name="deliveryid_show" id="deliveryid_show"></td>
							</tr>
							<tr>
								<td><label>问卷名称</label></td> 
								<td colspan="3"><input style="width: 300px" name="sqnname_show" id="sqnname_show"></td>
							</tr>
							<tr>
								<td><label>出题人</label></td>
								<td colspan="3"><input style="width: 300px" name="editor_show" id="editor_show"></td>
							</tr>
							<tr>
								<td><label>标签</label></td>
								<td>
									<input style="width: 130px" name="tag1Str_show" id="tag1Str_show">
								</td>
								<td>
									<input style="width: 130px" name="tag2Str_show" id="tag2Str_show">
								</td>
								<td>
									<input style="width: 130px" name="tag3Str_show" id="tag3Str_show">
								</td>
							</tr>	
							<tr>
								<td><label></label></td>
								<td>
									<input style="width: 130px" name="tag4Str_show" id="tag4Str_show">
								</td>
								<td>
									<input style="width: 130px" name="tag5Str_show" id="tag5Str_show">
								</td>
							</tr>
							<tr>
								<td><label>采集数量:</label></td> 
								<td colspan="3"><input style="width: 300px" name="collectNum_show" id="collectNum_show"></td>
							</tr>
							<tr>
								<td><label>显示标题:</label></td> 
								<td colspan="3"><input style="width: 300px" name="showTitle_show" id="showTitle_show"></td>
							</tr>
							<tr>
								<td><label>起始时间:</label></td> 
								<td colspan="1"><input style="height: 25px;width: 130px;text-align: left;" class="form-control pull-right" name="begintime_show" id="begintime_show"></td>
								<td colspan="1"><input style="height: 25px;width: 130px;" class="form-control pull-right" name="endtime_show" id="endtime_show"></td>
							</tr>
							<tr>
								<td><label>显示简介:</label></td> 
								<td colspan="3">
									<textarea style="width: 300px;" rows="" cols="" id="showDes_show" name="showDes_show"></textarea>
								</td>
							</tr>
							<tr>
								<td><label>投放图片:</label></td>
								<td colspan="2"><input type="file" name="uploadImgmes" id="uploadImgmes" value=""></td>
								<td colspan="1"><input type="button" onclick="uploadpic();" value="点击上传"></td>
							</tr>
							<tr>
								<td></td>
								<td  style="text-align:middle;"><img height="100px" width="130px" id="imgurl" src=""
									class="img-rounded"></td>
							    <td><div style="color: red;text-align:center;" id="errorMsg"></div></td>
							</tr>
							<tr>
								<td><label>广告id:</label></td>
								<td colspan="2"><input type="text" name="adId" id="adId" value=""></td>
							</tr>							
						</table>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" onclick="editniSurveyDeliveryWanxSave()"
						class="btn btn-primary">保存</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 运营调整 -->
    <div class="modal fade" id="operateChange" tabindex="-1" >
		<div class="modal-dialog">
			<div style="width: 800px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" >×</button>
					<h4 class="modal-title" id="myModalLabel">编辑运营调整</h4>
				</div>
				<div class="modal-body">
					<form>
				        <table style="border-spacing: 10px;border-collapse: separate;">
			         		<tr>
			         			<td><label>&nbsp;&nbsp;计划采集份数&nbsp;: &nbsp;</label></td> <td colspan="1"><input id="sqnName" type="text" maxlength="28" style="width: 200px;height: 30px;" class="form-control" value="" disabled="disabled"></td>
			         			<td><label>&nbsp;&nbsp;上线时间&nbsp;: &nbsp;</label></td><td colspan="1"><input id="sqnTime" type="text" maxlength="28" style="width: 200px;height: 30px;" class="form-control" value="" disabled="disabled"></td> 
			    			<td colspan="1">
			         			
			         			</td>
			         		</tr>
			         		<tr>
			         			<td><label>&nbsp;&nbsp;实际收集份数&nbsp;: &nbsp;</label></td> <td colspan="1"><input id="trueOrderNum" type="text" maxlength="28" style="width: 200px;height: 30px;" class="form-control" value="" disabled ></td>
			         			<td><label>&nbsp;&nbsp;页面显示份数&nbsp;: &nbsp;</label></td> <td colspan="1"><input id="showNum" type="text" maxlength="28" style="width: 200px;height: 30px;" class="form-control" value="" disabled="disabled"></td>
			         		</tr>    
			          		<tr>
			         			<td><label>&nbsp;&nbsp;使用调整数据&nbsp;: &nbsp;</label></td> <td><input id="datau1" value="调整" name="datau" type="radio" onclick="startChangeNum();" ></td>
			         			<td><label>&nbsp;&nbsp;使用真实数据&nbsp;: &nbsp;</label></td> <td><input id="datau2" value="真实" name="datau" type="radio" onclick="stopChangeNum();" checked="checked"></td>
			         		</tr>
			         		<tr>
			         			<td><label>&nbsp;&nbsp;模拟增长数值&nbsp;: &nbsp;</label></td> <td colspan="5"><input id="simulationNum" type="text" maxlength="28" style="width: 200px;height: 30px;" class="form-control" value="" disabled="disabled" ></td>
			         		</tr>        		     		
			         		<tr>
			         			<td><label>&nbsp;&nbsp;时间&nbsp;: &nbsp;</label></td>
			         		    <td colspan="5">
			         		    	<select id="publisherTime" style="width: 170px;height: 30px;">
			         		    		<option value="0.5">0.5小时</option>
			         		    		<option value="1">1小时</option>
			         		    		<option value="2">2小时</option>
			          		    		<option value="5">5小时</option>
			         		    		<option value="10">10小时</option>
			         		    		<option value="15">15小时</option>
			         		    		<option value="24">24小时</option>          		    		
			         		    	</select></td>
			         		    <td><input type="hidden" id="sqn_Id" value=""><input type="hidden" id="delivery_id" value=""> </td>
			         		</tr>   
			          		<tr>
			         			<td><label>&nbsp;&nbsp;目标增长数量&nbsp;: &nbsp;</label></td> <td colspan="5"><input id="publisherNum" type="text" maxlength="28" style="width: 200px;height: 30px;" class="form-control" value="" ></td>
			         		</tr>        	     		
			         	</table>
					  </form>
				</div>
				<div class="modal-footer">
					<button type="button" onclick="surveyDeliveryOperateSave()" class="btn btn-primary">保存</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
				</div>
			</div>
			</div>
		</div>
	</div>
	

<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/js/validate.js"></script> 
  <script>
  $(function () {
    $("#example1").DataTable();
  });

  //开始时间，截至时间
 $('#begintime_show').datepicker({
     autoclose: true
   });
 $('#endtime_show').datepicker({
  autoclose: true
 });
 
 var sqnId = "";
//图片路径
 var imgurl = "";
 //图片的存储路径
 var jdbcUrl= "";
 
 //显示收集份数定时
 var set_interval;
 //定时根据开关设置显示收集份数
 var adjust_ShowNum;
	 $(function(){
		 $('#operateChange').on('hidden.bs.modal', function () {
			 	//终止 显示收集分数调用
			 	clearInterval(set_interval);
			 	clearInterval(adjust_ShowNum);
			 	
			});
	 });
	 
	  //上传图片即可显示
/* 	  function PreviewImage(imgFile){
			var path;
			if (document.all)//IE 
			{
				imgFile.select();
				path = document.selection.createRange().text;
				document.getElementById("imgurl").innerHTML = "";
			} else
			{
				path = URL.createObjectURL(imgFile.files[0]);
				$("#imgurl").attr("src", path);
			}
		  
	  } */
	 
	 //使用调整数据
	 function startChangeNum(){
		$("#publisherNum").attr("disabled",false);  
	 }
	 //使用真实数据
	 function stopChangeNum(){
	 	$("#publisherNum").attr("disabled",true);
	 }
	 //弹出运营调整模态框
	 function OperationChange(deliveryid,sqnId){
	 	$.ajax({
	 		url:'${ctx}/platform/niSurveyDeliveryWanxOperateModal.do',
	 		data:{
	 			deliveryid : deliveryid,
	 			sqnId : sqnId
	 		},
	 		success:function(data){
			   	$("#operateChange").modal("show");
			   	if(data.success){
			   		$("#sqnName").val(data.niSurveyDeliveryWanx.collectnum);  //计划采集分数
			   		$("#sqnTime").val(data.niSurveyDeliveryWanx.btime);   //上线时间
			   		$("#trueOrderNum").val(data.trueOrderNum);    //实际收集分数
			   		$("#showNum").val(data.niSurveyDeliveryWanx.collectednum);  //页面显示分数
			   		
			   		$("#delivery_id").val(deliveryid);
			   		$("#sqn_Id").val(sqnId);
			   	}
			//   	set_interval = setInterval("changeShowNum("+deliveryid+","+sqnId+")",1000*60*3);  //定时请求显示运营调整数据   生产时
			
			set_interval = setInterval("changeShowNum("+deliveryid+","+sqnId+")",1000*3);   //测试     3秒钟
			
			//   	adjust_ShowNum = setInterval("adjustShowNum("+deliveryid+","+sqnId+")",1000*60*3); //定时检查开关状态,设置显示收集份数
	 		}
	 	});
	 }
    //保存运营调整
    function surveyDeliveryOperateSave(){
    	var showNum = $('#showNum').val();  //显示收集份数
    	var publisherTime = $('#publisherTime').val();   //时间
    	var publisherNum = $('#publisherNum').val();    //目标增长数量
    	var trueOrderNum = ${trueOrderNum}+"";   // 真实数据
    	
		var delivery_id = $("#delivery_id").val();
		var sqn_Id = $("#sqn_Id").val();

    	var datauCheck = document.getElementById("datau1");
    	//开启运营调整才会提交
    	if(datauCheck.checked){
    		if(publisherNum.trim()==""){
    			showMessageDialog("请输入目标增长数");
    			return;
    		}
    		
    		if(!checkNum(publisherNum)){
    			showMessageDialog("目标增长数只能输入首位非0数字");
    			return;
    		}
    		
    		$.ajax({
    			url:'${ctx}/platform/niSurveyDeliveryWanxOperateSave.do',
    			type:'post',
    			dataType:'json',
    			data:{
    				showNum : showNum ,
    				publisherTime : publisherTime,
    				publisherNum : publisherNum,
    				deliveryid : delivery_id,
    				sqnId : sqn_Id,
    				trueOrderNum : trueOrderNum
    			},
    			success:function(data){
    				if(data.success){
    					$("#operateChange").modal("hide");
    					showMessageDialog(data.msg);
    				}
    			}
    		});
    	}else{
    		//关闭运营调整
    	}
    }
    //定时根据开关修改显示收集份数
/*     function adjustShowNum(deliveryid,sqnId){
    	
    	var datau1 = document.getElementById("datau1");
    //	var datau2 = document.getElementById("datau2");
    	
    	if(datau1.checked){
    		bj = 0;
    	}else{
    		bj = 1;
    	}
    	
		//将调整数据设置为显示收集份数
		$.ajax({
			url:'${ctx}/platform/AdjustSurveyDeliveryWanxOperate.do',
			dataType:'json',
			data:{
				bj : bj,
				deliveryid : deliveryid,
				sqnId : sqnId
			},
			success:function(data){
				
			}
		});
    } */
    //请求显示运营调整数据
    function changeShowNum(deliveryid,sqnId){
/*     	if(sqnId == "" || deliveryid == ""){
    		showMessageDialog("未选择问卷");
    		return;
    	}
    	 */
    //	var collectednum = null;
    	$.ajax({
    		url:'${ctx}/platform/findShowOrderNum.do',
    		type:'post',
    		dataType:'json',
    		data:{
    			sqnId : sqnId,
    			deliveryid : deliveryid
    		},
    		success:function(data){
    			$('#showNum').val(data.collectednum);
    			$('#simulationNum').val(data.simulationNum);
    		}
    	});
    }
    
 
 //保存编辑
 function editniSurveyDeliveryWanxSave(){
	 var deliveryid_show = $.trim($("#deliveryid_show").val()); 
	 var collectNum_show = $.trim($("#collectNum_show").val()); 
	 var showTitle_show =  $.trim($("#showTitle_show").val());
	 var begintime_show =  $.trim($("#begintime_show").val());
	 var endtime_show =  $.trim($("#endtime_show").val());
	 
	 var sqnname_show = $.trim($("#sqnname_show").val());   //问卷名称
	 var editor_show = $.trim($("#editor_show").val());    //出题人
	 var tag1Str_show= $.trim($("#tag1Str_show").val());   //标签
	 var tag2Str_show= $.trim($("#tag2Str_show").val());   //标签
	 var tag3Str_show= $.trim($("#tag3Str_show").val());   //标签
	 var tag4Str_show= $.trim($("#tag4Str_show").val());   //标签
	 var tag5Str_show= $.trim($("#tag5Str_show").val());   //标签
	 var showDes_show= $.trim($("#showDes_show").val());   //显示简介
	 
	 var adId = $.trim($("#adId").val());  //广告id
	 
	 var errorMsg = $("#errorMsg");
	 errorMsg.html("");
	 
	 if(deliveryid_show == ""){
		 errorMsg.html("丢失投放id");
		 return false;
	 }else if(sqnname_show == ""){
		 errorMsg.html("请输入问卷名称");
		 return false;
	 }else if(editor_show == ""){
		 errorMsg.html("请输入出题人");
		 return false;
	 }else if(showTitle_show == ""){
		 errorMsg.html("请输入标题");
		 return false;
	 }else if(begintime_show == ""){
		 errorMsg.html("请输入开始时间");
		 return false;
	 }else if(endtime_show == ""){
		 errorMsg.html("请输入截至时间");
		 return false;
	 }
	 
	 $.ajax({
		 url:'${ctx}/platform/niSurveyDeliveryWanxEditorSave.do',
		 dataType:'json',
		 type:'post',
		 data:{
			 deliveryid_show : deliveryid_show,
			 sqnId : sqnId,
			 collectNum_show : collectNum_show,
			 showTitle_show : showTitle_show,
			 begintime_show : begintime_show,
			 endtime_show : endtime_show,
			 imgmes  : jdbcUrl,
			 sqnname_show : sqnname_show,
			 editor_show : editor_show,
			 tag1Str_show: tag1Str_show,
			 tag2Str_show: tag2Str_show,
			 tag3Str_show: tag3Str_show,
			 tag4Str_show: tag4Str_show,
			 tag5Str_show: tag5Str_show,
			 showDes_show: showDes_show,
			 adId : adId
			 
		 },
		 success:function(data){
			$("#editCarousel").modal("hide");
			showMessageDialog(data.msg);
		 }
	 });
	 
 }
 //编辑投放
 function editniSurveyDeliveryWanx(deliveryid,sqnId){
	 window.location.href = "${ctx}/platform/niSurveyDeliveryWanxAddMES_Edit.do?deliveryid="+deliveryid;
 }
  //编辑跳转
  function editniSurveyDeliveryWanx_bak(deliveryid){
	  $.ajax({
		  url:'${ctx}/platform/niSurveyDeliveryWanxEditor.do',
		  type:'post',
		  data:{
			  deliveryid : deliveryid
		  },
		  dataType:'json',
		  success:function(data){
			  $("#editCarousel").modal("show");
			  $("#deliveryid_show").val(data.sdw.deliveryid);
			  $("#sqnname_show").val(data.sqt.sqnname);  //问卷名称
			  $("#editor_show").val(data.sqt.editor);   //出题人
			  
			  $("#tag1Str_show").val(data.sdw.tag1str);  //标签
			  $("#tag2Str_show").val(data.sdw.tag2str);  //标签
			  $("#tag3Str_show").val(data.sdw.tag3str);  //标签
			  $("#tag4Str_show").val(data.sdw.tag4str);  //标签
			  $("#tag5Str_show").val(data.sdw.tag5str);  //标签
			  
			  $("#collectNum_show").val(data.sdw.collectnum);
			  $("#showTitle_show").val(data.sdw.showtitle);
			  $("#showDes_show").val(data.sdw.showdes);   //问卷显示简介
			  $("#adId").val(data.sdw.adid);   //广告id
			  
			  $("#begintime_show").val(data.btimeStr);
			  $("#endtime_show").val(data.etimeStr);
			  $(".img-rounded").attr("src",data.sdw.img);
			  
			  sqnId = data.sdw.sqnid;
			  jdbcUrl = data.sdw.img;
			  
		  },
		  error:function(){
			  showMessageDialog("请求失败");
		  }
		  
	  });
  }
  //上传图片
  function uploadpic(){
	  var formData = new FormData($("#pageListForm")[0]);
	  $.ajax({
		  url: '${ctx}/platform/niSurveyUploadPicture.do?sqnId='+sqnId, 
		  type:'post',
		  data: formData,
		  dataType:'json',
          async: false,  
          cache: false,  
          contentType: false,  
          processData: false,  
		  success:function(data){
             imgurl = data.url;
             jdbcUrl=data.jdbcUrl;
             $(".img-rounded").attr("src",imgurl);
		  }
		  
	  });
	  
  }
  
  //终止
  function overQuestionnaire(){
	  var checkniSurveyQuestionnaire = document.getElementsByName("checkniSurveyQuestionnaire");
	  var deleStats = document.getElementsByName("deleStat");
	  var count = 0;
	  var deliveryid = "";
	  for(var i=0;i<checkniSurveyQuestionnaire.length;i++){
		  if(checkniSurveyQuestionnaire[i].checked){
			  var stat = deleStats[i].value;
			  if(stat != 3 && stat != 2 && stat != 1){
				  showMessageDialog("该问卷不能被终止 , 请检查问卷的状态 ");
				  return;
			  }
			  count++;
			  deliveryid = checkniSurveyQuestionnaire[i].value+"|"+deliveryid;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择要操作的问卷");
		  return;
	  }
	  showConfirmDialog("确定要终止吗?",function(check){
		  if(check){
			// 状态 2
			  var statu2 = 4;
			   $.ajax({
				  type:"post",
				  dataType:"json",
				  data:{
					  deliveryid : deliveryid,
					  statu : statu2
				  },
				  url:"${ctx}/platform/overStatSurveyDeliveryWanx.do",
				  success:function(data){
					  showMessageDialog(data.msg, function(){
						  if(data.success){
							  window.location.href = "${ctx}/platform/niSurveyDeliveryWanxList.do";
						  }
					  });
				  }
			  }); 
		  }
	  });
  }
  
  // 恢复
  function regenerationQuestionnaire(){
	  var checkniSurveyQuestionnaire = document.getElementsByName("checkniSurveyQuestionnaire");
	  var deleStats = document.getElementsByName("deleStat");
	  var qstatus_arr = document.getElementsByName("qstatus");
	  var count = 0;
	  var deliveryid = "";
	  for(var i=0;i<checkniSurveyQuestionnaire.length;i++){
		  if(checkniSurveyQuestionnaire[i].checked){
			  var stat = deleStats[i].value;   //投放状态
			  var _qstatus = qstatus_arr[i].value; //问卷状态
			  if(stat != 3){
				  showMessageDialog("只能恢复暂停中问卷");
				  return;
			  }else if(_qstatus != 2){
				 showMessageDialog("问卷必须是定稿状态才能恢复");
				 return;
			  }
			  count++;
			  deliveryid = checkniSurveyQuestionnaire[i].value+"|"+deliveryid;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择要操作的问卷");
		  return;
	  }
	  // 状态 2
	  var statu2 = 2;
	  
	   $.ajax({
		  type:"post",
		  dataType:"json",
		  data:{
			  deliveryid : deliveryid,
			  statu : statu2
		  },
		  url:"${ctx}/platform/updateStatSurveyDeliveryWanx.do",
		  success:function(data){
			  showMessageDialog(data.msg, function(){
				  if(data.success){
					  window.location.href = "${ctx}/platform/niSurveyDeliveryWanxList.do";
				  }
			  });
		  }
	  }); 
	  
  }
  
  //暂停
  function stopQuestionnaire(){
	  var checkniSurveyQuestionnaire = document.getElementsByName("checkniSurveyQuestionnaire");
	  var deleStats = document.getElementsByName("deleStat");
	  var count = 0;
	  var deliveryid = "";
	  for(var i=0;i<checkniSurveyQuestionnaire.length;i++){
		  if(checkniSurveyQuestionnaire[i].checked){
			  var stat = deleStats[i].value;
			  if(stat != 2){
				  showMessageDialog("只能暂停投放中问卷");
				  return;
			  }
			  count++;
			  deliveryid = checkniSurveyQuestionnaire[i].value+"|"+deliveryid;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择要操作的问卷");
		  return;
	  }
	  // 状态 2
	  var statu2 = 3;
	   $.ajax({
		  type:"post",
		  dataType:"json",
		  data:{
			  deliveryid : deliveryid,
			  statu : statu2
		  },
		  url:"${ctx}/platform/stopStatSurveyDeliveryWanx.do",
		  success:function(data){
			  showMessageDialog(data.msg, function(){
				  if(data.success){
					  window.location.href = "${ctx}/platform/niSurveyDeliveryWanxList.do";
				  }
			  });
		  }
	  }); 
  }
  
  //撤回
  function withdrawnQuestionnaire(){
	  var checkniSurveyQuestionnaire = document.getElementsByName("checkniSurveyQuestionnaire");
	  var deleStats = document.getElementsByName("deleStat");
	  var count = 0;
	  var deliveryid = "";
	  for(var i=0;i<checkniSurveyQuestionnaire.length;i++){
		  if(checkniSurveyQuestionnaire[i].checked){
			  var stat = deleStats[i].value;
			  if(stat != 2){
				  showMessageDialog("只能撤回投放中的问卷");
				  return;
			  }
			  count++;
			  deliveryid = checkniSurveyQuestionnaire[i].value+"|"+deliveryid;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择要操作的问卷");
		  return;
	  }
	  // 状态 2
	  var statu2 = 1;
	  showConfirmDialog("确定要撤回问卷吗?", function(check){
		  if(check){
			   $.ajax({
					  type:"post",
					  dataType:"json",
					  data:{
						  deliveryid : deliveryid,
						  statu : statu2
					  },
					  url:"${ctx}/platform/withdrawnQuestionnaire.do",
					  success:function(data){
						  showMessageDialog(data.msg, function(){
							  if(data.success){
								  window.location.href = "${ctx}/platform/niSurveyDeliveryWanxList.do";
							  }
						  });
					  }
				  }); 
		  }
	  });
  }
  
  //投放
  function putQuestionnaire(){
	  var checkniSurveyQuestionnaire = document.getElementsByName("checkniSurveyQuestionnaire");
	  var deleStats = document.getElementsByName("deleStat");  // 投放状态
	  var qstatus_arr = document.getElementsByName("qstatus"); //问卷状态
	  var count = 0;
	  var deliveryid = "";
	  for(var i=0;i<checkniSurveyQuestionnaire.length;i++){
		  if(checkniSurveyQuestionnaire[i].checked){
			  var stat = deleStats[i].value;
			  var q_stat = qstatus_arr[i].value; //问卷状态
			  if(stat != 1){
				  showMessageDialog("只能投放待投放状态");
				  return;
			  }else if(q_stat != 2){
					showMessageDialog("所选问卷必须是定稿状态");
					return;
			  }
			  count++;
			  deliveryid = checkniSurveyQuestionnaire[i].value+"|"+deliveryid;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择要操作的问卷");
		  return;
	  }
	  //投放中状态 2
	  var statu2 = 2;
	  
	   $.ajax({
		  type:"post",
		  dataType:"json",
		  data:{
			  deliveryid : deliveryid,
			  statu : statu2
		  },
		  url:"${ctx}/platform/updateStatSurveyDeliveryWanx.do",
		  success:function(data){
			  showMessageDialog(data.msg, function(){
				  if(data.success){
					  window.location.href = "${ctx}/platform/niSurveyDeliveryWanxList.do";
				  }
			  });
		  }
	  }); 
  }
  
  // 添加
  function addQuestionnaire(){
	  window.location.href = "${ctx}/platform/niSurveyDeliveryWanxAddQue.do";
  }
  
  
  var sqnNameOrpublisherNameL = 20;
  //查询
  function searchSueryQuestion(){
		var sqnpublish = $('#sqnpublish').val();  //问卷名称出题人标记
		var sqnNameOrEditor = $('#sqnNameOrEditor').val();   //问卷名称或出题人
		var qnclassid = $('#qnclassid').val(); //报告
		var adclassid = $('#adclassid').val();     //广告
		//获取所有被选中的check的value
		var spCodesTemp = "";
		 $('input:checkbox[name=checkbox1]:checked').each(function(i){
		  if(0==i){
		   spCodesTemp = $(this).val();
		  }else{
		   spCodesTemp += ("|"+$(this).val());
		  }
		 });
		  $("#txt_spCodes").val(spCodesTemp); 
		
		if(checkQuote(sqnNameOrEditor)){
			showMessageDialog("查询条件请勿输入特殊字符");
			return;
		}
		
		$('#formSearch').submit();
  }
  </script>
</body>

</html>