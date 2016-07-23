<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>I ShINE ENGLISH</title>
<!-- css文件 -->
<link href="<%=request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">

<!-- js文件 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).ready(function(e){
	//获取CS列表
	$.ajax({
		url : 'getCSUser.action',
		type : 'post',
		dataType : 'json',
		success : function(e){
			var data = eval("(" + e + ")");
			for (var i = 0; i < data.length; i++) {
				var id = data[i].id;
				var name = data[i].name;
				$("#cs").append("<option value=\"" + name + "\">" + name + "</option>");
			}
		},
		error : function(e){
			alert("系统出错！请联系管理员！");
		}
	});
	
	$("#submitSJ").click(function(){
		var $btn = $(this).button('loading');
		
		var contactTel1 = $("#contactTel1").val();
		if(contactTel1 == ""){
			alert("请输入联系电话1");
			$("#contactTel1").focus();
			$btn.button('reset');
			return;
		}
		
		$.ajax({
			url : "addOpp.action",
			type : "post",
			data : $("#sjForm").serialize(),
			success : function(data){
				var result = eval("(" + data + ")");
				var result1 = result.result;
				if(result1 == "success"){
					alert("提交成功！");
					$btn.button('reset');
					window.location.reload();
				}else if(result1 == "fail"){
					$btn.button('reset');
					alert("提交失败！");
				}
			},
			error : function(XMLResponse){
				$btn.button('reset');
				alert("提交失败！请联系系统管理员！");
			}
		});
	});
	
	/** 展现修改用户密码modal **/
	$("#nameShow").click(function(){
		$("#modifyPswModal").modal({
			keyboard : true
		});
	});
	
	/** 提交修改密码请求 **/
	$("#modifyPswButton").click(function(){
		var newPassword = $("#newPassword").val();
		var confirmPassword = $("#confirmPassword").val();
		if (newPassword != confirmPassword) {
			alert("新密码两次输入不一致，请重新输入！");
			return;
		}
		
		$.ajax({
			url : 'modifyUserPsw',
			type : 'post',
			data : {'username' : $("#nameShow").text().split(":")[1],'usedPassword' : $("#usedPassword").val(),
				'newPassword' : $("#newPassword").val()},
			dataType : 'json',
			success : function(e){
				var data = eval("(" + e + ")");
				var result = data.result;
				if (result == "systemError") {
					alert("未查到当前用户！请稍后再试！");
				}else if (result == "usedPasswordError"){
					alert("原密码错误,请确认后再试！");
				}else if (result == "modifyError"){
					alert("修改密码出错，请稍后再试！");
				}else if (result == "success"){
					alert("修改成功！");
				}
			},
			error : function(e){
				alert("系统出错！请联系管理员！");
			}
		});
	});
});
</script>
</head>
<body>
<% 
	HttpSession sessions = request.getSession();
	Object username = sessions.getAttribute("username");
%>
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid" style="padding-top: 5px;">
		<div class="navbar-header">
			<a class="navbar-brand" href="#" style="padding-top: 5px;">
        		<img alt="" src="<%=request.getContextPath()%>/img/index_logo.png">
      		</a>
		</div>
		<div class="collapse navbar-collapse" style="margin-left: auto;margin-right: auto;width: 70%;">
			<ul class="nav nav-pills">
				<li role="presentation"><a id="nameShow" href="#">欢迎您:<%=username %></a></li>
  				<li role="presentation" class="active"><a href="#">录入新生资源</a></li>
  				<li role="presentation"><a href="<%=request.getContextPath()%>/channel/importOpp.jsp">批量导入</a></li>
  				<li role="presentation"><a href="<%=request.getContextPath()%>/channel/deal.jsp">成单</a></li>
			</ul>
		</div>
		<div class="navbar-footer"></div>
	</div>
</nav>
<br/>
<br/><br/><br/>
<div id="body">
<div id="showSjInput" style="width: 65%;margin-left: auto;margin-right: auto;">
	<form id="sjForm">
		<div class="form-group">
  			<p class="bg-primary">注意：带*号为必填项</p>	
  		</div>
  		<div class="form-group">
  			<label for="name">学员姓名</label>
    		<input type="text" class="form-control" id="name" name="name" placeholder="请输入学员姓名">
  		</div>
  		<div class="form-group">
  			<label for="englishName">英文姓名</label>
    		<input type="text" class="form-control" id="englishName" name="englishName" placeholder="请输入英文姓名">
  		</div>
  		<div class="form-group">
    		<label for="contactTel1">联系方式1<span class="badge">*</span></label>
    		<input type="text" class="form-control" id="contactTel1" name="contactTel1" placeholder="请输入手机号码/座机号码">
  		</div>
  		<div class="form-group">
    		<label for="contactTel2">联系方式2</label>
    		<input type="text" class="form-control" id="contactTel2" name="contactTel2" placeholder="请输入手机号码/座机号码">
  		</div>
  		<div class="form-group">
  			<label for="gender">性别</label>
    		<select id="gender" class="form-control" name="gender">
    			<option value="1">男</option>
    			<option value="2">女</option>
    		</select>
  		</div>
  		<div class="form-group">
    		<label for="birthday">生日</label>
    		<input type="text" class="form-control" id="birthday" name="birthday" onclick="WdatePicker()" placeholder="请选择生日">
  		</div>
  		<div class="form-group">
    		<label for="cs">客服</label>
    		<select id="cs" class="form-control" name="cs" placeholder="请选择客服人员">
    		</select>
  		</div>
  		<div class="form-group">
    		<label for="source">来源</label>
    		<input type="text" class="form-control" id="source" name="source" placeholder="请输入信息来源">
  		</div>
  		<input type="hidden" value="<%=username %>" id="giveOrg" name="giveOrg"/>
  		<input type="button" data-loading-text="提交中……" class="btn btn-primary" id="submitSJ" value="提交"/>
	</form>
</div>
</div>
<br/>
<br/><br/><br/>

<!-- 修改密码 -->
<div class="modal fade" id="modifyPswModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">修改密码</h4>
      </div>
      <div class="modal-body"> 
      	<form id="modifyPswForm">
  			<div class="form-group">
    			<label for="usedPassword">原密码</label>
      			<input type="text" class="form-control" id="usedPassword" name="usedPassword" placeholder="原始密码">
  			</div>
  			<div class="form-group">
  				<label class="newPassword">新密码</label>
  				<input type="text" class="form-control" id="newPassword" name="newPassword" placeholder="新密码">
  			</div>
  			<div class="form-group">
  				<label for="confirmPassword">确认密码</label>
  				<input type="text" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="确认密码">
  			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" id="modifyPswButton" class="btn btn-primary">确认修改</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>