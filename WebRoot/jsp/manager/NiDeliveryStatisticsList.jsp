<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>统计管理-投放统计</title>
<link rel="stylesheet" href="${ctx}/js/sang.css" type="text/css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- Main content -->
		<div class="panel panel-default">
			<div class="panel-body">
				<form class="form-horizontal" method="post" action="${ctx}/platform/niDeliveryStatisticsList.do" id="deliveryStatisticsListForm">

					<div class="row mb10">
						<div class="col-sm-2">
							<select class="form-control select-sm" id="searchType" name="searchType">
								<option value="0">投放id</option>
								<option value="1">问卷名称</option>
							</select>
						</div>
						<div class="input-group input-group-sm col-sm-3">
							<input id="deliveryContent" type="text" class="form-control" name="deliveryContent"> 
							<span class="input-group-btn">
								<button type="button" onclick="searchDeliveryStatistics()" class="btn btn-info btn-flat">Go!</button>
							</span>
						</div>
					</div>
					<div class="row mb10">
						<div class="col-sm-2">
							<label class="select-label">渠道</label> <select
								class="form-control select-sm" id="deliveryChannel" name="deliveryChannel" style="width:75%;">
								<option value="">请选择渠道</option>
								<option value="1">玩校</option>
								<option value="2">微信</option>
								<option value="3">APP</option>
							</select>
						</div>
						<div class="col-sm-3">
							<label class="select-label">投放状态</label>
							<select
								class="form-control select-sm" id="deliveryStatus"
								name="deliveryStatus" style="width:75%;">
								<option value="">请选择投放状态</option>
								<option value="1">待投放</option>
								<option value="2">投放中</option>
								<option value="3">暂停中</option>
								<option value="4">人工终止</option>
								<option value="5">时间完成</option>
								<option value="6">数量完成</option>
							</select>

						</div>
					</div>
					<div class="row mb10">
						<div class="col-sm-2">
							<label class="select-label">类型</label>
							<select class="form-control select-sm" id="selectDeliveryType" name="selectDeliveryType" style="width:75%;">
								<option value="">请选择类型</option>
								<option value="1">调查</option>
								<option value="2">测评</option>
								<option value="3">投票</option>
							</select>
						</div>
					</div>

				</form>
			</div>

		</div>

		<div class="panel panel-default">
			<div class="panel-body">
				<div class="button-group">

					<button type="button" class="btn btn-default btn-sm"
						onclick="downloadDetail();" style="width: 12%;">下载答题明细</button>
					<button type="button" onclick="exportSheet();"
						class="btn btn-default btn-sm" style="width: 12%;">导出表格</button>
					<button type="button" onclick="exportCube();"
						class="btn btn-default btn-sm" style="width: 12%;">导出访问情况Cube表</button>
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
								<th>编号</th>
								<th>投放id</th>
								<th>问卷名称</th>
								<th>渠道</th>
								<th>状态</th>
								<th>类型</th>
								<th>答题人数</th>
								<!-- <th>提交人数</th>
								<th>完成人数</th> -->
								<th>修正数</th>
								<th>奖励领取人数</th>
								<th>投放开始时间</th>
								<th>投放结束时间</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${niDeliveryStatistics}" var="deliveryStatistics" varStatus="n">
								<tr>
									<td>
										<input type="checkbox" id="checkDeliveryStatistics" name="checkDeliveryStatistics"
										value="${deliveryStatistics.deliveryId }"> 
										<input type="hidden" id="answerChannel" name="answerChannel"
										value="${deliveryStatistics.channelId }"> 
										<input type="hidden" id="type" name="type" value="${deliveryStatistics.type }">
									</td>
									<td>${n.index+1}</td>
									<td>${deliveryStatistics.deliveryId}</td>
									<td>
										<div
											style="width: 172px;white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
											${deliveryStatistics.showTitle}</div>
									</td>
									<td><c:if test="${deliveryStatistics.channelId ==1 }">玩校</c:if>
										<c:if test="${deliveryStatistics.channelId ==2 }">微信</c:if>
										<c:if test="${deliveryStatistics.channelId ==3 }">APP</c:if>
									</td>
									<td><c:if test="${deliveryStatistics.status == 1}">待投放</c:if>
										<c:if test="${deliveryStatistics.status == 2}">投放中</c:if> 
										<c:if test="${deliveryStatistics.status == 3}">暂停中</c:if> 
										<c:if test="${deliveryStatistics.status == 4}">人工终止</c:if> 
										<c:if test="${deliveryStatistics.status == 5}">时间完成</c:if> 
										<c:if test="${deliveryStatistics.status == 6}">数量完成</c:if>
									</td>
									<td><c:if test="${deliveryStatistics.type == 1}">调查</c:if>
										<c:if test="${deliveryStatistics.type == 2}">测评</c:if> 
										<c:if test="${deliveryStatistics.type == 3}">投票</c:if>
									</td>
									
									<%-- <td><c:if test="${deliveryStatistics.answerNum == 0}">未实现该功能</c:if></td> --%>
									<td>${deliveryStatistics.commitNum}</td>
									<%-- <td>${deliveryStatistics.finishNum}</td> --%>
									<td>${deliveryStatistics.correctNum}</td>
									<td>${deliveryStatistics.awardNum}</td>
									<td><fmt:formatDate value="${deliveryStatistics.bTime}" pattern="yyyy-MM-dd"/></td>
									<td><fmt:formatDate value="${deliveryStatistics.eTime}" pattern="yyyy-MM-dd"/></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>

	<!-- 投放统计内容部分结束 -->

   <!-- 下载答题详细信息模态框 -->
    <div class="modal fade" id="downloadDetailInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-deliveryid="0" data-type="0" data-channel="0">
    	 <div class="modal-dialog">
    	 	<div class="modal-content">
    	 	    <div class="modal-header">
            		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
           			<h4 class="modal-title" id="myModalLabel" style="color: #3c8dbc">请选择下载格式</h4>
           		</div><!-- /.modal-modal-header -->
           		<div class="modal-body">   
           			<div class="form-group has-success col-sm-3"  style="width:300px; padding-right:0px;">     		
					<button type="button" class="btn btn-default btn-sm"
						onclick="downloadDetail1();" style="width: 300px;">统计模式</button>
					</div>
					<!-- <div class="form-group has-success col-sm-3"  style="width:300px; padding-right:0px;"> 
					<button type="button" class="btn btn-default btn-sm"
						onclick="downloadDetail2();" style="width: 300px;">横表模式</button>
					</div> -->
					
					<div class="form-group has-success col-sm-3"  style="width:300px; padding-right:0px;"> 
					<button type="button" class="btn btn-default btn-sm"
						onclick="download_AnswerDetail();" style="width: 300px;">横表模式</button>
					</div>       		       			
           		</div><!-- /.modal-body -->
           		<div class="modal-footer">
            		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
         		</div>
    	 	</div>
    	 </div>
    </div>
    
	<div class="control-sidebar-bg"></div>

	<!-- ./wrapper -->
	<script src="${ctx}/static/js/moment.min.js"></script>
	<script src="${ctx}/js/validate.js"></script>

	<script>
		var deliveryContentL = 20;
		//按条件查询投放统计表
		function searchDeliveryStatistics() {

			var searchType = $("#searchType").val();
			var deliveryContent = $("#deliveryContent").val();

			if (searchType != null && deliveryContent != null
					&& searchType.length != 0
					&& deliveryContent.length != 0) {
				//判断输入长度
				if (deliveryContent.trim().length > deliveryContentL) {
					showMessageDialog("投放id或问卷名称不能超过" + deliveryContentL
							+ "个字符!");
					return;
				} else {
					if (searchType == 0) {
						//id
						if (checkNumber(deliveryContent) == false) {
							showMessageDialog("您输入的不是数字,请重新输入!");
							return;
						} else if (checkNumber(deliveryContent) == true) {
							if (checkNum(deliveryContent) == false) {
								//再判断是否为0开头
								showMessageDialog("您输入的数字不能以0开头,请重新输入!");
								return;
							}
						}
					} else if (searchType == 1) {
						//名称
						if (checkQuote(deliveryContent) == true) {
							showMessageDialog("不能包含特殊字符!");
							return;
						} else if (deliveryContent.trim().length == 0) {
							showMessageDialog("不能输入空字符串,请重新输入!");
							return;
						}
					}
				}

			}

			$('#deliveryStatisticsListForm').submit();

		}

		//将页面中展示的数据导出表格
		function exportSheet() {
			var checkDeliveryStatistics_arr = document
					.getElementsByName("checkDeliveryStatistics");
			var answerChannel_arr = document.getElementsByName("answerChannel");
			var type_arr = document.getElementsByName("type");
			var deliveryId = "";
			var channel = "";
			var type = "";
			for (var i = 0; i < checkDeliveryStatistics_arr.length; i++) {
				deliveryId = checkDeliveryStatistics_arr[i].value + "!"
						+ deliveryId;
				channel = answerChannel_arr[i].value + "!" + channel;
				type = type_arr[i].value + "!" + type;
			}
			window.location.href = "${ctx}/platform/exportSheet.do?deliveryId="
					+ deliveryId + "&checkChannel=" + channel + "&checkType="
					+ type;

		}

		//下载答题明细的响应
		function downloadDetail() {
			//下载每次只能下载一个
			var checkDeliveryStatistics_arr = document
					.getElementsByName("checkDeliveryStatistics");
			var answerChannel_arr = document.getElementsByName("answerChannel");
			var type_arr = document.getElementsByName("type");
			var count = 0;
			var answerChannel = "";
			var type = "";
			var deliveryId = "";
			for (var i = 0; i < checkDeliveryStatistics_arr.length; i++) {
				if (checkDeliveryStatistics_arr[i].checked) {
					//取出id,渠道和类型值
					deliveryId = checkDeliveryStatistics_arr[i].value;
					answerChannel = answerChannel_arr[i].value;
					type = type_arr[i].value;
					count++;
				}
			}
			if (count == 0) {
				showMessageDialog("请选择要下载的问卷");
				return;
			} else if (count > 1) {
				showMessageDialog("对不起,每次只能下载一个问卷明细");
				return;
			}
			
			$('#downloadDetailInfo')
				.attr('data-deliveryid',deliveryId)
				.attr('data-type',type)
				.attr('data-channel',answerChannel)
				.modal('show');

		}
		//点击进入模态框后进入不同选择按钮的JS
		function downloadDetail1(){
			var deliveryId = $('#downloadDetailInfo').attr('data-deliveryid');
			var type = $('#downloadDetailInfo').attr('data-type');
			var channel = $('#downloadDetailInfo').attr('data-channel');
			window.location.href = "${ctx}/platform/downloadDetail1.do?deliveryId="
					+ deliveryId
					+ "&answerChannel="
					+ channel
					+ "&type="
					+ type;
			
		}
		
		// 横表模式
		function downloadDetail2(){
			var deliveryId = $('#downloadDetailInfo').attr('data-deliveryid');
			var type = $('#downloadDetailInfo').attr('data-type');
			var channel = $('#downloadDetailInfo').attr('data-channel');
			window.location.href = "${ctx}/platform/downloadDetail2.do?deliveryId="
					+ deliveryId
					+ "&answerChannel="
					+ channel
					+ "&type="
					+ type;
		}
		// 竖表模式
		function download_AnswerDetail(){
			var deliveryId = $('#downloadDetailInfo').attr('data-deliveryid');
			var type = $('#downloadDetailInfo').attr('data-type');
			var channel = $('#downloadDetailInfo').attr('data-channel');
			window.location.href = "${ctx}/platform/download_AnswerDetail.do?deliveryId="
					+ deliveryId
					+ "&answerChannel="
					+ channel
					+ "&type="
					+ type;
			
		}
		
		function exportCube() {
			var checkDeliveryStatistics_arr = document
					.getElementsByName("checkDeliveryStatistics");
			var type_arr = document.getElementsByName("type");
			var deliveryIdArr = "";
			var channel = "1";
			var typeArr = "";
			var count = 0;
			for (var i = 0; i < checkDeliveryStatistics_arr.length; i++) {
				if (checkDeliveryStatistics_arr[i].checked) {
					//取出id,渠道和类型值
					deliveryIdArr = checkDeliveryStatistics_arr[i].value + "-"
							+ deliveryIdArr;
					typeArr = type_arr[i].value + "-" + typeArr;
					count++;
				}
			}
			if (count == 0) {
				showMessageDialog("请选择要导出Cube表的问卷");
				return;
			}
			/* $.ajax({
				url : '${ctx}/platform/exportStatisticsCube.do',
				type : 'post',
				dataType : 'json',
				data : {
					deliveryIdArr : deliveryIdArr,
					typeArr : typeArr,
					channel : channel
				},
				success : function(data) {
					showMessageDialog(data.msg);
				}
			}); */
			window.location.href = "${ctx}/platform/exportStatisticsCube.do?deliveryIdArr="
					+ deliveryIdArr
					+ "&typeArr="
					+ typeArr
					+ "&channel="
					+ channel;
		}
	</script>
</body>
</html>
