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
	var table = $("#mainTable").DataTable({
		scrollX : true
	});
	
	$("#searchButton").click(function(){
		$.ajax({
			url : 'viewApplication.action',
			type : 'post',
			dataType : 'json',
			data : $("#searchApplicationForm").serialize(),
			success : function(e){
				var data = eval("(" + e + ")");
				var operationColumn = "<button name=\'approve\' class=\'btn btn-primary\'>审批</button>"
				for (var i = 0; i < data.length; i++) {
					var obj = [
					           data[i].id,
					           data[i].classCode,
					           data[i].applyTime,
					           data[i].applicant,
					           data[i].state,
					           operationColumn
					           ];
					table.row.add(obj).draw(false);
				}
			},
			error : function(e){
				alert("系统错误！请检查网络连接！");
			}
		});
	});
	
	$("#mainTable").on("click","button[name='approve']",function(e){
		var td = $(this).parent();
		var tr = $(td).parent();
		var tds = $(tr).children("td");
		
		$("#id_approve_td").text($(tds[0]).text());
		$("#classCode_approve_td").text($(tds[1]).text());
		$("#time_approve_td").text($(tds[2]).text());
		$("#applicant_approve_td").text($(tds[3]).text());
		$("#state_approve_td").text($(tds[4]).text());
		
		$("#approveModal").modal({
			keyboard : true
		});
	});
	
	$("#submitApprove").click(function(e){
		var applicationId = $("#id_approve_td").text();
		var classCode = $("#classCode_approve_td").text();
		var approveResult = $("#approveSelect").val();
		
		var param = {	
						'applicationId' : applicationId,
						'classCode' : classCode, 
						'approveResult' : approveResult
					};
		
		$.ajax({
			url : 'approveApplication.action',
			type : 'post',
			data : param,
			dataType : 'json',
			success : function(e){
				var data = eval("(" + e + ")");
				if (data.result == "success") {
					alert("审批成功！");
				}else {
					alert("审批失败！");
				}
			},
			error : function(e){
				alert("系统错误！请联系管理员！");
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
  				<li role="presentation"><a href="<%=request.getContextPath() %>/president/manageUser.jsp">管理系统用户</a></li>
  				<li role="presentation" class="active"><a href="#">增班申请</a></li>
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
				<th>操作</th>
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
				<th>操作</th>
			</tr>
		</tfoot>
	</table>
</div>

<!-- 修改密码 -->
<div class="modal fade" id="approveModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">增班审批</h4>
      </div>
      <div class="modal-body"> 
      	<table class="table table-bordered">
      		<tr>
      			<td>ID</td>
      			<td id="id_approve_td"></td>
      			<td>班级编码</td>
      			<td id="classCode_approve_td"></td>
      		</tr>
      		<tr>
      			<td>申请时间</td>
      			<td id="time_approve_td"></td>
      			<td>申请人</td>
      			<td id="applicant_approve_td"></td>
      		</tr>
      		<tr>
      			<td>申请状态</td>
      			<td id="state_approve_td"></td>
      			<td>审批</td>
      			<td>
      				<select id="approveSelect">
      					<option value="1">通过申请</option>
      					<option value="0">驳回申请</option>
      				</select>
      			</td>
      		</tr>
      	</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" id="submitApprove" class="btn btn-primary">确认</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>