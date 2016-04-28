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
	$("#importExcel").click(function(){
		$("#file_upload").uploadify("upload");
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
				<li role="presentation"><a id="usernameShow" href="#">欢迎您:<%=username %></a></li>
  				<li role="presentation"><a href="<%=request.getContextPath()%>/channel/singleInputOpp.jsp">录入商机</a></li>
  				<li role="presentation" class="active"><a href="#">导入商机</a></li>
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
</body>
</html>