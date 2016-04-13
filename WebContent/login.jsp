<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>I-shine English</title>
<!-- css文件 -->
<link href="<%=request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">

<!-- js文件 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(document).ready(function(e){
	//登陆操作
	$("#btnLogin").click(function(){
		var $btn = $(this).button('loading');
		
		var userName = $("#username").val();
		var passWord = $("#password").val();
		
		if(userName == ""){
			alert("请输入用户名！");
			return;
		}
		if(passWord == ""){
			alert("请输入密码！");
			return;
		}
		$.ajax({
			url : "login.action",
			type : 'post',
			data : $("#loginForm").serialize(),
			success : function(data){
				var info = eval("("+data+")");
				var loginResult = info.loginResult;
				if(loginResult == "success"){
					var role = info.role;
					if(role == '1'){
						//校长
						window.location.href = "";
					}else if (role == '2') {
						//教师主管
						window.location.href = "";
					}else if (role == '3') {
						//渠道（行政或市场）
						window.location.href = "channel/singleInputOpp.jsp";
					}else if (role == '4') {
						//客服（销售）
						window.location.href = "";
					}else if (role == '5') {
						//老师
						window.location.href = "";
					}
					$btn.button('reset');
				}else{
					alert("登陆失败！用户名或密码错误！");
					$btn.button('reset');
				}
			},
			error : function(){
				alert("登陆失败！网络出错！请联系管理员！");
				$btn.button('reset');
			}
		});
	});
});
</script>
</head>
<body style="background-image:url('<%=request.getContextPath() %>/img/bg.jpg');">
<form id="loginForm">
	<div style="width: 20%;margin-left: auto;margin-right: auto;margin-top: 15%">
		<div class="form-group">
			<label>I SHINE ENGLISH</label>
		</div>
		<div class="form-group">
			<div class="input-group">
  				<span class="input-group-addon">用户名</span>
  				<input id="username" name="username" type="text" class="form-control" placeholder="请输入用户名" aria-describedby="sizing-addon1">
			</div>
		</div>
		<div class="form-group">	
			<div class="input-group">
  				<span class="input-group-addon">密&nbsp;&nbsp; 码</span>
  				<input id="password" name="password" type="password" class="form-control" placeholder="请输入密码" aria-describedby="sizing-addon1">
			</div>
		</div>
		<div class="form-group">
			<input type="button" class="btn btn-primary" value="登陆" id="btnLogin" title="点击登陆" data-loading-text="登陆中">
		</div>
	</div>
</form>
</body>
</html>