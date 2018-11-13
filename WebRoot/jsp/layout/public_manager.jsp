<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<title><sitemesh:title /></title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="${ctx}/BS/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx}/BS/plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet" href="${ctx}/BS/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="${ctx}/BS/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="${ctx}/BS/plugins/iCheck/flat/blue.css">
<link rel="stylesheet" href="${ctx}/BS/plugins/morris/morris.css">
<link rel="stylesheet" href="${ctx}/BS/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<link rel="stylesheet" href="${ctx}/BS/plugins/datepicker/datepicker3.css">
<link rel="stylesheet" href="${ctx}/BS/plugins/daterangepicker/daterangepicker-bs3.css">
<link rel="stylesheet" href="${ctx}/BS/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<link rel="stylesheet" href="${ctx}/BS/plugins/jquery-ui-1.11.4.custom/jquery-ui.min.css">
<link rel="stylesheet" href="${ctx}/BS/plugins/jquery-ui-1.11.4.custom/jquery-ui.structure.css">

<link rel="stylesheet" href="${ctx}/js/sang.css">

<link rel="shortcut icon" href="${ctx}/static/images/small-logo.png">
<style>   
.mb10 {
	margin-bottom: 10px;
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

.content-wrapper {
	background-color: #222d32;
}
.rightmenu{
	background:#eee;
	margin-left:240px;
	padding:0px;
	overflow-x:auto;
}

#back_to_top{
    position: fixed;
    bottom: 100px;
    width: 80px;
    height: 80px;
    background-color: #999;
    z-index: 9999;
    right: 100px;
    text-align: center;
    line-height: 80px;
    color: white;
    border-radius: 8px;
 }
 
</style>
</head>

