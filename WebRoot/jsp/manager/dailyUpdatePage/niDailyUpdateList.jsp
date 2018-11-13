<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>每日三更</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
</head>

<body class="hold-transition skin-blue sidebar-mini">
	<div class="box box-primary">
		<table style="padding: 100px;margin: 10px;">
			<tr>
				<td style="width: 100px;padding: 3px;"><a href="${ctx}/platform/loadDaily3UpdateList.do">
					<button type="button" class="btn btn-block btn-primary" style="background:green">页面栏目</button></a></td>
				<td style="width: 100px;padding: 3px;"><a href="${ctx}/platform/loadSuperList.do">
					<button type="button" class="btn btn-block btn-primary">合作问卷</button></a></td>
				<td style="width: 100px;padding: 3px;"><a href="${ctx}/platform/loadAssessList.do">
					<button type="button" class="btn btn-block btn-primary">专业测评</button></a></td>
				<td style="width: 100px;padding: 3px;"><a href="${ctx}/platform/loadReportList.do">
					<button type="button" class="btn btn-block btn-primary">数字报告</button></a></td>
			</tr>
		</table>
		<!-- 内容部分 -->

		<!-- Main content -->
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" method="post" action="${ctx}/platform/loadDaily3UpdateList.do" id="pageListForm">
						<table style="border-spacing: 10px;border-collapse: separate;">
							<tr>
								<td><select class="form-control select-sm" name="choose"
									id="choose">
										<option value="0">问卷名称</option>
										<option value="1">投放Id</option>
								</select></td>
								<td><input id="qnName" name="qnName" type="text"
									class="form-control"></td>
								<td><label>页面状态</label></td>
								<td><select class="form-control" name="pageStatus" id="pageStatus">
										<option value="">不限</option>
										<option value="1">待发布</option>
										<option value="2">定时发布</option>
										<option value="3">已发布</option>
								</select></td>
								
								<td><label>&nbsp;栏目名称</label></td>
								<td style="width: 160px;">
									<select class="form-control select-sm" style="font-size: 16px;height: 35px;" name="superListCategory_Str_GO" id="superListCategory_Str_GO">
										<c:forEach items="${daily3update_CategoryList }" var="daily3update_Category">
											<option 
												<c:if test="${superListCategory_Str == daily3update_Category.categoryKey}">selected="selected"</c:if> 
													value="${daily3update_Category.categoryKey}">${daily3update_Category.categoryValue}
											</option>
										</c:forEach>
									</select>
								</td>
								
								<td><span class="input-group-btn">
										<button type="button"
											onclick="searchDaily3UpdateQuestionnaire()"
											class="btn btn-info btn-flat">查询</button>
								</span></td>
							</tr>
						</table>
				</form>
				</div>
				<div class="panel-body">
					<div class="button-group">
					<button type="button" onclick="addDaily3Update()" class="btn btn-default btn-sm">添加</button>
					<button type="button" onclick="postDaily3Update()" class="btn btn-default btn-sm">发布</button>
					<button type="button" onclick="revokeDaily3Update()" class="btn btn-default btn-sm">撤销</button>
					<button type="button" onclick="deleteDaily3UpdateFromList()" class="btn btn-default btn-sm">删除</button>
					<button type="button" onclick="timerDaily3Update()" class="btn btn-default btn-sm">定时器</button>
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
							<th>选择</th>
							<th>投放ID/文章ID</th>
							<th>类型</th>
							<th>栏目名称</th>
							<th>投放/文章标题</th>
							<th>页面状态</th>
							<!-- <th>投放状态</th> -->
							<th>发布时间</th>
							<!-- <th>更新时间</th> -->
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
		<c:forEach items="${niDaily3UpdateList}" var="daily3updateSurvey" varStatus="n">
			<!-- 不置顶 -->
			<c:if test="${daily3updateSurvey.isTop==2}">
				<c:if test="${daily3updateSurvey.pageStatus!=3}">
				<tr style="background:#B0E0E6">
					<td><c:if test="${daily3updateSurvey.isTop==2}">${n.index+1}</c:if>
						<c:if test="${daily3updateSurvey.isTop==1}">
						</c:if></td>
					<td>
					<input type="checkbox" id="checkniDaily3Update" name="checkniDaily3Update" value="${daily3updateSurvey.itemId }">
					<input type="hidden" value="${daily3updateSurvey.pageStatus}" name="upPageStatus" id="upPageStatus">
					<input type="hidden" value="${daily3updateSurvey.qnType}" name="qnTypes" id="qnTypes">
					<input type="hidden" value="${daily3updateSurvey.itemStatus}" name="status" id="status"> 
					<input type="hidden" value="${daily3updateSurvey.showOrder}" name="showorder" id="showorder">
					<input type="hidden" value="${daily3updateSurvey.reportId }" name="reportId" id="reportId">
					<input type="hidden" value="${daily3updateSurvey.tweetId }" name="tweetId" id="tweetId">
					<input type="hidden" value="${daily3updateSurvey.qnListCategory }" name="qnListCategory" id="qnListCategory">
					</td>
									<td>
										<c:if test="${daily3updateSurvey.deliveryId != 0}">${daily3updateSurvey.deliveryId }</c:if>
										<c:if test="${daily3updateSurvey.reportId != null and daily3updateSurvey.reportId != 0 }">${daily3updateSurvey.reportId}</c:if>
										<c:if test="${daily3updateSurvey.tweetId != null and daily3updateSurvey.tweetId != 0}">${daily3updateSurvey.tweetId}</c:if>
									</td>
										<td>
											<c:if test="${daily3updateSurvey.qnType==1}">调查</c:if>
											<c:if test="${daily3updateSurvey.qnType==2}">测评</c:if> 
											<c:if test="${daily3updateSurvey.qnType==3}">投票</c:if>
											<c:if test="${daily3updateSurvey.reportId != null and daily3updateSurvey.reportId != 0}">报告</c:if>
											<c:if test="${daily3updateSurvey.tweetId != null and daily3updateSurvey.tweetId != 0}">文章</c:if>
										</td>
										<td>
											<c:if test="${daily3updateSurvey.qnListCategory == 'schoolQnList' }">校园生活</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'societyQnList' }">社会镜头</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'relationQnList' }">亲密关系</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'emotionQnList' }">我的情绪</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'brainQnList' }">最强大脑</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'futureQnList' }">未来规划</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'scienceQnList' }">懂点科学</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'healthyQnList' }">生理健康</c:if>
											
											<c:if test="${daily3updateSurvey.qnListCategory == 'floatLatestList' }">最新</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'floatClassicList' }">经典</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'floatTweetList' }">趣文</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'floatVideoList' }">视频</c:if>
													
											<c:if test="${daily3updateSurvey.qnListCategory == 'awardQnList' }">有奖问卷</c:if>
														
											<c:if test="${daily3updateSurvey.qnListCategory == 'professialAssessQnList' }">专业测评</c:if>
										</td>
										<td>
					                  		<div style="width: 250px;white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
					                  			${daily3updateSurvey.showTitle }
					                  		</div>									
										</td>
										<td>
											<c:if test="${daily3updateSurvey.pageStatus== 2}">定时发布</c:if>
											<c:if test="${daily3updateSurvey.pageStatus == 1}">待发布</c:if>
											<c:if test="${daily3updateSurvey.pageStatus == 3}">已发布</c:if>
										</td>

										<td>
											<fmt:formatDate value="${daily3updateSurvey.pTime }" type="both" />
										</td>
										<td>
											<button type="button"
												onclick="editDaily3UpdateList(${daily3updateSurvey.itemId },${daily3updateSurvey.pageStatus})"
												class="btn btn-default btn-sm">编辑</button>
											<button type="button"
												onclick="moveUp(${n.index+1},${daily3updateSurvey.itemId },${daily3updateSurvey.showOrder },${daily3updateSurvey.pageStatus},'${daily3updateSurvey.qnListCategory}')"
												class="btn btn-default btn-sm">上移</button>
											<button type="button"
												onclick="moveDown(${n.index+1},${daily3updateSurvey.itemId },${daily3updateSurvey.showOrder },${daily3updateSurvey.pageStatus},'${daily3updateSurvey.qnListCategory}')"
												class="btn btn-default btn-sm">下移</button>
											<button type="button" id="top"
												onclick="changeIsTop(${daily3updateSurvey.itemId},${daily3updateSurvey.isTop},${daily3updateSurvey.pageStatus},'${daily3updateSurvey.qnListCategory}')"
												class="btn btn-default btn-sm">
												<c:if test="${daily3updateSurvey.isTop==2}">置顶</c:if>
												<c:if test="${daily3updateSurvey.isTop==1}">取消</c:if>
											</button>
										</td>
							</tr>
					</c:if>
					<c:if test="${daily3updateSurvey.pageStatus==3}">
						<tr>
						<td><c:if test="${daily3updateSurvey.isTop==2}">${n.index+1}</c:if>
							<c:if test="${daily3updateSurvey.isTop==1}">
							</c:if></td>
						<td>
						<input type="checkbox" id="checkniDaily3Update" name="checkniDaily3Update" value="${daily3updateSurvey.itemId }"> 
						<input type="hidden" value="${daily3updateSurvey.pageStatus}" name="upPageStatus" id="upPageStatus">
						<input type="hidden" value="${daily3updateSurvey.qnType}" name="qnTypes" id="qnTypes">
						<input type="hidden" value="${daily3updateSurvey.itemStatus}" name="status" id="status">
						<input type="hidden" value="${daily3updateSurvey.showOrder}" name="showorder" id="showorder">
						<input type="hidden" value="${daily3updateSurvey.reportId }" name="reportId" id="reportId">
						<input type="hidden" value="${daily3updateSurvey.tweetId }" name="tweetId" id="tweetId">						
						<input type="hidden" value="${daily3updateSurvey.qnListCategory }" name="qnListCategory" id="qnListCategory">
						</td>
									<td>
										<c:if test="${daily3updateSurvey.deliveryId != 0}">${daily3updateSurvey.deliveryId }</c:if>
										<c:if test="${daily3updateSurvey.reportId != null and daily3updateSurvey.reportId != 0 }">${daily3updateSurvey.reportId}</c:if>
										<c:if test="${daily3updateSurvey.tweetId != null and daily3updateSurvey.tweetId != 0}">${daily3updateSurvey.tweetId}</c:if>
									</td>
										<td>
											<c:if test="${daily3updateSurvey.qnType==1}">调查</c:if>
											<c:if test="${daily3updateSurvey.qnType==2}">测评</c:if> 
											<c:if test="${daily3updateSurvey.qnType==3}">投票</c:if>
											<c:if test="${daily3updateSurvey.reportId != null and daily3updateSurvey.reportId != 0}">报告</c:if>
											<c:if test="${daily3updateSurvey.tweetId != null and daily3updateSurvey.tweetId != 0}">文章</c:if>
										</td>
										<td>
											<c:if test="${daily3updateSurvey.qnListCategory == 'schoolQnList' }">校园生活</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'societyQnList' }">社会镜头</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'relationQnList' }">亲密关系</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'emotionQnList' }">我的情绪</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'brainQnList' }">最强大脑</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'futureQnList' }">未来规划</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'scienceQnList' }">懂点科学</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'healthyQnList' }">生理健康</c:if>
											
											<c:if test="${daily3updateSurvey.qnListCategory == 'floatLatestList' }">最新</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'floatClassicList' }">经典</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'floatTweetList' }">趣文</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'floatVideoList' }">视频</c:if>
													
											<c:if test="${daily3updateSurvey.qnListCategory == 'awardQnList' }">有奖问卷</c:if>
														
											<c:if test="${daily3updateSurvey.qnListCategory == 'professialAssessQnList' }">专业测评</c:if>
										</td>										
										<td>
					                  		<div style="width: 250px;white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
					                  			${daily3updateSurvey.showTitle }
					                  		</div>										
										</td>
										<td>
											<c:if test="${daily3updateSurvey.pageStatus== 2}">定时发布</c:if>
											<c:if test="${daily3updateSurvey.pageStatus == 1}">待发布</c:if>
											<c:if test="${daily3updateSurvey.pageStatus == 3}">已发布</c:if>
										</td>

										<td><fmt:formatDate value="${daily3updateSurvey.pTime }" type="both" /></td>
										<td>
											<button type="button"
												onclick="editDaily3UpdateList(${daily3updateSurvey.itemId },${daily3updateSurvey.pageStatus})"
												class="btn btn-default btn-sm">编辑</button>
											<button type="button"
												onclick="moveUp(${n.index+1},${daily3updateSurvey.itemId },${daily3updateSurvey.showOrder },${daily3updateSurvey.pageStatus},'${daily3updateSurvey.qnListCategory}')"
												class="btn btn-default btn-sm">上移</button>
											<button type="button"
												onclick="moveDown(${n.index+1},${daily3updateSurvey.itemId },${daily3updateSurvey.showOrder },${daily3updateSurvey.pageStatus},'${daily3updateSurvey.qnListCategory}')"
												class="btn btn-default btn-sm">下移</button>
											<button type="button" id="top"
												onclick="changeIsTop(${daily3updateSurvey.itemId},${daily3updateSurvey.isTop},${daily3updateSurvey.pageStatus},'${daily3updateSurvey.qnListCategory}')"
												class="btn btn-default btn-sm">
												<c:if test="${daily3updateSurvey.isTop==2}">置顶</c:if>
												<c:if test="${daily3updateSurvey.isTop==1}">取消</c:if>
											</button>
										</td>
									</tr>
						</c:if>
					</c:if>
					<!--  置顶  -->
					<c:if test="${daily3updateSurvey.isTop==1}">
						<tr style="background:#DDA0DD">
							<td><c:if test="${daily3updateSurvey.isTop==2}">${n.index+1}</c:if>
								<c:if test="${daily3updateSurvey.isTop==1}"><i class="fa fa-fw fa-chevron-up"></i></c:if></td>
							<td>
								<input type="checkbox" id="checkniDaily3Update" name="checkniDaily3Update" value="${daily3updateSurvey.itemId }" > 
								<input type="hidden" value="${daily3updateSurvey.pageStatus}" name="upPageStatus" id="upPageStatus">
								<input type="hidden" value="${daily3updateSurvey.qnType}" name="qnTypes" id="qnTypes">
								<input type="hidden" value="${daily3updateSurvey.itemStatus}" name="status" id="status">
								<input type="hidden" value="${daily3updateSurvey.showOrder}" name="showorder" id="showorder">
								<input type="hidden" value="${daily3updateSurvey.reportId }" name="reportId" id="reportId">
								<input type="hidden" value="${daily3updateSurvey.tweetId }" name="tweetId" id="tweetId">								
								<input type="hidden" value="${daily3updateSurvey.qnListCategory }" name="qnListCategory" id="qnListCategory">
							</td>
									<td>
										<c:if test="${daily3updateSurvey.deliveryId != 0}">${daily3updateSurvey.deliveryId }</c:if>
										<c:if test="${daily3updateSurvey.reportId != null and daily3updateSurvey.reportId != 0 }">${daily3updateSurvey.reportId}</c:if>
										<c:if test="${daily3updateSurvey.tweetId != null and daily3updateSurvey.tweetId != 0}">${daily3updateSurvey.tweetId}</c:if>
									</td>
									<td>
										<c:if test="${daily3updateSurvey.qnType==1}">调查</c:if>
										<c:if test="${daily3updateSurvey.qnType==2}">测评</c:if> 
										<c:if test="${daily3updateSurvey.qnType==3}">投票</c:if>
										<c:if test="${daily3updateSurvey.reportId != null and daily3updateSurvey.reportId != 0}">报告</c:if>
										<c:if test="${daily3updateSurvey.tweetId != null and daily3updateSurvey.tweetId != 0}">文章</c:if>
									</td>
										<td>
											<c:if test="${daily3updateSurvey.qnListCategory == 'schoolQnList' }">校园生活</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'societyQnList' }">社会镜头</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'relationQnList' }">亲密关系</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'emotionQnList' }">我的情绪</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'brainQnList' }">最强大脑</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'futureQnList' }">未来规划</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'scienceQnList' }">懂点科学</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'healthyQnList' }">生理健康</c:if>
											
											<c:if test="${daily3updateSurvey.qnListCategory == 'floatLatestList' }">最新</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'floatClassicList' }">经典</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'floatTweetList' }">趣文</c:if>
											<c:if test="${daily3updateSurvey.qnListCategory == 'floatVideoList' }">视频</c:if>
													
											<c:if test="${daily3updateSurvey.qnListCategory == 'awardQnList' }">有奖问卷</c:if>
														
											<c:if test="${daily3updateSurvey.qnListCategory == 'professialAssessQnList' }">专业测评</c:if>
										</td>									
									<td>
				                  		<div style="width: 250px;white-space: nowrap;text-overflow:ellipsis;overflow: hidden;">
				                  			${daily3updateSurvey.showTitle }
				                  		</div>
									</td>
									<td>
										<c:if test="${daily3updateSurvey.pageStatus== 2}">定时发布</c:if>
										<c:if test="${daily3updateSurvey.pageStatus == 1}">待发布</c:if>
										<c:if test="${daily3updateSurvey.pageStatus == 3}">已发布</c:if>
									</td>

									<td><fmt:formatDate value="${daily3updateSurvey.pTime }" type="both" /></td>
									<td>
										<button type="button"
											onclick="editDaily3UpdateList(${daily3updateSurvey.itemId },${daily3updateSurvey.pageStatus})"
											class="btn btn-default btn-sm">编辑</button>
										<button type="button"
											onclick="moveUp(${n.index+1},${daily3updateSurvey.itemId },${daily3updateSurvey.showOrder },${daily3updateSurvey.pageStatus},'${daily3updateSurvey.qnListCategory}')"
											class="btn btn-default btn-sm">上移</button>
										<button type="button"
											onclick="moveDown(${n.index+1},${daily3updateSurvey.itemId },${daily3updateSurvey.showOrder },${daily3updateSurvey.pageStatus},'${daily3updateSurvey.qnListCategory}')"
											class="btn btn-default btn-sm">下移</button>
										<button type="button" id="top"
											onclick="changeIsTop(${daily3updateSurvey.itemId},${daily3updateSurvey.isTop},${daily3updateSurvey.pageStatus},'${daily3updateSurvey.qnListCategory}')"
											class="btn btn-default btn-sm">
											<c:if test="${daily3updateSurvey.isTop==2}">置顶</c:if>
											<c:if test="${daily3updateSurvey.isTop==1}">取消</c:if>
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

