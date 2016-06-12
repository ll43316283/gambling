<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
<link href="../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"> 
<link href="../resources/fileinput/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
<script src="../resources/jquery/jquery-1.9.1.min.js"></script>
<!-- canvas-to-blob.min.js is only needed if you wish to resize images before upload.
     This must be loaded before fileinput.min.js -->
<script src="../resources/fileinput/js/canvas-to-blob.min.js" type="text/javascript"></script>
   
<script src="../resources/fileinput/js/fileinput.min.js"></script>
<script src="../resources/bootstrap/js/bootstrap.min.js"></script>
 <script src="../resources/chart/Chart.bundle.js"></script> 
 
 
<title>math040</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style type="text/css">
 	.kv-avatar .file-preview-frame,.kv-avatar .file-preview-frame:hover {
	    margin: 0;
	    padding: 0;
	    border: none;
	    box-shadow: none;
	    text-align: center;
	}
	.kv-avatar .file-input {
	    display: table-cell;
	    max-width: 220px;
	}
    </style>
</head>
<body>
<c:set var="menu" scope="request" value="statistics"/>  
 <div class="container-fluid">
	<div class="row">
		<div class="col col-md-12 col-sm-12">
		 <jsp:include page="menu.jsp" flush="true">
				 	<jsp:param name="menu" value="${menu}"/> 
		</jsp:include> 
		
		 
		 <div id="kv-avatar-errors-1" class="center-block" style="width:800px;display:none"></div>
			<form class="text-center" action="<c:url value="/user"/>/<sec:authentication property="principal.username" /> " method="post" enctype="multipart/form-data">
			    <div class="kv-avatar center-block" style="width:200px">
			        <input id="file" name="file" type="file" class="file-loading">
			    </div>
			      <button type="submit"   class="btn btn-primary">提交</button> 
			    <!-- include other inputs if needed and include a form submit (save) button -->
			</form>
		 
		 
		</div>
	</div>
 </div>
    <script> 
     
    
		$("#file").fileinput({
		    overwriteInitial: true,
		    maxFileSize: 4000,
		    showClose: false,
		    showCaption: false,
		    browseLabel: '',
		    removeLabel: '',
		    browseIcon: '<i class="glyphicon glyphicon-folder-open"></i>',
		    removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
		    removeTitle: 'Cancel or reset changes',
		    elErrorContainer: '#kv-avatar-errors-1',
		    msgErrorClass: 'alert alert-block alert-danger',
		    defaultPreviewContent: '<img src="../resources/image/user_default_avatar.jpg" alt="Your Avatar" style="width:160px">',
		    layoutTemplates: {main2: '{preview} ' + ' {remove} {browse}'},
		    allowedFileExtensions: ["jpg", "png", "gif"]
		});
    </script>
</body>

</html>