<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<header class="main-header">
			<!-- Logo -->
			<a href="#" class="logo">
				<span class="logo-lg">超级调查系统</span>
			</a>
			
			<nav class="navbar navbar-static-top">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas">
				 <span> &nbsp;&nbsp;&nbsp;&nbsp; <sitemesh:title default="未知页面"/></span>
				</a>
				
				 <div class="navbar-custom-menu">
				      <ul class="nav navbar-nav">
				        <li class="dropdown messages-menu">
							<a href="#0" id="logout">
				            <i style="font-size: 18px;">注销</i>
				          </a>
				        </li>
				        
				      </ul>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    </div>
			</nav>
		</header>
		
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
		  <section class="sidebar">
			<ul class="sidebar-menu" id="primary-sidebar">
				<li class="treeview">
					<a href="#" data-id="1"><i class="fa fa-share"></i><span>内容管理</span> <i class="fa fa-angle-left pull-right"></i></a>
					<ul class="treeview-menu">
						<li><a href="${ctx}/platform/NiSurveyQuestionnaireList.do" data-id="11"><i class="fa fa-circle-o"></i>  调查</a></li>
						<li><a href="${ctx}/platform/niAssessQuestionnaireList.do" data-id="13"><i class="fa fa-circle-o"></i>  测评</a></li>
						<li><a href="${ctx}/platform/NiVoteQuestionnaireList.do" data-id="12"><i class="fa fa-circle-o"></i>  投票</a></li>
						<li><a href="${ctx}/platform/NiReportList.do" data-id="14"><i class="fa fa-circle-o"></i>  报告</a></li>
						<li><a href="${ctx}/platform/findAllTweets.do" data-id="31"><i class="fa fa-circle-o"></i>  推文</a></li>
					</ul>
				</li>

				<li class="treeview">
					<a href="#" data-id="2"><i class="fa fa-share"></i> <span>投放管理</span><i class="fa fa-angle-left pull-right"></i></a>
					<ul class="treeview-menu">
					 	<li>
					 		<a href="#" data-id="21"><i class="fa fa-circle-o"></i> 调查 <i class="fa fa-angle-left pull-right"></i></a>
						   	<ul class="treeview-menu">
						     	<li><a href="${ctx}/platform/niSurveyDeliveryWanxList.do?channel=1" data-id="211"><i class="fa fa-circle-o"></i> 玩校</a></li>
						      	<li><a href="${ctx}/platform/niSurveyDeliveryWanxList.do?channel=2"><i class="fa fa-circle-o" data-id="212"></i> 微信</a></li>
					    	</ul>
				  		</li>
              			<li>
                			<a href="#" data-id="22"><i class="fa fa-circle-o"></i> 测评 <i class="fa fa-angle-left pull-right"></i></a>
			                <ul class="treeview-menu">
								<li><a href="${ctx}/platform/niAssessDeliveryWanxList.do?channel=1" data-id="221"><i class="fa fa-circle-o"></i> 玩校</a></li>
								<li><a href="${ctx}/platform/niAssessDeliveryWanxList.do?channel=2"><i class="fa fa-circle-o" data-id="222"></i> 微信</a></li>
			                </ul>
            			</li>
            			<li>
                			<a href="#" data-id="23"><i class="fa fa-circle-o"></i> 投票 <i class="fa fa-angle-left pull-right"></i></a>
			                <ul class="treeview-menu">
								<li><a href="${ctx}/platform/niVoteDeliveryWanxList.do?channel=1" data-id="231"><i class="fa fa-circle-o"></i> 玩校</a></li>
								<li><a href="${ctx}/platform/niVoteDeliveryWanxList.do?channel=2"><i class="fa fa-circle-o" data-id="232"></i> 微信</a></li>
			                </ul>
              			</li>
            		</ul>
          		</li>
          		
          		<li class="treeview">
					<a href="#" data-id="3"><i class="fa fa-share"></i> <span>页面管理</span> <i class="fa fa-angle-left pull-right"></i></a>
					<ul class="treeview-menu">
						<li>
							<a href="#" data-id="31"><i class="fa fa-circle-o"></i>完美校园<i class="fa fa-angle-left pull-right"></i></a>
							<ul class="treeview-menu">
								<li>
									<a href="#" data-id="743"> <i class="fa fa-circle-o"></i>广告<i class="fa fa-angle-left pull-right"></i></a>
									<ul class="treeview-menu">
										<li><a href="${ctx}/platform/loadCarouselList.do" data-id="311"><i class="fa fa-circle-o"></i>轮播图</a></li>
										<li><a href="${ctx}/platform/loadBannerList.do" data-id="312"><i class="fa fa-circle-o"></i>Banner</a></li>
									</ul>
								</li>
								<li><a href="${ctx}/platform/loadDaily3UpdateList.do?superListCategory_Str_GO=floatLatestList " data-id="321"><i class="fa fa-circle-o"></i>页面栏目</a></li>
								<li><a href="${ctx}/platform/loadSuperList.do?superListCategory_Str_GO=awardQnList" data-id="322"><i class="fa fa-circle-o"></i>合作问卷</a></li>
								<li><a href="${ctx}/platform/loadAssessList.do" data-id="323"><i class="fa fa-circle-o"></i>专业测评</a></li>
								<li><a href="${ctx}/platform/loadReportList.do" data-id="324"><i class="fa fa-circle-o"></i>数字报告</a></li>
							</ul>
						</li>
						<li>
							<a href="#" data-id="32"><i class="fa fa-circle-o"></i>微信<i class="fa fa-angle-left pull-right"></i></a>
							<ul class="treeview-menu">
								<li>
									<a href="#" data-id="743"> <i class="fa fa-circle-o"></i>广告<i class="fa fa-angle-left pull-right"></i></a>
									<ul class="treeview-menu">
										<li><a href="${ctx}/platform/loadCarouselList.do" data-id="311"><i class="fa fa-circle-o"></i>轮播图</a></li>
									</ul>
								</li>
								<li><a href="${ctx}/light/listPage.do?channelId=2" data-id="323"><i class="fa fa-circle-o"></i>通用页面</a></li>
							</ul>
						</li>
					</ul>
				</li>

				<li class="treeview">
					<a href="#" data-id="4"><i class="fa fa-share"></i> <span>营销管理</span><i class="fa fa-angle-left pull-right"></i></a>
					<ul class="treeview-menu">
						<li>
							<a href="#" data-id="41"> <i class="fa fa-circle-o"></i>奖励管理<i class="fa fa-angle-left pull-right"></i></a>
							<ul class="treeview-menu">
								<li><a href="${ctx}/platform/awardPool.do" data-id="411"><i class="fa fa-circle-o"></i>奖池管理</a></li>
								<li><a href="${ctx}/platform/awardList.do" data-id="412"><i class="fa fa-circle-o"></i>奖品管理</a></li>
								<li><a href="${ctx}/platform/lotteryList.do" data-id="413"><i class="fa fa-circle-o"></i>抽奖管理</a></li>
								<li><a href="${ctx}/platform/awardPayList.do?awardType=1" data-id="414"><i class="fa fa-circle-o"></i>发放管理</a></li>
							</ul>
						</li>
						
								<li><a href="${ctx}/platform/loadAdInfoList.do" data-id="414"><i class="fa fa-circle-o"></i>广告管理</a></li>		
						
					</ul>
				</li>
				
				<li class="treeview">
					<a href="#" data-id="6"><i class="fa fa-share"></i><span> 用户管理</span> <i class="fa fa-angle-left pull-right"></i></a>
					<ul class="treeview-menu">
						<li>
							<a href="" data-id="61"><i class="fa fa-circle-o"></i>用户信息管理<i class="fa fa-angle-left pull-right"></i></a>
							<ul class="treeview-menu">
								<li><a href="${ctx}/platform/showUserInfoList.do" data-id="611"><i class="fa fa-circle-o"></i>用户信息列表</a></li>
							</ul>
						</li>
						<li><a href="${ctx}/platform/authorList.do" data-id="62"><i class="fa fa-circle-o"></i>作者管理</a></li>
						<li><a href="" data-id="62"><i class="fa fa-circle-o"></i>用户标签管理</a></li>
						<li><a href="" data-id="63"><i class="fa fa-circle-o"></i>用户评论管理</a></li>
						<li><a href="${ctx}/platform/niImportData.do" data-id="64"><i class="fa fa-circle-o"></i>玩校数据库导入</a></li>
					</ul>
				</li>

		        <!-- 统计管理 -->
		        <li class="treeview">
		            <a href="#" data-id="7"><i class="fa fa-share"></i><span> 统计管理</span> <i class="fa fa-angle-left pull-right"></i></a>
		            <ul class="treeview-menu">
						<li><a href="${ctx}/platform/niAdStatisticsList.do" data-id="71"> <i class="fa fa-circle-o"></i>广告统计</a></li>
						<li><a href="${ctx}/platform/niDeliveryStatisticsList.do" data-id="72"> <i class="fa fa-circle-o"></i>投放统计</a></li>
						<li><a href="${ctx}/platform/niReportStatisticsList.do" data-id="73"> <i class="fa fa-circle-o"></i>报告统计</a></li>
						<li>
							<a href="#" data-id="74"> <i class="fa fa-circle-o"></i>奖励统计<i class="fa fa-angle-left pull-right"></i></a>
							<ul class="treeview-menu">
								<li><a href="${ctx}/platform/niFixedAwardStatisticsList.do" data-id="741"><i class="fa fa-circle-o"></i>定奖统计</a></li>
								<li><a href="${ctx}/platform/niLotteryStatisticsList.do" data-id="742"><i class="fa fa-circle-o"></i>抽奖统计</a></li>
								<li>
									<a href="#" data-id="743"> <i class="fa fa-circle-o"></i>积分统计<i class="fa fa-angle-left pull-right"></i></a>
									<ul class="treeview-menu">
										<li><a href="${ctx}/platform/niWanxPointStatisticsList.do" data-id="7431"><i class="fa fa-circle-o"></i>玩校积分</a></li>
										<li><a href="#" data-id="7432"><i class="fa fa-circle-o"></i>超级调查积分(迭代)</a></li>
									</ul>
								</li>
							</ul>
						</li>
			            <li><a href="${ctx}/platform/niUserStatisticsList.do" data-id="75"> <i class="fa fa-circle-o"></i>用户统计</a></li>
		            </ul>
		        </li>
		        
		        <!-- 统计管理 -->
		        <li class="treeview">
		            <a href="#" data-id="7"><i class="fa fa-share"></i><span> 产品包管理</span> <i class="fa fa-angle-left pull-right"></i></a>
		            <ul class="treeview-menu">
						<li><a href="${ctx}/platform/findAll_SchoolMember.do" data-id="71"> <i class="fa fa-circle-o"></i>学校会员</a></li>
						<li><a href="${ctx}/platform/list_ProductPackage.do" data-id="72"> <i class="fa fa-circle-o"></i>产品包</a></li>
						<li><a href="${ctx}/platform/list_ProductRecommend.do" data-id="72"> <i class="fa fa-circle-o"></i>推荐秘籍</a></li>
		            </ul>
		        </li>		        
		        
		        <li class="treeview">
		            <a href="#" data-id="7"><i class="fa fa-share"></i><span> 工具</span> <i class="fa fa-angle-left pull-right"></i></a>
		            <ul class="treeview-menu">
						<li><a href="${ctx}/platform/new_wanxUrl_page.do" data-id="71"> <i class="fa fa-circle-o"></i>玩校登录地址配置</a></li>
		            </ul>
		        </li>	
			</ul>
			</section>
			<!-- /.sidebar -->
		</aside>
		
		<div class="content-wrapper rightmenu">
			<sitemesh:body />
		</div>
		
		<footer class="main-footer">
			<div class="pull-right hidden-xs">
				<b>版本</b> 1.0.0
			</div>
			<strong>超级问卷调查 &copy; 2016-07-15 </strong>
		</footer>
		
	</div>
	<script src="${ctx}/BS/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<script src="${ctx}/BS/bootstrap/js/bootstrap.min.js"></script>
	<script src="${ctx}/BS/plugins/daterangepicker/daterangepicker.js"></script>
	<script src="${ctx}/BS/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="${ctx}/BS/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script src="${ctx}/static/js/moment.min.js"></script>
	<script src="${ctx}/BS/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script src="${ctx}/BS/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<script src="${ctx}/BS/plugins/fastclick/fastclick.js"></script>
	<script src="${ctx}/BS/dist/js/app.min.js"></script>
	<script src="${ctx}/BS/dist/js/demo.js"></script>
	<script src="${ctx}/js/validate.js"></script>
	<script src="${ctx}/BS/plugins/select2/select2.full.min.js"></script>
	<script src="${ctx}/BS/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
	<script src="${ctx}/BS/plugins/timepicker/bootstrap-timepicker.min.js"></script>
	<script src="${ctx}/BS/plugins/iCheck/icheck.min.js"></script>
	<script src="${ctx}/BS/plugins/jQueryUI/jquery-ui.min.js"></script>
		
	<!-- 引入富文本编辑器   begin -->
	<script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/lang/zh-cn/zh-cn.js"></script>
    <!-- 引入富文本编辑器   end -->
	<script>
		$(function() {
			$("#example1").DataTable();
		});
		$('#reservation').daterangepicker();
		
		
		 $(document).ready(function()
		   {
			    $("#mymenu ul li").next("ul").hide();
			    $("#mymenu ul li").click(function()
			    {
			     $(this).next("ul").toggle();
			    });

			    $("#primary-sidebar a").click(function(event) {
			    	var id = $(this).attr("data-id");
			    	var status = localStorage.getItem(id);
			    	if (status == "open"){	
				    	localStorage.setItem(id, "close");
			    	} else {
			    		localStorage.setItem(id, "open");
			    		$(this).parent('li').siblings('li').find('a').each(function(index, el) {
			    			var thisId = $(this).attr("data-id");
			    			var thisStatus = localStorage.getItem(thisId);
			    			localStorage.setItem(thisId, "close");
			    		});
			    	}
			    });

			    $("#primary-sidebar a").each(function(index, el) {
			    	var id = $(this).attr("data-id");
			    	var status = localStorage.getItem(id);
			    	if (status == "open"){
			    		$(this).next('ul').css('display','block');
			    	}
			    });

			    $('#logout').click(function(event) {
		            localStorage.setItem("autologin", "false");
		            window.location.href = '${ctx }/managerUser/manageUserSingOut.do';
				});

		   });
		 
	</script>
</body>


</html>