<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>奖池管理</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="${ctx}/BS/plugins/datepicker/datepicker3.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">
    <!-- 内容部分 -->
      <!-- Main content -->
      <section class="content" >
        <div class="panel panel-default">
          <div class="panel-body">
            <form class="form-horizontal"  method="post" action="${ctx}/platform/searchawardPool.do" id ="pageListForm">
           
              <div class="row mb10">
                <div class="col-sm-2">
                  <select class="form-control select-sm" id="selectdetail" name = "selectdetail">
                    <option value="">详细搜索</option>
                    <option value="awardPoolName">货品名称</option>
                    <option value="provider">提供者</option>
                    <option value="awardPoolId">货品编码</option>
                  </select>
                </div>
                <div class="input-group input-group-sm col-sm-3">
                  <input id="searchContent" type="text" class="form-control" name = "searchContent">
                  <span class="input-group-btn">
                      <button type="button" onclick="searchawardpool();" class="btn btn-info btn-flat">Go!</button>
                   </span>
                </div>
              </div>
                  <div class="row mb10">
                <div class="col-sm-2">
                  <select class="form-control select-sm" name="selectprice" id="selectprice" ">
                    <option value="">请选择价格</option>
                    <option value="0-50">50元以下</option>
                    <option value="50-100">50-100元</option>
                    <option value="100-200">100-200元</option>
                    <option value="200-100000000">200元以上</option>
                  </select>
                </div>
                  <div class="col-sm-3" style="padding:0px">
            
               	 <select class="form-control select-sm" id="selectstatus"  name="selectstatus">
               	 <option value="">请选择状态</option>
                  	<option value="">不限</option>
                  	<option value="1">未上架</option>
                  	<option value="2">上架</option>
                  	<option value="3">失效</option>
                </select>
                </div>
               
              </div>
            
              <div class="row mb10">
              
               <div class="col-sm-2">
              
                  
                  <select class="form-control select-sm" id="selectawardType"  name="selectawardType">
                  	<option value="">请选择类型</option>
                  	<option value="1">实物</option>
                  	<option value="2">外链</option>
                  	<option value="3">玩校积分</option>
					<option value="4">静态优惠码</option>
                  </select>
               
                </div>
                
               <div class="input-group col-sm-3">
                  
                  <input type="text" class="form-control pull-right" name="reservation" id="reservation" style="height:30px;" placeholder="">
                  <div class="input-group-addon ">
                    <i class="fa fa-calendar"></i>
                  </div>
                </div>
            
              </div>              
           </form>
          </div>

        </div>
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="button-group">
              <button type="button" class="btn btn-default btn-sm"  onclick="return awardAddModal();" >添加</button>
                <button type="button" onclick="return awardonwork();" class="btn btn-default btn-sm" >上架</button>
                <button type="button" onclick="return awardnotwork();" class="btn btn-default btn-sm">下架</button>
                <button type="button" onclick="return awardInvalid();" class="btn btn-default btn-sm">失效</button>
                <button type="button" onclick="return awarddelete();" class="btn btn-default btn-sm">删除</button>
              
              
            </div>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="box">

            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th></th>
                  <th>货品编码</th>
                  <th>货品名称</th>
                  <th>提供者</th>
                  <th>类型</th>
                  <th>市场价格</th>
                  <th>库存</th>
                  <th>状态</th>
                  <th>起始时间</th>
                  <th>终止时间</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                   <c:forEach items="${awardpoolList}" var="awardItem" varStatus="n">
                      <tr>
                        <td><input type="checkbox" id = "checkAwardpool" name="checkAwardpool" value="${awardItem.awardPoolId }=${awardItem.status }"></td>
                        <td>${awardItem.awardPoolId}</td>
                        <td >${awardItem.awardPoolName}</td>
                        <td>${awardItem.provider}</td>
                        <td>
                        	<c:if test="${awardItem.awardType == 1}">实物</c:if>
                        	<c:if test="${awardItem.awardType == 2}">外链</c:if>
                        	<c:if test="${awardItem.awardType == 3}">玩校积分</c:if>   
                        	<c:if test="${awardItem.awardType == 4}">静态优惠码</c:if>                    
                        </td>
                       
                        <td>${awardItem.equalMoney}</td>
                         <td>${awardItem.totalNum}</td>
                        <td id="status">
                        	<c:if test="${awardItem.status == 1}">未上架</c:if>
                            <c:if test="${awardItem.status == 2}">上架</c:if>
                            <c:if test="${awardItem.status == 3}">失效</c:if>
                        </td>
