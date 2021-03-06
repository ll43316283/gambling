<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
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
 
.cursor-point{  
cursor: pointer
}
</style>
</head>
<body> 
 <div class="container-fluid">
	<div class="row-fluid">
		<div class="col-md-12 col-sm-12"> 
  			  
  			<div class="page-header">
				<h1>
					<strong>数040竞猜</strong>
				</h1>
			</div>
  			 <ul class="breadcrumb" contenteditable="true">
				<li ><a  class="cursor-point home" >主页</a> <span class="divider"></span></li>
				<li class="active">详细</li> 
				<li class="pull-right">	<a href='<c:url value='/j_spring_security_logout' />' class="cursor-point">
						<sec:authentication property="principal.username" />     Logout</a></li>
			</ul> 
  			  
			 <form class="form-horizontal" id="debtForm" role="form" >
				   <div class="form-group">
				      <label for="title" class="col-md-2 col-sm-2 control-label">盘口</label>
				      <div class="col-md-6 col-sm-6">
				        <textarea class="form-control" id="title" name="title"  readonly>${debt.title}</textarea>
				      </div>
				   </div>
				   
				   <div class="form-group">
				      <label for="title" class="col-md-2 col-sm-2 control-label">Dealer</label>
				      <div class="col-md-6 col-sm-6">
				       <input class="form-control" size="16" type="text" name="dealer" value="${debt.dealer.userName}"  readonly/>
				      </div>
				   </div>
				     
		         
	             <div class="form-group">
			        	<label class="col-sm-2 col-md-2 control-label">截至日期</label> 
			        	<div class="col-md-6 col-sm-6">
		                    <input class="form-control" size="16" type="text" name="deadLine" value="${debt.deadline}" readonly>
		                </div>
				   </div> 
				   
				   <div class="form-group">
				      <label for="title" class="col-md-2 col-sm-2 control-label">结局</label>
				      <div class="col-md-6 col-sm-6">
				       	<button class="btn-result" disabled>${debt.result}</button>
				      </div>
				   </div>
		    </form>
		    
		    <c:if test="${ transList!=null && transList.size()>0  }">
			    <div class="list-group col-sm-offset-2 col-sm-6 col-md-offset-2 col-md-6" contenteditable="true">
			    	<a class="list-group-item active" href="#">赌博池</a> 
			    	<c:forEach var="trans" items="${transList}" >
						<div class="list-group-item">
							<input type="hidden" class="isDealer" value="<c:out value='${trans.isDealer }'/>"/> 
							<input type="hidden" class="predict" value="<c:out value='${trans.predict }'/>"/> 
							<span class="label  label-as-badge pull-right">
								<c:out value="${trans.winAmount }"/>
							</span>
							<c:out value="${trans.gambler.userName }"/>
						</div>
					</c:forEach> 
				</div>
		    </c:if>
		     
			  
   		</div>
	</div>
</div>
 <script type="text/javascript">  
 $(document).ready(function() {
	 $(".breadcrumb .home").on('click',function(){ 
		 window.location.href = "<%=request.getContextPath()%>/debt/list";
	 });
	 
	 $('.btn-result').each(function(){
		   var bt = $(this);
		   if(bt.html()=="Y"){
			   bt.addClass("btn-success");
		   }
		   else if(bt.html()=="N"){
			   bt.addClass("btn-danger");
		   }else{
			   bt.addClass("btn-warning");
		   }
	   });
	 
	 $("div.list-group-item").each(function(){
		 var l= $(this);
		 var isDealer = l.find(".isDealer").val();
		 var predict = l.find(".predict").val();
		 if("Y"==isDealer){
			 l.find(".label-as-badge").addClass("label-warning");
		 }else if("Y"==predict){
			 l.find(".label-as-badge").addClass("label-success");
		 }else{
			 l.find(".label-as-badge").addClass("label-danger");
		 }
		  
	 })
 });
 
  
  </script>
</body>
</html>