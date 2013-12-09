<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<c:url value='/res/css/theme.css'></c:url>" />
		<script type="text/javascript" src="<c:url value='/res/js/jquery.js'></c:url>"></script>
		<script type="text/javascript" src="<c:url value='/res/js/cui/DateUtils.js'></c:url>"></script>
		<script type="text/javascript">
			// 最后一次发送
			var lastSendTime = 0;
			var lastSendMsg = "";
			// 最后一次接收时间
			var lastReceiveTime = 0;
			var receiveErrorCount = 0;
			// 刷新列表额度，以15分钟左右为准
			var reveiveCredit = 1000;
			var pullUserListCredit = 200;
		
			$(function(){
				scrollToChatBottom();
				
				setInterval(pullMsg,1000);
				pullUserList();
				setInterval(pullUserList,5000);
				
				$("#chat_input").keydown(function(event){
					if(event.keyCode==13){
						sendMsg();
					}
				});
				$("#chat_btn").click(function(){
					sendMsg();
				});
				$("#roomlist ul li").mouseenter(function() {
					$(this).css("background-color","#D5EAFB");
				});
				$("#roomlist ul li").mouseleave(function() {
					$(this).css("background-color","");
				});
			});
			
			function sendMsg(){
				var message = $("#chat_input").val();
				// 刷屏校验
				if(/^\s*$/.test(message)){
					addSysMsg("你说啥？啥也没写~");
					return;
				}
				if(message==lastSendMsg&&new Date().getTime() - lastSendTime < 5000){
					addSysMsg("复读机啊你！");
					return;
				}
				if(new Date().getTime() - lastSendTime < 1000){
					addSysMsg("刷你妹！");
					return;
				}
				// 发送信息
				$("#chat_btn").removeAttr("disabled");
				$.ajax({
					type:"POST",
					url:"<c:url value='/wisw/hall/sendMsg.do'></c:url>",
					dataType:"json",
					data:{
						message:message
					},
					success:function(response){
						reveiveCredit=1000;
						pullUserListCredit=200;
						if(response.result){
							lastSendTime=new Date().getTime();
							lastSendMsg=message;
							$("#chat_input").val("");
						}else{
							addSysMsg(response.message);
						}
						$("#chat_btn").removeAttr("disabled");						
					},
					error:function(){
						$("#chat_btn").removeAttr("disabled");
						addSysMsg("没发出去，你太卡逼了！");
					}
				});
				
				
			}

			function addSysMsg(message){
				$("#chat_content").append("<p class='red'>[系统提示]"+message+"</p>");
				scrollToChatBottom();
			}
			
			function addMsg(nickname,username,sendTime,message){
				$("#chat_content").append("<p><span class='blue'>"+nickname+"("+username+") "+DateUtils.date2String(new Date(sendTime.getTime()),7)+"</span><br />"+message+"</p>");
				scrollToChatBottom();
			}

			function scrollToChatBottom(){
				var scrollTop = $("#chat_content")[0].scrollHeight;  
				$("#chat_content").scrollTop(scrollTop);
			}
			
			function pullMsg() {
				if(reveiveCredit>0){
					reveiveCredit--;
				}else{
					return;
				}
				$.ajax({
					url:"<c:url value='/wisw/hall/pullMsg.do'></c:url>?a="+new Date().getTime(),
					dataType:'json',
					data:{
						lastReceiveTime:lastReceiveTime
					},
					success:function(response){
						receiveErrorCount = 0;
						if(response.unreadMessageList.length>0){
							lastReceiveTime=response.lastReceiveTime;
							for(var i=0;i<response.unreadMessageList.length;i++){
								var message=response.unreadMessageList[i];
								addMsg(message.nickname,message.username,new Date(message.sendTime.time),message.message);
							}
						}
					},
					error:function(){
						receiveErrorCount++;
						if(receiveErrorCount==5){
							reveiveCredit = 0;
							pullUserListCredit = 0;
							addSysMsg("网络情况不佳，请刷新重试");
						}
					}
				});
			}
			
			function pullUserList() {
				if(pullUserListCredit>0){
					pullUserListCredit--;
				}else{
					return;
				}
				$.ajax({
					url:"<c:url value='/wisw/hall/pullUserList.do'></c:url>?a="+new Date().getTime(),
					dataType:'json',
					success:function(response){
						$("#userlist ul").html("");
						for(var i=0;i<response.length;i++){
							var user=response[i];
							$("#userlist ul").append("<li>"+user.nickname+"("+user.username+")</li>");
						}
						$("#userlist ul li").mouseenter(function() {
							$(this).css("background-color","#D5EAFB");
						});
						$("#userlist ul li").mouseleave(function() {
							$(this).css("background-color","");
						});
					},
					error:function(){
						receiveErrorCount++;
						if(receiveErrorCount==5){
							reveiveCredit = 0;
							pullUserListCredit = 0;
							addSysMsg("网络情况不佳，请刷新重试");
						}
					}
				});
			}
		</script>
		<title>游戏大厅</title>
	</head>
	<body>
		<div class="alg_c w800 m_t50">
			<div id="userlist">
				<h2 class="item_title">用户列表</h2>
				<ul></ul>
			</div>
			<div id="roomlist">
				<h2 class="item_title">房间列表<a href="###">[创建]</a></h2>
				<ul>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
					<li>123123</li>
				</ul>
			</div>
			<div id="chat_hall">
				<div id="chat_content">
				</div>
				<input id="chat_input" type="text" size="50" />
				<input id="chat_btn" type="button" value="提交" />
			</div>
		</div>
	</body>
</html>