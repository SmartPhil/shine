<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>I SHINE ENGLISH</title>
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
	$("#submitAddClass").click(function(){
		$.ajax({
			url : 'addClass.action',
			type : 'post',
			dataType : 'json',
			data : $("#addClassForm").serialize(),
			success : function(e){
				var data = eval("(" + e + ")");
				var result = data.result;
				if (result == "success") {
					alert("添加成功！");
					window.location.reload();
				}else if (result == "fail") {
					alert("添加失败！");
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
  				<li role="presentation" class="active"><a href="#">添加班级</a></li>
			</ul>
		</div>
		<div class="navbar-footer"></div>
	</div>
</nav>
<br/>
<br/>
<br/>
<br/>
<div id="addClassDiv" style="width: 65%;margin-left: auto;margin-right: auto;">
	<form id="addClassForm">
		<div class="form-group">
  			<p class="bg-primary">注意：带*号为必填项</p>	
  		</div>
  		<div class="form-group">
  			<label for="level">级别</label>
    		<input type="text" class="form-control" id="level" name="level" placeholder="请输入班级级别">
  		</div>
  		<div class="form-group">
  			<label for="classCode">班级编码<span class="badge">*</span></label>
    		<input type="text" class="form-control" id="classCode" name="classCode" placeholder="请输入班级编码">
  		</div>
  		<div class="form-group">
    		<label for="beginDate">开课日期</label>
    		<input type="text" class="form-control" id="beginDate" name="beginDate" onclick="WdatePicker()" placeholder="请输入开课日期">
  		</div>
  		<div class="form-group">
    		<label for="endDate">结课日期</label>
    		<input type="text" class="form-control" id="endDate" name="endDate" onclick="WdatePicker()" placeholder="请输入结课日期">
  		</div>
  		<div class="form-group">
    		<label for="beginWeek">上课周期</label>
    		<input type="text" class="form-control" id="beginWeek" name="beginWeek" placeholder="请输入上课周期">
  		</div>
  		<div class="form-group">
    		<label for="beginTime">上课时间</label>
    		<input type="text" class="form-control" id="beginTime" name="beginTime" placeholder="请输入上课时间">
  		</div>
  		<div class="form-group">
    		<label for="foreignTeacher">外教</label>
    		<input type="text" class="form-control" id="foreignTeacher" name="foreignTeacher" placeholder="请输入外教姓名">
  		</div>
  		<div class="form-group">
  			<label for="chinaTeacher">中教</label>
    		<input type="text" class="form-control" id="chinaTeacher" name="chinaTeacher" placeholder="请输入中教姓名">
  		</div>
  		<div class="form-group">
    		<label for="classManager">班主任</label>
    		<input type="text" class="form-control" id="classManager" name="classManager" placeholder="请输入班主任姓名">
  		</div>
  		<div class="form-group">
    		<label for="fee">班主任</label>
    		<input type="text" class="form-control" id="fee" name="fee" placeholder="请输入学费">
  		</div>
  		<input type="button" data-loading-text="提交中……" class="btn btn-primary" id="submitAddClass" value="提交"/>
	</form>
</div>
</body>
</html>