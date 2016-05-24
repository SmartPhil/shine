<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理用户</title>
<!-- css文件 -->
<link href="<%=request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">

<!-- js文件 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
$(document).ready(function(e){
	var table = $("#mainTable").DataTable({});
	
	$("#searchButton").click(function(e){
		$.ajax({
			url : 'getUser.action',
			type : 'post',
			data : {'username' : $("#username").val(),'role' : $("#role").val()},
			dataType : 'json',
			success : function(e){
				table.clear().draw(false);
				var data = eval("(" + e + ")");
				var operation = "<button name=\"deleteUser\" class=\"btn btn-primary\">删除用户</button>&nbsp;" + 
								"<button name=\"modifyUser\" class=\"btn btn-primary\">修改密码</button>"
				for (var i = 0; i < data.length; i++) {
					var obj = [data[i].id,data[i].username,data[i].role,operation];
					table.row.add(obj).draw(false);
				}
			},
			error : function(e){
				alert("系统出错！请联系管理员！");
			}
		});
	});
	
	$("#addUserBtn").click(function(e){
		$("#addUserModal").modal({
			keyboard : true
		});
	});
	
	$("#add_username").blur(function(e){
		var username = $(this).val();
		$.ajax({
			url : 'usernameIsExiste.action',
			type : 'post',
			data : {'username' : username},
			datatype : 'json',
			success : function(e){
				var data = eval("(" + e + ")");
				var result = data.result;
				if (result == "fail") {
					alert("此用户名已存在！");
					$("#add_username").focus();
				}
			},
			error : function(e){
				alert("系统出错！请联系管理员！");
			}
		});
	});
	
	$("#add_confirmpassword").blur(function(e){
		var password = $("#add_password").val();
		var confirmpassword = $("#add_confirmpassword").val();
		
		if (password != confirmpassword) {
			alert("密码不一致！请重新输入！");
			return;
		}
	});
	
	$("#btn_submitAdd").click(function(e){
		$.ajax({
			url : 'addUser.action',
			type : 'post',
			data : $("#addform").serialize(),
			dataType : 'json',
			success : function(e){
				var data = eval("(" + e + ")");
				var result = data.result;
				if (result == "had") {
					alert("添加失败！用户名已存在！");
				}else if (result == "success") {
					alert("添加成功！");
				}else if (result == "fail") {
					alert("添加失败！");
				}
			},
			error : function(e){
				alert("系统出错！请联系管理员！");
			}
		});
	});
	
	$("#mainTable").on("click","button[name='deleteUser']",function(e){
		if(confirm("确定删除用户吗？")) {
			var td = $(this).parent();
			var tr = $(td).parent();
			var tds = $(tr).children("td");
			var id = $(tds[0]).text();
			$.ajax({
				url : 'deleteUser.action',
				type : 'post',
				data : {'id' : id},
				dataType : 'json',
				success : function(e){
					var data = eval("(" + e + ")");
					var result = data.result;
					if (result == "success") {
						alert("删除成功！");
						window.location.reload();
					}else {
						alert("删除失败！");
					}
				},
				error : function(e){
					alert("系统出错！请联系管理员！");
				}
			});
		}else {
			return;
		}
	});
	
	$("#mainTable").on("click","button[name='modifyUser']",function(e){
		var td = $(this).parent();
		var tr = $(td).parent();
		var tds = $(tr).children("td");
		
		var id = $(tds[0]).text();
		var username = $(tds[1]).text();
		
		$("#modify_id").val(id);
		$("#modify_username").val(username);
		
		$("#modifyUserModal").modal({
			keyboard : true
		});
	});
	
	$("#btn_submitModify").click(function(e){
		var password = $("#modify_newPassword").val();
		var confirmPassword = $("#modify_confirmPassword").val();
		if (password != confirmPassword) {
			alert("两次密码输入不一致！请重新输入！");
			return;
		}
		if (password == null || password == "") {
			alert("密码不能为空！");
			return;
		}
		$.ajax({
			url : 'modifyUser.action',
			type : 'post',
			data : {
						'id' : $("#modify_id").val(),
						'newPassword' : $("#modify_newPassword").val(),
						'originalPassword' : $("#modify_originalPassword").val()
					},
			dataType : 'json',
			success : function(e){
				var data = eval("(" + e + ")");
				var result = data.result;
				if (result == "success") {
					alert("修改成功！");
				}else if (result == "fail") {
					alert("修改失败！");
				}else if (result == "originalPswNotCorrect") {
					alert("原密码错误！请确认！");
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
  				<li role="presentation"><a href="getUnAssignOppPresident.action">未分配商机</a></li>
  				<li role="presentation" class="active"><a href="#">管理系统用户</a></li>
			</ul>
		</div>
		<div class="navbar-footer"></div>
	</div>
</nav>
<br/>
<br/>
<br/>
<br/>
<div style="width: 68%;margin-left: auto;margin-right: auto;" class="well well-lg">
	<form class="form-inline">
  		<div class="form-group">
    		<div class="input-group">
      			<div class="input-group-addon">用户名</div>
      			<input type="text" class="form-control" id="username" placeholder="用户名">
    		</div>
  		</div>
  		&nbsp;&nbsp;&nbsp;&nbsp;
  		<div class="form-group">
    		<div class="input-group">
      			<div class="input-group-addon">角色</div>
      			<select class="form-control" id="role">
      				<option value="0"></option>
  					<option value="1">校长</option>
  					<option value="2">教师主管</option>
  					<option value="3">行政人员</option>
  					<option value="4">客服人员</option>
  					<option value="5">老师</option>
				</select>
    		</div>
  		</div>
  		&nbsp;&nbsp;&nbsp;
  		<input type="button" class="btn btn-primary" id="searchButton" value="查询" data-loading-text="查询中">
  		<input type="button" class="btn btn-primary" id="addUserBtn" value="增加用户">
	</form>
</div>
<div id="dataShowDiv" class="panel panel-primary" style="width: 68%;margin-left: auto;margin-right: auto;white-space:nowrap;">
	<div class="panel-heading">用户列表</div>
	<table style="width: 100%" aria-describedby="example_info" class="table table-striped table-bordered dataTable" id="mainTable">
		<thead>
			<tr>
				<th>ID</th>
				<th>用户名</th>
				<th>角色</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
		<tfoot>
			<tr>
				<th>ID</th>
				<th>用户名</th>
				<th>角色</th>
				<th>操作</th>
			</tr>
		</tfoot>
	</table>
</div>
<!-- Add Modal -->
<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">添加用户</h4>
      </div>
      <div class="modal-body"> 
       	<form id="addform">
  			<div class="form-group">
    			<label for="username">用户名</label>
    			<input type="text" class="form-control" id="add_username" name="username" placeholder="用户名">
  			</div>
  			<div class="form-group">
    			<label for="password">密码</label>
    			<input type="password" class="form-control" id="add_password" name="password" placeholder="密码">
  			</div>
  			<div class="form-group">
    			<label for="password">确认密码</label>
    			<input type="password" class="form-control" id="add_confirmpassword" name="confirmpassword" placeholder="确认密码">
  			</div>
  			<div class="form-group">
    			<label for="exampleInputPassword1">角色</label>
    			<select class="form-control" id="add_role" name="role">
  					<option value="2">教师主管</option>
  					<option value="3">行政人员</option>
  					<option value="4">客服人员</option>
  					<option value="5">老师</option>
				</select>
  			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" id="btn_submitAdd" class="btn btn-primary">提交</button>
      </div>
    </div>
  </div>
</div>

<!-- modify modal -->
<div class="modal fade" id="modifyUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">修改密码</h4>
      </div>
      <div class="modal-body"> 
       	<form id="modifyForm">
       		<div class="form-group">
    			<label for="modify_id">id</label>
    			<input type="text" class="form-control" id="modify_id" name="id" disabled>
  			</div>
  			<div class="form-group">
    			<label for="modify_username">用户名</label>
    			<input type="text" class="form-control" id="modify_username" name="username" disabled>
  			</div>
  			<div class="form-group">
    			<label for="modify_originalPassword">原密码</label>
    			<input type="password" class="form-control" id="modify_originalPassword" name="originalPassword" placeholder="原密码">
  			</div>
  			<div class="form-group">
    			<label for="modify_newPassword">新密码</label>
    			<input type="password" class="form-control" id="modify_newPassword" name="newPassword" placeholder="新密码">
  			</div>
  			<div class="form-group">
    			<label for="exampleInputPassword1">确认密码</label>
    			<input type="password" class="form-control" id="modify_confirmPassword" name="confirmPassword" placeholder="确认密码">
  			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" id="btn_submitModify" class="btn btn-primary">提交</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>