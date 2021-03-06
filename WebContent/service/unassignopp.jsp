<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.shine.dto.Opportunity" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>未分配商机</title>
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
	var table = $("#mainTable").DataTable({});
	
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
	/* $("#mainTable").on("click","button[name=recive]",function(){
		var $btn = $(this).button("loading");
		var td = $(this).parent();
		var tr = $(td).parent();
		var tds = $(tr).children("td");
		$("#handle_stuNameTd").text($(tds[0]).text());
		$.ajax({
			url : 'reciveOppByCS.action',
			type : 'post',
			dataType : 'json',
			data : {'name' : $("#nameShow").html().split(':')[1],'id' : $(tds[0]).text()},
			success : function(e){
				var data = eval("(" + e + ")");
				if (data.result == "success") {
					alert("接收成功！");
					$btn.button("reset");
					window.location.reload();
				}else if (data.result == "fail") {
					alert("接收失败！");
					$btn.button("reset");
				}else if (data.result == "recived") {
					alert("已经被接取，请刷新页面！");
					$btn.button("reset");
				}
			},
			error : function(e){
				alert("接收失败！请联系管理员！");
				$btn.button("reset");
			}
		})
	}) */
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
  				<li role="presentation" class="active"><a href="#">未分配新生</a></li>
  				<li role="presentation"><a href="<%=request.getContextPath() %>/service/myopp.jsp">我的商机</a></li>
			</ul>
		</div>
		<div class="navbar-footer"></div>
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
				<th>英文名</th>
				<th>联系方式1</th>
				<th>联系方式2</th>
				<th>家庭地址</th>
				<th>预约时间</th>
				<th>是否到店</th>
				<th>到店时间</th>
				<th>是否成单</th>
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
				<td><%=oppList.get(i).getEnglishName() %></td>
				<td><%=oppList.get(i).getContactTel1() %></td>
				<td><%=oppList.get(i).getContactTel2() %></td>
				<%if(oppList.get(i).getAddress() == null || "".equals(oppList.get(i).getAddress())){ %>
					<td>无</td>
				<%} else {%>
					<td><%=oppList.get(i).getAddress() %></td>
				<%} %>
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
			</tr>
		<%} %>
		</tbody>
		<tfoot>
			<tr>
				<th>ID</th>
				<th>学员姓名</th>
				<th>英文名</th>
				<th>联系方式1</th>
				<th>联系方式2</th>
				<th>家庭地址</th>
				<th>预约时间</th>
				<th>是否到店</th>
				<th>到店时间</th>
				<th>是否成单</th>
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