<%--                         <td>${awardItem.validBTime}</td>
                        <td>${awardItem.validETime}</td> --%>
                        <td ><fmt:formatDate value="${awardItem.validBTime}" pattern="yyyy-MM-dd"/></td>
                        <td ><fmt:formatDate value="${awardItem.validETime}" pattern="yyyy-MM-dd"/></td>
                         <td>
                        	 <button type="button" onclick="awardPoolEditPage(${awardItem.awardPoolId})"
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
    

  <div class="control-sidebar-bg"></div>
  
<!-- 添加模态框 -->
<div class="modal fade" id="addaward" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
   	  <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel" style="color: #3c8dbc">添加/编辑奖池</h4>
	      </div>
      <div class="modal-body content" >
    
	<div class="row">
		<div class="form-group has-success col-sm-6"" id="awardpoolname-title">
		  <label class="control-label" for="inputSuccess1"><span id="awardpoolname-pic" class="glyphicon glyphicon-bell" aria-hidden="true"></span>   奖品名称</label>
		  <input type="text" class="form-control" id="awardpoolname">
		  <input id="awardPoolId" name="awardPoolId" type="hidden" class="form-control"></td>
		</div>
		<div class="form-group has-success  col-sm-6" id="provider-title">
		  <label class="control-label" for="inputWarning1"><span id="provider-pic" class="glyphicon glyphicon-user" aria-hidden="true"></span>   提供方</label>
		  <input type="text" class="form-control" id="provider">
		</div>
	</div>

	<div class="row">
		<div class="form-group has-success col-sm-6" id="equalMoney-title">
		    <label class="control-label" for="inputError1"><span id="equalMoney-pic" class="glyphicon glyphicon-usd" aria-hidden="true"></span>   价格</label>
			  <div class="input-group">
			
			  <input type="text" class="form-control" id="equalMoney">
			  <span class="input-group-addon">元</span>
			  </div>
		</div>
		<div class="form-group has-success col-sm-6" id="totalNum-title">
		  <label class="control-label" for="inputWarning1"><span id="totalNum-pic" class="glyphicon glyphicon-home" aria-hidden="true"></span>   库存</label>
		  <input type="text" class="form-control" id="totalNum">
		</div>
	</div>
	<div class="row">
		<div class="form-group has-success col-sm-6" id="awardType-title">
	  		<label class="control-label" for="inputError1"><span id="awardType-pic" class="glyphicon glyphicon-wrench" aria-hidden="true"></span>   分类</label>
	    	  <select class="form-control select-sm" id="awardType" name = "searchOption">
	            <option value=""></option>
	            <option value="1">实物</option>
	            <option value="2">外链</option>
	            <option value="3">玩校积分</option>
	            <option value="4">静态优惠码</option>
	            <option value="5">现金红包</option>
	          </select>
		</div>
	
		<div class="form-group has-success col-sm-6" id="url-title">
	  		<label class="control-label" for="inputWarning1"><span id="awardLink-pic" class="glyphicon glyphicon-hand-up" aria-hidden="true"></span>   外链url</label>
	    	<input type="text" class="form-control" id="awardLink">
		</div>
	
	</div>

<div class="row">
<div class="form-group has-success col-sm-6" id="datepicker-title">
  <label class="control-label" for="inputError1"> <span id="datepicker-pic" class="glyphicon glyphicon-time" aria-hidden="true"></span>   起始时间</label>
  <input type="text" class="form-control pull-right" id="datepicker">
</div>

<div class="form-group has-success col-sm-6" id="datepicker1-title">
  <label class="control-label" for="inputWarning1"><span id="datepicker1-pic" class="glyphicon glyphicon-time" aria-hidden="true"></span>  终止时间</label>
  <input type="text" class="form-control pull-right" id="datepicker1">
</div>

</div>
<div class="row">
<div class="form-group has-success col-sm-12" id="showdes-title">
<label><span id="showdes-pic" class="glyphicon glyphicon-pencil" aria-hidden="true"></span>  &nbsp;&nbsp;简介&nbsp;: &nbsp;</label>
<textarea id="showdes" class="form-control"  placeholder="Enter ..."></textarea>
</div>
</div>

</form>
      </div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="return save();">保存</button>
      </div>
    </div>
  </div>
