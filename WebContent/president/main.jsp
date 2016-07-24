<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.shine.dto.Opportunity" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
$(document).ready(function(e){
	var table = $("#mainTable").DataTable({});
	
	$("#mainTable").on("click","button[name='assign']",function(e){
		var td = $(this).parent();
		var tr = $(td).parent();
		var tds = $(tr).children("td");
		
		$("#assign_id").text($(tds[0]).text());
		$("#assign_stuName").text($(tds[1]).text());
		$("#assign_parentName").text($(tds[2]).text());
		$("#assign_contactTel1").text($(tds[3]).text());
		$("#assign_contactTel2").text($(tds[4]).text());
		$("#assign_address").text($(tds[5]).text());
		
		//获取所有客服人员
		$.ajax({
			url : 'getCSUser.action',
			type : 'post',
			dataType : 'json',
			success : function(e){
				var data = eval("(" + e + ")");
				for (var i = 0; i < data.length; i++) {
					$("#assign_cs").append("<option value=\"" + data[i].id +"\">" + data[i].name +"</option>")	
				}
			},
		});
		
		$("#assignOppModal").modal({
			keyboard : true
		});
	});
	
	$("#btn_submitAssign").click(function(e){
		$.ajax({
			url : 'assignOpp.action',
			type : 'post',
			data : {'oppId' : $("#assign_id").text(),'csId' : $("#assign_cs").val()},
			dataType : 'json',
			success : function(e){
				var data = eval("(" + e + ")");
				var result = data.result;
				if (result == "success") {
					alert("分配成功！");
				}else {
					alert("分配失败！");
				}
				window.location.reload();
			},
			error : function(e){
				alert("系统出错！请联系管理员！");
			}
		})
	});
	
	
	$("#nameShow").click(function(){
		$("#modifyPswModal").modal({
			keyboard : true
		});
	});
	
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
  				<li role="presentation" class="active"><a href="#">未分配商机</a></li>
  				<li role="presentation"><a href="<%=request.getContextPath() %>/president/manageUser.jsp">管理系统用户</a></li>
			</ul>
		</div>
	</div>
</nav>
<br/>
<br/>
<br/>
<br/>
<div id="dataShowDiv" class="panel panel-primary" style="width: 90%;margin-left: auto;margin-right: auto;white-space:nowrap;">
	<div class="panel-heading">未分配商机</div>
	<table style="width: 100%" aria-describedby="example_info" class="table table-striped table-bordered dataTable" id="mainTable">
		<thead>
			<tr>
				<th>ID</th>
				<th>学员姓名</th>
				<th>家长姓名</th>
				<th>联系方式1</th>
				<th>联系方式2</th>
				<th>家庭地址</th>
				<th>预约时间</th>
				<th>是否到店</th>
				<th>到店时间</th>
				<th>是否成单</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<% 
			List<Opportunity> oppList = (List<Opportunity>)request.getAttribute("oppList");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		%>
		<% for(int i = 0; i < oppList.size(); i++){ %>
			<tr>
				<td><%=oppList.get(i).getId() %></td>
				<td><%=oppList.get(i).getName() %></td>
				<td><%=oppList.get(i).getParentName() %></td>
				<td><%=oppList.get(i).getContactTel1() %></td>
				<td><%=oppList.get(i).getContactTel2() %></td>
				<td><%=oppList.get(i).getAddress() %></td>
				<%if(oppList.get(i).getOrderTime() != null){ %>
					<td><%=sdf.format(oppList.get(i).getOrderTime()) %></td>
				<%} else{%>
					<td></td>
				<%} %>
				<%if(oppList.get(i).getIsArrive() == 0){ %>
					<td>未到店</td>
				<%} else if(oppList.get(i).getIsArrive() == 1){%>
					<td>已到店</td>
				<%} %>
				<%if(oppList.get(i).getArriveTime() != null){ %>
					<td><%=sdf.format(oppList.get(i).getArriveTime()) %></td>
				<%} else{%>
					<td></td>
				<%} %>
				<%if(oppList.get(i).getIsDeal() == 0){ %>
					<td>未成单</td>
				<%} else if(oppList.get(i).getIsDeal() == 1){%>
					<td>已成单</td>
				<%} %>
				<td>
					<button class="btn btn-primary" name="assign">分配</button>
				</td>
			</tr>
		<%} %>
		</tbody>
		<tfoot>
			<tr>
				<th>ID</th>
				<th>学员姓名</th>
				<th>家长姓名</th>
				<th>联系方式1</th>
				<th>联系方式2</th>
				<th>家庭地址</th>
				<th>预约时间</th>
				<th>是否到店</th>
				<th>到店时间</th>
				<th>是否成单</th>
				<th>操作</th>
			</tr>
		</tfoot>
	</table>
</div>
<!-- Assign Modal -->
<div class="modal fade" id="assignOppModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">分配商机</h4>
      </div>
      <div class="modal-body"> 
       	<table id="assignTable" class="table table-bordered">
			<tr>
        		<td>id</td>
        		<td id="assign_id"></td>
        		<td>学员姓名</td>
        		<td id="assign_stuName"></td>
        	</tr>
			<tr>
				<td>联系方式1</td>
				<td id="assign_contactTel1"></td>
				<td>联系方式2</td>
				<td id="assign_contactTel2"></td>
			</tr>
			<tr>
				<td>家长姓名</td>
				<td id="assign_parentName"></td>
				<td>家庭地址</td>
				<td id="assign_address"></td>
			</tr>
			<tr>
				<td>客服</td>
				<td colspan="3">
					<select id="assign_cs"></select>
				</td>
			</tr>
	 	</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" id="btn_submitAssign" class="btn btn-primary">提交</button>
      </div>
    </div>
  </div>
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