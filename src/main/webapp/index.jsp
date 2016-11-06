<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>SmartNest</title>
<meta name="description"
	content="Worthy a Bootstrap-based, Responsive HTML5 Template">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
body {
	background: #444;
	text-align: center;
}

h2 {
	color: #fff;
	display: block;
	text-align: center;
	font-size: 3.5em;
	margin-top: 250px;
}

#gotoconfig {
	display: inline-block;
	width: 200px;
	text-align: center;
	line-height: 50px;
	text-decoration: none;
	color: #444;
	border-radius: 10px;
	margin-top: 50px;
	background: -moz-linear-gradient(top, #ffffff 0%, #cccccc 100%);
	background: -webkit-
		gradient(linear, left top, left bottom, color-stop(0%, #ffffff),
		color- stop(100%, #cccccc));
	background: -webkit-linear-gradient(top, #ffffff 0%, #cccccc 100%);
	background: -o-linear-gradient(top, #ffffff 0%, #cccccc 100%);
	background: -ms-linear-gradient(top, #ffffff 0%, #cccccc 100%);
	background: linear-gradient(top, #ffffff 0%, #cccccc 100%);
}

#gotoconfig:hover {
	background: -moz-linear-gradient(top, #cccccc 0%, #ffffff 100%);
	background: -webkit-
		gradient(linear, left top, left bottom, color-stop(0%, #ffffff),
		color- stop(100%, #cccccc));
	background: -webkit-linear-gradient(top, #cccccc 0%, #ffffff 100%);
	background: -o-linear-gradient(top, #cccccc 0%, #ffffff 100%);
	background: -ms-linear-gradient(top, #cccccc 0%, #ffffff 100%);
	background: linear-gradient(top, #cccccc 0%, #ffffff 100%);
}
</style>
</head>
<body>
	<h2>Welcome to SmartNest!</h2>
	<a id="gotoconfig" href="./config">Config the database</a>
</body>
</html>