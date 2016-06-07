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
	var table = $("#mainTable").DataTable({});
	
	$("#searchButton").click(function(e){
		$.ajax({
			url : 'getOppChannel',
			type : 'post',
			dataType : 'json',
			data : $("#dealFindOppForm").serialize(),
			success : function(e){
				table.clear().draw(false);
				var data = eval("(" + e + ")");
				var operation = "<button name=\"markToDeal\" class=\"btn btn-primary\">标为已成单</button>"
				for (var i = 0; i < data.length; i++) {
					var obj = [
					           	data[i].id,data[i].name,data[i].parentName,data[i].contactTel1,
					           	data[i].contactTel2,data[i].address,data[i].orderTime,
					           	data[i].isArrive,data[i].arriveTime,data[i].isDeal,operation
					           ]
					table.row.add(obj).draw(false);
				}
			},
			error : function(e){
				alert("系统出错！请联系管理员！");				
			}
		});
	});
	
	$("#mainTable").on("click","button[name='markToDeal']",function(e){
		$.ajax({
			url : 'getShineClassChannel',
			type : 'post',
			dataType : 'json',
			success : function(e){
				var data = eval("(" + e + ")");
				for (var i = 0; i < data.length; i++) {
					$("#classCode").append("<option value=\"" + data[i].classCode + "\">" + data[i].classCode + "</option>");	
				}
			},
			error : function(e){
				alert("系统出错！请联系管理员！");
			}
		});
		
		var td = $(this).parent();
		var tr = $(td).parent();
		var tds = $(tr).children();
		$("#deal_id").text($(tds[0]).text());
		$("#deal_stuName").text($(tds[1]).text());
		$("#deal_contactTel1").text($(tds[3]).text());
		$("#deal_contactTel2").text($(tds[4]).text());
		
		$("#dealModal").modal({
			keyboard : true
		})
	});
	
	$("#btn_submitDeal").click(function(e){
		if (confirm("确认成单吗？")) {
			$.ajax({
				url : 'markToDealChannel.action',
				type : 'post',
				dataType : 'json',
				data : {'id' : $("#deal_id").text(),'classCode' : $("#classCode").val()},
				success : function(e){
					var data = eval("(" + e + ")");
					var result = data.result;
					if (result == "success") {
						alert("成功！");
					}else {
						alert("失败！");
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
				<li role="presentation"><a href="#">欢迎您:<%=username %></a></li>
  				<li role="presentation"><a href="<%=request.getContextPath()%>/channel/singleInputOpp.jsp">录入商机</a></li>
  				<li role="presentation"><a href="<%=request.getContextPath()%>/channel/importOpp.jsp">批量导入</a></li>
  				<li role="presentation" class="active"><a href="#">成单</a></li>
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
	<form class="form-inline" id="dealFindOppForm">
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
  		&nbsp;&nbsp;&nbsp;&nbsp;
  		<div class="form-group">
    		<div class="input-group">
      			<div class="input-group-addon">电话号码</div>
      			<input type="text" class="form-control" id="contactTel" name="contactTel" placeholder="电话号码">
    		</div>
  		</div>
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
<!-- deal modal -->
<div class="modal fade" id="dealModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">成单</h4>
      </div>
      <div class="modal-body"> 
        <table id="dealTable" class="table table-bordered"> 
        	<tr>
        		<td>id</td>
        		<td id="deal_id"></td>
        		<td>学员姓名</td>
        		<td id="deal_stuName"></td>
        	</tr>
			<tr>
				<td>联系方式1</td>
				<td id="deal_contactTel1"></td>
				<td>联系方式2</td>
				<td id="deal_contactTel2"></td>
			</tr>
			<tr>
				<td>选择班级</td>
				<td colspan="3">
					<select id="classCode" name="classCode">
					</select>
				</td>
			</tr>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" id="btn_submitDeal" class="btn btn-primary">提交</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>