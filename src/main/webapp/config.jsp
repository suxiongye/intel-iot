<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>DB Config</title>

<link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<style type="text/css">
body {
	background: #444;
}
#databaseform {
	width: 800px;
	margin: 0 auto;
	color: #fff;
}
h2 {
	color: #fff;
	display: block;
	text-align: center;
	font-size: 3.5em;
	line-height: 150px;
	margin-top: 100px;
}
.control-label {
	font-size: 1.4em;
}
</style>
</head>

<body>
	<div id="databaseform">
	<h2>Config the database</h2>
	<form action="./config" method="post" class="form-horizontal" role="form">
		<div class="form-group">
		    <label for="address" class="col-sm-4 control-label">Address</label>
		    <div class="col-sm-8">
		      <input class="form-control" id="address" name="address" value="115.28.160.247" placeholder="Address">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="port" class="col-sm-4 control-label">Port</label>
		    <div class="col-sm-8">
		      <input class="form-control" id="port" name="port" value="3306" placeholder="Port">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="dbName" class="col-sm-4 control-label">Database Name</label>
		    <div class="col-sm-8">
		      <input class="form-control" id="dbName" name="dbName" value="intel_iot" placeholder="Database Name">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="user" class="col-sm-4 control-label">User</label>
		    <div class="col-sm-8">
		      <input class="form-control" id="user" name="user" value="root" placeholder="User">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="user" class="col-sm-4 control-label">Password</label>
		    <div class="col-sm-8">
		      <input type="password" class="form-control" id="password" name="pwd" value="root" placeholder="Password">
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-8">
		      <button type="submit" class="btn btn-default">Commit</button>
		    </div>
		  </div>
	</form>
	</div>
</body>
</html>
