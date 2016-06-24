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
    .padding-top{
         padding-top:40px
        }  
    </style>
      
</head>
<body>
<c:set var="menu" scope="request" value="statistics"/>  
 <div class="container-fluid">
	<div class="row"> 
		 <jsp:include page="menu.jsp" flush="true">
				 	<jsp:param name="menu" value="${menu}"/> 
		</jsp:include> 
			 
			
			
			<div class="col-md-10 col-sm-10 col-xs-10 "> 
			    <div style="width:100%;">
			        <canvas id="canvas"></canvas>
			    </div> 
			</div>
			
			<div class="col-md-2 col-sm-2 col-xs-2 "> 
					<div class="padding-top"></div>
					<div class="btn-group-vertical" id="usernameButtons" data-toggle="buttons">
					 
					  <c:forEach var="nm"  items="${nameList}" >
						  <label class="btn btn-default">
						     <input type="checkbox" name="usernames"   value="${nm }" autocomplete="off" >${nm }
						  </label>
					   </c:forEach>
					</div>
			</div>
	</div>
</div>


    <script>
         
        
        var randomColorFactor = function() {
            return Math.round(Math.random() * 255);
        };
        var randomColor = function(opacity) {
            return 'rgba(' + randomColorFactor() + ',' + randomColorFactor() + ',' + randomColorFactor() + ',' + (opacity || '.3') + ')';
        };
        
        var allData =  <c:out value='${allscores}' escapeXml="false"/>;
        
        var sequenceBetIds = <c:out value='${sequenceBetIds}' escapeXml="false"/>; 
       
        var names = <c:out value='${names}' escapeXml="false"/>; 
       
       var getBetIndex = function(betId){
       		var result=-1;
       	  $.each(sequenceBetIds,function(i,sequenceBetId){
       	  	if(sequenceBetId == betId){
       	  		result = i;
       	  		return;
       	  	}
       	  });
       	  return result;
       };
       
      
       
       var filterDataset = function(names){
   					var result = []; 
            if(!names || names.length<=0){
            	return result;
            }  
            $.each(names, function(i,name){
            	$.each(allData, function(i,dataset){
            		if(name == dataset.name){
            			var newObject = jQuery.extend(true, {}, dataset);
            			result.push(newObject);
            		}
            	});
            });
            return result;
       };
       
       
       
       var getDisplayLabel = function(filterDatasets){
       		var result = [];
       		if(filterDatasets.length<=0){
       			return result;
       		}
       		var betids = [];
       		$.each(filterDatasets,function(i,dataset){
       			$.each(dataset.datasets,function(i,bet){ 
       				if($.inArray(bet.betId,betids)<0){
       					betids.push(bet.betId);
       					result.push(bet);
       				}
       			}); 
       		});
       		return sortBetsLabel(result);
       }; 
        
       var sortBetsLabel = function(bets){
       	 if(!bets || bets.length<=0){
       	    return [];	
       	 }
       	 result = bets.sort(function(a,b){
       	 	 return getBetIndex(a.betId)-  getBetIndex(b.betId);
       	 });
       	 return result;
       };
       
       //console.log(getDisplayLabel(filterDataset(['qian'])));
       
       var getBetById = function(bets,betLabel){
       	 var result = null; 
       	 $.each(bets,function(i,bet){
       	 		if(bet.betId == betLabel.betId){
       	 			result = bet;
       	 			return;
       	 		} 
       	 });
       	 if(result==null){
       	 	 result = {
       	 	 	 betId:betLabel.betId,
       	 	 	 title:betLabel.title,
       	 	 	 amount:0
       	 	 }
       	 }
       	 return result;
       };
       
       var sortBetsDataSet = function(betsLabel,filterDatasets){ 
       		$.each(filterDatasets,function(i, dataset){
       			var sortedBets = []; 
       			var bets = dataset.datasets;
       			$.each(betsLabel,function(j,betLabel){
       				 sortedBets.push(getBetById(bets,betLabel)); 
       			});
       			dataset.datasets = sortedBets;
       		});
       		return filterDatasets;
       };
       
        var config = {
            type: 'line',
            data: {
                labels: [],
                datasets: []
            },
            options: {
                responsive: true,
                legend: { 
                    position: 'bottom',
                    onClick:null,
                },
                hover: {
                    mode: 'label'
                },
                scales: {
                    xAxes: [{
                        display: false,
                        scaleLabel: {
                            display: true,
                            labelString: 'Bet'
                        }
                    }],
                    yAxes: [{
                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: 'Amount'
                        }
                    }]
                },
                title: {
                    display: true,
                    text: '战绩图'
                },
                animation: {
                        onComplete: function () {
                            var chartInstance = this.chart;
                            var ctx = chartInstance.ctx;
                            ctx.textAlign = "center";

                            Chart.helpers.each(this.data.datasets.forEach(function (dataset, i) {
                                var meta = chartInstance.controller.getDatasetMeta(i);
                                Chart.helpers.each(meta.data.forEach(function (line, index) {
                                	   //console.log(dataset.label);
                                    ctx.fillText(dataset.data[index],
                                     line._model.x, line._model.y  );
                                }),this)
                            }),this);
                        }
                      }
            }
        }; 
       
       var generateConfigs = function(selectedNames){
       	    config.data.labels = [];
       			config.data.datasets = [];
       			var datasets = filterDataset(selectedNames); 
       			console.log(datasets);
            var betsLabel = getDisplayLabel(datasets);
            datasets = sortBetsDataSet(betsLabel,datasets); 
       			$.each(datasets,function(i,dataset){
		         	  var chart_dataset =  {
		         	  		label: dataset.name,
		                    data: [],
		                    fill: false,
		         	  }; 
			         $.each(dataset.datasets,function(j,bet){ 
			         		 config.data.labels.push(bet.title); 
				        	 chart_dataset.data.push(bet.amount);  
			         })
			         config.data.datasets.push(chart_dataset); 
        		}); 
        
		        $.each(config.data.datasets, function(i, dataset) {
		            var background = randomColor(0.5);
		            dataset.borderColor = background;
		            dataset.backgroundColor = background;
		            dataset.pointBorderColor = background;
		            dataset.pointBackgroundColor = background;
		            dataset.pointBorderWidth = 1;
		            dataset.pointRadius=8;
		        });
       };
                
         
	    $(document).ready(function(){ 
					 
					
            var selectedNames = []; 
            generateConfigs(selectedNames); 
            
		      var ctx = document.getElementById("canvas").getContext("2d");
	          window.myLine = new Chart(ctx, config);
	          
	         $(".btn").change(function(){
	              var lb = $(this);
	              	lb.removeClass("btn-primary");
	              	lb.removeClass("btn-default");
	              if(lb.find("input[name='usernames']")[0].checked){
	            	  lb.addClass("btn-primary");
	              }else{
	            	  lb.addClass("btn-default");
	              } 
	              var usernames = $("#usernameButtons input:checked").map(function(){
	              	return $(this).val();
	              }).get() ; 
	             //console.log(usernames);
	             generateConfigs(usernames);
	             window.myLine.update();
	         });
        
		}); 

       
 
    </script>
</body>


</html>