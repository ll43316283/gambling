<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script src="../resources/jquery/jquery-1.9.1.min.js"></script>
<link href="../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"> 
 <script src="../resources/bootstrap/js/bootstrap.min.js"></script>
<title>math040</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 
</head>
<body>
 <div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3>
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 任何漏洞都躲不过李大神
			</h3>
			<div class="alert alert-danger" role="alert">
			  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			  <span class="sr-only">Error:</span> 
			  ${ex.message }
			</div>
		</div>
	</div>
</div>
</body>
</html>