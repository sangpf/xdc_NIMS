<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>超级调查</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

</head>

<body class="hold-transition skin-blue sidebar-mini">
	<div class="box box-primary">
		<table style="padding: 100px;margin: 10px;">
			<tr>
				<td style="width: 100px;padding: 3px;"><a href="${ctx}/platform/loadDaily3UpdateList.do">
					<button class="btn btn-block btn-primary" style="">页面栏目</button></a></td>
				<td style="width: 100px;padding: 3px;"><a href="${ctx}/platform/loadSuperList.do">
					<button class="btn btn-block btn-primary" style="background:green">合作问卷</button></a></td>
				<td style="width: 100px;padding: 3px;"><a href="${ctx}/platform/loadAssessList.do">
					<button class="btn btn-block btn-primary">专业测评</button></a></td>
				<td style="width: 100px;padding: 3px;"><a href="${ctx}/platform/loadReportList.do">
					<button class="btn btn-block btn-primary">数字报告</button></a></td>
			</tr>
		</table>
		<div class="box-header with-border">
			<h3 class="box-title">列表管理—超级调查</h3>
		</div>
		<!-- 内容部分 -->
		<!-- Main content -->
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" method="post" action="${ctx}/platform/loadSuperList.do" id="pageListForm">
						<table style="border-spacing: 5px;border-collapse: separate;">
							<tr>
								<!-- <td><select class="form-control select-sm" name="choose"
									id="choose">
										<option value="0">问卷名称</option>
										<option value="1">投放Id</option>
								</select></td> -->
								<!-- <td><input id="qnName" name="qnName" type="text" style="width: 100px;"
									class="form-control"></td> -->
									
								<td><label>&nbsp;页面状态&nbsp;&nbsp;</label></td>
								<td>
									<select class="form-control" name="pageStatus" id="pageStatus">
										<option value="">不限</option>
										<option value="1">待发布</option>
										<option value="2">定时发布</option>
										<option value="3">已发布</option>
									</select>
							 	</td>

								<td><label>&nbsp;&nbsp;类型&nbsp;&nbsp;</label></td>
								<td>
									<select class="form-control" name="qnType" id="qnType">
										<option value="">不限</option>
										<option value="1">调查</option>
										<option value="3">投票</option>
									</select>
								</td>
								
								<td><label>&nbsp;栏目名称</label></td>
								<td style="width: 160px;">
									<select class="form-control select-sm" style="font-size: 16px;height: 35px;" name="superListCategory_Str_GO" id="superListCategory_Str_GO">
										<c:forEach items="${superListCategory_list }" var="superListCategory">
											<option <c:if test="${superListCategory_Str == superListCategory.srtId}">selected="selected"</c:if> value="${superListCategory.srtId}">${superListCategory.strName}</option>
										</c:forEach>
									</select>
								</td>
								
								<td><span class="input-group-btn">
										<button type="button" onclick="searchSuperQuestionnaire()"
											class="btn btn-info btn-flat">Go!</button>
								</span></td>
							</tr>
						</table>
						<!-- <div class="input-group col-sm-3">
							<div class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</div>
							<input type="text" class="form-control pull-right"
								name="reservation" id="reservation" style="height:30px;"
								placeholder="请选择日期">
						</div> -->
				</form>
				</div>
				<div class="panel-body">
					<div class="button-group">

						<button type="button" onclick="addSuper()"
							class="btn btn-default btn-sm">添加</button>
						<button type="button" onclick="postSuper()"
							class="btn btn-default btn-sm">发布</button>
						<button type="button" onclick="revokeSuper()"
							class="btn btn-default btn-sm">撤销</button>
						<button type="button" onclick="deleteSuperFromList()"
							class="btn btn-default btn-sm">删除</button>
						<button type="button" onclick="refreshSuper()"
							class="btn btn-default btn-sm">刷新</button>
					    
						<button type="button" onclick="timerSuper()"
							class="btn btn-default btn-sm">定时器</button>
					</div>
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
							<th>编号</th>
							<th>投放ID</th>
							<th>投放标题</th>
							<th>问卷类型</th>
							<th>页面状态</th>
							<!-- <th>投放状态</th> -->
							<th>发布时间</th>
							<th>栏目名称</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
	<c:forEach items="${niSuper}" var="superSurvey" varStatus="n">
	<!-- isTop 1：置顶；2未置顶 -->
	<c:if test="${superSurvey.isTop==2}">
		<c:if test="${superSurvey.pageStatus!=3}">
			<tr style="background:#87CEFA">
				<td>
					<input type="checkbox" id="checkniSuper" name="checkniSuper" value="${superSurvey.deliveryId }">
					<input type="hidden" value="${superSurvey.pageStatus}" name="deleStat" id="deleStat">
					<input type="hidden" value="${superSurvey.qnType}" name="qnTypes" id="qnTypes">
					<input type="hidden" value="${superSurvey.status}" name="status" id="status">
					<input type="hidden" value="${superSurvey.showOrder}" name="showorder" id="showorder">
					<input type="hidden" value="${superSurvey.superListCategory }" name="superListCategory_Str" id="superListCategory_Str">
				</td>
							<td><c:if test="${superSurvey.isTop==2}">${n.index+1}</c:if>
								<c:if test="${superSurvey.isTop==1}">
								</c:if></td>
							<td>${superSurvey.deliveryId }</td>
							
							<td>
		                  		<div style="width: 250px;white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
		                  			${superSurvey.showTitle }
		                  		</div>								
							</td>
							
							<td><c:if test="${superSurvey.qnType==1}">调查</c:if> <c:if
									test="${superSurvey.qnType==2}">测评</c:if> <c:if
									test="${superSurvey.qnType==3}">投票</c:if></td>
							<td><c:if test="${superSurvey.pageStatus== 2}">定时发布</c:if>
								<c:if test="${superSurvey.pageStatus == 1}">待发布</c:if> <c:if
									test="${superSurvey.pageStatus == 3}">已发布</c:if></td>
									
							<%-- <td><c:if test="${superSurvey.status== 2}">投放中</c:if> <c:if
									test="${superSurvey.status == 1}">待投放</c:if> <c:if
									test="${superSurvey.status == 3}">暂停中</c:if> <c:if
									test="${superSurvey.status == 4}">人工终止</c:if> <c:if
									test="${superSurvey.status == 5}">时间完成</c:if> <c:if
									test="${superSurvey.status == 6}">数量完成</c:if></td> --%>
									
							<td><fmt:formatDate value="${superSurvey.pTime }" type="both" /></td>
							<!-- 页面列表分类 surveyQnList, 调查，awardQnList, 有奖问卷，funQnList, 吃喝玩乐，gestureQnList, 涨姿势，roseQnList, 花式开撩 -->
							<td>
								<c:if test="${superSurvey.superListCategory == 'schoolQnList'}">校园生活</c:if>
								<c:if test="${superSurvey.superListCategory == 'societyQnList'}">社会镜头</c:if>
								<c:if test="${superSurvey.superListCategory == 'relationQnList'}">亲密关系</c:if>
								<c:if test="${superSurvey.superListCategory == 'emotionQnList'}">我的情绪</c:if>
								<c:if test="${superSurvey.superListCategory == 'brainQnList'}">最强大脑</c:if> 
								<c:if test="${superSurvey.superListCategory == 'awardQnList'}">有奖问卷</c:if>
								<c:if test="${superSurvey.superListCategory == 'futureQnList'}">未来规划</c:if>
								<c:if test="${superSurvey.superListCategory == 'scienceQnList'}">懂点科学</c:if>
								<c:if test="${superSurvey.superListCategory == 'healthyQnList'}">生理健康</c:if>
								
							</td>
							<td>
								<button type="button"
									onclick="editSuperList(${superSurvey.showOrder },${superSurvey.pageStatus})"
									class="btn btn-default btn-sm">编辑</button>
								<button type="button"
									onclick="moveUp(${n.index+1},${superSurvey.deliveryId },${superSurvey.qnType },${superSurvey.showOrder },${superSurvey.pageStatus},'${superSurvey.superListCategory}')"
									class="btn btn-default btn-sm">上移</button>
								<button type="button"
									onclick="moveDown(${n.index+1},${superSurvey.deliveryId },${superSurvey.qnType },${superSurvey.showOrder },${superSurvey.pageStatus},'${superSurvey.superListCategory}')"
									class="btn btn-default btn-sm">下移</button>
								<button type="button" id="top"
									onclick="changeIsTop(${superSurvey.deliveryId},${superSurvey.isTop},${superSurvey.qnType},${superSurvey.pageStatus},'${superSurvey.superListCategory}')"
									class="btn btn-default btn-sm">
									<c:if test="${superSurvey.isTop==2}">置顶</c:if>
									<c:if test="${superSurvey.isTop==1}">取消</c:if>
								</button>
							</td>
						</tr>
					</c:if>
		<c:if test="${superSurvey.pageStatus==3}">
			<tr>
				<td>
					<input type="checkbox" id="checkniSuper" name="checkniSuper" value="${superSurvey.deliveryId }">
					<input type="hidden" value="${superSurvey.pageStatus}" name="deleStat" id="deleStat">
					<input type="hidden" value="${superSurvey.qnType}" name="qnTypes" id="qnTypes">
					<input type="hidden" value="${superSurvey.status}" name="status" id="status">
					<input type="hidden" value="${superSurvey.showOrder}" name="showorder" id="showorder">
					<input type="hidden" value="${superSurvey.superListCategory }" name="superListCategory_Str" id="superListCategory_Str">
				</td>
							<td><c:if test="${superSurvey.isTop==2}">${n.index+1}</c:if>
								<c:if test="${superSurvey.isTop==1}">
								</c:if></td>
							<td>${superSurvey.deliveryId }</td>
							
							<td>
		                  		<div style="width: 250px;white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
		                  			${superSurvey.showTitle }
		                  		</div>								
							</td>
							
							<td><c:if test="${superSurvey.qnType==1}">调查</c:if> <c:if
									test="${superSurvey.qnType==2}">测评</c:if> <c:if
									test="${superSurvey.qnType==3}">投票</c:if></td>
							<td><c:if test="${superSurvey.pageStatus== 2}">定时发布</c:if>
								<c:if test="${superSurvey.pageStatus == 1}">待发布</c:if> <c:if
									test="${superSurvey.pageStatus == 3}">已发布</c:if></td>
									
							<%-- <td><c:if test="${superSurvey.status== 2}">投放中</c:if> <c:if
									test="${superSurvey.status == 1}">待投放</c:if> <c:if
									test="${superSurvey.status == 3}">暂停中</c:if> <c:if
									test="${superSurvey.status == 4}">人工终止</c:if> <c:if
									test="${superSurvey.status == 5}">时间完成</c:if> <c:if
									test="${superSurvey.status == 6}">数量完成</c:if></td> --%>
									
							<td><fmt:formatDate value="${superSurvey.pTime }"
									type="both" /></td>
							<td>
								<c:if test="${superSurvey.superListCategory == 'schoolQnList'}">校园生活</c:if>
								<c:if test="${superSurvey.superListCategory == 'societyQnList'}">社会镜头</c:if>
								<c:if test="${superSurvey.superListCategory == 'relationQnList'}">亲密关系</c:if>
								<c:if test="${superSurvey.superListCategory == 'emotionQnList'}">我的情绪</c:if>
								<c:if test="${superSurvey.superListCategory == 'brainQnList'}">最强大脑</c:if> 
								<c:if test="${superSurvey.superListCategory == 'awardQnList'}">有奖问卷</c:if>
								<c:if test="${superSurvey.superListCategory == 'futureQnList'}">未来规划</c:if>
								<c:if test="${superSurvey.superListCategory == 'scienceQnList'}">懂点科学</c:if>
								<c:if test="${superSurvey.superListCategory == 'healthyQnList'}">生理健康</c:if>
								
							</td>
							<td>
								<button type="button"
									onclick="editSuperList(${superSurvey.showOrder },${superSurvey.pageStatus})"
									class="btn btn-default btn-sm">编辑</button>
								<button type="button"
									onclick="moveUp(${n.index+1},${superSurvey.deliveryId },${superSurvey.qnType },${superSurvey.showOrder },${superSurvey.pageStatus},'${superSurvey.superListCategory}')"
									class="btn btn-default btn-sm">上移</button>
								<button type="button"
									onclick="moveDown(${n.index+1},${superSurvey.deliveryId },${superSurvey.qnType },${superSurvey.showOrder },${superSurvey.pageStatus},'${superSurvey.superListCategory}')"
									class="btn btn-default btn-sm">下移</button>
								<button type="button" id="top"
									onclick="changeIsTop(${superSurvey.deliveryId},${superSurvey.isTop},${superSurvey.qnType},${superSurvey.pageStatus},'${superSurvey.superListCategory}')"
									class="btn btn-default btn-sm">
									<c:if test="${superSurvey.isTop==2}">置顶</c:if>
									<c:if test="${superSurvey.isTop==1}">取消</c:if>
								</button>
							</td>
						</tr>
					</c:if>
				</c:if>
							
				
				<c:if test="${superSurvey.isTop==1}">
					<tr style="background:#DDA0DD">
					<td>
						<input type="checkbox" id="checkniSuper" name="checkniSuper" value="${superSurvey.deliveryId }">
						<input type="hidden" value="${superSurvey.pageStatus}" name="deleStat" id="deleStat">
						<input type="hidden" value="${superSurvey.qnType}" name="qnTypes" id="qnTypes">
						<input type="hidden" value="${superSurvey.status}" name="status" id="status">
						<input type="hidden" value="${superSurvey.showOrder}" name="showorder" id="showorder">
						<input type="hidden" value="${superSurvey.superListCategory }" name="superListCategory_Str" id="superListCategory_Str">
					</td>
									<td><c:if test="${superSurvey.isTop==2}">${n.index+1}</c:if>
										<c:if test="${superSurvey.isTop==1}">
											<i class="fa fa-fw fa-chevron-up"></i>
										</c:if></td>
									<td>${superSurvey.deliveryId }</td>
									
									<td>
				                  		<div style="width: 250px;white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
				                  			${superSurvey.showTitle }
				                  		</div>							
									</td>
									
									<td><c:if test="${superSurvey.qnType==1}">调查</c:if> <c:if
											test="${superSurvey.qnType==2}">测评</c:if> <c:if
											test="${superSurvey.qnType==3}">投票</c:if></td>
									<td><c:if test="${superSurvey.pageStatus== 2}">定时发布</c:if>
										<c:if test="${superSurvey.pageStatus == 1}">待发布</c:if> <c:if
											test="${superSurvey.pageStatus == 3}">已发布</c:if></td>
											
									<%-- <td><c:if test="${superSurvey.status== 2}">投放中</c:if> <c:if
											test="${superSurvey.status == 1}">待投放</c:if> <c:if
											test="${superSurvey.status == 3}">暂停中</c:if> <c:if
											test="${superSurvey.status == 4}">人工终止</c:if> <c:if
											test="${superSurvey.status == 5}">时间完成</c:if> <c:if
											test="${superSurvey.status == 6}">数量完成</c:if></td> --%>
											
									<td><fmt:formatDate value="${superSurvey.pTime }" type="both" /></td>
									<td>
										<c:if test="${superSurvey.superListCategory == 'schoolQnList'}">校园生活</c:if>
										<c:if test="${superSurvey.superListCategory == 'societyQnList'}">社会镜头</c:if>
										<c:if test="${superSurvey.superListCategory == 'relationQnList'}">亲密关系</c:if>
										<c:if test="${superSurvey.superListCategory == 'emotionQnList'}">我的情绪</c:if>
										<c:if test="${superSurvey.superListCategory == 'brainQnList'}">最强大脑</c:if> 
										<c:if test="${superSurvey.superListCategory == 'awardQnList'}">有奖问卷</c:if>
										<c:if test="${superSurvey.superListCategory == 'futureQnList'}">未来规划</c:if>
										<c:if test="${superSurvey.superListCategory == 'scienceQnList'}">懂点科学</c:if>
										<c:if test="${superSurvey.superListCategory == 'healthyQnList'}">生理健康</c:if>
									</td>
									<td>
										<button type="button"
											onclick="editSuperList(${superSurvey.showOrder },${superSurvey.pageStatus})"
											class="btn btn-default btn-sm">编辑</button>
										<button type="button"
											onclick="moveUp(${n.index+1},${superSurvey.deliveryId },${superSurvey.qnType },${superSurvey.showOrder },${superSurvey.pageStatus},'${superSurvey.superListCategory}')"
											class="btn btn-default btn-sm">上移</button>
										<button type="button"
											onclick="moveDown(${n.index+1},${superSurvey.deliveryId },${superSurvey.qnType },${superSurvey.showOrder },${superSurvey.pageStatus},'${superSurvey.superListCategory}')"
											class="btn btn-default btn-sm">下移</button>
										<button type="button" id="top"
											onclick="changeIsTop(${superSurvey.deliveryId},${superSurvey.isTop},${superSurvey.qnType},${superSurvey.pageStatus},'${superSurvey.superListCategory}')"
											class="btn btn-default btn-sm">
											<c:if test="${superSurvey.isTop==2}">置顶</c:if>
											<c:if test="${superSurvey.isTop==1}">取消</c:if>
										</button>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- /.box-body -->
		</div>
	</div>

	<!-- 内容部分结束 -->


	<div class="control-sidebar-bg"></div>
	<!-- ./wrapper -->
	<%-- <script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script> --%>
	<script
		src="${ctx}/static/js/moment.min.js"></script>
	<script src="${ctx}/js/validate.js"></script>
	<script>
		//发布超级调查问卷
		function postSuper() {
			var checkniSuper = document.getElementsByName("checkniSuper");
			var qnTypes = document.getElementsByName("qnTypes");
			var poststatus = document.getElementsByName("status");
			var superListCategory_Str_arr = document.getElementsByName("superListCategory_Str");
			
			var firstSuperListCategory = superListCategory_Str_arr[0].value;
			var count = 0;
			var deliveryId = "";
			var qnType = "";
			var superListCategory = "";
			for (var i = 0; i < checkniSuper.length; i++) {
				if (checkniSuper[i].checked) {
					count++;
					var stat = poststatus[i].value;
					if (stat != 2) {
						alert("只能发布投放中的问卷,请重新选择");
						return;
					}
					qnType = qnTypes[i].value + "!" + qnType;
					deliveryId = checkniSuper[i].value + "!" + deliveryId;
					superListCategory = superListCategory_Str_arr[i].value + "!" + superListCategory;
				}

			}
			if (count == 0) {
				alert("请选择要发布的问卷");
				return;
			}
		 	 $.ajax({
					type : "post",
					dataType : "json",
					url : "${ctx}/platform/postSuper.do",
					data : {
						deliveryId : deliveryId,
						qnType : qnType,
						superListCategory : superListCategory
					},
					success : function(data) {
						alert(data.msg);
						if (data.success) {
							window.location.href = "${ctx}/platform/loadSuperList.do?superListCategory_Str_GO="+firstSuperListCategory;
						}
					},
					error : function(){
						showMessageDialog("网络异常");
					}
				});
		}
		//撤销超级问卷
		function revokeSuper() {
			var checkniSuper = document.getElementsByName("checkniSuper");
			var qnTypes = document.getElementsByName("qnTypes");
			var superListCategory_Str_arr = document.getElementsByName("superListCategory_Str");
			
			var firstSuperListCategory = superListCategory_Str_arr[0].value;
			var count = 0;
			var deliveryId = "";
			var qnType = "";
			var superListCategory = "";
			for (var i = 0; i < checkniSuper.length; i++) {
				if (checkniSuper[i].checked) {
					count++;
					deliveryId = checkniSuper[i].value + "!" + deliveryId;
					qnType = qnTypes[i].value + "!" + qnType;
					superListCategory = superListCategory_Str_arr[i].value +"!" + superListCategory;
				}

			}
			if (count == 0) {
				alert("请选择要撤销的问卷");
				return;
			}
			showConfirmDialog("确定要撤销吗?",function(check) {
						if (check) {
							$.ajax({
								type : "post",
								dataType : "json",
								url : "${ctx}/platform/revokeSuper.do",
								data : {
									deliveryId : deliveryId,
									qnType : qnType,
									superListCategory : superListCategory
								},
								success : function(data) {
									alert(data.msg);
									if (data.success) {
				window.location.href = "${ctx}/platform/loadSuperList.do?superListCategory_Str_GO="+firstSuperListCategory;
									}
								}
							});
						}
					});

		}
		//刷新超级问卷列表
		function refreshSuper() {
			var superListCategory_Str = document.getElementById("superListCategory_Str_GO").value;
			window.location.href = "${ctx}/platform/loadSuperList.do?superListCategory_Str_GO="+superListCategory_Str;

		}
		//更改置顶状态
		function changeIsTop(deliveryId, isTop, qnType, pageStatus,superListCategory) {
			if (pageStatus != 3) {
				alert("必须为发布状态才能调整位置！");
				return;
			}
			//当状态为置顶则取消置顶
			if (isTop == 1) {
				$.ajax({
					type : "post",
					dataType : "json",
					url : "${ctx}/platform/topCancelSuper.do",
					data : {
						deliveryId : deliveryId,
						qnType : qnType,
						superListCategory : superListCategory
					},
					success : function(data) {
						if (data.success) {
							alert("取消置顶成功");
							window.location.href = "${ctx}/platform/loadSuperList.do?superListCategory_Str_GO="+superListCategory;
						}
					}
				});
			}

			if (isTop == 2) {
				$.ajax({
					type : "post",
					dataType : "json",
					url : "${ctx}/platform/topSuper.do",
					data : {
						deliveryId : deliveryId,
						qnType : qnType,
						superListCategory : superListCategory
					},
					success : function(data) {
						if (data.success) {
							alert("置顶成功");
							window.location.href = "${ctx}/platform/loadSuperList.do?superListCategory_Str_GO="+superListCategory;
						}
					}
				});
			}
		}