<div class="modal fade" id="timer_Modal"  role="dialog" aria-hidden="true">
    	 <div class="modal-dialog" >
    	 	<div class="modal-content" >
    	 	
    	 		<div class="modal-header">
            		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
           			<h4 class="modal-title" id="myModalLabel">选择发布时间</h4>
           		</div>
           		
           		<div class="modal-body">
	           		<div class="form-group">
	 					<label class="control-label col-sm-2" >时间</label>
	  					<div class="col-sm-9">
	  						<input type="text" class="form-control " id="ruleName_add">
						</div>
					</div>
					
    	 		</div>
    	 	
    	 		<div class="modal-footer">
            		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            		<button type="button" onclick="lotteryPreferAdd_Save();" class="btn btn-primary">保存</button>
         		</div>
    	 </div>
    </div>
</div>
	<!-- 内容部分结束 -->

	<div class="control-sidebar-bg"></div>
	<script src="${ctx}/static/js/moment.min.js"></script>
	<script src="${ctx}/js/validate.js"></script>
	<script>
		//发布
		function postDaily3Update() {
			
			var listCategory = document.getElementById("superListCategory_Str_GO").value;
			
			var checkniDaily3Update = document.getElementsByName("checkniDaily3Update");
			var count = 0;
			var itemIds = "";
			for (var i = 0; i < checkniDaily3Update.length; i++) {
				if (checkniDaily3Update[i].checked) {
					count++;
					itemIds = checkniDaily3Update[i].value + "!" + itemIds;
				}

			}
			if (count == 0) {
				alert("请选择要发布的内容");
				return;
			}
			$.ajax({
					type : "post",
					dataType : "json",
					url : "${ctx}/platform/postDaily3Update.do",
					data : {
						itemIds : itemIds
					},
					success : function(data) {
						if (data.success) {
							alert(data.msg);
							window.location.href = "${ctx}/platform/loadDaily3UpdateList.do?superListCategory_Str_GO="+listCategory;
						}
					}
				});
		}
		//撤销超级问卷
		function revokeDaily3Update() {
			var checkniDaily3Update = document.getElementsByName("checkniDaily3Update");
			var count = 0;
			var itemIds = "";
			for (var i = 0; i < checkniDaily3Update.length; i++) {
				if (checkniDaily3Update[i].checked) {
					count++;
					itemIds = checkniDaily3Update[i].value + "!" + itemIds;
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
									url : "${ctx}/platform/revokeDaily3Update.do",
									data : {
										itemIds : itemIds
									},
									success : function(data) {
										if (data.success) {
											alert(data.msg);
											window.location.href = "${ctx}/platform/loadDaily3UpdateList.do";
										}
									}
								});
						}
					});

		}
		
		//定时器
		function timerDaily3Update(){
			
			var checkniDaily3Update_arr = document.getElementsByName("checkniDaily3Update");
			var pageStat_arr = document.getElementsByName("upPageStatus");
			
			var count = 0;
			var itemId = "";
			for(var i =0 ; i<checkniDaily3Update_arr.length ; i++){
				if(checkniDaily3Update_arr[i].checked){
					count ++;
					
					itemId = checkniDaily3Update_arr[i].value;
					
					var pageStat = pageStat_arr[i].value;
					
					if(pageStat != 1){
						showMessageDialog("只能选择页面状态为待发布的问卷,请重新选择");
						return false;
					}
				}
			}
			
			if(count == 0){
				showMessageDialog("请选择至少一条记录");
				return false;
			}else if(count > 1){
				showMessageDialog("只能选择一条记录");
				return false;
			}
			
			window.location.href = "${ctx}/platform/jumpDaily3UpdateTimerPage.do?itemId="+itemId;
			
		}
		
		//更改置顶状态
		function changeIsTop(itemId, isTop, pageStatus,qnListCategory) {
			if (pageStatus != 3) {
				alert("必须为发布状态才能调整位置！");
				return;
			}
			//当状态为置顶则取消置顶
			if (isTop == 1) {
				$.ajax({
					type : "post",
					dataType : "json",
					url : "${ctx}/platform/topCancelDaily3Update.do",
					data : {
						itemId : itemId
					},
					success : function(data) {
						if (data.success) {
							//alert("取消置顶成功");
							window.location.href = "${ctx}/platform/loadDaily3UpdateList.do?superListCategory_Str_GO="+qnListCategory;
						}
					}
				});
			}

			if (isTop == 2) {
				$.ajax({
						type : "post",
						dataType : "json",
						url : "${ctx}/platform/topDaily3Update.do",
						data : {
							itemId : itemId
						},
						success : function(data) {
							if (data.success) {
								//alert("置顶成功");
								window.location.href = "${ctx}/platform/loadDaily3UpdateList.do?superListCategory_Str_GO="+qnListCategory;
							}
						}
					});
			}
		}

		//跳转到添加页面
		function addDaily3Update() {
			window.location.href = "${ctx}/platform/addDaily3UpdateToList.do";
		}
		//查询
		function searchDaily3UpdateQuestionnaire() {
			var qnNameL = 22;
			var qnName = $("#qnName").val();
			if (qnName.trim().length > qnNameL) {
				alert("问卷名称不能超过" + qnNameL + "字符");
				return;
			}
			$('#pageListForm').submit();
		}

		//编辑
		function editDaily3UpdateList(itemId, pageStatus) {
			if (pageStatus != 1) {
				alert("只能编辑待发布状态的问卷");
				return false;
			}
			window.location.href = "${ctx}/platform/editDaily3UpdateList.do?itemId=" + itemId;
		}
		//删除
		function deleteDaily3UpdateFromList() {
			var checkniDaily3Update = document.getElementsByName("checkniDaily3Update");  //复选框  内容为 id
			var upPageStatus = document.getElementsByName("upPageStatus");   //页面状态
			var count = 0;
			var itemIds = "";
			for (var i = 0; i < checkniDaily3Update.length; i++) {
				if (checkniDaily3Update[i].checked) {
					//判断页面状态 
					var stat = upPageStatus[i].value;
					if (stat != 1) {
						showMessageDialog("只能删除待发布状态的问卷,请重新选择");
						return;
					}
				}
				if (checkniDaily3Update[i].checked) {
					//拼接投放id
					count++;
					itemIds = checkniDaily3Update[i].value + "!" + itemIds;
				}
			}
			if (count == 0) {
				alert("请选择要删除的问卷");
				return;
			}
			showConfirmDialog("确定要删除吗?",function(check) {
						if (check) {
							$.ajax({
									url : "${ctx}/platform/deleteDaily3UpdateFromList.do",
									type : "post",
									data : {
										itemIds : itemIds
									},
									dataType : "json",
									success : function(data) {
										alert(data.msg);
										if (data.success) {
											window.location.href = "${ctx}/platform/loadDaily3UpdateList.do";
										}
									}
								});
							}
					});
		}
		//上移
		function moveUp(a, itemId, showOrder, pageStatus,qnListCategory) {
			var index = a - 2;
			if (pageStatus != 3) {
				alert("必须为发布状态才能调整位置！");
				return;
			}
			if (a == 1) {
				alert("无法继续上移！");
				return;
			}
			var itemIds = document.getElementsByName("checkniDaily3Update");
			var showOrders = document.getElementsByName("showorder");
			var upPageStatus = document.getElementsByName("upPageStatus");
			var lastItemId = itemIds[index].value;
			var lastShowOrder = showOrders[index].value;
			var lastPageStat = upPageStatus[index].value;
			if (lastPageStat == 1) {
				alert("无法继续上移！");
				return;
			}
				$.ajax({
						url : "${ctx}/platform/moveUpDaily3Update.do",
						type : "post",
						data : {
							itemId : itemId,
							showOrder : showOrder,
							lastItemId : lastItemId,
							lastShowOrder : lastShowOrder
						},
						dataType : "json",
						success : function(data) {
							if (data.success) {
								window.location.href = "${ctx}/platform/loadDaily3UpdateList.do?superListCategory_Str_GO="+qnListCategory;
							}
						}
					});
		}
		function moveDown(a, itemId, showOrder, pageStatus,qnListCategory) {
			var index = a;
			if (pageStatus != 3) {
				alert("必须为发布状态才能调整位置！");
				return;
			}
			var itemIds = document.getElementsByName("checkniDaily3Update");
			var showOrders = document.getElementsByName("showorder");
			if (index >= itemIds.length) {
				alert("无法继续下移");
				return;
			}
			var nextitemId = itemIds[index].value;
			var nextShowOrder = showOrders[index].value;
			$.ajax({
				url : "${ctx}/platform/moveDownDaily3Update.do",
				type : "post",
				data : {
					itemId : itemId,
					showOrder : showOrder,
					nextitemId : nextitemId,
					nextShowOrder : nextShowOrder
				},
				dataType : "json",
				success : function(data) {
					if (data.success) {
						window.location.href = "${ctx}/platform/loadDaily3UpdateList.do?superListCategory_Str_GO="+qnListCategory;
					}
				}
			});
		}
	</script>
</body>

</html>