</div>
  
  
  
  
  <!-- ./wrapper -->
  <script src="${ctx}/static/js/moment.min.js"></script>
  <script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
 <script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
  <script>
 $('#datepicker').datepicker({
     autoclose: true,format: 'yyyy-mm-dd'
   });
 $('#datepicker1').datepicker({
  autoclose: true,format: 'yyyy-mm-dd'
 });
  
  //分页查询
  function searchAward(){
	  $('#pageListForm').submit();
  }
  var imgurl = null;//图片地址
  
  //添加模态框show
  function awardAddModal(){
	  $('#addaward').modal('show');	
	  document.getElementById("awardPoolId").value="";
	  document.getElementById("awardpoolname").value=""; 				  
	  document.getElementById("provider").value="";				  
	  document.getElementById("equalMoney").value=""; 				   
	  document.getElementById("totalNum").value="";
	  document.getElementById("awardType").value=""; 
	  document.getElementById("awardLink").value="";
	  document.getElementById("datepicker").value="";
	  document.getElementById("datepicker1").value="";
	  document.getElementById("showdes").value="";
  }
  
  //添加或编辑奖金池
   function save(){
	  
	var awardPoolId = document.getElementById("awardPoolId").value; //奖品ID
	  var awardpoolname = $('#awardpoolname').val();  //奖品名字
	  var provider = $('#provider').val(); //提供商	  
	  var equalMoney = $('#equalMoney').val();    //市场价格
	  var totalNum = $('#totalNum').val();    //总数
	  var awardType = $('#awardType').val();    //分类
	  var awardLink = $('#awardLink').val();  //奖品外链
	  var btime = $('#datepicker').val();   //发布时间
	  var etime = $('#datepicker1').val();   //截止时间
	  var showdes = $('#showdes').val();    //简介
	 
	  var flag=0;
	  
	  if(awardpoolname==""){
	   $('#awardpoolname-title').removeClass().addClass("form-group has-error col-sm-6");
	   $('#awardpoolname-pic').removeClass().addClass("glyphicon glyphicon-remove");
	   flag=1;
	  }else{
	  $('#awardpoolname-title').removeClass().addClass("form-group has-success col-sm-6");
	   $('#awardpoolname-pic').removeClass().addClass("glyphicon glyphicon-bell");
	  }
	  if(provider==""){
	   $('#provider-title').removeClass().addClass("form-group has-error col-sm-6");
	   $('#provider-pic').removeClass().addClass("glyphicon glyphicon-remove");
	   flag=1;
	  }else{
	  $('#provider-title').removeClass().addClass("form-group has-success col-sm-6");
	   $('#provider-pic').removeClass().addClass("glyphicon glyphicon-user");
	  }
	  if(btime==""){
	   $('#datepicker-title').removeClass().addClass("form-group has-error col-sm-6");
	   $('#datepicker-pic').removeClass().addClass("glyphicon glyphicon-remove");
	   flag=1;
	  }else{
	  $('#datepicker-title').removeClass().addClass("form-group has-success col-sm-6");
	   $('#datepicker-pic').removeClass().addClass("glyphicon glyphicon-time");
	  }
	  if(etime==""){
	   $('#datepicker1-title').removeClass().addClass("form-group has-error col-sm-6");
	   $('#datepicker1-pic').removeClass().addClass("glyphicon glyphicon-remove");
	   flag=1;
	  }else{
	  $('#datepicker1-title').removeClass().addClass("form-group has-success col-sm-6");
	   $('#datepicker1-pic').removeClass().addClass("glyphicon glyphicon-time");
	  }
	  if(equalMoney==""){
	   $('#equalMoney-title').removeClass().addClass("form-group has-error col-sm-6");
	   $('#equalMoney-pic').removeClass().addClass("glyphicon glyphicon-remove");
	   flag=1;
	  }else{
	  $('#equalMoney-title').removeClass().addClass("form-group has-success col-sm-6");
	   $('#equalMoney-pic').removeClass().addClass("glyphicon glyphicon-usd");
	  }
	  if(awardType==""){
	   $('#awardType-title').removeClass().addClass("form-group has-error col-sm-6");
	   $('#awardType-pic').removeClass().addClass("glyphicon glyphicon-remove");
	   flag=1;
	  }else{
	  $('#awardType-title').removeClass().addClass("form-group has-success col-sm-6");
	   $('#awardType-pic').removeClass().addClass("glyphicon glyphicon-wrench");
	  }
	   if(totalNum==""){
	   $('#totalNum-title').removeClass().addClass("form-group has-error col-sm-6");
	   $('#totalNum-pic').removeClass().addClass("glyphicon glyphicon-remove");
	   flag=1;
	  }else{
	  $('#totalNum-title').removeClass().addClass("form-group has-success col-sm-6");
	   $('#totalNum-pic').removeClass().addClass("glyphicon glyphicon-home");
	  }
	   if(showdes==""){
	   $('#showdes-title').removeClass().addClass("form-group has-error col-sm-6");
	   $('#showdes-pic').removeClass().addClass("glyphicon glyphicon-remove");
	   flag=1;
	  }else{
	  $('#showdes-title').removeClass().addClass("form-group has-success col-sm-6");
	   $('#showdes-pic').removeClass().addClass("glyphicon glyphicon-pencil");
	  }
	  if(flag==1){
/* 		  $('#addaward').modal('hide')
		showMessageDialog("请将信息补充完整后再次提交",function(){
			 window.location.href = "${ctx}/platform/awardPool.do";
		}); */
		alert("请将信息补充完整");
	    return;
	  }
	  
	  //新增
	  if(awardPoolId==""){
		  $.ajax({
			  url:'${ctx}/platform/addawardpool.do',
			  type:'post',
			  dataType:'json',
			  data:{
				  awardpoolname :awardpoolname,
				  provider : provider,
				  equalMoney : equalMoney,
				  totalNum : totalNum,
				  awardType : awardType,
				  awardLink : awardLink,
				  btime : btime,
				  etime : etime,			 
				  awardDes : showdes

				
			  },
			  success:function(data){
			  $('#addaward').modal('hide');
			  showMessageDialog(data.msg,function(){
				  if(data.success){
					  window.location.href = "${ctx}/platform/awardPool.do";
				  }
			  });
			  }
			  
		  });
	  }
	  //编辑
	  else if(awardPoolId!=""){
		  $.ajax({
			  url:'${ctx}/platform/editAwardPool.do',
			  type:'post',
			  dataType:'json',
			  data:{
				  awardPoolId : awardPoolId,
				  awardpoolname :awardpoolname,
				  provider : provider,
				  equalMoney : equalMoney,
				  totalNum : totalNum,
				  awardType : awardType,
				  awardLink : awardLink,
				  btime : btime,
				  etime : etime,			 
				  awardDes : showdes

				
			  },
			  success:function(data){
			  $('#addaward').modal('hide');
			  showMessageDialog(data.msg,function(){
				  if(data.success){
					  window.location.href = "${ctx}/platform/awardPool.do";
				  }
			  });
			  }
			  
		  });
	  }

	  
  }
  //初始化添加事件
