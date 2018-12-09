<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录界面</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>
<body>
	<center>
		<h3>login</h3>
		<form action="/ShoppingCart/login.do" method="post"
			class="form-horizontal">
			<div class="form-group">
				<label for="account" class="col-sm-5 control-label">账号:</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="account" name="account"
						placeholder="请输入账号">
				</div>
				<div class="col-sm-1">
					<button type="button" class="btn btn-lg btn-info" onclick="info()">注册账号</button>
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-5 control-label">密码:</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="password" name="password"
						placeholder="请输入密码">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-1">
					<input type="reset" value="重置" class="btn btn-lg btn-warning">
					
				</div>
				<div class="col-sm-1">
					<input type="submit" value="登录" class="btn btn-lg btn-success">
				</div>
			</div>
		</form>
		<script type="text/javascript">
			function info(){
				window.location.href="/ShoppingCart/info.jsp"; 
			}
		</script>
	</center>
</body>
</html>