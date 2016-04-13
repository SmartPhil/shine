<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商机分销系统</title>
<!-- css文件 -->
<link href="<%=request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">

<!-- js文件 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).ready(function(e){
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
});
</script>
</head>
<body>
<% 
	HttpSession sessions = request.getSession();
	Object username = sessions.getAttribute("username");
%>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">
        		<img alt="" src="<%=request.getContextPath()%>/img/index_logo.png">
      		</a>
		</div>
		<div class="collapse navbar-collapse" style="margin-left: auto;margin-right: auto;width: 70%;">
			<ul class="nav nav-pills">
				<li role="presentation"><a href="#">欢迎您:<%=username %></a></li>
  				<li role="presentation" class="active"><a href="#">录入商机</a></li>
  				<li role="presentation"><a href="<%=request.getContextPath()%>/channel/importOpp.jsp">批量导入</a></li>
			</ul>
		</div>
		<div class="navbar-footer"></div>
	</div>
</nav>
<br/>
<div id="body">
<div id="showSjInput" style="width: 60%;margin-left: auto;margin-right: auto;">
	<form id="sjForm">
		<div class="form-group">
  			<p class="bg-primary">注意：带*号为必填项</p>	
  		</div>
  		<div class="form-group">
  			<label for="exampleInputEmail1">学员姓名</label>
    		<input type="text" class="form-control" id="name" name="name" placeholder="请输入学员姓名">
  		</div>
  		<div class="form-group">
  			<label for="exampleInputEmail1">英文姓名</label>
    		<input type="text" class="form-control" id="englishName" name="englishName" placeholder="请输入英文姓名">
  		</div>
  		<div class="form-group">
    		<label for="exampleInputPassword1">家长姓名</label>
    		<input type="text" class="form-control" id="parentName" name="parentName" placeholder="请输入家长姓名">
  		</div>
  		<div class="form-group">
    		<label for="exampleInputFile">联系方式1<span class="badge">*</span></label>
    		<input type="text" class="form-control" id="contactTel1" name="contactTel1" placeholder="请输入手机号码/座机号码">
  		</div>
  		<div class="form-group">
    		<label for="exampleInputFile">联系方式2</label>
    		<input type="text" class="form-control" id="contactTel2" name="contactTel2" placeholder="请输入手机号码/座机号码">
  		</div>
  		<div class="form-group">
    		<label for="exampleInputFile">Email</label>
    		<input type="text" class="form-control" id="email" name="email" placeholder="请输入Email">
  		</div>
  		<div class="form-group">
    		<label for="exampleInputFile">预约时间</label>
    		<input type="text" class="form-control" id="orderTime" name="orderTime" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="请选择预约时间">
  		</div>
  		<div class="form-group">
  			<label for="exampleInputEmail1">性别</label>
    		<select id="gender" class="form-control" name="gender">
    			<option value="1">男</option>
    			<option value="2">女</option>
    		</select>
  		</div>
  		<div class="form-group">
    		<label for="exampleInputPassword1">年龄</label>
    		<input type="text" class="form-control" id="age" name="age" onkeyup="value=value.replace(/[^(\d)]/g,'')" placeholder="请输入年龄">
  		</div>
  		<div class="form-group">
    		<label for="exampleInputPassword1">生日</label>
    		<input type="text" class="form-control" id="birthday" name="birthday" onclick="WdatePicker()" placeholder="请选择生日">
  		</div>
		<div class="form-group">
    		<label for="exampleInputFile">学校</label>
    		<input type="text" class="form-control" id="school" name="school" placeholder="请输入学员就读学校">
  		</div>
  		<div class="form-group">
    		<label for="exampleInputFile">家庭地址</label>
    		<input type="text" class="form-control" id="address" name="address" placeholder="请输入学员地址">
  		</div>
  		<div class="form-group">
    		<label for="exampleInputFile">来源</label>
    		<input type="text" class="form-control" id="source" name="source" placeholder="请输入信息来源">
  		</div>
  		<input type="hidden" value="<%=username %>" id="giveOrg" name="giveOrg"/>
  		<input type="button" data-loading-text="提交中……" class="btn btn-primary" id="submitSJ" value="提交"/>
	</form>
</div>
</div>
</body>
</html>