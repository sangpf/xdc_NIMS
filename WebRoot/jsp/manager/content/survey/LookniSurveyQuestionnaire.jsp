<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>预览问卷</title>
	<link href="res/css/index-a.css" rel="stylesheet">
	<link href="res/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet">
	</head>
	<body>
		<div class="main">
			<a href="${ctx}/platform/lookNiSurveyQuestion.do" >
				<div class="header">
					<div class="pic">
						<img style="width: 200px;height: 150px;" src="${imgurl }" />
					</div>
					<div class="title-info">
						<h3> 问卷名称 :  ${nsq.sqnname } </h3>
						<p class="time"><i class="fa fa-clock-o"></i> 创建时间 :  <fmt:formatDate value="${nsq.ctime }" pattern="yyyy-MM-dd"/> &nbsp; 提交时间 : <fmt:formatDate value="${nsq.stime }" pattern="yyyy-MM-dd"/> </p>
						<p><i class="fa fa-user"></i> 发布者名称 : ${nsq.publishername }</p>
					</div>
				</div>
				<div class="tags">
					<span>问卷标签 : </span>
					<span>${nsq.tag1id }</span>
					<span>${nsq.tag2id }</span>
					<span>${nsq.tag3id }</span>
					<span>${nsq.tag4id }</span>
					<span>${nsq.tag5id }</span>
				</div>
				<br/>
				<div class="introduce-info">
					<p>问卷简介 :  ${nsq.sqnsummary }</p>
				</div>
				<div class="introduce-info">
					<p>建议回收数量 :  ${nsq.recommandqty }</p>
				</div>		
				<div class="introduce-info">
					<p>题目数量 :  ${nsq.questionQty }</p>
				</div>						
				<div class="introduce-info">
					<p>问卷编辑 :  ${nsq.editor }</p>
				</div>
			</a>
		</div>	
	</body>
</html>
