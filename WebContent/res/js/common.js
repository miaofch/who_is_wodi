function ajaxError(jqXHR, textStatus, errorThrown) {
	if (jqXHR.status = 600) {
		alert("未登录或登录超时，请重新登录");
		location.href = base + "/login.jsp";
	}
}