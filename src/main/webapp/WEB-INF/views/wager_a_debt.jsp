<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <script src="../resources/jquery/jquery-1.9.1.min.js"></script>
<link href="../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"> 
<link href="../resources/bootstrap/css/bootstrapValidator.min.css" rel="stylesheet"> 
<link href="../resources/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
 <script src="../resources/bootstrap/js/bootstrap.min.js"></script>
 <script src="../resources/bootstrap/js/bootstrap-datetimepicker.min.js"></script> 
 <script src="../resources/bootstrap/js/bootstrapValidator.js"></script> 
<title>开盘口</title>
<style type="text/css"> 
</style>
</head>
<body> 
 <div class="container-fluid">
	<div class="row-fluid">
		<div class="span12"> 
  			  
  			<div class="page-header">
				<h1>
					<strong>数040竞猜</strong>
				</h1>
			</div>
  			  
  			  
			 <form class="form-horizontal" id="debtForm" role="form" >
				   <div class="form-group">
				      <label for="title" class="col-md-2 col-sm-2 control-label">盘口</label>
				      <div class="col-md-6 col-sm-6">
				        <textarea class="form-control" id="title" name="title"  readonly>${debt.title}</textarea>
				      </div>
				   </div>
				     
		         
	             <div class="form-group">
			        	<label class="col-sm-2 col-md-2 control-label">截至日期</label> 
			        	<div class="col-md-6 col-sm-6">
		                    <input class="form-control" size="16" type="text" name="deadLine" value="${debt.deadline}" readonly>
		                </div>
				   </div> 
		    </form>
		    
			<form   id="wagerForm" role="form" method="post" action='<c:url value="/transaction"/>'>
		          <div class="form-group">    
					   <div class="col-sm-offset-1  col-md-offset-1 btn-group" data-toggle="buttons">
						    <label class="btn btn-success disabled">
							    <input type="radio" name="bt"  /> 是
							  </label>
						   <c:forEach var="item" items='${predicts.get("Y")}'>
							  <label class="btn btn-success active">
							    <input type="radio" name="options"  value="Y<c:out value='${item }'/>"   /> <c:out value='${item }'/> 
							  </label>
							</c:forEach> 
							 <label class="btn btn-danger disabled">
							    <input type="radio" name="bt"  /> 否
							  </label>
							<c:forEach var="item" items='${predicts.get("N")}'>
							  <label class="btn btn-danger active">
							    <input type="radio" name="options"  value="N<c:out value='${item }'/>" /> <c:out value='${item }'/> 
							  </label>
							</c:forEach> 
						</div>
					</div>
					 
				   <div class="form-group">
				      <div class="col-sm-offset-2 col-sm-10 col-md-offset-2 col-md-10">
				         <button type="submit" class="btn btn-primary">提交</button>
				      </div>
				   </div>
			</form>
   		</div>
	</div>
</div>
 <script type="text/javascript">  
 $('#wagerForm').bootstrapValidator({
	 message: 'This value is not valid',
     feedbackIcons: {
         valid: 'glyphicon glyphicon-ok',
         invalid: 'glyphicon glyphicon-remove',
         validating: 'glyphicon glyphicon-refresh'
     },
     fields: {
    	 options:{ 
             validators: {
                 notEmpty: {
                     message: '你傻啊！赌博不下注啊！'
                 } 
             }
         }
     }
 });
  </script>
</body>
</html>