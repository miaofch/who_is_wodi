<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<c:url value='/res/css/theme.css'></c:url>" />
		<script type="text/javascript" src="<c:url value='/res/js/jquery.js'></c:url>"></script>
		<script type="text/javascript" src="<c:url value='/res/js/md5.js'></c:url>"></script>
		<script type="text/javascript">
			$(function(){
				$("#loginBtn").removeAttr("disabled");
				$("#password").keydown(function(event){
					if(event.keyCode==13){
						login();
					}
				});
				$("#loginBtn").click(function(){
					login();
				});
				$("#registerBtn").click(function(){
					location.href="<c:url value='/register.jsp'></c:url>?username="+$("#username").val();
				});
			});
			
			function login(){
				$("#loginBtn").attr("disabled","disabled");
				$("#errMsg").fadeOut();
				$.ajax({
					type:"POST",
					url:"<c:url value='/login.do'></c:url>",
					dataType:"json",
					data:{
						username:$("#username").val(),
						password:$("#password").val()==""?"":MD5($("#password").val())
					},
					success:function(response){
						if(response.result){
							location.href="<c:url value='/wisw/hall/index.do'></c:url>";
						}else{
							$("#errMsg").html(response.message);
							$("#errMsg").fadeIn();
							$("#loginBtn").removeAttr("disabled");						
						}
					},
					error:function(){
						$("#loginBtn").removeAttr("disabled");
						alert("系统忙请稍后再试");
					}
				});
			}
		</script>
		<title>登录</title>
	</head>
	<body>
		<div class="alg_c h300 w400 m_t100">
			<p id="errMsg" class="alg_c" <c:if test="${param.message==null||param.message==''}">style="display:none"</c:if>><c:out value="${param.message}"></c:out></p>
			<p class="alg_c">
				<span class="label">用户名：</span>
				<input id="username" type="text" class="input_text" size="20"/>
			</p>
			<p class="alg_c">
				<span class="label">密码：</span>
				<input id="password" type="password" class="input_text" size="20"/>
			</p>
			<p class="alg_c">
				<input type="button" id="loginBtn" value="登录" />
				<input type="button" id="registerBtn" value="注册" />
			</p>
		</div>
	</body>
</html>
