<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script src="../resources/jquery/jquery-1.12.3.min.js"></script>
<link href="../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"> 
<link href="../resources/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
 <script src="../resources/bootstrap/js/bootstrap.min.js"></script>
 <script src="../resources/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
<title>开盘口</title>
</head>
<body>
 <div class="container-fluid">
	<div class="row-fluid">
		<div class="span12"> 
  			 <jsp:include page="menu.jsp" flush="true">
  			 	<jsp:param name="menu" value="add"/> 
  			 </jsp:include>  
			 </br>
			 </br>
			 <form class="form-horizontal" role="form">
				   <div class="form-group">
				      <label for="title" class="col-sm-2 control-label">盘口</label>
				      <div class="col-sm-8">
				        <textarea class="form-control" id="title" rows="3" maxlength="100" placeholder="写个语言精简，逻辑缜密的盘口吧，不要超过100字哦"></textarea>
				      </div>
				   </div>
				     
		            
		            <div class="form-group">
                		<label for="deadline" class="col-md-2 control-label">截至日期</label>
                		<div class="input-group date form_datetime col-sm-8"   data-date-format="yyyy-mm-dd hh:ii" data-link-field="deadline">
	                    	<input class="form-control" size="10" type="text" value="" readonly>
	                    	<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
							<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                		</div>
						<input type="hidden" id="deadline" value="" /><br/>
            		</div>
		            
		            
		            
				   <div class="form-group">
				      <div class="col-sm-offset-2 col-sm-10">
				         <div class="checkbox">
				            <label>
				               <input type="checkbox"> 开盘无悔，歧义通赔。 开盘有风险。。。。
				            </label>
				         </div>
				      </div>
				   </div>
				   <div class="form-group">
				      <div class="col-sm-offset-2 col-sm-10">
				         <button type="submit" class="btn btn-default">登录</button>
				      </div>
				   </div>
			</form>
   		</div>
	</div>
</div>
 <script type="text/javascript"> 
 $('.form_datetime').datetimepicker({
     //language:  'fr',
     weekStart: 1,
     todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
     showMeridian: 1
 });
  </script>
</body>
</html>