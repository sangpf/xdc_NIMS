<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>倾向型抽奖管理</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  
  <style>
    .mb10 {
      margin-bottom: 1px;
    }
    
    .select-label {
      float: left;
      line-height: 30px;
      font-size: 12px;
      margin-right: 10px;
    }
    
    .select-sm {
      height: 30px;
      font-size: 12px;
    }
    
    .ft-12 {
      font-size: 12px;
    }
    
    .pd-15 {
      padding: 0 15px;
    }
    
    .button-group button {
      width: 100px;
      margin: auto 10px;
    }
  </style>
</head>

<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    
    <!-- 内容部分 -->
   	   <table style="padding: 100px;margin: 10px;">
          	<tr>
          		<td style="width: 100px;padding: 3px;"><button type="button" onclick="uestionnairePage();" class="btn btn-block btn-primary">随机抽奖</button></td>
          		<td style="width: 100px;padding: 3px;"><button type="button"  style="background-color: #BC8F8F" onclick="lotteryPreferPage();" class="btn btn-block btn-primary">倾向抽奖</button></td>
          	</tr>
         </table>

      <section class="content">
        <div class="panel panel-default">
          <div class="panel-body">
            <form class="form-horizontal"  method="post" action="${ctx}/platform/lotteryList.do" id ="pageListForm">
              <div class="row mb10">
                <div class="col-sm-2">
                  <select class="form-control select-sm" id="searchOption" name = "searchOption">
                    <option value="">请选择</option>
                    <option value="1">抽奖ID</option>
                    <option value="2">抽奖名称</option>
                  </select>
                </div>
                <div class="input-group input-group-sm col-sm-3">
                  <input id="searchContent" type="text" class="form-control" name = "searchContent">
                  <span class="input-group-btn">
                      <button type="button" onclick="searchLottery()" class="btn btn-info btn-flat">Go!</button>
                   </span>
                </div>
              </div>                     
           </form>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="button-group">
                <button type="button" onclick="showLotteryPreferAdd_Modal()" class="btn btn-default btn-sm">添加</button>
                <button type="button" onclick="deleteLotteryPrefer()" class="btn btn-default btn-sm">删除</button>
            </div>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="box">
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th width="20px"></th>
                  <th width="40px">编号</th>
                  <th width="50px">抽奖ID</th>
                  <th>抽奖名称</th>
                  <th>低区间</th>
                  <th>中区间</th>
                  <th>高区间</th>
                  <th width="35px">操作</th>
                </tr>
                </thead>
                <tbody>
                   <c:forEach items="${niLotteryPreferDict_List}" var="niLotteryPreferDict" varStatus="n">
                      <tr>
                        <td><input type="checkbox" id ="checkLottery" name="checkLottery" value="${niLotteryPreferDict.evaluateId }"></td>
                        <td>${n.index+1}</td>
                        <td>${niLotteryPreferDict.evaluateId}</td>
                         <td>${niLotteryPreferDict.ruleName}</td>
                        <td>${niLotteryPreferDict.lowBegin} ~ ${niLotteryPreferDict.lowEnd}</td>
                        <td>${niLotteryPreferDict.midBegin} ~ ${niLotteryPreferDict.midEnd}</td>
                        <td>${niLotteryPreferDict.highBegin} ~ ${niLotteryPreferDict.highEnd}</td>
                       <td>
							<button type="button" onclick="toLotteryEditPage(${niLotteryPreferDict.evaluateId})"
							class="btn btn-default btn-sm">编辑</button>
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
    
    <!-- 添加倾向抽奖模态框 -->
    <div class="modal fade" id="lotteryPreferAdd_Modal" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	 <div class="modal-dialog">
    	 	<div class="modal-content">
    	 		<div class="modal-header">
            		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
           			<h4 class="modal-title" id="myModalLabel">添加倾向抽奖规则</h4>
           		</div>
           		
           		<div class="modal-body">
           	<form class="form-horizontal" role="form">
           		<div class="form-group">
 					<label class="control-label col-sm-2" >规则名称</label>
  					<div class="col-sm-9">
  						<input type="text" class="form-control " id="ruleName_add">
					</div>
				</div>
				  <div class="box-footer"></div>   
				       
				<!-- 高区间 -->
				<div class="form-group has-success">
					<label class="control-label col-sm-2">高区间</label>				
				</div>
				<div class="form-group">
					<label class="control-label  col-sm-2" >奖品ID</label>
  					<div class=" col-sm-3">
  					 	<input type="text" class="form-control" id="histAwardId_add">
					</div>
 					<label class="control-label col-sm-2" >中奖概率</label>
	  					<div class="col-sm-4">
	  					<input type="text" class="form-control" id="highRate_add">   						 					
						</div>
					<label class="control-label col-sm-2" >起始值</label>	
						<div class="col-sm-3">
	  					<input type="text" class="form-control" id="highBegin_add">   						 					
						</div>	
					<label class="control-label col-sm-2" >结束值</label>
						<div class="col-sm-4">
	  					<input type="text" class="form-control " id="highEnd_add">   						 					
						</div>
						
					<label class="control-label col-sm-2" >奖品总数</label>	
						<div class="col-sm-3">
	  					<input type="text" class="form-control" id="highAwardTotal_add">   						 					
						</div>						
							
				</div>
				  <div class="box-footer"></div>     
				     
			    <!-- 中区间 -->
     		    <div class="form-group has-success">
					<label class="control-label col-sm-2">中区间</label>				
				</div>
				<div class="form-group">
					<label class="control-label  col-sm-2" >奖品ID</label>
  					<div class=" col-sm-3">
  						<input type="text" class="form-control" id="midAwardId_add">
					</div>
 					<label class="control-label col-sm-2" >中奖概率</label>
  					<div class="col-sm-4">
  					<input type="text" class="form-control " id="midRate_add">   						 					
					</div>
					<label class="control-label col-sm-2" >起始值</label>	
						<div class="col-sm-3">
	  					<input type="text" class="form-control " id="midBegin_add">   						 					
						</div>	
					<label class="control-label col-sm-2" >结束值</label>
						<div class="col-sm-4">
	  					<input type="text" class="form-control " id="midEnd_add">   						 					
						</div>	
						
					<label class="control-label col-sm-2" >奖品总数</label>	
						<div class="col-sm-3">
	  					<input type="text" class="form-control" id="midAwardTotal_add">   						 					
						</div>							
						
				</div>
   		      <div class="box-footer"></div>        
         		
         	  <!-- 地区间  -->
         		<div class="form-group has-success">
					<label class="control-label col-sm-2">低区间</label>				
				</div>
				<div class="form-group">
					<label class="control-label  col-sm-2" >奖品ID</label>
  					<div class=" col-sm-3">
  					<input type="text" class="form-control" id="lowAwardId_add">
					</div>
 					<label class="control-label col-sm-2" >中奖概率</label>
  					<div class="col-sm-4">
  					<input type="text" class="form-control " id="lowRate_add">   						 					
					</div>
					<label class="control-label col-sm-2" >起始值</label>	
						<div class="col-sm-3">
	  					<input type="text" class="form-control " id="lowBegin_add">   						 					
						</div>	
					<label class="control-label col-sm-2" >结束值</label>
						<div class="col-sm-4">
	  					<input type="text" class="form-control " id="lowEnd_add">   						 					
						</div>
						
					<label class="control-label col-sm-2" >奖品总数</label>	
						<div class="col-sm-3">
	  					<input type="text" class="form-control" id="lowAwardTotal_add">   						 					
						</div>							
				</div>
				<div class="box-footer"></div>    
				
				<div class="form-group">
	         		<label class="control-label col-sm-2">备注</label>	
	         		<textarea class="col-sm-8" id="comment_add"  rows="1"  placeholder="enter ..."></textarea>
        		</div>
        		<div style="color: red;text-align:center;" id="errorMsg"></div>      
         </form>          			
           		</div>
           		<div class="modal-footer">
            		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            		<button type="button" onclick="lotteryPreferAdd_Save();" class="btn btn-primary">保存</button>
         		</div>
    	 	</div>
    	 </div>
    </div>
    
     <!-- 编辑倾向抽奖模态框 -->
    <div class="modal fade" id="lotteryPreferEdit_Modal" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	 <div class="modal-dialog">
    	 	<div class="modal-content">
    	 		<div class="modal-header">
            		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
           			<h4 class="modal-title" id="myModalLabel">添加倾向抽奖规则</h4>
           		</div>
           		
           		<div class="modal-body">
           	<form class="form-horizontal" role="form">
           		<div class="form-group">
 					<label class="control-label col-sm-2" >规则名称</label>
  					<div class="col-sm-9">
  						<input type="text" class="form-control" id="ruleName_edit">
  						<input type="hidden" id="evaluateId_edit">
					</div>
				</div>
				  <div class="box-footer"></div>   
				       
				<!-- 高区间 -->
				<div class="form-group has-success">
					<label class="control-label col-sm-2">高区间</label>				
				</div>
				<div class="form-group">
					<label class="control-label  col-sm-2" >奖品ID</label>
  					<div class=" col-sm-3">
  					 	<input type="text" class="form-control" id="histAwardId_edit">
					</div>
 					<label class="control-label col-sm-2" >中奖概率</label>
	  					<div class="col-sm-4">
	  					<input type="text" class="form-control" id="highRate_edit">   						 					
						</div>
					<label class="control-label col-sm-2" >起始值</label>	
						<div class="col-sm-3">
	  					<input type="text" class="form-control" id="highBegin_edit">   						 					
						</div>	
					<label class="control-label col-sm-2" >结束值</label>
						<div class="col-sm-4">
	  					<input type="text" class="form-control " id="highEnd_edit">   						 					
						</div>	

					<label class="control-label col-sm-2" >奖品总数</label>	
						<div class="col-sm-3">
	  					<input type="text" class="form-control" id="highAwardTotal_edit">   						 					
						</div>	
												
				</div>
				  <div class="box-footer"></div>     
				     
			    <!-- 中区间 -->
     		    <div class="form-group has-success">
					<label class="control-label col-sm-2">中区间</label>				
				</div>
				<div class="form-group">
					<label class="control-label  col-sm-2" >奖品ID</label>
  					<div class=" col-sm-3">
  						<input type="text" class="form-control" id="midAwardId_edit">
					</div>
 					<label class="control-label col-sm-2" >中奖概率</label>
  					<div class="col-sm-4">
  					<input type="text" class="form-control " id="midRate_edit">   						 					
					</div>
					<label class="control-label col-sm-2" >起始值</label>	
						<div class="col-sm-3">
	  					<input type="text" class="form-control " id="midBegin_edit">   						 					
						</div>	
					<label class="control-label col-sm-2" >结束值</label>
						<div class="col-sm-4">
	  					<input type="text" class="form-control " id="midEnd_edit">   						 					
						</div>	
						
					<label class="control-label col-sm-2" >奖品总数</label>	
						<div class="col-sm-3">
	  					<input type="text" class="form-control" id="midAwardTotal_edit">   						 					
						</div>							
				</div>
   		      <div class="box-footer"></div>        
         		
         	  <!-- 地区间  -->
         		<div class="form-group has-success">
					<label class="control-label col-sm-2">低区间</label>				
				</div>
				<div class="form-group">
					<label class="control-label  col-sm-2" >奖品ID</label>
  					<div class=" col-sm-3">
  					<input type="text" class="form-control" id="lowAwardId_edit">
					</div>
 					<label class="control-label col-sm-2" >中奖概率</label>
  					<div class="col-sm-4">
  					<input type="text" class="form-control " id="lowRate_edit">   						 					
					</div>
					<label class="control-label col-sm-2" >起始值</label>	
						<div class="col-sm-3">
	  					<input type="text" class="form-control " id="lowBegin_edit">   						 					
						</div>	
					<label class="control-label col-sm-2" >结束值</label>
						<div class="col-sm-4">
	  					<input type="text" class="form-control " id="lowEnd_edit">   						 					
						</div>
						
					<label class="control-label col-sm-2" >奖品总数</label>	
						<div class="col-sm-3">
	  					<input type="text" class="form-control" id="lowAwardTotal_edit">   						 					
						</div>								
				</div>
				<div class="box-footer"></div>    
				
				<div class="form-group">
	         		<label class="control-label col-sm-2">备注</label>	
	         		<textarea class="col-sm-8" id="comment_edit"  rows="1"  placeholder="enter ..."></textarea>
        		</div>
        		<div style="color: red;text-align:center;" id="errorMsg_Edit"></div>      
         </form>          			
           		</div>
           		<div class="modal-footer">
            		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            		<button type="button" onclick="lotteryPreferEdit_Save();" class="btn btn-primary">编辑保存</button>
         		</div>
    	 	</div>
    	 </div>
    </div>


  <div class="control-sidebar-bg"></div>
  <script src="${ctx}/js/validate.js"></script>
  <script>
  
   //跳转到随机抽奖页面
   function uestionnairePage(){
	   window.location.href = "${ctx}/platform/lotteryList.do";
   }
   
	//打开添加倾向抽奖页面
	function showLotteryPreferAdd_Modal(){
		//$('#lotteryPreferAdd_Modal').modal('show');
		
		window.location.href = "${ctx}/platform/jumpTo_lotteryPreferAddPage.do";
		
	}
	
	//删除倾向抽奖规则
	function deleteLotteryPrefer(){
		var checkLotteryId_arr = "";
		var count = 0;
		var checkLottery_arr = document.getElementsByName("checkLottery");
		
		for(var i=0; i<checkLottery_arr.length;i++){
			if(checkLottery_arr[i].checked){
				count ++;
				checkLotteryId_arr = checkLottery_arr[i].value+"|"+checkLotteryId_arr;
			}
		}
		if(count == 0){
			showMessageDialog("选择要删除的选项");
			return false;
		}
		
		$.ajax({
			url : "${ctx}/platform/lotteryPreferPageDelte.do?checkLotteryId_arr="+checkLotteryId_arr,
			dataType:'json',
			type:'post',
			success : function(data){
				showMessageDialog(data.msg,function(){
					window.location.href = "${ctx}/platform/lotteryPreferPageList.do";
				});
				
			},
			error : function(){
				showMessageDialog("网络异常");
			}
		});
		
	}
	
	//添加倾向抽奖规则
	function lotteryPreferAdd_Save(){
		var errorMsg = $('#errorMsg');
		var ruleName_add = $('#ruleName_add').val();
		
		var histAwardId_add = $('#histAwardId_add').val();
		var highRate_add = $('#highRate_add').val();   //高区间中奖概率
		var highBegin_add =$('#highBegin_add').val();
		var highEnd_add = $('#highEnd_add').val();
		var highAwardTotal_add = $('#highAwardTotal_add').val(); //1等奖奖品总数
		
		var midAwardId_add = $('#midAwardId_add').val();
		var midRate_add = $('#midRate_add').val();  //中奖概率
		var midBegin_add = $('#midBegin_add').val();
		var midEnd_add = $('#midEnd_add').val();
		var midAwardTotal_add = $('#midAwardTotal_add').val();
		
		var lowAwardId_add = $('#lowAwardId_add').val();
		var lowRate_add = $('#lowRate_add').val();   //中奖概率
		var lowBegin_add = $('#lowBegin_add').val();
		var lowEnd_add = $('#lowEnd_add').val();
		var lowAwardTotal_add = $('#lowAwardTotal_add').val(); //奖品总数
		
		var comment_add = $('#comment_add').val();
		
		if(ruleName_add == ""){
			errorMsg.html("请录入规则名称");
			return false;
		}
		
		else if(histAwardId_add == ""){
			errorMsg.html("高区间奖品id未录入");
			return false;
		}else if(!checkNumber(histAwardId_add)){
			errorMsg.html("高区间奖品id格式录入有误");
			return false;
		}
		
		else if(highRate_add == ""){
			errorMsg.html("高区间中奖概率未录入");
			return false;
		}else if(parseInt(highRate_add) > 1 ){
			errorMsg.html("高区间中奖概率不能大于1");
			return false;
		}
		
		else if(highBegin_add == ""){
			errorMsg.html("高区间起始值未录入");
			return false;
		}else if(!checkNumber(highBegin_add)){
			errorMsg.html("高区间起始值格式录入有误");
			return false;
		}
		
		else if(highEnd_add == ""){
			errorMsg.html("高区间结束值未录入");
			return false;
		}else if(!checkNumber(highEnd_add)){
			errorMsg.html("高区间结束值格式录入有误");
			return false;
		}
		
		else if(parseInt(highBegin_add) >= parseInt(highEnd_add)){
			errorMsg.html("高区间起始值不能大于或等于结束值");
			return false;
		}
		
		else if(highAwardTotal_add == ""){
			errorMsg.html("高区间奖品总数未录入");
			return false;
		}else if(!checkNumber(highAwardTotal_add)){
			errorMsg.html("高区间奖品总数格式录入有误");
			return false;
		}
		//-----------------中区间-----
		else if(midAwardId_add == ""){
			errorMsg.html("中区间奖品id未录入");
			return false;
		}else if(!checkNumber(midAwardId_add)){
			errorMsg.html("中区间奖品id格式录入有误");
			return false;
		}
		
		else if(midRate_add == ""){
			errorMsg.html("中区间中奖概率未录入");
			return false;
		}else if(parseInt(midRate_add) >1 ){
			errorMsg.html("中区间中奖概率不能大于1");
			return false;
		}
		
		else if(midBegin_add == ""){
			errorMsg.html("中区间起始值未录入");
			return false;
		}else if(!checkNumber(midBegin_add)){
			errorMsg.html("中区间起始值格式录入有误");
			return false;
		}
		else if(midEnd_add == ""){
			errorMsg.html("中区间结束值未录入");
			return false;
		}else if(!checkNumber(midEnd_add)){
			errorMsg.html("中区间结束值格式录入有误");
			return false;
		}
		
		else if(parseInt(midBegin_add) >= parseInt(midEnd_add)){
			errorMsg.html("中区间起始值不能大于或等于结束值");
			return false;
		}
		
		else if(midAwardTotal_add == ""){
			errorMsg.html("中区间奖品总数未录入");
			return false;
		}else if(!checkNumber(midAwardTotal_add)){
			errorMsg.html("中区间奖品总数格式录入有误");
			return false;
		}
		
		//----------------地区间--
		else if(lowAwardId_add == ""){
			errorMsg.html("低区间奖品id未录入");
			return false;
		}else if(!checkNumber(lowAwardId_add)){
			errorMsg.html("低区间奖品id格式录入有误");
			return false;
		}
		
		else if(lowRate_add == ""){
			errorMsg.html("低区间中奖概率未录入");
			return false;
		}else if(parseInt(lowRate_add) >1 ){
			errorMsg.html("低区间中奖概率不能大于1");
			return false;
		}
		
		else if(lowBegin_add == ""){
			errorMsg.html("低区间起始值未录入");
			return false;
		}else if(!checkNumber(lowBegin_add)){
			errorMsg.html("低区间起始值格式录入有误");
			return false;
		}
		else if(lowEnd_add == ""){
			errorMsg.html("低区间结束值未录入");
			return false;
		}else if(!checkNumber(lowEnd_add)){
			errorMsg.html("低区间结束值格式录入有误");
			return false;
		}
		else if(parseInt(lowBegin_add) >= parseInt(lowEnd_add)){
			errorMsg.html("低区间起始值不能大于或等于结束值");
			return false;
		}
		
		else if(lowAwardTotal_add == ""){
			errorMsg.html("低区间奖品总数未录入");
			return false;
		}else if(!checkNumber(lowAwardTotal_add)){
			errorMsg.html("低区间奖品总数格式录入有误");
			return false;
		}
		
		else if(parseInt(lowEnd_add) > parseInt(midBegin_add)){
			errorMsg.html("低区间结束值不能大于中区间起始值");
			return false;
		}
		else if(parseInt(midEnd_add) > parseInt(highBegin_add)){
			errorMsg.html("中区间结束值不能大于高区间起始值");
			return false;
		}
		
		$.ajax({
			url : "${ctx}/platform/lotteryPreferPageAdd.do",
			type : "post",
			dataType : "json",
			data : {
				ruleName_add : ruleName_add,
				
				histAwardId_add : histAwardId_add,
				highRate_add : highRate_add,
				highBegin_add : highBegin_add,
				highEnd_add : highEnd_add,
				highAwardTotal_add : highAwardTotal_add,
				
				midAwardId_add : midAwardId_add,
				midRate_add : midRate_add,
				midBegin_add : midBegin_add,
				midEnd_add : midEnd_add,
				midAwardTotal_add : midAwardTotal_add,
				
				lowAwardId_add : lowAwardId_add,
				lowRate_add : lowRate_add,
				lowBegin_add : lowBegin_add,
				lowEnd_add : lowEnd_add,
				lowAwardTotal_add : lowAwardTotal_add, 
				comment_add : comment_add
			},
			success : function(data) {
				if(!data.success && data.errorCode == '001'){
					errorMsg.html(data.error);
					return false;
				}
				$('#lotteryPreferAdd_Modal').modal('hide');
				showMessageDialog(data.msg,function(){
					if(data.success){
						window.location.href = "${ctx}/platform/lotteryPreferPageList.do";
					}
				});
			},
			error : function(){
				showMessageDialog("网络异常");
			}
			
		});
		
	}
	
	// 编辑保存
	function lotteryPreferEdit_Save(){
		var errorMsg = $('#errorMsg_Edit');
		
		var evaluateId_edit = $('#evaluateId_edit').val();
		var ruleName_add = $('#ruleName_edit').val();
		
		var histAwardId_add = $('#histAwardId_edit').val();
		var highRate_add = $('#highRate_edit').val();
		var highBegin_add =$('#highBegin_edit').val();
		var highEnd_add = $('#highEnd_edit').val();
		var highAwardTotal_edit = $('#highAwardTotal_edit').val();
		
		var midAwardId_add = $('#midAwardId_edit').val();
		var midRate_add = $('#midRate_edit').val();
		var midBegin_add = $('#midBegin_edit').val();
		var midEnd_add = $('#midEnd_edit').val();
		var midAwardTotal_edit = $('#midAwardTotal_edit').val();
		
		var lowAwardId_add = $('#lowAwardId_edit').val();
		var lowRate_add = $('#lowRate_edit').val();
		var lowBegin_add = $('#lowBegin_edit').val();
		var lowEnd_add = $('#lowEnd_edit').val();
		var lowAwardTotal_edit = $('#lowAwardTotal_edit').val();
		
		var comment_edit = $('#comment_edit').val();
		
		if(ruleName_add == ""){
			errorMsg.html("请录入规则名称");
			return false;
		}
		
		else if(histAwardId_add == ""){
			errorMsg.html("高区间奖品id未录入");
			return false;
		}else if(!checkNumber(histAwardId_add)){
			errorMsg.html("高区间奖品id格式录入有误");
			return false;
		}
		
		else if(highRate_add == ""){
			errorMsg.html("高区间中奖概率未录入");
			return false;
		}else if(parseInt(highRate_add) > 1 ){
			errorMsg.html("高区间中奖概率不能大于1");
			return false;
		}
		
		else if(highBegin_add == ""){
			errorMsg.html("高区间起始值未录入");
			return false;
		}else if(!checkNumber(highBegin_add)){
			errorMsg.html("高区间起始值格式录入有误");
			return false;
		}
		
		else if(highEnd_add == ""){
			errorMsg.html("高区间结束值未录入");
			return false;
		}else if(!checkNumber(highEnd_add)){
			errorMsg.html("高区间结束值格式录入有误");
			return false;
		}
		
		else if(parseInt(highBegin_add) >= parseInt(highEnd_add)){
			errorMsg.html("高区间起始值不能大于或等于结束值");
			return false;
		}
		else if(highAwardTotal_edit == ""){
			errorMsg.html("高区间奖品总数未录入");
			return false;
		}else if(!checkNumber(highAwardTotal_edit)){
			errorMsg.html("高区间奖品总数格式录入有误");
			return false;
		}
		
		//-----------------中区间-----
		else if(midAwardId_add == ""){
			errorMsg.html("中区间奖品id未录入");
			return false;
		}else if(!checkNumber(midAwardId_add)){
			errorMsg.html("中区间奖品id格式录入有误");
			return false;
		}
		
		else if(midRate_add == ""){
			errorMsg.html("中区间中奖概率未录入");
			return false;
		}else if(parseInt(midRate_add) >1 ){
			errorMsg.html("中区间中奖概率不能大于1");
			return false;
		}
		
		else if(midBegin_add == ""){
			errorMsg.html("中区间起始值未录入");
			return false;
		}else if(!checkNumber(midBegin_add)){
			errorMsg.html("中区间起始值格式录入有误");
			return false;
		}
		else if(midEnd_add == ""){
			errorMsg.html("中区间结束值未录入");
			return false;
		}else if(!checkNumber(midEnd_add)){
			errorMsg.html("中区间结束值格式录入有误");
			return false;
		}
		
		else if(parseInt(midBegin_add) >= parseInt(midEnd_add)){
			errorMsg.html("中区间起始值不能大于或等于结束值");
			return false;
		}
		
		else if(midAwardTotal_edit == ""){
			errorMsg.html("中区间奖品总数未录入");
			return false;
		}else if(!checkNumber(midAwardTotal_edit)){
			errorMsg.html("中区间奖品总数格式录入有误");
			return false;
		}
		//----------------地区间--
		else if(lowAwardId_add == ""){
			errorMsg.html("低区间奖品id未录入");
			return false;
		}else if(!checkNumber(lowAwardId_add)){
			errorMsg.html("低区间奖品id格式录入有误");
			return false;
		}
		
		else if(lowRate_add == ""){
			errorMsg.html("低区间中奖概率未录入");
			return false;
		}else if(parseInt(lowRate_add) >1 ){
			errorMsg.html("低区间中奖概率不能大于1");
			return false;
		}
		
		else if(lowBegin_add == ""){
			errorMsg.html("低区间起始值未录入");
			return false;
		}else if(!checkNumber(lowBegin_add)){
			errorMsg.html("低区间起始值格式录入有误");
			return false;
		}
		else if(lowEnd_add == ""){
			errorMsg.html("低区间结束值未录入");
			return false;
		}else if(!checkNumber(lowEnd_add)){
			errorMsg.html("低区间结束值格式录入有误");
			return false;
		}
		else if(parseInt(lowBegin_add) >= parseInt(lowEnd_add)){
			errorMsg.html("低区间起始值不能大于或等于结束值");
			return false;
		}
		
		else if(lowAwardTotal_edit == ""){
			errorMsg.html("低区间奖品总数未录入");
			return false;
		}else if(!checkNumber(lowAwardTotal_edit)){
			errorMsg.html("低区间奖品总数格式录入有误");
			return false;
		}
		
		else if(parseInt(lowEnd_add) > parseInt(midBegin_add)){
			errorMsg.html("低区间结束值不能大于中区间起始值");
			return false;
		}
		else if(parseInt(midEnd_add) > parseInt(highBegin_add)){
			errorMsg.html("中区间结束值不能大于高区间起始值");
			return false;
		}
		
		$.ajax({
			url : "${ctx}/platform/lotteryPreferEditSave.do",
			type : "post",
			dataType : "json",
			data : {
				evaluateId_edit: evaluateId_edit,
				ruleName_add : ruleName_add,
				
				histAwardId_add : histAwardId_add,
				highRate_add : highRate_add,
				highBegin_add : highBegin_add,
				highEnd_add : highEnd_add,
				highAwardTotal_edit : highAwardTotal_edit,
				
				midAwardId_add : midAwardId_add,
				midRate_add : midRate_add,
				midBegin_add : midBegin_add,
				midEnd_add : midEnd_add,
				midAwardTotal_edit : midAwardTotal_edit,
				
				lowAwardId_add : lowAwardId_add,
				lowRate_add : lowRate_add,
				lowBegin_add : lowBegin_add,
				lowEnd_add : lowEnd_add,
				lowAwardTotal_edit : lowAwardTotal_edit,
				comment_edit : comment_edit
			},
			success : function(data) {
				if(!data.success && data.errorCode == '001'){
					errorMsg.html(data.error);
					return false;
				}
				
				$('#lotteryPreferEdit_Modal').modal('hide');
				showMessageDialog(data.msg,function(){
					if(data.success){
						window.location.href = "${ctx}/platform/lotteryPreferPageList.do";
					}
				});
			},
			error : function(){
				showMessageDialog("网络异常");
			}
			
		});
		
	}
  
  //分页查询
  function searchLottery(){
	  $('#pageListForm').submit();
  }
  
  //删除奖品
  function deleteLottery(){
	  var checkLottery = document.getElementsByName("checkLottery");
	  var count = 0 ;
	  var lotteryId = "";
	  for(var i=0;i<checkLottery.length;i++){
		  if(checkLottery[i].checked){
			  count++;
			  lotteryId = checkLottery[i].value+"!"+lotteryId;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择要删除的抽奖组合");
		  return;
	  }
	  showConfirmDialog("确认删除抽奖组合？",function(check){
		  if(check){
			  $.ajax({
				  url:"${ctx}/platform/deleteLottery.do?lotteryId="+lotteryId,
			  	  type:"post",
				  dataType:"json",
				  success:function(data){
					  showMessageDialog(data.msg,function(){
					  if(data.success){
						 
						  window.location.href = "${ctx}/platform/lotteryList.do";
					  }
					  });
				  }
				  });
		  }
	  });
	  
  }
  
  //跳到编辑倾向抽奖页面
  function toLotteryEditPage(evaluateId){
	  $.ajax({
		  url:"${ctx}/platform/fineLotteryPreferDictById.do?evaluateId="+evaluateId,
	  	  type:"post",
		  dataType:"json",
		  success:function(data){
			  $('#lotteryPreferEdit_Modal').modal('show');
			  document.getElementById("evaluateId_edit").value=data.niLotteryPreferDict.evaluateId;
			  document.getElementById("ruleName_edit").value=data.niLotteryPreferDict.ruleName;
			  
			  document.getElementById("histAwardId_edit").value=data.niLotteryPreferDict.highAwardId;
			  document.getElementById("highRate_edit").value=data.niLotteryPreferDict.highRate;
			  document.getElementById("highBegin_edit").value=data.niLotteryPreferDict.highBegin;
			  document.getElementById("highEnd_edit").value=data.niLotteryPreferDict.highEnd;
			  document.getElementById("highAwardTotal_edit").value=data.niLotteryPreferDict.highAwardTotal;
			  
			  
			  document.getElementById("midAwardId_edit").value=data.niLotteryPreferDict.midAwardId;
			  document.getElementById("midRate_edit").value=data.niLotteryPreferDict.midRate;
			  document.getElementById("midBegin_edit").value=data.niLotteryPreferDict.midBegin;
			  document.getElementById("midEnd_edit").value=data.niLotteryPreferDict.midEnd;
			  document.getElementById("midAwardTotal_edit").value=data.niLotteryPreferDict.midAwardTotal;
			  
			  document.getElementById("lowAwardId_edit").value=data.niLotteryPreferDict.lowAwardId;
			  document.getElementById("lowRate_edit").value=data.niLotteryPreferDict.lowRate;
			  document.getElementById("lowBegin_edit").value=data.niLotteryPreferDict.lowBegin;
			  document.getElementById("lowEnd_edit").value=data.niLotteryPreferDict.lowEnd;
			  document.getElementById("lowAwardTotal_edit").value=data.niLotteryPreferDict.lowAwardTotal;
			  
			  document.getElementById("comment_edit").value=data.niLotteryPreferDict.comment;
		  },
		  error:function(data){
			  showMessageDialog("请求失败");
		  }
		  });
  }
//搜索一等奖奖品信息
  function searchAward1(){
	var awardId = document.getElementById("award1Id").value;
	  $.ajax({
		  url:'${ctx}/platform/searchAward.do',
		  type:'post',
		  dataType:'json',
		  data:{			 
			  awardId : awardId	 
		  },
		  success:function(data){	
			 console.log(123)
				document.getElementById("award1Id").value=data.awardId;
				document.getElementById("award1Name").innerHTML=data.awardName;			  
		  },
		  error: function(data) {
			  document.getElementById("award1Name").innerHTML="未查询到奖品信息"; 
		  }
	  });

}  

//搜索二等奖奖品信息
  function searchAward2(){
	var awardId = document.getElementById("award2Id").value;
	  $.ajax({
		  url:'${ctx}/platform/searchAward.do',
		  type:'post',
		  dataType:'json',
		  data:{			 
			  awardId : awardId	 
		  },
		  success:function(data){		
			  document.getElementById("award2Id").value=data.awardId;
			  document.getElementById("award2Name").innerHTML=data.awardName;
		  },
		  error: function(data) {
			  document.getElementById("award2Name").innerHTML="未查询到奖品信息"; 
		  }		  
	  });

} 

//搜索三等奖奖品信息
  function searchAward3(){
	var awardId = document.getElementById("award3Id").value;
	  $.ajax({
		  url:'${ctx}/platform/searchAward.do',
		  type:'post',
		  dataType:'json',
		  data:{			 
			  awardId : awardId	 
		  },
		  success:function(data){		
			  document.getElementById("award3Id").value=data.awardId;
			  document.getElementById("award3Name").innerHTML=data.awardName;
		  },
		  error: function(data){
			  document.getElementById("award3Name").innerHTML="未查询到奖品信息"; 
		  }
	  });

}

//保存编辑信息
  function updatelottery(){
 	  var lotteryName = $('#lotteryName').val();   //奖品名称
 	  var lotteryId = $('#lotteryId').val(); //抽奖组合ID
 	  var award1Id = $('#award1Id').val();   //一等奖ID	  	 
 	  var award1Rate = $('#award1Rate').val(); //一等奖名称	  
 	  var award2Id = $('#award2Id').val();   //二等奖ID	 
 	  var award2Rate = $('#award2Rate').val(); //二等奖名称
 	  var award3Id = $('#award3Id').val();   //三等奖ID	  
 	  var award3Rate = $('#award3Rate').val(); //三等奖名称 
 	  var comment = $('#comment').val(); //
 	 
 	  if(lotteryName==''){
 		 $('#editLottery').modal('hide');  
 		  showMessageDialog("请输入抽奖组合名称");
			  return;
		  }
	if(award1Id=='' | award1Rate==''){
		$('#editLottery').modal('hide');	  
		showMessageDialog("请设置一等奖的ID或数量");
			  return;
		 }

 	  $.ajax({
 		  url:'${ctx}/platform/updateLottery.do',
 		  type:'post',
 		  dataType:'json',
 		  data:{			 
  			  lotteryName : lotteryName,
  			  lotteryId:lotteryId,
 			  award1Id : award1Id,
 			  award1Rate : award1Rate,
  			  award2Rate:award2Rate,
  			  award2Id :award2Id,
 			  award3Id : award3Id,
 			  award3Rate : award3Rate, 
 			  comment : comment			 
 		  },
 		
 		
 		  
 		  
		  success:function(data){
			  
			  if(data.success){	
				  $('#editLottery').modal('hide');
				  showMessageDialog(data.msg,function(){
						 if(data.success){
							 window.location.href = "${ctx}/platform/lotteryList.do";
						  }
					  })
				 				  
			  }
		  },
 		  error:function(data){
 			 $('#editLottery').modal('hide');
 			 showMessageDialog("保存失败");
 		  }
 		  
 	  });
 	  
 }
  </script>
</body>

</html>