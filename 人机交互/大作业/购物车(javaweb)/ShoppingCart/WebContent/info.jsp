<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册页面</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
</head>
<body>
	<form class="form-horizontal" role="form" action="/ShoppingCart/info.do" method="post" id="form">
		<div class="form-group">
			<label for="account" class="col-sm-offset-3 col-sm-2 control-label">注册账号</label>
			<div class=" col-sm-3">
				<input type="text" class="form-control" id="account" name="account"
					placeholder="请选择您所需注册的账号">
			</div>
		</div>
		<div class="form-group">
			<label for="nickname" class="col-sm-offset-3 col-sm-2 control-label">昵称</label>
			<div class=" col-sm-3">
				<input type="text" class="form-control" id="nickname" name="nickname"
					placeholder="请选择您所需注册的昵称">
			</div>
		</div>
		<div class="form-group">
			<label for="password1" class="col-sm-offset-3 col-sm-2 control-label">密码</label>
			<div class="col-sm-3">
				<input type="password" class="form-control" id="password1" name="password1"
					placeholder="请填写您的密码">
			</div>
		</div>
		<div class="form-group">
			<label for="password2" class="col-sm-offset-3 col-sm-2 control-label">确认密码</label>
			<div class="col-sm-3">
				<input type="password" class="form-control" id="password2"
					placeholder="请确认您的密码">
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-5 col-sm-8">
				<input type="submit" class="btn btn-lg btn-success" value="注册">
			</div>
		</div>
	</form>
	<script type="text/javascript">
		$("#form").on("submit",function(event){
			var account=document.getElementById("account");
			var nickname=document.getElementById("nickname");
			var password1=document.getElementById("password1");
			var password2=document.getElementById("password2");
			if(account.value==""||nickname.value==""||password1.value==""||password2.value==""){
				alert("信息未填写完整，请填写完整信息后提交");
				event.preventDefault();   //阻止表单提交
				return;
			}
			if(password1.value!=password2.value){
				alert("两次输入的密码必须一致");
				event.preventDefault();   //阻止表单提交
				return;
			}
			event.submit(); // 提交表单
		})
	</script>
</body>
</html>