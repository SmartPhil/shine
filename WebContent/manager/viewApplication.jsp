<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>I ShINE ENGLISH</title>
<!-- css文件 -->
<link href="<%=request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">

<!-- js文件 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	/** ---- 将当前用户的用户名赋值给搜索框下的隐藏input ---  **/
	var username = $("#nameShow").text().split(":")[1];
	$("#username").val(username);
	
	var table = $("#mainTable").DataTable({
		scrollX : true
	});
	
	$("#searchButton").click(function(){
		$.ajax({
			url : 'searchApplication.action',
			type : 'post',
			dataType : 'json',
			data : $("#searchApplicationForm").serialize(),
			success : function(e){
				var data = eval("(" + e + ")");
				for (var i = 0; i < data.length; i++) {
					var obj = [
					           data[i].id,
					           data[i].classCode,
					           data[i].applyTime,
					           data[i].applicant,
					           data[i].state
					           ];
					table.row.add(obj).draw(false);
				}
			},
			error : function(e){
				alert("系统错误！请检查网络连接！");
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
  				<li role="presentation"><a href="<%=request.getContextPath()%>/manager/addClass.jsp">申请添加班级</a></li>
  				<li role="presentation" class="active"><a href="#">查看申请进度</a></li>
			</ul>
		</div>
		<div class="navbar-footer"></div>
	</div>
</nav>
<br/>
<br/>
<br/>
<br/>
<div style="width: 80%;margin-left: auto;margin-right: auto;" class="well well-lg">
	<form class="form-inline" id="searchApplicationForm">
  		<div class="form-group">
    		<div class="input-group">
      			<div class="input-group-addon">起始日期</div>
      			<input type="text" class="form-control" id="beginDate" name="beginDate" onclick="WdatePicker()" placeholder="起始日期">
    		</div>
  		</div>
  		&nbsp;&nbsp;&nbsp;&nbsp;
  		<div class="form-group">
    		<div class="input-group">
      			<div class="input-group-addon">截止日期</div>
      			<input type="text" class="form-control" id="endDate" name="endDate" onclick="WdatePicker()" placeholder="截止日期">
    		</div>
  		</div>
  		<input type="hidden" id="username" name="username">
  		&nbsp;&nbsp;&nbsp;
  		<input type="button" class="btn btn-primary" id="searchButton" value="查询" data-loading-text="查询中">
	</form>
</div>
<br>
<div id="dataShowDiv" class="panel panel-primary" style="width: 80%;margin-left: auto;margin-right: auto;white-space:nowrap;">
	<div class="panel-heading">学员列表</div>
	<table style="width: 100%" aria-describedby="example_info" class="table table-striped table-bordered dataTable" id="mainTable">
		<thead>
			<tr>
				<th>ID</th>
				<th>班级编码</th>
				<th>申请时间</th>
				<th>申请人</th>
				<th>申请状态</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
		<tfoot>
			<tr>
				<th>ID</th>
				<th>班级编码</th>
				<th>申请时间</th>
				<th>申请人</th>
				<th>申请状态</th>
			</tr>
		</tfoot>
	</table>
</div>
</body>
</html>