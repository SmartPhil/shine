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
$(document).ready(function(e){
	var table = $("#mainTable").DataTable({});
	$.ajax({
		url : 'getMyOppByCS.action',
		type : 'post',
		data : {'username' : $("#nameShow").text().split(':')[1]},
		datatype : 'json',
		success : function(e){
			table.clear().draw(false);
			var data = eval("(" + e + ")");
			var operationRow = "<button name=\"follow\" class=\"btn btn-primary\">跟进</button>&nbsp;"
							   + "<button name=\"release\" class=\"btn btn-primary\">释放</button>";
			for (var i = 0; i < data.length; i++) {
				var obj = [
				           data[i].id,data[i].stuName,data[i].parentName,data[i].contactTel1,
				           data[i].contactTel2,data[i].address,data[i].orderTime,data[i].isArrive,
				           data[i].arriveTime,data[i].isDeal,operationRow
				          ];
				table.row.add(obj).draw(false);
			}
		},
		error : function(e){
			alert("系统出错！请联系管理员！");
		}
	});
	
	$("#mainTable").on("click","button[name='follow']",function(e){
		var td = $(this).parent();
		var tr = $(td).parent();
		var tds = $(tr).children("td");
		
		$("#follow_id").text($(tds[0]).text());
		$("#follow_stuName").text($(tds[1]).text());
		$("#follow_parentName").text($(tds[2]).text());
		$("#follow_contactTel1").text($(tds[3]).text());
		$("#follow_contactTel2").text($(tds[4]).text());
		$("#follow_address").text($(tds[5]).text());
		
		$.ajax({
			url : 'getFollowContentByOppId.action',
			type : 'post',
			data : {'oppId' : $(tds[0]).text()},
			dataType : 'json',
			success : function(e){
				$("#followContentTable tbody").html("");
				var data = eval("(" + e + ")");
				for(var i = 0; i < data.length; i++){
					$("#followContentTable tbody").append("<tr>"
							+ "<td>" + data[i].followTime + "</td>"
							+ "<td>" + data[i].followContent + "</td>"
							+ "<td>" + data[i].follower + "</td>"
							+ "</tr>")
				}
			},
			error : function(e){
				alert("系统出错！请联系管理员！");
			}
		});
		
		$("#followOppModal").modal({
			keyboard : true
		});
	});
	
	$("#btn_submitFollow").click(function(e){
		$.ajax({
			url : 'insertFollowContent.action',
			type : 'post',
			data : {
				'oppId' : $("#follow_id").text(),
				'followContent' : $("#follow_Content").val(),
				'follower' : $("#nameShow").text().split(':')[1]
			},
			dataType : 'json',
			success : function(e){
				var data = eval("(" + e + ")");
				var result = data.result;
				if (result == "success") {
					alert("插入成功！");
				}else {
					alert("插入失败！");
				}
			},
			error : function(e){
				alert("系统出错！请联系管理员！");
			}
		});
	});
	
	$("#mainTable").on("click","button[name='release']",function(e){
		if (confirm("确定释放吗？")) {
			var td = $(this).parent();
			var tr = $(td).parent();
			var tds = $(tr).children("td");
			
			$.ajax({
				url : 'releaseOpp.action',
				type : 'post',
				data : {'oppId' : $(tds[0]).text()},
				dataType : 'json',
				success : function(e){
					var data = eval("(" + e + ")");
					var result = data.result;
					if (result == "success") {
						alert("释放成功！");
					}else {
						alert("释放失败！");
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
				<li role="presentation"><a id="nameShow" href="#">欢迎您:<%=username %></a></li>
  				<li role="presentation"><a href="<%=request.getContextPath() %>/getUnAssignOpp.action">未分配商机</a></li>
  				<li role="presentation" class="active"><a href="#">我的商机</a></li>
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
	<div class="panel-heading">我的商机</div>
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

<!-- follow modal -->
<div class="modal fade" id="followOppModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">跟进商机</h4>
      </div>
      <div class="modal-body"> 
      	<table id="followContentTable" border="1" class="table table-bordered">
			<thead>
				<tr>
					<td>跟进时间</td>
					<td>跟进内容</td>
					<td>跟进人</td>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
        <table class="table table-bordered"> 
        	<tr>
        		<td>id</td>
        		<td id="follow_id"></td>
        		<td>学员姓名</td>
        		<td id="follow_stuName"></td>
        	</tr>
			<tr>
				<td>联系方式1</td>
				<td id="follow_contactTel1"></td>
				<td>联系方式2</td>
				<td id="follow_contactTel2"></td>
			</tr>
			<tr>
				<td>家长姓名</td>
				<td id="follow_parentName"></td>
				<td>家庭地址</td>
				<td id="follow_address"></td>
			</tr>
			<tr>
				<td>内容</td>
				<td colspan="3">
					<textarea id="follow_Content" rows="4" cols="65"></textarea>
				</td>
			</tr>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" id="btn_submitFollow" class="btn btn-primary">提交</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>