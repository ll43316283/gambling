<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>math040</title>
</head>
<body>
 
			<div class="page-header">
				<h1>
					<strong>数040竞猜</strong>
				</h1>
			</div>
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#">进行中</a>
				</li>
				<li>
					<a href="#">已完结</a>
				</li>
				<li >
					<a href='<c:url value="/debt/new"/>'>我要开盘</a>
				</li>
				<li class="dropdown ">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">下拉</a>
					<ul class="dropdown-menu">
						<li>
							<a href="#">操作</a>
						</li>
						<li>
							<a href="#">设置栏目</a>
						</li>
						<li>
							<a href="#">更多设置</a>
						</li>
						<li class="divider">
						</li>
						<li>
							<a href="#">分割线</a>
						</li>
					</ul>
				</li>
			</ul>
		<script type="text/javascript">
    	var menu = <%=request.getParameter("menu")%>;
    	alert(menu);
    </script>
</body>
</html>