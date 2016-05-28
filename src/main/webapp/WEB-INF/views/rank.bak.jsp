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
  <style>
    		 
    	  .table tr td {
    	  	font-size: 20px;
    	  	font-weight:bold;
    	   text-align:center;	 
    	   height:80px;
    	  }
    	  .table thead tr th {
    	   text-align:center	
    	  }
    	  .table tr td:first-child{
    	    padding-top:25px;
    	  }
        .liquidFillGaugeText { font-family: Helvetica; font-weight: bold; }
        
        .star-color{
         color: #FFCC33
        }
    </style>
</head>
<body>
 



 <c:set var="menu" scope="request" value="statistics"/>  
 <div class="container-fluid">
	<div class="row">
		<div class="col-md-12 col-sm-12">
		 <jsp:include page="menu.jsp" flush="true">
				 	<jsp:param name="menu" value="${menu}"/> 
				 </jsp:include> 
			<div class="row">
				<div class="col-md-6 col-sm-6">
					<table class="table table-striped table-condensed">
						<thead>
							<tr>
								<th>
									num
								</th>
								<th>
									rate
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<span class="glyphicon glyphicon-star-empty star-color" 
									  aria-hidden="true">
									</span>  song
								</td>
								<td>
								 <svg id="fillgauge1"  height="100%" onclick="gauge1.update(NewValue());"></svg>
								</td>
							</tr>
							<tr>
								<td>
									lee
								</td>
								<td>
								 <svg id="fillgauge2"  height="100%" onclick="gauge2.update(NewValue());"></svg>
								</td>
							</tr>
							
							<tr>
								<td>
									zhantang
								</td>
								<td>
								 <svg id="fillgauge3"  height="100%" onclick="gauge3.update(NewValue());"></svg>
								</td>
							</tr>
							
						</tbody>
					</table>
				</div>
				<div class="col-md-6 col-sm-6">
					<p>
						<em>Git</em>sdfsdfsdfs<strong>Linus Torvalds</strong>tghuishf asjdfajlfjasljfb sd fklsajfoiasf skfsdufiokj sdwsdfkjskljfkl;jpiulk sdf dfj s
					</p>
				</div>
			</div>
		</div>
	</div>
</div>
<script language="JavaScript">
	
	  var top1Config = liquidFillGaugeDefaultSettings();
    top1Config.circleColor = "#FFCC33";
    top1Config.textColor = "#FF3300";
    top1Config.waveTextColor = "#FF3300";
    top1Config.waveColor = "#FFCC33";
    top1Config.circleThickness = 0.2;
    top1Config.textVertPosition = 0.5;
    top1Config.waveAnimateTime = 1000;
    
    var gauge1 = loadLiquidFillGauge("fillgauge1", 55,top1Config);
    
    var top2Config = liquidFillGaugeDefaultSettings();
    top2Config.circleColor = "#CCCCCC";
    top2Config.textColor = "#FF3300";
    top2Config.waveTextColor = "#FF3300";
    top2Config.waveColor = "#CCCCCC";
    top2Config.circleThickness = 0.2;
    top2Config.textVertPosition = 0.5;
    top2Config.waveAnimateTime = 1000; 
    
    
    var gauge2 = loadLiquidFillGauge("fillgauge2", 41,top2Config);
    
    var top3Config = liquidFillGaugeDefaultSettings();
    top3Config.circleColor = "#CC6633";
    top3Config.textColor = "#FF3300";
    top3Config.waveTextColor = "#FF3300";
    top3Config.waveColor = "#CC6633";
    top3Config.circleThickness = 0.2;
    top3Config.textVertPosition = 0.5;
    top3Config.waveAnimateTime = 1000; 
    
    
    var gauge3 = loadLiquidFillGauge("fillgauge3", 40,top3Config);
    
</script>
</body>
</html>