<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>批量导入商机</title>
<link href="<%=request.getContextPath()%>/uploadify/uploadify.css" rel="stylesheet"  type="text/css" media="screen"/>
<link href="<%=request.getContextPath()%>/bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	/** 配置上传组件 **/
	$("#file_upload").uploadify({
		'swf' : '<%=request.getContextPath()%>/uploadify/uploadify.swf',  
	    'uploader' : '<%=request.getContextPath()%>/importExcelOpp.action',  
	    'cancelImg'      : '<%=request.getContextPath()%>/img/uploadify-cancel.png',  
	    'folder'         : 'UploadFiles',  
	    'queueID'        : 'some_file_queue',  
	    'auto'           : false,  
	    'multi'          : true,  
	    'simUploadLimit' : 2,
	    'fileObjName' : 'file_upload',
	    'buttonText' : '选择数据文件',
	    'onUploadStart' : function(file) {
	        $("#file_upload").uploadify('settings','formData',{'fileName':file.name,'username':$("#usernameShow").text().split(":")[1]});
	    },
	    'onUploadSuccess' : function(file, data, response) {
	    	var a = JSON.parse(data);
	    	if(a.result == "success"){
	    		alert("上传成功！");
	    	}else if(a.result == "fail"){
	    		alert("上传失败！");
	    	}else if(a.result == "null"){
	    		alert("请不要上传空文件！");
	    	}
        }
	});
	
	/** 启动上传组件 **/
	$("#importExcel").click(function(){
		$("#file_upload").uploadify("upload");
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
<nav class="navbar navbar-default">
	<div class="container-fluid" style="padding-top: 5px;">
		<div class="navbar-header">
			<a class="navbar-brand" href="#" style="padding-top: 5px;">
        		<img alt="" src="<%=request.getContextPath()%>/img/index_logo.png">
      		</a>
		</div>
		<div class="collapse navbar-collapse" style="margin-left: auto;margin-right: auto;width: 70%;">
			<ul class="nav nav-pills">
				<li role="presentation"><a id="nameShow" href="#">欢迎您:<%=username %></a></li>
  				<li role="presentation"><a href="<%=request.getContextPath()%>/channel/singleInputOpp.jsp">录入商机</a></li>
  				<li role="presentation" class="active"><a href="#">导入商机</a></li>
  				<li role="presentation"><a href="<%=request.getContextPath()%>/channel/deal.jsp">成单</a></li>
			</ul>
		</div>
	</div>
</nav>
<div class="panel panel-primary" style="width: 65%;margin-left: auto;margin-right: auto;">
	<div class="panel-heading">上传商机</div>
  	<div class="panel-body">
    	<input type="file" name="file_upload" id="file_upload"/>
		<div id="some_file_queue"></div>
		<button id="importExcel" class="btn btn-primary">上传数据</button>
  	</div>
</div>
<div class="panel panel-primary" style="width: 65%;margin-left: auto;margin-right: auto;">
	<div class="panel-heading">下载模板</div>
  	<div class="panel-body">
    	<a href="downloadModal.action" class="btn btn-primary">下载模板</a>
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