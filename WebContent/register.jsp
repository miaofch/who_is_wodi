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
				$("#registerBtn").removeAttr("disabled");
				$("#registerBtn").click(function(){
					$("#errMsg").fadeOut();
					$("#registerBtn").attr("disabled","disabled");	
					if (!valid()){
						$("#registerBtn").removeAttr("disabled");
						return;
					}
					$.ajax({
						type:"POST",
						url:"<c:url value='/register.do'></c:url>",
						dataType:"json",
						data:{
							username:$("#username").val(),
							password:MD5($("#password").val()),
							nickname:$("#nickname").val(),
							email:$("#email").val()
						},
						success:function(response){
							if(response.result){
								$("#message").val("注册成功，请登录");
								$("#jumpForm").submit();
							}else{
								$("#errMsg").html(response.message);
								$("#errMsg").fadeIn();
								$("#registerBtn").removeAttr("disabled");
							}
						},
						error:function(){
							$("#registerBtn").removeAttr("disabled");
							alert("系统忙请稍后再试");
						}
					});
				});
				
			});
			
			function valid(){
				if($("#password").val()!=$("#repassword").val()){
					$("#errMsg").html("两次密码输入不一致");
					$("#errMsg").fadeIn();
					return false;
				}
				if($("#password").val().length < 6 || $("#password").val().length > 20){
					$("#errMsg").html("密码必须为6-20位");
					$("#errMsg").fadeIn();
					return false;
				}
				return true;
			}
		</script>
		<title>注册</title>
	</head>
	<body>
		<div class="alg_c w400 m_t100">
			
			<p class="alg_c">
				<span class="label">*用户名：</span>
				<input id="username" type="text" class="input_text" size="20" value="<c:out value='${param.username}'></c:out>" />
			</p>
			<p class="alg_c">
				<span class="label">*密码：</span>
				<input id="password" type="password" class="input_text" size="20"/>
			</p>
			<p class="alg_c">
				<span class="label">*确认密码：</span>
				<input id="repassword" type="password" class="input_text" size="20"/>
			</p>
			<p class="alg_c">
				<span class="label">*昵称：</span>
				<input id="nickname" type="text" class="input_text" size="20"/>
			</p>
			<p class="alg_c">
				<span class="label">邮箱：</span>
				<input id="email" type="text" class="input_text" size="50"/>
			</p>
			<p class="alg_c">
				<input type="button" id="registerBtn" value="注册" />
			</p>
			<form id="jumpForm" action="<c:url value='/login.jsp'></c:url>" method="post" style="display:none">
				<input id="message" name="message" />
			</form>
		</div>
	</body>
</html>