/*   function initadd(){
  $(".img-rounded").attr("src","/NIMS/pic/default.gif");
  } */
 //上传图片
/*  function uploadpic(){
    var formData = new FormData($( "#getpic")[0]);  
    $.ajax({  
         url: '${ctx}/platform/uploadPicture.do',  
         type: 'POST',  
         data: formData,  
         async: false,  
         cache: false,  
         contentType: false,  
         processData: false,  
         success: function (data) {  
             imgurl = data.url;
             $(".img-rounded").attr("src",imgurl);
         },  
         error: function (data) {  
             showMessageDialog(data.msg);  
         }  
    }); 
} */
//批量上架奖池
function awardonwork(){

	  var checkAwardpool = document.getElementsByName("checkAwardpool");
	  var count = 0 ;
	  var awardPoolId = "";
	  for(var i=0;i<checkAwardpool.length;i++){
		  if(checkAwardpool[i].checked){
			  count++;
			  var poolIdAndStatus = checkAwardpool[i].value;
			  var temp= new Array();
			  temp = poolIdAndStatus.split("=");
			  if(temp[1]!=1){
				  showMessageDialog("选中奖品已上架或已失效…");
				  return false;
			  } 
			  awardPoolId = temp[0]+"!"+awardPoolId;
			 
		  }
		  
	  }
	  if(count == 0){
		  showMessageDialog("请选择要上架的奖品…");
		  return false;
	  }
	 
	  $.ajax({
		  url:"${ctx}/platform/updateawardpool.do",
		  data:{
			  awardPoolId : awardPoolId
		  },
	  	  type:"post",
		  dataType:"json",
		  success:function(data){
			  
				  showMessageDialog(data.msg,function(){
					  window.location.href = "${ctx}/platform/awardPool.do";
				  });
				  
			  
		  }
	  });
	  
  }
