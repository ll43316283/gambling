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
 <script src="../resources/d3/d3.v3.min.js"></script>
 <script src="../resources/d3/liquidFillGauge.js"></script>
<title>math040</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
 .liquidFillGaugeText { font-family: Helvetica; font-weight: bold; }
</style>
</head>
<body>
 



 <c:set var="menu" scope="request" value="statistics"/>  
 <div class="container-fluid">
	<div class="row-fluid">
		<div class="span12"> 	
			   <jsp:include page="menu.jsp" flush="true">
				 	<jsp:param name="menu" value="${menu}"/> 
				 </jsp:include>   
			<div class="row-fluid">	
				<div class="span6">
					<table class="table" contenteditable="true">
						<thead>
						<tr> 
						<th>id</th>
						<th>胜率</th> 
						</tr>
						</thead>
						<tbody>
						<tr>
						<td>liang</td>
						<td><svg id="fillgauge1"   height="50"  onclick="gauge1.update(NewValue());"></svg></td> 
						</tr> 
						</tbody>
					</table>
				</div> 
				<div class="span6">
						sdfsdf
				</div>
			 </div>
  		</div>
  	</div>
  </div>
   <script type="text/javascript"> 
   var gauge1 = loadLiquidFillGauge("fillgauge1", 55);
   function NewValue(){
       if(Math.random() > .5){
           return Math.round(Math.random()*100);
       } else {
           return (Math.random()*100).toFixed(1);
       }
   }
   </script>
</body>
</html>