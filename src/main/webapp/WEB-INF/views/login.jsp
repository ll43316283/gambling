<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <script src="resources/jquery/jquery-1.9.1.min.js"></script>
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"> 
<link href="resources/bootstrap/css/bootstrapValidator.min.css" rel="stylesheet">  
 <script src="resources/bootstrap/js/bootstrap.min.js"></script> 
 <script src="resources/bootstrap/js/bootstrapValidator.js"></script> 
<title>登录</title>
 
</head>
<body>
 <div class="container" style="margin-top: 50px;">
        <div class="row">
            <div class="col-lg-4 col-lg-offset-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Login</h3>
                        <c:if test='${error=="error" }'>
	                        <div class="alert alert-danger" role="alert">
							  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> 
							  Error:incorrect username or password 
							</div>
						</c:if>
                    </div>

                    <div class="panel-body">
                        <form id="loginForm"  action="<c:url value='/j_spring_security_check' />" method="post">
                            <div class="form-group">
                                <label>Username</label>
                                <input type="text" class="form-control" name="username" placeholder="Username" />
                            </div>
 

                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" class="form-control" name="password" placeholder="Password" />
                            </div>

                            <div class="form-group"> 
						        <input type="checkbox" name="remember-me" value="true" th:checked="checked"/>Remember me
						        	
						   </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">Login</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

<script type="text/javascript">
$(document).ready(function() {
    $('#defaultForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                message: 'The username is not valid',
                validators: {
                    notEmpty: {
                        message: 'The username is required and can\'t be empty'
                    } 
                }
            },
             
            password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required and can\'t be empty'
                    } 
                }
            } 
        }
    });
});
</script>
</body>
</html>