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
#datetimePicker  {
    top: 0;
    right: -15px;
}
#acceptConvention{  
}
</style>
</head>
<body>
<c:set var="menu" scope="request" value="add"/>  
 <div class="container-fluid">
	<div class="row-fluid">
		<div class="span12"> 
  			 <jsp:include page="menu.jsp" flush="true">
  			 	<jsp:param name="menu" value="${menu}"/> 
  			 </jsp:include>  
			 </br>
			 </br>
			 <form class="form-horizontal" id="newDebtForm" role="form" method="post" action="<c:url value="/debt"/>">
				   <div class="form-group">
				      <label for="title" class="col-md-2 col-sm-2 control-label">盘口</label>
				      <div class="col-md-6 col-sm-6">
				        <textarea class="form-control" id="title" name="title" rows="3" maxlength="100" placeholder="写个语言精简，逻辑缜密的盘口吧，不要超过100字哦"></textarea>
				      </div>
				   </div>
				     
		            
	             <div class="form-group">
			        	<label class="col-sm-2 col-md-2 control-label">截至日期</label>
			     
			        
			        
			        	<div class="input-group date  col-md-6 col-sm-6"  id="datetimePicker"    data-date-format="yyyy-mm-dd hh:ii" data-link-field="deadLineStr">
		                    <input class="form-control" size="16" type="text" name="datetimePicker" value="" readonly> 
							<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
		                </div>
						<input type="hidden" name="deadLineStr" id="deadLineStr" value="" /><br/>
				   </div> 
		             
				   <div class="form-group">
				   	  <div class="col-sm-2 col-md-2 control-label"></div>
				      <div class="col-md-6 col-sm-6"  >
				         <div class="checkbox" > 
				         	<label>
				               <input type="checkbox" name="acceptConvention" id="acceptConvention"> 开盘无悔，歧义通赔。 开盘有风险。。。。 
				        	</label>
				         </div>
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
 
 function timeStamp2String(time){  
	    var datetime = new Date();  
	    datetime.setTime(time);  
	    var year = datetime.getFullYear();  
	    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;  
	    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();  
	    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();  
	    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();  
	    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();  
	    return year + "-" + month + "-" + date+" "+hour+":"+minute;  
} 
 //alert(timeStamp2String((new Date()).getTime()+60*60*1000));
 $(document).ready(function() {
	 $('#datetimePicker').datetimepicker({ 
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
	        showMeridian: 1,
	        startDate:new Date((new Date()).getTime()+60*60*1000),
	        pickerPosition:'bottom-left'
	        
	    });

	 
	 $('#newDebtForm').bootstrapValidator({
	        message: 'This value is not valid',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	title: {
	                message: 'The title is not valid',
	                validators: {
	                    notEmpty: {
	                        message: 'The title is required and can\'t be empty'
	                    },
	                    stringLength: {
	                        min: 6,
	                        max: 100,
	                        message: 'The title must be more than 6 and less than 100 characters long'
	                    } 
	                }
	            },
	            datetimePicker: {
	                validators: {
	                    notEmpty: {
	                        message: 'The date is required and cannot be empty'
	                    } 
	                    
	                }
	            },
	            acceptConvention: {
	            	validators: {
	                    notEmpty: {
	                        message: 'You have to accept the convention '
	                    }
	                }
	            } 
	    }
	 });
	 
	 $('#datetimePicker')
     .on('changeDate show', function (e) { 
         // Revalidate the date when user change it
        // bootstrapValidator.validateField("datetimePicker");
         $('#newDebtForm')
         .bootstrapValidator('updateStatus', 'datetimePicker', 'NOT_VALIDATED')
         .bootstrapValidator('validateField', 'datetimePicker');
     });
	    
	});
 
 
  </script>
</body>
</html>