//跳转到添加页面
function addSuper() {
	window.location.href = "${ctx}/platform/addSuperToList.do";
}
		
//查询
function searchSuperQuestionnaire() {
	var qnNameL = 22;
	var qnName = $("#qnName").val();
	if (qnName.trim().length > qnNameL) {
		alert("问卷名称不能超过" + qnNameL + "字符");
		return;
	}
	$('#pageListForm').submit();
}
		
//编辑
function editSuperList(showOrder, editStat) {
	if (editStat != 1) {
		showMessageDialog("只能编辑待发布状态的问卷,请先撤销问卷");
		return;
	}
	window.location.href = "${ctx}/platform/editSuperList.do?showOrder=" + showOrder;
}
		
//定时器
function timerSuper(){
	var checkBoxDelId_arr = document.getElementsByName("checkniSuper");   //投放id
	var pageStats_arr = document.getElementsByName("deleStat");   //页面状态
	var qnTypes_arr = document.getElementsByName("qnTypes");  //问卷类型
	var devStatus_arr = document.getElementsByName("status");  //投放状态
	var superListCategory_Str_arr = document.getElementsByName("superListCategory_Str");
	
	var count = 0;
	var checkBoxDelId = "";
	var qnType = "";
	var superListCategory ="";
	for(var i =0 ; i<checkBoxDelId_arr.length ; i++){
		if(checkBoxDelId_arr[i].checked){
			
			var pageStats = pageStats_arr[i].value;
			var devStatus = devStatus_arr[i].value;
			
			if(pageStats != 1){
				showMessageDialog("只能选择页面状态为待发布的问卷,请重新选择");
				return false;
			}else if(devStatus != 2){
				showMessageDialog("只能选择投放中问卷,请重新选择");
				return false;
			}
			
			superListCategory = superListCategory_Str_arr[i].value;
			checkBoxDelId = checkBoxDelId_arr[i].value;
			qnType = qnTypes_arr[i].value;
			
			count ++;
		}
		
	}
	
	if(count == 0){
		showMessageDialog("请选择至少一条记录");
		return false;
	}else if(count > 1){
		showMessageDialog("只能选择一条记录");
		return false;
	}
	
window.location.href = 
	"${ctx}/platform/jumpToTimerSuperPage.do?deliveryId="+checkBoxDelId+"&qnType="+qnType+"&superListCategory="+superListCategory;
	
}
		
		//删除
		function deleteSuperFromList() {
			var checkniSuper = document.getElementsByName("checkniSuper");
			var deleStats = document.getElementsByName("deleStat");
			var qnTypes = document.getElementsByName("qnTypes");
			var superListCategory_Str_arr = document.getElementsByName("superListCategory_Str");
			
			var firstSuperListCategory = superListCategory_Str_arr[0].value;
			var count = 0;
			var deliveryId = "";
			var qnType = "";
			var superListCategory = "";
			for (var i = 0; i < checkniSuper.length; i++) {
				if (checkniSuper[i].checked) {
					count++;
					var stat = deleStats[i].value;
					if (stat != 1) {
						alert("只能删除待发布状态的问卷,请重新选择");
						return;
					}
					deliveryId = checkniSuper[i].value + "!" + deliveryId;
					qnType = qnTypes[i].value + "!" + qnType;
					superListCategory = superListCategory_Str_arr[i].value + "!" + superListCategory;
				}
				
			}
			if (count == 0) {
				alert("请选择要删除的问卷");
				return;
			}
			showConfirmDialog(
					"确定要删除吗?",
					function(check) {
						if (check) {
							$.ajax({
								url : "${ctx}/platform/deleteSuperFromList.do",
								type : "post",
								data : {
									deliveryId : deliveryId,
									qnType : qnType,
									superListCategory : superListCategory
								},
								dataType : "json",
								success : function(data) {
									alert(data.msg);
									if (data.success) {
										window.location.href = "${ctx}/platform/loadSuperList.do?superListCategory_Str_GO="+firstSuperListCategory;
									}
								}
							});
						}
					});
		}
		
		//上移
		function moveUp(a, deliveryId, qnType, showOrder, pageStatus,superListCategory) {
			var index = a - 2;
			if (pageStatus != 3) {
				alert("必须为发布状态才能调整位置！");
				return;
			}
			if (a == 1) {
				alert("无法继续上移！");
				return;
			}
			var deliveryIds = document.getElementsByName("checkniSuper");
			var qnTypes = document.getElementsByName("qnTypes");
			var showOrders = document.getElementsByName("showorder");
			var deleStats = document.getElementsByName("deleStat");
			var superListCategory_arr = document.getElementsByName("superListCategory_Str");
			
			var lastDeliveryId = deliveryIds[index].value;
			var lastQnType = qnTypes[index].value;
			var lastShowOrder = showOrders[index].value;
			var lastPageStat = deleStats[index].value;
			var lastSuperListCategory = superListCategory_arr[index].value;
			
			if (lastPageStat == 1) {
				alert("无法继续上移！");
				return;
			}
				$.ajax({
					url : "${ctx}/platform/moveUpSuper.do",
					type : "post",
					data : {
						deliveryId : deliveryId,
						qnType : qnType,
						showOrder : showOrder,
						superListCategory : superListCategory,
						lastDeliveryId : lastDeliveryId,
						lastQnType : lastQnType,
						lastShowOrder : lastShowOrder,
						lastSuperListCategory : lastSuperListCategory
					},
					dataType : "json",
					success : function(data) {
						if (data.success) {
							window.location.href = "${ctx}/platform/loadSuperList.do?superListCategory_Str_GO="+superListCategory;
						}
					}
				});
		}
		
		//下移
		function moveDown(a, deliveryId, qnType, showOrder, pageStatus,superListCategory) {
			var index = a;
			if (pageStatus != 3) {
				alert("必须为发布状态才能调整位置！");
				return;
			}
			var deliveryIds = document.getElementsByName("checkniSuper");
			var qnTypes = document.getElementsByName("qnTypes");
			var showOrders = document.getElementsByName("showorder");
			var superListCategory_arr = document.getElementsByName("superListCategory_Str");
			
			if (index >= deliveryIds.length) {
				alert("无法继续下移");
				return;
			}
			var nextDeliveryId = deliveryIds[index].value;
			var nextQnType = qnTypes[index].value;
			var nextShowOrder = showOrders[index].value;
			var nextSuperListCategory = superListCategory_arr[index].value;
			
			$.ajax({
						url : "${ctx}/platform/moveDownSuper.do",
						type : "post",
						data : {
							deliveryId : deliveryId,
							qnType : qnType,
							showOrder : showOrder,
							superListCategory :superListCategory,
							nextDeliveryId : nextDeliveryId,
							nextQnType : nextQnType,
							nextShowOrder : nextShowOrder,
							nextSuperListCategory : nextSuperListCategory
						},
						dataType : "json",
						success : function(data) {
							//	alert(data.msg);
							if (data.success) {
								window.location.href = "${ctx}/platform/loadSuperList.do?superListCategory_Str_GO="+superListCategory;
							}
						}
					});
		}
	</script>
</body>

</html>