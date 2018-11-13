<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>抽奖管理</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
</head>

<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    
    <!-- 内容部分 -->
   	   <table style="padding: 100px;margin: 10px;">
          	<tr>
          		<td style="width: 100px;padding: 3px;"><button type="button" style="background-color: #BC8F8F" onclick="uestionnairePage();" class="btn btn-block btn-primary">随机抽奖</button></td>
          		<td style="width: 100px;padding: 3px;"><button type="button" onclick="lotteryPreferPage();" class="btn btn-block btn-primary">倾向抽奖</button></td>
          	</tr>
         </table>

      <!-- Main content -->
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
              <a href="${ctx}/platform/lotteryAdd.do">
                <button type="button" class="btn btn-default btn-sm">添加</button>
              </a>
              <a href="#">
                <button type="button" onclick="deleteLottery()" class="btn btn-default btn-sm">删除</button>
              </a>
            </div>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">抽奖组合列表</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th></th>
                  <th>编号</th>
                  <th>抽奖ID</th>
                  <th>抽奖组合名称</th>
                  <th>一等奖</th>
                  <th>二等奖</th>
                  <th>三等奖</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                   <c:forEach items="${lotteryList}" var="lotteryItem" varStatus="n">
                      <tr>
                        <td><input type="checkbox" id = "checkLottery" name="checkLottery" value="${lotteryItem.lotteryId }"></td>
                        <td>${n.index+1}</td>
                        <td>${lotteryItem.lotteryId}</td>
                         <td>${lotteryItem.lotteryName}</td>
                        <td>${lotteryItem.award1Rate}</td>
                        <td>${lotteryItem.award2Rate}</td>
                        <td>${lotteryItem.award3Rate}</td>
                       <td>
							<button type="button" onclick="toLotteryEditPage(${lotteryItem.lotteryId})"
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
    
    <!-- 内容部分结束 -->
     <!-- 编辑抽奖组合模态框 -->
    <div class="modal fade" id="editLottery" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	 <div class="modal-dialog">
    	 	<div class="modal-content">
    	 		 <div class="modal-header">
            		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
           			<h4 class="modal-title" id="myModalLabel">编辑抽奖组合</h4>
           		</div><!-- /.modal-modal-header -->
           		<div class="modal-body">
           		<form class="form-horizontal" role="form">
           		<div class="form-group">
  					<label class="control-label  col-sm-2" >抽奖ID</label>
  					<div class=" col-sm-3">
  					<input type="text" class="form-control" id="lotteryId" disabled="disabled">
					</div>
 					<label class="control-label col-sm-2" >抽奖名称</label>
  					<div class="col-sm-5">
  					<input type="text" class="form-control " id="lotteryName">
					</div>
				</div>
				  <div class="box-footer"></div>        
				<!-- 一等奖 -->
				<div class="form-group has-success">
					<label class="control-label col-sm-2">一等奖</label>				
				</div>
				<div class="form-group">
					<label class="control-label  col-sm-2" >奖品ID</label>
  					<div class=" col-sm-3">
  					<input type="text" class="form-control" id="award1Id" onblur="searchAward1()">
					</div>
 					<label class="control-label col-sm-2" >设置数值</label>
  					<div class="col-sm-5">
  					<input type="text" class="form-control " id="award1Rate">   						 					
					</div>				
					<font class="col-sm-offset-2 col-sm-3"id="award1Name"></font>
					<font  class="col-sm-7" color="red" align="right"><small>*大于等于1表示中奖数目，小于1是概率，如0.05，下同。</small></font>	
				</div>
				  <div class="box-footer"></div>        
			    <!-- 二等奖 -->
     		    <div class="form-group has-success">
					<label class="control-label col-sm-2">二等奖</label>				
				</div>
				<div class="form-group">
					<label class="control-label  col-sm-2" >奖品ID</label>
  					<div class=" col-sm-3">
  					<input type="text" class="form-control" id="award2Id" onblur="searchAward2()">
					</div>
 					<label class="control-label col-sm-2" >设置数值</label>
  					<div class="col-sm-5">
  					<input type="text" class="form-control " id="award2Rate">   						 					
					</div>
					<font class="col-sm-offset-2 col-sm-3"id="award2Name"></font>								
				</div>
   		      <div class="box-footer"></div>        
         		
         	  <!-- 三等奖  -->
         		<div class="form-group has-success">
					<label class="control-label col-sm-2">三等奖</label>				
				</div>
				<div class="form-group">
					<label class="control-label  col-sm-2" >奖品ID</label>
  					<div class=" col-sm-3">
  					<input type="text" class="form-control" id="award3Id" onblur="searchAward3()">
					</div>
 					<label class="control-label col-sm-2" >设置数值</label>
  					<div class="col-sm-5">
  					<input type="text" class="form-control " id="award3Rate">   						 					
					</div>
					<font class="col-sm-offset-2 col-sm-3"id="award3Name"></font>								
				</div>
			<div class="box-footer"></div>    
				<div class="form-group">
         		<label class="control-label col-sm-2">抽奖说明</label>	
         		<textarea class="col-sm-9" id="comment"  rows="3"  placeholder="Enter ..."></textarea>
         </div>      
         </form>          			
           		</div><!-- /.modal-body -->
           		<div class="modal-footer">
            		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            		<button type="button" onclick="updatelottery();" class="btn btn-primary">保存</button>
         		</div><!-- /.modal-footer -->
    	 	</div><!-- /.modal-content -->
    	 </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

  <div class="control-sidebar-bg"></div>
  </div>
  <script src="${ctx}/js/validate.js"></script>
  <script>
  
  //倾向抽奖
  function lotteryPreferPage(){
	  window.location.href = "${ctx}/platform/lotteryPreferPageList.do";
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
					  })
				  }
				  });
		  }
	  })
	  
  }
  
  //跳到编辑抽奖组合页面
  function toLotteryEditPage(lotteryId){
	  $.ajax({
		  url:"${ctx}/platform/toLotteryEditPage.do",
	  	  type:"post",
		  dataType:"json",
		  data:{
			  lotteryId : lotteryId		
		  }, 
		  success:function(data){
			  $('#editLottery').modal('show');
			  document.getElementById("lotteryName").value=data.lotteryName;
			  document.getElementById("lotteryId").value=data.lotteryId;
			  document.getElementById("award1Id").value=data.award1Id;
			  document.getElementById("award1Rate").value=data.award1Rate;
			  document.getElementById("award2Id").value=data.award2Id;
			  document.getElementById("award2Rate").value=data.award2Rate;
			  document.getElementById("award3Id").value=data.award3Id;
			  document.getElementById("award3Rate").value=data.award3Rate;
			  document.getElementById("comment").value=data.comment;
			  

			  
		  },
		  error:function(data){
			  showMessageDialog("请求失败");
		  }
		  })
	  // window.location.href = "${ctx}/platform/toLotteryEditPage.do?lotteryId="+ lotteryId;
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