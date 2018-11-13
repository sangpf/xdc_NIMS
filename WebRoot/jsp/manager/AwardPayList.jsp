<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>发放管理</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  
</head>

<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    
    <!-- 内容部分 -->
      <!-- Main content -->
      <section class="content">
        <div class="panel panel-default">
          <div class="panel-body">
            <form class="form-horizontal"  method="post" action="${ctx}/platform/awardPayList.do" id ="pageListForm">              
              
              <div class="row mb10">
                <div class="col-sm-2">
                  <select class="form-control select-sm" id="searchOption" name = "searchOption">
                    <option value="1" selected>问卷名称</option>
                    <option value="2">奖品名称</option>
                  </select>
                </div>
                <div class="input-group input-group-sm col-sm-3">
                  <input id="searchContent" type="text" class="form-control" name = "searchContent" value="${searchContent}">
                  <span class="input-group-btn">
                      <button type="button" onclick="searchAwardPay()" class="btn btn-info btn-flat">查询</button>
                   </span>
                </div>
              </div> 
            
              <div class="row mb10">
                <div class="col-sm-3">
                  <label class="select-label">领取状态</label>
                  <select class="form-control select-sm" id="awardGetStatus" style="width:50%;" name = "awardGetStatus">
                    <option value="">不限</option>
                    <option value="1">未领取</option>
                    <option value="2">已领取</option>
                    <option value="3">放弃</option>
                  </select>
                </div>
              
               <div class="col-sm-3">
                  <label class="select-label">发放状态</label>
                  <select class="form-control select-sm" id="awardPayStatus" style="width: 50%;" name="awardPayStatus">
                  	<option value="">不限</option>
                  	<option value="1">未发放</option>
                  	<option value="2">已发放</option>                 	
                  </select>
                </div>
              <div class="col-sm-3">
                  <label class="select-label">奖品类型</label>
                  <select class="form-control select-sm" id="awardType" style="width:50%;" name = "awardType">
                    <!-- <option value="">不限</option> -->
                  	<option value="1">实物</option>
                  	<option value="2">外链</option>
                  	<option value="3">玩校积分</option>
					<option value="4">静态优惠码</option>
                  </select>
               </div>
              </div>                     
           </form>
          </div>

        </div>
        
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="button-group">
             
                <button type="button" class="btn btn-default btn-sm"  onclick="exportAwardPaySheet1();" style="width: 12%;">导出实物奖励信息</button>      
                <button type="button" class="btn btn-default btn-sm"  onclick="exportAwardPaySheet2();" style="width: 12%;">导出外链奖励信息</button>        
                
            </div>
          </div>
        </div>

        <div class="panel panel-default">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">奖品发放列表</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>编号</th>
                  <th>用户ID</th>
                  <th>问卷名称</th>
                  <th>奖品名称</th>
                  <th>奖品ID</th>
                  <th>获奖时间</th>
                  <th>领取状态</th>
                  <th>发放状态</th>
                  <th>发放时间</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                   <c:forEach items="${awardPayList}" var="awardPayItem" varStatus="n">
                      <tr>
                        <td>${n.index+1}</td>                                               
                        <td>${awardPayItem.userId}</td>
                        <td>${awardPayItem.qnName}</td>
                        <td>${awardPayItem.awardName}</td>
                        <td>${awardPayItem.awardId}</td>
                        <td>${awardPayItem.awardGetTime}</td>  
                        <td>
                        	<c:if test="${awardPayItem.awardGetStatus == 1}">未领取</c:if>
                        	<c:if test="${awardPayItem.awardGetStatus == 2}">已领取</c:if>
                        	<c:if test="${awardPayItem.awardGetStatus == 3}">放弃</c:if>
                        	</td>
                        <td>
                        	<c:if test="${awardPayItem.awardPayStatus == 1}">未发放</c:if>
                        	<c:if test="${awardPayItem.awardPayStatus == 2}">已发放</c:if>
                       </td>
                        
                        <td>${awardPayItem.awardPayTime}</td>                        
                       <td>
                       <button type="button" onclick="payAward(${awardPayItem.orderId},${awardPayItem.qnType},${awardPayItem.awardGetStatus},${awardPayItem.awardPayStatus})"
							class="btn btn-default btn-sm">发放</button>
					  <button type="button" onclick="getAwardPostalInfo(${awardPayItem.userId},${awardPayItem.awardId})"
							class="btn btn-default btn-sm">查看</button>
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
     <!-- 查看邮寄信息模态框 -->
    <div class="modal fade" id="postalInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	 <div class="modal-dialog">
    	 	<div class="modal-content">
    	 		 <div class="modal-header">
            		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
           			<h4 class="modal-title" id="myModalLabel" style="color: #3c8dbc">用户邮寄信息</h4>
           		</div><!-- /.modal-modal-header -->
           		<div class="modal-body">
           		
           		<div class="row">
           			<div class="form-group has-success col-sm-3" id="awardName-title" style="width:120px; padding-right:0px;">
  					<label class="control-label" ><span id="awardName-pic" class="glyphicon glyphicon-bell" aria-hidden="true"></span>    &nbsp;&nbsp;奖品名称:</label> 						
					</div>
					<div class="col-sm-9"  padding-right:0px;> 	
					<label id="awardName" ></label>				
					</div>					
           		</div>
           		
           		<div class="row">
           			<div class="form-group has-success col-sm-3" id="awardId-title" style="width:120px; padding-right:0px;">
  					<label class="control-label" ><span id="awardId-pic" class="glyphicon glyphicon-tags" aria-hidden="true"></span>    &nbsp;&nbsp;奖品ID:</label> 						
					</div>
					<div class="col-sm-9"  padding-right:0px;> 	
					<label id="awardId" ></label>				
					</div>					
           		</div>
           		
           		<div class="row">
           			<div class="form-group has-success col-sm-3" id="recipientName-title" style="width:120px; padding-right:0px;">
  					<label class="control-label" ><span id="recipientName-pic" class="glyphicon glyphicon-user" aria-hidden="true"></span>    &nbsp;&nbsp;收件人:</label> 						
					</div>
					<div class="col-sm-9"  padding-right:0px;> 	
					<label id="recipientName" ></label>				
					</div>					
           		</div>
           		
           		<div class="row">
           			<div class="form-group has-success col-sm-3" id="recipientPhone-title" style="width:120px; padding-right:0px;">
  					<label class="control-label" ><span id="recipientPhone-pic" class="glyphicon glyphicon-phone" aria-hidden="true"></span>    &nbsp;&nbsp;联系电话:</label> 						
					</div>
					<div class="col-sm-9"  padding-right:0px;> 	
					<label id="recipientPhone" ></label>				
					</div>					
           		</div>
 		
           		<div class="row">
           			<div class="form-group has-success col-sm-3" id="mailAddress-title" style="width:120px; padding-right:0px;">
  					<label class="control-label" ><span id="mailAddress-pic" class="glyphicon glyphicon-tree-conifer" aria-hidden="true"></span>    &nbsp;&nbsp;邮寄地址:</label> 						
					</div>
					<div class="col-sm-9"  padding-right:0px;> 	
					<label id="mailAddress" ></label>				
					</div>					
           		</div>
           		
           		<div class="row">
           			<div class="form-group has-success col-sm-3" id="postCode-title" style="width:120px; padding-right:0px;">
  					<label class="control-label" ><span id="postCode-pic" class="glyphicon glyphicon-envelope" aria-hidden="true"></span>    &nbsp;&nbsp;邮箱:</label> 						
					</div>
					<div class="col-sm-9"  padding-right:0px;> 	
					<label id="postCode" ></label>				
					</div>					
           		</div>   
           		
           		<div class="row">
           			<div class="form-group has-success col-sm-3" id="province-title" style="width:120px; padding-right:0px;">
  						<label class="control-label" >
  							<span id="province-pic" class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;支付宝:</label> 						
					</div>
					<div class="col-sm-9" padding-right:0px;> 	
						<label id="province" ></label>				
					</div>					
           		</div>
           		
           		<!-- <div class="row">
           			<div class="form-group has-success col-sm-3" id="postCode-title" style="width:120px; padding-right:0px;">
  					<label class="control-label" ><span id="postCode-pic" class="glyphicon glyphicon-envelope" aria-hidden="true"></span>    &nbsp;&nbsp;邮箱:</label> 						
					</div>
					<div class="col-sm-9"  padding-right:0px;> 	
					<label id="email" ></label>				
					</div>					
           		</div>   -->
           		       			
           		</div><!-- /.modal-body -->
           		<div class="modal-footer">
            		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
         		</div><!-- /.modal-footer -->
    	 	</div><!-- /.modal-content -->
    	 </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

  <div class="control-sidebar-bg"></div>
  <!-- ./wrapper -->
 <script src="${ctx}/js/validate.js"></script>
  <script>
  
  //分页查询
  function searchAwardPay(){
	  
/* 	  var searchContent_str = $('#searchContent').val();
	  
	  if(searchContent_str == ""){
		  showMessageDialog("查询请输入问卷名称或奖品名称");
		  return false;
	  } */
	  
	  $('#pageListForm').submit();
  } 
 
  //发放奖品
  function payAward(orderId,qnType,awardGetStatus,awardPayStatus){
	  if(awardGetStatus !=2 ){
		  showMessageDialog("用户未领取奖品或放弃领取");
		  return;
	  }	
	  if(awardPayStatus == 2){
		  showMessageDialog("奖品已发放");
		  return;
	  }		
	  $.ajax({
			type : "post",
			dataType : "json",
			url : "${ctx}/platform/payAward.do",
			data : {
				orderId : orderId,
				qnType : qnType
			},
			success : function(data) {
				showMessageDialog(data.msg,function(){
					if (data.success) {
						window.location.href = "${ctx}/platform/awardPayList.do";
					}
				})
				
			}
		});
	  
  }
  
  //查看发放信息
  function getAwardPostalInfo(userId,awardId){
	  $.ajax({
		  url:"${ctx}/platform/getAwardPostalInfo.do",
	  	  type:"post",
		  dataType:"json",
		  data:{
			  userId : userId,
			  awardId: awardId
		  }, 
		  success:function(data){
			  $('#postalInfo').modal('show');
			  document.getElementById("awardName").innerHTML=data.awardName;
			  document.getElementById("awardId").innerHTML=data.awardId;
			  document.getElementById("recipientName").innerHTML=data.recipientName;
			  document.getElementById("recipientPhone").innerHTML=data.recipientPhone;
			  document.getElementById("province").innerHTML=data.province;
			  document.getElementById("mailAddress").innerHTML=data.mailAddress;
			  document.getElementById("postCode").innerHTML=data.postCode; 
			  //document.getElementById("email").innerHTML=data.email;
		  },
		  error:function(data){
			  showMessageDialog("未查询到邮寄信息");
		  }
		  })
	//  window.location.href = "${ctx}/platform/getAwardPostalInfo.do?userId="+ userId+"&awardId="+awardId;
	}

	//导出实物奖励的邮寄信息
	function exportAwardPaySheet1(){
		var searchContent_par = $('#searchContent').val();
		//alert(searchContent_par);
		window.location.href="${ctx}/platform/exportAwardPaySheet.do?awardType=1&searchContent="+searchContent_par;
		
/*  		 $.ajax({
			 url : "${ctx}/platform/exportAwardPaySheet.do",
			 data:{
				 awardType : 1,
				 searchContent : searchContent_par
			 },
			 type:'post',
			 dataType:'json',
			 success:function(data){
				 //alert("hello");
				 showMessageDialog(data.msg);
			 }
			 
		 });  */
	}
	//导出外链奖励的邮寄信息
	function exportAwardPaySheet2(){
		 
		 window.location.href="${ctx}/platform/exportAwardPaySheet.do?awardType=2";
	}
  
  </script>
 
  
</body>

</html>