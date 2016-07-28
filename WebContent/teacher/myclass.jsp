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
			url : 'getClassInfo.action',
			type : 'post',
			dataType : 'json',
			data : {'classCode' : $("#classCode").val()},
			success : function(e){
				var data = eval("(" + e + ")");
				var classInfo = data.classInfoJsonString;
				var studentInfo = data.studentInfoJsonString;
				var classInfoJson = eval("(" + classInfo + ")");
				var studentInfoJson = eval("(" + studentInfo + ")");
				/** 清空班级详情里面的数据 **/
				$("#classInfoTable tbody").html("");
				/** 展现班级详情 **/
				for (var i = 0; i < classInfoJson.length; i++) {
					$("#classInfoTable tbody").append("<tr>");
					$("#classInfoTable tbody").append("<td>" + classInfoJson[i].id + "</td>");
					$("#classInfoTable tbody").append("<td>" + classInfoJson[i].level + "</td>");
					$("#classInfoTable tbody").append("<td>" + classInfoJson[i].classCode + "</td>");
					$("#classInfoTable tbody").append("<td>" + classInfoJson[i].beginDate + "</td>");
					$("#classInfoTable tbody").append("<td>" + classInfoJson[i].endDate + "</td>");
					$("#classInfoTable tbody").append("<td>" + classInfoJson[i].beginWeek + "</td>");
					$("#classInfoTable tbody").append("<td>" + classInfoJson[i].beginTime + "</td>");
					$("#classInfoTable tbody").append("<td>" + classInfoJson[i].foreignTeacher + "</td>");
					$("#classInfoTable tbody").append("<td>" + classInfoJson[i].chinaTeacher + "</td>");
					$("#classInfoTable tbody").append("<td>" + classInfoJson[i].classManager + "</td>");
					$("#classInfoTable tbody").append("<td>" + classInfoJson[i].currentNum + "</td>");
					$("#classInfoTable tbody").append("<td>" + classInfoJson[i].fee + "</td>");
					$("#classInfoTable tbody").append("</tr>")
				}
				
				/** 展现学员信息详情  **/
				tab.clear().draw(false);
				for (var j = 0; j < studentInfoJson.length; j++) {
					var obj = [	
					           	studentInfoJson[j].id,
					           	studentInfoJson[j].name,
					           	studentInfoJson[j].englishName,
					           	studentInfoJson[j].parentName,
					           	studentInfoJson[j].contactTel1,
					           	studentInfoJson[j].contactTel2,
					           	studentInfoJson[j].address,
					           	studentInfoJson[j].school,
			           		];
					tab.row.add(obj).draw(false);
				}
			},
			error : function(e){
				alert("系统出错！请联系管理员！");
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

<!-- 班级详情DIV -->
<div id="classInfoDiv" class="panel panel-primary" style="width: 80%;margin-left: auto;margin-right: auto;white-space:nowrap;">
	<div class="panel-heading">班级详情</div>
	<table style="width: 100%;text-align: center;" aria-describedby="example_info" class="table table-bordered" id="classInfoTable">
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

<!-- 学员详情DIV -->
<div id="dataShowDiv" class="panel panel-primary" style="width: 80%;margin-left: auto;margin-right: auto;white-space:nowrap;">
	<div class="panel-heading">班级学员</div>
	<table style="width: 100%" aria-describedby="example_info" class="table table-striped table-bordered dataTable" id="mainTable">
		<thead>
			<tr>
				<th>ID</th>
				<th>中文姓名</th>
				<th>英文姓名</th>
				<th>父母姓名</th>
				<th>联系方式1</th>
				<th>联系方式2</th>
				<th>家庭住址</th>
				<th>就读学校</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
		<tfoot>
			<tr>
				<th>ID</th>
				<th>中文姓名</th>
				<th>英文姓名</th>
				<th>父母姓名</th>
				<th>联系方式1</th>
				<th>联系方式2</th>
				<th>家庭住址</th>
				<th>就读学校</th>
			</tr>
		</tfoot>
	</table>
</div>

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