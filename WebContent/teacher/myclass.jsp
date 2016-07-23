<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的商机</title>
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
	var tab = $("#mainTable").DataTable({
		"scrollX" : true
	});
	
	$.ajax({
		url : 'getClassByTeacher.action',
		type : 'post',
		dataType : 'json',
		data : {'username' : $("#nameShow").text().split(':')[1]},
		success : function(e){
			var data = eval("(" + e + ")");
			for (var i = 0; i < data.length; i++) {
				/* var obj = [data[i].id,data[i].level,data[i].classCode,data[i].beginDate,
				           data[i].endDate,data[i].beginWeek,data[i].beginTime,data[i].foreignTeacher,
				           data[i].chinaTeacher,data[i].classManager,data[i].currentNum,
				           data[i].fee];
				tab.row.add(obj).draw(false); */
				/** 添加班级编码 **/
				$("#classCode").append("<option value=\"" + data[i].classCode + "\">" + data[i].classCode + "</option>")
			}
		},
		error : function(e){
			alert("系统出错！请联系管理员！");
		}
	});
	
	$("#searchButton").click(function(){
		$.ajax({
			url : 'getClassByTeacher.action',
			type : 'post',
			dataType : 'json',
			data : {'username' : $("#nameShow").text().split(':')[1]},
			success : function(e){
				var data = eval("(" + e + ")");
				for (var i = 0; i < data.length; i++) {
					var obj = [data[i].id,data[i].level,data[i].classCode,data[i].beginDate,
					           data[i].endDate,data[i].beginWeek,data[i].beginTime,data[i].foreignTeacher,
					           data[i].chinaTeacher,data[i].classManager,data[i].currentNum,
					           data[i].fee];
					tab.row.add(obj).draw(false);
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
  				<li role="presentation"><a href="#">我的班级</a></li>
			</ul>
		</div>
		<div class="navbar-footer"></div>
	</div>
</nav>
<br/>
<br/>
<br/>
<br/>
<!-- 查询我的班级条件DIV -->
<div style="width: 80%;margin-left: auto;margin-right: auto;" class="well well-lg">
	<form class="form-inline" id="dealFindOppForm">
  		<div class="form-group">
    		<div class="input-group">
      			<div class="input-group-addon">班级编码</div>
      			<select class="form-control" name="classCode" id="classCode">
      			</select>
    		</div>
  		</div>
  		&nbsp;&nbsp;&nbsp;
  		<input type="button" class="btn btn-primary" id="searchButton" value="查询" data-loading-text="查询中">
	</form>
</div>

<!-- 数据展示DIV -->
<div id="dataShowDiv" class="panel panel-primary" style="width: 80%;margin-left: auto;margin-right: auto;white-space:nowrap;">
	<div class="panel-heading">我的班级</div>
	<table style="width: 100%" aria-describedby="example_info" class="table table-striped table-bordered dataTable" id="mainTable">
		<thead>
			<tr>
				<th>ID</th>
				<th>班级级别</th>
				<th>班级编码</th>
				<th>开课日期</th>
				<th>结课日期</th>
				<th>上课周期</th>
				<th>上课时间</th>
				<th>外教老师</th>
				<th>中教老师</th>
				<th>班主任</th>
				<th>当前人数</th>
				<th>班级学费</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
		<tfoot>
			<tr>
				<th>ID</th>
				<th>班级级别</th>
				<th>班级编码</th>
				<th>开课日期</th>
				<th>结课日期</th>
				<th>上课周期</th>
				<th>上课时间</th>
				<th>外教老师</th>
				<th>中教老师</th>
				<th>班主任</th>
				<th>当前人数</th>
				<th>班级学费</th>
			</tr>
		</tfoot>
	</table>
</div>

</body>
</html>