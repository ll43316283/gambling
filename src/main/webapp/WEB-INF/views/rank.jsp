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
 <script src="../resources/chart/Chart.bundle.js"></script> 
<title>math040</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style type="text/css">
    canvas{
        -moz-user-select: none;
        -webkit-user-select: none;
        -ms-user-select: none;
    }
     #chartjs-tooltip {
      opacity: 1;
      position: absolute;
      background: rgba(0, 0, 0, .7);
      color: white;
      border-radius: 3px;
      -webkit-transition: all .1s ease;
      transition: all .1s ease;
      pointer-events: none;
      -webkit-transform: translate(-50%, 0);
      transform: translate(-50%, 0);
    }
    .chartjs-tooltip-key {
      display: inline-block;
      width: 10px;
      height: 10px;
    }
    .star-color{
         color: #FFCC33
        }
    .b-color{
         color: red
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
		
		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingOne">
				      <h4 class="panel-title">
				        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
				          	 冒泡图
				        </a>
				      </h4>
				    </div>
				    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
				       <div  class="panel-body " >
						 	<canvas id="canvas"  ></canvas>
						</div>
				    </div>
				  </div>
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingTwo">
				      <h4 class="panel-title">
				        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
				          	 表格
				        </a>
				      </h4>
				    </div>
				    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
				      <div class="panel-body">
				        <table class="table table-hover" contenteditable="true">
								<thead>
									<tr> 
										<th>赌客</th>
										<th>筹码</th>
										<th>胜率</th> 
									</tr>
								</thead>
							
								<tbody > 
									 <c:forEach var="us"  items="${usList}" >
										<tr class="cursor-point">  
											<td>
												<c:forEach var="title"  items="${us.titles}" >
												<c:if test='${title.code=="RP" }'>
													<span class="glyphicon glyphicon-star-empty star-color" 
													  aria-hidden="true" 
													  data-toggle="collapse" href="#richPerson" aria-expanded="false" aria-controls="richPerson">
													</span>
													 <div class="collapse" id="richPerson">
													  <div class="well">
													   ${title.description }
													  </div>
												 </div>
												</c:if> 
												<c:if test='${title.code=="HR" }'>
												 <span class="glyphicon glyphicon-bitcoin b-color" 
												  aria-hidden="true"
												  data-toggle="collapse" href="#hierRate" aria-expanded="false" aria-controls="hierRate"
												  > 
												</span>
												<div class="collapse" id="hierRate">
													  <div class="well">
													   ${title.description }
													  </div>
												 </div>
												</c:if>
												</c:forEach>
												${us.gambler.userName}  
												
												 
											
											</td>
											<td>${us.amount} </td> 
											<td>${us.winningRate} </td> 
										</tr> 
									</c:forEach>
								</tbody>
						 </table>
						
						 
				       </div>
				    </div>
				  </div> 
			</div> 
		</div>
	</div>
 </div>
    <script> 
     
 		
        var randomColorFactor = function() {
            return Math.round(Math.random() * 255);
        };
        var randomColor = function() {
            return 'rgba(' + randomColorFactor() + ',' + randomColorFactor() + ',' + randomColorFactor() + ',.7)';
        };
        
    	 var rankAndradiusMap = { 
    	 							1:30, 2:20, 3:15, 4:10, 5:9, 6:8, 7:7,
    	 							8:6, 9:5, 10:4, 11:3, 12:2, 13:1
    	 						};
    	 
    	 var getRadius = function( rank){
    		 var result = 1;
    		 if(rankAndradiusMap[rank]){
    			 result = rankAndradiusMap[rank];
    		 }
    		 console.log(result);
    		 return result;
    	 };
    	
    	datasets=  <c:out value='${ranks}' escapeXml="false"/>;
    	  
    	 var bubbleChartData = {
            animation: {
                duration: 10000
            },
            datasets: [ ] 
        };
        
    	  for(var i=0;i<datasets.length;i++){
    	  
	    	  var tempdata = datasets[i];
	    	  
	    	  var dataset={
	    	     label : 	tempdata.name,
	    	     backgroundColor : randomColor(),
	    	     data  :	[
	    	     						{
	    	     							y:	tempdata.score,
	    	     							x:  tempdata.rate,
	    	     							r:  getRadius(tempdata.rank)
	    	     						}
	    	     				  ]
	    	  }; 
	    	  
	    	 
	    	  
	    	  bubbleChartData.datasets.push(dataset);
    	  
    	  }
    	 
    	var tooltipBody = function (o){
    			var nm = o.split(":")[0];
    			var ov = o.split(":")[1].trim(); 
    			var no_kuohao = ov.substring(1).substring(0,ov.substring(1).length-1);
    			var rate = no_kuohao.split(",")[0]+"%";
    			var score = no_kuohao.split(",")[1];
    			return nm+"  rate: "+ rate+"	score: "+score;
    	}
    	  
    	var customTooltips = function(tooltip) {
				      // Tooltip Element
				      var tooltipEl = $('#chartjs-tooltip');
				
				      if (!tooltipEl[0]) {
				        $('body').append('<div id="chartjs-tooltip"></div>');
				        tooltipEl = $('#chartjs-tooltip');
				      }
				
				      // Hide if no tooltip
				      if (!tooltip.opacity) {
				        tooltipEl.css({
				          opacity: 0
				        });
				        $('.chartjs-wrap canvas')
				          .each(function(index, el) {
				            $(el).css('cursor', 'default');
				          });
				        return;
				      }
				
				      $(this._chart.canvas).css('cursor', 'pointer');
				
				      // Set caret Position
				      tooltipEl.removeClass('above below no-transform');
				      if (tooltip.yAlign) {
				        tooltipEl.addClass(tooltip.yAlign);
				      } else {
				        tooltipEl.addClass('no-transform');
				      }
				
				      // Set Text
				      if (tooltip.body) {
				      	var bd = tooltipBody(tooltip.body[0]);
				        var innerHtml = [
				          (tooltip.beforeTitle || []).join('\n'), (tooltip.title || []).join('\n'), (tooltip.afterTitle || []).join('\n'), (tooltip.beforeBody || []).join('\n'), bd, (tooltip.afterBody || []).join('\n'), (tooltip.beforeFooter || [])
				          .join('\n'), (tooltip.footer || []).join('\n'), (tooltip.afterFooter || []).join('\n')
				        ];
				        
				        tooltipEl.html(innerHtml.join('\n'));
				      }
				
				      // Find Y Location on page
				      var top = 0;
				      if (tooltip.yAlign) {
				        if (tooltip.yAlign == 'above') {
				          top = tooltip.y - tooltip.caretHeight - tooltip.caretPadding;
				        } else {
				          top = tooltip.y + tooltip.caretHeight + tooltip.caretPadding;
				        }
				      }
				
				      var position = $(this._chart.canvas)[0].getBoundingClientRect();
				
				      // Display, position, and set styles for font
				      tooltipEl.css({
				        opacity: 1,
				        width: tooltip.width ? (tooltip.width + 'px') : 'auto',
				        left: position.left + tooltip.x + 'px',
				        top: position.top + top + 'px',
				        fontFamily: tooltip._fontFamily,
				        fontSize: tooltip.fontSize,
				        fontStyle: tooltip._fontStyle,
				        padding: tooltip.yPadding + 'px ' + tooltip.xPadding + 'px',
				      });
    };
			  
	function testclick(obj){
			console.log(obj);
	}
        window.onload = function() {
            var ctx = document.getElementById("canvas").getContext("2d");
            window.myChart = new Chart(ctx, {
                type: 'bubble',
                data: bubbleChartData,
                options: {
                    responsive: true,
                    title:{
                        display:true,
                        text:'ranking'
                    },
                    legend:{
                    	position:'bottom'
                    },
                    
                    onClick: testclick,
                     tooltips: {
                     	  enabled: false,
                     		custom:customTooltips,
							          mode: 'single'
							        }, 
							        scales: {
							        	 yAxes: [{
							            scaleLabel: {
							              display: true,
							              labelString: 'score'
							            }
							          }],
							          xAxes: [{
							            scaleLabel: {
							              display: true,
							              labelString: 'rate%'
							            }
							          }]
							         
							        }, 
							        
							        animation: {
                        onComplete: function () {
                            var chartInstance = this.chart;
                            var ctx = chartInstance.ctx;
                            ctx.textAlign = "center";
                            Chart.helpers.each(this.data.datasets.forEach(function (dataset, i) {
                                var meta = chartInstance.controller.getDatasetMeta(i);
                                Chart.helpers.each(meta.data.forEach(function (bubble, index) {
                                	   //console.log(dataset.label);
                                    ctx.fillText(dataset.label + " :"+dataset.data[index].x+"% ~ "+dataset.data[index].y,
                                     bubble._model.x, bubble._model.y + dataset.data[index].r+ 10);
                                }),this)
                            }),this);
                        }
                      }
                }
            });
        };
        
    </script>
</body>

</html>