//批量下架奖池
function awardnotwork(){

	  var checkAwardpool = document.getElementsByName("checkAwardpool");
	  var count = 0 ;
	  var awardPoolId = "";
	  for(var i=0;i<checkAwardpool.length;i++){
		  if(checkAwardpool[i].checked){
			  count++;
			  var poolIdAndStatus = checkAwardpool[i].value;
			  var temp= new Array();
			  temp = poolIdAndStatus.split("=");
			  if(temp[1]!=2){
				  showMessageDialog("选中奖品已下架或已失效…");
				  return false;
			  } 
			  awardPoolId = temp[0]+"!"+awardPoolId;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择要下架的奖品");
		  return false;
	  }
	 
	  $.ajax({
		  url:"${ctx}/platform/updateawardpool1.do",
		  data:{
			  awardPoolId : awardPoolId
		  },
	  	  type:"post",
		  dataType:"json",
		  success:function(data){
			  
				  showMessageDialog(data.msg,function(){
					  window.location.href = "${ctx}/platform/awardPool.do";
				  });
				  
			  
		  }
	  });
	  
  }
  
  //批量失效
  function awardInvalid(){

	  var checkAwardpool = document.getElementsByName("checkAwardpool");
	  var count = 0 ;
	  var awardPoolId = "";
	  for(var i=0;i<checkAwardpool.length;i++){
		  if(checkAwardpool[i].checked){
			  count++;
			  var poolIdAndStatus = checkAwardpool[i].value;
			  var temp= new Array();
			  temp = poolIdAndStatus.split("=");
			  if(temp[1]!=1){
				  showMessageDialog("选中奖品正上架或已失效…");
				  return false;
			  } 
			  awardPoolId = temp[0]+"!"+awardPoolId;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择要失效的奖品");
		  return false;
	  }
	 
	  $.ajax({
		  url:"${ctx}/platform/updateawardpool2.do",
		  data:{
			  awardPoolId : awardPoolId
		  },
	  	  type:"post",
		  dataType:"json",
		  success:function(data){
			  
				  showMessageDialog(data.msg,function(){
					  window.location.href = "${ctx}/platform/awardPool.do";
				  });
				  
			  
		  }
	  });
	  
  }
  
  //批量删除
    function awarddelete(){

	  var checkAwardpool = document.getElementsByName("checkAwardpool");
	  var count = 0 ;
	  var awardPoolId = "";
	  for(var i=0;i<checkAwardpool.length;i++){
		  if(checkAwardpool[i].checked){
			  count++;
			  var poolIdAndStatus = checkAwardpool[i].value;
			  var temp= new Array();
			  temp = poolIdAndStatus.split("=");
			  if(temp[1]==2){
				  showMessageDialog("选中奖品正上架…");
				  return false;
			  } 
			  awardPoolId = temp[0]+"!"+awardPoolId;
		  }
	  }
	  if(count == 0){
		  showMessageDialog("请选择要删除的奖品…");
		  return false;
	  }
	 
	  showConfirmDialog("不再想想了么(T ^ T)",function(check){
		  if(check){
			  $.ajax({
				  url:"${ctx}/platform/deleteAwardPool.do",
				  data:{
					  awardPoolId : awardPoolId
				  },
			  	  type:"post",
				  dataType:"json",
				  success:function(data){
					  
						  showMessageDialog(data.msg,function(){
							  window.location.href = "${ctx}/platform/awardPool.do";
						  });
						  
					  
				  }
			  });
		  }
	  })

	  
  }
//按条件查询  
function searchawardpool(){
	    $('#pageListForm').submit();
  }
  
//编辑
    function awardPoolEditPage(awardPoolId){	 
	  $.ajax({
		  url:"${ctx}/platform/getAwardPoolInfo.do",
	  	  type:"post",
		  dataType:"json",
		  data:{
			  awardPoolId : awardPoolId
		  },
		  success:function(data){
			  $('#addaward').modal('show');	
			  	document.getElementById("awardPoolId").value=data.awardPoolId;
				  document.getElementById("awardpoolname").value=data.awardPoolName; 				  
				  document.getElementById("provider").value=data.provider;				  
 				   document.getElementById("equalMoney").value=data.equalMoney; 				   
				  document.getElementById("totalNum").value=data.totalNum;
				  document.getElementById("awardType").value=data.awardType; 
				  document.getElementById("awardLink").value=data.awardLink;
				  var date1 = new Date(data.validBTime);
				  document.getElementById("datepicker").value=date1.getFullYear()+'-'+(date1.getMonth()+1)+'-'+date1.getDate();
				  var date2 = new Date(data.validETime);
				  document.getElementById("datepicker1").value=date2.getFullYear()+'-'+(date2.getMonth()+1)+'-'+date2.getDate();
				  document.getElementById("showdes").value=data.awardDes;
/* 				  if(data.status==1){
						document.getElementById("Awardstatus").innerHTML="无效";	
					}
					else if(data.status==2){
						document.getElementById("Awardstatus").innerHTML="有效";	
					}
					else {
						document.getElementById("Awardstatus").innerHTML="失效";
					} */

		  },
		  error: function(data) {
			  showMessageDialog("请求失败");
		  }
		  });
	 // window.location.href = "${ctx}/platform/toAwardEditPage.do?awardId="+ awardId;
  }
  
  </script>
</body>

</html>