<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<c:url value='/res/css/theme.css'></c:url>" />
		<script type="text/javascript" src="<c:url value='/res/js/jquery.js'></c:url>"></script>
		<script type="text/javascript" src="<c:url value='/res/js/cui/DateUtils.js'></c:url>"></script>
		<script type="text/javascript">
			
		</script>
		<title>房间</title>
	</head>
	<body>
		<div class="alg_c w800 m_t50">
			<div id="left">
				<div id="roomuserlist">
					<h2 class="item_title">用户列表</h2>
					<ul></ul>
				</div>
				<div id="roomstatus">
					<h2 class="item_title">游戏信息</h2>
				</div>
			</div>
			<div id="testimony_hall">
				<h2 class="item_title">证词</h2>
				<div id="testimony_content">
					<p>123</p>
					<p>123</p>
					<p>123</p>
					<p>123</p>
					<p>123</p>
					<p>123</p>
					<p>123</p>
					<p>123</p>
					<p>123</p>
					<p>123</p>
					<p>123</p>
				</div>
				<input id="testimony_input" type="text" size="50" />
				<input id="testimony_btn" type="button" value="发表" />
			</div>
			<div id="roomchat_hall">
				<h2 class="item_title">闲聊</h2>
				<div id="roomchat_content">
					<p>123</p>
					<p>123</p>
					<p>123</p>
					<p>123</p>
				</div>
				<input id="roomchat_input" type="text" size="50" />
				<input id="roomchat_btn" type="button" value="胡诌" />
			</div>
		</div>
	</body>
</html>