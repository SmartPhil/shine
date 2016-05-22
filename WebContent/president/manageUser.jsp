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
				var operation = "<button name=\"delete\" class=\"btn btn-primary\">删除</button>&nbsp;" + 
								"<button name=\"modify\" class=\"btn btn-primary\">修改</button>"
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
</body>
</html>