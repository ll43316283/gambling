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
<style type="text/css">
 
.cursor-point{  
cursor: pointer
}
</style>
</head>
<body>
<div class="modal fade" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
        <p>One fine body&hellip;</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->




	<c:set var="menu" scope="request" value="debtList"/>  
 <div class="container-fluid">
	<div class="row-fluid">
		<div class="span12"> 	
			   <jsp:include page="menu.jsp" flush="true">
				 	<jsp:param name="menu" value="${menu}"/> 
				 </jsp:include>  
			  <c:if test="${debts.size()==0 }">
			  	<h3 contenteditable="true">好萧条啊， 连盘口都没有。。。你们蛀虫都不当了？</h3>
			  </c:if>
			  <c:if test="${debts.size()>0 }">
				<table class="table table-hover" contenteditable="true">
					<thead>
						<tr> 
							<th>盘口</th>
							<th>截至时间</th>
							<th>庄家</th>
						</tr>
					</thead>
				
					<tbody > 
						 <c:forEach var="debt"  items="${debts}" >
							<tr class="cursor-point">  
								<td>${debt.title} 
									<input type="hidden" class="debt-id" value="${debt.id}"/>
								</td>
								<td>${debt.deadline} </td> 
								<td>${debt.dealer.userName} </td> 
							</tr> 
						</c:forEach>
					</tbody>
				</table>
			  </c:if>
 
  </div></div></div>
   <script type="text/javascript"> 
   $(document).ready(function() { 
	   var $jq = jQuery.noConflict();
	   $jq('.cursor-point').on("click",function(){   
		   var debtId = $jq(this).find(".debt-id").val();
		   //$jq.get("<%=request.getContextPath()%>/debt/"+debtId);
		  // window.location.href = "<%=request.getContextPath()%>/debt/"+debtId;
		  $jq('#myModal').modal();
	   });
   });
   </script>
</body>
</html>