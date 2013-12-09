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
				$("#loginBtn").click(function(){
					$("#loginBtn").attr("disabled","disabled");
					$("#errMsg").fadeOut();
					$.ajax({
						type:"POST",
						url:"<c:url value='/login.do'></c:url>",
						dataType:'json',
						data:{
							username:$("#username").val(),
							password:MD5($("#password").val())
						},
						success:function(response){
							if(response.result=="true"){
								location.href="<c:url value='/hall/index.do'></c:url>";
							}else{
								$("#errMsg").html(response.message);
								$("#errMsg").fadeIn();
							}
							$("#loginBtn").removeAttr("disabled");						
						},
						error:function(){
							$("#loginBtn").removeAttr("disabled");
							alert("系统忙请稍后再试");
						}
					});
				});
			});
		</script>
		<title>404了有木有</title>
	</head>
	<body>
		<div class="alg_c h300 w400 m_t100">
			<p class="alg_c">页面找不到了爱咋咋滴</p>
		</div>
	</body>
</html>
